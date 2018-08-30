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
		width:25%;
		text-align:right;
		height:30px;
		
	}
	.td_right{
		width:25%;
	}
	.td_left_1{
		background: #b6c1ca;
		width:30%;
		text-align:right;
		height:30px;
		
	}
	.td_right_1{
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
		$('#dg_changeequipment').datagrid({    
	    url:'ce_findAll.action', 
	    pagination:'true',
	    singleSelect:'true',//只允许选取一行
	    fitColumns:true,//宽度自适应  
	    rownumbers:true,
	    toolbar: '#tb_changeequipment',
	    columns:[[
	        {checkbox:true},   
	        {field:'id',title:'编号',align:'center',width:4},  
	        {field:'customer',title:'所属公司',align:'center',width:8},
	        {field:'carnumber',title:'车牌号码',align:'center',width:8},
	        {field:'color',title:'车辆颜色',align:'center',width:6},
	        {field:'phone',title:'联系电话',align:'center',width:8},
	        {field:'sim',title:'SIM卡号',align:'center',width:8},
	        {field:'terminal',title:'终端型号/终端品牌',align:'center',width:10},
	        {field:'people',title:'安装人',align:'center',width:6,
	        	formatter: function(value,row,index){
	        		return value.name;
				}
			},
			{field:'changedate',title:'换装日期',align:'center',width:8},
			{field:'cartype',title:'车辆类型',align:'center',width:10,
				formatter: function(value,row,index){
					return value.text;
				}
			},
			{field:'number',title:'安装单编号',align:'center',width:8},
			{field:'registerdate',title:'登记日期',align:'center',width:8},
			{field:'endpay',title:'结款',align:'center',width:6},
			{field:'remark',title:'备注',align:'center',width:12},
	        ]]    
		});
		
		//打开新增窗体
		$('#tb_changeequipment_add').on('click',function(){
		    $('#tb_changeequipment_add_dialog').dialog('open'); //点击新增按钮弹出新增页面
		    initCombox01($('#changeequipment_addForm #peopleid'),'persionfile_findPeople.action',null,-1);
		    initCombox($('#changeequipment_addForm #cartype'),'ce_findCartypeList.action',null,-1);
		    setDatebox('#changeequipment_addForm #changedate');
		    setDatebox('#changeequipment_addForm #registerdate');
		});
		
		
		
		//打开选择公司窗体
		$('#tb_company_dialog_bt').on('click',function(){
		    var top = $("#tb_changeequipment_add_dialog").offset().top + 150;
            var left = $("#tb_changeequipment_add_dialog").offset().left+470;
            $('#tb_company_dialog').dialog('open').window('resize',{top: top,left:left}); //点击新增按钮弹出新增页面
            
            //加载表格数据
		    $('#dg_company').datagrid({    
	            url:'customer_findTransportCompanys.action', 
	            pagination:'true',
	            singleSelect:'true',//只允许选取一行
	            fitColumns:true,//宽度自适应  
	            rownumbers:true,
	            columns:[[
	                {checkbox:true},     
	                {field:'id',title:'编号',align:'center',width:2},
	                {field:'name',title:'企业名称',align:'center',width:8},
	            ]]    
		    });
		});
		
		//编辑打开选择公司窗体
		$('#tb_ed_company_dialog_bt').on('click',function(){
		    var top = $("#tb_changeequipment_edit_dialog").offset().top + 150;
            var left = $("#tb_changeequipment_edit_dialog").offset().left+470;
            $('#tb_edit_company_dialog').dialog('open').window('resize',{top: top,left:left}); //点击新增按钮弹出新增页面
            
            //加载表格数据
		    $('#dg_edit_company').datagrid({    
	            url:'customer_findTransportCompanys.action', 
	            pagination:'true',
	            singleSelect:'true',//只允许选取一行
	            fitColumns:true,//宽度自适应  
	            rownumbers:true,
	            columns:[[
	                {checkbox:true},     
	                {field:'id',title:'编号',align:'center',width:2},
	                {field:'name',title:'企业名称',align:'center',width:8},
	            ]]    
		    });
		});
		
		//公司选择确定
		$('#tb_company_dialog_bts').on('click',function(){
		    var rows=$('#dg_company').datagrid('getChecked');
			$('#changeequipment_addForm #customer').val(rows[0].name);
			$('#tb_company_dialog').dialog('close');
		});
		
		//公司选择取消
		$('#tb_company_dialog_btc').on('click',function(){
			$('#tb_company_dialog').dialog('close');
		});
		
		//保存
		$('#tb_changeequipment_add_dialog_bts').on('click',function(){
			$('#changeequipment_addForm').form('submit', {    
			    url:'ce_add.action',     
			    success:function(data){  
			    //将 data转换成json  原因：从后台传递过来的数据，都是字符串  
			        var obj=$.parseJSON(data);
			        if(obj.success){
			       		$('#changeequipment_addForm').form('clear');//清除表单数据。
						$('#dg_changeequipment').datagrid('reload');// 重新载入当前页面数据 
						$('#tb_changeequipment_add_dialog').dialog('close');//关闭对话框	
			        }
			        $.messager.show({title:'提示',msg:obj.message});
			    }    
			});
		});
		
		//取消添加换装信息
		$('#tb_changeequipment_add_dialog_btc').on('click',function(){
			$('#tb_changeequipment_add_dialog').dialog('close');
		});
		
		//删除
		$('#tb_changeequipment_del').on('click',function(){
			var rows=$('#dg_changeequipment').datagrid('getChecked');//在复选框呗选中的时候返回所有行   	
			if(rows.length==0){
				 $.messager.show({title:'提示',msg:'请勾选要删除的记录'});
			}else if(rows.length!=1){
				$.messager.show({title:'提示',msg:'删除时只能选择一条记录'});
			}else{
				$.messager.confirm('确认','您确认想要删除该条记录吗？',function(r){    
				    if (r){    
				        $.post('ce_del.action',{"id":rows[0].id},function(data){
							var obj=$.parseJSON(data);
					        $.messager.show({title:'提示',msg:obj.message});
							$('#dg_changeequipment').datagrid('reload');//重新加载
						});    
				    }    
				});  
			} 
		});
		
		//修改信息
		$('#tb_changeequipment_ed').on('click',function(){
			var rows=$('#dg_changeequipment').datagrid('getChecked'); //在复选框呗选中的时候返回所有行  
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
			    $('#tb_changeequipment_edit_dialog').dialog('open');
			    $('#changeequipment_editForm #id').val(rows[0].id);
				$('#changeequipment_editForm #customer').val(rows[0].customer);
				$('#changeequipment_editForm #customerid').val(rows[0].customer.id);
				$('#changeequipment_editForm #carnumber').val(rows[0].carnumber);
	    		$('#changeequipment_editForm #color').val(rows[0].color);
	    		$('#changeequipment_editForm #phone').val(rows[0].phone);
	    		$('#changeequipment_editForm #sim').val(rows[0].sim);
	    		$('#changeequipment_editForm #terminal').val(rows[0].terminal);
	    		initCombox01($('#changeequipment_editForm #peopleid'),'persionfile_findPeople.action',null,rows[0].people.id);
	    		$('#changeequipment_editForm #changedate').datebox('setValue', rows[0].changedate);
	    		initCombox($('#changeequipment_editForm #cartype'),'ce_findCartypeList.action',null,rows[0].cartype.id);
	    		$('#changeequipment_editForm #number').val(rows[0].number);
	    		$('#changeequipment_editForm #registerdate').datebox('setValue', rows[0].registerdate);
				$('#changeequipment_editForm #endpay').val(rows[0].endpay);
				$('#changeequipment_editForm #remark').val(rows[0].remark);
	    		
				
			} 
		});
		
		//修改保存
		$('#tb_changeequipment_edit_dialog_bts').on('click',function(){
			$('#changeequipment_editForm').form('submit', {    
			    url:'ce_update.action',     
			    success:function(data){    
			        var obj=$.parseJSON(data);
			        if(obj.success){
			        	$('#changeequipment_editForm').form('clear');
						$('#dg_changeequipment').datagrid('reload');
						$('#tb_changeequipment_edit_dialog').dialog('close');	
			        }
			        $.messager.show({
					    title:'提示',
					    msg:obj.message
					});
			    }    
			});
		});
		
		//取消修改换装信息
		$('#tb_changeequipment_edit_dialog_btc').on('click',function(){
			$('#tb_changeequipment_edit_dialog').dialog('close');
		});
		
		
		
		//编辑公司选择确定
		$('#tb_edit_company_dialog_bts').on('click',function(){
		    var rows=$('#dg_edit_company').datagrid('getChecked');
			$('#changeequipment_editForm #customer').val(rows[0].name);
			$('#tb_edit_company_dialog').dialog('close');
		});
		
		//编辑公司选择取消
		$('#tb_edit_company_dialog_btc').on('click',function(){
			$('#tb_edit_company_dialog').dialog('close');
		});
		
		//模糊查询
		$('#btn').bind('click', function(){
		    var starttime = document.getElementById("starttime").value;
		    var endtime = document.getElementById("endtime").value;
		    var carnumber = $('#carnumber').val();
		    //把字符串格式转换为日期类
		    var startdate = new Date(Date.parse(starttime));
		    var enddate = new Date(Date.parse(endtime));
		    if((starttime == "" && endtime != "")||(starttime != "" && endtime == "")){
			    $.messager.alert('提示','请输入完整的时间间断'); 
		    }
		    else if(startdate >= enddate){
			    $.messager.alert('提示','起始日期不能大于结束日期'); 
		    }else{
		        var idStart="",idEnd="";
			    $.ajax({
				    url: 'ce_findBytime.action',
				    data:{carnumber:carnumber,idStart:starttime,idEnd:endtime},
				    type:"post",
			        dataType:"json",   
				    success: function(data) {
					    if(data.length == 0){
						    $.messager.alert('提示','根据查询条件，数据库无任何记录');
					    }else{
					        $('#dg_changeequipment').datagrid('reload',{carnumber:carnumber,idStart:starttime,idEnd:endtime});//重新加载
					    }
				    }
			    });  	 
		    }
		});
		
		/* 为导出报表使用 */
		$.ajax({
			url: 'ce_findBytime.action',
			data:{},
			type:"post",
			dataType:"json",   
			success: function(data) {
			}
		});
	});
	
	function search(value,name){ 
		$('#dg_changeequipment').datagrid('reload',{"carnumber":value});
	} 	
	
	function setDatebox(id){
	    var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        $(id).datebox('setValue', currentdate);
	}
