<%@page import="java.util.List" %>
<%@ page import="com.github.ulwx.aka.admin.domain.db.sys.SysRight" %>
<%@ page import="com.github.ulwx.aka.admin.domain.SessionUserInfo" %>
<%@ page import="com.github.ulwx.aka.admin.domain.UserRight" %>
<%@ page import="com.github.ulwx.aka.webmvc.WebMvcCbConstants" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage="" %>

<!DOCTYPE html>
<html>
<head>
    <% SessionUserInfo info = (SessionUserInfo) request.getSession().getAttribute(com.github.ulwx.aka.webmvc.WebMvcCbConstants.USER); %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>派速捷云平台</title>
    <jsp:include page="/head.jsp" flush="true"></jsp:include>


    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/css/main.css"/>
    <style>
        #tabs .tabs-panels .panel-body {
            overflow: hidden
        }

        #tabs .tabs-header {
            background: #F5F4F4
        }

        .tabs li a.tabs-inner {
            background-color: #DCDCDC;
            border: 0px;
        }

        .tabs li.tabs-selected a.tabs-inner {
            border: 1px solid #DCDCDC;
            background-color: #f5f4f4
        }

        .tabs {
            border-color: #dcdcdc
        }

        .layout-panel-north {
            margin: 0px;
            border: 0px;
            border-bottom: 1px solid #DCDCDC;;
        }

        .myHeaderCls {
            background: #315B95;
            border: 0px;
        }

        .myHeaderMenuCls {
            background: #133E73;
            color: white;
            border: 0px;
        }

        .accordion .accordion-header {
            background: #133E73;
            border: 0px;
            height: 28px;
            padding: 0px;
            margin: 0px;
            line-height: 28px;
            border-top: 1px solid #2E5483;
        }

        .accordion-expand {
            background: rgba(0, 0, 0, 0) url("images/ulwxbase.sys/arrow_all.png") no-repeat scroll -48px 0;
        }

        .accordion-collapse {
            background: rgba(0, 0, 0, 0) url("images/ulwxbase.sys/arrow_all.png") no-repeat scroll -32px 0;
        }

        .accordion .accordion-header-selected {
            background: #193156;
            filter: alpha(opacity=100);
            opacity: 1;
            -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=100)";
        }

        .accordion .accordion-header .panel-title {
            color: white;
            margin-left: 20px;
            height: 28px;
            line-height: 28px;
        }

        .panel-tool a {
            opacity: 0.8
        }

        .panel-tool a:hover {
            background-color: transparent;
            opacity: 1
        }

        .accordion-header .icon-plus {
            left: 20px
        }

        .accordion-header .panel-icon, .accordion-header .panel-tool {
            /* 	margin-top: -5px; */
        }

        .accordion-header .panel-tool {
            margin-right: 20px;
        }

        .accordion .accordion-header .panel-icon {
            filter: alpha(opacity=60);
            opacity: 0.6;
            -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=60)";
        }

        .accordion .accordion-header:hover .panel-icon {
            filter: alpha(opacity=100);
            opacity: 1;
            -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=100)";
        }

        .accordion .accordion-header-selected .panel-icon {
            filter: alpha(opacity=100);
            opacity: 1;
            -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=100)";
        }
    </style>


    <script>
        var index = 0;//可以上下键选择列表坐标

        var menuItems = [];
        var menuItemLevel0 = {
            <%
            List<UserRight> rightList=(List<UserRight>)session.getAttribute("rights");
            String aStr="";
            for(int i=0; i<rightList.size(); i++){
                UserRight sr=rightList.get(i);
                if(sr.getCode().endsWith("000")){
                    aStr=aStr+"'"+sr.getRightName()+"':'"+sr.getIcon()+"',";

                }

            };
            if(aStr.length()>0){
                aStr=aStr.substring(0,aStr.length()-1);
                out.write(aStr);
            }

            %>

        };

        var lastClickUrl;
        var lastClickTab = '';

        $(document).ready(function () {
            check();
            $('.accordion-header .icon-plus').each(function () {

                var mnanme = $.trim($(this).prev().text());
                var gval = '';
                $.each(menuItemLevel0, function (key, value) {
                    if (mnanme == key) {
                        gval = value;
                        return false;
                    }
                });
                if (gval) {
                    // $(this).css({
                    // 	'background':'url("images/ulwxbase.sys/menu/'+gval+'_active.png")  no-repeat scroll'
                    //
                    // });
                }


            });
            var hashchangeFun = function () {
                var hash = window.location.hash;
                //alert(hash);
                hash = hash.substring(1); //去掉  "/"字符
                var vars = hash.split("@@");
                if (vars && vars.length && vars.length >= 2) {
                    var title = vars[0];
                    title = decodeURIComponent(title);
                    var href = vars[1];
                    href = decodeURIComponent(href);

                    var refresh = vars[2];
                    var icon = vars[3];
                    //还原状态
                    loadTab(title, href, refresh, icon);
                }

            };
            //$(window).bind('hashchange',hashchangeFun);；

            $('#tabs').tabs({
                border: false,
                plain: true,
                fit: true,
                onSelect: function (title, index) {

                },
                onBeforeClose: function (title, index) {
                    var target = this;
                    var frame = findFrameInTab(title);


                    return true;

                },
                onAdd: function (title, index) {

                }
            });
            //start by tangjk 菜单选定样式

            addTab('首页', 'welcome.jsp', 0, '0', false, {
                tools: [{
                    iconCls: 'icon-mini-refresh',
                    handler: function () {
                        refreshTab({
                            tabTitle: "首页",
                            url: $.pageRoot + "/" + "welcome.jsp",
                            closable: true
                        });
                    }
                }]
            });
            //hashchangeFun();

            $("a").click(function () {
                $(lastClickUrl).removeClass("clicked");
                $(this).addClass("clicked");
                lastClickUrl = this;
                index = parseInt(lastClickUrl.name.split("_")[1]);//切换当前坐标
            });
            //end by tangjk

            $("body").css("visibility", "visible");
            initModifyWin();

            $(document).keydown(function (e) {
                var allLins = $("[name=addTab_" + index + "]");
                switch (e.keyCode) {
                    case 13:
                        window.location.href = lastClickUrl.href;
                        break;
                    case 38:
                        for (var i = 0; i < allLins.length; i++) {
                            var a = allLins[i];
                            if (allLins[i] == lastClickUrl) {
                                if (i == 0) {
                                    $(lastClickUrl).removeClass("clicked");
                                    $(allLins[allLins.length - 1]).addClass("clicked");
                                    lastClickUrl = allLins[allLins.length - 1];
                                } else {
                                    $(lastClickUrl).removeClass("clicked");
                                    $(allLins[i - 1]).addClass("clicked");
                                    lastClickUrl = allLins[i - 1];
                                }
                                return;
                            }
                        }
                        break;
                    case 40:
                        for (var i = 0; i < allLins.length; i++) {
                            if (allLins[i] == lastClickUrl) {
                                if (i == allLins.length - 1) {
                                    $(lastClickUrl).removeClass("clicked");
                                    $(allLins[0]).addClass("clicked");
                                    lastClickUrl = allLins[0];
                                } else {
                                    $(lastClickUrl).removeClass("clicked");
                                    $(allLins[i + 1]).addClass("clicked");
                                    lastClickUrl = allLins[i + 1];
                                }
                                return;
                            }
                        }
                        break;
                }
            });


        });//$(document).ready
        /**document 已经渲染完，一下是function*/

        function iframeTabLoaded(obj) {

            var tab = $('#tabs').tabs('getTab', obj.title);

            $(tab).hideLoading();

        }

        function check() {
            $.ajax({
                url: "<%=request.getContextPath()%>/sys/sys_Login_selectUserInfoJSON.action",
                type: "post",
                data: {},
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    if (data.status == 1) {

                    } else {
                        $.messager.alert("提示", data.message);
                    }
                }
            });
        }

        function loadTab(title, href, refresh, icon, closable, options) {

            if (href.startWith('http://') || href.startWith('HTTP://') || href.startWith('https://') || href.startWith('HTTPS://')) {
            } else {
                href = $.pageRoot + "/" + href;
            }
            //alert(href);
            var tt = $('#tabs');
            if (closable == null || closable == undefined) {
                closable = true;
            }
            if (tt.tabs('exists', title)) {//如果tab已经存在,则选中并刷新该tab
                tt.tabs('select', title);
                if (refresh == null || refresh == 'undefined' || refresh == 1) {
                    refreshTab({
                        tabTitle: title,
                        url: href,
                        closable: closable
                    });
                }


            } else {
                if (href) {
                    var content = '<iframe' + '  title="' + title + '"'
                        + '  style="margin: 0; padding: 0"'
                        + 'width="100%" height="100%" class="my-tab-iframe" onload="iframeTabLoaded(this)"  frameborder="0" scrolling="auto" src="'
                        + href + '" >' + '</iframe>';
                } else {
                    var content = '未实现';
                }

                var opt = $.extend({}, {
                    title: title,
                    closable: closable,
                    content: content,
                    selected: true
                }, (options || {}));

                tt.tabs('add', opt);
                var tab = $('#tabs').tabs('getTab', title);

                $(tab).showLoading({
                    "vPos": $(tab).height() * 0.28,
                    "hPos": $(tab).width() * 0.4

                });


            }

        }

        function addTab(title, href, refresh, icon, closable, opt) {

            if (refresh == null || refresh == 'undefined') {
                refresh = 1;
            }
            if (!icon)
                icon = "0";
            if (closable == null || closable == 'undefined') {
                closable = true;
            }
            if (title == '首页') {
                loadTab(title, href, refresh, icon, closable, opt);
                return;
            }
            if (href.startWith('http://') || href.startWith('HTTP://') || href.startWith('https://') || href.startWith('HTTPS://')) {
            } else {
                href = $.pageRoot + "/" + href;
            }
            var hash = title + "@@" + href + "@@" + refresh + "@@" + icon;
            var curHash = location.hash;
            if (curHash != '') {
                curHash = decodeURIComponent(curHash);
                curHash = curHash.substring(1);

            }
            if (hash != curHash) {
                //alert(hash+";"+curHash);
                //location.hash=hash;

            }
            /////////
            loadTab(title, href, refresh, icon, closable, opt);
        }

        /**
         * 刷新tab
         * @cfg
         *example: {tabTitle:'tabTitle',url:'refreshUrl'}
         *如果tabTitle为空，则默认刷新当前选中的tab
         *如果url为空，则默认以原来的url进行reload
         */
        function refreshTab(cfg) {
            var refresh_tab = cfg.tabTitle ? $('#tabs')
                .tabs('getTab', cfg.tabTitle) : $('#tabs').tabs('getSelected');
            if (refresh_tab && refresh_tab.find('iframe').length > 0) {
                var _refresh_ifram = refresh_tab.find('iframe')[0];
                var refresh_url = cfg.url ? cfg.url : _refresh_ifram.src;
                // _refresh_ifram.contentWindow.location.href=refresh_url;
                var ptitle = refresh_tab.panel('options').title;
                if (refresh_url) {
                    var content = '<iframe' + '  title="' + ptitle + '"'
                        + '  style="margin: 0; padding: 0"'
                        + 'width="100%" height="100%" onload="iframeTabLoaded(this)" frameborder="0" scrolling="auto" src="'
                        + refresh_url + '" >' + '</iframe>';

                } else {
                    var content = '未实现';
                }

                $('#tabs').tabs('update', {
                    tab: refresh_tab,
                    options: {
                        content: content
                    }

                });

                $(refresh_tab).showLoading({
                    "vPos": $(refresh_tab).height() * 0.28,
                    "hPos": $(refresh_tab).width() * 0.4

                });

            }
        }

        function activeTab(title) {
            if (title) {
                $('#tabs').tabs('select', title);
            }
        }

        function closeTab(title) {
            if (title) {
                $('#tabs').tabs('close', title);
            }
        }

        function findFrameInTab(title) {
            var tab = title ? $('#tabs').tabs('getTab', title) : $('#tabs').tabs(
                'getSelected');
            if (tab && tab.find('iframe').length > 0) {
                var _refresh_ifram = tab.find('iframe')[0];
                return _refresh_ifram;
            }
            return null;
        }

        $(function () {
            bindTabEvent();
            bindTabMenuEvent();
        });

        //绑定tab的双击事件、右键事件
        function bindTabEvent() {

            $(".tabs-inner").on('contextmenu', function (e) {
                //alert(5678);
                $('#mm').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                });
                var subtitle = $(this).children("span").text();
                $('#mm').data("currtab", subtitle);
                return false;
            });
        }

        //绑定tab右键菜单事件
        function bindTabMenuEvent() {
            //关闭当前
            $('#mm-tabclose').click(function () {
                var currtab_title = $.trim($('#mm').data("currtab"));
                //根据currtab_title查找tab
                var tabpanel = $("#tabs").tabs("getTab", currtab_title);
                var closable = tabpanel.panel('options').closable;
                if (closable) {
                    //alert(currtab_title);
                    var frame = findFrameInTab(currtab_title);

                    $('#tabs').tabs('close', currtab_title);

                }
            });
            //全部关闭  span.tabs-title
            $('#mm-tabcloseall').click(function () {
                $('.tabs-inner span.tabs-title').each(function (i, n) {
                    if ($(this).parent().next().is('.tabs-close')) {
                        var t = $(n).text();
                        $('#tabs').tabs('close', t);
                    }
                });
            });
            //关闭除当前之外的TAB
            $('#mm-tabcloseother').click(function () {
                var currtab_title = $('#mm').data("currtab");
                $('.tabs-inner span.tabs-title').each(function (i, n) {
                    if ($(this).parent().next().is('.tabs-close')) {
                        var t = $(n).text();
                        if (t != currtab_title)
                            $('#tabs').tabs('close', t);
                    }
                });
            });
            //关闭当前右侧的TAB
            $('#mm-tabcloseright').click(function () {
                var nextall = $('.tabs-selected').nextAll();
                if (nextall.length == 0) {
                    alert('已经是最后一个了');
                    return false;
                }
                nextall.each(function (i, n) {
                    if ($('a.tabs-close', $(n)).length > 0) {
                        var t = $('a:eq(0) span', $(n)).text();
                        $('#tabs').tabs('close', t);
                    }
                });
                return false;
            });
            //关闭当前左侧的TAB
            $('#mm-tabcloseleft').click(function () {
                var prevall = $('.tabs-selected').prevAll();
                if (prevall.length == 1) {
                    alert('已经是第一个了');
                    return false;
                }
                prevall.each(function (i, n) {
                    if ($('a.tabs-close', $(n)).length > 0) {
                        var t = $('a:eq(0) span', $(n)).text();
                        $('#tabs').tabs('close', t);
                    }
                });
                return false;
            });
            //定位菜单项
            $('#mm-tabclosepos').click(function () {
                var currtab_title = $.trim($('#mm').data("currtab"));
                if (menuItems.length) {
                    for (var i = 0; i < menuItems.length; i++) {
                        var three = menuItems[i].split("#");
                        var ctitle = three[0];
                        var ptitle = three[1];
                        var chRightCode = three[2];
                        if (currtab_title == ctitle) {
                            $('#leftmenu').accordion("select", ptitle);

                            var allLins = $("#" + chRightCode);
                            $(lastClickUrl).removeClass("clicked");
                            allLins.addClass("clicked");
                            lastClickUrl = allLins;
                            break;
                        }
                    }

                }

            });
        }


        //############添加修改密码函数 -- linrb
        function initModifyWin() {
            $('#win').window({
                width: 540,
                left: getMiddlePosition(203, 520).left,
                top: getMiddlePosition(203, 520).top,
                closed: true,
                resizable: false,
                minimizable: false,
                maximizable: false,
                modal: true
            });
        }

        function modifyPassView() {
            $("#OriginalPass").val("");
            $("#NewPass").val("");
            $("#VerifyPass").val("");
            $('#win').window('open');
        }

        function modifyPass() {
            //验证
            var OriginalPass = $("#OriginalPass").val();
            var NewPass = $("#NewPass").val();
            var VerifyPass = $("#VerifyPass").val();

            if (NewPass != VerifyPass) {
                $.messager.alert("提示", "确认密码与新密码不一致!!");
                return;
            }

            $('#form2').form('submit', {
                url: "<%=request.getContextPath()%>/sys/sys_SysUser_execModifyPassJSON.action",
                onSubmit: function (param) {
                    return true;
                },
                success: function (data) {
                    if (data == "ok") {
                        $.messager.alert("提示", "修改成功!", "info");
                    } else {
                        $.messager.alert("提示", data, "info");
                    }
                    $('#win').window('close');

                }
            });
        }


        function logout() {
            $.messager.confirm("提示", "确定要退出系统？", function (b) {
                if (b) {
                    window.location.href = "<%=request.getContextPath()%>/sys/logout.action";
                }
            });
        }

        //##
    </script>
