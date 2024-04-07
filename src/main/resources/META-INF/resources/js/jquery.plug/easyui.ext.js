/*******************************************************************************
 * 判断是否为非easyui输入控件
 */

function isEasyuiInput(selector) {
	var _easyui_input_class_="";
	if ($(selector).is(":input")) {
		if ($(selector).is(_easyui_input_class_)) {
			return true;
		}
	}
	return false;
}

/*******************************************************************************
 * ajax全局控制
 */

function bindTimeOutEvent(linkbutton) {
	var opts = $(linkbutton).linkbutton("options");
	var timeout = 0;
	if (opts.timeout != undefined) {
		if (opts.timeout <= 0) {
			timeout = 0;
		} else {
			timeout = opts.timeout;
		}
	}
	if (timeout > 0) {
		setTimeout(function() {
			$(linkbutton).linkbutton("disable");
		}, 1);

		setTimeout(function() {
			$(linkbutton).linkbutton("enable");
		}, timeout);
	}
}

$(function() {
	// 全局的ajax访问，处理ajax清求时sesion超时
	$.ajaxSetup({
		cache : false,
		global : true,
		crossDomain : true,
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		timeout : 1000 * 60 * 15
	});

	/**
	 * 解决ajax重复提交的问题,此处可以全局作缓存
	 */
	var pendingRequests = {};

	function mySign(options) {
		if (options) {// 签名
			var str = options.url;
			var queryStr = "";
			if ((!str || str.indexOf("?") < 0)
					&& (!options.data || options.data == '')) {// 不用签名
				return;
			}

			var index = str.indexOf("?");

			if (index >= 0 && str.length - 1 > index) {
				queryStr = str.substr(index + 1);
			}
			if (options.data && options.data != ''
					&& (typeof options.data == "string")) {
				if (queryStr != '') {
					queryStr = queryStr + "&" + options.data
				} else {
					queryStr = options.data;
				}
			}
			var sign = "";
			var md5toStr = "";
			// console.log("queryStr="+queryStr);
			if (queryStr == '') {
				// console.log("md5toStr="+md5toStr+_global_token);
				sign = md5(md5toStr + _global_token);

				if (options.url.indexOf("?") > 0) {
					options.url = options.url + "&sign=" + sign;
				} else {
					options.url = options.url + "?sign=" + sign;
				}
				return;
			}

			var qo = parseSearchString(queryStr);
			// console.log("qo="+$.toJSON(qo));

			// 排序qo的键
			var keys = Object.keys(qo);
			// console.log("keys="+$.toJSON(keys));
			if (keys && isArray(keys) && keys.length > 0) {
				keys.sort();

				for (var index = 0; index < keys.length; index++) {
					var val = qo[keys[index]];
					if (isArray(val)) {
						val.sort();
						val = val.join(",");
					}
					md5toStr = md5toStr + "&" + keys[index] + "=" + val;
				}

				// console.log("md5toStr="+md5toStr+_global_token);
				sign = md5(md5toStr + _global_token);
				// console.log("sign="+sign);
				if (options.url.indexOf("?") > 0) {
					options.url = options.url + "&sign=" + sign;
				} else {
					options.url = options.url + "?sign=" + sign;
				}
			}

		}
	}
	jQuery.ajaxPrefilter(function(options, originalOptions, jqXHR) {
		// console.log(options);
		mySign(options);

		var complete = options.complete;
		options.complete = function(jqXHR, textStatus) {
			check_time_out(jqXHR);
			// pendingRequests[key] = null;
			if (jQuery.isFunction(complete)) {
				complete.apply(this, arguments);
			}
		};
		var success = options.success;
		options.success = function(data, textStatus, jqXHR) {
			check_time_out(jqXHR);
			// pendingRequests[key] = null;
			if (jQuery.isFunction(success)) {
				success.apply(this, arguments);
			}
		}
		var error = options.error;
		options.error = function(event, jqxhr, settings, thrownError) {
			check_time_out(jqXHR);
			// pendingRequests[key] = null;
			if (jQuery.isFunction(error)) {
				error.apply(this, arguments);
			}
		}
	});
	var isTimeOutAlert=false;
	function check_time_out(XMLHttpRequest) {
		if (XMLHttpRequest.getResponseHeader) {
			var sessionstatus = XMLHttpRequest
					.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，
			if (sessionstatus) {
				if (sessionstatus == "timeout") {
					// 如果超时就处理 ，指定要跳转的页面

					var xmlDoc = XMLHttpRequest.responseText;
					var ret = eval('(' + xmlDoc + ')');// $.evalJSON(xmlDoc);
					if(ret.content!=null) {
						///
					}else{
						ret=ret.data;
					}
					if(!isTimeOutAlert) {
						isTimeOutAlert=true;
						alert(ret.content);
						if (window.top) {
							window.top.location = ret.login;
						} else {
							window.location = ret.login;
						}
					}


				} else if (sessionstatus == "control") {
					// 如果访问控制就处理 ，指定要跳转的页面

					var xmlDoc = XMLHttpRequest.responseText;
					var ret = eval('(' + xmlDoc + ')');// $.evalJSON(xmlDoc);
					if(ret.content!=null) {
						///
					}else{
						ret=ret.data;
					}
					alert(ret.content);
					if (ret.exit == 1) {
						if (window.top) {
							window.top.location = ret.login;
						} else {
							window.location = ret.login;
						}
					}
				}
			}
			return false;
		}
		return true;
	}

});

