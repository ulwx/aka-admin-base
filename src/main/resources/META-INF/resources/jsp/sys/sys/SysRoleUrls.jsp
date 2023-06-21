<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage=""%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>角色URL列表</title>
<jsp:include page="/head.jsp" flush="true"></jsp:include>

<style  type="text/css">

</style>
<script type="text/javascript">

function getDataGridColums(){
	 var columns=[[
		/* {field: 'ck', checkbox: true,  align: 'center',width:100}, */
        {field: 'id', title: '序号', align: 'center',width:100,sortable:true},
        {field: 'roleName', title: '角色名称', align: 'center',width:100,sortable:true},
        {field: 'urlMatch', title: '角色菜单', align: 'center',width:100,sortable:true},
        {field: 'updatime', title: '创建时间', align: 'center',width:100,sortable:true},
        {field:"operation",title:'操作',align: 'center',width:100,sortable:true
        	 ,formatter: function(value,row,index){
        		return  "<a href='#' style='color:#056dae' onclick='editRoleUrls("+index+")'>修改</a> <a href='#' style='color:#056dae' onclick='delRoleUrls("+index+")'>删除</a>";
			} 
        }
    ]];
	
	return columns; 
}
	
	function reloadGrid(){
		$(".roleUrlsList").datagrid("reload");
	}
	
    $(document).ready(function () {
    	var gridUrl="<%=request.getContextPath()%>/sys/sys_SysRoleUrls_getJProjectListJSON.action";
    	initDataGrid(".roleUrlsList",gridUrl,getQueryParm(),getDataGridColums());
    	loadRoleList('');
    	$(".combo").click(function(){
			$(this).prev().combobox("showPanel");
		})
        $("body").css("visibility", "visible");
    });
    
    function lookup() {      
  		$('.roleUrlsList').datagrid("load",getQueryParm());   
     }
	
    function getQueryParm(){
 	   var parmObject =$(".searchForm").serializeObject();
     	return parmObject;
    }
    
    
    //删除
	function delRoleUrls(index){
    	
		var rows = $('.roleUrlsList').datagrid('getRows');
	   	var row = rows[index];
		$.messager.confirm('提示','确定要删除吗?',function(r){
		    if (r){
		    	var address = "<%=request.getContextPath()%>/sys/sys_SysRoleUrls_delRoleUrlsJSON.action";
		    	$.ajax({
		      		url:address,
		      		dataType:"json",
		      		data:{
		      			"id":row.id
		      		},
		      		type:"POST",
		      		success:function(data){
		      			$.messager.alert("提示", "删除成功!", "info", function(r) {
		       				window.location.reload();
		       			});
		      		}
		      	})
		    }
		});
		
	}
  
	//添加
	function addRoleUrls(){
		var url = "<%=request.getContextPath()%>/jsp/sys/sys/EditSysRoleUrls.jsp";
		addRec(url,reloadGrid,'编辑角色菜单',{},630,350);
	}
    
	
	//编辑分公司
    function editRoleUrls(index) {
    	var rows = $('.roleUrlsList').datagrid('getRows');
    	var row = rows[index];
    	var url="<%=request.getContextPath()%>/jsp/sys/sys/EditSysRoleUrls.jsp";
    	editRec2('.roleUrlsList',url,reloadGrid,"编辑角色菜单",{},row,630,350);
    	
    }
    
   //角色类型
	function loadRoleList(ids){
		var url='<%=request.getContextPath()%>/sys/sys_SysRoleUrls_getRoleListJSON.action?id='+ids;
		loadCombobox("#roleId",url,ids,null,{multiple:false});
	}
  
</script>
</head>
<body class="easyui-layout commonPage" style="visibility: hidden" >
	<div  region="center" border="false" title="" data-options="headerCls:'headerClass'" >
		 <div id="top" class="tools-box">
			<div class="search-box">
				<form class="searchForm" onsubmit="return false;">
				
					<select data-options="label:'角色类型'" id="roleId" name="roleId" class="easyui-combobox" style="width:240px;">
					</select>
					<a class="search easyui-linkbutton" onclick="lookup()">查询</a>
					<a class="search easyui-linkbutton" onclick="addRoleUrls()">新增</a>
				</form>
			</div>
		</div>
		<table class="roleUrlsList" >
		</table>
	</div>
</body>
</html>