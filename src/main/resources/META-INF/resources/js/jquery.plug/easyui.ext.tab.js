(function($) {
	$.namespace('easyui.ext.tab');
	easyui.ext.tab.loadTab=function(tabid, title, href, refresh, icon, closable, options) {

		if (href.startWith('http://') || href.startWith('HTTP://')
				|| href.startWith('https://') || href.startWith('HTTPS://')) {
		} else {
			href = $.pageRoot + "/" + href;
		}
		var tt = $(tabid);
		if (closable == null || closable == undefined) {
			closable = true;
		}
		if (tt.tabs('exists', title)) {// 如果tab已经存在，则选中并刷新该tab
			tt.tabs('select', title);
			if (refresh == null || refresh == 'undefined' || refresh == 1) {
				this.refreshTab(tabid,{
					tabTitle : title,
					url : href,
					closable : closable
				});
			}

		} else {
			if (href) {
				var content = '<iframe'
						+ '  title="'
						+ title
						+ '"'
						+ '  style="margin: 0; padding: 0"'
						+ 'width="100%" height="100%" class="my-tab-iframe" onload="easyui.ext.tab.iframeTabLoaded(\'#tt\',this)"  frameborder="0" scrolling="auto" src="'
						+ href + '" >' + '</iframe>';
			} else {
				var content = '未实现';
			}

			var opt = $.extend({}, {
				title : title,
				closable : closable,
				content : content,
				selected : true
			}, (options || {}));

			tt.tabs('add', opt);
			var tab = $(tabid).tabs('getTab', title);

			$(tab).showLoading({
				"vPos" : $(tab).height() * 0.28,
				"hPos" : $(tab).width() * 0.4

			});

		}

	}
	easyui.ext.tab.iframeTabLoaded=function(tabid,obj){ 

		var tab=$(tabid).tabs('getTab', obj.title);
		
    	$(tab).hideLoading();

    }
	easyui.ext.tab.addTab=function(tabid, title, href, refresh, icon, closable, opt) {

		if (refresh == null || refresh == 'undefined') {
			refresh = 1;
		}
		if (!icon)
			icon = "0";
		if (closable == null || closable == 'undefined') {
			closable = true;
		}
		if (title == '首页') {
			this.loadTab(tabid, title, href, refresh, icon, closable, opt);
			return;
		}
		if (href.startWith('http://') || href.startWith('HTTP://')
				|| href.startWith('https://') || href.startWith('HTTPS://')) {
		} else {
			href = $.pageRoot + "/" + href;
		}

		this.loadTab(tabid, title, href, refresh, icon, closable, opt);
	}
	/**
	 * 刷新tab
	 * 
	 * @cfg example: {tabTitle:'tabTitle',url:'refreshUrl'}
	 *      如果tabTitle为空，则默认刷新当前选中的tab 如果url为空，则默认以原来的url进行reload
	 */
	easyui.ext.tab.refreshTab=function (tabid, cfg) {
		var refresh_tab = cfg.tabTitle ? $(tabid).tabs('getTab', cfg.tabTitle)
				: $(tabid).tabs('getSelected');
		if (refresh_tab && refresh_tab.find('iframe').length > 0) {
			var _refresh_ifram = refresh_tab.find('iframe')[0];
			var refresh_url = cfg.url ? cfg.url : _refresh_ifram.src;
			// _refresh_ifram.contentWindow.location.href=refresh_url;
			var ptitle = refresh_tab.panel('options').title;
			if (refresh_url) {
				var content = '<iframe'
						+ '  title="'
						+ ptitle
						+ '"'
						+ '  style="margin: 0; padding: 0"'
						+ 'width="100%" height="100%" onload="easyui.ext.tab.iframeTabLoaded(\'#tt\',this)" frameborder="0" scrolling="auto" src="'
						+ refresh_url + '" >' + '</iframe>';

			} else {
				var content = '未实现';
			}

			$(tabid).tabs('update', {
				tab : refresh_tab,
				options : {
					content : content
				}

			});

			$(refresh_tab).showLoading({
				"vPos" : $(refresh_tab).height() * 0.28,
				"hPos" : $(refresh_tab).width() * 0.4

			});

		}
	}
	easyui.ext.tab.activeTab=function(tabid, title) {
		if (title) {
			$(tabid).tabs('select', title);
		}
	}

	easyui.ext.tab.closeTab=function(tabid, title) {
		if (title) {
			$(tabid).tabs('close', title);
		}
	}

	easyui.ext.tab.findFrameInTab=function(tabid, title) {
		var tab = title ? $(tabid).tabs('getTab', title) : $(tabid).tabs(
				'getSelected');
		if (tab && tab.find('iframe').length > 0) {
			var _refresh_ifram = tab.find('iframe')[0];
			return _refresh_ifram;
		}
		return null;
	}

	// 绑定tab的双击事件、右键事件
	easyui.ext.tab.bindTabEvent=function(tabid) {

		$(".tabs-inner").live('contextmenu', function(e) {
			// alert(5678);
			$(tabid + '-mm').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
			var subtitle = $(this).children("span").text();
			$(tabid + '-mm').data("currtab", subtitle);
			return false;
		});
	}
	// 绑定tab右键菜单事件
	easyui.ext.tab.bindTabMenuEvent=function(tabid) {
		// 关闭当前
		var that=this;
		$(tabid + '-mm-tabclose').click(function() {
			var currtab_title = $.trim($(tabid + '-mm').data("currtab"));
			// 根据currtab_title查找tab
			var tabpanel = $(tabid).tabs("getTab", currtab_title);
			var closable = tabpanel.panel('options').closable;
			if (closable) {
				// alert(currtab_title);
				var frame = that.findFrameInTab(tabid,currtab_title);
				$(tabid).tabs('close', currtab_title);

			}
		});
		// 全部关闭 span.tabs-title
		$(tabid + '-mm-tabcloseall').click(function() {
			$('.tabs-inner span.tabs-title').each(function(i, n) {
				if ($(this).parent().next().is('.tabs-close')) {
					var t = $(n).text();
					$(tabid).tabs('close', t);
				}
			});
		});
		// 关闭除当前之外的TAB
		$(tabid + '-mm-tabcloseother').click(function() {
			var currtab_title = $(tabid + '-mm').data("currtab");
			$('.tabs-inner span.tabs-title').each(function(i, n) {
				if ($(this).parent().next().is('.tabs-close')) {
					var t = $(n).text();
					if (t != currtab_title)
						$(tabid).tabs('close', t);
				}
			});
		});
		// 关闭当前右侧的TAB
		$(tabid + '-mm-tabcloseright').click(function() {
			var nextall = $('.tabs-selected').nextAll();
			if (nextall.length == 0) {
				alert('已经是最后一个了');
				return false;
			}
			nextall.each(function(i, n) {
				if ($('a.tabs-close', $(n)).length > 0) {
					var t = $('a:eq(0) span', $(n)).text();
					$(tabid).tabs('close', t);
				}
			});
			return false;
		});
		// 关闭当前左侧的TAB
		$(tabid + '-mm-tabcloseleft').click(function() {
			var prevall = $('.tabs-selected').prevAll();
			if (prevall.length == 1) {
				alert('已经是第一个了');
				return false;
			}
			prevall.each(function(i, n) {
				if ($('a.tabs-close', $(n)).length > 0) {
					var t = $('a:eq(0) span', $(n)).text();
					$(tabid).tabs('close', t);
				}
			});
			return false;
		});

	}
	
	
})(jQuery);
