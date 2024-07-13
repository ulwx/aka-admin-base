
<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage=""%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta charset="UTF-8">
<title></title>
<jsp:include page="/head.jsp" flush="true"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ueditor/ueditor.all.js"></script>
<style>

</style>

<script>
	function alter(){
		loadform(dlg.updateRec);
	}

	function initform(){
		clearEasyUiInvalidTip("#saveUserForm");
	}

	function initfirst(){
		initform();
	}

	function loadform(uRec){
		$('#saveUserForm').form('load',uRec);

	}
	/**弹窗框弹出的时候会初始化调用init方法，dlg为弹出框的引用*/
	var dlg=null;
	var edit=false;
	function init(d){
		dlg=d;
		$("body").css("visibility", "visible");
	}

	$(document).ready(function() {
		$("body").css("visibility", "visible");
		myInit();
		clearEasyUiInvalidTip("#saveUserForm");

	});

	function myInit(){
		if(dlg!=null) {
			initfirst();
			loadDicStatus();
			if(dlg.updateRec){//修改
				edit=true;
				alter(dlg.updateRec);
			}else{//新增
				edit=false;
				add();
			}
			return;
		}
		setTimeout(myInit,100);
	}

	function loadDicStatus(){
		var url='<%=request.getContextPath()%>/sys/sys_SysPages_getloadDicStatusJSON.action';
		loadCombobox("#statusName",url,null,null,{multiple:false,onSelect:function(record){
			
		},
  		onLoadSuccess:function(){  //加载完成后,设置选中第一项
			var val = $(this).combobox("getData");
			for (var item in val[0]) {
				if (item == "id") {
					$(this).combobox("select", val[0][item]);
				}
			}
		}
  		});
	}
	
    function saveFormData() {
    	clearEasyUiInvalidTip("#saveUserForm");
    	$("body").showLoading();
    	var pageName = $("#pageName").textbox("getValue");
    	var matchUrlSuffix = $("#matchUrlSuffix").textbox("getValue");
    	var statusName = $("#statusName").combobox("getValue");
    	if(pageName == ""){
    		$("body").hideLoading();
    		$.messager.alert("提示","请输入页面名称");
    		$("#pageName").textbox("setValue","");
    		return false;
    	}
    	if(matchUrlSuffix == ""){
    		$("body").hideLoading();
    		$.messager.alert("提示","请输入页面地址");
    		$("#matchUrlSuffix").textbox("setValue","");
    		return false;
    	}
    	var url = null;
    	if(dlg.updateRec != null){
    		url="<%=request.getContextPath()%>/sys/sys_SysPages_updatePageJSON.action";
    	}
    	else {
    		url="<%=request.getContextPath()%>/sys/sys_SysPages_AddPageJSON.action";
    	}
    	
      	$('#saveUserForm').form('submit',{
   			url : url,
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
   				var ret = $.evalJSON(data);
   				if (ret.status == 1) {
   					$.messager.alert("提示", ret.message, "info",
   							function() {
		   						dlg.reloadGrid();//重新刷新后端表格
								dlg.close();
   							});
					$("body").hideLoading();
				} else {
   					$.messager.alert("提示",
   							"操作失败！[" + ret.message + "]", "error",
   							function() {
   							});
					$("body").hideLoading();
				}
   			}
    	});
	};

    
	//点击取消把窗口关闭
	function closeWindow() {
		dlg.close();
	}
</script>

</head>
<body style="visibility: hidden;">

	<form class="commonForm" id="saveUserForm">
				<div class="commonForm-items">
				<input type="hidden" name="id" id="id">
				<input data-options="required:true,label:'页面名称'" style="width:100%"
					class="easyui-textbox" name="pageName" id="pageName" />
				</div>
				<div class="commonForm-items">
			
				<input data-options="required:true,label:'页面地址'" style="width:100%"
					class="easyui-textbox" name="matchUrlSuffix" id="matchUrlSuffix" />
				</div>
				
				<div class="commonForm-items">
			
					<select id="statusName" name="statusName" class="easyui-combobox" style="width:100%"
						data-options="label:'状态' ,required:true,editable:false">
					</select>
				</div>
				<div class="btns">
					<a style="margin-left: -150px;" class="easyui-linkbutton"
						onclick="saveFormData()">保存</a> <a
						class="easyui-linkbutton normalBtn" onclick="closeWindow()">取消</a>
				</div>
	</form>


</body>
</html>