(function($) {
	if ($.validatebox) {
		$.extend($.fn.validatebox.defaults.rules, {
			validateOnCreate : true
		});
	}

	if ($.messager) {
		$.extend($.messager.defaults, {
			width : 400,
			height : 'auto',
			minHeight : 200,
			modal : true

		});
		if ($.messager.show) {
			var show = $.messager.show;
			$.messager.show = function(options) {
				if (!options.timeout) {
					options.timeout = 4000;
				} else {
					options.timeout = 4000;
				}
				return show(options);
			}
		}
		;
		if ($.messager.alert) {
			var alert2 = $.messager.alert;
			$.messager.alert = function(title, msg, icon, fn) {
				if (!icon || icon == "") {
					icon = "info";
				}
				return alert2(title, msg, icon, fn);
			}
		}
		;

		if ($.messager.confirm) {
			var confirm2 = $.messager.confirm;
			$.messager.confirm = function(title, msg, fn) {
				return confirm2({
					'title' : title,
					'msg' : msg,
					'fn' : fn,
					'ok' : '是',
					'cancel' : '否'
				});
			}
		}

		if ($.messager) {
			// $.messager.defaults.ok = '是';
			// $.messager.defaults.cancel = '否';
		}
	}
	/** *改写默认控件的高度** */
	var my_default_height = 26;
	if ($.fn.textbox)
		$.fn.textbox.defaults.height = my_default_height;


	if ($.fn.numberbox)
		$.fn.numberbox.defaults.height = my_default_height;
	if ($.fn.datebox)
		$.fn.datebox.defaults.height = my_default_height;
	if ($.fn.datetimebox)
		$.fn.datetimebox.defaults.height = my_default_height;
	if ($.fn.filebox)
		$.fn.filebox.defaults.height = my_default_height;
	if ($.fn.combotree)
		$.fn.combotree.defaults.height = my_default_height;// combogrid
	if ($.fn.combogrid)
		$.fn.combogrid.defaults.height = my_default_height;// passwordbox
	if ($.fn.combotreegrid)
		$.fn.combotreegrid.defaults.height = my_default_height;
	if ($.fn.passwordbox)
		$.fn.passwordbox.defaults.height = my_default_height;// numberspinner
	if ($.fn.datetimespinner)
		$.fn.datetimespinner.defaults.height = my_default_height;
	if ($.fn.numberspinner)
		$.fn.numberspinner.defaults.height = my_default_height;
	if ($.fn.timespinner)
		$.fn.timespinner.defaults.height = my_default_height;

	/**
	 * 1.修复linkbutton的disable和enable不对称触发时，引起的href事件丢失的问题
	 * 2.对linkbutton增加timeout属性，指定多长时间解禁按钮，默认1000秒
	 *
	 */
	if($.fn.combobox){
		$.extend($.fn.linkbutton.methods, {

		});
	}
	if ($.fn.linkbutton) {
		$.fn.linkbutton.defaults.height = my_default_height;
		function setDisabled(target, disabled) {
			var state = $.data(target, 'linkbutton');
			var opts = state.options;
			$(target).removeClass('l-btn-disabled l-btn-plain-disabled');
			if (disabled) {
				opts.disabled = true;

				var href = $(target).attr('href');
				if (href) {
					if (!state.href) {
						state.href = href;
					}
					$(target).attr('href', 'javascript:void(0)');
				}
				if (target.onclick) {
					if (!state.onclick) {
						state.onclick = target.onclick;
					}
					target.onclick = null;
				}
				opts.plain ? $(target).addClass(
						'l-btn-disabled l-btn-plain-disabled') : $(target)
						.addClass('l-btn-disabled');
			} else {
				opts.disabled = false;
				if (state.href) {
					$(target).attr('href', state.href);
				}
				if (state.onclick) {
					target.onclick = state.onclick;
				}
			}
		}

		$.extend($.fn.linkbutton.methods, {
			enable : function(jq) {
				return jq.each(function() {
					setDisabled(this, false);
				});
			},
			disable : function(jq) {
				return jq.each(function() {
					setDisabled(this, true);
				});
			}
		});

		$(function() {
			$(".easyui-linkbutton").bind('click.my', function() {
				bindTimeOutEvent(this);
			});
		});

	}

	if ($.fn.layout) {
		$.fn.layout.paneldefaults.border = false;
	}
	;

	/**
	 * datagrid默认的分页
	 */
	if ($.fn.datagrid) {
		$.fn.datagrid.defaults.loadMsg = '正在处理，请稍待。。。';
		/* 修改分页信息 by liujp */
		$.fn.datagrid.defaults.pageSize = 20;
		$.fn.datagrid.defaults.pageList = [ 20, 50, 100 ];
		$.fn.datagrid.defaults.border = false;
	}
	;
	if ($.fn.combo) {
		$.fn.combobox.defaults.panelHeight = 120;
		$.fn.combobox.defaults.limitToList = true;
		$.fn.combobox.defaults.height=26;
	}
	;




	var ie = (function() {
		var undef, v = 3, div = document.createElement('div'), all = div
				.getElementsByTagName('i');
		while (div.innerHTML = '<!--[if gt IE ' + (++v)
				+ ']><i></i><![endif]-->', all[0])
			;
		return v > 4 ? v : undef;
	}());
	/**
	 * add by cgh 针对panel window dialog三个组件调整大小时会超出父级元素的修正
	 * 如果父级元素的overflow属性为hidden，则修复上下左右个方向 如果父级元素的overflow属性为非hidden，则只修复上左两个方向
	 *
	 * @param width
	 * @param height
	 * @returns
	 */
	var easyuiPanelOnResize = function(width, height) {
		if (!$.data(this, 'window') && !$.data(this, 'dialog'))
			return;

		if (ie === 8) {
			var data = $.data(this, "window") || $.data(this, "dialog");
			if (data.pmask) {
				var masks = data.window.nextAll('.window-proxy-mask');
				if (masks.length > 1) {
					$(masks[1]).remove();
					masks[1] = null;
				}
			}
		}
		if ($(this).panel('options').maximized == true) {
			$(this).panel('options').fit = false;
		}
		$(this).panel('options').reSizing = true;
		if (!$(this).panel('options').reSizeNum) {
			$(this).panel('options').reSizeNum = 1;
		} else {
			$(this).panel('options').reSizeNum++;
		}
		var parentObj = $(this).panel('panel').parent();
		var left = $(this).panel('panel').position().left;
		var top = $(this).panel('panel').position().top;

		if ($(this).panel('panel').offset().left < 0) {
			$(this).panel('move', {
				left : 0
			});
		}
		if ($(this).panel('panel').offset().top < 0) {
			$(this).panel('move', {
				top : 0
			});
		}

		if (left < 0) {
			$(this).panel('move', {
				left : 0
			}).panel('resize', {
				width : width
			});
		}
		if (top < 0) {
			$(this).panel('move', {
				top : 0
			}).panel('resize', {
				height : height
			});
		}

		$(this).panel('options').reSizing = false;
	};
	/**
	 * add by cgh 针对panel window dialog三个组件拖动时会超出父级元素的修正
	 * 如果父级元素的overflow属性为hidden，则修复上下左右个方向 如果父级元素的overflow属性为非hidden，则只修复上左两个方向
	 *
	 * @param left
	 * @param top
	 * @returns
	 */
	var easyuiPanelOnMove = function(left, top) {
		if ($(this).panel('options').reSizing)
			return;
		var parentObj = $(this).panel('panel').parent();
		var width = $(this).panel('options').width;
		var height = $(this).panel('options').height;
		var right = left + width;
		var buttom = top + height;
		var parentWidth = parentObj.width();
		var parentHeight = parentObj.height();

		if (left < 0) {
			$(this).panel('move', {
				left : 0

			});

		}
		if (top < 0) {
			$(this).panel('move', {
				top : 0
			});
		}

	};
	if ($.fn.window) {
		$.fn.window.defaults.onResize = easyuiPanelOnResize;
		$.fn.window.defaults.onMove = easyuiPanelOnMove;
	}

	if ($.fn.dialog) {
		$.fn.dialog.defaults.onResize = easyuiPanelOnResize;
		$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
	}

})(jQuery);

