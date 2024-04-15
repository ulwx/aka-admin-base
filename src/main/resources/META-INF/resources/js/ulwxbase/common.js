function getUserName() {
	return window.top.$('.userName').html();
}

function loadCombotree(selector, value, url, multiple, title) {
	if (multiple == null || multiple == undefined) {
		multiple = true;
	}

	$(selector).combotree({
		url : url,
		multiple : multiple,
		prompt : title,

		onlyLeafCheck : multiple ? false : true,
		editable : true,
		loadFilter : function(data) {

			if (data.status == 1) {// 判断是否成功
				return data.data;
			} else {
				$.messager.alert("提示", data.message, "error");
				return [];
			}

		},
		onLoadSuccess : function() {
			if (value != null) {
				if (multiple) {
					if (isArray(value)) {
						$(selector).combotree("setValues", value);
					} else {
						$(selector).combotree("setValues", [ value ]);
					}
				} else {
					$(selector).combotree("setValue", value);
				}
			}
		}
	})
}

function loadCombobox(selector, url, cvalue, excludeFirst, options,insertFirst) {

	var opt = $.extend({}, {
		url : url,
		panelHeight : 160,
		valueField : 'id',
		textField : 'text',
		editable : true,
		limitToList : true,
		loadFilter : function(data) {

			if (data.status == 1) {// 判断是否成功
				if ($.type(data.data) == 'string') {
					data.data = $.parseJSON(data.data);
				}
				if (excludeFirst) {
					data.data.splice(0, 1);
				}
				if(insertFirst){
					data.data.splice(0,0,{id:"",text:"==请选择=="})
				}
				return data.data;
			} else {
				$.messager.alert("提示", data.message, "error");
				return [];
			}

		},
		onLoadSuccess : function() {
			if (!isNull(cvalue)) {
				var newArray = [];
				if (!isArray(cvalue)) {// 是否是array
					cvalue = [ cvalue ];
				} else {
					if (isArrayEmpty(cvalue)) {
						return;
					}
				}
				var data = $(selector).combobox("getData");
				var opts = $(selector).combobox("options");
				if (data) {
					for (var index = 0; index < data.length; index++) {
						var val = data[index][opts.valueField];
						if ($.type(cvalue[0]) == "string") {
							val = val + "";
						} else if ($.type(cvalue[0]) == "number") {
							val = val - 0;
						}
						var findIndex =-1;
						for(var f=0; f<cvalue.length; f++){
							var ss=cvalue[f];
							if ($.type(ss) == "string") {
								if(ss.toLowerCase()==val.toLowerCase()){
									newArray.push(val);
									findIndex=f;
									break;

								}
							}else if ($.type(ss) == "number") {
								val = val - 0;
								if(ss==val){
									newArray.push(val);
									findIndex=f;
									break;
								}
							}
						}
						if(findIndex>=0){
							cvalue.splice(f, 1)
						}
						if (cvalue.length == 0) {
							break;
						}

					}
				}
				$(selector).combobox("setValues", newArray);
			}

		}

	}, (options || {}));

	$(selector).combobox(opt);

}
function loadTagbox(selector, url, cvalue, excludeFirst, options,insertFirst) {

	var opt = $.extend({}, {
		url : url,
		panelHeight : 160,
		valueField : 'id',
		textField : 'text',
		editable : true,
		limitToList : true,
		loadFilter : function(data) {

			if (data.status == 1) {// 判断是否成功
				if ($.type(data.data) == 'string') {
					data.data = $.parseJSON(data.data);
				}
				if (excludeFirst) {
					data.data.splice(0, 1);
				}
				if(insertFirst){
					data.data.splice(0,0,{id:"",text:"==请选择=="})
				}
				return data.data;
			} else {
				$.messager.alert("提示", data.message, "error");
				return [];
			}

		},
		onLoadSuccess : function() {
			if (!isNull(cvalue)) {
				var newArray = [];
				if (!isArray(cvalue)) {// 是否是array
					cvalue = [ cvalue ];
				} else {
					if (isArrayEmpty(cvalue)) {
						return;
					}
				}
				var data = $(selector).tagbox("getData");
				var opts = $(selector).tagbox("options");
				if (data) {
					for (var index = 0; index < data.length; index++) {
						var val = data[index][opts.valueField];
						if ($.type(cvalue[0]) == "string") {
							val = val + "";
						} else if ($.type(cvalue[0]) == "number") {
							val = val - 0;
						}
						var findIndex =-1;
						for(var f=0; f<cvalue.length; f++){
							var ss=cvalue[f];
							if ($.type(ss) == "string") {
								if(ss.toLowerCase()==val.toLowerCase()){
									newArray.push(val);
									findIndex=f;
									break;

								}
							}else if ($.type(ss) == "number") {
								val = val - 0;
								if(ss==val){
									newArray.push(val);
									findIndex=f;
									break;
								}
							}
						}
						if(findIndex>=0){
							cvalue.splice(f, 1)
						}
						if (cvalue.length == 0) {
							break;
						}

					}
				}
				$(selector).tagbox("setValues", newArray);
			}

		}

	}, (options || {}));

	$(selector).tagbox(opt);

}
function selectRec(datagridSelector, url, reloadGrid, title, data, width,
		height) {
	var records = $(datagridSelector).datagrid('getChecked');
	// console.log(records);
	if (!records.length) {
		$.messager.alert("提示", "请勾选内容", "info");
		return false;
	}

	easyui.ext.open({
		width : width && width > 0 ? width : 680,
		height : height && height > 0 ? height : 550,
		title : title && title != '' ? title : '操作',
		self : false,
		autoHeight : true,
		content : 'url:' + url,
		resizable : true,
		modal : true,
		shadow : true,
		onLoad : function(dlg) {
			dlg.reloadGrid = reloadGrid;//
			dlg.selectRecs = records;//
			dlg.data = data;
			var win = dlg.window;
			win["init"](dlg);// 调用远程的js的init方法，参数为当前的dlg对象
		}
	});

}

