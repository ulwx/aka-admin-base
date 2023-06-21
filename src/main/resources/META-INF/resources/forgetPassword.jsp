<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>忘记密码</title>
<jsp:include page="/head.jsp" flush="true"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.plug/jsencrypt.js"></script>
<style type="text/css">
.container {
	width: 100%;
	max-width: 1920px;
	height: 100%;
	display: block;
	position: relative;
}

.container .getPassWord-header {
	background: #157ed1;
	line-height: 40px;
	height: 40px;
	color: #fff;
}

.container .getPassWord-header .content {
	width: 1000px;
	height: 100%;
	display: block;
	margin: 0 auto;
	padding: 0 20px;
}

.container .getPassWord-header .content .logo {
	display: block;
	float:left;
	width: 28px;
	height: 28px;
	margin:8px 10px 0 0;
	background:url('images/common/favico.ico.png') no-repeat;
	background-size: 100%;
}

.container .getPassWord-header .content .PN {
	display:block;
	float:left;
	height:100%;
	width:80px;
	text-align:center;
	font-size: 18px;
	letter-spacing:2px;
	font-family: "微软雅黑";
}

.container .getPassWord-header .content .login {
	text-align: center;
	width: 60px;
	height: 100%;
	color: #fff;
	font-size: 16px;
	float: right;
	cursor: pointer;
}

.container .main {
	background: url('./images/ulwxbase.sys/bg.png');
	width: 100%;
	height: 600px;
}

.container .main>.getMyPassword {
	width: 600px;
	height: 510px;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
	margin: auto;
	position: fixed;
	border: 1px solid #ddd;
	background: #fff;
	border-radius: 20px;
}

.container .main>.getMyPassword>.title {
	width: 100%;
	height: 50px;
	line-height: 50px;
	border-bottom: 1px solid #ddd;
	text-align: left;
	padding: 0 18px;
	font-family: "微软雅黑";
	font-size: 16px;
}

.container .main>.getMyPassword .steps {
	margin-top: 40px;
}

.container .main>.getMyPassword .steps .title {
	overflow: hidden;
	width: 580px;
	margin: 0 auto;
	height: 30px;
	line-height: 30px;
}

.container .main>.getMyPassword .steps .title>li {
	text-align: center;
	float: left;
	width: 33%;
}

.container .main>.getMyPassword .steps>img {
	display: block;
	height: 30px;
	width: 423px;
	margin: 0 auto;
}

.container .main>.getMyPassword .stepBox {
	margin: 62px auto 0;
	width: 300px;
	height: 130px;
	font-size: 16px;
	line-height: 40px;
}

.container .main>.getMyPassword .first-step>input {
	width: 100%;
	height: 40px;
	padding: 0 15px;
	display: block;
	border: 1px solid #ddd;
	border-radius: 4px;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
}

.container .main>.getMyPassword .second-step {
	margin-top: 50px;
}

.container .main>.getMyPassword .second-step .isReadySend {
	height: 30px;
	line-height: 30px;
	text-align: center;
}

.container .main>.getMyPassword .second-step>input {
	display: inline-block;
	width: 200px;
	height: 40px;
	border: 1px solid #ddd;
	margin-right: 12px;
	border-radius: 4px;
	padding: 0 15px;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
}

.container .main>.getMyPassword .second-step>.reGetSms {
	width: 80px;
	height: 40px;
	text-align: center;
	border: 1px solid #0e85e3;
	display: inline-block;
	background: #fff;
	color: #0e85e3;
	border-radius: 4px;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	cursor: pointer;
}
.container .main>.getMyPassword .second-step> .countDown{
	width: 80px;
	height: 40px;
	text-align: center;
	border: 1px solid #0e85e3;
	display: inline-block;
	background: #fff;
	color: #0e85e3;
	border-radius: 4px;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	cursor: pointer;
	display:none;
}

.container .main>.getMyPassword .last-step {
	margin-top: 40px;
	height: 200px;
}

