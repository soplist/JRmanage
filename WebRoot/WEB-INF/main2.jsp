<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>

<head>
    <title>管理员首页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<style type="text/css">
	
	 table tr td{
		    border-right: solid 1px #c0cdd8;
			padding:3px 0;
			font-size: 15px;
	}
	
	#state li{
		list-style-type:none;
		line-height:30px;
		float: left;
		text-align: center;
	}
	#notcheck li{
		line-height:30px;
		float: left;
		text-align: center;
		margin-right: 50px;
		
	}	
</style>
</head>
<script type="text/javascript">
	$(function(){	
		/* 页面自适应 */
		$(window).resize(function () {
            $('#dg').datagrid('resize', {
                width: $(window).width() - 50,
            }).datagrid('resize', {
                width: $(window).width() - 50,
            });
        });
        
		/* 验证session开始 */
		var curPath = window.document.location.href;
	 	var pathName = window.document.location.pathname;
	 	var	basePath = curPath.substring(0,curPath.indexOf(pathName)) + "/";
	 	var s="<%=session.getAttribute("login_users")%>"; 
	 	if(s=="null"){ 
	 		top.location.href = location.href=basePath+"JRmanage";
	 	}	
		
	 	
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
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        //获取当前时间结束
	 	/* 获取车辆状态 */
		 $.ajax({
				url: 'publiccar_findcar.action',
				data:{},
				type:"post",
			    dataType:"json",   
				success: function(data) {
					var html="";
					var yearcheck="";
					var bdate="";
					for(var i=0;i<data.length;i++){
							yearcheck=getPreMonth(data[i].yearcheck);
							insurance=getPreMonth(data[i].insurance);
							if(yearcheck<currentdate && insurance>currentdate && data[i].balance>300){
								    html='<li style="width: 25%;">'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 25px; color:#22847d;">'+data[i].carnumber+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">年检到期日期</span><span style="margin-left: 30px;font-size: 18px;color: red;">'+data[i].yearcheck+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">保险到期日期</span><span style="margin-left: 30px;font-size: 18px;color: #06d606;">'+data[i].insurance+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">加油卡余额</span><span style="margin-left: 50px;font-size: 18px;color: #06d606;">'+data[i].balance+'元</span>'
										+'</div>'
									+'</li>';
									$("#state").append(html);
							}else if(yearcheck<currentdate && insurance<currentdate && data[i].balance>300){
								html='<li style="width: 25%;">'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 25px; color:#22847d;">'+data[i].carnumber+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">年检到期日期</span><span style="margin-left: 30px;font-size: 18px;color: red;">'+data[i].yearcheck+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">保险到期日期</span><span style="margin-left: 30px;font-size: 18px;color: red;">'+data[i].insurance+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">加油卡余额</span><span style="margin-left: 50px;font-size: 18px;color: #06d606;">'+data[i].balance+'元</span>'
										+'</div>'
									+'</li>';
									$("#state").append(html);
							}else if(yearcheck<currentdate && insurance<currentdate && data[i].balance<=300){
								html='<li style="width: 25%;">'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 25px; color:#22847d;">'+data[i].carnumber+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">年检到期日期</span><span style="margin-left: 30px;font-size: 18px;color: red;">'+data[i].yearcheck+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">保险到期日期</span><span style="margin-left: 30px;font-size: 18px;color: red;">'+data[i].insurance+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">加油卡余额</span><span style="margin-left: 50px;font-size: 18px;color: red;">'+data[i].balance+'元</span>'
										+'</div>'
									+'</li>';
									$("#state").append(html);
							}else if(yearcheck>currentdate && insurance>currentdate && data[i].balance<=300){
								html='<li style="width: 25%;">'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 25px; color:#22847d;">'+data[i].carnumber+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">年检到期日期</span><span style="margin-left: 30px;font-size: 18px;color: #06d606;">'+data[i].yearcheck+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">保险到期日期</span><span style="margin-left: 30px;font-size: 18px;color: #06d606;">'+data[i].insurance+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">加油卡余额</span><span style="margin-left: 50px;font-size: 18px;color: red;">'+data[i].balance+'元</span>'
										+'</div>'
									+'</li>';
									$("#state").append(html);
							}else if(yearcheck<currentdate && insurance>currentdate && data[i].balance<=300){
								html='<li style="width: 25%;">'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 25px; color:#22847d;">'+data[i].carnumber+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">年检到期日期</span><span style="margin-left: 30px;font-size: 18px;color: red;">'+data[i].yearcheck+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">保险到期日期</span><span style="margin-left: 30px;font-size: 18px;color: #06d606;">'+data[i].insurance+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">加油卡余额</span><span style="margin-left: 50px;font-size: 18px;color: red;">'+data[i].balance+'元</span>'
										+'</div>'
									+'</li>';
									$("#state").append(html);
							}else if(yearcheck>currentdate && insurance<currentdate && data[i].balance<=300){
								html='<li style="width: 25%;">'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 25px; color:#22847d;">'+data[i].carnumber+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">年检到期日期</span><span style="margin-left: 30px;font-size: 18px;color: #06d606;">'+data[i].yearcheck+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">保险到期日期</span><span style="margin-left: 30px;font-size: 18px;color: red;">'+data[i].insurance+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">加油卡余额</span><span style="margin-left: 50px;font-size: 18px;color: red;">'+data[i].balance+'元</span>'
										+'</div>'
									+'</li>';
									$("#state").append(html);
							}else if(yearcheck>currentdate && insurance<currentdate && data[i].balance>300){
								html='<li style="width: 25%;">'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 25px; color:#22847d;">'+data[i].carnumber+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">年检到期日期</span><span style="margin-left: 30px;font-size: 18px;color: #06d606;">'+data[i].yearcheck+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">保险到期日期</span><span style="margin-left: 30px;font-size: 18px;color: red;">'+data[i].insurance+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">加油卡余额</span><span style="margin-left: 50px;font-size: 18px;color: #06d606;">'+data[i].balance+'元</span>'
										+'</div>'
									+'</li>';
									$("#state").append(html);
							}else{
								html='<li style="width: 25%;">'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 25px; color:#22847d;">'+data[i].carnumber+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">年检到期日期</span><span style="margin-left: 30px;font-size: 18px;color: #06d606;">'+data[i].yearcheck+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">保险到期日期</span><span style="margin-left: 30px;font-size: 18px;color: #06d606;">'+data[i].insurance+'</span>'
										+'</div>'
										+'<div style="text-align: left; height:35px;">'
											+'<span style="font-size: 18px;">加油卡余额</span><span style="margin-left: 50px;font-size: 18px;color: #06d606;">'+data[i].balance+'元</span>'
										+'</div>'
									+'</li>';
									$("#state").append(html);
							}
                    }
				}
		});
			
		/* 获取未维修车辆 */
		$.ajax({
				url: 'cc_findAllIsNull.action',
				data:{},
				type:"post",
			    dataType:"json",   
				success: function(data) {
					var html="";
					for(var i=0;i<data.length;i++){
					       html='<li style="">'
								+'<span style="font-size: 22px;color:#98c3c0">'+data[i].carnumber+'</span>'
								+'<span name="kk" style="font-size: 16px;color: red;">[状况：'+data[i].errorbrief+']</span>'
								+'</li>';
		        			$("#notcheck").append(html);
                    }
				}
		});
	});
	  function getPreMonth(date) {
            var arr = date.split('-');
            var year = arr[0]; //获取当前日期的年份
            var month = arr[1]; //获取当前日期的月份
            var day = arr[2]; //获取当前日期的日
            var days = new Date(year, month, 0);
            days = days.getDate(); //获取当前日期中月的天数
            var year2 = year;
            var month2 = parseInt(month) - 1;
            if (month2 == 0) {
                year2 = parseInt(year2) - 1;
                month2 = 12;
            }
            var day2 = day;
            var days2 = new Date(year2, month2, 0);
            days2 = days2.getDate();
            if (day2 > days2) {
                day2 = days2;
            }
            if (month2 < 10) {
                month2 = '0' + month2;
            }
            var t2 = year2 + '-' + month2 + '-' + day2;
            return t2;
        }
	</script>
	
<body >

	<div style="width: 100%;">
		<table style="width: 100%;">
			<tr>
				<td style="height: 50px; font-size: 25px; text-align: left;">
					<div>
						<img style="width: 60px; height:35px; margin-left: 50px;" src="./resource/image/qiche.jpg">
						<span style="margin-left: 30px; color:#6db4de;">公司车辆状态</span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="border-bottom: solid 1px #c0cdd8;">
					<ul id="state">
						
					</ul>
				</td>
			</tr>
			<tr>
				<td style="border-bottom: solid 1px #c0cdd8;">
					
				</td>
			</tr>
			<tr>
				<td style="height: 50px; font-size: 25px; text-align: left;">
					<img style="width: 60px; height:35px; margin-left: 50px;" src="./resource/image/gps.png">
					<span style="margin-left: 30px; color:#6db4de;">车辆GPS报修</span>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="notcheck">
						
					</ul>
				</td>
			</tr>
		</table>
	</div>

</body>
</html>