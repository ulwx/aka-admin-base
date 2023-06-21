package com.github.ulwx.aka.admin.filter;

import com.ulwx.tool.ArrayUtils;
import com.ulwx.tool.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Servlet Filter implementation class XSSFilter
 */
public class XSSFilter implements Filter {

	private String notFilterURLs = "";
	private String filterURLs = "";
	
	public void init(FilterConfig filterConfig) throws ServletException {
		String notFilterURLStr = StringUtils.trim(filterConfig
				.getInitParameter("notFilterURLs"));
		String filterURLStr = StringUtils.trim(filterConfig
				.getInitParameter("filterURLs"));

		notFilterURLs = notFilterURLStr;
		filterURLs = filterURLStr;
	}


	public void destroy() {

	}


	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hreq = (HttpServletRequest) req;
		String ruri = hreq.getRequestURI();
		String rur3 = hreq.getContextPath();
		if (StringUtils.hasText(notFilterURLs)) {
			String[] strs = StringUtils.split(notFilterURLs, ",");
			if (ArrayUtils.isNotEmpty(strs)) {
				for (int i = 0; i < strs.length; i++) {
					if (strs[i].startsWith("/")) {
						if (ruri.startsWith(rur3 + strs[i])) {
							chain.doFilter(req, res);
							return;
						}
					} else {
						if (StringUtils.endsWith(ruri, strs[i], false)) {
							chain.doFilter(req, res);
							return;
						}
					}
				}
			}

		}
		
		chain.doFilter(new XSSRequestWrapper((HttpServletRequest) req), res);
		return;
		
	}
	



	

}