.container .main>.getMyPassword .last-step>input {
	width: 100%;
	height: 40px;
	padding: 0 15px;
	display: block;
	border: 1px solid #ddd;
	border-radius: 4px;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
}

.tips {
	height: 40px;
	margin-bottom: 5px;
	text-align: left;
	padding: 0 15px;
}

.nextStep {
	color: #fff;
	display: block;
	position: relative;
	border-radius: 4px;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	background: #0d85e4;
	width: 300px;
	height: 40px;
	text-align: center;
	cursor: pointer;
}

.nextStep:hover {
	background: #5BA0E4;
}

*{
	box-sizing:border-box;
}
</style>
</head>
<body>
	<div class="container">
		<header class="getPassWord-header">
			<div class="content">
				<div class="logo"></div>
				<span class="PN">简易贷</span>
				<div class="login" onclick="toLogin()">登录</div>
			</div>
		</header>
		<div class="main">
			<div class="getMyPassword">
				<p class="title">找回密码</p>
				<div class="steps">
					<ul class="title">
						<li>身份确认</li>
						<li>身份验证</li>
						<li>修改密码</li>
					</ul>
					<img src="./images/ulwxbase.sys/step1.png" alt="">
				</div>
				<div class="first-step stepBox">
					<input placeholder="请输入您的登陆账号" type="text" name="account">
					<div class="tips">请输入登陆账号</div>
					<button class="nextStep" onclick="getSmsCode(1)">下一步</button>
				</div>
				<div class="second-step stepBox" style="display: none">
					<p class="isReadySend"></p>
					<input placeholder="请输入验证码" type="text" name="smsCode">
					<button id="reGetSms" class="reGetSms" onclick="getSmsCode(2)">重新获取</button>
					<div class="countDown"></div>
					<div class="tips">请输入验证码</div>
					<button class="nextStep" onclick="checkSmsCode()">下一步</button>
				</div>
				<div class="last-step stepBox"  style="display: none">
					<input class="easyui-passwordbox" data-options="required:true,showEye:false,validType:['password']" name="password" id="password" type="password" style="width:300px;height:40px;"  placeholder="请输入您的新密码" />
					<p class="tips">请输入您的新密码</p>
					<input class="easyui-passwordbox" data-options="required:true,showEye:false,validType:['confirmPass[password]']" type="password" name="conFirmpwd" id="conFirmpwd" style="width:300px;height:40px;" placeholder="请再次输入您的新密码" />
					<p class="tips">请再次输入您的新密码</p>
					<button class="nextStep" onclick="comfirmPassword()">下一步</button>
				</div>
			</div>
		</div>
	</div>
