<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage=""%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>派速捷云平台</title>

<jsp:include page="/head.jsp" flush="true"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.plug/jsencrypt.js"></script>
<style type="text/css" >
body {
	width: 100%;
	height: 100%;
}

* {
	box-sizing: border-box;
} 

.login-container {
	width: 100%;
	height: 100%;
}

.login-container .backgroundImg {
	position: absolute;
	z-index: -1;
	display: block;
	max-width: 100%;
	width: 100%;
	height: 100%;
	background: url('./images/ulwxbase.sys/backgroundImg.jpg') no-repeat center;
	background-size: 100%;
}

.login-container .login-modal {
	width: 762px;
	height: 370px;
	position: absolute;
	z-index: 99;
	border: 1px solid transparent;
	top: 0;
	bottom: 0;
	right: 0;
	left: 0;
	margin: auto;
}

.login-container .login-modal .left-part {
	background: rgba(0, 0, 0, 0.7);
	width: 380px;
	height: 100%;
	border-top-left-radius: 10px;
	border-bottom-left-radius: 10px;
	float: left;
	position: relative;
}

.login-container .login-modal .left-part .logo {
	top: 50%;
    right: 0;
    left: 0;
    margin: auto;
    position: absolute;
    background: url(./images/ulwxbase.sys/login_content_bg.png?v=20180124) no-repeat center;
    width: 236px;
    height: 245px;
    margin-top: -122.5px;
}

.login-container .login-modal .left-part .logo-name {
	width: 100%;
	height: 60px;
	line-height: 60px;
	text-align: center;
	top: 225px;
	bottom: 0;
	right: 0;
	left: 0;
	margin: auto;
	position: absolute;
	color: #fff;
	font-size: 46px;
	font-weight: bold;
	letter-spacing: 8px;
	padding-left: 16px;
}

.login-container .login-modal .right-part {
	position: relative;
	float: left;
	background: rgba(0, 0, 0, 0.5);
	width: 380px;
	height: 100%;
	-webkit-border-bottom-right-radius: 10px;
	-webkit-border-top-right-radius: 10px;
	-moz-border-bottom-right-radius: 10px;
	-moz-border-top-right-radius: 10px;
	border-bottom-right-radius: 10px;
	border-top-right-radius: 10px;
}

.login-container .login-modal .right-part .loginBox {
	margin: 10px auto 0;
	width: 300px;
}

.login-container .login-modal .right-part .loginBox .errTips {
	text-align: left;
	height: 20px;
	line-height: 20px;
	font-size: 16px;
	color: #D70903;
	margin-bottom: 10px;
	min-height: 20px;
}

.login-container .login-modal .right-part .loginBox  i {
	content: '';
	display: block;
	position: absolute;
	width: 28px;
	height: 28px;
	top: 10px;
	left: 10px;
	background: url('./images/ulwxbase.sys/icon1.png') no-repeat;
}

.login-container .login-modal .right-part .loginBox .login-setting .forgetPassword
	{
	float: right;
	height: 100%;
	display: block;
	color: #fff;
	cursor: pointer;
}

.login-button:hover {
	background: #4db3ff;
	border-color: #4db3ff;
	color: #fff;
}

table  td{
 text-align:left;
 vertical-align:bottom;
}

#verifyImg {
	width:73px;
	height:36px;
	display:inline-block;
	position:relative;
	left:2px;
	top:14px;
	cursor: pointer;
}

