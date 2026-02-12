<%@ page import="com.ulwx.tool.StringUtils" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
		 errorPage=""%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT"/>
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/images/ulwxbase.sys/favico.ico.png"/>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/js/jquery.load/css/showLoading2.css?v=2125081179" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/js/jquery.easyui/themes/metro-gray/easyui.css?v=2125081179" />

<link rel="stylesheet" type="text/css"    
	href="<%=request.getContextPath()%>/js/jquery.easyui/themes/icon.css?v=2125081179"/>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/js/jquery.easyui/themes/color.css?v=2125081179"/>

<link rel="stylesheet" type="text/css"    
	href="<%=request.getContextPath()%>/css/page.css?v=2125081179" />
	
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/rewrite.css?v=2125081179"/>

<script type="text/javascript">

  var _global_token="<%=StringUtils.trim(session.getAttribute("token"))%>";
</script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/jquery.js?v=2125081179"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/jquery-migrate.js?v=2125081179"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.easyui/jquery.easyui.min.js?v=2125081179"></script>
	
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.easyui/locale/easyui-lang-zh_CN.js?v=2125081179"></script>
	
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.plug/template-web.js?v=2125081179"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.plug/jquery.json.js?v=2125081179"></script>
<script type="text/javascript"     
	src="<%=request.getContextPath()%>/js/jquery.plug/js.cookie.js?v=2125081179"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.load/js/jquery.showLoading2.js?v=2125081179"></script>
<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/jquery.plug/jquery.blockUI.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.plug/jquery.namespace.js?v=2125081179"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.plug/md5.js?v=2125081179"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.plug/common.js?v=2125081179"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.plug/easyui.ext.js?v=2125081179"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.plug/easyui.ext.my.js?v=2125081179"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.plug/easyui.ext.win.js?v=2125081179"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.plug/ext.date.js?v=2125081179"></script>
<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/jquery.easyui.ext/datagrid-export.js?v=2125081179"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/ulwxbase/common.js?v=2125081179"></script>
<script type="text/javascript">
	$.pageRoot = window.location.protocol + "//" + window.location.host + ""+'<%=request.getContextPath()%>';
	$.ContexPath='<%=request.getContextPath()%>';
	$.HTTPPRE=window.location.protocol + "//" + window.location.host;
	
</script>

	