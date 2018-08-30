<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%> 
<jsp:include page="Inc.jsp"></jsp:include>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>综合业务管理系统管理</title>
    <link rel= "shortcut icon " href= "./resource/img/ljlogo.png">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">	
<style type="text/css">
		.apps{
		background: url("./resource/image/bg_header.jpg") repeat-x;
		}
		.main-footer{
		background: url("./resource/image/bg_footer.jpg") repeat-x;
		}
  	   .tree-title {
	    font-size: 15px;
	    display: inline-block;
	    text-decoration: none;
	    vertical-align: top;
	    white-space: nowrap;
	    /* padding: 0 2px; */
	    height: 15px;
	    /* line-height: 15px; */
	}
	ul li{
		margin-top: 8px;
	}
 </style>
	
  </head>
  
  <body class="easyui-layout">   
  <!-- begin of header-->
    <div data-options="region:'north',split:true" style="overflow:hidden ;height:100%;background-color: #87c8e0;" class="apps">
		<div style="float: left;margin-top:1%;margin-left:2%;"><img style="width:75px;height: 55px;" src="./resource/image/JRlogo.png" /></div>
        <div style="float: left;padding-left: 8px;margin-top:1.7%;color: #FFFFFF;font-weight: bold;font-size: 35px;">综合业务管理系统</div>
    	<span style="float: right;padding-right: 20px;color:#FFFFFF;font-size: 22px;">
    	    <br><span>
    			<img style="width:32px;height: 30px;" src="./resource/image/user-info.png" /><div style="float: right;margin-top:1.5%;">${login_users.rname} [${login_users.lname}]</div><br>   
    		</span>
    		<span style="float: right;">
    		    <a href="#" id="tBox" title="这是一个提示信息" style="font-size:20px;"><button id="btn_msg">系统消息</button></a>
    			<a href="user_logout.action" style="text-decoration: none;color:yellow;font-size: 14px;">[安全退出]</a>
    	    </span>
    	</span>	
    </div>
    <!-- end of header -->  
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:230px;font-weight: bold;color:#000000;">
    	<div id="menu" class="easyui-accordion" data-options="animate:true,plain:true">
    	 <ul id="cart_tree"  class="easyui-tree" data-options="lines:true"></ul>
    	</div>
    </div>   
    <div data-options="region:'center',title:'主界面'" style="padding:5px;background:#eee;">
    	<div id="cartab" class="easyui-tabs" style="width:500px;height:250px;" data-options="fit:true">   
		    <div title="首页"> 
		        <jsp:include page="main2.jsp"></jsp:include>     
		    </div>   
		</div> 
    </div> 
    
    <div data-options="region:'south'" class="main-footer">
    	<div id="clock"  style="height:30px;font-size: 14px;font-weight:bold;text-align: center;color: yellow;"> </div> 
        <div style="text-align: center;font-size: 10px;color: #FFFFFF;padding-bottom: 5px;">© 2018  技术支持@金瑞科技</div>
    </div>
     
</body> 
</html>

	<script type="text/javascript">
		$(function(){	
			var height1 = $(window).height()-20;  
			$("#main_layout").attr("style","width:100%;height:"+height1+"px");  
			$("#main_layout").layout("resize",{ width:"100%", height:height1+"px" });  
			$(window).resize(function(){
			     var height1 = $(window).height()-30;  
			     $("#main_layout").attr("style","width:100%;height:"+height1+"px");  
			     $("#main_layout").layout("resize",{   width:"100%",   height:height1+"px"   });  
			});
		
			/* 验证session开始 */
			var curPath = window.document.location.href;
		 	var pathName = window.document.location.pathname;
		 	var	basePath = curPath.substring(0,curPath.indexOf(pathName)) + "/";
		 	var s="<%=session.getAttribute("login_users")%>"; 
		 	if(s=="null"){ 
		 		top.location.href = location.href=basePath+"JRmanage";
		 	}
		 	
		 	$('#cart_tree').tree({
            url : 'tree_loadTree.action',
            idFiled:"id",
			textFiled:"text",
			parentField:"pid",
			attributes:"attributes",
            onClick : function(node) {
  				if(node.state=='open'){
					$('#cart_tree').tree('collapse',node.target);
				}else if(node.state=='closed'){
					$('#cart_tree').tree('expand',node.target);
				}else{
					//在主页面打开对应的URL地址
					opentab(node);
				}
            }
        });       
        
        //初始化系统提示框，加载提示框数据  2018-08-24 by 李金
	 	$.ajax({
			url: 'persionfile_findEndDate.action',
			data:{},
			type:"post",
			dataType:"json",   
			success: function(data) {
				$("#tBox").tooltip({
			 		content: "当前有"+data.length+"条系统消息！"
				}); 
			}
		}); 	  	 
	 	$('#btn_msg').on('click', function () {
		 	$.ajax({
			url: 'persionfile_findEndDate.action',
			data:{},
			type:"post",
			dataType:"json",   
			success: function(data) {
				var content = '';
				for(var i=0;i<data.length;i++){
					content +="["+(i+1)+"]合同到期人员："+data[i].name+",到期日期："+data[i].enddate+",请尽快处理！<br>";
				}				
				$.messager.alert('系统消息',content); 
			}
			});
		 });
        
        
	});
		
		function opentab(node){
			//判断当前打开的窗口中是否存在点击的菜单
			var isExist=$('#cartab').tabs('exists',node.text);
			if(isExist){
				$('#cartab').tabs('select',node.text);
			}else{
				//如果节点不存在提示系统错误
				if(node.attributes.url==null){
					$.messager.show({title:'系统消息',msg:'节点错误，请联系管理员',timeout:5000 });
					return ;
				};
				
				//打开选项卡
				$('#cartab').tabs('add',{    
				    title:node.text,    
				  	content:"<iframe src=forward_url?url="+node.attributes.url+" frameboder=0 style='width:100%;height:100%;border:0;'></iframe>",
				    closable:true,    
				    tools:[{    
				        iconCls:'icon-mini-refresh',    
				        handler:function(){  
	   						var tab = $('#cartab').tabs('getSelected');
							$('#cartab').tabs('update', {
								tab: tab,
								options:tab.panel('options')
							});
				        }    
				    }]    
				});
			}
		}
	</script>
<script language="javascript"> 
function realSysTime(clock){ 
var now=new Date(); //创建Date对象 
var year=now.getFullYear(); //获取年份 
var month=now.getMonth(); //获取月份 
var date=now.getDate(); //获取日期 
var day=now.getDay(); //获取星期 
var hour=now.getHours(); //获取小时 
var minu=now.getMinutes(); //获取分钟 
var sec=now.getSeconds(); //获取秒钟 
month=month+1; 
var arr_week=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六"); 
var week=arr_week[day]; //获取中文的星期 
var time="系统当前时间："+year+"年"+month+"月"+date+"日 "+week+"  "+hour+":"+minu+":"+sec; //组合系统时间 
clock.innerHTML=time; //显示系统时间 
} 
window.onload=function(){ 
window.setInterval("realSysTime(clock)",1000); //实时获取并显示系统时间 
} 
</script> 
	