.textbox .textbox-text{
  font-size:18px;
}
.btn_class .l-btn-text{
  font-size:22px;
}
.l-btn-text{
	margin: 0 auto !important;
}
.easyui-linkbutton.l-btn-disabled:hover{
	border: 1px solid #5e6e80!important;
}
</style>
<script type="text/javascript">
	if (window.parent != window.self) {
		window.parent.location = window.self.location;
	}
	window.onload = function()
    {
       document.getElementById("username").focus();
       	$("#username").textbox("setValue","");
		$("#password").passwordbox("setValue","");
		
    }
	$(document).ready(function() {
		$("body").css("visibility" ,"visible");
		// var userInfo={
		// 	userName:"",
		// 	password:""
		// }
		var userName
		var userInfoCookies=  Cookies.get('user-info');
		if(!isEmpty(userInfoCookies)){
			var userInfo = $.evalJSON(userInfoCookies);
			$("#username").textbox("setValue",userInfo.userName);
			$("#password").passwordbox("setValue",userInfo.password);
		}else{
			$("#username").textbox("setValue","");
			$("#password").passwordbox("setValue","");
		}
		resetImageCode('verifyImg');//初始化验证码
	});
	
	/**重设图像验证码*/
	function resetImageCode(verifyImg){		
	    var rnd = Math.random();
	    document.getElementById(verifyImg).src='validcode?sds='+rnd;
	    //清空验证码框，并获取焦点
	    $('#valcode').textbox("setValue","");
	    $('#valcode').focus();
	}

	
	function login(){
		var username=jQuery.trim( $("#username").textbox("getValue") );
		var pass=jQuery.trim( $("#password").passwordbox("getValue") );
		var valcode = jQuery.trim( $('#valcode').textbox("getValue"));
		var smscode=jQuery.trim($("#smscode").textbox("getValue"));
		if(username==''){
			$.messager.alert("提示","请输入帐号！");
			document.getElementById("username").focus();
			return;
		}
		if(pass==''){
			$.messager.alert("提示","请输入密码！");
			document.getElementById("password").focus();
			return;
		}
		if(valcode==''){
			$.messager.alert("提示","请输入验证码！");
			document.getElementById("valcode").focus();
			resetImageCode('verifyImg');//初始化验证码
			return;
		}
		
		if(!smscode){
			$.messager.alert("提示","请输入短信验证码！");
			document.getElementById("smscode").focus();
			//resetImageCode('verifyImg');//初始化验证码
			return;
		}
		$("#btn").linkbutton("disable");
		var encrypt = new JSEncrypt();
	    encrypt.setPublicKey(getRsaPublicKey());
		var orginPass=pass;
	    pass=encrypt.encrypt(pass);

		$.ajax({ 
			url:'<%=request.getContextPath()%>/sys/sys_Login_loginJSON.action',
			type:'post',
			dataType:'JSON',
			data: {
				"username": username , 
				"password": pass,
				"valcode" : valcode,
				"smscode" : smscode
			},
			success: function(data){
			   	//console.log(data);
			   	$("#btn").linkbutton("enable");
				if(data.status==1){
					var userInfo={
						userName:username,
						password:orginPass
					}
					Cookies.set('user-info', $.toJSON(userInfo), { path: '/',expires: 365  });

				   $("#username").textbox("setValue","");
				   $("#password").passwordbox("setValue","");
				   $("#valcode").textbox("setValue","");
				   $("#smscode").textbox("setValue","");
				   window.location.href=data.data;
			   	}
			   else{
				   $("#valcode").textbox("setValue","");
				   $.messager.alert("提示",data.message,"info");
				   resetImageCode('verifyImg');//初始化验证码
				   return false
			   }
			},
			error:function(err){

				$("#btn").linkbutton("enable");
				$("#valcode").textbox("setValue","");
				resetImageCode('verifyImg');//初始化验证码
				$.messager.alert("提示",err.message);
				return false
			}
		})
		
		
		
	}

	function getSmsCode(){
		var params=new Object();
		if($("#username").textbox("getValue") && $("#valcode").textbox("getValue")&& $("#password").passwordbox("getValue")){
		    params.username=$("#username").textbox("getValue");
		    params.valcode=$("#valcode").textbox("getValue");
		    var sendUrl=$.ContexPath+"/sys/sys_Login_getMobileSmsJSON.action";
		    $('#smsId').linkbutton('disable');
		    asynPostParam(sendUrl,params,function(data){
		    	data=$.parseJSON(data);
		    	if(data.status==1){
		    		sendSms();
		    	}else{
		    		$.messager.alert("提示",data.message);
		    		$('#smsId').linkbutton('enable');
		    		return;
		    	}
		    });
		    
		}else{
			if(!$("#username").textbox("getValue")){
				$.messager.alert("提示","请输入帐号！");
				document.getElementById("username").focus();
				return;
			}
			
			if(!$("#valcode").textbox("getValue")){
				$.messager.alert("提示","请输入图形验证码");
				document.getElementById("valcode").focus();
				return;
			}
			
			if(!$("#password").passwordbox("getValue")){
				$.messager.alert("提示","请输入密码");
				document.getElementById("password").focus();
				return;
			}
		}
	}
	
	
	var ints = 60;
	var app;

	function sendSms() {
	    if (ints != 0) {
	        clearInterval(app);
	        app = self.setInterval("time($('#smsId .l-btn-text'))", 1000);
	    } else {
	        app = self.setInterval("time($('#smsId .l-btn-text'))", 1000);
	    }

	}


	// 定时获取验证码
	function time(obj) {
	    // console.log(int);
	    if (ints == 0) {
	        clearInterval(app);
	        obj.html("发送短信");
	        obj.attr('href', 'javascript:sendSms();');
	        $('#smsId').linkbutton('enable');
	        ints = 60;
	    } else {

	     $('#smsId').linkbutton('disable');
	        obj.html(ints + '秒后重发');
	        ints--;
	    }
	}
	
    function clearUserName(){
    	$('#username').textbox("setValue","");
    }
    
    /*忘记密码内容*/
    function forgetPassword(){
    	
    }
    
    $(function(){
    	$("body").bind("keypress",function(event){
    		if(event.keyCode==13){
				 login();
    		}
    	});
    });
