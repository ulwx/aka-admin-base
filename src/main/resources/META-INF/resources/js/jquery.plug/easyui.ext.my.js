 (function($){
	  $.parser.plugins.push("mytextbox");//注册扩展组件
      $.fn.mytextbox = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.textbox.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.textbox.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).textbox(myopts);
             
         })
      };
      
      
	  $.parser.plugins.push("mypasswordbox");//注册扩展组件
      $.fn.mypasswordbox = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.passwordbox.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.passwordbox.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).passwordbox(myopts);
             
         })
      };

	  $.parser.plugins.push("mycombo");//注册扩展组件
      $.fn.mycombo = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.combo.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.combo.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).combo(myopts);
             
         })
      };
      
	  /****************************************************************************
		1.扩展了onSelectByClickItem事件，当用户手动选择combobox下拉选项时触发
		  onSelectByClickItem:function(index, itemRow){}
	  */
	  $.parser.plugins.push("mycombobox");//注册扩展组件

      $.fn.mycombobox = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.combobox.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({},$.fn.mycombobox.defaults, $.fn.combobox.parseOptions(this), options);

             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	    //扩展点
					// onSelectByClickItem:function(index, itemRow){},  //扩展onSelectByClickItem事件
				     onShowPanel : function(){

						var panel=$(this).combobox("panel");
						var target=this;
						$ (".combobox-item", panel).unbind ('click').click (function (event) {
								var sindex = $(this).attr('id').lastIndexOf('_');
								var index = $(this).attr('id').substr(sindex + 1,$(this).attr('id').length - sindex - 1);
								var data = $(target).combobox ("getData");
								var itemRow=data[index];
								if(opts.onSelectByClickItem){
									opts.onSelectByClickItem.call(target, index,itemRow)
								}
								
						});
						if(opts.onShowPanel){
							var ret=opts.onShowPanel.call(this);
						}

					 }//onShowPannel
             });
             $(this).combobox(myopts);
             
         })
      };  
	
      $.fn.mycombobox.defaults = $.extend({}, {
		   onSelectByClickItem:function(index, itemRow){} //扩展onSelectByClickItem事件	
	  });
      
	  /***********************************************************************/
	  $.parser.plugins.push("mycombotree");//注册扩展组件
      $.fn.mycombotree = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.combotree.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.combotree.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).combotree(myopts);
             
         })
      };
	  $.parser.plugins.push("mycombogrid");//注册扩展组件
      $.fn.mycombogrid = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.combogrid.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.combogrid.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).combogrid(myopts);
             
         })
      };
      
      
	  $.parser.plugins.push("mycombotreegrid");//注册扩展组件
      $.fn.mycombotreegrid = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.combotreegrid.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.combotreegrid.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).combotreegrid(myopts);
             
         })
      };
      
	  $.parser.plugins.push("mynumberbox");//注册扩展组件
      $.fn.mynumberbox = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.numberbox.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.numberbox.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).numberbox(myopts);
             
         })
      };
      
      
	  $.parser.plugins.push("mydatebox");//注册扩展组件
      $.fn.mydatebox = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.datebox.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.datebox.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).datebox(myopts);
             
             if(myopts.displayRealTime){
            	 var that=this;
            	 $(that).datebox("setValue",new Date().format());
            	 setInterval(function(){
            		 $(that).datebox("setValue",new Date().format());
            	 },1000); 
             }
             
         })
      };
      
	  $.parser.plugins.push("mydatetimebox");//注册扩展组件
	  /****
	   扩展属性：
	   displayRealTime:显示当前实时日期时间
	   */
      $.fn.mydatetimebox = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.datetimebox.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.datetimebox.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             
             $(this).datetimebox(myopts);
             if(myopts.displayRealTime){
            	 var that=this;
            	 $(that).datetimebox("setValue",new Date().format());
            	 setInterval(function(){
            		 $(that).datetimebox("setValue",new Date().format());
            	 },1000); 
             }
             
         })
      };
      
      $.parser.plugins.push("mydatetimespinner");//注册扩展组件
      $.fn.mydatetimespinner = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.datetimespinner.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.datetimespinner.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).datetimespinner(myopts);
             
         })
      };
      
      $.parser.plugins.push("mycalendar");//注册扩展组件
      $.fn.mycalendar = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.calendar.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.calendar.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).calendar(myopts);
             
         })
      };
      
     // $.parser.plugins.push("myspinner");//注册扩展组件
      $.fn.myspinner = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.spinner.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.spinner.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).spinner(myopts);
             
         })
      };
      
      $.parser.plugins.push("mynumberspinner");//注册扩展组件
      $.fn.mynumberspinner = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.numberspinner.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.numberspinner.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).numberspinner(myopts);
             
         })
      };
      
      $.parser.plugins.push("mytimespinner");//注册扩展组件
      $.fn.mytimespinner = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.timespinner.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.timespinner.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).timespinner(myopts);
             
         })
      };
      
      $.parser.plugins.push("myslider");//注册扩展组件
      $.fn.myslider = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.slider.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.slider.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).slider(myopts);
             
         })
      };
      
      $.parser.plugins.push("myfilebox");//注册扩展组件
      $.fn.myfilebox = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.filebox.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.filebox.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).filebox(myopts);
             
         })
      };
      
      $.parser.plugins.push("mywindow");//注册扩展组件
      $.fn.mywindow = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.window.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.window.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).window(myopts);
             
         })
      };
      
      $.parser.plugins.push("mydialog");//注册扩展组件
      $.fn.mydialog = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.dialog.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.dialog.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).dialog(myopts);
             
         })
      };
      
      /**
       * 
       * 1: 扩展了highlightRowLikeSelect(rowindex)方法：高亮某行，效果和select某行的效果相似，只是没有真实选择效果
       * 3：field里有redStar:true的，则对应的表头文字前有红色*
       * 4: 添加onOutCellClick(editCellsInfo,srcdom)事件，当用户点击表格数据外的区域触发，editCellsInfo 为：[{'rowIndex':1,'fields':['userName','sex']},..]
	   * 5：扩展方法acceptChange(uuid,type)，提交指定行的改变,uuid：所提交某行的uuid
	   * 6:扩展方法clearSelection(rowindex)，和highlightRowLikeSelect相反的效果，取消高亮某行
	   * 7:扩展方法：getEditCells() ,扩展方法：getEditCells() ,获得当前所有的编辑cell的索引，返回数组，例如[{'rowIndex':1,'fields':['userName','sex']},..]
	   * 8:扩展方法：getEditRows()，获得当前所有正在编辑的行号数组
	   * 9:扩展方法：updateCell(rowIndex,field,val) ,可以在不用刷新的情况下更新某个单元格
	   * 10:扩展方法：isSelection(rowIndex)，判断指定的行是否选中
	   * 11:扩展方法:isChecked(rowIndex)，判断指定的行是否已经选择
       */
      $.parser.plugins.push("mydatagrid");//注册扩展组件
      $.fn.mydatagrid = function (options, param) {//定义扩展组件

		  
    	  function isNullObj (value) {
    			return value == null || value == undefined || value.length == 0 || value == 'null';
    		}
		  function addRedStars ($table) {
				var array = [];
				var opt = $table.datagrid ('options').columns;
				if(opt && opt[0] && opt[0].length){
					for (var i = 0; i < opt[0].length; i++){
						if (!isNullObj (opt[0][i].redStar)){
							array.push (opt[0][i].field);
						}
					}
				}
				
				var dom = $table.datagrid ("getPanel").find ('.datagrid-header-row .datagrid-cell span:first-child').each (
				        function () {
					        if (array.indexOf ($ (this).parent ().parent ().attr ('field')) > -1)
						        $ (this).before ("<span style='color:red'>*</span>");
				 });
		 }
         //当options为字符串时，说明执行的是该插件的方法。
        // if (typeof options == "string") { return $.fn.datagrid.apply(this,arguments); }
		 if (typeof options == 'string'){
			var method = $.fn.mydatagrid.methods[options];
			if (method){
				 var args = Array.prototype.slice.call(arguments); 
				 args[0]=this;
				 //alert($.toJSON(args));
				 return method.apply(this, args);
			} else {
				return $.fn.datagrid.apply(this,arguments);
			}
		}

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.datagrid.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
				 /**
				 扩展事件，当用户点击表格数据外的区域触发
				 editCellsInfo: 为数组，例如[{'rowIndex':1,'fields':['userName','sex']},{'rowIndex':2,'fields':['sex']},..]
				 srcdom：当前点击的dom对象
				 */
 				onOutCellClick:function(editCellsInfo,srcdom){},
 				onClickRow:function(index,row){
					$(this).mydatagrid ("uncheckAll");
 	 				$(this).mydatagrid ("checkRow",index);
 	 				$(this).mydatagrid ("highlightRowLikeSelect",index);
 					if(opts.onClickRow){
 						var ret=opts.onClickRow.call(this,index,row);
 					}
 				},
 				onCheck:function(index,row){
					$(this).mydatagrid ("highlightRowLikeSelect",index);
 					if(opts.onCheck){
 						var ret=opts.onCheck.call(this,index,row);
 					}
 					
 				},
				onBeforeEdit:function(index,row){
					 if(opts.onBeforeEdit){
						var ret=opts.onBeforeEdit.call(this,index,row);
					}
				 },
				 loadFilter:function(data){
					 var newData=null;
					if(opts.loadFilter){
						newData=opts.loadFilter.call(this,data);

					}else{
						newData=data;
					}
					 if(newData && newData.rows && newData.rows.length){
						 //alert($.toJSON(data));
						 $.each(newData.rows,function(index,e){
							 e.uuid=myuuid(10,10);
						 });

					 }
					 return newData;
					
				}

             });

             $(this).datagrid(myopts);
              addRedStars($(this));
			 var panel=$(this).datagrid('getPanel');

			 var target=this;

			$("body").on('click',function(e){
				if($(e.target).is(":input")||$(e.target).closest("a,.window").length){
					return;
				}
				//display: block;
				if($(".window:visible").length){
					return;
				}

				var jtable=$(panel).find('.datagrid-body .datagrid-btable');
				var ret=$(e.target).closest('.datagrid-btable tr,td');
				if(ret.length){
					//
				}else{
					
					var cells=$(target).mydatagrid("getEditCells");//数组,元素为对象。[{'rowIndex':1,'fields':['userName','sex']},..]
					if(opts.onOutCellClick && cells.length>0){
						//alert(33333);
						opts.onOutCellClick.call(target,cells,e.target);
					}
					

				}
			});

             
         })//each
      };//mydatagrid define
	
	 $.fn.mydatagrid.methods = {
		/**highlightRowLikeSelect方法：高亮某行，效果和选择某行效果相似，只是不会触发选择事件
		   index:行号
		 */
		highlightRowLikeSelect: function(jq, index){
			return jq.each(function(){
				var panel=$(this).datagrid("getPanel");
				panel.find('.datagrid-body tr[datagrid-row-index="'+index+'"]').addClass("datagrid-row-selected");

			});
		},
		isSelection:function(jq,index){
			var rows=$(jq).datagrid("getSelections");
			if(rows && rows.length>0){
				for(var i=0;i<rows.length; i++){
					var rowindex=$(jq).datagrid("getRowIndex",rows[i]);
					if(index==rowindex){
						return true;
					}
				}
			}
			return false;
		},
		isChecked:function(jq,index){
			var rows=$(jq).datagrid("getChecked");
			if(rows && rows.length>0){
				for(var i=0;i<rows.length; i++){
					var rowindex=$(jq).datagrid("getRowIndex",rows[i]);
					if(index==rowindex){
						return true;
					}
				}
			}
			return false;
		},
		/**
		 * 重写clearChecked方法，解决原clearChecked方法的bug
		 */
		clearChecked:function(jq){
			var ret=$(jq).datagrid('clearChecked');
			var panel=$(jq).datagrid("getPanel");
			panel.find("div.datagrid-cell-check input[type=checkbox]").attr("checked",false);
			return ret;
		},
		/**
		  扩展方法clearSelection，和highlightRowLikeSelect相反的效果，取消高亮某行
		  index：行号
		*/
		clearSelection:function(jq,index){
			return jq.each(function(){
				var panel=$(this).datagrid("getPanel");
				panel.find('.datagrid-body tr[datagrid-row-index="'+index+'"]').removeClass("datagrid-row-selected");
		
			});
		},
		/**
		  扩展方法：getEditCells() ,获得当前所有的编辑cell的索引，返回数组，数组元素为对象{'rowIndex':1,'fields':['userName','sex']}
		*/

		getEditCells:function(jq){
				var obj=[];
				var panel=$(this).datagrid("getPanel");
				var eds=$(panel).find('.datagrid-body .datagrid-editable').each(function(){
						var editcell=$(this).closest("td[field]"); //field="attr1"
						var field=editcell.attr("field");
						var rowIndex=$(this).closest(".datagrid-body tr[datagrid-row-index]").attr("datagrid-row-index")-0;
						var find=false;
						for(var i=0;i<obj.length ;i++ )
						{
							var e=obj[i];
							if(e.rowIndex==rowIndex){
							   obj.push({'rowIndex':rowIndex,'fields':e.fields.push(field)});
							   find=true;
							}
						}
						if(!find){
							 obj.push({'rowIndex':rowIndex,'fields':[field]});
						}
						
					});
				return obj;
			
			//
		},
		/**
		  扩展方法：getEditRows()，获得当前所有正在编辑的行号,返回数组包含行索引
		*/
		getEditRows:function(jq){
			var obj=[];
			var panel=$(this).datagrid("getPanel");
			var eds=$(panel).find('.datagrid-body .datagrid-editable').each(function(){
				var rowIndex=$(this).closest(".datagrid-body tr[datagrid-row-index]").attr("datagrid-row-index")-0;
				if(obj.indexOf(rowIndex)<0){
					obj.push(rowIndex);
				}
			});
			return obj;
		},
		/**扩展方法：updateCell(rowIndex,field,val) ,可以在不用刷新的情况下更新某个单元格
		*/
		updateCell:function(jq,rowIndex,field,val){
			return jq.each(function(){
				var panel=$(this).datagrid("getPanel");
				$(panel).find('.datagrid-body tr[datagrid-row-index="'+rowIndex+'"] td[field="'+field+'"] div.datagrid-cell').html(val);
				var rows=$(this).datagrid('getRows');
				var row=rows[rowIndex];
				row[field]=val;
				//改变update change数组
				var  upds=$.data(this,"datagrid").updatedRows;
				var find=false;
				for(var i=0; i<upds.length; i++){
					if(upds[i].uuid==row.uuid){
						find=true;
						break;
					}
				}
				if(!find){
					upds.push(row);
				}
				
				//alert('row='+$.toJSON(row)+",value="+val+',field='+field);

			});

		},
		appendRow:function(jq,row){
			row.uuid=myuuid(10,10);
			return $(jq).datagrid('appendRow',row);
		},
		insertRow:function(jq,param){
			param.row.uuid=myuuid(10,10);
			return $(jq).datagrid('insertRow',param);
		},
		
		/**
			扩展方法：acceptChange，提交指定行的改变
			uuid：所提交某行的uuid。如果是数字，表明是行号。insert和upate可以用行号，但delete必须用uuid
			type: inserted,deleted,updated，如果不赋值，则在所有类型（增删改）改变的行里查找rowIndex，并提交。
		*/
		acceptChange:function(jq,uuid,type){
			return jq.each(function(){
				var ins=$.data(this,"datagrid").insertedRows;
				var  dels=$.data(this,"datagrid").deletedRows;
				var  upds=$.data(this,"datagrid").updatedRows;
				var  orgs=$.data(this,"datagrid").originalRows;
				var _this=this;

				//如果 uuid参数传过来的为数字，表明是行号
				if(typeof(uuid)=='number'){
					var rows=$(this).datagrid("getRows");
					uuid=rows[uuid-0].uuid;
				}
				

				function doAccept(changeRowsArray,arrayIndex,uuid,dgridEl,type){

					//删除原始数组里的相应值
					if(orgs.length){
						for(var i=0; i<orgs.length; i++){
							if(orgs[i].uuid==uuid){
								orgs.splice(i,1);
								break;
							}
							
						}
					}
					if(type=='inserted' || type=='updated'){
						var rowIndex=-1;
						var rows=$(dgridEl).datagrid("getRows");
						if(rows.length){
							for(var n=0;n<rows.length; n++){
								if(rows[n].uuid==uuid){
									//alert(n);
									rowIndex=n;
								}
							}
						}
						if(rowIndex>=0&& validateRow(dgridEl,rowIndex)){
							orgs.push(changeRowsArray[arrayIndex]);
							$(_this).mydatagrid("endEdit",rowIndex);
						}
								
					}else{ // deleted

					}
			
					//changeRowsArray.splice(arrayIndex,1);//删除
					for(var i=0; i<ins.length; i++){
						if(ins[i].uuid==uuid){
							ins.splice(i,1);//删除
							i--;
						}
					}
					for(var i=0; i<dels.length; i++){
						if(dels[i].uuid==uuid){
							dels.splice(i,1);//删除
							i--;
						}
					}
					for(var i=0; i<upds.length; i++){
						if(upds[i].uuid==uuid){
							upds.splice(i,1);//删除
							i--;
						}
					}
					
					
				}

				function validateRow(dgridEl,index){
					var tr=$.data(dgridEl,"datagrid").options.finder.getTr(dgridEl,index);
					if(!tr.hasClass("datagrid-row-editing")){
						return true;
					}
					var vbox=tr.find(".validatebox-text");
					vbox.validatebox("validate");
					vbox.trigger("mouseleave");
					var invalbox=tr.find(".validatebox-invalid");
					return invalbox.length==0;
				};
				if(!type){
					for(var i=0; i<ins.length; i++){
						if(ins[i].uuid==uuid){
								doAccept(ins,i,uuid,this,'inserted');
								return;
							}
					}
					
					for(var i=0; i<upds.length; i++){
						if(upds[i].uuid==uuid){
							doAccept(upds,i,uuid,this,'updated');
							return;
						}
					}

					for(var i=0; i<dels.length; i++){
						if(dels[i].uuid==uuid){
							doAccept(dels,i,uuid,this,'deleted');
							return;
						}
					}
				}else{
					if(type=='inserted'){
						for(var i=0; i<ins.length; i++){
							if(ins[i].uuid==uuid){
								doAccept(ins,i,uuid,this,'inserted');
								return;
							}
						}
					}
					if(type=='updated'){
						for(var i=0; i<upds.length; i++){
							if(upds[i].uuid==uuid){
								doAccept(upds,i,uuid,this,'updated');
								return;
							}
						}
					}

					if(type=='deleted'){
						for(var i=0; i<dels.length; i++){
							if(dels[i].uuid==uuid){
								doAccept(dels,i,uuid,this,'deleted');
								return;
							}
						}
					}
				}

			});
		
		}


	 };
	  //////////////////////////////////////////////////
      $.parser.plugins.push("mydatalist");//注册扩展组件
      $.fn.mydatalist  = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.datalist.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.datalist.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).datalist(myopts);
             
         })
      };
      
      $.parser.plugins.push("mypropertygrid");//注册扩展组件
      $.fn.mypropertygrid = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.propertygrid.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.propertygrid.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).propertygrid(myopts);
             
         })
      };
      
      $.parser.plugins.push("mytree");//注册扩展组件
      $.fn.mytree = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.tree.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.tree.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).tree(myopts);
             
         })
      };
      
      $.parser.plugins.push("mytreegrid");//注册扩展组件
      $.fn.mytreegrid = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.treegrid.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.treegrid.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).treegrid(myopts);
             
         })
      };
      
      
      $.parser.plugins.push("myvalidatebox");//注册扩展组件
      $.fn.myvalidatebox = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.validatebox.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.validatebox.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).validatebox(myopts);
             
         })
      };
      
      
      $.parser.plugins.push("myswitchbutton");//注册扩展组件
      $.fn.myswitchbutton = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.switchbutton.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.switchbutton.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).switchbutton(myopts);
             
         })
      };
      
      $.parser.plugins.push("mysplitbutton");//注册扩展组件
      $.fn.mysplitbutton = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.splitbutton.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.splitbutton.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).splitbutton(myopts);
             
         })
      };
      
      $.parser.plugins.push("mymenubutton");//注册扩展组件
      $.fn.mymenubutton = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.menubutton.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.menubutton.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).menubutton(myopts);
             
         })
      };
      
      $.parser.plugins.push("mylinkbutton");//注册扩展组件
      $.fn.mylinkbutton = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.linkbutton.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.linkbutton.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).linkbutton(myopts);
             
         })
      };
      
      $.parser.plugins.push("mymenu");//注册扩展组件
      $.fn.mymenu = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.menu.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.menu.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).menu(myopts);
             
         })
      };
      
      $.parser.plugins.push("mylayout");//注册扩展组件
      $.fn.mylayout = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.layout.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.layout.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).layout(myopts);
             
         })
      };
      
      $.parser.plugins.push("myaccordion");//注册扩展组件
      $.fn.myaccordion = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.accordion.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.accordion.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).accordion(myopts);
             
         })
      };
      
      $.parser.plugins.push("mytabs");//注册扩展组件
      $.fn.mytabs = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.tabs.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.tabs.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).tabs(myopts);
             
         })
      };
      
      $.parser.plugins.push("mypanel");//注册扩展组件
      $.fn.mypanel = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.panel.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.panel.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).panel(myopts);
             
         })
      };
      
      $.parser.plugins.push("mytooltip");//注册扩展组件
      $.fn.mytooltip = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.tooltip.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.tooltip.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).tooltip(myopts);
             
         })
      };
      
      $.parser.plugins.push("myprogressbar");//注册扩展组件
      $.fn.myprogressbar = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.progressbar.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.progressbar.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).progressbar(myopts);
             
         })
      };
      
      $.parser.plugins.push("mysearchbox");//注册扩展组件
      $.fn.mysearchbox = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.searchbox.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.searchbox.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).searchbox(myopts);
             
         })
      };
      
      $.parser.plugins.push("mypagination");//注册扩展组件
      $.fn.mypagination = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.pagination.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.pagination.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).pagination(myopts);
             
         })
      };
      
      $.parser.plugins.push("myresizable");//注册扩展组件
      $.fn.myresizable = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.resizable.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.resizable.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).resizable(myopts);
             
         })
      };     
      $.parser.plugins.push("mydroppable");//注册扩展组件
      $.fn.mydroppable = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.droppable.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.droppable.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).droppable(myopts);
             
         })
      }; 
      
      
      $.parser.plugins.push("mydraggable");//注册扩展组件
      $.fn.mydraggable = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { return $.fn.draggable.apply(this,arguments); }

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.draggable.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{ 
            	 //扩展点
             });
             $(this).draggable(myopts);
             
         })
      }; 
	})(jQuery);

 
  /***********************************
  * 
  * 1.easyui扩展组件 ckcombobox ,扩充combobox多选功能，使其选项里增加checkbox选择框
  * 2. 增加 onSelectByClickItem事件，当用户点击下拉框的item时触发
		 onSelectByClickItem:function(index, itemRow){}
  * @param $
  */
 (function($){
	  $.parser.plugins.push("ckcombobox");//注册扩展组件
      $.fn.ckcombobox = function (options, param) {//定义扩展组件

         //当options为字符串时，说明执行的是该插件的方法。
         if (typeof options == "string") { 
			 return $.fn.combobox.apply(this,arguments);
		  }
         options = options || {}; 

         //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
         return  this.each(function () {

             //$.fn.combobox.parseOptions(this)作用是获取页面中的data-options中的配置
             var opts = $.extend({}, $.fn.combobox.parseOptions(this), options);
             //把配置对象myopts放到$.fn.combobox这个函数中执行。
             var myopts = $.extend({}, opts,{
							formatter : function(row){
								if(opts.formatter){
									var ret=opts.formatter.call(this, row);
								}
								 var opts2 = $(this).combobox("options");
								 if( row[opts2.valueField]=='-999999'){
										return "<span class='ckcombobox-xyz'  style='display:inline-block;width:100%'>[<a  href='javascript:void(0);' style='text-decoration:none;'>全选</a>] [<a  href='javascript:void(0);' style='text-decoration:none;'>全不选</a>] [<a style='text-decoration:none;'  href='javascript:void(0);'>反选</a>]</span>";
								 }
								 if(ret){
									 return "<input type='checkbox' class='combobox-checkbox'>" +ret;
								 }else{
									 return "<input type='checkbox' class='combobox-checkbox'>" + row[opts2.textField];
								 }

							},
							loadFilter: function(data){
								var opts2 = $(this).combobox("options");
								if(opts.loadFilter){
								   data=opts.loadFilter.call(this,data);
								}
								if(data && data.length>0){
									var obj={};
									obj[opts2.valueField]=-999999
									obj[opts2.textField]='全选,全不选,反选'
									data.unshift(obj);
								}
								return data;

							},
							onShowPanel : function(){

								///增加onSelectByClickItem事件
								var panel=$(this).combobox("panel");
								var target=this;
								$ (".combobox-item", panel).unbind ('click.ckcombobox').bind('click.ckcombobox',function (event) {
										var sindex = $(this).attr('id').lastIndexOf('_');
										var index = $(this).attr('id').substr(sindex + 1,$(this).attr('id').length - sindex - 1);
										var data = $(target).combobox ("getData");
										var itemRow=data[index];
										if(opts.onSelectByClickItem){
											opts.onSelectByClickItem.call(target, index-1,itemRow)
										}
										
								});
								if(opts.onShowPanel){
									var ret=opts.onShowPanel.call(this);
								}
								var opts2 = $(this).combobox("options");
								target = this;

								$(".ckcombobox-xyz").unbind('click.ckcombobox').bind('click.ckcombobox',function(event){
									event.stopPropagation();
									return false;
								});
								var comdata=$(target).combobox("getData");
									
								var valuesArray=$.map(comdata,function(item){
									if(item[opts2.valueField]==-999999){
										return null;
									}

									return item[opts2.valueField];
								});

								
								$(".ckcombobox-xyz a").unbind('click.ckcombobox').bind('click.ckcombobox',function(event){
									if($(this).text()=='全选'){
										
										$(target).combobox("setValues",valuesArray);
									}else if($(this).text()=='全不选'){
										$(target).combobox("setValues",[]);
									}if($(this).text()=='反选'){
										var comvalues = $(target).combobox("getValues");
										var novaluesArray=$.map(comdata,function(item){
											if(item[opts2.valueField]==-999999){
												return null;
											}
											if($.inArray(item[opts2.valueField]+'',comvalues)<0){
												return item[opts2.valueField];
											}
											return null;
										});
										$(target).combobox("setValues",novaluesArray);
									}
									event.stopPropagation();
									return false;

								});


								var values = $(target).combobox("getValues");
								$.map(values, function(value){
									var children = $(target).combobox("panel").children();
									$.each(children, function(index, obj){
										var row=$(target).combobox("getData")[index];
										if(value == row[opts2.valueField] && obj.children && obj.children.length > 0){
											obj.children[0].checked = true;
										}
									});
								});

								 
							},
							onLoadSuccess : function(){
								if(opts.onLoadSuccess){
									var ret=opts.onLoadSuccess.call(this);
								}
								var opts2 = $(this).combobox("options");
								var target = this;
								var values = $(target).combobox("getValues");
								$.map(values, function(value){
									var children = $(target).combobox("panel").children();
									$.each(children, function(index, obj){
										var row=$(target).combobox("getData")[index];
										if(value ==  row[opts2.valueField] && obj.children && obj.children.length > 0){
											obj.children[0].checked = true;
										}
									});
								});

								
							},

							onSelect : function(row){
								if(opts.onSelect){
									var ret=opts.onSelect.call(this,row);
								}
								var target = this;
								var opts2 = $(this).combobox("options");
								var objCom = null;
								var children = $(this).combobox("panel").children();
								$.each(children, function(index, obj){
									var row2=$(target).combobox("getData")[index];
									if(row[opts2.valueField] == row2[opts2.valueField]){
										objCom = obj;
									}
								});
								if(objCom != null && objCom.children && objCom.children.length > 0){
									objCom.children[0].checked = true;
								}

								
							},

							onUnselect : function(row){
								if(opts.onUnselect){
								   var ret=opts.onUnselect.call(this,row);
								}
								var target = this;
								var opts2 = $(this).combobox("options");
								var objCom = null;
								var children = $(this).combobox("panel").children();
								$.each(children, function(index, obj){
									var row2=$(target).combobox("getData")[index];
									if(row[opts2.valueField] == row2[opts2.valueField]){
									objCom = obj;
									}
								});
								if(objCom != null && objCom.children && objCom.children.length > 0){
									objCom.children[0].checked = false;
								}

								
							}
				});
				 $(this).combobox(myopts);
              //$.fn.combobox.call(this, myopts);
         });
     };

	 $.ajaxSetup({
		 timeout: 1200000  // 设置超时为1200秒（20分钟）
	 });

	})(jQuery);