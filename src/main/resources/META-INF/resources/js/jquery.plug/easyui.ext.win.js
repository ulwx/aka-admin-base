/**
  * @requires jquery, EasyUI 1.2.6+
  * 
  * 此方法是对 EasyUI.window 和 EasyUI.dialog 的扩展
  * 可以实现如下功能：
  * 1、使用框架页面时可以控制窗口是否跨框架弹出在框架最顶层页面，还是框架内当前页面。默认框架最顶层页面
  * 2、可以控制url加载页面方式，是使用默认方式，还是iframe加载， 默认iframe加载
  * 3、使用iframe加载页面时，可以实现父页面向子页面传递javaScript对象
  * 4、使用iframe加载页面时，可以订制iframe onLoad事件
  * 5、扩展content属性，自动识别是静态文本内容，还是加载页面
  * 6、通过赋值ID属性，控制弹出窗体唯一性
  * 7、toolbar、buttons中定义按钮的handler属性，支持弹出窗体iframe中方法调用。
  * 8、弹出窗体关闭方式更灵活
 
  * 
  * @author zhaojh<zjh527@163.com>
  * @date 2012.11.15
  * 
  */
 (function($){
 	$.namespace('easyui.ext');
 	
 	/**
 	 * 普通窗体
 	 * 
 	 * 新增属性说明如下
 	 * @param isFrame	是否开启使用iframe加载给定url页面, 此属性设置为true时则开启使用iframe加载页面。 值：true|false,  默认值true
 	 * @param self		用于框架页面，如果值为true则不跨框架，否则跨框架弹出在框架最顶层页面。 值:true|false,	默认值false
 	 * @param data		用于在使用iframe加载给定页面时，父页面给子页面传递数据。	默认值null
 	 * 
 	 * 扩展属性说明如下
 	 * @param onLoad	当使用iframe加载给定url页面时，在iframe加载完成后调用。
 	 * 					默认接收一个参数对象，参数对象属性说明参见下面toolbar、buttons说明第2项。
 	 * @param content	可根据内容前缀关键字'url:',来判断是显示静态文本还是加载页面。
 	 * @param id		此属性用来标识弹出窗体的唯一性，不再用来充当panel的id属性
 	 * 
 	 * 特殊属性说明如下
 	 * this.content		iframe方式加载内容页的window对象。 用于onLoad方法中的调用
 	 * 
 	 * 
 	 * toolbar、buttons	属性定义按钮handler属性扩展说明如下
 	 * 1、当handler 被赋值字符串时，表示调用弹出窗体iframe中已有的与字符串值同名的方法,否则调用父里的方法，通过在父和弹出窗口里定义handler来交互数据
 	 * 2、被调用方法默认接收一个参数对象，对象属性如下：
 	 *   data: 类型：Object，是对vseaf.open方法参数data的引用
 	 *   close: 类型：Function，用来关闭弹出窗体
 	 * 
 	 * 
 	 * 
 	 * 
 	 * 注：其他属性请参考EasyUI API文档。
 	 * 
 	 */
 	easyui.ext.open = function(opts){
 		var win=null;
 		var defaults = {
     		minimizable: true,
     		maximizable: true,
     		width:500,
     		height:500,
     		collapsible: true,
     		resizable: true,
     		autoHeight:false,
     		isFrame: true, //是否使用iframe
     		self: false, //用于框架页面，如果值为true则不跨框架，否则跨框架弹出在框架最顶层页面
     		data: null, //iframe方式下用来父页面向弹出窗体中子页面传递数据
     		content: '',
     		onLoad: null,
			constrain:true

 		};
 		
 		var options = $.extend({}, defaults, opts);

 		//取顶层页面
 		var  _top = (function(w){
			var _doc=null;
 			try{
 				_doc = w['top'].document;
 				_doc.getElementsByTagName;
 			}catch(e){
 				return w;
 			}
 			
 			if(options.self ){
 				return w;
 			}
 			
 			var topWin= w['top'];
			if (topWin.jQuery && topWin.jQuery.fn ) {
				// 确认是真正的 jQuery 而不仅仅是名为 jQuery 的变量
				if (topWin.jQuery.fn.dialog){
					return topWin;
				}
			}
			return w;
		})(window);

 		//检查content内容是静态文本，还是url地址
 		var iframe=null;
 		var isUrl = /^url:/.test(options.content);
 		if(isUrl){
 			var url = options.content.substr(4, options.content.length);
 			//构建iframe加载方式
 			if(options.isFrame){
 				if(options.autoHeight){
 					iframe = $('<iframe ></iframe>')
			            .attr('height', '100%')
			            .attr('width', '100%')
			            .attr('marginheight', '0')
			            .attr('marginwidth', '0')
			            .attr('frameborder','0');
 				}else{
 					iframe = $('<iframe ></iframe>')
			            .attr('height', '100%')
			            .attr('width', '100%')
			            .attr('marginheight', '0')
			            .attr('marginwidth', '0')
			            .attr('frameborder','0');
 				}

 				var _this = this;
 				var frameOnLoad = function(){
 					//_this.content = iframe.get(0).contentWindow;
 					var autoSizeFunc=function(){
 						var ch1=$(iframe.get(0)).contents().find("body")[0].scrollHeight;
	 					var ch2=0;
	 					var ch3=$($(iframe.get(0)).contents().find("body")[0]).height();
	 					
	 					ch1=Math.min(ch1,ch3);
	 					if(options.autoHeight){
	 					  //$(iframe.get(0)).contents().find("body").css("overflow","hidden");
	 					}
	 					var titleHeight=win.dialog("header").height()+40;
	 					var ch= Math.max(ch1, ch2)+titleHeight;
	 					if(win){
							win.dialog("resize",{  
								height: ch
							});
							win.dialog("center");
	 					}else{
	 					}
 					};

 					options.onLoad && options.onLoad.call(_this, {
 						data: options.data,
 						window:iframe.get(0).contentWindow,
 						instance:win,
 						autoSize:autoSizeFunc,
 						close: function(){
 							win.dialog('close');
 						}
 					});

					options =win.dialog("options");
					options.onMaximize=function(){
						if(win && _top.top!=_top){
							//alert(_top.jQuery(_top).height())
							var maxHt=_top.jQuery(_top).height();
							var maxWt=_top.jQuery(_top).width();
							win.dialog("restore");
							win.dialog("resize",{
								top:0,
								left:0,
								height: maxHt-30,
								width:maxWt
							});
							//win.dialog("center");
							win.dialog("doLayout");
							var iframeWin = iframe.get(0).contentWindow;

						}else{
						}
					}

					if(!options.onClose){
						options.onClose=function (){
							var $dialog=win.dialog('dialog');
							win.dialog('destroy');
							$dialog.remove();
						};
						win.dialog(options);
					}else{
						var _onClose=options.onClose;
						options.onClose=function (){
							_onClose.apply(this)
							var $dialog=win.dialog('dialog');
							win.dialog('destroy');
							$dialog.remove();
						}

						win.dialog(options);
					}
 					if(options.autoHeight){
 						autoSizeFunc();
 					}
					if(options.maximized){

					}
 				};
 				
 				delete options.content;
 				
 			}else{//使用默认页面加载方式
 				options.href = url;
 			}
 		}
 		
 		//加工toolbar和buttons中定义的handler方法，使其可以接收给定参数，用于iframe方式下的父子页面传值和窗口关闭

 		var warpHandler = function(handler){
				var args = {
					 data: options.data,
					 close: function(){
					 	win.dialog('close');

				 	 }
			 };
 			
 			/***通过父亲和子窗口里定义的handler，并且通过options.data来传值
 			/**父亲定义的handler***/
 			if(typeof handler =='function'){
 				return function(){
 					handler(args);
 				};
 			}
 			/**子窗口定义的handler**/
 			if(typeof handler == 'string' && options.isFrame){
 				return function(){
 					iframe.get(0).contentWindow[handler](args);
 				};
 			}
 		};
 		
 		//处理toolbar数组事件定义,选择器形式不做处理
 		if(options.toolbar && $.isArray(options.toolbar)){
 			for(var i in options.toolbar){
 				options.toolbar[i].handler = warpHandler(options.toolbar[i].handler);
 			}
 		}
 		
 		//处理buttons数组事件定义,选择器形式不做处理
 		if(options.buttons && $.isArray(options.buttons)){
 			for(var i in options.buttons){
 				options.buttons[i].handler = warpHandler(options.buttons[i].handler);
 			}
 		}
		winId= Math.floor(Math.random() * 9000000000) + 1000000000;
 		if(options.isFrame && iframe){

 			win = _top.jQuery('<div  style="overflow:hidden"></div>', {id: winId}).append(iframe).dialog(options);
 			iframe.attr('src', url);
 			iframe.bind('load', frameOnLoad);
 			
			setTimeout(function(){
 				
			}, 50);
 			
 			
 		}else{
 			win = _top.jQuery('<div ></div>', {id: winId}).dialog(options);
 		}
 		return win;
 	}



	 /**
 	 * 
 	 * 模式窗体
 	 * 
 	 * 参数说明请看easyui.ext.open
 	 * 
 	 */
 	easyui.ext.showModalDialog = function(opts){
 		var defaults = $.extend(
 					{}, 
 					opts, 
 					{
 						modal: true, 
 						minimizable: false, 
 						maximizable: false, 
 						resizable: false, 
 						collapsible: false 
 					}
 				);
 		return easyui.ext.open(defaults);
 	};

 	
 	
 })(jQuery);