/**
 * @param type :
 *            1：项目相关文件 2：用户相关文件
 * @param ftype :
 *            1：图片 2：文件
 * @param id :
 *            如果type=1，则为项目id；如果type=2，则为用户id
 * @Param callback(data,status,errorMsg)
 *            data:为返回的对象，对象里的属性如下： relaFilePath: 文件服务器相对路径 httpPath:
 *            文件服务器http绝对地址 ossPath: 阿里云oss相对路径, ossHttpPath:
 *            阿里云oss相对路径的http绝对地址 status: 1:成功 0：失败； 只有成功时data才有意义
 *            errorMsg：如果status为0时，错误提示信息
 * @returns
 */
function openUpFileDlg(type,dir, ftype, id,userid,callback) {
	var url = $.ContexPath + "/jsp/common/fileup/up.jsp";
	var data = {
		'type' : type,
		'dir' : dir,
		'ftype' : ftype,
		'id' : id,
		'userid':userid,
		'callback' : callback
	};
	addRec(url, null, '上传文件', data, 540, 210);
}

function openUpMultiFileDlg(type,dir, ftype, id,userid, callback) {
	var url = $.ContexPath + "/jsp/common/fileup/upMany.jsp";
	var data = {
		'type' : type,
		'dir' : dir,
		'ftype' : ftype,
		'id' : id,
		'userid':userid,
		'callback' : callback
	};
	addRec(url, null, '上传文件', data, 540, 210);
}
/**
 * @Param callback(data,status,errorMsg)
 *            data:为返回的对象，对象里的属性如下： relaFilePath: 文件服务器相对路径 httpPath:
 *            文件服务器http绝对地址 ossPath: 阿里云oss相对路径, ossHttpPath:
 *            阿里云oss相对路径的http绝对地址 status: 1:成功 0：失败； 只有成功时data才有意义
 *            errorMsg：如果status为0时，错误提示信息
 * @returns
 */
function openUpActivityDlg(callback) {
	var url = $.ContexPath + "/jsp/common/fileup/upActivity.jsp";
	var data = {
		'callback' : callback
	};
	addRec(url, null, '上传文件', data, 540, 200);
}


