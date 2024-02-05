<%@ page import="com.github.ulwx.aka.admin.utils.CbAppConfigProperties" %>
<%@ page import="com.github.ulwx.aka.webmvc.BeanGet" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage=""%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta charset="UTF-8">
<title>用户管理</title>
<jsp:include page="/head.jsp" flush="true"></jsp:include>
<style type="text/css">
	.commonForm-items{
		width:380px !important;
	}
</style>

<%
 CbAppConfigProperties properties = BeanGet.getBean(CbAppConfigProperties.class,request);
 String initPass=properties.getLoginConfig().getInitPassword();
%>
<script>
	
	$(document).ready(function () {			
			 
	})

	function initform(){

	    clearEasyUiInvalidTip("#saveUserForm");
	}
	function initfirst(){
		initform();
		
	}
  	function add(){
  		loadSysRoleIds();
  		loadSysRoleTypeCodes();
	}
  	function loadSysRoleIds(ids){
  		//loadCombobox(selector,url,value,excludeFirst,options)
  		var url='<%=request.getContextPath()%>/sys/sys_SysUser_getSysRolesJSON.action';
  		if(ids){
  			ids=ids.split(",");
  		}
  		loadCombobox("#sysRoleIds",url,ids,null,{multiple:true});
  	}
  	
  	function loadSysRoleTypeCodes(ids){
  		var url='<%=request.getContextPath()%>/sys/sys_SysUser_getSysRoletypesJSON.action';
  		if(ids){
  			ids=ids.split(",");
  		}
  		loadCombobox("#sysRoleTypeCodes",url,ids,null,{multiple:false});
  	}
	function alter(){
		loadform(dlg.updateRec);
	}
	function loadform(uRec){
		
		$("input[name='originPw']:radio:eq(1)").click();
		 
		var pass="xyz123456@1234";
		uRec.originPw="false";
		uRec.password=pass;
		uRec.confirmPassword=pass;
		//alert($.toJSON(uRec));
		$('#saveUserForm').form('load',uRec);
		
		loadSysRoleIds(uRec.sysRoleIds);
  		loadSysRoleTypeCodes(uRec.sysRoleTypeCodes);
		
  	  
	}
	/**弹窗框弹出的时候会初始化调用init方法，dlg为弹出框的引用*/
	var dlg=null;
	var edit=false;
	function init(d){
		dlg=d;
		
		initfirst();
		
		if(dlg.updateRec){//修改
			edit=true;
			alter();
		}else{//新增
			edit=false;
			add();
			
		}
		$("body").css("visibility", "visible");  
		
	}	

	/**更新时使用，此方法会在当前页面所有ajax调用完后被框架触发，保证combobox类似这种异步加载完数据后，加载当前页面的数据 */
	function all_ajax_loaded_on_init(){
		if(edit){
		}
		
	}
	
    function saveFormData() {
    	clearEasyUiInvalidTip("#saveUserForm");
    
    	$("body").showLoading();
    	var url="<%=request.getContextPath()%>/sys/sys_SysUser_addUserJSON.action";
    	if(edit){
    		url="<%=request.getContextPath()%>/sys/sys_SysUser_editUserJSON.action";
		} 
		$('#saveUserForm').form('submit',{
				url : url,
				onSubmit : function(param) {
					$(this).form('enableValidation');
					var ret =$(this).form('validate');
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

	//点击取消把窗口关闭
	function closeWindow() {
		dlg.close();
	}
</script>

</head>
<body style="visibility: hidden;" >

	<form class="commonForm" id="saveUserForm">
		<div class="commonForm-items">
			<input type="text" id="account" class="easyui-textbox easyui-mytooltip" name="account" style="width:100%"
				data-options="label:'登陆账号:' ,required:true,tipPosition:'right'" />
				<div class="form-tips">请填写登陆账号</div>
		</div>
		<div class="commonForm-items">
			<input type="text" id="phone" class="easyui-textbox" name="phone" style="width:100%"
				data-options="label:'手机号:' ,required:true,validType:'mobile'" />
				<div class="form-tips">请输入手机号码</div>
		</div>
		<div class="commonForm-items">
			<input data-options="required:true,label:'姓名:',validType:['chinese','maxLength[6]']"  style="width:100%"
				class="easyui-textbox" name="name" id="name" />
			<input type="hidden" name="sysUserSno" />
			<div class="form-tips">请输入中文,最大长度6个字符</div>
		</div>
		<div class="commonForm-items">
			<input data-options="label:'昵称:',validType:'maxLength[10]'" style="width:100%"
				class="easyui-textbox" name="nikeName" id="nikeName" />
				<div class="form-tips">最大长度10字符</div>
		</div>
		<div class="commonForm-items">
			<select id="sex" class="easyui-combobox" name="sex" id="sex" style="width:100%"
				data-options="label:'性别:' ,required:true" >
				<option value="男" selected="selected">男</option>
				<option value="女">女</option>
			</select>
		</div>

		<div class="commonForm-items">
			<input 
				data-options="label:'密码:' ,required:true,showEye:false,validType:'password'" style="width:100%"
				class="easyui-passwordbox" id="password" name="password" />
				<div class="form-tips">8-16位，不包含空格，不能为纯数字或纯字母</div>
		</div>
		<div class="commonForm-items">
			<input 
				data-options="label:'确认密码:',required:true,showEye:false" style="width:100%"
				class="easyui-passwordbox" id="confirmPassword" validType='confirmPass["#password"]' name="confirmPassword" />
		</div>
		<div class="commonForm-items">
			<select id="sysRoleIds" name="sysRoleIds" class="easyui-combobox" style="width:100%"
				data-options="label:'菜单角色:' ,required:true">
			</select>
		</div>
		<div class="commonForm-items">
			<select id="sysRoleTypeCodes" name="sysRoleTypeCodes" class="easyui-combobox" style="width:100%"
				data-options="label:'角色类型:' ,required:true">
			</select>
		</div>
		<div class="btns">
			<a style="margin-right: 15px;" class="easyui-linkbutton"
				onclick="saveFormData()">保存</a> <a
				class="easyui-linkbutton normalBtn" onclick="closeWindow()">取消</a>
		</div>
	</form>


</body>
</html>