</head>
<body id="mybody" class="easyui-layout" style="visibility: hidden;">
<div id="mynorth" region="north"
     data-options="headerCls:'myHeaderCls',style:{position:'relative'},border:false"
     style="height: 85px;" class="north" split="true" title=" ">
    <div id="myheader" style="height: 100%; background: #315B95;">
        <a href="./main.jsp">
            <img src="images/ulwxbase.sys/index_logo_text.png?v=20180120" border="0"
                 style="position: absolute; top: 5px"/>
        </a>
        <span class="myspan">
				<span style="color: white">当前登录用户:<%=info.getUser().getAccount() %>&nbsp;&nbsp;用户ID:<%=info.getUser().getId() %></span>&nbsp;
				<a href="javascript:void(0)" id="toDoElBill" style="color: red"></a> &nbsp;
				<span style="color: white">|</span>&nbsp; 
				<img src="images/ulwxbase.sys/index_log_password.png" width="11" height="11" border="0"/>
				<a href="javascript:void(0)" id="ModifyPassword" onclick="modifyPassView();"
                   style="color: white">修改密码</a> &nbsp;
				<span style="color: white">|</span>&nbsp; 
				<img src="images/ulwxbase.sys/index_eixt_icon.png" width="13" height="13" border="0"/>
				<a href="javascript:void(0);" onclick="logout();" style="color: white"> 退出</a>
			</span>
    </div>
