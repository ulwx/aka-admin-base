<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage=""%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>编辑角色链接</title>
<jsp:include page="/head.jsp" flush="true"></jsp:include>

<script>

	
	function add(){
		loadRoleList('');
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
		$("#id").val(dlg.updateRec.id);
		
		$("#roleId").combobox("disable");
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
		
		if(dlg.updateRec){//修改
			edit=true;
			loadRoleList('');
			alter();
		}else{//新增
			edit=false;
			add();
			$(".combo").click(function(){
				$(this).prev().combobox("showPanel");
			})
		}
		
		
		
		$("body").css("visibility", "visible");  
		
	}	
    
	//点击取消把窗口关闭
	function closeWindow() {
		dlg.close();
	}


	//保存
	function saveFormData() {
		var	url= "";
    	clearEasyUiInvalidTip("#saveUserForm");
    	$("body").showLoading();
    	if(edit){
    		url = "<%=request.getContextPath()%>/sys/sys_SysRoleUrls_updateRoleUrlsByIdJSON.action";
    	}else{
    		url = "<%=request.getContextPath()%>/sys/sys_SysRoleUrls_addRoleUrlsJSON.action";
    	}
    	
		
		$('#saveUserForm').form('submit',{
				url : url,
				onSubmit : function(param) {
					var ret = $(this).form('enableValidation').form('validate');
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
					} else {
						$.messager.alert("提示",
								"操作失败！[" + ret.message + "]", "error",
								function() {
								});
					}
				}
		});
	}

	//角色类型
	function loadRoleList(ids){
		var url='<%=request.getContextPath()%>/sys/sys_SysRoleUrls_getRoleListJSON.action?id='+ids;
		loadCombobox("#roleId",url,ids,null,{multiple:false});
	}
	
</script>



</head>
<body class="easyui-layout commonPage" style="visibility: hidden;">
<div  region="center" border="false" title="" data-options="headerCls:'headerClass'" 
 style="background-color:white">
	<form class="commonForm" id="saveUserForm">
	
		<div class="commonForm-items">
			<select id="roleId" class="easyui-combobox" name="roleId" style="width:100%"
				data-options="label:'角色名称' ,required:true" >
			</select>
		</div>
		
		<div class="error-tips"></div>
		
		<div class="commonForm-items">
			<input id="urlMatch" class="easyui-textbox" name="urlMatch" style="height:180px;width:100%"
				data-options="label:'角色菜单' ,multiline:true,required:true" />
		</div>
		<input id="id" name="id" type="hidden" />
		<div class="btns">
			<a style="margin-right: 15px;" class="easyui-linkbutton"
				onclick="saveFormData()">保存</a> <a
				class="easyui-linkbutton normalBtn" onclick="closeWindow()">取消</a>
		</div>
	</form>
		
</div>
</body>
</html>