<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../Inc.jsp"></jsp:include>
<style type="text/css">
body {
		margin: 8px;
		padding: 0;
	}
	
	.td_left{
		background: #b6c1ca;
		width:20%;
		text-align: right;
		height:30px;
	}
	.td_right{
		width: 30%;
	}
	.datagrid-btable tr td{
		border:1px solid #c0cdd8;
        height: 30px;
	}
	 table{
        border-collapse:collapse;
        border: solid 1px #c0cdd8;
    }
	 input{
		padding:2px 0;
		margin-left:2px;
		border: #5890a570 1px solid;
		outline-color:#1553a263 ;
		width: 80%;
		font-size: 15px;
	}
	
	#is1{
        width: 25px;
    }
    #is2{
        width: 25px;
    }


</style>
<script type="text/javascript">
    //$('#personfile_updForm #sex1').val("1");
    
    $(function(){
    	/* 页面自适应 */
		$(window).resize(function () {
            $('#dg_persionfile').datagrid('resize', {
                width: $(window).width() - 50,
            }).datagrid('resize', {
                width: $(window).width() - 50,
            });
        });
        
        var inservice = $("input[name='inservice']:checked").val();
	        
        //加载表格数据
		$('#dg_persionfile').datagrid({    
	    url:'persionfile_findAllPersonfiles.action', 
	    pagination:'true',
	    singleSelect:'true',//只允许选取一行
	    //sortName:'lname,isgrant',//定义哪些行可以排序
	    fitColumns:true,//宽度自适应
	    //rownumbers:true,   
	    toolbar: '#tb_personfile',  
	    queryParams:{inservice:inservice},               
	    columns:[[
	        {checkbox:true},     
	        {field:'id',title:'编号',align:'center',width:4}, 
	        {field:'name',title:'姓名',align:'center',width:8},
	        {field:'sex',title:'性别',align:'center',width:8,
	        	formatter: function(value,row,index){
					if (row.sex==1){
						return '男';
					} else {
						return '女';
					}
				}
	        },
	        {field:'depart',title:'部门',align:'center',width:8,
	        	formatter: function(value,row,index){
					if (row.depart==4){
						return '综合部';
					}else if (row.depart==5){
						return '业务部';
					}else if (row.depart==6){
						return '研发部';
					}else {
						return '财务部';
					}
				}
	        },
	        {field:'job',title:'职务',align:'center',width:8}, 
	        {field:'email',title:'邮箱',align:'center',width:14}, 
	        {field:'startdate',title:'入职日期',align:'center',width:8}, 
	        {field:'education',title:'学历',align:'center',width:8},
	        {field:'workdate',title:'转正日期',align:'center',width:8}, 
	        {field:'enddate',title:'合同到期日期',align:'center',width:8}, 
	        {field:'phone',title:'联系电话',align:'center',width:8}, 
	        {field:'driver',title:'是否有驾驶证',align:'center',width:8,
	        	formatter: function(value,row,index){
					if (row.driver==0){
						return '无';
					} else {
						return '有';
					}
				}
	        },
	        {field:'inservice',title:'是否在职',align:'center',width:8,
	        	formatter: function(value,row,index){
					if (row.inservice==0){
						return '离职';
					} else {
						return '在职';
					}
				}
	        },
	        {field:'remark',title:'备注',align:'center',width:8},
	        ]]
		      
		});
		
		$("input[name='inservice']").click(function(){
            var inservice = $(this).val();
            $('#dg_persionfile').datagrid('reload',{"inservice":inservice});
        });
		
        //设置text需要验证
        $('input[type=text]').validatebox();
    
        //打开新增档案窗体
		$('#tb_personfile_add').on('click',function(){
		    //初始化数据
		    //$("#sex").append("<option value=''>请选择</option>");
		    initCombox($('#personfile_addForm #depart'),'user_finddpartList.action',null,-1);
		    $('#depart').prepend("<option value=''>请选择</option>'");
			$('#tb_personfile_add_dialog').dialog('open'); //点击新增按钮弹出新增页面
		});
		
		$('#tb_personfile_add_dialog_bts').on('click',function(){
			$('#personfile_addForm').form('submit', {    
			    url:'persionfile_add.action',     
			    success:function(data){  
			        var obj=$.parseJSON(data);
			        if(obj.success){
			            $('#personfile_addForm').form('clear');//清除表单数据。
						$('#dg_persionfile').datagrid('reload');// 重新载入当前页面数据 
						$('#tb_personfile_add_dialog').dialog('close');//关闭对话框
			            //window.location.reload();//重新载入当前页面
			        }
			        $.messager.show({
					    title:'提示',
						msg:obj.message
					});
			        //alert("success!!");
			    }    
			});
		});
		$('#tb_personfile_add_dialog_btc').on('click',function(){
			$('#tb_personfile_add_dialog').dialog('close');
		});
		
		//修改档案
		$('#tb_personfile_ed').on('click',function(){
			var rows=$('#dg_persionfile').datagrid('getChecked'); //在复选框呗选中的时候返回所有行  
			if(rows.length==0){
				 $.messager.show({
								title:'提示',
								msg:'请勾选要修改的记录'
				});
			}else if(rows.length!=1){
				$.messager.show({
								title:'提示',
								msg:'修改时只能选择一一条记录'
			    });
			}else{
			    
			    $('#personfile_updForm #id').val(rows[0].id);
				$('#personfile_updForm #name').val(rows[0].name);
				$('#personfile_updForm #sex').val(rows[0].sex);
				//$('#personfile_updForm #sex').find("option[value = '"+rows[0].sex+"']").attr("selected","selected");
				$('#personfile_updForm #depart').val(rows[0].depart);
				$('#personfile_updForm #job').val(rows[0].job);
				$('#personfile_updForm #email').val(rows[0].email);
				//$('#personfile_updForm #startdate').val(rows[0].startdate);
				$('#personfile_updForm #startdate').datebox('setValue', rows[0].startdate);	
				$('#personfile_updForm #education').val(rows[0].education);
				//$('#personfile_updForm #workdate').val(rows[0].workdate);
				$('#personfile_updForm #workdate').datebox('setValue', rows[0].workdate);	
				//$('#personfile_updForm #enddate').val(rows[0].enddate);
				$('#personfile_updForm #enddate').datebox('setValue', rows[0].enddate);
				$('#personfile_updForm #phone').val(rows[0].phone);
				$('#personfile_updForm #driver').val(rows[0].driver);
				$('#personfile_updForm #inservice').val(rows[0].inservice);
				$('#personfile_updForm #remark').val(rows[0].remark);
				$('#tb_personfile_upd_dialog').dialog('open');
				//alert($('#personfile_updForm #sex').val());
				//$('#personfile_updForm #name').focus();
				
			} 
		});
		
		//修改保存
		$('#tb_personfile_upd_dialog_bts').on('click',function(){
			$('#personfile_updForm').form('submit', {    
			    url:'persionfile_update.action',     
			    success:function(data){    
			        var obj=$.parseJSON(data);
			        if(obj.success){
			        	$('#personfile_updForm').form('clear');
						$('#dg_persionfile').datagrid('reload');
						$('#tb_personfile_upd_dialog').dialog('close');	
			        }
			        $.messager.show({
					    title:'提示',
					    msg:obj.message
					});
			    }    
			});
		});
		
		$('#tb_personfile_upd_dialog_btc').on('click',function(){
			$('#tb_personfile_upd_dialog').dialog('close');
		});
		
		date_formatter('#startdate');
		date_formatter('#workdate');
		date_formatter('#enddate');
		
        
        //选择框验证
        $.extend($.fn.validatebox.defaults.rules, {
            selectValueRequired: {
                validator: function(value,param){
                    return $(param[0]).find("option:contains('"+value+"')").val() != '';
                },
                message: "该输入项是必填项！"
            }
        }); 
        
        //验证手机号  
        $.extend($.fn.validatebox.defaults.rules, {   
            phoneNum: { 
                validator: function(value, param){
                return /^1[3-8]+\d{9}$/.test(value);
                },   
                message: '请输入正确的手机号码!'  
            }
	    });
	});
	
	function date_formatter(id){ 
		$(id).datebox({
            formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); }, 
            parser: function(date){ return new Date(Date.parse(date.replace(/-/g,"/"))); }
        });
	} 
	
	function search(value,name){ 
		$('#dg_persionfile').datagrid('reload',{"name":value});
	} 
	 
