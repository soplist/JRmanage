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
		//初始化表格结构
		$('#dg_dic').datagrid({    
		    url:'', 
		    pagination:'true',   
		    rownumbers:'true',
		    toolbar: '#tb_dic',
		    columns:[[ 
		    	{checkbox:true},   
		        {field:'id',title:'编号',},    
		        {field:'parent',title:'父类名称',
		        	formatter: function(value,row,index){
						return value.text;
					}
		        },
		        {field:'text',title:'文本'},
		        {field:'isdisable',title:'是否启用',
		        	formatter: function(value,row,index){
						if (row.isdisable==1){
							return '启用';
						} else {
							return '禁用';
						}
					}
		        }  
			    ]]    
		}); 
		
		
//***************************************************************************************//		
		
		
		//初始化大分类,在下拉框中显示大分类的数据
		initCombox($('#dic_max'),'dictionary_findDicByParent.action',null,-1);
		//给大分类添加onSelect事件，在用户选择列表项的时候触发。
		$('#dic_max').combobox({
			onSelect:function(rec){
				//alert(rec.id);
				//清空三级分类的数据
				//$('#dic_min').combobox("clear");//清除下拉列表框的值。
				//$('#dic_min').combobox("loadData",[]);//读取本地列表数据
				//初始化中分类,在下拉框中显示中分类数据
				initComboxData($('#dic_middle'),'dictionary_findDicByParent.action?parent.id='+rec.id,null);
				//加载大分类数据
				$('#dg_dic').datagrid({url:'dictionary_findDicPageDataByPid.action?parent.id='+rec.id}); 
			}
		});
		
		//给中分类添加onSelect事件
		$('#dic_middle').combobox({
			onSelect:function(rec){
				//alert('中分类onSelect事件'+rec.id);
				//初始化小分类
				initComboxData($('#dic_min'),'dictionary_findDicByParent.action?parent.id='+rec.id,null);
				//加载中分类数据
				$('#dg_dic').datagrid({url:'dictionary_findDicPageDataByPid.action?parent.id='+rec.id}); 
			}
		});
		

		//给小分类添加onSelect事件
		$('#dic_min').combobox({
			onSelect:function(rec){
				//alert(rec.id);
				//加载小分类数据
				$('#dg_dic').datagrid({url:'dictionary_findDicPageDataByPid.action?parent.id='+rec.id}); 
			}
		});
	
	//***************************************************************************************//	
		
		
		//新增字典表数据
		$('#tb_dic_add').on('click',function(){
			//点击新增按钮弹出新增页面 
			$('#tb_dic_add_dialog').dialog('open');
			$('#dic_addForm [name=lev]').get(0).click();
			$('#dic_addForm [name=isdisable]').get(0).click();
			
		});
		//保存
		$('#tb_dic_add_dialog_bts').on('click',function(){
			$('#dic_addForm').form('submit', {    
			    url:'dictionary_add.action',     
			    success:function(data){  
			    	//alert(data);
			        var obj=$.parseJSON(data);
			        if(obj.success){
			        	$('#dic_addForm').form('clear');//清除表单数据
						$('#dg_dic').datagrid('reload');// 重新载入当前页面数据 
						$('#tb_dic_add_dialog').dialog('close');//关闭对话框		
			        }
			        $.messager.show({
								title:'提示',
								msg:obj.message
					});
			    }    
			});
		});
		//取消
		$('#tb_dic_add_dialog_btc').on('click',function(){
			$('#tb_dic_add_dialog').dialog('close');
		});
		
		
		//给单选框添加事件
		$('#dic_addForm [name=lev]').on('click',function(){
			//alert(lev);
			var lev=$(this).val();
			$('#dic_add_select').combobox("clear");//
			$('#dic_add_select').combobox("loadData",[]);//读取本地列表数据。
			$('#dic_add_select').combobox("setValue",'无');//设置下拉列表框的值。 
			if(lev!=1){
				initCombox($('#dic_addForm #dic_add_select'),'dictionary_findDicByLev.action?lev='+(lev-1),null,-1);
			}
		});
		
		
		
//***************************************************************************************//		
		
		
		//修改字典表数据
		$('#tb_dic_ed').on('click',function(){
			
			var rows=$('#dg_dic').datagrid('getChecked'); //在复选框呗选中的时候返回所有行    
			
			if(rows.length==0){
				 $.messager.show({
								title:'提示',
								msg:'请勾选要修改的字典数据'
				});
			}else if(rows.length!=1){
				$.messager.show({
								title:'提示',
								msg:'修改时只能选择一个字典数据'
				});
			}else{
				$('#tb_dic_upd_dialog').dialog('open');
				
				
				/*
				val()函数用于设置或返回当前jQuery对象所匹配的DOM元素的value值。
					该函数常用于设置或获取表单元素的value属性值。
					例如：<input>、<textarea>、<select>、<option>、<button>等。
				  */
				$.post('dictionary_findById.action',{"id":rows[0].id},function(data){
					$('#dic_updForm #id').val(data.id);//必须先获取对应ID的，否则修改不了
					$('#dic_updForm [name=lev][value='+data.lev+']').prop("checked",true);//获取原先的等级，否则会显示为初始状态
					//alert(data.parent.id);
					initCombox($('#dic_updForm #dic_upd_select'),'dictionary_findDicByLev.action?lev='+(data.lev-1),null,data.parent.id);
					$('#dic_updForm #text').val(data.text);//在修改页面显示对应位置的值
					
					/*
					jquery中的prop方法:
						以属性名/值对方式设置所用匹配元素的值
						$(selector).prop(key,value)
						key:定义要设置值得属性名称
						value:定义要设置的属性值
				*/
					//在修改之前保持上次修改的状态
					$('#dic_updForm [name=isdisable][value='+data.isdisable+']').prop("checked",true);
				},'json');
			} 
		});
		
		$('#tb_dic_upd_dialog_bts').on('click',function(){
			$('#dic_updForm').form('submit', {    
			    url:'dictionary_upd.action',     
			    success:function(data){    
			        var obj=$.parseJSON(data);
			        if(obj.success){
			        	$('#dic_updForm').form('clear');
						$('#dg_dic').datagrid('reload');
						$('#tb_dic_upd_dialog').dialog('close');	
			        }
			        $.messager.show({
								title:'提示',
								msg:obj.message
					});
			    }    
			});
		});
		
		$('#tb_dic_upd_dialog_btc').on('click',function(){
			$('#tb_dic_upd_dialog').dialog('close');
		});
		
		//给单选框添加事件
		$('#dic_updForm [name=lev]').on('click',function(){
			var lev=$(this).val();
			//$('#dic_upd_select').combobox("clear");
			$('#dic_upd_select').combobox("loadData",[]);
			$('#dic_upd_select').combobox("setValue",'无');
			if(lev!=1){
				initCombox($('#dic_updForm #dic_upd_select'),'dictionary_findDicByLev.action?lev='+(lev-1),null,-1);
			}
		});
		
		
		
		
	
	});