</script>
<!-- 用户数据表格 -->
<table class="datagrid-btable" id="dg_changeequipment"></table>
<br><br><br>

<!-- 定义菜单 -->
<div id="tb_changeequipment">
	<a id="tb_changeequipment_add" style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
	<div class="datagrid-btn-separator"></div> 
	<a id="tb_changeequipment_ed" style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
	<div class="datagrid-btn-separator"></div> 
	<a id="tb_changeequipment_del"  style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	<div class="datagrid-btn-separator"></div>
	<!-- 创建搜索框 -->
	车牌号码<input id="carnumber" placeholder="车牌号码" style="width:130px;"></input>&nbsp;&nbsp;
	起始时间：<input id="starttime"  type="text"  style="width:130px;"  placeholder="此处选择起始时间" class="Wdate" onfocus="WdatePicker({lang:'zh-cn',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>&nbsp;------
	结束时间：<input id="endtime"  type="text"  style="width:130px;"  placeholder="此处选择结束时间" class="Wdate" onfocus="WdatePicker({lang:'zh-cn',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
	<button id="btn" class="easyui-linkbutton" style="width:50px;">搜索</button>
	<a href="ce_exportExcel.action" style="text-decoration: none;">
	    <button id="export">导出报表</button>
	</a>
</div>

