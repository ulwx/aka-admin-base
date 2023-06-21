
/**
 * 这个是合计的方法，合计是对应着第一列，合计受formatter影响，如果有特殊的formatter格式，需要重写它
 * formatter : function (value, row, index) { return value },
 * 对需要计算的列使用sum属性： ‘sum : true’, 提示文本设置：sumMsg : '合计:'
 * 调用加载表格尾部的方法：$ ('#tt').datagrid ('statistics');
 */

function isExistMsg(opt, i)
{
	return (opt[0][i].sumMsg != null && opt[0][i].sumMsg != undefined && opt[0][i].sumMsg.length > 0);
}

$(function() {
    $.extend($.fn.datagrid.methods, {
        statistics: function(jq) {
            var opt = $(jq).datagrid('options').columns;
            var rows = $(jq).datagrid("getRows");
            var footer = new Array();
            footer['sum'] = "";
            var sumMsg = null;
            for (var i = 0; i < opt[0].length; i++) {
                if (opt[0][i].sum) {
                    sumMsg = isExistMsg(opt, i) ? opt[0][i].sumMsg: null;
                    footer['sum'] = footer['sum'] + sum(sumMsg, opt[0][i].field) + ',';
                }
                else if(isExistMsg(opt, i)){
                	footer['sum'] += '"' + opt[0][i].field + '":"'+ opt[0][i].sumMsg + '"' + ',';
                }
            }

            var footerObj = new Array();

            if (footer['sum'] != "") {
                var tmp = '{' + footer['sum'].substring(0, footer['sum'].length - 1) + "}";
                var obj = eval('(' + tmp + ')');
                footerObj.push(obj);
            }
            if (footerObj.length > 0) {
                $(jq).datagrid('reloadFooter', footerObj);
            }

            function sum(sumMsg, filed) {
                var sumNum = 0;
                for (var i = 0; i < rows.length; i++) {
                    var tempNum = isNaN(Number(rows[i][filed])) ? 0 : Number(rows[i][filed]);
                    sumNum += tempNum;
                }

                return sumMsg == null ? '"' + filed + '":"' + sumNum + '"' 
                		         : '"' + filed + '":"' + sumMsg + sumNum + '"';
            }
        }
    });
});