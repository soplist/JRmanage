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
	 input{
		padding:2px 0;
		margin-left:2px;
		border: #5890a570 1px solid;
		outline-color:#1553a263 ;
		width: 80%;
		height: 90%;
		font-size: 18px;
	}
	
	
</style>
<script type="text/javascript">
$(function(){
	/* 验证session开始 */
	var curPath = window.document.location.href;
	 var pathName = window.document.location.pathname;
	 var	basePath = curPath.substring(0,curPath.indexOf(pathName)) + "/";
	 var s="<%=session.getAttribute("login_users")%>"; 
	 if(s=="null"){ 
	 	top.location.href = location.href=basePath+"JRmanage";
	 }
	 
	 //加载表格数据
	 $('#dg_user').datagrid({    
	    url:'global_findAllGlobal.action', 
	    pagination:'true',
	    singleSelect:'true',//只允许选取一行
	    fitColumns:true,//宽度自适应
	    rownumbers:true,   
	    toolbar: '#tb_user',
	    columns:[[
	        {checkbox:true},     
	        {field:'output',title:'排量（L）',align:'center',width:12},
	        {field:'hkm',title:'公里单价（元）',align:'center',width:15}
		]]    
	 });
	
	//添加用户
	$('#tb_user_add').on('click',function(){
		$('#tb_user_add_dialog').dialog('open'); //点击新增按钮弹出新增页面
	});
	//保存
	$('#tb_user_add_dialog_bts').on('click',function(){
		$('#user_addForm').form('submit', {    
			    url:'global_add.action',     
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
		var rows=$('#dg_user').datagrid('getChecked'); //在复选框呗选中的时候返回所有行  
		if(rows.length==0){
				 $.messager.show({title:'提示',msg:'请勾选要修改的配置项'});
		}else if(rows.length!=1){
				$.messager.show({title:'提示',msg:'修改时只能选择一个配置项'});
		}else{
				$('#tb_user_upd_dialog').dialog('open');
				$('#user_updForm #id').val(rows[0].id);
				$('#user_updForm #output').val(rows[0].output);
				$('#user_updForm #hkm').val(rows[0].hkm);		
		} 
	});
	//保存
	$('#tb_user_upd_dialog_bts').on('click',function(){
			$('#user_updForm').form('submit', {    
			    url:'global_upd.action',     
			    success:function(data){
			        var obj=$.parseJSON(data);
			        if(obj.success){
			        	$('#user_updForm').form('clear');
						$('#dg_user').datagrid('reload');
						$('#tb_user_upd_dialog').dialog('close');	
			        }
			        $.messager.show({title:'提示',msg:obj.message});
			    }    
			}); 
	});	
	$('#tb_user_upd_dialog_btc').on('click',function(){
		$('#tb_user_upd_dialog').dialog('close');
	});
	
	//删除
	$('#tb_user_del').on('click',function(){	
		var rows=$('#dg_user').datagrid('getChecked');//在复选框呗选中的时候返回所有行   
		if(rows.length==0){
			 $.messager.show({title:'提示',msg:'请勾选要修改的配置项'});
		}else if(rows.length!=1){
			$.messager.show({title:'提示',msg:'修改时只能选择一个配置项'});
		}else{
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
				if (r){    
					$.post('global_del.action',{"id":rows[0].id},function(data){
					var obj=$.parseJSON(data);
					//重新加载
					$.messager.show({title:'提示',msg:obj.message});$('#dg_user').datagrid('reload');});    
				}    
		    });  

		} 
	});
});
</script>


<!-- 用户数据表格 -->
<table id="dg_user"></table>
<br><br><br>
&nbsp;&nbsp;使用须知：<br>
&nbsp;&nbsp;1，全局配置项，提供公里油耗标准设定、费用核算公式比例设置；<br>
&nbsp;&nbsp;2，可以添加多条，用于核算油耗金额；<br><hr><br>

<!-- 定义菜单 -->
<div id="tb_user">
	<a id="tb_user_add" style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
	<div class="datagrid-btn-separator"></div> 
	<a id="tb_user_ed" style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
	<div class="datagrid-btn-separator"></div> 
	<a id="tb_user_del"  style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	<div class="datagrid-btn-separator"></div> 
</div>


<!-- 定义添加窗口-->
<div id="tb_user_add_dialog" class="easyui-dialog" title="新增配置" style="width:500px;height:250px;align:center;top:1px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_user_add_dialog_bt'">   
       <form id="user_addForm" method="post">
       		<br><br>
			<table style="width: 96%;margin-left:2%;border: solid 1px #c0cdd8;border-right: none;border-bottom: none;" cellpadding="1" cellspacing="0">
				<tr>
					<td class="td_left">汽车排量:
						<font color="red">*</font>
					</td>
					<td class="td_right">
						<input id="output" name="output" type="text" style="width:87%;" >升
					</td>
				</tr>
				<tr>
					<td class="td_left">公里单价:
						<font color="red">*</font>
					</td>
					<td class="td_right">
						<input id="hkm" name="hkm" type="text" style="width:87%;" >元
					</td>
				</tr>
				
			</table>
       </form>
</div>
<div id="tb_user_add_dialog_bt">
	<a id="tb_user_add_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
	<a id="tb_user_add_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>


<!-- 修改用户信息窗口 -->
<div id="tb_user_upd_dialog" class="easyui-dialog" title="修改配置" style="width:500px;height:250px;align:center;top:1px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_user_upd_dialog_bt',">   
       <form id="user_updForm" method="post">
       		<input id="id" name="id" type="hidden">
       		<br><br>
			<table style="width: 96%;margin-left:2%;border: solid 1px #c0cdd8;border-right: none;border-bottom: none;" cellpadding="1" cellspacing="0">
				<tr>
					<td class="td_left">汽车排量:
						<font color="red">*</font>
					</td>
					<td class="td_right">
						<input id="output" name="output" type="text" style="width:87%;" >升
					</td>
				</tr>
				<tr>
					<td class="td_left">公里单价:
						<font color="red">*</font>
					</td>
					<td class="td_right">
						<input id="hkm" name="hkm" type="text" style="width:87%;" >元
					</td>
				</tr>
				
			</table>
       </form>
</div>
<div id="tb_user_upd_dialog_bt">
	<a id="tb_user_upd_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
	<a id="tb_user_upd_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>