<!-- 定义添加窗口-->
<div id="tb_changeequipment_add_dialog" class="easyui-dialog" title="新增换装车辆信息" style="width:580px;height:auto;align:center;top:1px;padding:15px 15px 30px 15px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_changeequipment_add_dialog_bt'">   
       <form id="changeequipment_addForm" method="post" enctype="multipart/form-data">
       		<br><br><br>
       		<table class="datagrid-btable" style="width: 96%;margin-left:2%;border: solid 1px #c0cdd8;">
       		    <tr>
	    			<td class="td_left_1">所属公司：</td>
	    			<td class="td_right_1">
	    			    <input type="text" style="width:280px;" id="customer" name="customer"  class="easyui-validatebox" data-options="required:true" readonly="true"/><input id="tb_company_dialog_bt" type="button" value="选择公司"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left_1">车牌号码：</td>
	    			<td class="td_right_1">
	    				<input type="text" style="width:280px;" id="carnumber" name="carnumber"  class="easyui-validatebox" data-options="required:true" />
	    			</td>
	    		</tr>
	    		<tr>
	    		    <td class="td_left_1">车辆颜色：</td>
	    			<td class="td_right_1">
	    			    <select id="color" editable="false" name="color" class="easyui-combobox" style="width:280px;">   
							<option value="蓝色">蓝色</option>
							<option value="黄色">黄色</option>
							<option value="绿色">绿色</option>
						</select>
	    			</td>
	    		</tr>
	    		<tr>
	    		    <td class="td_left_1">联系电话：</td>
	    			<td class="td_right_1">
	    			    <input id="phone" name="phone" style="width:280px;" type="text" />
	    			</td>
	    		</tr>
	    		<tr>
	    		    <td class="td_left_1">SIM卡号：</td>
	    			<td class="td_right_1">
	    			    <input id="sim" name="sim" style="width:280px;" type="text" />
	    			</td>
	    		</tr>
	    		<tr>
	    		    <td class="td_left_1">终端型号/终端品牌：</td>
	    			<td class="td_right_1">
	    			    <input id="terminal" name="terminal" style="width:280px;" type="text" />
	    			</td>
	    		</tr>
	    		<tr>
	    		    <td class="td_left_1">安装人：</td>
	    			<td class="td_right_1">
	    			    <input id="peopleid" name="people.id" style="width:280px;"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left_1">换装日期：</td>
	    			<td class="td_right_1">
	    				<input id="changedate" name="changedate"  type="text" style="width:280px;" class="easyui-datebox" editable="false" value=""/>
	    			</td>
	    		</tr>
	    		<tr>
	    		    <td class="td_left_1">车辆类型：</td>
	    			<td class="td_right_1">
	    			    <input id="cartype" name="cartype.id" style="width:280px;"/>
	    			</td>
	    		</tr>
	    		<tr>
	    		    <td class="td_left_1">安装单编号：</td>
	    			<td class="td_right_1">
	    			    <input id="number" name="number" style="width:280px;" type="text"  />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left_1">登记日期：</td>
	    			<td class="td_right_1">
	    				<input id="registerdate" name="registerdate"  type="text" style="width:280px;" class="easyui-datebox" editable="false" value=""/>
	    			</td>
	    		</tr>
	    		<tr>
	    		<td class="td_left_1">结款：</td>
	    			<td class="td_right_1">
	    				<select style="width:280px;" class="easyui-combobox" id="endpay" name="endpay" >
	    				    <option value='未收款'>未收款</option>
	    				    <option value='已收款'>已收款</option>
	    				    <option value='免费安装'>免费安装</option>
	    				</select> 
					</td>
				</tr>
				<tr>
	    			<td class="td_left_1">备注：</td>
	    			<td class="td_right_1">
	    				<input id="remark" name="remark" style="height: 50px;width: 280px;"/>
	    			</td>
	    		</tr>
			</table>	
       </form>
