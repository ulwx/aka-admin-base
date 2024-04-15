<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>功能菜单</title>
<jsp:include page="/head.jsp" flush="true"></jsp:include>

<script>
	var editObj = null;
	$(document).ready(function(){
		$("body").css("visibility", "visible");
		//先隐藏窗口
		$('#win').window({
			width:580,
			height:450,
			closed: true,
			resizable: false,
			minimizable: false,
			maximizable: false
		});
		
		$("#Enable").combobox({
			editable: false,
			panelHeight: 'auto'
		});
		
		$('#mmenu').menu({   
		    onClick:function(item){   
		    	if(item.id=="mm_edit"){
		    		editFn();
		    	}else if(item.id=="mm_del"){
		    		delFn();
		    	}
		    }   
		}); 
		
		$('#tt2').mydatagrid({
			iconCls:'icon-save',
			nowrap: false,
			singleSelect: false,
			striped: true,
			fit: true,
			fitColumns:true,
			border:false,
			url:'<%=request.getContextPath()%>/sys/sys_SysRight_listJSON.action',
			idField:'sysRightCode',
			frozenColumns:[[
                {field:'ck',checkbox:true},
                {title:'权限码',field:'sysRightCode',width:80, align: 'center'}
			]],
			columns:[
			[
				{field:'sysRightName',title:'权限名称',width:100, align: 'center'},
				{field:'sysRightUrl',title:'菜单的URL',width:300, 
					formatter:function(value,rec){
					       return '<div style="word-wrap:  break-word; overflow:hidden; ">'
					      				 + value + '</div>';
		        	}
				},
				{field:'enable',title:'状态',width:60, 
					formatter:function(value,rec){
						if(value == 1)
							return "有效";
						else
							return "无效";
		        	}
				, align: 'center'},
				{field:'orderCode',title:'排序码',width:60, align: 'center'},
				{field:'updateTime',title:'更新时间',width:100, align: 'right'},
				{field:'updator',title:'更新人',width:100, align: 'center'}
			]],
			pagination:true,
			rownumbers:true,
			pageNumber:1,
			onRowContextMenu:function(e, rowIndex, rowData){
				$('#tt2').datagrid('unselectAll');
 				$('#mmenu').menu('show', {
 					left: e.pageX,
 					top: e.pageY
 				});
 				$('#tt2').datagrid('selectRow', rowIndex);
 				e.preventDefault();
			},
		    loadFilter: function(data){
                	if(data.data){
      				if (data.status==1){
      					return data.data;
      				} else {
      					$.messager.alert('提示',data.message,'error');
      					return {};
      				}
      			}else{
      				return data;
      			}
          	},
			toolbar:"#top"
		});
	});
	
	function addFn(){
		clear();
		clearEasyUiInvalidTip("#form2");
		$('#win').window('open');
		$('#win').window('center');
	}
	

	//###########删除数据
	function deleteData(value){
		$.ajax({
			type: "POST",
			dataType: "text",
			url: "<%=request.getContextPath()%>/sys/sys_SysRight_deleteJSON.action",
			data: {"deleteId":value},
			success: function(data){
				data = $.trim(data);
				data=$.evalJSON(data);
				if(data && data.status==1){
					$.messager.show({
						title:'提示',
						msg:"删除成功!!",
						showType:'show'
					});
					$('#tt2').datagrid("reload");
					$('#tt2').datagrid("unselectAll");
				}else{
					$.messager.alert("提示", "删除数据失败!!");
					return;
				}
			},
			error: function(){
				$.messager.alert("提示", "网络连接失败!");
			}
		});
	}
	
	//###########加载编辑页面
	function loadEdit(value){
		
		$.ajax({
			type: "GET",
			dataType: "text",
			url: "<%=request.getContextPath()%>/sys/sys_SysRight_getOneDataJSON.action?sno=" + value,
 				success: function(data){
 					data = $.trim(data);
 				
 					var jsonObject = eval('(' + data + ')');
 					if(jsonObject && jsonObject.status==1){
 						
 						clear();
 						var editObj = jsonObject.data;
 						
 						$('#form2').form('load',editObj);
 				  		$("#hiddenCode").val(editObj.sysRightCode);
						clearEasyUiInvalidTip("#form2");
 				  		$('#win').window('open');
 						$('#win').window('center');
 					
 					}else{
						$.messager.alert("提示", "获取数据失败!!");
						return;
 					}
 				},
 				error: function(){
 					$.messager.alert("提示", "网络连接失败!");
 				}
		});
	}
	
	//处理关闭
	function cancel(){
   		$('#win').window('close',true);
	}
	function reloadGrid(){
		$("#tt2").datagrid("reload");
	}
	
	//###################处理提交
	function ok(){

		var sno = $.trim($("#hiddenCode").val());
  		var url = "<%=request.getContextPath()%>/sys/sys_SysRight_insertJSON.action";

  		if(sno != ""){

  			url = "<%=request.getContextPath()%>/sys/sys_SysRight_updateJSON.action";
		}

		$("body").showLoading();
		$('#form2').form(
				'submit',
				{
					url : url,
					onSubmit : function(param) {
						if (sno != '') {
							param.sno = sno;
						}
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

						if (ret && ret.status == 1) {
							$.messager.alert("提示", ret.message, "info",
									function() {
										reloadGrid();//重新刷新后端表格
										cancel();
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
	//###################处理查询
	 function getQueryParm(){
 	   var parmObject =$(".searchForm").serializeObject();
     	return parmObject;
    }

    function lookup() {          
  		$('#tt2').datagrid("load",getQueryParm());   
     }

	//清理编辑页面的值
	function clear() {
		editObj = null;
		$('#win #form2').form("clear");
	}

	//修改函数
	function editFn() {
		var selections = $('#tt2').datagrid("getChecked");
		if (selections.length > 1) {
			var msg = "";
			for (var i = 0; i < selections.length; i++) {
				if (i > 0)
					msg += ",";
				msg += selections[i].sysRightCode;
			}
			$.messager.alert("提示", "至多选择修改一项!!您已勾选ID为" + msg);
			return;
		}
		if (selections.length < 1) {
			$.messager.alert("提示", "请选择要修改哪一项!!");
			return;
		}
		loadEdit(selections[0].sysRightCode);
	}
	//删除函数
	function delFn() {
		var selections = $('#tt2').datagrid("getChecked");
		if (selections.length < 1) {
			$.messager.alert("提示", "请选择需要删除的项目!!");
			return;
		}
		var msg = "";
		for (var i = 0; i < selections.length; i++) {
			if (i > 0)
				msg += ",";
			msg += selections[i].sysRightCode;
		}
		$.messager.confirm("提示", "确定删除吗?", function(b) {
			if (b)
				deleteData(msg);
		});
	}
</script>
</head>

<body class="easyui-layout commonPage" style="visibility: hidden;">
	<div region="center" border="false" title="权限管理"
		data-options="headerCls:'headerClass'">
		<div id="top" class="tools-box">
			<div class="search-box">
				<form class="searchForm">
					<div class="search-params">
						<input type="text" name="sSysRightCode"
							data-options="prompt:'权限码'" class="easyui-textbox"
							id="sSysRightCode" style="width: 80px;" />
					</div>
					<div class="search-params">
						<input type="text" name="sSysRightName" class="easyui-textbox"
							data-options="prompt:'权限名称'" id="sSysRightName"
							style="width: 150px;" />
					</div>

					<a href="#" class="easyui-linkbutton" iconCls="icon-search"
						onclick="lookup()">查询</a>
				</form>
			</div>

			<div class="oper-tool-box">
				<a href="javascript:void(0)" onclick="delFn()"
					class="easyui-linkbutton orangeBtn">删除</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="editFn()">修改</a> <a href="javascript:void(0)"
					class="easyui-linkbutton " onclick="addFn()">新增</a>
			</div>
		</div>

		<table id="tt2"></table>
	</div>




	<!--编辑页面 -->
	<div id="win" class="easyui-window" title="编辑功能菜单"
		data-options="closed:true">
		<form class="commonForm" id="form2">

			<div class="commonForm-items">
				<input class="easyui-textbox" type="text"
					data-options="label:'权限码:',required:true,validType:['positiveInteger','maxLength[5]']" name="sysRightCode"
					id="sysRightCode" style="width: 100%" /> <input
					type="hidden" name="hiddenCode" id="hiddenCode" />
					<div class="form-tips">只能输入正整数，最大输入5个字符，格式如：10000</div>
			</div>

			<div class="commonForm-items">

				<input class="easyui-textbox" type="text" style="width: 100%"
					name="sysRightName" id="sysRightName"
					data-options="label:'权限名称:',required:true,validType:'maxLength[20]'" />
					<div class="form-tips">最大输入20个字符</div>
			</div>
			<div class="commonForm-items">

				<input type="text" class="easyui-textbox" style="width: 100%"
					name="sysRightUrl" id="sysRightUrl"
					data-options="label:'权限URL:',required:false,validType:'maxLength[200]'" />
					<div class="form-tips">最大输入200个字符</div>
			</div>
			<div class="commonForm-items">

				<input class="easyui-textbox" type="text" style="width: 100%"
					name="icon" id="icon" data-options="label:'icon位置:',validType:['maxLength[2]']" />
				<div class="form-tips">菜单的icon放在一张图片里，垂直排列,最大输入2个字符</div>
				
			</div>
			<div class="commonForm-items">

				<input class="easyui-textbox" type="text" style="width: 100%"
					name="orderCode" id="orderCode"
					data-options="label:'排序码:',required:true,validType:['maxLength[6]']" />
					<div class="form-tips">最大输入6个字符</div>
			</div>
			<div class="commonForm-items">
				<select class="easyui-combobox" id="enable" name="enable"
					style="width: 100%" data-options="label:'状态:',required:true,validType:['maxLength[2]']">
					<option value="1" selected="selected">有效</option>
					<option value="0">无效</option>
				</select>
				<div class="form-tips">最大输入2个字符</div>
			</div>
			<div class="btns">
				<a style="margin-right: 15px;" class="easyui-linkbutton"
					onclick="ok();">保存</a> <a class="easyui-linkbutton normalBtn"
					onclick="cancel()">取消</a>
			</div>
		</form>
	</div>

	<!--  -->



	<div id="mmenu" class="easyui-menu" style="width: 120px;">
		<div id="mm_edit" iconCls="icon-edit">修改</div>
		<div class="menu-sep"></div>
		<div id="mm_del" iconCls="icon-cut">删除</div>
	</div>
</body>

</html>