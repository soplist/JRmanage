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
		$('#dg_file').datagrid({    
	    url:'nc_findAll.action', 
	    pagination:'true',
	    singleSelect:'true',//只允许选取一行
	    fitColumns:true,//宽度自适应  
	    rownumbers:true,
	    toolbar: '#tb_file',
	    columns:[[
	        {checkbox:true},     
	        {field:'title',title:'标题',align:'center',width:8},
	        {field:'content',title:'内容',align:'center',width:15},
	        {field:'people',title:'创建人',align:'center',width:8},
	        {field:'fname',title:'文件名',align:'center',width:8},
	        {field:'createtime',title:'创建时间',align:'center',width:6},
	        {field:'null',title:'操作',
	        	formatter: function(value,row,index){
					var str='';
					if (row.fname!='无'){
					    str='<a href="javascript:fileDown('+row.id+');">文件下载</a>';
					}
					return str;
			},width:8}, 
	       ]]    
		});
		
		 //打开添加窗口---增加功能
		$('#tb_file_add').on('click',function(){
			$('#tb_file_add_dialog').dialog('open'); //点击新增按钮弹出新增页面 
		});
		
		//保存
		$('#tb_file_add_dialog_bts').on('click',function(){
			$('#add_form').form('submit', {    
			    url:'nc_add.action',     
			    success:function(data){  
			    //将 data转换成json  原因：从后台传递过来的数据，都是字符串  
			        var obj=$.parseJSON(data);
			        if(obj.success){
			       		$('#add_form').form('clear');//清除表单数据。
						$('#dg_file').datagrid('reload');// 重新载入当前页面数据 
						$('#tb_file_add_dialog').dialog('close');//关闭对话框	
			        }
			        $.messager.show({title:'提示',msg:obj.message});
			    }    
			});
		});
		//取消
		$('#tb_file_add_dialog_btc').on('click',function(){
			$('#tb_file_add_dialog').dialog('close');
		});
		
		
		//删除某条公告
		$('#tb_file_del').on('click',function(){
			var rows=$('#dg_file').datagrid('getChecked');//在复选框呗选中的时候返回所有行   	
			if(rows.length==0){
				 $.messager.show({title:'提示',msg:'请勾选要删除的记录'});
			}else if(rows.length!=1){
				$.messager.show({title:'提示',msg:'删除时只能选择一条记录'});
			}else{
				$.messager.confirm('确认','您确认想要删除该条记录吗？',function(r){    
				    if (r){    
				        $.post('nc_del.action',{"id":rows[0].id},function(data){
							var obj=$.parseJSON(data);
					        $.messager.show({title:'提示',msg:obj.message});
							$('#dg_file').datagrid('reload');//重新加载
						});    
				    }    
				});  
			} 
		});
		

}); 

//文件下载
function fileDown(id){
    var form = $('<form></form>');  
    form.attr('action', 'nc_filedown.action');  
    form.attr('method', 'post');  
    var input = $('<input type="hidden" />');  
    input.attr('value', id);  
    input.attr('name', 'id');
    form.append(input);  
    form.appendTo('#tb_file_add_dialog').submit().remove();
	//$.post('nc_filedown.action',{"id":id}); 
}

function search(value,name){ 
		$('#dg_file').datagrid('reload',{"title":value});
	} 
</script>

<!-- 公告开始 -->
<!-- 用户数据表格 -->
<table class="datagrid-btable" id="dg_file"></table>
<br><br>
&nbsp;&nbsp;使用须知：<br>
&nbsp;&nbsp;1，日常文件管理上传单个文件最大不能超过15MB，上传多文件时请将多文件打包成一个压缩包zip格式再上传且压缩包不能大于20MB,否则会上传失败；<br>
&nbsp;&nbsp;2，上传文件格式仅限于doc|docx|pdf|xlsx|xls|ppt|pptx,压缩包文件除外；<br>
&nbsp;&nbsp;3，上传多文件时文件名不能重复，文件名不能含特殊字符；<br><hr><br>
<!-- 定义菜单 -->
<div id="tb_file">
	<a id="tb_file_add" style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
	<div class="datagrid-btn-separator"></div> 
	<a id="tb_file_del"  style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	<div class="datagrid-btn-separator"></div>
	<input id="title" class="easyui-searchbox" name="title" style="width:200px" 
	data-options="searcher:search,prompt:'此处输入标题，支持模糊查询',menu:'#titles'"></input> 
	<div id="titles" style="width:120px"> 
		<div data-options="name:'all',iconCls:'icon-ok'">标题</div> 
	</div>
</div>

<!-- 定义添加窗口-->
<div id="tb_file_add_dialog" class="easyui-dialog" title="新增文件信息" style="width:450px;height:300px;align:center;top:1px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_file_add_dialog_bt'">   
       <form action="" id="add_form" method="post" enctype="multipart/form-data">
       
       		<br><br><br>
       		<table class="datagrid-btable" style="width: 96%;margin-left:2%;border: solid 1px #c0cdd8;">
	    		<tr>
	    		    <td class="td_left">标题：</td>
	    			<td class="td_right">
	    			    <input type="text" style="width:280px;" id="title" name="title"  class="easyui-validatebox" data-options="required:true" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">摘要：</td>
	    			<td class="td_right">
	    				<textarea style="height:80px;width:280px" id="content" name="content"></textarea>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">文件上传：</td>
	    			<td class="td_right">
	    				<input type="file" name="daily"/><br/>
	    			</td>
	    		</tr>
			</table>	
       </form>
       
</div>
<div id="tb_file_add_dialog_bt">
	<a id="tb_file_add_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
	<a id="tb_file_add_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>