</script>

<!-- 用户数据表格 -->
<table class="datagrid-btable" id="dg_persionfile"></table>
<br><br><br>
&nbsp;&nbsp;使用须知：<br>
&nbsp;&nbsp;1，档案无法删除以保持系统数据的完整性；<br>


<!-- 档案列表 -->
<div id="tb_personfile">
	<a id="tb_personfile_add" style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
	<div class="datagrid-btn-separator"></div> 
	<a id="tb_personfile_ed" style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
	<div class="datagrid-btn-separator"></div> 
	<!-- 创建搜索框 -->
	<input id="name" class="easyui-searchbox" name="name" style="width:400px" 
	data-options="searcher:search,prompt:'此处输入姓名，支持模糊查询',menu:'#names'"></input> 

	<div id="names" style="width:120px"> 
		<div data-options="name:'all',iconCls:'icon-ok'">姓名</div> 
	</div>
	<input id="is1" name="inservice" type="radio" value="1" checked="checked"/>在职
    <input id="is2" name="inservice" type="radio" value="0"/>离职
</div>
		
<!-- 定义添加窗口-->
<div id="tb_personfile_add_dialog" class="easyui-dialog" title="新增人事档案" style="width:700px;height:400px;align:center;top:1px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_personfile_add_dialog_bt'">   
       <form id="personfile_addForm" method="post">
       		<br><br><br>
       		<table class="datagrid-btable" style="width: 96%;margin-left:2%;border: solid 1px #c0cdd8;border-right: none;border-bottom: none;">
	    		<tr>
	    			<td class="td_left">姓名：</td>
	    			<td class="td_right">
	    				<input type="text" style="width:92%; height: 25px;" id="name" name="name"  class="easyui-validatebox" data-options="required:true" />
					</td>
					
					<td class="td_left">性别：</td>
	    			<td class="td_right">
	    				<select style="width:180px;" class="easyui-combobox" id="sex" name="sex" required="true" validType="selectValueRequired['#sex']" >
	    				    <option value=''>请选择</option>
	    				    <option value='0'>女</option>
	    				    <option value='1'>男</option>
	    				</select> 
					</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">部门：</td>
	    			<td>
	    				<input id="depart" name="depart" style="width:180px;"/>
					</td>
					
					<td class="td_left">现任职务：</td>
	    			<td>
	    				<input type="text" style="width:92%;" id="job" name="job"  class="easyui-validatebox" data-options="required:true" />
					</td>
					
	    		</tr>
	    		<tr>
	    			<td class="td_left">邮箱：</td>
	    			<td>
	    				<input style="width:92%;" id="email" name="email" type="text" validtype="email" required="true" missingMessage="不能为空" invalidMessage="邮箱格式不正确" />
					</td>
					
					<td class="td_left">入职日期：</td>
	    			<td>
	    				<input id="startdate" name="startdate"  type="text" style="width:180px;" class="easyui-datebox" editable="false" value=""/>
					</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">学历：</td>
	    			<td>
	    				<select style="width:180px;" class="easyui-combobox" id="education" name="education" required="true" validType="selectValueRequired['#education']">
	    				    <option value=''>请选择</option>
	    				    <option value='初中'>初中</option>
	    				    <option value='高中'>高中</option>
	    				    <option value='中专'>中专</option>
	    				    <option value='大专'>大专</option>
	    				    <option value='本科'>本科</option>
	    				    <option value='硕士'>硕士</option>
	    				    <option value='博士'>博士</option>
	    				</select>
					</td>
					
					<td class="td_left">转正日期：</td>
	    			<td>
	    				<input id="workdate" name="workdate"  type="text" style="width:180px;" class="easyui-datebox" editable="false" value=""/>
					</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">合同到期日期：</td>
	    			<td>
	    				<input id="enddate" name="enddate"  type="text" style="width:180px;" class="easyui-datebox" editable="false" value=""/>
	    			</td>
					
					<td class="td_left">联系电话：</td>
	    			<td>
	    				<input id="phone" name="phone" style="width:92%;" type="text" validType="phoneNum" data-options="required:true" />
					</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">是否有驾驶证：</td>
	    			<td>
	    				<select style="width:180px;" class="easyui-combobox" id="driver" name="driver" required="true" validType="selectValueRequired['#driver']" >
	    				    <option value=''>请选择</option>
	    				    <option value='0'>无</option>
	    				    <option value='1'>有</option>
	    				</select>
	    			</td>
					
					<td class="td_left">是否在职：</td>
	    			<td>
	    				<select style="width:180px;" class="easyui-combobox" id="inservice" name="inservice" required="true" validType="selectValueRequired['#driver']" >
	    				    <option value=''>请选择</option>
	    				    <option value='0'>离职</option>
	    				    <option value='1'>在职</option>
	    				</select>
	    			</td>
	    		</tr>
	    		
	    		<tr>
	    			<td class="td_left">备注：</td>
	    			<td colspan="3">
	    				<input id="remark" name="remark" style="height: 30px;width: 95%;"/>
	    			</td>
	    		</tr>
	    	</table>	
       </form>
