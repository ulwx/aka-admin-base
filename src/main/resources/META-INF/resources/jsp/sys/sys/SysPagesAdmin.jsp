<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<!DOCTYPE >
<html>
<head>
    <meta charset="UTF-8">
    <title>页面管理</title>
    <jsp:include page="/head.jsp" flush="true"></jsp:include>

    <style type="text/css">
        .textbox-label {
            width: 60px;
        }

        .datagrid-toolbar {
            border-width: 0 0 0 0
        }
    </style>
    <script type="text/javascript">

        function getDataGridColums() {
            var columns = [[
                {field: 'id', title: '编号', align: 'center', width: 60, sortable: true},
                {field: 'name', title: '用户姓名', align: 'center', width: 100, sortable: true},
                {field: 'account', title: '用户账号', align: 'center', width: 100, sortable: true},
                {field: 'pageName', title: '页面名称', align: 'center', width: 200, sortable: true},
                {field: 'serviceRight', title: '权限点', align: 'center', width: 100, sortable: true},
                {field: 'updateTime', title: '修改时间', align: 'center', width: 150, sortable: true},
                {field: 'options', title: '操作', align: 'center', width: 100, sortable: true,
                	formatter:function(value,row,index){
                		return "<a href='#' style='color:#056dae' onclick='del("+index+")'>删除</a>"
                	}	
                }
            ]];

            return columns;
        };


		function del(index){
			 var rows = $(".pageAdminList").datagrid("getRows");
        	 var row=rows[index];
        	 $.messager.confirm('?','是否确认删除', function(r){
        		 if(r){
         			$.ajax({
     					url:"<%=request.getContextPath()%>/sys/sys_SysPages_deleteJSON.action",
     					type:"post",
     		    		data:{
     		    			"id":row.id
     		    		},
     		    		dataType:"json",
     		    		success:function(data){
     						if(data.status==1){
     							$.messager.alert("提示", data.message, "info",
     							function() {
     								window.location.reload();
     							});
     						}
     						else{
     							$.messager.alert("提示", data.message);
     						}
     		    		}
     				});
         		}
        	 });
		}

        function lookup() {
            $('.pageAdminList').datagrid("load", getQueryParm());
        }

        function getQueryParm() {
            var parmObject = $(".searchForm").serializeObject();
            return parmObject;
        }


        function reloadGrid() {
            $(".pageAdminList").datagrid("reload");
        }

        $(document).ready(function () {
        	var gridUrl="<%=request.getContextPath()%>/sys/sys_SysPages_getPageAdminListJSON.action";
            initDataGrid(".pageAdminList", gridUrl, getQueryParm(), getDataGridColums(), {fitColumns: false});


            $("body").css("visibility", "visible");
        });

        function lookup() {
            $('.pageAdminList').datagrid("load", getQueryParm());
        }

        function getQueryParm() {
            var parmObject = $(".searchForm").serializeObject();
            return parmObject;
        }


        function reloadGrid() {
            $(".pageAdminList").datagrid("reload");
        }

		function addFn(){
			var url = "<%=request.getContextPath()%>/jsp/sys/sys/SysAddAdmin.jsp";
			addRec(url, reloadGrid, '页面设置', {}, 750, 500);
			};
		


    </script>
</head>
<body class="easyui-layout commonPage" style="visibility: hidden">
<div region="center" border="false" title="" data-options="headerCls:'headerClass'">
    <div id="top" class="tools-box">
        <form class="searchForm" onsubmit="return false;">
	        <div class="search-params">
				<input class="easyui-textbox" type="text" name="name"
					id="name" style="width: 120px;" data-options="prompt:'用户姓名'" />
					
			</div>
            <a class="search easyui-linkbutton" onclick="lookup()">查询</a>
            <a class="search easyui-linkbutton" onclick="addFn()">添加</a>
        </form>
    </div>
    <table id="pageAdminList" class="pageAdminList">
    </table>
</div>
</body>
</html>