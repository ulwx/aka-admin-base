<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色管理</title>
<jsp:include page="/head.jsp" flush="true"></jsp:include>
<style>
.form-item__content {
	width: 350px;
}

</style>
<script>


	
	$(document).ready(function(){
		$("body").css("visibility", "visible");
		//先隐藏窗口
		$('#win').window({
			width: 550,
			height:300,
			closed: true,
			resizable: false,
			minimizable: false,
			maximizable: false
		});;
		init();		
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
			url:'<%=request.getContextPath()%>/sys/sys_SysRole_listJSON.action',
			idField:'sysRoleSno',
			frozenColumns:[[
                {field:'ck',checkbox:true},
                {title:'角色ID',field:'sysRoleSno',width:80, align: 'center'}
			]],
			columns:[
			[
				{field:'roleName',title:'角色名称',width:100, align: 'center'},
				{field:'description',title:'角色说明',width:160, 
					formatter:function(value,rec){
						 return '<div style="word-wrap:  break-word; overflow:hidden; ">'
	      				 	+ value + '</div>';
		        	}
				},
				{field:'updateTime',title:'更新时间',width:100, align: 'right'},
				{field:'updator',title:'更新人',width:100, align: 'center'}
			]],
			pagination:true,
			rownumbers:true,
			pageNumber:1,

			//增加右键菜单显示
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
		$('#win').window('open');
		$('#win').window('center');
		loadSysRight();
		clearEasyUiInvalidTip("#form2");
	}
	//###########删除数据
	function deleteData(value){
		$.ajax({
			type: "POST",
			dataType: "text",
			url: "<%=request.getContextPath()%>/sys/sys_SysRole_deleteJSON.action",
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
					$.messager.alert("提示", "删除数据失败!"+data.message);
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
			url: "<%=request.getContextPath()%>/sys/sys_SysRole_getOneDataJSON.action?sno=" + value,
 				success: function(data){
 					data = $.trim(data);
 					clear();
					var jsonObject = eval('(' + data + ')');
 					if(jsonObject && jsonObject.status==1){
 						
 						
						var editObj = jsonObject.data;
						var roleInfo=editObj.roleInfo;
						var rightList=editObj.rightList;
					
 						$('#form2').form('load',roleInfo);
 				  		$("#hiddenCode").val(roleInfo.sysRoleSno);
 				  	
 				  		$('#win').window('open');
 						$('#win').window('center');
 					
						loadSysRight(rightList);

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
	function cancel(type){
   		$('#win').window('close');
	}
	//###################处理提交
	
	function reloadGrid(){
		$("#tt2").datagrid("reload");
	}
	function ok(){


 		var sno = $.trim($("#hiddenCode").val());
 	  	var url = "<%=request.getContextPath()%>/sys/sys_SysRole_insertJSON.action";
 	  	if(sno && sno != ""){
  			url = "<%=request.getContextPath()%>/sys/sys_SysRole_updateJSON.action";
 	  	}
  	
  		
  		$("body").showLoading();
    	$('#form2').form('submit', {
    	    url:url,
    	    onSubmit: function(param){
    	    	if(sno && sno!=''){
    	    		param.sno=sno;
    	    	}
    	    	 var ret= $(this).form('enableValidation').form('validate');
    	    	 if(!ret){
    	    		 $("body").hideLoading(); 
    	    	 }
    	    	 return ret;
    	    },
    	    success:function(data){
    	    	$("body").hideLoading();
    	   
    	    	var ret=$.evalJSON(data);
    	 
    	    	
    	        if(ret && ret.status==1){
    	        	$.messager.alert("提示",ret.message,"info",function(){
    	        		reloadGrid();//重新刷新后端表格
    	        		cancel();
    	        	});
    	        }else{
    	        	$.messager.alert("提示","操作失败！["+ret.message+"]","error",function(){
    	        	});
    	        }
    	    }
    	});
			
	}
	
	
    function getQueryParm(){
 	   var parmObject =$("#form1").serializeObject();
     	return parmObject;
    }
    //查询用户数据
    function lookup() {
         
  		$('#tt2').datagrid("load",getQueryParm());
        
     }

	//清理编辑页面的值
	function clear(){
		roleName = null;
		$('#win #form2').form("clear");

	   	
	}
	
	//修改函数
	function editFn(){
		
		var selections = $('#tt2').mydatagrid("getChecked");
		if(selections.length > 1){
			var msg = "";
			for(var i=0; i < selections.length; i++){
				if(i > 0)
					msg += ",";
				msg += selections[i].sysRoleSno;
			}
			$.messager.alert("提示", "至多选择修改一项!!您已勾选ID为" + msg);
			return;
		}
		if(selections.length < 1){
			$.messager.alert("提示", "请选择要修改哪一项!!");
			return;
		}
		clear();
		loadEdit(selections[0].sysRoleSno);
	}
	//删除函数
	function delFn(){
		var selections = $('#tt2').mydatagrid("getChecked");
		if(selections.length < 1){
			$.messager.alert("提示", "请选择需要删除的项目!!");
			return;
		}
		var msg = "";
		for(var i=0; i < selections.length; i++){
			if(i > 0)
				msg += ",";
			msg += selections[i].sysRoleSno;
		}
		$.messager.confirm("提示", "确定删除吗?", function(b){
			if(b)deleteData(msg);
		});
	}
	
	
	function loadSysRight(value){
		var multiple=true;
	    
		$("#sysRight").combotree({  
		    url:'<%=request.getContextPath()%>/sys/sys_SysRole_getSysRightsJSON.action',
			multiple : multiple,
			checkbox : true,
			loadFilter : function(data) {

				if (data.status == 1) {//判断是否成功
					return data.data;
				} else {
					$.messager.alert("提示", data.message,
							"error");
					return [];
				}

			},
			editable : false,
			onLoadSuccess : function() {
				if (value != null) {
					if (multiple) {
						if (isArray(value)) {
							$("#sysRight").combotree(
									"setValues", value);
						} else {
							$("#sysRight").combotree(
									"setValues", [ value ]);
						}
					} else {
						$("#sysRight").combotree("setValue",
								value);
					}
				}
			}
		});
	}
	function init() {
		//初始化功能菜单

	}
</script>
</head>
<body class="easyui-layout commonPage" style="visibility: hidden;">
	<div region="center" border="false" title="角色管理"
		data-options="headerCls:'headerClass'">
		<div id="top" class="tools-box">
			<div class="search-box">
				<form class="searchForm" id="form1">
					<div class="search-params">
						<input class="easyui-textbox" type="text" name="sRoleName"
							id="sRoleName" style="width: 120px;" data-options="prompt:'角色名称'" />
							
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
	<div id="win" class="easyui-window" title="编辑角色"
		data-options="closed:true">
		<form class="commonForm" id="form2">

			<div class="commonForm-items">
				<input class="easyui-textbox" type="text" name="roleName"
					id="roleName" data-options="label:'角色名称:',required:true,validType:'maxLength[20]'"
					style="width: 100%" /> <input type="hidden" name="hiddenCode"
					id="hiddenCode" />
					<div class="form-tips">最大输入20个字符</div>
			</div>
			<div class="error-tips"></div>

			<div class="commonForm-items">

				<input id="sysRight" name="sysRight" class="easyui-combotree"
					data-options="label:'功能菜单:',required:true" style="width: 100%" />

			</div>
			<div class="commonForm-items">

				<input id="description" name="description" type="text"
					class="easyui-textbox"
					data-options="label:'说明:',required:false,multiline:true,height:70,validType:['maxLength[300]']"
					style="width: 100%" />
				<div class="form-tips">最大输入300个字符</div>
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