</div>
<div id="tb_changeequipment_add_dialog_bt">
	<a id="tb_changeequipment_add_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
	<a id="tb_changeequipment_add_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>

<!-- 定义选择公司窗口-->
<div id="tb_company_dialog" class="easyui-dialog" title="公司信息" style="width:350px;height:500px;align:center;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_company_dialog_bt'">  
       <table id="dg_company"></table> 
       <!-- <input type="radio" name="cmys" checked value="1" ><span>aa</span></input> -->
       <!-- <input type="radio" name="cmys" checked value="2" ><span>bb</span></input> -->
</div>
<div id="tb_company_dialog_bt">
	<a id="tb_company_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">确定</a>
	<a id="tb_company_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>

<!-- 定义修改选择公司窗口-->
<div id="tb_edit_company_dialog" class="easyui-dialog" title="公司信息" style="width:350px;height:500px;align:center;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_edit_company_dialog_bt'">  
       <table id="dg_edit_company"></table> 
       <!-- <input type="radio" name="cmys" checked value="1" ><span>aa</span></input> -->
       <!-- <input type="radio" name="cmys" checked value="2" ><span>bb</span></input> -->
</div>
<div id="tb_edit_company_dialog_bt">
	<a id="tb_edit_company_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">确定</a>
	<a id="tb_edit_company_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>

