<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../Inc.jsp"></jsp:include>
 
    <style>
        html,body {
            height : 100%;
        }
        .box {
        	/* background-color:rgba(224,236,255,0.5); */
            background-repeat: no-repeat;
            background-size:100% 100%;
            margin: 0 auto;
            position: relative;
            width: 100%;
            height: 100%;
        }
        .login-box {
            width: 100%;
            max-width:400px;
            height: 400px;
            position: absolute;
            top: 30%;

            margin-top: -200px;
            /*设置负值，为要定位子盒子的一半高度*/

        }
        @media screen and (min-width:500px){
            .login-box {
                left: 50%;
                /*设置负值，为要定位子盒子的一半宽度*/
                margin-left: -510px;
            }
        }

        .form {
            width: 100%;
            max-width:500px;
            height: 360px;
            margin: 25px auto 0px auto;
            padding-top: 25px;
        }
        .login-content {
            height: 320px;
            width: 100%;
            max-width:500px;
            background-color: rgba(255, 250, 2550, .6);
            float: left;
            background-color:rgba(224,236,255,0.5);
        }


        .input-group {
            margin: 0px 0px 30px 0px !important;
        }
        .form-control,
        .input-group {
            height: 40px;
        }

        .form-group {
            margin-bottom: 0px !important;
        }
        .login-title {
            padding: 20px 10px;
            background-color: rgba(0, 0, 0, .6);
        }
        .login-title h1 {
            margin-top: 10px !important;
        }
        .login-title small {
            color: #fff;
        }

        .link p {
            line-height: 20px;
            margin-top: 30px;
        }
        .btn-sm {
            padding: 8px 24px !important;
            font-size: 16px !important;
        }
        .text-white{
            color: white;
            
        }
        .input-group-addon {
		    padding: 6px 12px;
		    font-size: 14px;
		    font-weight: normal;
		    line-height: 1;
		    color: #555;
		    text-align: center;
		    background-color: #eee;
		    border: 1px solid #ccc;
		    border-radius: 4px;
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
		 		top.location.href = location.href=basePath+"jfinfo";
		 	}
			/* 验证session开始 */
		$('#opass').on('blur',function(){
			//调用validate方法并且返回验证结果，true或者false
			var b=$('#opass').validatebox('isValid');			
			if(!b){return;}
			//获取密码
			var opass=$(this).val();
			$.post('user_checkpass',{opass:opass},function(data){
				
				if(!data){						
					$.messager.alert('提示','原始密码不正确','warning');
					$('#opass').val('');//如果不写，将会卡死在页面
					$('#opass').focus();//如果不写，警告图标和提示信息将会消失
				}
			},'json');
		});
		//检查密码和确认密码是否相同
		$.extend($.fn.validatebox.defaults.rules, {    
		    equals: {    
		    	//value 为需要校验的输入框的值 , param为使用此规则时存入的参数   
		        validator: function(value,param){    
		            return value == $(param[0]).val();    
		        },    
		        message: '新密码和确认密码不相同'   
		    }    
		});
		
		$('#qbutton').on('click',function(){
			
			$.post('user_changePass.action',{"pwd":$('#pwd').val(),"rpwd":$('#rpwd').val()},function(data){
				if($('#pwd').val()){
					if(data.success){
						//$.messager.alert('提示','修改成功，将跳至登陆界面','warning');
						//alert('修改成功，将跳至登陆界面');
						window.parent.location.href='user_logout.action';	
					}else{
						$.messager.alert('提示','修改失败','warning');
					}
				}
			},'json');
		});
	});


</script>


<body>
<br>
<div style="height: 20%;border-bottom:0px solid #000;">
<img alt="" src="./resource/image/tishi.png" style="width: 23px;">&nbsp;&nbsp;提示：<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请牢记密码，如果忘记密码，请与管理员联系！<br><br><hr><br>
</div>
<div class="box" style="height: 80%;">
    <br/>
    <!-- <div class="login-box">
        <div class="login-content ">
            <div class="form" style="text-align: center;"> -->
                 <form id="login_form" action="" method="post" style="margin-left: 20px;">
                    <div class="form-group">
                        <div class="col-xs-12  ">
                            <div class="input-group">
                                <span class="input-group-addon">原始密码</span>
                                <input style="width: 20%;height: 30px;" id="opass" type="password" class="easyui-validatebox" required="required" placeholder="原始密码"/>
                            </div>
                        </div>
                    </div> 
                    <div class="form-group">
                        <div class="col-xs-12  ">
                            <div class="input-group">
                                <span class="input-group-addon">新&nbsp;密&nbsp;码</span>
                                <input style="width: 20%;height: 30px;" id="pwd" name="lpass" type="password" class="easyui-validatebox" required="required" placeholder="新密码"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12  ">
                            <div class="input-group">
                                <span class="input-group-addon">确认密码</span></span>
                                <input style="width: 20%;height: 30px;" id="rpwd" validType="equals['#pwd']"  type="password" class="easyui-validatebox" required="required" placeholder="确认密码">
                            </div>
                           	
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-xs-4 col-xs-offset-4 " style="margin: 5 38%;margin-left: 10%;">
                        	<input style="width:60px;height: 35px;" type="button" id="qbutton" value="确认"/>
 							<input style="width:60px;height: 35px; margin-left: 5px;" type="reset" value="清空"/>
                        </div>
                    </div>
                </form>
            </div>
  <!--       </div>
    </div>
</div> -->
<script type="text/javascript">
	 $(function(){
	 	var password_ok = $("#password_ok").val();
	 	var rname = $("#rname").val();
	 	var legal = $("#legal").val();
	 	var address = $("#address").val();
	 	var phone = $("#phone").val();
	 	
		$("#login_form").submit(function(){
			return false;
		});	
		
		var regu = "^[ ]+$";
		var re = new RegExp(regu);
		$("#password_ok").blur(function(){
			  password_ok = $("#password_ok").val();
			if(password_ok!=$("#password").val()){
				$("#warm").css("display","block");
				$("#submit").attr("disabled","true");
			}else{
				$("#warm").css("display","none");
				$("#submit").removeAttr("disabled","false");
			}
		});
	
		
		$("#rname").blur(function(){
		  rname = $("#rname").val();
			if ((re.test(rname) || rname.length==0)){
				$("#conpany_name").css("display","block");
				$("#submit").attr("disabled","true");
			}else{
				$("#conpany_name").css("display","none");
				$("#submit").removeAttr("disabled","false");
			}
		});
	
	$("#submit").click(function(){
		$.post("user_firstUpd.action",{lpass:password_ok,rname:rname,legal:legal,address:address,phone:phone},function(res){
				if(res){
					//alert("success");
					window.location.href="forward_url.action?url=main.jsp";
				}else{
					//alert("fail");
				}
			});
	});
}); 

</script>


</body>