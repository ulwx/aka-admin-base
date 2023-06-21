<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta charset="UTF-8">
<title>上传</title>
<jsp:include page="/head.jsp" flush="true"></jsp:include>

<style>
</style>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/ueditor/ueditor.config.js"></script>
 <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/ueditor/ueditor.all.js"></script>
<script>
	
	$(document).ready(function () {			
		$("body").css("visibility", "visible");  
	})

	
	
    function saveFormData() {
    	clearEasyUiInvalidTip("#saveUserForm");
    
    	$("body").showLoading();
    	var url="testUEditorSubmit.jsp";

		$('#saveUserForm').form('submit',
		{
			url : url,
			iframe:true,
			onSubmit : function(param) {
				var ret = $(this).form('enableValidation').form(
						'validate');
				if (!ret) {
					$("body").hideLoading();
				}
				return ret;
			},
			success : function(data) {
				$("body").hideLoading();
		
			}
		});

	}

	//点击取消把窗口关闭
	function closeWindow() {
		dlg.close();
	}
</script>

</head>
<body >

	<form class="commonForm" id="saveUserForm" enctype="multipart/form-data" method="post">
		<div class="commonForm-items">
		<textarea id='myEditor' name='myEditor' style="width:700px;height:500px"></textarea>

		</div>
		<div class="btns">
			<a style="margin-right: 15px;" class="easyui-linkbutton"
				onclick="saveFormData()">保存</a> &nbsp; <a
				class="easyui-linkbutton normalBtn" onclick="closeWindow()">取消</a>
		</div>
	</form>

	<script type="text/javascript">
        //渲染编辑器
        var ue = UE.getEditor('myEditor');
    </script>

</body>
</html>