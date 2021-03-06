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
	}
	.td_right{
		width: 30%;
	}
	 table tr td{
		    border-right: solid 1px #c0cdd8;
  			border-bottom: solid 1px #c0cdd8;
			padding:3px 0;
			height: 30px;
			font-size: 15px;
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
		<div id="tj" class="easyui-dialog" title="公车信息增加"
			style="width:600px;height:atuo;padding:25px 25px 50px 25px;top:15px;"
			data-options="iconCls:'icon-save',resizable:true,modal:true,
	        buttons:'#tb_user_add_dialog_bt'">
			<form method="post" id="form" enctype="multipart/form-data"
				style="margin-top: 15px; margin-left: 15px;">
				<input type="hidden" name="id" id="id" value="0">
					<div style="width: 100%;">
						<table style="width: 96%;margin-left:2%;border: solid 1px #c0cdd8;border-right: none;border-bottom: none;" cellpadding="1" cellspacing="0">
								<tr>
									<td class="td_left">车牌号:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<input id="carnumber" name="carnumber" type="text" style="width:87%;" >
									</td>
								</tr>
								<tr>
									<td class="td_left">年检日期:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<input id="yearcheck" name="yearcheck" editable="false" type="text" style="width:325px;height: 25px;" class="easyui-datebox" value="1">
									</td>
								</tr>
								<tr>
									<td class="td_left">保险日期:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<input id="insurance" name="insurance" editable="false" type="text" style="width:325px;height: 25px;" class="easyui-datebox" value="1">
									</td>
								</tr>
								<tr>
									<td class="td_left">保养日期:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<input id="bdate" name="bdate" editable="false" type="text" style="width:325px;height: 25px;" class="easyui-datebox" value="1">
									</td>
								</tr>
								<tr>
									<td class="td_left">保养公里数:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<input id="bsum" name="bsum" type="text" style="overflow: hidden;width:87%;" >
									</td>
								</tr>
								<tr>
									<td class="td_left">排量:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<!-- <input id="output" name="output" type="text" style="overflow: hidden;width:87%;"> -->
										<select id="output" editable="false" name="output" class="easyui-combobox" style="width:325px;height: 25px;margin-left: 15px;">   
									     
										</select>
									升</td>
								</tr>
								<tr>
									<td class="td_left">油卡卡号:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<input id="blanknum" name="blanknum" type="text" style="overflow: hidden;width:87%;" >
									</td>
								</tr>
								<tr>
									<td class="td_left">购车时间:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<input id="buytime" name="buytime" editable="false" type="text" style="width:325px;height: 25px;" class="easyui-datebox" value="1">
									</td>
								</tr>
								<tr>
									<td class="td_left">品牌型号:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<input id="brand" name="brand" type="text" style="overflow: hidden;width:87%;" >
									</td>
								</tr>
								<tr>
									<td class="td_left">油卡余额:
										<font color="red">*</font>
									</td>
									<td class="td_right">
										<input id="balance" name="balance" type="text" style="overflow: hidden;width:87%;" >
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
		<div style="margin-left: 25px; height: 40px; float: left;">
			<div style="margin-top: 15px;">
				<a href="javascript:add()" id="tb_user_add" style="float:left;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
				<div class="datagrid-btn-separator"></div> 
			</div>
		</div>
		<div style="margin-left: 25px; height: 40px;float: left;">
			<script type="text/javascript">
				function search(value, name) {
					$('#dg').datagrid('reload', {
						"carnumber" : value
					});
				}
			</script>
			<div style="margin-top: 15px;">
				<input id="uuu" class="easyui-searchbox" style="width:400px;"
					data-options="searcher:search,prompt:'此处输入车牌号，支持模糊查询',menu:'#mm'"></input>
				<div id="mm" style="width:120px">
					<div data-options="name:'all',iconCls:'icon-ok'">车牌号码</div>
				</div>

			</div>
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
			
			/* 列表展示 */
			$("#dg").datagrid({
					'url' : 'publiccar_list.action',//请求的地址
					  fitColumns:true,//宽度自适应
					'loadMsg' : '正在加载...',//加载时的提示
					'rownumbers' : true,//显示行数
					'pagination':true,
					'singleSelect' : true,//只允许选择一行
					//pageSize:20,
					 toolbar: '#tb',
					columns : [[{field : 'carnumber',title : '车牌号',align:'center',width:10},
							{field : 'yearcheck',title : '年检日期',align:'center',width:10},
							{field : 'insurance',title : '保险日期',align:'center',width:10},
							{field : 'bdate',title : '保养日期',align:'center',width:10},
							{field : 'bsum',title : '保养公里数',align:'center',width:10},
							{field : 'output',title : '排量',align:'center',width:10},
							{field : 'blanknum',title : '油卡卡号',align:'center',width:10},
							{field : 'buytime',title : '购车时间',align:'center',width:10},
							{field : 'brand',title : '品牌型号',align:'center',width:10},
							{field : 'balance',title : '油卡余额',align:'center',width:10},
							{field : 'createtime',title : '记录时间',align:'center',width:10},
							{field : 'null',title : '操作',align:'center',width:10,
								formatter : function(value, row, index) {
									   return "<a style='margin-right: 5px;' href='javascript:edit(" + row.id+ ");'>修改</a>"
									   		 +"<a style='margin-right: 5px;margin-left: 15px;' href='javascript:del(" + row.id+ ");'>删除</a>";
								}
							}
					]]
			});
  	});
  /* 	function date_formatter(id){ 
		$(id).datebox({
            formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); }, 
            parser: function(date){ return new Date(Date.parse(date.replace(/-/g,"/"))); }
        });
	}  */
  	//车辆增加
  	function add(){
  		//获取当前时间开始
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
        //获取当前时间结束
        $('#tj #yearcheck').datebox('setValue', currentdate);
        $('#tj #buytime').datebox('setValue', currentdate);
        $('#tj #insurance').datebox('setValue',currentdate);
        $('#tj #bdate').datebox('setValue',currentdate);
        initCombox04($('#output'),'global_findGlobal.action',null,-1);
		//打开添加窗口
		$('#tj').dialog('open');
		//取消
		$('#tb_user_add_dialog_btc').on('click',function(){
			$('#tj').dialog('close');
			window.location.reload();//重新载入当前页面
		});
		//你想在关闭时执行的代码
		$('#tj').dialog({onClose:function(){
			window.location.reload();//重新载入当前页面
		}});
  		/* form表单提交 */	
		var a = false;
		var b = false;
		$('#tb_user_add_dialog_bts').on('click',function(){
		
			var inp = $("#form").find("table").find("tr").find("td").find("input[type='text']").each(function(){
					var values = $(this).val();
					 if (!values || values.trim()==""){
						$(this).css("border","1px solid red");
						$(this).attr("placeholder","提示:本项不能为空");
						a= false;
						return false;
					}else{
						$(this).css("border","1px solid #C0CDD8");
						$(this).removeAttr("placeholder");
						a =true;
					}
			});
			if(a){
				$("#form").form("submit", {
				    url:'publiccar_add.action',     
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
			}
		});
	}
	//删除
	function del(id){
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){    
		        $.ajax({
					url : 'publiccar_del.action',
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
	//编辑
	function edit(id){
		var cnum = document.getElementById("carnumber").readOnly=true;
		var outp = document.getElementById("output").readOnly=true;
		var a = false;
		var b = false;
		//改变对话框的标题
		$('#tj').dialog({ title: '车辆信息修改'});
		//根据id获取信息
		$.ajax({
			    url : 'publiccar_findById.action',
			    type : "post",
			    data:{id:id},
			    dataType : "json",  
			    success : function(data)  {
				    //回填数据
				    //给隐藏域赋值
				    $('#tj #id').val(data.id);
					$('#tj #carnumber').val(data.carnumber);
					$('#tj #yearcheck').datebox('setValue', data.yearcheck);
					$('#tj #bdate').datebox('setValue', data.bdate);
					$('#tj #bsum').val(data.bsum);
					//$('#tj #output').val(data.output);
					initCombox04($('#output'),'global_findGlobal.action',null,data.output);
					$('#tj #blanknum').val(data.blanknum);
					$('#tj #buytime').datebox('setValue', data.buytime);
					$('#tj #brand').val(data.brand);
					$('#tj #insurance').datebox('setValue', data.insurance);
					$('#tj #balance').val(data.balance);
					$('#tj #create').val(data.create);
					
					//打开对话框
					$("#tj").dialog('open');
					$('#tb_user_add_dialog_bts').on('click',function(){
						var inp = $("#form").find("table").find("tr").find("td").find("input[type='text']").each(function(){
								var values = $(this).val();
								 if (!values || values.trim()==""){
									$(this).css("border","1px solid red");
									$(this).attr("placeholder","提示:本项若无请填写  0");
									a= false;
									return false;
								}else{
									$(this).css("border","1px solid #C0CDD8");
									$(this).removeAttr("placeholder");
									a =true;
								}
						});
							if(a){
								$('#form').form('submit', {
								    url:'publiccar_updata.action',     
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
							}
			 	    });
					//取消
					$('#tb_user_add_dialog_btc').on('click',function(){
						$('#tj').dialog('close');
						window.location.reload();//重新载入当前页面
					});
					$('#tj').dialog({onClose:function(){
				  			 //你想在关闭时执行的代码
						window.location.reload();//重新载入当前页面
					}});
			 },
		});
	}

	
</script>
</html>