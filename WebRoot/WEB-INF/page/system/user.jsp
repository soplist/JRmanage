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
	 		top.location.href = location.href=basePath+"JRmanage";
	 	}
		/* 验证session开始 */
		//加载表格数据
		$('#dg_user').datagrid({    
	    url:'user_findAllUsers.action', 
	    pagination:'true',
	    singleSelect:'true',//只允许选取一行
	    fitColumns:true,//宽度自适应
	    rownumbers:true,   
	    toolbar: '#tb_user',
	    columns:[[
	        {checkbox:true},     
	        {field:'lname',title:'登陆账号',align:'center',width:8},
	        {field:'rname',title:'姓名',align:'center',width:8},
		    /* 	单元格formatter(格式化器)函数，带3个参数：
				value：字段值。
				rowData：行记录数据。
				rowIndex: 行索引 */
	        {field:'phone',title:'电话',align:'center',width:8},
	        {field:'dpart',title:'所属部门',align:'center',width:10,
	        	formatter: function(value,row,index){
					return value.text;
				}
			},
	        {field:'isdisable',title:'启停状态',align:'center',width:8,
	        	formatter: function(value,row,index){
					if (row.isdisable==1){
						return '<span style="color:green">启用</span>';
					} else {
						return '<span style="color:red">禁用</span>';
					}
				}
	        },
	        {field:'null',title:'操作',align:'center',width:8,
	        	formatter: function(value,row,index){
					var str='';
					str='<a href="javascript:resetPass('+row.id+');">重置密码</a>';
					return str;
				}},  
		    ]]    
		});
		
		//添加用户
		$('#tb_user_add').on('click',function(){
		
			//初始化添加用户数据
			initCombox($('#user_addForm #dpart'),'user_finddpartList.action',null,-1);
			$('#tb_user_add_dialog').dialog('open'); //点击新增按钮弹出新增页面 
			
			//监听增加时用户名是否存在
			$('#user_addForm #lname').blur(function(){
				var lname = $('#user_addForm #lname').val();
				$.ajax({
					url:"user_isbe.action",
					data:{lname:lname},
					dataType:"json",
					type:"post",
					success:function(dat){
						
						if(dat){
							 //alert("用户已存在");
							 $('#user_addForm #lname_span').html("用户已存在");
							 $('#user_addForm #lname_span').css("color","red");
							 $('#tb_user_add_dialog_bts').css("display","none");
						}else{
							//alert("可以使用");
							$('#user_addForm #lname_span').html("可以使用");
							$('#user_addForm #lname_span').css("color","green");
							$('#tb_user_add_dialog_bts').css("display","inline-block");
						}
					}
				});
			});
		});
		//保存
		$('#tb_user_add_dialog_bts').on('click',function(){
			$('#user_addForm').form('submit', {    
			    url:'user_add.action',     
			    success:function(data){  
			    //将 data转换成json  原因：从后台传递过来的数据，都是字符串  
			        var obj=$.parseJSON(data);
			        if(obj.success){
			       		//$('#user_addForm').form('clear');//清除表单数据。
						//$('#dg_user').datagrid('reload');// 重新载入当前页面数据 
						//$('#tb_user_add_dialog').dialog('close');//关闭对话框
						window.location.reload();//重新载入当前页面
			        }
			        $.messager.show({
								title:'提示',
								msg:obj.message
					});
			    }    
			});
		});
		//取消
		$('#tb_user_add_dialog_btc').on('click',function(){
			$('#tb_user_add_dialog').dialog('close');
		});
		
		//修改用户
		$('#tb_user_ed').on('click',function(){
			var rows=$('#dg_user').datagrid('getChecked'); //在复选框呗选中的时候返回所有行  
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
			/*
			val()函数用于设置或返回当前jQuery对象所匹配的DOM元素的value值。
				该函数常用于设置或获取表单元素的value属性值。
				例如：<input>、<textarea>、<select>、<option>、<button>等。
			  */
				$('#tb_user_upd_dialog').dialog('open');
				$('#user_updForm #id').val(rows[0].id);
				$('#user_updForm #lname').val(rows[0].lname);
				$('#user_updForm #rname').val(rows[0].rname);
				$('#user_updForm #phone').val(rows[0].phone);
				initCombox($('#user_updForm #dpart'),'user_finddpartList.action',null,rows[0].dpart.id);
				/*
					jquery中的prop方法:
					以属性名/值对方式设置所用匹配元素的值
					$(selector).prop(key,value)
					key:定义要设置值得属性名称
					value:定义要设置的属性值
				*/
				$('#user_updForm [name=isdisable][value='+rows[0].isdisable+']').prop("checked",true);
				
			} 
		});
		//保存
		$('#tb_user_upd_dialog_bts').on('click',function(){
		
			$('#user_updForm').form('submit', {    
			    url:'user_upd.action',     
			    success:function(data){
			        var obj=$.parseJSON(data);
			        if(obj.success){
			        	$('#user_updForm').form('clear');
						$('#dg_user').datagrid('reload');
						$('#tb_user_upd_dialog').dialog('close');	
			        }
			        $.messager.show({
								title:'提示',
								msg:obj.message
					});
			    }    
			}); 
		});
		
		$('#tb_user_upd_dialog_btc').on('click',function(){
			$('#tb_user_upd_dialog').dialog('close');
		});
		
		
		//删除用户
		$('#tb_user_del').on('click',function(){
			
			var rows=$('#dg_user').datagrid('getChecked');//在复选框呗选中的时候返回所有行   
			
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
			
				$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
				    if (r){    
				        $.post('user_del.action',{"id":rows[0].id},function(data){
							var obj=$.parseJSON(data);
					        $.messager.show({
										title:'提示',
										msg:obj.message
							});
							$('#dg_user').datagrid('reload');//重新加载
						});    
				    }    
				});  

			} 
		});
		
		//指派角色
		$('#tb_user_grant').on('click',function(){
			
			var rows=$('#dg_user').datagrid('getChecked');   
			
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
				$('#tb_user_grant_dialog').dialog('open');
				
				$.post('role_findRoles.action',function(data){
					//alert(data);
					var roles=$.parseJSON(data);
					var html="<div>";
					$.each(roles.rows,function(i,v){
						var checked=false;
						$.each(rows[0].qRoles,function(index,value){
							if(value.id==v.id){
								checked=true;
							}
						});
						
						if(checked){
							html+="<input type='checkbox' name='role' value='"+v.id+"' checked='checked' />"+v.name;//显示选择的
						}else{
							html+="<input type='checkbox' name='role' value='"+v.id+"' />"+v.name;//显示未选择的
						}
					});
					html+="</div>";
					//alert("html ---"+html);
					$('#tb_user_grant_dialog #grantroles').html(html);//显示整个页面
				});
				
			} 
		});
		
		
		//保存
		$('#tb_user_grant_dialog_bts').on('click',function(){
		
			var rows=$('#dg_user').datagrid('getChecked'); //在复选框被选中的时候返回所有行
			
			//获取已经选择的角色
			var roles=$('#grantroles :checkbox[name=role]:checked');
			var rids=[];
			var id=rows[0].id;
			$.each(roles,function(i,v){
				rids.push(v.value);
			});
			var mids=rids.join(',');
			$.post('user_grant.action',{"id":id,"rids":mids},function(data){
					var obj=$.parseJSON(data);
			        $.messager.show({
								title:'提示',
								msg:obj.message
					});
					$('#dg_user').datagrid('reload');
					$('#tb_user_grant_dialog').dialog('close');
			});
		});
		
		//取消
		$('#tb_user_grant_dialog_btc').on('click',function(){
			$('#tb_user_grant_dialog').dialog('close');
		});
		
			
	});
	
	
	//重置密码
	function resetPass(id){
		
		$.post('user_resetPass.action',{"id":id},function(data){
			var obj=$.parseJSON(data);
	        if(obj.success){
				$('#dg_user').datagrid('reload');
	        }
	        $.messager.show({
						title:'提示',
						msg:obj.message
			});
		});
	}
	
	function search(value,name){ 
		$('#dg_user').datagrid('reload',{"rname":value});
	} 

	
