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
	function add(){
		clearEasyUiInvalidTip("#saveUserForm");
	}

	function initfirst(){
		initform();
	}

	function initform(){
		clearEasyUiInvalidTip("#saveUserForm");
	}

	function alter(){
		loadform(dlg.updateRec);

	}

	function loadform(uRec){
		$('#saveUserForm').form('load',uRec);
	}

	/**弹窗框弹出的时候会初始化调用init方法，dlg为弹出框的引用*/
	var dlg=null;
	var edit=false;
	function init(d){
		dlg=d;
		initfirst();

		loadUserName();
		loadPageName();
		loadRightName();

		if(dlg.updateRec){//修改
			edit=true;
			loadRoleList('');
			alter();
		}else{//新增
			edit=false;
			add();
		}

		$("body").css("visibility", "visible");

	}

	function loadPageName(){
		var url='<%=request.getContextPath()%>/sys/sys_SysPages_getloadPageNameJSON.action';
		loadCombobox("#pageName",url,null,null,{multiple:false,onSelect:function(record){

		},
		onLoadSuccess: function (){ //加载完成后,设置选中第一项
			var val = $(this).combobox("getData");
			for (var item in val[0]) {
				if (item == "id") {
					$(this).combobox("select", val[0][item]);
				}
			}
		}
		});
	}

	function loadRightName(){
		var url='<%=request.getContextPath()%>/sys/sys_SysPages_getloadRightNameJSON.action';
		loadCombobox("#rightName",url,null,null,{multiple:false,onSelect:function(record){

		},
		onLoadSuccess: function (){ //加载完成后,设置选中第一项
				var val = $(this).combobox("getData");
					  for (var item in val[0]) {
							if (item == "id") {
							   $(this).combobox("select", val[0][item]);
							}
						 }
				  }
		});
	}

	function loadUserName(){
		var url='<%=request.getContextPath()%>/sys/sys_SysPages_getSysloadJSON.action';
		loadCombobox("#name",url,null,null,{multiple:true,onSelect:function(record){

		}
		});
	}

	
    function saveFormData() {
    	clearEasyUiInvalidTip("#saveUserForm");
    	$("body").showLoading();
    	var name = $("#name").combobox("getValues");
    	var pageName = $("#pageName").combobox("getValue");
    	var rightName = $("#rightName").combobox("getValue");
    	if(name.length<=0){
    		$("body").hideLoading();
    		$.messager.alert("提示","请选择用户");
    		$("#name").textbox("setValue","");
    		return false;
    	}
    	var url="<%=request.getContextPath()%>/sys/sys_SysPages_addPagesServiceRightUserJSON.action";
		$.ajax({
			url:url,
			dateType:"json",
			type:"post",
			traditional: true,
			data:{
				"name":name,
				"pageName":pageName,
				"rightName":rightName
			},
			success:function(data){
    			$("body").hideLoading();
    			data=$.evalJSON(data);
    			if (data.status == 1) {
					$.messager.alert("提示", data.message, "info",
							function() {
								dlg.reloadGrid();//重新刷新后端表格
								dlg.close();
							});
				} else {
					$.messager.alert("提示",
							"操作失败！[" + data.message + "]", "error",
							function() {
							});
    				}
    			}
    		}) ;
    	
	}

    
	//点击取消把窗口关闭
	function closeWindow() {
		dlg.close();
	};
</script>

</head>
<body style="visibility: hidden;">

	<form class="commonForm" id="saveUserForm">
				<div class="commonForm-items">
					<select id="pageName" name="pageName" class="easyui-combobox" style="width:100%"
						data-options="label:'页面名称' ,required:true,editable:false">
					</select>
				</div>
				
				<div class="commonForm-items">
					<select id="rightName" name="rightName" class="easyui-combobox" style="width:100%"
						data-options="label:'权限' ,required:true,editable:false">
					</select>
				</div>
				
				<div class="commonForm-items">
					<select id="name" name="name" class="easyui-combobox" style="width:100%"
						data-options="label:'用户' ,required:true">
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