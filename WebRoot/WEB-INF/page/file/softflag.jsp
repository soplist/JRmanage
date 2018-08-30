<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../Inc.jsp"></jsp:include>
<style type="text/css">
    table{
        border-collapse:collapse;
    }

    .datagrid-btable tr td{
		border:1px solid #c0cdd8;
        height: 30px;
	}
    .td_left{
		background: #b6c1ca;
		width:30%;
		text-align:right;
		height:30px;
		
	}
	.td_right{
		width:70%;
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
		/* 验证session开始 */
        //加载表格数据
		$('#dg_softflag').datagrid({    
	    url:'sf_findAll.action', 
	    pagination:'true',
	    singleSelect:'true',//只允许选取一行
	    fitColumns:true,//宽度自适应  
	    rownumbers:true,
	    toolbar: '#tb_softflag',
	    columns:[[
	        {checkbox:true},     
	        {field:'name',title:'项目名称',align:'center',width:8},
	        {field:'starttime',title:'开始时间',align:'center',width:15},
	        {field:'endtime',title:'结束时间',align:'center',width:8},
	        {field:'people',title:'负责人',align:'center',width:6},
	        {field:'brief',title:'项目简介',align:'center',width:6},
	        {field:'filename',title:'文件名称',align:'center',width:8},
	        {field:'peopleid',title:'记录人',align:'center',width:6},
	        {field:'null',title:'操作',
	        	formatter: function(value,row,index){
					var str='';
					if (row.filename!='无'){
					    str='<a href="javascript:fileDown('+row.id+');">文件下载</a>';
					}
					return str;
			},width:8}, 
	       ]]    
		});
		$('#tb_softflag_add').on('click',function(){
			$('#tb_softflag_add_dialog').dialog('open'); //点击新增按钮弹出新增页面 
		});
		
		//保存
		$('#tb_softflag_add_dialog_bts').on('click',function(){
			$('#add_form').form('submit', {    
			    url:'sf_add.action',     
			    success:function(data){  
			    //将 data转换成json  原因：从后台传递过来的数据，都是字符串  
			        var obj=$.parseJSON(data);
			        if(obj.success){
			       		$('#add_form').form('clear');//清除表单数据。
						$('#dg_softflag').datagrid('reload');// 重新载入当前页面数据 
						$('#tb_softflag_add_dialog').dialog('close');//关闭对话框	
			        }
			        $.messager.show({title:'提示',msg:obj.message});
			    }    
			});
		});
		//取消
		$('#tb_softflag_add_dialog_btc').on('click',function(){
			$('#tb_softflag_add_dialog').dialog('close');
		});
		
		$('#tb_softflag_del').on('click',function(){
			var rows=$('#dg_softflag').datagrid('getChecked');//在复选框呗选中的时候返回所有行   	
			if(rows.length==0){
				 $.messager.show({title:'提示',msg:'请勾选要删除的记录'});
			}else if(rows.length!=1){
				$.messager.show({title:'提示',msg:'删除时只能选择一条记录'});
			}else{
				$.messager.confirm('确认','您确认想要删除该条记录吗？',function(r){    
				    if (r){    
				        $.post('sf_del.action',{"id":rows[0].id},function(data){
							var obj=$.parseJSON(data);
					        $.messager.show({title:'提示',msg:obj.message});
							$('#dg_softflag').datagrid('reload');//重新加载
						});    
				    }    
				});  
			} 
		});
	}); 
	
	function search(value,name){ 
		$('#dg_softflag').datagrid('reload',{"name":value});
	} 
	
	//文件下载
    function fileDown(id){
        var form = $('<form></form>');  
        form.attr('action', 'sf_filedown.action');  
        form.attr('method', 'post');  
        var input = $('<input type="hidden" />');  
        input.attr('value', id);  
        input.attr('name', 'id');
        form.append(input);  
        form.appendTo('#tb_softflag_add_dialog').submit().remove();
	    //$.post('nc_filedown.action',{"id":id}); 
    }
</script>
<!-- 用户数据表格 -->
<table class="datagrid-btable" id="dg_softflag"></table>
<br><br><br>

<!-- 定义菜单 -->
<div id="tb_softflag">
	<a id="tb_softflag_add" style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
	<div class="datagrid-btn-separator"></div> 
	<a id="tb_softflag_del"  style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	<div class="datagrid-btn-separator"></div>
	<input id="name" class="easyui-searchbox" name="name" style="width:200px" 
	data-options="searcher:search,prompt:'此处输入标题，支持模糊查询',menu:'#names'"></input> 
	<div id="names" style="width:120px"> 
		<div data-options="name:'all',iconCls:'icon-ok'">标题</div> 
	</div>
</div>

<!-- 定义添加窗口-->
<div id="tb_softflag_add_dialog" class="easyui-dialog" title="新增项目立项信息" style="width:450px;height:400px;align:center;top:1px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_softflag_add_dialog_bt'">   
       <form action="" id="add_form" method="post" enctype="multipart/form-data">
       
       		<br><br><br>
       		<table class="datagrid-btable" style="width: 96%;margin-left:2%;border: solid 1px #c0cdd8;">
	    		<tr>
	    		    <td class="td_left">项目名称：</td>
	    			<td class="td_right">
	    			    <input type="text" style="width:280px;" id="name" name="name"  class="easyui-validatebox" data-options="required:true" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">开始时间：</td>
	    			<td class="td_right">
	    				<input id="starttime" name="starttime"  type="text" style="width:280px;" class="easyui-datebox" editable="false" value=""/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">结束时间：</td>
	    			<td class="td_right">
	    				<input id="endtime" name="endtime"  type="text" style="width:280px;" class="easyui-datebox" editable="false" value=""/>
	    			</td>
	    		</tr>
	    		<tr>
	    		    <td class="td_left">负责人：</td>
	    			<td class="td_right">
	    			    <input type="text" style="width:280px;" id="people" name="people"  class="easyui-validatebox" data-options="required:true" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">项目简介：</td>
	    			<td class="td_right">
	    				<textarea style="height:80px;width:280px" id="brief" name="brief"></textarea>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">文件上传：</td>
	    			<td class="td_right">
	    				<input type="file" name="sffile"/><br/>
	    			</td>
	    		</tr>
			</table>	
       </form>
       
</div>

<div id="tb_softflag_add_dialog_bt">
	<a id="tb_softflag_add_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
	<a id="tb_softflag_add_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>