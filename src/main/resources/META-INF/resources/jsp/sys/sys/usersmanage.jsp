<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage=""%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户管理列表</title>
<jsp:include page="/head.jsp" flush="true"></jsp:include>

<style  type="text/css">

</style>
<script type="text/javascript">
	
function getDataGridColums(){
	var columns=[[
        {field: 'ck', checkbox: true,  align: 'center',width:100},
        {field: 'name', title: '姓名', align: 'center',width:100,sortable:true},
        {field: 'nikeName', title: '昵称', align: 'center',width:100,sortable:true},
        {field: 'sex', title: '性别', align: 'center',width:100,sortable:true},
        {field: 'phone', title: '手机号',  align: 'center',width:100,sortable:true},
        {field: 'account', title: '帐号',  align: 'center',width:100,sortable:true},
        {field: 'sysRoleNames', title: '角色',  align: 'center',width:100},
        {field: 'sysRoleTypeNames', title: '角色类型',  align: 'center',width:100},
		{field: 'enable', title: '有效',  align: 'center',width:100, formatter: function(value,row,index){
				if(value==1){
					return "是";
				}else{
					return "否";
				}

			}},
        {field: 'isAdmin', title: '是否是管理员', align: 'center',width:100, 
        	formatter: function(value,row,index){
        		if(isAdmin(row.sysRoleTypeCodes)){
        			return "是";
        		}else{
        			return "否";
        		}
			
			}
        }
    ]];
	
	return columns;
}

 function isAdmin(sysRoleTypeCodes){
	if(isEmpty(sysRoleTypeCodes)){
	 return false;
	}
	var roleTypes=sysRoleTypeCodes.split(",");
	var index=roleTypes.indexOf("0");
	if(index>=0){
		return  true;
	}else{
		return false;
	}
 }

    $(document).ready(function () {
    	
     	var gridUrl="<%=request.getContextPath()%>/sys/sys_SysUser_userListJSON.action";
    	initDataGrid(".userList",gridUrl,getQueryParm(),getDataGridColums()); 
    	 
        $("body").css("visibility", "visible");
    });
    

    function getQueryParm(){
 	   var parmObject =$(".searchForm").serializeObject();
     	return parmObject;
    }

    function lookup() {          
  		$('.userList').datagrid("load",getQueryParm());   
     }
    
    function editItems() { 
    	var url="<%=request.getContextPath()%>/jsp/sys/sys/userop.jsp";
    	editRec('.userList',url,reloadGrid,"编辑用户",{},740);    
    }
    
    //跳转到新增用户页面
    function addItem() {
    	var url="<%=request.getContextPath()%>/jsp/sys/sys/userop.jsp";
    	addRec(url,reloadGrid,'新增用户',{},740);  
    }
    
	function reloadGrid(){
		$(".userList").datagrid("reload");
	}
   
    //确认删除
    function delUser() {
    	
    	var records = $('.userList').datagrid('getChecked');
    	if(records.length && records.length>0){
    		for(var i=0; i<records.length; i++){
        		if(isAdmin(records[i].sysRoleTypeCodes)){
        			$.messager.alert("提示","管理员不能删除！","info");
        			return false;
        		}
        			
        	}
    	}
  
    	$.messager.confirm('确认', '是否删除账户信息?', function(r){
    		if (r){
    			var url='<%=request.getContextPath()%>/sys/sys_SysUser_delUserJSON.action';
		    	operRec('.userList',url,reloadGrid);
    		}
    	});
    	
    }
   
</script>
</head>
<body class="easyui-layout commonPage" style="visibility: hidden" >

		<div  region="center" border="false" title="用户管理" data-options="headerCls:'headerClass'" >
			 <div id="top" class="tools-box">
				<div class="search-box">
				<form class="searchForm" onsubmit="return false;">
					<div class="search-params">
						<input  type="text" class="easyui-textbox" name="userName" data-options="prompt:'姓名/账号'">
					</div>
					<div class="search-params">
						<input  type="text" class="easyui-textbox" name="userPhone" data-options="prompt:'手机号'" >
					</div>
					<div class="search-params">
						<select id="enable" class="easyui-combobox" name="enable"  style="width:100%"
								data-options="label:'是否有效:' ,required:true" >
							<option value="1" >有效</option>
							<option value="0">无效</option>
						</select>
					</div>
					<a class="search easyui-linkbutton" onclick="lookup()">查询</a>
				</form>
				</div>
				<div class="oper-tool-box">
					
					<a onclick="delUser()"
						class="easyui-linkbutton orangeBtn">删除</a>
					<a class="easyui-linkbutton" onclick="editItems()">修改</a>
					<a class="easyui-linkbutton" onclick="addItem()">新增</a>
				</div>
			</div>
			<table class="userList" >
			</table>
		</div>

</body>
</html>