<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../Inc.jsp"></jsp:include>
<script type="text/javascript">
	
	$(function(){
			/* 验证session开始 */
			var curPath = window.document.location.href;
		 	var pathName = window.document.location.pathname;
		 	var	basePath = curPath.substring(0,curPath.indexOf(pathName)) + "/";
		 	var s="<%=session.getAttribute("login_users")%>"; 
		 	if(s=="null"){ 
		 		top.location.href = location.href=basePath+"jfinfo";
		 	}
			/* 验证session开始 */
		//加载表格数据
		$('#dg_role').datagrid({    
	    url:'role_findRoles.action', 
	    pagination:'true',   
	    rownumbers:'true',
	    toolbar: '#tb_role',
	    columns:[[    
	        {field:'id',title:'编号',checkbox:true},    
	        {field:'name',title:'角色名称'}   
		    ]]    
		}); 
		
		//打开添加角色窗口
		$('#tb_role_add').on('click',function(){
			$('#tb_role_add_dialog').dialog('open');  
		});
		
		$('#tb_role_add_dialog_bts').on('click',function(){
			$('#role_addForm').form('submit', {    
			    url:'role_add.action',     
			    success:function(data){    
			        var obj=$.parseJSON(data);
			        if(obj.success){
			        	$('#role_addForm').form('clear');
						$('#dg_role').datagrid('reload');
						$('#tb_role_add_dialog').dialog('close');	
			        }
			        $.messager.show({
								title:'提示',
								msg:obj.message
					});
			    }    
			});
		});
		
		$('#tb_role_add_dialog_btc').on('click',function(){
			$('#tb_role_add_dialog').dialog('close');
		});
		
		//打开编辑窗口
		$('#tb_role_ed').on('click',function(){
			
			var rows=$('#dg_role').datagrid('getChecked');   
			
			if(rows.length==0){
				 $.messager.show({
								title:'提示',
								msg:'请勾选要修改的角色'
				});
			}else if(rows.length!=1){
				$.messager.show({
								title:'提示',
								msg:'修改时只能选择一个角色'
				});
			}else{
				$('#tb_role_upd_dialog').dialog('open');
				$('#id').val(rows[0].id);
				$('#name').val(rows[0].name);
			} 
		});
		
		$('#tb_role_upd_dialog_bts').on('click',function(){
			$('#role_updForm').form('submit', {    
			    url:'role_upd.action',     
			    success:function(data){    
			        var obj=$.parseJSON(data);
			        if(obj.success){
			        	$('#role_updForm').form('clear');
						$('#dg_role').datagrid('reload');
						$('#tb_role_upd_dialog').dialog('close');	
			        }
			        $.messager.show({
								title:'提示',
								msg:obj.message
					});
			    }    
			});
		});
		
		$('#tb_role_upd_dialog_btc').on('click',function(){
			$('#tb_role_upd_dialog').dialog('close');
		});
		
		
		//打开授权窗口
		$('#tb_role_grant').on('click',function(){
			
			var rows=$('#dg_role').datagrid('getChecked');   
			
			if(rows.length==0){
				 $.messager.show({
								title:'提示',
								msg:'请勾选要授权的的角色'
				});
			}else if(rows.length!=1){
				$.messager.show({
								title:'提示',
								msg:'授权时只能选择一个角色'
				});
			}else{
				//打开授权窗口
				$('#tb_role_grant_dialog').dialog('open');
				//加载树形菜单
				$('#tt').tree({    
				    url:'role_findTreeByRole.action?id='+rows[0].id,
					idFiled:"id",
					textFiled:"text",
					parentField:"pid",
					checkbox:true,
					checked:"checked"
				});
			}
			
		});
		$('#tb_role_grant_dialog_bts').on('click',function(){
			
			var ids=[];
			//获取当前的用户ID
			var rows=$('#dg_role').datagrid('getChecked');  
			
			
			var id=rows[0].id;
			
			//获取选中的菜单
			var nodes = $('#tt').tree('getChecked', ['checked','indeterminate']);
			
			$.each(nodes,function(i,v){
					ids.push(v.id);
			});
			
			var mids=ids.join(',');
			
			$.post('role_grantRole.action',{"id":id,"mids":mids},function(data){
			
				var obj=$.parseJSON(data);
		        if(obj.success){
		        	$('#role_updForm').form('clear');
					$('#dg_role').datagrid('reload');
					$('#tb_role_grant_dialog').dialog('close');	
		        }
		        $.messager.show({
							title:'提示',
							msg:obj.message
				});
			});
			
		});
		
		$('#tb_role_grant_dialog_btc').on('click',function(){
			$('#tb_role_grant_dialog').dialog('close');
		});
		
		
		
	});
</script>

<!-- 定义数据表格 -->
<table id="dg_role"></table>
<br><br><br>
&nbsp;&nbsp;使用须知：<br>
&nbsp;&nbsp;1，角色对应的权限为查看的菜单选项，系统默认配置好，一般情况下不建议修改；<br>
&nbsp;&nbsp;2，选中角色-授权-可勾选对应的菜单，这些菜单将会在拥有此角色的用户在登陆系统时加载；<br>
&nbsp;&nbsp;3，更多问题请联系超级管理员<br><hr><br>
<!-- 定义菜单 -->
<div id="tb_role">
	<a id="tb_role_add"  style="float:left;" class="easyui-linkbutton"  data-options="iconCls:'icon-add',plain:true">新增</a>
	<div class="datagrid-btn-separator"></div> 
	<a id="tb_role_ed"  style="float:left;"class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
	<div class="datagrid-btn-separator"></div> 
	<a id="tb_role_grant" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">授权</a>
</div>

<!-- 定义添加窗口-->
<div id="tb_role_add_dialog" class="easyui-dialog" title="新增角色" style="width:300px;height:120px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_role_add_dialog_bt',">   
       <form id="role_addForm" method="post">
       		<table>
       			<tr>
       				<th>角色名称</th>
       				<td>
       					<input type="text" name="name">
       				</td>
       			</tr>
       		</table>
       </form>
</div>
<div id="tb_role_add_dialog_bt">
	<a id="tb_role_add_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
	<a id="tb_role_add_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>

<!-- 定义编辑窗口-->
<div id="tb_role_upd_dialog" class="easyui-dialog" title="编辑角色" style="width:300px;height:120px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_role_upd_dialog_bt',">   
       <form id="role_updForm" method="post">
       		<table>
       			<tr>
       				<th>角色名称</th>
       				<td>
       					<input type="hidden" name="id" id="id">
       					<input type="text" name="name" id="name" >
       				</td>
       			</tr>
       		</table>
       </form>
</div>
<div id="tb_role_upd_dialog_bt">
	<a id="tb_role_upd_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
	<a id="tb_role_upd_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>


<!-- 定义授权窗口 -->
<div id="tb_role_grant_dialog" class="easyui-dialog" title="角色授权" style="width:500px;height:420px;overflow: scroll;top:1px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_role_grant_dialog_bt',">   
     
       	<ul id="tt"></ul> 
</div>
<div id="tb_role_grant_dialog_bt">
	<a id="tb_role_grant_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
	<a id="tb_role_grant_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>