</div>
<div id="tb_personfile_add_dialog_bt">
	<a id="tb_personfile_add_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
	<a id="tb_personfile_add_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>

<!-- 定义修改窗口-->
<div id="tb_personfile_upd_dialog" class="easyui-dialog" title="修改人事档案" style="width:700px;height:400px;align:center;top:1px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_personfile_upd_dialog_bt'">   
       <form id="personfile_updForm" method="post">
       		<br><br><br>
       		<table class="datagrid-btable"  style="width: 96%;margin-left:2%;border: solid 1px #c0cdd8;border-right: none;border-bottom: none;">
	    		<tr>
	    			<td class="td_left">姓名：</td>
	    			<td class="td_right">
	    				<input type="hidden" id="id" name="id"/>
	    				<input type="text" style="width:92%;" id="name" name="name"  class="easyui-validatebox" data-options="required:true" />
					</td>
					
					<td class="td_left">性别：</td>
	    			<td class="td_right">
	    				<select style="width:92%;" id="sex" name="sex" required="true" validType="selectValueRequired['#sex']" >
	    				    <option value=''>请选择</option>
	    				    <option value='0'>女</option>
	    				    <option value='1'>男</option>
	    				</select> 
					</td>
					
	    		</tr>
	    		<tr>
	    			<td class="td_left">部门：</td>
	    			<td>
	    			    <select style="width:92%;" id="depart" name="depart" required="true" validType="selectValueRequired['#depart']" >
	    				    <option value='4'>综合部</option>
	    				    <option value='5'>业务部</option>
	    				    <option value='6'>研发部</option>
	    				    <option value='7'>财务部</option>
	    				</select> 
					</td>
					
					<td class="td_left">现任职务：</td>
	    			<td>
	    				<input type="text" style="width:92%;" id="job" name="job"  class="easyui-validatebox" data-options="required:true" />
					</td>
					
	    		</tr>
	    		<tr>
	    			<td class="td_left">邮箱：</td>
	    			<td>
	    				<input style="width:92%;" id="email" name="email" type="text" validtype="email" required="true" missingMessage="不能为空" invalidMessage="邮箱格式不正确" />
					</td>
					
					<td class="td_left">入职日期：</td>
	    			<td>
	    				<input id="startdate" name="startdate"  type="text" style="width:180px;" class="easyui-datebox" editable="false" value=""/>
					</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">学历：</td>
	    			<td>
	    				<select style="width:92%;" id="education" name="education" required="true" validType="selectValueRequired['#education']">
	    				    <option value=''>请选择</option>
	    				    <option value='初中'>初中</option>
	    				    <option value='高中'>高中</option>
	    				    <option value='中专'>中专</option>
	    				    <option value='大专'>大专</option>
	    				    <option value='本科'>本科</option>
	    				    <option value='硕士'>硕士</option>
	    				    <option value='博士'>博士</option>
	    				</select>
					</td>
					
					<td class="td_left">转正日期：</td>
	    			<td>
	    				<input id="workdate" name="workdate"  type="text" style="width:180px;" class="easyui-datebox" editable="false" value=""/>
					</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">合同到期日期：</td>
	    			<td>
	    				<input id="enddate" name="enddate"  type="text" style="width:180px;" class="easyui-datebox" editable="false" value=""/>
	    			</td>
					
					<td class="td_left">联系电话：</td>
	    			<td>
	    				<input id="phone" name="phone" style="width:92%;" type="text" validType="phoneNum" data-options="required:true" />
					</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left">是否有驾驶证：</td>
	    			<td>
	    				<select style="width:92%;" id="driver" name="driver" required="true" validType="selectValueRequired['#driver']" >
	    				    <option value=''>请选择</option>
	    				    <option value='0'>无</option>
	    				    <option value='1'>有</option>
	    				</select>
	    			</td>
					
					<td class="td_left">是否在职：</td>
	    			<td>
	    				<select style="width:92%;" id="inservice" name="inservice" required="true" validType="selectValueRequired['#driver']" >
	    				    <option value=''>请选择</option>
	    				    <option value='0'>离职</option>
	    				    <option value='1'>在职</option>
	    				</select>
	    			</td>
	    		</tr>
	    		
	    		<tr>
	    			<td class="td_left">备注：</td>
	    			<td colspan="3">
	    				<input id="remark" name="remark" style="height: 30px;width: 95%;"/>
	    			</td>
	    		</tr>
	    	</table>	
       </form>
</div>
<div id="tb_personfile_upd_dialog_bt">
	<a id="tb_personfile_upd_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
	<a id="tb_personfile_upd_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>