if ($.fn.validatebox) {
	$.extend($.fn.validatebox.defaults.rules,
	{
		minLength : { // 判断最小长度
			validator : function(value, param) {
				return value.length >= param[0];
			},
			message : '最少输入 {0} 个字符。'
		},
		maxLength : { // 判断最大长度
			validator : function(value, param) {
				return value.length <= param[0];
			},
			message : '最大输入 {0} 个字符。'
		},
		length : {
			validator : function(value, param) {
				var len = $.trim(value).length;
				return len >= param[0] && len <= param[1];
			},
			message : "输入内容长度必须介于{0}和{1}之间."
		},
		phone : {// 验证电话号码
			validator : function(value) {
				return /^(\d{3,4}\-)?\d{6,8}\-?\d+$/i.test(s);
			},
			message : '格式不正确,请使用下面格式:020-12345678'
		},
		mobile : {// 验证手机号码
			validator : function(value) {
				return /^(\+)?(86)?0?1\d{10}$/i.test(value);
			},
			message : '手机号码格式不正确'
		},
		idcard : {// 验证身份证
			validator : function(value) {
				return /^\d{15}(\d{2}[A-Za-z0-9])?$/i
						.test(value);
			},
			message : '身份证号码格式不正确'
		},
		intOrFloat : {// 验证整数或小数
			validator : function(value) {
				return /^\d+(\.\d+)?$/i.test(value);
			},
			message : '请输入数字，并确保格式正确'
		},
		intOrFloatWithPoint : {// 验证整数或小数
			validator : function(value, param) {
				return /^\d+(\.\d{1,2})?$/i.test(value);
			},
			message : '请输入数字，并确保格式正确'
		},
		interestRate : {// 验证利率 2位正整数
			validator : function(value) {
				/* return /^[1-9][0-9]?$/i.test(value); */
				return /^[0-9][0-9]?([.][0-9]{1,2})?$/i
						.test(value);
			},
			message : '格式为1到2位正整数或保留最多2位小数!'
		},
		currency : {// 验证货币
			validator : function(value) {
				return /^\d+(\.\d+)?$/i.test(value);
			},
			message : '货币格式不正确'
		},
		money : {// 验证金额 非0开头的金额
			validator : function(value, param) {
				var patern = "^[0-9]([0-9]{0," + (param[0] - 1)
						+ "})$";
				var m = new RegExp(patern);
				if (!m.test(value)) {
					$.fn.validatebox.defaults.rules.money.message = "不大于"
							+ param[0] + "位正整数!";
				}
				return m.test(value);
			},
			message : ''
		},
		qq : {// 验证QQ,从10000开始
			validator : function(value) {
				return /^[1-9]\d{4,9}$/i.test(value);
			},
			message : 'QQ号码格式不正确'
		},
		zeroNineNum : {// 验证0-9的数字
			validator : function(value) {
				return /^[0-9]+$/i.test(value);
			},
			message : '请输入0-9之间的数字'
		},
		integer : {// 验证整数
			validator : function(value) {
				return /^-?\d+$/i.test(value);
			},
			message : '请输入整数'
		},
		positiveInteger : {// 验证正整数
			validator : function(value) {
				return /^[+]?[1-9]+\d*$/i.test(value);
			},
			message : '请输入正整数'
		},
		chinese : {// 验证中文
			validator : function(value) {
				return /^[\u0391-\uFFE5]+$/i.test(value);
			},
			message : '请输入中文'
		},
		english : {// 验证英语
			validator : function(value) {
				return /^[A-Za-z]+$/i.test(value);
			},
			message : '请输入英文'
		},
		normal : {// 验证普通字符,包括英文数字中划线下划线
			validator : function(value) {
				return /^[a-zA-Z0-9_-]{1,40}$/i.test(value);
			},
			message : '请勿输入英文数字中划线下划线之外的字符'
		},
		unnormal : {// 验证是否包含空格和非法字符
			validator : function(value) {
				return /.+/i.test(value);
			},
			message : '输入值不能为空和包含其他非法字符'
		},
		username : {// 验证用户名
			validator : function(value) {
				return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i
						.test(value);
			},
			message : '只允许字母开头，6-16字节，字母数字下划线'
		},
		realname : {// 验证真实名称,只能中文
			validator : function(value) {
				return /^[\u4E00-\u9FA5]+$/i.test(value);
			},
			message : '只允许中文'
		},
		content : {// 验证文本,包含中文、字母、数字
			validator : function(value) {
				return /^[\u4E00-\u9FA5A-Za-z0-9]+$/i
						.test(value);
			},
			message : '只允许中文、字母或数字'
		},
		multiText : {// 多条文本验证
			validator : function(value) {
				return /^((([\u4E00-\u9FA5A-Za-z0-9!@#$%^&*()_=《》<>.、，；?？;‘’\“”\"。,\-\+])*(\r*|\n*|\s*)?)*)?$/i
						.test(value);
			},
			message : '请勿输入特殊字符'
		},
		faxno : {// 验证传真
			validator : function(value) {
				return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i
						.test(value);
			},
			message : '传真号码不正确'
		},
		zip : {// 验证邮政编码
			validator : function(value) {
				return /^[1-9]\d{5}$/i.test(value);
			},
			message : '邮政编码格式不正确'
		},
		ip : {// 验证IP地址
			validator : function(value) {
				return /\d+.\d+.\d+.\d+/i.test(value);
			},
			message : 'IP地址格式不正确'
		},
		name : {// 验证姓名，可以是中文或英文
			validator : function(value) {
				return /^[\u0391-\uFFE5]+$/i.test(value)
						| /^\w+[\w\s]+\w+$/i.test(value);
			},
			message : '输入的值不能包含非法字符'
		},
		carNo : {
			validator : function(value) {
				return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/
						.test(value);
			},
			message : '车牌号码无效（例：粤J12350）'
		},
		carenergin : {
			validator : function(value) {
				return /^[a-zA-Z0-9]{16}$/.test(value);
			},
			message : '发动机型号无效(例：FG6H012345654584)'
		},
		email : {
			validator : function(value) {
				return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
						.test(value);
			},
			message : '请输入有效的电子邮件账号(例：abc@126.com)'
		},
		msn : {
			validator : function(value) {
				return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
						.test(value);
			},
			message : '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
		},
		startDate : {
			validator : function(value, param) {
				var s = $("#" + param[0]).combobox("getValue");
				if (s != "" && value != "") {
					return s >= value;
				} else {
					return true;
				}
			},
			message : '开始日期不能大于结束日期！'
		},
		endDate : {
			validator : function(value, param) {
				var s = $("#" + param[0]).combobox("getValue");
				if (s != "" && value != "") {
					return s <= value;
				} else {
					return true;
				}
			},
			message : '结束日期不能小于开始日期！'
		},
		same : {
			validator : function(value, param) {
				if ($("#" + param[0]).val() != ""
						&& value != "") {
					return $("#" + param[0]).val() == value;
				} else {
					return true;
				}
			},
			message : '两次输入的密码不一致！'
		},
		notsame : {
			validator : function(value, param) {
				if ($("#" + param[0]).val() != ""
						&& value != "") {
					return !($("#" + param[0]).val() == value);
				} else {
					return true;
				}
			},
			message : '新密码和旧密码一致！'
		},
		account : {// param的值为[]中值
			validator : function(value, param) {
				if (value.length < param[0]
						|| value.length > param[1]) {
					$.fn.validatebox.defaults.rules.account.message = '用户名长度必须在'
							+ param[0] + '至' + param[1] + '范围';
					return false;
				} else {
					if (!/^[\w]+$/.test(value)) {
						$.fn.validatebox.defaults.rules.account.message = '用户名只能数字、字母、下划线组成.';
						return false;
					} else {
						return true;
					}
				}
			},
			message : ''
		},
		xiaoshu : { // 校验小数后n位
			validator : function(value, param) {
				var patern = "^(([1-9]{1}[0-9]*)|([0-9]+\.[0-9]{1,"
						+ param[0] + "}))$";
				var hh = new RegExp(patern);
				if (!hh.test(value)) {
					$.fn.validatebox.defaults.rules.xiaoshu.message = "最多保留"
							+ param[0] + "位小数！";
				}
				return hh.test(value);
			},
			message : ''
		},
		password : {// 密码验证
			validator : function(value) {
				var patern = /^(?![0-9]+$)(?![a-zA-Z]+$)\S{6,16}$/;
				var hh = new RegExp(patern);
				return hh.test(value);
			},
			message : '8-16位，不能有空格，纯数字或纯字母'
		},
		confirmPass : {
			validator : function(value, param) {
				var pass = $(param[0]).passwordbox('getValue');
				return value == pass;
			},
			message : '确认密码与原密码不一致'
		}
	});
}



function formReset(selector) {
	$(selector)[0].reset();
	$(selector).form("disableValidation");
	$(selector).form("enableValidation");
	$(selector + ' :input').blur();
}

// 在主框架页面的添加tab
/**
 * title：选项卡名称 href:选项卡url refresh：是否刷新，1 或 0
 */
function addMyTab(title, href, refresh) {
	if (window.top) {
		window.top.addTab(title, href, refresh);
	}
}

function closeMyTab(title) {
	if (window.top) {
		window.top.closeTab(title);
	}
}

function activeMyTab(title) {
	if (window.top) {
		window.top.activeTab(title);
	}
}
function findFrameInTab(title) {
	if (window.top) {
		return window.top.findFrameInTab(title);
	}
	return null;
}
function getCurPageTitle() {
	return document.title;
}
/**
 * 向某个主框架tab发送数据，目标回调函数为receiveData;
 * @param title
 * @param fromsrcObj
 * @param data
 */
function sendDataInTab(title, fromsrcObj, data) {
	var ret = findFrameInTab(title);
	if (ret) {
		if (ret.contentWindow && ret.contentWindow.receiveData) {
			ret.contentWindow.receiveData(fromsrcObj, data);
		}
	}
}
/**清除easyui input 验证提示**/
/**
 * target:选择器或jquery对象
 */
function clearEasyUiInvalidTip(target) {

	$(target).find(".textbox-invalid").each(function() {

		$(this).removeClass('textbox-invalid');
	})

	//removeClass('textbox-invalid');
	$(target).find(".validatebox-invalid").each(function() {

		$(this).removeClass('validatebox-invalid');
	});

	// $(target).find("input").blur();

}

function addEasyUiInvalidTip(target) {
	var target = $(target).next("span").addClass("textbox-invalid");
	target.find(".validatebox-text").addClass('validatebox-invalid');

}