</div>
<div region="west" split="true" title=" "
     data-options="headerCls:'myHeaderMenuCls',border:true"
     style="width: 210px; background: #F5F5F5;">
    <div id="leftmenu" class="easyui-accordion"
         data-options="border:false">
        <%
            List<UserRight> list = (List<UserRight>) session.getAttribute("rights");
            int count = 0;
            for (int i = 0; i < list.size(); i++) {

                UserRight right = list.get(i);
                String rightCode = right.getCode();
                String rightName = right.getRightName().trim();
                String firstLevelTwo = rightCode.substring(0, 2);
                String secondLevelThree = rightCode.substring(2, 5);
                if (secondLevelThree.equals("000")) {
                    out.write("<div iconCls='icon-plus'  class='mymenu' title='" + rightName.trim()
                            + "' style='padding:6px 2px 12px 2px;overflow:auto;' >");

                    int n = 0;
                    for (n = i + 1; n < list.size(); n++) {
                        UserRight chRight = list.get(n);
                        String chRightCode = chRight.getCode();
                        String chRightName = chRight.getRightName().trim();
                        String chFirstLevelTwo = chRightCode.substring(0, 2);
                        String chSecondLevelThree = chRightCode.substring(2, 5);

                        String chUrl = chRight.getRightUrl();
                        if (chSecondLevelThree.equals("000")) {
                            break;
                        }
        %>
        <a id="<%=chRightCode%>" name="addTab_<%=count%>"
           href="javascript:addTab('<%=chRightName.trim()%>','<%=chUrl%>',1);void 0;">
            <img src="images/ulwxbase.sys/menu_icon.png" width="7px" height="7px"
                 border="0" align="bottom"/>&nbsp;<%=chRightName.trim()%>
        </a>
        <script type="text/javascript">
            menuItems.push('<%=chRightName.trim()+"#"+rightName.trim()+"#"+chRightCode%>');
        </script>
        <%
                    }
                    out.write("</div>");
                    i = n - 1;
                    ++count;
                }
            }
        %>


    </div>