</script>
<br>
<!-- 定义数据表格 -->
<table id="dg_dic"></table>
<br><br><br>
&nbsp;&nbsp;使用须知：<br>
&nbsp;&nbsp;<span style="color: red">1，字典内容将会被程序调用，非专业人员禁止修改；</span><br>
&nbsp;&nbsp;2，字典分为三级菜单，添加时应选择正确的目录结构；<br>
&nbsp;&nbsp;3，字典数据只有在【启用】状态下才可以被调用；<br>
&nbsp;&nbsp;4，更多问题请联系超级管理员<br><hr><br>

<!-- 数据工具栏创建位置 -->   
<div id="tb_dic">
	<a class="easyui-linkbutton" id="tb_dic_add" data-options="iconCls:'icon-add',plain:true" style="float:left;">新增</a>
	<div class="datagrid-btn-separator"></div>		
	<a class="easyui-linkbutton" id="tb_dic_ed" data-options="iconCls:'icon-edit',plain:true" style="float:left;">编辑</a>
	<div class="datagrid-btn-separator"></div>
	
	<!-- 下拉框1,初始显示大分类 -->
	&nbsp;&nbsp;&nbsp;一级目录：<input id="dic_max" 
		class="easyui-combobox" name="pid" />
   	<!-- 下拉框2,初始显示中分类 -->
   	&nbsp;&nbsp;&nbsp;二级目录：<input id="dic_middle" name="pid"/>
   	
   	<!-- 下拉框3,初始显示小分类 -->
   	<%--&nbsp;&nbsp;&nbsp;小分类：<input id="dic_min" name="pid"/>--%>
</div>

<!-- 定义新增窗口 -->
<div id="tb_dic_add_dialog" class="easyui-dialog" title="新增字典表信息" style="width:300px;height:250px;align:center;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_dic_add_dialog_bt',">
        <form id="dic_addForm" method="post">
			<table>
				<tr>
					<td style="text-align: right;">选择级别：</td>
					<td>
						<input type="radio" name="lev" value="1" checked="checked"/>Lv1
						<input type="radio" name="lev" value="2"/>Lv2
						<input type="radio" name="lev" value="3"/>Lv3
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">上级菜单：</td>
					<td>
						<select id="dic_add_select" name="parent.id" class="easyui-combobox" style="width:143px;">
							<option value="-1">无</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">是否启用：</td>
					<td>
						<input type="radio" name="isdisable" value="1" checked="checked"/>启用
						<input type="radio" name="isdisable" value="0"/>禁用
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">字典文本：</td>
					<td>
						<input type="text" name="text" maxlength="10"/>
					</td>
				</tr>
			</table>
			
		</form>	
</div>     
<div id="tb_dic_add_dialog_bt">
	<a id="tb_dic_add_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
	<a id="tb_dic_add_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>



<!-- 定义修改窗口 -->
<div id="tb_dic_upd_dialog" class="easyui-dialog" title="修改字典表信息" style="width:300px;height:250px;align:center;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_dic_upd_dialog_bt',">
        <form id="dic_updForm" method="post">
			<table>
				<tr>
					<td style="text-align: right;">选择级别：</td>
					<td>
						<input type="hidden" name="id" id="id"/>
						<input type="radio" name="lev" value="1" checked="checked"/>Lv1
						<input type="radio" name="lev" value="2"/>Lv2
						<input type="radio" name="lev" value="3"/>Lv3
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">上级菜单：</td>
					<td>
						<select id="dic_upd_select" name="parent.id" class="easyui-combobox" style="width:143px;">
							<option value="-1">无</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">是否启用：</td>
					<td>
						<input type="radio" name="isdisable" value="1" checked="checked"/>启用
						<input type="radio" name="isdisable" value="0"/>禁用
					</td>
				</tr>
				<tr>
					<td style="text-align: rigth;">字典文本：</td>
					<td>
						<input type="text" name="text" id="text" maxlength="10"/>
					</td>
				</tr>
			</table>
			
		</form>	
</div>     
<div id="tb_dic_upd_dialog_bt">
	<a id="tb_dic_upd_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
	<a id="tb_dic_upd_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>   


   

