<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage=""%>
	
<%
  String data=request.getParameter("ret");
  out.write(data);
  out.flush();

%>

