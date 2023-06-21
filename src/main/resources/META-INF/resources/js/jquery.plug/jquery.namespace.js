/****
定义命名空间
用法如下：
$.namespace('easyui.ext');

easyui.ext.xxx=function(){

}
***/
(function($){
 	$.extend({
 		namespace: function(ns){
 			if(typeof ns != 'string'){
 				throw new Error('namespace must be a string');
 			}
 			
 			var ns_arr = ns.split('.');
 			var parent = window;
 			for(var i in ns_arr){
 				parent[ns_arr[i]] = parent[ns_arr[i]] || {};
 				parent = parent[ns_arr[i]];
 			}
 		}
 	});
 })(jQuery);