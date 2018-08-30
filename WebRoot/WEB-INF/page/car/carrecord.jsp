<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../Inc.jsp"></jsp:include>
<style type="text/css">
body {
		margin: 8px;
		padding: 0;
	}
	.td_right{
		width: 30%;
	}
	
	.td_left{
		background: #b6c1ca;
		width:20%;
		text-align: right;
		height: 30px;
	}
	 table tr td{
		    border-right: solid 1px #c0cdd8;
  			border-bottom: solid 1px #c0cdd8;
			padding:3px 0;
			font-size: 15px;
	}
	/* 设置datagrid表格td高度 */
	.datagrid-btable tr td{
		height: 30px;
	}
	.datagrid-htable{
		/* border-top: solid 1px #c0cdd8; */
	}
	#td_left{
		background: #D6E4F0;
		width:20%;
		text-align: right;
		
	}
	 input{
		padding:2px 0;
		margin-left:2px;
		border: #c0cdd8 1px solid;
		outline-color:#c0cdd8 ;
		width: 80%;
		font-size: 15px;
	}
	.searchbox-button{
		width: 40px;
	}
	.searchbox{
		width: 400px;
	}
	
</style>
<html>
 
	   
	  
<body>
	<!-- 列表 -->
	<table id="dg" ></table> 
	<!-- 列表 -->
	<!-- 添加开始 -->
   	<div style="display: none;">
		<div id="tj" class="easyui-dialog" title="出车记录增加"
			style="width:600px;height:atuo;padding:25px 25px 50px 25px;top:15px;"
			data-options="iconCls:'icon-save',resizable:true,modal:true,
	        buttons:'#tb_user_add_dialog_bt'">
			<form method="post" id="form" enctype="multipart/form-data"
				style="margin-top: 15px; margin-left: 15px;">
				<input type="hidden" name="id" id="id" value="0">
					<div style="width: 100%;">
						<table style="width: 96%;margin-left:2%;border: solid 1px #c0cdd8;border-right: none;border-bottom: none;" cellpadding="1" cellspacing="0">
								<tr>
									<td class="td_left">车辆:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<select id="carid" editable="false" name="carid" class="easyui-combobox" style="width:325px;height: 25px;margin-left: 15px;">   
									     
										</select>
									</td>
								</tr>
								<tr>
									<td class="td_left">派车人:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<select id="pnameid" editable="false" name="pnameid" class="easyui-combobox" style="width:325px;height: 25px;margin-left: 15px;">   
									     
										</select>
									</td>
								</tr>
								<tr>
									<td class="td_left">驾车人:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<select id="jnameid" editable="false" name="jnameid" class="easyui-combobox" style="width:325px;height: 25px;margin-left: 15px;">   
									     
										</select>
									</td>
								</tr>
								<tr>
									<td class="td_left">出车时间:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<input id="starttime" name="starttime" type="text" style="width:325px;height: 25px;maxHeight:300px" class="easyui-datetimebox" value="1">
									</td>
								</tr>
								<tr>
									<td class="td_left">出车公里数:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<input id="startkm" name="startkm" type="number" min="0" style="width:87%;" >
									</td>
								</tr>
								<tr>
									<td class="td_left">返回时间:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<input id="endtime" name="endtime" type="text" style="width:325px;height: 25px;" class="easyui-datetimebox" value="1">
									</td>
								</tr>
								<tr>
									<td class="td_left">返回公里数:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<input id="endkm" name="endkm"  type="number" min="0" style="width:87%;" >
									</td>
								</tr>
								<tr>
									<td class="td_left">行驶公里数:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<input id="km" name="km" type="text" style="width:87%;" readonly="true">
									</td>
								</tr>
								<tr>
									<td class="td_left">金额:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<input id="money" name="money" type="text" style="overflow: hidden;width:87%;" >元
									</td>
								</tr>
								<tr>
									<td class="td_left">用途:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<input id="brief" name="brief" type="text" style="overflow: hidden;width:87%;" >
									</td>
								</tr>
								<tr>
									<td class="td_left">备注:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<input id="remark" name="remark" type="text" style="overflow: hidden;width:87%;" >
									</td>
								</tr>
							</table>
					</div>
			</form>
		</div>
		<div id="tb_user_add_dialog_bt">
			<a id="tb_user_add_dialog_bts" class="easyui-linkbutton" data-options="iconCls:'icon-save',">保存</a>
			<a id="tb_user_add_dialog_btc" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',">取消</a>
		</div>
	</div> 
	<!-- 添加结束 -->
	<!-- 模糊查询开始 -->
	<div id="tb">
		<div style="margin-left: 25px; height: 45px; float: left;">
			<div style="margin-top: 8px;">
				<a href="javascript:add()" id="tb_user_add" style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
				<div class="datagrid-btn-separator"></div> 
			</div>
		</div>
		<div style="margin-left: 5px; height: 30px;float: left;">
			<table>
				<tr>
					<td style="border-left: solid 1px #c0cdd8;">
						<div>
							车牌号码<input id="carnum" placeholder="车牌号码" style="width:100px; border: #ABCDEF 1px solid;outline-color:#96c4f3 ;"></input>&nbsp;&nbsp;&nbsp;
							起始时间：<input id="starttime"  type="text"  style="width:120px; height:90%; border: #ABCDEF 1px solid;outline-color:#96c4f3 ;"  placeholder="起始时间" class="Wdate" onfocus="WdatePicker({lang:'zh-cn',skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>&nbsp;---
							结束时间：<input id="endtime"  type="text"  style="width:120px; height:90%; border: #ABCDEF 1px solid;outline-color:#96c4f3 ;"  placeholder="结束时间" class="Wdate" onfocus="WdatePicker({lang:'zh-cn',skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
							<button id="btn" class="easyui-linkbutton" style="width:50px;">搜索</button>
						</div>
					</td>
					<td>
						<!-- 导出报表 -->
							<a href="carrecord_exportExcel.action" style="text-decoration: none;">
						    	<button id="export" style="width: 70px;height: 35px;border: none; background:#d5e0e8;margin-left: 10px;">导出报表</button>
						    </a>
					</td>
				</tr>
			</table>
		</div>
	</div> 
	<!-- 模糊查询结束 -->