</script>
</head>
<body >
	<div class="login-container">
		<div class="backgroundImg"></div>
		<div class="login-modal">
			<div class="left-part">
				<div class="logo"></div>
			</div>
			<div class="right-part">
				<div class="loginBox" style="margin: -20px auto 0;">
					<p class="errTips"></p>
					<form name="loginForm" id="loginForm">
					 <table style="width:240px">
						 	
					    <tr style="height:50px">
					      <td >
					      <input class="easyui-textbox" id="username" name="username" 
					       style="width:100%;height:38px;"
					        data-options="prompt:'账户',iconCls:'icon-man',iconWidth:30,iconAlign:'left'"/>
					      </td>
					    </tr>
					    <tr style="height:50px">
					    <td>
					     <input class="easyui-passwordbox" id="password" 
					        name="password" style="width:100%;height:38px;"
					          data-options="prompt:'密码',iconCls:'icon-lock',
					          showEye:false,iconWidth:30,iconAlign:'left',lastDelay:0,checkInterval:0" />
					    </td>
					    </tr>
					 	 <tr style="height:50px">
					    <td>
					    <input class="easyui-textbox" id="valcode" 
					     name="valcode" style="width:170px;height:38px;padding-left:10px;"
					      data-options="prompt:'验证码'" />
					     
					      <img  id="verifyImg" onclick="this.src=this.src+'?'+Math.random();">
					    
					    </td>
					    </tr>
					    
					    <tr style="height:50px">
					    <td>
							<div class="verification-input">
								<input class="easyui-textbox"  name="smscode" id="smscode"
									style="width:170px;height:38px;padding-left:10px;"
									placeholder="短信验证码" type="text">
								<a id="smsId" href="#" class="easyui-linkbutton" style="height:38px;margin:3px;width:73px"
								onclick="javascript:getSmsCode();">发送短信</a>
		
							</div>
							
					    </td>
					    
					    </tr>
					    <tr style="height:26px">
					     <td>
					     <div class="login-setting">
							<span onclick="forgetPassword()" class="forgetPassword">忘记密码？</span>
						</div>
					     </td>
					    </tr>
					     <tr style="height:50px" class="btn_class">
					     <td >
						<a id="btn" href="#" class="easyui-linkbutton" style="height:38px;margin:3px;width:100%;font-size:20px"
								onclick="javascript:login()">登&nbsp;&nbsp;录</a>
					     </td>
					    </tr>
					 </table>

					</form>
				</div>

			</div>
		</div>
	</div>

</body>
</html>