<!-- 定义修改窗口-->
<div id="tb_changeequipment_edit_dialog" class="easyui-dialog" title="修改换装车辆信息" style="width:580px;height:700px;align:center;top:1px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#tb_changeequipment_edit_dialog_bt'">   
       <form id="changeequipment_editForm" method="post" enctype="multipart/form-data">
       		<br><br><br>
       		<table class="datagrid-btable" style="width: 96%;margin-left:2%;border: solid 1px #c0cdd8;">
       		    <tr>
	    			<td class="td_left_1">所属公司：</td>
	    			<td class="td_right_1">
	    			    <input type="text" style="width:280px;" id="customer" name="customer"  class="easyui-validatebox" data-options="required:true" readonly="true"/><input id="tb_ed_company_dialog_bt" type="button" value="选择公司"/>
	    				<input type="hidden" id="id" name="id"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left_1">车牌号码：</td>
	    			<td class="td_right_1">
	    				<input type="text" style="width:280px;" id="carnumber" name="carnumber"  class="easyui-validatebox" data-options="required:true" />
	    			</td>
	    		</tr>
	    		<tr>
	    		    <td class="td_left_1">车辆颜色：</td>
	    			<td class="td_right_1">
	    			    <select id="color" editable="false" name="color" style="width:280px;">   
							<option value="蓝色">蓝色</option>
							<option value="黄色">黄色</option>
							<option value="绿色">绿色</option>
						</select>
	    			</td>
	    		</tr>
	    		<tr>
	    		    <td class="td_left_1">联系电话：</td>
	    			<td class="td_right_1">
	    			    <input id="phone" name="phone" style="width:280px;" type="text" />
	    			</td>
	    		</tr>
	    		<tr>
	    		    <td class="td_left_1">SIM卡号：</td>
	    			<td class="td_right_1">
	    			    <input id="sim" name="sim" style="width:280px;" type="text" />
	    			</td>
	    		</tr>
	    		<tr>
	    		    <td class="td_left_1">终端型号/终端品牌：</td>
	    			<td class="td_right_1">
	    			    <input id="terminal" name="terminal" style="width:280px;" type="text" />
	    			</td>
	    		</tr>
	    		<tr>
	    		    <td class="td_left_1">安装人：</td>
	    			<td class="td_right_1">
	    			    <input id="peopleid" name="people.id" style="width:280px;"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left_1">换装日期：</td>
	    			<td class="td_right_1">
	    				<input id="changedate" name="changedate"  type="text" style="width:280px;" class="easyui-datebox" editable="false" value=""/>
	    			</td>
	    		</tr>
	    		<tr>
	    		    <td class="td_left_1">车辆类型：</td>
	    			<td class="td_right_1">
	    			    <input id="cartype" name="cartype.id" style="width:280px;"/>
	    			</td>
	    		</tr>
	    		<tr>
	    		    <td class="td_left_1">安装单编号：</td>
	    			<td class="td_right_1">
	    			    <input id="number" name="number" style="width:280px;" type="text"  />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="td_left_1">登记日期：</td>
	    			<td class="td_right_1">
	    				<input id="registerdate" name="registerdate"  type="text" style="width:280px;" class="easyui-datebox" editable="false" value=""/>
	    			</td>
	    		</tr>
	    		<tr>
	    		<td class="td_left_1">结款：</td>
	    			<td class="td_right_1">
	    				<select style="width:280px;" class="easyui-combobox" id="endpay" name="endpay" >
	    				    <option value='未收款'>未收款</option>
	    				    <option value='已收款'>已收款</option>
	    				    <option value='免费安装'>免费安装</option>
	    				</select> 
					</td>
				</tr>
				<tr>
	    			<td class="td_left_1">备注：</td>
	    			<td class="td_right_1">
	    				<input id="remark" name="remark" style="height: 50px;width: 280px;"/>
	    			</td>
	    		</tr>
			</table>	
       </form>
</div>
<div id="tb_changeequipment_edit_dialog_bt">
	<a id="tb_changeequipment_edit_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-ok',">保存</a>
	<a id="tb_changeequipment_edit_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
</div>