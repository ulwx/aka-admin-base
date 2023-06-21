package com.github.ulwx.aka.admin.filter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface AccessPlugin {

	public AccessBean doVerify(HttpServletRequest hreq, HttpServletResponse hres, Filter filter) ;
}