function editRec(datagridSelector, url, reloadGrid, title, data, width, height,options) {
	var records = $(datagridSelector).datagrid('getChecked');
	// console.log(records);
	if (!records.length) {
		$.messager.alert("提示", "请勾选内容", "info");
		return false;
	}
	if (records.length > 1) {
		$.messager.alert("提示", "修改只能修改行数据", "info");
		return false;
	}

	var opt = $.extend({},{
		width : width && width > 0 ? width : 680,
		height : height && height > 0 ? height : 550,
		title : title && title != '' ? title : '修改记录',
		self : false,
		autoHeight : false,
		content : 'url:' + url,
		resizable : true,
		modal : true,
		shadow : true,
		onLoad : function(dlg) {
			dlg.reloadGrid = reloadGrid;//
			dlg.updateRec = records[0];// 修改的时候
			dlg.data = data;
			var win = dlg.window;
			win["init"](dlg);// 调用远程的js的init方法，参数为当前的dlg对象
		}
	}, (options || {}));

	easyui.ext.open(opt);

}
function editRec2(datagridSelector, url, reloadGrid, title, data, row, width,
		height,options) {
	var opt = $.extend({},{
		width : width && width > 0 ? width : 680,
		height : height && height > 0 ? height : 500,
		title : title && title != '' ? title : '修改记录',
		self : false,
		autoHeight : false,
		content : 'url:' + url,
		resizable : true,
		modal : true,
		shadow : true,
		onLoad : function(dlg) {
			dlg.reloadGrid = reloadGrid;//
			dlg.updateRec = row;// 修改的时候
			dlg.data = data;
			var win = dlg.window;
			win["init"](dlg);// 调用远程的js的init方法，参数为当前的dlg对象
		}
	}, (options || {}));

	easyui.ext.open(opt);

}
function showHtmlDialog(html,width,height) {
	$('<div>' +html+ '</div>').dialog({
		title: '<%=MM.M("feelist.ts")%>',
		width: width&&width>0?width:600,
		height: height&&height>0?height:600,
		closed: false,
		cache: false,
		modal: true,
		onBeforeOpen: function () {
		},
		onClose: function () {
			$(this).dialog('destroy'); // 关闭时销毁对话框
		}
	});
}
// 跳转到新增用户页面
function addRec(url, reloadGrid, title, data, width, height, options) {
	var opt = $.extend({}, {
		width : width && width > 0 ? width : 680,
		height : height && height > 0 ? height : 550,
		title : title && title != '' ? title : '新增记录',
		self : false,
		autoHeight : false,
		content : 'url:' + url,
		resizable : true,
		modal : true,
		shadow : true,
		onLoad : function(dlg) {
			dlg.reloadGrid = reloadGrid;
			dlg.data = data;
			var win = dlg.window;
			win["init"](dlg);// 调用远程的js的init方法，参数为当前的dlg对象
		}
	}, (options || {}));
	easyui.ext.open(opt);
}

function operRec(datagridSelector, url, reloadGrid) {
	var records = $(datagridSelector).datagrid('getChecked');
	if (!records.length) {
		$.messager.alert("提示", "请勾选内容", "info");
		return false;
	}

	$("body").showLoading();

	var parms = {
		selInfos : $.toJSON(records)
	};
	asynPostParam(url, parms, function(data) {
		$("body").hideLoading();

		data = $.evalJSON(data);
		if (data) {
			if (data.status == 1) {
				$.messager.alert("提示", "操作成功", "info", function() {
					if (reloadGrid) {
						reloadGrid();
					}
				});
			} else {
				$.messager.alert("提示", data.message, "info");
			}

		} else {
			$.messager.alert("提示", "操作失败，请联系管理员！", "info", function() {

			});
		}
	});

}
function delRec(url,deleteId,reloadGrid){
	$.ajax({
		type: "POST",
		dataType: "text",
		url: url,
		data: {"deleteId":deleteId},
		success: function(data){
			data = $.trim(data);
			data=$.evalJSON(data);
			if(data && data.status==1){
				$.messager.alert("提示", "删除成功！！", "info", function() {
					if (reloadGrid) {
						reloadGrid();
					}
				});
			}else{
				$.messager.alert("提示", data.message);
				return;
			}
		},
		error: function(){
			$.messager.alert("提示", "网络连接失败!");
		}
	});

}
function initDataGrid(selector, url, queryParams, columns, options) {

	var opt = $.extend({}, {
		queryParams : queryParams,
		url : url,
		striped : true,
		columns : columns,
		border : false,
		nowrap : false,
		remoteSort : false,
		multiSort : false,
		fitColumns : true,
		pagination : true,// 表示在datagrid设置分页
		rownumbers : true,
		scrollOnSelect:false,
		singleSelect : false,
		toolbar : "#top",
		fit : true,
		onBeforeSortColumn : function(sort, order) {
		},
		loadFilter : function(data) {
			if (data.data) {
				if (data.status == 1) {
					return data.data;
				} else {
					$.messager.alert('提示', data.message, 'error');
					return {};
				}
			} else {
				return data;
			}
		}
	}, (options || {}));
	$(selector).mydatagrid(opt);
}

function getQueryParm(selector, otherParm) {
	var parmObject = $(selector).serializeObject();
	var result = $.extend({}, parmObject, otherParm)
	return result;
}

// alert(getAge("1980-03-22 10:1:2", "1982-03-22 10:1:2"));
// alert(getAge("1980-06-29", "1984-02-03"));
// alert(getAge("1980-03-22 10:1:2", "1980-03-23 9:1:1"));
// alert(getAge("1981-02-28 10:1:2", "1981-03-01 10:1:2"));
function getDate(date) {
	var myDate
	if (date) {
		myDate = date;
	} else {
		myDate = new Date();
	}

	var year = myDate.getFullYear() + "";
	var month = myDate.getMonth();
	month = month + 1;
	month = month + "";
	if (month.length == 1) {
		month = "0" + month;
	}
	var day = myDate.getDate();
	day = day + "";
	if (day.length == 1) {
		day = "0" + day;
	}
	return year + "-" + month + "-" + day;
}