</body>
<script type="text/javascript">
	$(function() {
			/* 页面自适应 */
			$(window).resize(function () {
	            $('#dg').datagrid('resize', {
	                width: $(window).width() - 50,
	            }).datagrid('resize', {
	                width: $(window).width() - 50,
	            });
	        });
	        
	        /* 隐藏添加窗口 */
			$('#tj').dialog('close');
			
  			/* 验证session开始 */
			var curPath = window.document.location.href;
		 	var pathName = window.document.location.pathname;
		 	var	basePath = curPath.substring(0,curPath.indexOf(pathName)) + "/";
		 	var s="<%=session.getAttribute("login_users")%>"; 
		 	if(s=="null"){ 
		 		top.location.href = location.href=basePath+"JRmanage";
		 	}
			/* 验证session结束 */
			/* 为导出报表使用 */
			$.ajax({
				url: 'carrecord_list01.action',
				data:{},
				type:"post",
			    dataType:"json",   
				success: function(data) {
					
				}
			});
			/* 列表展示 */
			$("#dg").datagrid({
					'url' : 'carrecord_list.action',//请求的地址
					  fitColumns:true,//宽度自适应
					'loadMsg' : '正在加载...',//加载时的提示
					'rownumbers' : true,//显示行数
					'pagination':true,
					'singleSelect' : true,//只允许选择一行
					//pageSize:20,
					 toolbar: '#tb',
					columns : [[{field:'carid',title:'车辆',align:'center',width:10,},
							{field:'pnameid',title:'派车人',align:'center',width:10,},
					        {field:'jnameid',title:'驾车人',align:'center',width:10,},
							{field : 'starttime',title : '出车时间',align:'center',width:10},
							{field : 'startkm',title : '出车公里数',align:'center',width:10},
							{field : 'endtime',title : '返回时间',align:'center',width:10},
							{field : 'endkm',title : '返回公里数',align:'center',width:10},
							{field : 'km',title : '行驶公里数',align:'center',width:10},
							{field : 'money',title : '金额',align:'center',width:10},
							{field : 'brief',title : '用途',align:'center',width:10},
							{field : 'remark',title : '备注',align:'center',width:10},
							{field : 'createtime',title : '记录时间',align:'center',width:10},
							{field : 'null',title : '操作',align:'center',width:10,
								formatter : function(value, row, index) {
									   return "<a style='margin-right: 5px;margin-left: 15px;' href='javascript:del(" + row.id+ ");'>删除</a>";
								}
							}
					]]
			});
			
			//搜索框模糊查询
			$('#btn').bind('click', function(){
				var starttime = document.getElementById("starttime").value;
				var endtime = document.getElementById("endtime").value;
				var carnum = $('#carnum').val();
				//把字符串格式转换为日期类
				var startdate = new Date(Date.parse(starttime));
				var enddate = new Date(Date.parse(endtime));
				if(startdate > enddate){
					$.messager.alert('提示','起始日期不能大于结束日期'); 
				}else{
				    
				$.ajax({
						url: 'carrecord_findBytime.action',
						data:{carid:carnum,idStart:starttime,idEnd:endtime},
						type:"post",
					    dataType:"json",   
						success: function(data) {
							if(data.length == 0){
								$.messager.alert('提示','根据查询条件，数据库无任何记录');
							}else{
								$('#dg').datagrid('reload',{carid:carnum,idStart:starttime,idEnd:endtime});//重新加载
								/* 为导出报表使用 */
								$.ajax({
									url: 'carrecord_list01.action',
									data:{carid:carnum,idStart:starttime,idEnd:endtime},
									type:"post",
								    dataType:"json",   
									success: function(data) {
										
									}
								});
							}
						}
					});
					
					 
				}
			});
			
  	});
  	//车辆增加
  	function add(){
  		//获取当前时间开始
		var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        var hh = date.getHours();            //时
        var mm = date.getMinutes();          //分
        var ss = date.getSeconds();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        if (hh >= 1 && hh <= 9) {
            hh = "0" + hh;
        }
        if (mm >= 0 && mm <= 9) {
            mm = "0" + mm;
        }
        if (ss >= 0 && ss <= 9) {
            ss = "0" + ss;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate+' '+ hh + ':' + mm + ':' + ss;
        //获取当前时间结束
        //初始化赋值
        $('#tj #starttime').datebox('setValue', currentdate);
        $('#tj #endtime').datebox('setValue', currentdate);
        initCombox03($('#pnameid'),'persionfile_findPeople.action',null,-1);
  		initCombox03($('#jnameid'),'persionfile_findPeople.action',null,-1);
  		initCombox02($('#carid'),'publiccar_findcar.action',null,-1);
		//打开添加窗口
		$('#tj').dialog('open');
		
		//自动添加行驶公里数
		$(this).on('click',function(){
			var startkm = $('#form #startkm').val();
			var endkm = $('#form #endkm').val();
			if(startkm>=1 && endkm>=startkm){
				var km=endkm-startkm;
				$("#km").val(km);
			}
			
		});
		
		//取消
		$('#tb_user_add_dialog_btc').on('click',function(){
			$('#tj').dialog('close');
			window.location.reload();//重新载入当前页面
		});
		//你想在关闭时执行的代码
		$('#tj').dialog({onClose:function(){
			window.location.reload();//重新载入当前页面
		}});
  		//保存	
		$('#tb_user_add_dialog_bts').on('click',function(){
				$("#form").form("submit", {
				    url:'carrecord_add.action',     
				    success:function(data){  
				    //将 data转换成json  原因：从后台传递过来的数据，都是字符串  
				        var obj=$.parseJSON(data);
				        if(obj.success){
				       		$('#form').form('clear');//清除表单数据。
							window.location.reload();//重新载入当前页面
				        }
				        $.messager.show({
							title:'提示',
							msg:obj.message,
							timeout:1000,
							style:{  
								right:'',  
								bottom:''  
							}
						});
				    }    
				});
		});
	}
	//删除
	function del(id){
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){    
		        $.ajax({
					url : 'carrecord_del.action',
					data:{id:id},
					dataType : 'json',
					type : 'POST',
					success : function(data) {
						window.location.reload();//重新载入当前页面
					}
				});    
		    }    
		}); 
	}
	
</script>
</html>