</div>

<div border="false" region="center">
    <div id="tabs" class="easyui-tabs" border="false" plain="true"
         fit="true">
    </div>
</div>

<div id="mm" class="easyui-menu" style="width: 150px;">
    <div id="mm-tabclose">关闭</div>
    <div id="mm-tabcloseall">关闭全部</div>
    <div id="mm-tabcloseother">关闭其他</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseright">关闭右侧标签</div>
    <div id="mm-tabcloseleft">关闭左侧标签</div>
    <div class="menu-sep"></div>
    <div id="mm-tabclosepos">定位到菜单</div>
</div>
<div region="south" border="true"
     style="height: 23px; background: #E2EEFE; font-size: 11px; line-height: 23px; text-align: center; overflow: hidden">
    简易贷@2018
</div>


<!--修改密码页面  - linrb -->
<div id="win" class="easyui-window" title="修改密码" data-options="closed:true">
    <form class="commonForm" id="form2">

        <div class="commonForm-items">
            <input class="easyui-passwordbox" data-options="label:'旧密码：',required:true"
                   name="OriginalPass" id="OriginalPass" style="width:100%"
            />
            <div class="form-tips">请输入6到15位的数字字母密码</div>
        </div>
        <div class="error-tips"></div>

        <div class="commonForm-items">

            <input class="easyui-passwordbox" data-options="label:'新密码：',required:true"
                   name="NewPass" id="NewPass" style="width:100%"/>

        </div>
        <div class="commonForm-items">
            <input class="easyui-passwordbox" data-options="label:'确认密码：',required:true"
                   name="VerifyPass" id="VerifyPass" style="width:100%"/>

        </div>

        <div class="btns">
            <a style="margin-right: 15px;" class="easyui-linkbutton"
               onclick="modifyPass();">保存</a> <a class="easyui-linkbutton normalBtn"
                                                 onclick="cancel()">取消</a>
        </div>
    </form>
</div>


</body>
</html>