function getDate(date) {
	var myDate
	if (date) {
		myDate = date;
	} else {
		myDate = new Date();
	}

	var year = myDate.getFullYear() + "";
	var month = myDate.getMonth();
	month = month + 1;
	month = month + "";
	if (month.length == 1) {
		month = "0" + month;
	}
	var day = myDate.getDate();
	day = day + "";
	if (day.length == 1) {
		day = "0" + day;
	}
	return year + "-" + month + "-" + day;
}

function getAge(beginStr, endStr) {

	if (beginStr == null || beginStr == '0001-01-01' || endStr == null
			|| endStr == '0001-01-01') {
		return "";
	}
	var reg = new RegExp(
			/^(\d{1,4})(-|\/)(\d{1,2})(-|\/)(\d{1,2})((\s)(\d{1,2})(:)(\d{1,2})(:{0,1})(\d{0,2}))?$/);
	var beginArr = beginStr.match(reg);
	var endArr = endStr.match(reg);

	var days = 0;
	var month = 0;
	var year = 0;

	days = endArr[5] - beginArr[5];
	if (days < 0) {
		month = -1;
		days = 30 + days;
	}

	month = month + (endArr[3] - beginArr[3]);
	if (month < 0) {
		year = -1;
		month = 12 + month;
	}

	year = year + (endArr[1] - beginArr[1]);

	var yearString = year > 0 ? year + "岁" : "";
	var mnthString = month > 0 ? month + "月" : "";
	var dayString = days > 0 ? days + "天" : "";

	/*
	 * 1 如果岁 大于等于1 那么年龄取 几岁 2 如果 岁等于0 但是月大于1 那么如果天等于0 天小于3天 取小时 例如出生2天 就取 48小时
	 */
	var result = "";
	if (year >= 1) {
		result = yearString + mnthString;
	} else {
		if (month >= 1) {
			result = days > 0 ? mnthString + dayString : mnthString;
		} else {
			if (!beginArr[8]) {
				beginArr[8] = 0;
			}
			if (!beginArr[10]) {
				beginArr[10] = 0;
			}
			if (!beginArr[12]) {
				beginArr[12] = 0;
			}

			if (!endArr[8]) {
				endArr[8] = 0;
			}
			if (!endArr[10]) {
				endArr[10] = 0;
			}
			if (!endArr[12]) {
				endArr[12] = 0;
			}
			var begDate = new Date(beginArr[1], beginArr[3] - 1, beginArr[5],
					beginArr[8], beginArr[10], beginArr[12]);
			var endDate = new Date(endArr[1], endArr[3] - 1, endArr[5],
					endArr[8], endArr[10], endArr[12]);

			var between = (endDate.getTime() - begDate.getTime()) / 1000;
			days = Math.floor(between / (24 * 3600));
			var hours = Math.floor(between / 3600 - (days * 24));
			var dayString = days > 0 ? days + "天" : "";
			result = days >= 3 ? dayString : days * 24 + hours + "小时";
		}
	}

	return result;
}

function err(target, message) {
	var t = $(target);
	if (t.hasClass('textbox-text')) {
		t = t.parent();
	}
	var m = t.next('.error-message');
	if (!m.length) {
		m = $('<div class="error-message"></div>').insertAfter(t);
	}
	m.html(message);
}

var MaskUtil = (function() {

	var $mask, $maskMsg;
	var defMsg = '正在处理，请稍待。。。';

	function init() {
		if (!$mask) {
			$mask = $(
					"<div class=\"datagrid-mask mymask\" style='height:40px'></div>")
					.appendTo("body");
		}
		if (!$maskMsg) {
			$maskMsg = $(
					"<div class=\"datagrid-mask-msg mymask\" style='height:40px'>"
							+ defMsg + "</div>").appendTo("body").css({
				'font-size' : '10px'
			});
		}

		$mask.css({
			width : "100%",
			height : $(document).height()
		});
		var scrollTop = $(document.body).scrollTop();

		$maskMsg.css({
			left : ($(document.body).outerWidth(true) - 190) / 2,
			top : (($(window).height() - 45) / 2) + scrollTop
		});
	}

	return {
		mask : function(msg) {
			init();
			$mask.show();
			$maskMsg.html(msg || defMsg).show();
		},
		unmask : function() {
			$mask.hide();
			$maskMsg.hide();
		}
	}
}());
