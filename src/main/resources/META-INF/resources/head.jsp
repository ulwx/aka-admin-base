<%@ page import="com.ulwx.tool.StringUtils" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
		 errorPage=""%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT"/>
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/images/ulwxbase.sys/favico.ico.png"/>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/js/jquery.load/css/showLoading2.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/js/jquery.easyui/themes/metro-gray/easyui.css" />

<link rel="stylesheet" type="text/css"    
	href="<%=request.getContextPath()%>/js/jquery.easyui/themes/icon.css"/>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/js/jquery.easyui/themes/color.css"/>

<link rel="stylesheet" type="text/css"    
	href="<%=request.getContextPath()%>/css/page.css?v=202203089" />
	
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/rewrite.css?v=202203089"/>

<script type="text/javascript">

  var _global_token="<%=StringUtils.trim(session.getAttribute("token"))%>";
</script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/jquery.js"></script>  
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/jquery-migrate.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.easyui/jquery.easyui.min.js"></script>
	
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.easyui/locale/easyui-lang-zh_CN.js"></script>
	
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.plug/template-web.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.plug/jquery.json.js"></script>
<script type="text/javascript"     
	src="<%=request.getContextPath()%>/js/jquery.plug/js.cookie.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.load/js/jquery.showLoading2.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.plug/jquery.namespace.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.plug/md5.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.plug/common.js"></script>	

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.plug/easyui.ext.js"></script>  
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.plug/easyui.ext.my.js"></script> 
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.plug/easyui.ext.win.js"></script> 
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.plug/ext.date.js"></script> 
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/ulwxbase/common.js"></script>
<!--  $.pageRoot -->
<script type="text/javascript">
	$.pageRoot = window.location.protocol + "//" + window.location.host + ""+'<%=request.getContextPath()%>';
	$.ContexPath='<%=request.getContextPath()%>';
	
</script>

	