</script>


<br>
<!-- 用户数据表格 -->
<table id="dg_user"></table>
<br><br><br>
&nbsp;&nbsp;使用须知：<br>
&nbsp;&nbsp;1，新增账号时，密码初始为123456，【启停状态】为【启用】；<br>
&nbsp;&nbsp;2，如果用户忘记密码，管理员可通过账号后方对应的【重置密码】，将密码初始为123456；<br>

<!-- 定义菜单 -->
<div id="tb_user">
	<a id="tb_user_add" style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
	<div class="datagrid-btn-separator"></div> 
	<a id="tb_user_ed" style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
	<div class="datagrid-btn-separator"></div> 
	<a id="tb_user_del"  style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	<div class="datagrid-btn-separator"></div> 
	<a id="tb_user_grant" style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">指派角色</a>
	<div class="datagrid-btn-separator"></div>
	<!-- 创建搜索框 -->
	<input id="rname" class="easyui-searchbox" name="rname" style="width:400px" 
	data-options="searcher:search,prompt:'此处输入用户姓名，支持模糊查询',menu:'#rnames'"></input> 

	<div id="rnames" style="width:120px"> 
		<div data-options="name:'all',iconCls:'icon-ok'">用户姓名</div> 
	</div>
</div>


<!-- 定义添加窗口-->
<div id="tb_user_add_dialog" class="easyui-dialog" title="新增用户信息" style="width:450px;height:400px;align:center;top:1px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_user_add_dialog_bt'">   
       <form id="user_addForm" method="post">
       		<br><br><br>
       		<table>
	    		<tr>
	    			<td width="25px"></td>
	    			<td align="right">注&nbsp;册&nbsp;号：</td>
	    			<td>
	    				<input type="text" style="width:200px;" id="lname" name="lname"  class="easyui-validatebox" data-options="required:true" />
					</td>
					<td><span id="lname_span"></span></td>
	    		</tr>
	    		<tr>
	    			<td></td>
	    			<td align="right">密&nbsp;&nbsp;码&nbsp;&nbsp;：</td>
	    			<td>
	    				<input type="password" style="width:200px;" value="123456" name="lpass"/>
	    			</td>
	    		</tr>	    		
	    		<tr>
	    			<td></td>
	    			<td align="right">姓名：</td>
	    			<td>
	    				<input type="text" style="width:200px;" name="rname"/>
	    			</td>
	    		</tr>
	    			    		
	    		<tr>
	    			<td></td>
	    			<td align="right">电&nbsp;&nbsp;话&nbsp;&nbsp;：</td>
	    			<td>
	    				<input type="text" style="width:200px;" name="phone"/>
	    			</td>
	    		</tr>	    		
	    		
	    		<tr>
	    			<td></td>
	    			<td align="right">所属部门：</td>
	    			<td>
	    				<select id="dpart" class="easyui-combobox" name="dpart.id" style="width:200px;">   
									     
						</select> 
	    			</td>
	    		</tr>
	    		<tr>
	    			<td></td>
					<td style="text-align: rigth;">启停状态：</td>
					<td>
						<input type="radio" name="isdisable" value="1" checked="checked"/>启用
						<input type="radio" name="isdisable" value="0"/>禁用
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
<div id="tb_user_upd_dialog" class="easyui-dialog" title="修改用户信息" style="width:450px;height:400px;align:center;top:1px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_user_upd_dialog_bt',">   
       <form id="user_updForm" method="post">
       		<br><br><br>
       		<table>
	    		<tr>
	    			<td width="25px"></td>
	    			<td align="right">注&nbsp;册&nbsp;号：</td>
	    			<td>
	    				<input type="text" style="width:200px;" id="lname" name="lname" readonly="readonly"/>
	    				<!-- 隐藏的ID值 -->
	    				<input type="hidden" id="id" name="id"/>
					</td>
					<td><span style="color: red">该项无法修改</span></td>
	    		</tr>    		
	    		<tr>
	    			<td></td>
	    			<td align="right">姓名：</td>
	    			<td>
	    				<input type="text" style="width:200px;" name="rname" id="rname"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td></td>
	    			<td align="right">电&nbsp;&nbsp;话&nbsp;&nbsp;：</td>
	    			<td>
	    				<input type="text" style="width:200px;" name="phone" id="phone"/>
	    			</td>
	    		</tr>	    		
	    		<tr>
	    			<td></td>
	    			<td align="right">所属部门：</td>
	    			<td>
	    				<select id="dpart" class="easyui-combobox" name="dpart.id" style="width:200px;">   
									     
						</select> 
	    			</td>
	    		</tr>
	    		<tr>
	    			<td></td>
					<td style="text-align: rigth;">启停状态：</td>
					<td>
						<input type="radio" name="isdisable" value="1" checked="checked"/>启用
						<input type="radio" name="isdisable" value="0"/>禁用
					</td>
				</tr>
			</table>	
       </form>
</div>
<div id="tb_user_upd_dialog_bt">
	<a id="tb_user_upd_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
	<a id="tb_user_upd_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>

<!-- 指派角色窗口 -->
<div id="tb_user_grant_dialog" class="easyui-dialog" title="指派角色" style="width:360px;height:360px;align:center;top:1px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_user_grant_dialog_bt',">   
      <div id="grantroles">
      	
      </div>
</div>        

<div id="tb_user_grant_dialog_bt">
	<a id="tb_user_grant_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
	<a id="tb_user_grant_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>