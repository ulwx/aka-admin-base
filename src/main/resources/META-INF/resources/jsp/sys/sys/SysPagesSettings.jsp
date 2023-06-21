<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage="" %>
<!DOCTYPE >
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
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
                {field: 'pageName', title: '页面名称', align: 'center', width: 150, sortable: true},
                {field: 'matchUrlSuffix', title: 'URL', align: 'center', width: 300, sortable: true},
                {field: 'statusName', title: '状态', align: 'center', width: 150, sortable: true},
                {field: 'options', title: '操作', align: 'center', width: 150, sortable: true,
                	formatter:function(value,row,index){
                		return "<a href='#' style='color:#056dae' onclick='update("+index+")'>修改</a>"
                	}	
                }
            ]];

            return columns;
        };


		function update(index){
			 var rows = $(".pageList").datagrid("getRows");
        	 var row=rows[index];
        	 var url="<%=request.getContextPath()%>/jsp/sys/sys/SysAddPage.jsp";
        	 editRec2('.pageList',url,reloadGrid,"编辑信息",{},row,680,300);
		}

        function lookup() {
            $('.pageList').datagrid("load", getQueryParm());
        }

        function getQueryParm() {
            var parmObject = $(".searchForm").serializeObject();
            return parmObject;
        }


        function reloadGrid() {
            $(".pageList").datagrid("reload");
        }

        $(document).ready(function () {
        	var gridUrl="<%=request.getContextPath()%>/sys/sys_SysPages_getPageListJSON.action";
            initDataGrid(".pageList", gridUrl, getQueryParm(), getDataGridColums(), {fitColumns: false});


            $("body").css("visibility", "visible");
        });

        function lookup() {
            $('.pageList').datagrid("load", getQueryParm());
        }

        function getQueryParm() {
            var parmObject = $(".searchForm").serializeObject();
            return parmObject;
        }


        function reloadGrid() {
            $(".pageList").datagrid("reload");
        }

		function addFn(){
			var url = "<%=request.getContextPath()%>/jsp/sys/sys/SysAddPage.jsp";
			addRec(url, reloadGrid, '页面设置', {}, 650, 330);
		}


    </script>
</head>
<body class="easyui-layout commonPage" style="visibility: hidden">
<div region="center" border="false" title="" data-options="headerCls:'headerClass'">
    <div id="top" class="tools-box">
        <form class="searchForm" onsubmit="return false;">
	        <div class="search-params">
				<input class="easyui-textbox" type="text" name="pageName"
					id="pageName" style="width: 120px;" data-options="prompt:'页面名称'" />
					
			</div>
            <a class="search easyui-linkbutton" onclick="lookup()">查询</a>
            <a class="search easyui-linkbutton" onclick="addFn()">添加</a>
        </form>
    </div>
    <table id="pageList" class="pageList">
    </table>
</div>
</body>
</html>