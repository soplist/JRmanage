<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../Inc.jsp"></jsp:include>
<script type="text/javascript">

$(function(){
/* 页面自适应 */
	$(window).resize(function () {
	      $('#dg').datagrid('resize', {width: $(window).width() - 50,}).datagrid('resize', {
	      width: $(window).width() - 50,});
	 });
		/* 验证session开始 */
		var curPath = window.document.location.href;
	 	var pathName = window.document.location.pathname;
	 	var	basePath = curPath.substring(0,curPath.indexOf(pathName)) + "/";
	 	var s="<%=session.getAttribute("login_users")%>"; 
	 	if(s=="null"){ 
	 		top.location.href = location.href=basePath+"JRmanage";
	 	}
		/* 验证session开始 */
		//加载表格数据
		$('#dg_user').datagrid({    
	    url:'log_findAllLogs.action', 
	    pagination:'true',
	    fitColumns:'true',
	    rownumbers:true,   
	    toolbar: '#tb_user',
	    columns:[[	
	        {checkbox:true},             
	        {field:'id',title:'编号',width:10}, 
	        {field:'createtime',title:'操作日期',width:25},
	        {field:'title',title:'操作内容',width:15},
	        {field:'isfinish',title:'操作结果',width:10,
	        	formatter: function(value,row,index){
					if (row.isfinish=='1'){
						return '<span style="color:green">成功</span>';
					} else {
						return '<span style="color:red">失败</span>';
					}
				}
	        },
	        {field:'people',title:'操作用户',width:20},   
	        {field:'null',title:'操作',width:20,
	        	formatter: function(value,row,index){
		        	var str='';
					str='<a id="adel" href="javascript:logDel('+row.id+');">删除记录</a>';
					return str;	
				}}  
		    ]]    
		});
		
		//模糊查询
		$('#btn').bind('click', function(){
		var starttime = document.getElementById("starttime").value;
		var endtime = document.getElementById("endtime").value;
		//把字符串格式转换为日期类
		var startdate = new Date(Date.parse(starttime));
		var enddate = new Date(Date.parse(endtime));
		if(starttime == "" || endtime == ""){
			$.messager.alert('提示','请输入查询日期'); 
		}else if(startdate >= enddate){
			$.messager.alert('提示','起始日期不能大于结束日期'); 
		}else{
		    var idStart="",idEnd="";
			$.ajax({
				url: 'log_findlogBytime.action',
				data:{idStart:starttime,idEnd:endtime},
				type:"post",
			    dataType:"json",   
				success: function(data) {
					if(data.length == 0){
						$.messager.alert('提示','根据查询条件，数据库无任何记录');
					}else{
						$('#dg_user').datagrid('reload',{idStart:starttime,idEnd:endtime});//重新加载
					}
				}
			});  	 
		}
		});
});

	
	//删除
	function logDel(id){
		$.messager.confirm('确认','警告：删除后无法恢复!',function(r){    
		    if (r){    
		        $.post('log_logDel.action',{"id":id},function(data){
					var obj=$.parseJSON(data);
			        $.messager.show({title:'提示',msg:obj.message});
					$('#dg_user').datagrid('reload');//重新加载
				});    
		    }    
		}); 
	}
</script>


<!-- 当前用户的lname -->
&nbsp;&nbsp;当前用户帐号：<span id="sp_lname" >${login_users.lname}</span>
<br><br>
<!-- 用户数据表格 -->
<table id="dg_user"></table>
<br><br><br>
&nbsp;&nbsp;说明：<br>
&nbsp;&nbsp;1、所有的日志记录由系统自动生成；<br>
&nbsp;&nbsp;<span style="color: red">2、日志永久保存，仅管理员有删除权限；</span><br>
&nbsp;&nbsp;3，更多问题请联系超级管理员<br><hr><br>

<!-- 定义菜单 -->
<div id="tb_user">
	<div class="datagrid-btn-separator"></div>
	<!-- 创建搜索框 -->
	起始时间：<input id="starttime"  type="text"  style="width:150px;"  placeholder="此处选择起始时间" class="Wdate" onfocus="WdatePicker({lang:'zh-cn',skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>&nbsp;------
	结束时间：<input id="endtime"  type="text"  style="width:150px;"  placeholder="此处选择结束时间" class="Wdate" onfocus="WdatePicker({lang:'zh-cn',skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
	<button id="btn" class="easyui-linkbutton" style="width:50px;">搜索</button>
</div>