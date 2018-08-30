<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../Inc.jsp"></jsp:include>
<style type="text/css">
body {
		margin: 8px;
		padding: 0;
	}
	.td_right{
		width: 30%;
	}
	
	.td_left{
		background: #b6c1ca;
		width:20%;
		text-align: right;
		height: 30px;
	}
	 table tr td{
		    border-right: solid 1px #c0cdd8;
  			border-bottom: solid 1px #c0cdd8;
			padding:3px 0;
			font-size: 15px;
	}
	/* 设置datagrid表格td高度 */
	.datagrid-btable tr td{
		height: 30px;
	}

	 input{
		padding:2px 0;
		margin-left:2px;
		border: #c0cdd8 1px solid;
		outline-color:#c0cdd8 ;
		width: 80%;
		font-size: 15px;
	}

</style>
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
	
	 //加载表格数据
	 $('#dg').datagrid({    
	    url:'repair_findAll.action', 
	    pagination:'true',
	    singleSelect:'true',//只允许选取一行
	    fitColumns:true,//宽度自适应
	    rownumbers:true,   
	    toolbar: '#tb_user',
	    columns:[[{field:'carnumber',title:'车牌号',align:'center',width:12},
	        {field:'content',title:'维修项目',align:'center',width:15},
	        {field:'km',title:'维修公里数',align:'center',width:15},
	        {field:'retime',title:'维修时间',align:'center',width:15},
	        {field:'null',title:'操作',align:'center',width:20,
	        	formatter: function(value,row,index){
		        	var str='';
					str='<a id="adel" href="javascript:momoDel('+row.id+');">删除记录</a>';
					return str;	
				}
			}     
		]]    
	 });
	 
	  //添加
	$('#tb_user_add').on('click',function(){
		//获取当前时间开始
		var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        var hh = date.getHours();            //时
        var mm = date.getMinutes();          //分
        var ss = date.getSeconds();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
       
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        //获取当前时间结束
        //初始化赋值
        $('#retime').datebox('setValue', currentdate);
	    initCombox02($('#carid'),'publiccar_findcar.action',null,-1);
		$('#tb_user_add_dialog').dialog('open'); //点击新增按钮弹出新增页面
	});
	//保存
	$('#tb_user_add_dialog_bts').on('click',function(){
		$('#user_addForm').form('submit', {    
			    url:'repair_add.action',     
			    success:function(data){  
			    //将 data转换成json  原因：从后台传递过来的数据，都是字符串  
			        var obj=$.parseJSON(data);
			        if(obj.success){
						window.location.reload();//重新载入当前页面
			        }
			        $.messager.show({title:'提示',msg:obj.message});
			    }    
		});
	});
	//取消
	$('#tb_user_add_dialog_btc').on('click',function(){
		$('#tb_user_add_dialog').dialog('close');
	});	
	
	//保存
	$('#tb_user_upd_dialog_bts').on('click',function(){
			$('#user_updForm').form('submit', {    
			    url:'repair_upd.action',     
			    success:function(data){
			        var obj=$.parseJSON(data);
			        if(obj.success){
			        	$('#user_updForm').form('clear');
						$('#dg_memo').datagrid('reload');
						$('#tb_user_upd_dialog').dialog('close');	
			        }
			        $.messager.show({title:'提示',msg:obj.message});
			    }    
			}); 
	});	
	$('#tb_user_upd_dialog_btc').on('click',function(){
		$('#tb_user_upd_dialog').dialog('close');
	});
	
	//搜索框模糊查询
	$('#btn').bind('click', function(){
		var starttime = document.getElementById("starttime").value;
		var endtime = document.getElementById("endtime").value;
		var carnumber = $('#carnumber').val();
		//把字符串格式转换为日期类
		var startdate = new Date(Date.parse(starttime));
		var enddate = new Date(Date.parse(endtime));
		if(startdate > enddate){
					$.messager.alert('提示','起始日期不能大于结束日期'); 
		}else{
			$.ajax({
				url: 'repair_findBytime.action',
				data:{carnumber:carnumber,idStart:starttime,idEnd:endtime},
				type:"post",
			    dataType:"json",   
				success: function(data) {
					if(data.length == 0){
						$.messager.alert('提示','根据查询条件，数据库无任何记录');
					}else{
						$('#dg').datagrid('reload',{carnumber:carnumber,idStart:starttime,idEnd:endtime});//重新加载
					}
				}
			});  	 
		}
	});
});

//删除
function momoDel(id){
	$.messager.confirm('确认','是否删除此条信息!',function(r){    
		if(r){    
			$.post('repair_del.action',{"id":id},function(data){
				var obj=$.parseJSON(data);
				$.messager.show({title:'提示',msg:obj.message});
				$('#dg').datagrid('reload');//重新加载
			});    
 		}    
	}); 
}
</script>

<body>
	<!-- 用户数据表格 -->
	<table id="dg"></table>
	<!-- 定义菜单 -->
	<div id="tb_user">
		<a id="tb_user_add" style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
		<div class="datagrid-btn-separator"></div>
		<!-- 创建搜索框 -->
		车牌号<input id="carnumber" placeholder="车牌号" style="width:200px; border: #ABCDEF 1px solid;outline-color:#96c4f3 ;"></input>&nbsp;&nbsp;&nbsp;
		起始时间：<input id="starttime"  type="text"  style="width:200px;border: #ABCDEF 1px solid;outline-color:#96c4f3 ;"  placeholder="起始时间" class="Wdate" onfocus="WdatePicker({lang:'zh-cn',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>&nbsp;------
		结束时间：<input id="endtime"  type="text"  style="width:200px;border: #ABCDEF 1px solid;outline-color:#96c4f3 ;"  placeholder="结束时间" class="Wdate" onfocus="WdatePicker({lang:'zh-cn',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
		<button id="btn" class="easyui-linkbutton" style="width:50px;">搜索</button>
	</div>
	<!-- 定义添加窗口-->
	<div style="display: none;">
		<div id="tb_user_add_dialog" class="easyui-dialog" title="车辆维修记录添加" style="width:500px;height:300px;align:center;top:1px;"   
		       data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_user_add_dialog_bt'">   
		       <form id="user_addForm" method="post">
		       		<br><br><br>
		       		<table style="width: 90%;margin-left:5%;border: solid 1px #c0cdd8;border-right: none;border-bottom: none;" cellpadding="1" cellspacing="0">
			    		<tr>
							<td class="td_left">车辆:
								<font color="red">*</font>
							</td>
							<td class="td_right">
								<select id="carid" editable="false" name="carnumber" class="easyui-combobox" style="width:280px;height: 25px;margin-left: 15px;">   
							     
								</select>
							</td>
						</tr>   		
			    		<tr>
							<td class="td_left">维修项目:
								<font color="red">*</font>
							</td>
							<td class="td_right">
								<input id="content" name="content" type="text" style="width:87%;" >
							</td>
						</tr>	
			    		<tr>
							<td class="td_left">维修公里数:
								<font color="red">*</font>
							</td>
							<td class="td_right">
								<input id="km" name="km" type="text" style="width:87%;">
							</td>
						</tr>		    		
			    		<tr>
							<td class="td_left">维修时间:
								<font color="red">*</font>
							</td>
							<td class="td_right">
								<input id="retime" name="retime" editable="false" type="text" style="width:280.5px;height: 25px;maxHeight:300px" class="easyui-datebox" value="1">
							</td>
						</tr>	    		
					</table>	
		       </form>
		</div>
		<div id="tb_user_add_dialog_bt">
			<a id="tb_user_add_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
			<a id="tb_user_add_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
		</div>
	</div>
</body>

