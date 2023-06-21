/*******************************************************************************
 * 扩展datagrid排序功能
 */
(function($) {
	$.namespace('easyui.ext.datagrid');
	easyui.ext.datagrid.up = function(tableid) {
		var selections = $('#' + tableid).datagrid("getSelections");
		// 排序
		selections = $.merge([], selections);
		;

		if (selections.length == 0) {
			$.messager.alert("提示", "请选择需要排序的记录。", 'info');
			return;
		}

		selections.sort(function(x, y) { // 升序
			var xi = $('#' + tableid).datagrid("getRowIndex", x);
			var yi = $('#' + tableid).datagrid("getRowIndex", y);
			if (xi > yi) {
				return 1;
			} else if (xi == yi) {
				return 0;
			} else {
				return -1;
			}
		});
		for (var i = 0; i < selections.length; i++) {
			var selIndex = $('#' + tableid).datagrid("getRowIndex",
					selections[i]);
			var obj = selections[i];
			var flag = false;
			if ((selIndex - 1) >= 0) {
				var sels = $('#' + tableid).datagrid("getSelections");
				for (var n = 0; n < sels.length; n++) {
					var index = $('#' + tableid).datagrid("getRowIndex",
							sels[n]);
					if (index == selIndex)
						continue;
					if (index == selIndex - 1) {
						flag = true;
						break;
					}
					;
				}
				if (flag)
					continue;
				$('#' + tableid).datagrid("deleteRow", selIndex);
				// $('#'+tableid).datagrid("acceptChanges");
				$('#' + tableid).datagrid("insertRow", {
					index : (selIndex - 1),
					row : obj
				});
				// $('#'+tableid).datagrid("acceptChanges");
				$('#' + tableid).datagrid("selectRow", (selIndex - 1));
			}

		}
	};
	easyui.ext.datagrid.tobottom = function(tableid) {
		var selections = $('#' + tableid).datagrid("getSelections");
		//
		selections = $.merge([], selections);
		;
		if (selections.length == 0) {
			$.messager.alert("提示", "请选择需要排序的记录。", 'info');
			return;
		}

		selections.sort(function(x, y) { // 倒序
			var xi = $('#' + tableid).datagrid("getRowIndex", x);
			var yi = $('#' + tableid).datagrid("getRowIndex", y);
			if (xi > yi) {
				return -1;
			} else if (xi == yi) {
				return 0;
			} else {
				return 1;
			}
		});

		var rowLeng = $('#' + tableid).datagrid("getRows").length;
		for (var i = 0; i < selections.length; i++) {
			var selIndex = $('#' + tableid).datagrid("getRowIndex",
					selections[i]);
			if (selIndex >= rowLeng - 1 - i) {
				continue;
			}
			var obj = selections[i];// getRows

			$('#' + tableid).datagrid("deleteRow", selIndex);

			$('#' + tableid).datagrid("insertRow", {
				index : rowLeng - 1 - i,
				row : obj
			});
			// $('#'+tableid).datagrid("acceptChanges");
			$('#' + tableid).datagrid("selectRow", rowLeng - 1 - i);

		}
	};
	easyui.ext.datagrid.totop = function(tableid) {
		var selections = $('#' + tableid).datagrid("getSelections");
		// 排序
		selections = $.merge([], selections);
		;

		if (selections.length == 0) {
			$.messager.alert("提示", "请选择需要排序的记录。", 'info');
			return;
		}

		selections.sort(function(x, y) { // 升序
			var xi = $('#' + tableid).datagrid("getRowIndex", x);
			var yi = $('#' + tableid).datagrid("getRowIndex", y);
			if (xi > yi) {
				return 1;
			} else if (xi == yi) {
				return 0;
			} else {
				return -1;
			}
		});
		for (var i = 0; i < selections.length; i++) {
			var selIndex = $('#' + tableid).datagrid("getRowIndex",
					selections[i]);
			if (selIndex <= i) {
				continue;
			}
			var obj = selections[i];
			$('#' + tableid).datagrid("deleteRow", selIndex);
			// $('#'+tableid).datagrid("acceptChanges");
			$('#' + tableid).datagrid("insertRow", {
				index : i,
				row : obj
			});
			// $('#'+tableid).datagrid("acceptChanges");
			$('#' + tableid).datagrid("selectRow", (i));

		}

	};

	easyui.ext.datagrid.down = function down(tableid) {
		var selections = $('#' + tableid).datagrid("getSelections");
		//
		selections = $.merge([], selections);
		if (selections.length == 0) {
			$.messager.alert("提示", "请选择需要排序的记录。", 'info');
			return;
		}

		selections.sort(function(x, y) { // 倒序
			var xi = $('#' + tableid).datagrid("getRowIndex", x);
			var yi = $('#' + tableid).datagrid("getRowIndex", y);
			if (xi > yi) {
				return -1;
			} else if (xi == yi) {
				return 0;
			} else {
				return 1;
			}
		});

		var rowLeng = $('#' + tableid).datagrid("getRows").length;
		for (var i = 0; i < selections.length; i++) {
			var selIndex = $('#' + tableid).datagrid("getRowIndex",
					selections[i]);
			var obj = selections[i];// getRows
			var flag = false;
			if ((selIndex + 1) < rowLeng) {
				var sels = $('#' + tableid).datagrid("getSelections");
				for (var n = 0; n < sels.length; n++) {
					var index = $('#' + tableid).datagrid("getRowIndex",
							sels[n]);

					if (index == selIndex + 1) {
						flag = true;
						break;
					}
					;
				}
				if (flag)
					continue;
				$('#' + tableid).datagrid("deleteRow", selIndex);
				// $('#'+tableid).datagrid("acceptChanges");
				$('#' + tableid).datagrid("insertRow", {
					index : (selIndex + 1),
					row : obj
				});
				// $('#'+tableid).datagrid("acceptChanges");
				$('#' + tableid).datagrid("selectRow", (selIndex + 1));
			}

		}
	};
})(jQuery);