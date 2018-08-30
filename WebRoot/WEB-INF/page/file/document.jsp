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
        $('#dg_document').datagrid({    
	        url:'document_findAll.action', 
	        pagination:'true',
	        singleSelect:'true',//只允许选取一行
	        fitColumns:true,//宽度自适应  
	        rownumbers:true,
	        toolbar: '#tb_document',
	        columns:[[
	            {checkbox:true},     
	            {field:'style',title:'文件类型',align:'center',width:8},
	            {field:'title',title:'文件标题',align:'center',width:10},
	            {field:'type',title:'收发类型',align:'center',width:8},
	            {field:'epname',title:'单位名称',align:'center',width:8},
	            {field:'textsize',title:'文字字号',align:'center',width:6},
	            {field:'docode',title:'档案编号',align:'center',width:8},
	            {field:'acceptdate',title:'收发日期',align:'center',width:8},
	            {field:'result',title:'处理结果',align:'center',width:8},
	            {field:'filename',title:'文件名称',align:'center',width:8},
	            {field:'peopleid',title:'记录人',align:'center',width:8},
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
		$('#tb_document_add').on('click',function(){
		    initCombox($('#document_addForm #style'),'document_findstyleList.action',null,-1);
		    initCombox($('#document_addForm #type'),'document_findtypeList.action',null,-1);
			$('#tb_document_add_dialog').dialog('open'); //点击新增按钮弹出新增页面 
		});
		$('#tb_document_add_dialog_bts').on('click',function(){
		    $('#document_addForm').form('submit', {    
			    url:'document_add.action',     
			    success:function(data){  
			        var obj=$.parseJSON(data);
			        if(obj.success){
			            $('#document_addForm').form('clear');//清除表单数据。
						$('#dg_document').datagrid('reload');// 重新载入当前页面数据 
						$('#tb_document_add_dialog').dialog('close');//关闭对话框
			            //window.location.reload();//重新载入当前页面
			        }
			        $.messager.show({
					    title:'提示',
						msg:obj.message
					});
			    }    
			});
		});
		$('#tb_document_add_dialog_btc').on('click',function(){
			$('#tb_document_add_dialog').dialog('close');
		});
		
		//删除某条公告
		$('#tb_document_del').on('click',function(){
			var rows=$('#dg_document').datagrid('getChecked');//在复选框呗选中的时候返回所有行   	
			if(rows.length==0){
				 $.messager.show({title:'提示',msg:'请勾选要删除的记录'});
			}else if(rows.length!=1){
				$.messager.show({title:'提示',msg:'删除时只能选择一条记录'});
			}else{
				$.messager.confirm('确认','您确认想要删除该条记录吗？',function(r){    
				    if (r){    
				        $.post('document_del.action',{"id":rows[0].id},function(data){
							var obj=$.parseJSON(data);
					        $.messager.show({title:'提示',msg:obj.message});
							$('#dg_document').datagrid('reload');//重新加载
						});    
				    }    
				});  
			} 
		});
		
	});
	
	//文件下载
function fileDown(id){
    var form = $('<form></form>');  
    form.attr('action', 'document_filedown.action');  
    form.attr('method', 'post');  
    var input = $('<input type="hidden" />');  
    input.attr('value', id);  
    input.attr('name', 'id');
    form.append(input);  
    form.appendTo('#tb_document_add_dialog').submit().remove();
	//$.post('nc_filedown.action',{"id":id}); 
}
function search(value,name){ 
		$('#dg_document').datagrid('reload',{"title":value});
	} 
</script>
<!-- 用户数据表格 -->
<table class="datagrid-btable" id="dg_document"></table>
<br><br><br>

<div id="tb_document">
	<a id="tb_document_add" style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
	<div class="datagrid-btn-separator"></div> 
	<a id="tb_document_del"  style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	<div class="datagrid-btn-separator"></div>
	<input id="title" class="easyui-searchbox" name="title" style="width:200px" 
	data-options="searcher:search,prompt:'此处输入标题，支持模糊查询',menu:'#titles'"></input> 
	<div id="titles" style="width:120px"> 
		<div data-options="name:'all',iconCls:'icon-ok'">标题</div> 
	</div>
</div>

<!-- 定义添加窗口-->
<div id="tb_document_add_dialog" class="easyui-dialog" title="新增公文" style="width:550px;height:450px;align:center;top:1px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_document_add_dialog_bt'">   
       <form id="document_addForm" method="post" enctype="multipart/form-data">
       		<br><br><br>
       		<table class="datagrid-btable" style="width: 96%;margin-left:2%;border: solid 1px #c0cdd8;">
	    		<tr>
	    		    <td class="td_left">文件类型：</td>
	    			<td class="td_right">
	    			    <input id="style" name="style" style="width:280px;"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">文件标题：</td>
	    			<td class="td_right">
	    				<input type="text" style="width:280px;" id="title" name="title"  class="easyui-validatebox" data-options="required:true" />
	    			</td>
	    		</tr>
	    		<tr>
	    		    <td class="td_left">收发类型：</td>
	    			<td class="td_right">
	    			    <input id="type" name="type" style="width:280px;"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">单位名称：</td>
	    			<td class="td_right">
	    				<input type="text" style="width:280px;" id="epname" name="epname"  class="easyui-validatebox" data-options="required:true" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">文字字号：</td>
	    			<td class="td_right">
	    				<input type="text" style="width:280px;" id="textsize" name="textsize"  class="easyui-numberbox" data-options="required:true" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">档案编号：</td>
	    			<td class="td_right">
	    				<input type="text" style="width:280px;" id="docode" name="docode"  class="easyui-validatebox" data-options="required:true" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">收发日期：</td>
	    			<td class="td_right">
	    				<input id="acceptdate" name="acceptdate"  type="text" style="width:280px;" class="easyui-datebox" editable="false" value=""/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">处理结果：</td>
	    			<td class="td_right">
	    				<input type="text" style="width:280px;" id="result" name="result"  class="easyui-validatebox" data-options="required:true" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">文件：</td>
	    			<td class="td_right">
	    				<input type="file" name="document"/><br/>
	    			</td>
	    		</tr>
			</table>	
       </form>
</div>
<div id="tb_document_add_dialog_bt">
	<a id="tb_document_add_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
	<a id="tb_document_add_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>