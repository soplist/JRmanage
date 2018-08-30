
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    	<base href="<%=basePath%>">
  		<title>综合业务管理系统系统后台登陆</title>
		<title>登陆</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<link rel= "shortcut icon " href= "./resource/img/ljlogo.png">
		<link rel="stylesheet" type="text/css" href="./resource/css/bootstrap.css"/>
		<script src="./resource/js/jquery.js"></script>
		<script src="./resource/js/bootstrap.js"></script>

<style>
        * { margin: 0; padding: 0; }
        html { height: 100%; }
        body { height: 100%; background: #fff url(./resource/image/img_login/loginbg1.jpg); background-size: 100% 100%;}
        .dowebok { position: absolute; left: 50%; top: 65%; width: 380px; height: 380px; margin: -300px 0 0 -215px; border: 2px solid #fff; border-radius: 20px;}
         .logo { width: 75px; height: 75px; background: url(./resource/image/img_login/login.png); background-size: 100% 100%;}
        .form-item { position: relative; width: 360px; margin-left:10%; padding-bottom: 30px;}
        .form-item input { width: 300px; height: 40px; padding-left: 105px; border: 1px solid #fff; border-radius: 25px; font-size: 18px; color: #fff; background-color: transparent; outline: none;}
        .form-item button { width: 300px; height: 45px; border: 0; border-radius: 25px; font-size: 24px; color: #1f6f4a; outline: none; cursor: pointer; background-color: #fff; }
        #username { background: url(./resource/image/img_login/emil.png) 20px 14px no-repeat; }
        #password { background: url(./resource/image/img_login/password.png) 23px 11px no-repeat; }
        .tip { display: none; position: absolute; left: 20px; top: 52px; font-size: 14px; color: #f50; }
        .reg-bar { width: 360px; margin: 20px auto 0; font-size: 14px; overflow: hidden;}
        .reg-bar a { color: #fff; text-decoration: none; }
        .reg-bar a:hover { text-decoration: underline; }
        .reg-bar .reg { float: left; }
        .reg-bar .forget { float: right; }
        .dowebok ::-webkit-input-placeholder { font-size: 18px; line-height: 1.4; color: #fff;}
        .dowebok :-moz-placeholder { font-size: 18px; line-height: 1.4; color: #fff;}
        .dowebok ::-moz-placeholder { font-size: 18px; line-height: 1.4; color: #fff;}
        .dowebok :-ms-input-placeholder { font-size: 18px; line-height: 1.4; color: #fff;}

        @media screen and (max-width: 500px) {
            * { box-sizing: border-box; }
            .dowebok { position: static; width: auto; height: auto; margin: 0 30px; border: 0; border-radius: 0; }
            .logo { margin: 50px auto; }
            .form-item { width: auto; }
            .form-item input, .form-item button, .reg-bar { width: 100%; }
        }
</style>

</head>

<body>
<div style="width: 100%;height:60px;text-align: center; margin-top: 70px;font-weight: bold;">
	<span style="font-size: 45px;display: inline-block;line-height: 45px;color: #FFFFFF;font-family:KaiTi;">综&nbsp;合&nbsp;业&nbsp;务&nbsp;管&nbsp;理&nbsp;系&nbsp;统</span>
</div>
<div class="dowebok">
 		<div class="logo" style="margin: 0 auto; margin-top:35px;margin-bottom:45px;"></div>      
        <div class="form-item">
            <input placeholder="用  户  名" id="lname" name="lpass"  type="text" autocomplete="off">
            <p class="tip">请输入用户名</p>
        </div>
        <div class="form-item">
            <input placeholder="登录密码" id="lpass" name="lpass" type="password" autocomplete="off">
            <p class="tip">请输入密码</p>
        </div>  
        <div class="form-item"><button id="submit">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 录</button></div>
        <div id="div-message" style="color: red;display: inline;margin: 2px 0 0 2px;"></div>        
</div>
<p class="remind" style="text-align: center; color:#FFFFFF; position:absolute; bottom:0px;padding-left: 40%;">
	为了保障您更好的用户体验<br>推荐您使用 <span style="color:yellow;">Chrome，&nbsp;IE10+&nbsp;</span>浏览器来访问本系统
</p>
</body>


<script type="text/javascript">

		$(document).ready(function(){
		 $('#submit').on('click', function () {
		 	btn_login();
		 });				
		
		$("#lname").focus();
			var istrue=true;
		})
		  function btn_login(){
		    var messageDiv = $('#div-message');
		    if (!$('#lname').val()||$('#lname').val().trim()=="") {
		      $('#lname').focus();
		      messageDiv.text('请输入用户名');
		    	istrue=false;
		    	return false;
		    }else{
		    	istrue=true;
		    }
		    if (!$('#lpass').val()||$('#lpass').val().trim()=="") {
		      $('#lpass').focus();
		      messageDiv.text('请输入密码');
		      istrue=false;
			return false;
		    }else{
		    	istrue=true;
		    }
			messageDiv.text('');
    		if(istrue){
				$.ajax({
					url:"user_login.action",
					data:{
						lname:$('#lname').val(),
						lpass:$('#lpass').val()
					},
					dataType:"json",
					type:"post",
					success:function(res){
						 	if(res){						
						 		window.location.href="forward_url.action?url=main.jsp";	 
							}else{
								window.location.href="forward_url.action?url=login.jsp";
							}
						
					}
				});
    		}
		  }
		  
		$(document).keyup(function(event){
		    if(event.keyCode ==13){
		      btn_login();
		    } 
		});
</script>
</html>
