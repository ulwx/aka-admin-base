<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage=""%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>生成模拟登陆链接</title>
<jsp:include page="/head.jsp" flush="true"></jsp:include>

<script>


	
	function initform(){
		clearEasyUiInvalidTip("#saveUserForm");
	}
	
	$(document).ready(function(){
		
		$("body").css("visibility", "visible");  
		
	});	
    
	//点击取消把窗口关闭
	function closeWindow() {
		dlg.close();
	}


	//保存
	function saveFormData() {
		var	url= "<%=request.getContextPath()%>/sys/sys_Login_getAutoSignLoginUrlJSON.action";
    	clearEasyUiInvalidTip("#saveUserForm");
    	$("body").showLoading();
    	var params=new Object();
    	var mobile=$("#mobile").textbox("getValue");
    	var sysUserId=$("#sysUserId").textbox("getValue");
    	if(mobile && sysUserId){
    		params.mobile=mobile;
    		params.sysUserId=sysUserId;
    		asynPostParam(url,params,function(data){
    			$("body").hideLoading();
    			data = $.evalJSON(data);
    			if (data ) {
    				if(data.status == 1){
    					$("#sign").textbox("setValue",data.message);
    				}else{
    					$.messager.alert("提示", data.message, "info");
    				}

    			}else{
    				$.messager.alert("提示", "操作失败，请联系管理员！", "info", function() {
    		
    				});
    			}
    		});
    	}else{
    		$.messager.alert("提示","手机号或账户id不能为空");
    	}
    	
	}

	
</script>



</head>
<body class="easyui-layout commonPage" style="visibility: hidden;">
<div  region="center" border="false" title="" data-options="headerCls:'headerClass'" 
 style="background-color:white">
	<form class="commonForm" id="saveUserForm">

		<div class="commonForm-items">
			<input id="mobile" class="easyui-textbox" name="mobile" style="width:100%"
				data-options="label:'手机号码：' ,required:true" />
		</div>
		<div class="commonForm-items">
			<input id="sysUserId" class="easyui-textbox" name="sysUserId" style="width:100%"
				data-options="label:'账户id:' ,required:true" />
		</div>
		<div class="commonForm-items">
			<input id="sign" class="easyui-textbox" name="sign" style="height:90px;width:100%"
				data-options="label:'访问路径：' ,required:true,multiline : true" />
		</div>
		<div class="btns">
			<a style="margin-right: 15px;" class="easyui-linkbutton"
				onclick="saveFormData()">生成访问路径</a> 
		</div>
		
	</form>
		
</div>
</body>
</html>