<script>
	function  checkAccount(){
		var params=new Object();
        var account = $('.first-step>input[name="account"]').val();
        params.account=account;
        var checkAccountUrl="<%=request.getContextPath()%>/sys/sys_Login_checkAccountJSON.action";
        asynPostParam(checkAccountUrl,params,function(data){
        	data=$.parseJSON(data);
        	if(data.status==1){
        		
        	}else{
        		$.messager.alert("提示",data.message);
        	}
        });
	}


	var time = 60;
    function getSmsCode(type) {
    	 var account = $('.first-step>input[name="account"]').val();
    	 if(!account){
    		 $.messager.alert("提示","登陆账号不能为空");
    	 }
    	 
        if(type == 2){
        	var obj = $("#reGetSms"); 
        	$('#reGetSms').css({
        		'display':'none'
        	})
        	$('.countDown').css({
          		'display':'inline-block'
          	})
        	var countDownInterval = setInterval(function(){
        		time--;
        		$('.countDown').html(time+'s');
                if (time === 0) {
                  clearInterval(countDownInterval);
                  $('#reGetSms').css({
              		'display':'inline-block'
              	  })
              	  $('.countDown').css({
              		  'display':'none'
              	  }).html("");
                  time = 60;
                }
        	},1000);
        }
        
        //获取验证码 请求发送验证码到手机 type为2表示重新获取手机验证码
        var url = "<%=request.getContextPath()%>/sys/sys_Login_checkUserPhoneJSON.action";
        var params ={
        	"account":account,
        	"type":type
        }
        asynPostParam(url,params,function(data){
        	//data = JSON.parse(data);
        	data = $.evalJSON(data);
        	if(data.status != 1){
        		$.messager.alert("提示",data.message,"info");
        		return false;
        	}else{
        		//输入手机号码正确以后 上方图片进度变更
                $('.steps>img').attr('src', './images/ulwxbase.sys/step2.png')
                $('.first-step').css({
                    display: 'none'
                })            
               // var filerPhone = userPhone.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2')
                $('.isReadySend').html('已向账号' + account + '发送短信验证码');
                $('.second-step').css({
                    display: 'block'
                }) 
                
                var obj = $("#reGetSms"); 
            	$('#reGetSms').css({
            		'display':'none'
            	})
            	$('.countDown').css({
              		'display':'inline-block'
              	})
            	var countDownInterval = setInterval(function(){
            		time--;
            		$('.countDown').html(time+'s');
                    if (time === 0) {
                      clearInterval(countDownInterval);
                      $('#reGetSms').css({
                  		'display':'inline-block'
                  	  })
                  	  $('.countDown').css({
                  		  'display':'none'
                  	  }).html("");
                      time = 60;
                    }
            	},1000);
                
        	}
        });     
    }
    
    function checkSmsCode() {
        var smsCode = $('.second-step>input[name="smsCode"]').val();
        var account = $('.first-step>input[name="account"]').val();
        if (smsCode == '') {
        	$.messager.alert("提示",'短信验证码不能为空',"info");
            return false;
        }
        //需要判断验证码是否正确
        var url = "<%=request.getContextPath()%>/sys/sys_Login_checkSmsCodeJSON.action";
        var params = {
        	"smsCode":smsCode,
        	"account":account
        }
        asynPostParam(url,params,function(data){
        	data = $.evalJSON(data);
        	if(data.status != 1){
        		$.messager.alert("提示",data.message,"info");
        	}else{
        		$('.steps>img').attr('src', './images/ulwxbase.sys/step3.png')
        	    $('.second-step').css({
        	        display:'none'
        	    })
        	    $('.last-step').css({
        	        display:'block'
        	    })        	       
        	}
        })
      
    }
    function comfirmPassword() {
        var password = $("#password").passwordbox("getValue");
        var conFirmPWD = $("#conFirmpwd").passwordbox("getValue");
        var smsCode = $('.second-step>input[name="smsCode"]').val();
        var account = $('.first-step>input[name="account"]').val();
        if(password==''){
        	$.messager.alert("提示",'请填写新密码',"info");
            return false;
        }
        if(conFirmPWD==''){
        	$.messager.alert("提示",'请填写确认密码',"info");
            return false;
        }
        if(password!==conFirmPWD){
        	$.messager.alert("提示",'新密码和确认密码不一致!',"info");
            return false;
        }
        var encrypt = new JSEncrypt();
        encrypt.setPublicKey(getRsaPublicKey());
        password=encrypt.encrypt(password);
        conFirmPWD=encrypt.encrypt(conFirmPWD);
        var url ="<%=request.getContextPath()%>/sys/sys_Login_savePasswordJSON.action";
        var params = {
        	"password":password	,
        	"account":account,
        	"smsCode":smsCode,
        	"conFirmPWD":conFirmPWD
        }
        asynPostParam(url,params,function(data){
        	data = $.evalJSON(data);
        	if(data.status == 1){
        		$.messager.alert("提示",data.message,"info",function(){       		
        			toLogin();      			
        		});       		
        	}else{
        		alert(data.message);	
        	}
        })
    }
    
    function toLogin(){
    	location.href = 'index.jsp';
    }
</script>
</body>
</html>