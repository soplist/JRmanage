<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../Inc.jsp"></jsp:include>
<style type="text/css">

	.td_left{
		background: #b6c1ca;
		width:20%;
		text-align: right;
	}
	.td_right{
		width: 30%;
	}
	 table tr td{
		    border-right: solid 1px #c0cdd8;
  			border-bottom: solid 1px #c0cdd8;
			padding:3px 0;
			height: 40px;
			font-size: 18px;
	}
	#td_left{
		background: #D6E4F0;
		width:20%;
		text-align: right;
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
	 $('#dg_goodsin').datagrid({    
	    url:'gd_findAll.action', 
	    pagination:'true',
	    singleSelect:'true',//只允许选取一行
	    fitColumns:true,//宽度自适应
	    rownumbers:true,   
	    toolbar: '#tb_user',
	    columns:[[
	        {checkbox:true},     
	        {field:'name',title:'物品名称',align:'center',width:12},
	        {field:'innum',title:'入库数量',align:'center',width:15},
	        {field:'renum',title:'剩余数量',align:'center',width:15},
	        {field:'createtime',title:'记录时间',align:'center',width:15},
	        {field:'null',title:'操作',width:20,
	        	formatter: function(value,row,index){
		        	var str='';
					str='<a id="adel" href="javascript:GoodinDel('+row.id+');">删除记录</a>';
					return str;	
				}
			}  
		]]    
	 });
	 
	 //添加
	$('#tb_user_add').on('click',function(){
		$('#tb_user_add_dialog').dialog('open'); //点击新增按钮弹出新增页面
	});
	//保存
	$('#tb_user_add_dialog_bts').on('click',function(){
		$('#user_addForm').form('submit', {    
			    url:'gd_add.action',     
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
	
	//修改
	$('#tb_user_ed').on('click',function(){
		var rows=$('#dg_goodsin').datagrid('getChecked'); //在复选框呗选中的时候返回所有行  
		if(rows.length==0){
				 $.messager.show({title:'提示',msg:'请勾选要修改的配置项'});
		}else if(rows.length!=1){
				$.messager.show({title:'提示',msg:'修改时只能选择一个配置项'});
		}else{
				$('#tb_user_upd_dialog').dialog('open');
				$('#user_updForm #id').val(rows[0].id);
				$('#user_updForm #name').val(rows[0].name);
				$('#user_updForm #innum').val(rows[0].innum);	
				$('#user_updForm #renum').val(rows[0].renum);	
		} 
	});
	//保存
	$('#tb_user_upd_dialog_bts').on('click',function(){
			$('#user_updForm').form('submit', {    
			    url:'gd_upd.action',     
			    success:function(data){
			        var obj=$.parseJSON(data);
			        if(obj.success){
			        	$('#user_updForm').form('clear');
						$('#dg_goodsin').datagrid('reload');
						$('#tb_user_upd_dialog').dialog('close');	
			        }
			        $.messager.show({title:'提示',msg:obj.message});
			    }    
			}); 
	});	
	$('#tb_user_upd_dialog_btc').on('click',function(){
		$('#tb_user_upd_dialog').dialog('close');
	});
	
	
	//搜索框查询
	$('#btn').bind('click', function(){
		var lname = document.getElementById("lname").value;
		if(lname == ""){
			$.messager.alert('提示','请输入查询名称'); 
		}else{
			$.ajax({
				url: 'gd_findGoodsByLname.action',
				data:{lname:lname},
				type:"post",
			    dataType:"json",   
				success: function(data) {
					if(data == null){
						$.messager.alert('提示','根据查询条件，数据库无任何记录');
					}else{
						$('#dg_goodsin').datagrid('reload',{lname:lname});//重新加载
					}
				}
			});  	 
		}
	});

});

//删除
function GoodinDel(id){
	$.messager.confirm('确认','警告：删除后无法恢复!',function(r){    
		if (r){    
		    $.post('gd_del.action',{"id":id},function(data){
				var obj=$.parseJSON(data);
			    $.messager.show({title:'提示',msg:obj.message});
				$('#dg_goodsin').datagrid('reload');//重新加载
			});    
		}    
	}); 
}
</script>

<body>
<!-- 用户数据表格 -->
<table id="dg_goodsin"></table>
<br><br><br>
&nbsp;&nbsp;使用须知：<br>
&nbsp;&nbsp;1，配置物品基本库存信息，不可重名；<br><hr/><br>


<!-- 定义菜单 -->
<div id="tb_user">
	<a id="tb_user_add" style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
	<div class="datagrid-btn-separator"></div> 
	<a id="tb_user_ed" style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
	<div class="datagrid-btn-separator"></div>
	<!-- 创建搜索框 -->
	<input id="lname"  type="text"  style="width:300px;"  placeholder="此处输入物品名称" value=""/>
	<button id="btn" class="easyui-linkbutton" style="width:50px;">搜索</button>
</div>
</body>

<!-- 定义添加窗口-->
<div id="tb_user_add_dialog" class="easyui-dialog" title="新增物品信息" style="width:450px;height:300px;align:center;top:1px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_user_add_dialog_bt'">   
       <form id="user_addForm" method="post">
       		<br><br><br>
       		<table style="width: 96%;margin-left:2%;border: solid 1px #c0cdd8;" cellpadding="1" cellspacing="0">
	    		<tr>
					<td class="td_left">物品名称:
						<font color="red">*</font>
					</td>
					<td class="td_right">
						<input type="text" style="padding:2px 0; margin-left:2px;	border: #5890a570 1px solid; outline-color:#1553a263 ;width: 80%;height: 90%;font-size: 18px;" id="name" name="name" />
					</td>
				</tr>
   				<tr>
					<td class="td_left">入库数量:
						<font color="red">*</font>
					</td>
					<td class="td_right">
						<input type="number" style="padding:2px 0; margin-left:2px;	border: #5890a570 1px solid; outline-color:#1553a263 ;width: 80%;height: 90%;font-size: 18px;"  id="innum" name="innum" min="0"/>
					</td>
				</tr>
				<tr>
					<td class="td_left">剩余数量:
						<font color="red">*</font>
					</td>
					<td class="td_right">
						<input type="number" style="padding:2px 0; margin-left:2px;	border: #5890a570 1px solid; outline-color:#1553a263 ;width: 80%;height: 90%;font-size: 18px;" id="renum" name="renum" min="0"/>
					</td>
				</tr>    		
			</table>	
       </form>
</div>
<div id="tb_user_add_dialog_bt">
	<a id="tb_user_add_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
	<a id="tb_user_add_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>


<!-- 修改信息窗口 -->
<div id="tb_user_upd_dialog" class="easyui-dialog" title="物品信息修改" style="width:450px;height:300px;align:center;top:1px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_user_upd_dialog_bt',">   
       <form id="user_updForm" method="post">
       		<br><br><br>
       		<table style="width: 96%;margin-left:2%;border: solid 1px #c0cdd8;" cellpadding="1" cellspacing="0">
	    		<tr>
					<td class="td_left">物品名称:
						<font color="red">*</font>
					</td>
					<td class="td_right">
						<input type="text" style="padding:2px 0; margin-left:2px;	border: #5890a570 1px solid; outline-color:#1553a263 ;width: 80%;height: 90%;font-size: 18px;" id="name" name="name" />
					    <!-- 隐藏的ID值 -->
	    				<input type="hidden" id="id" name="id"/>
					</td>
				</tr>
   				<tr>
					<td class="td_left">入库数量:
						<font color="red">*</font>
					</td>
					<td class="td_right">
						<input type="number" style="padding:2px 0; margin-left:2px;	border: #5890a570 1px solid; outline-color:#1553a263 ;width: 80%;height: 90%;font-size: 18px;"  id="innum" name="innum" min="0"/>
					</td>
				</tr>
				<tr>
					<td class="td_left">剩余数量:
						<font color="red">*</font>
					</td>
					<td class="td_right">
						<input type="number" style="padding:2px 0; margin-left:2px;	border: #5890a570 1px solid; outline-color:#1553a263 ;width: 80%;height: 90%;font-size: 18px;" id="renum" name="renum" min="0"/>
					</td>
				</tr>    		
			</table>	
       </form>
</div>
<div id="tb_user_upd_dialog_bt">
	<a id="tb_user_upd_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
	<a id="tb_user_upd_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>