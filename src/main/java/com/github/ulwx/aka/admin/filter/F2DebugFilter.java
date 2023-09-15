package com.github.ulwx.aka.admin.filter;

import com.github.ulwx.aka.admin.domain.SessionUserInfo;
import com.github.ulwx.aka.admin.utils.AkaServiceUtils;
import com.github.ulwx.aka.admin.utils.CbAppConfigProperties;
import com.github.ulwx.aka.webmvc.BeanGet;
import com.github.ulwx.aka.webmvc.web.action.ActionSupport;
import com.ulwx.tool.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@WebFilter( urlPatterns = {"*.jsp","*.action","/swagger-ui/*"})
@Order(11)
public class F2DebugFilter implements Filter {
	private static Logger log = LoggerFactory.getLogger(F2DebugFilter.class);
	private String userName;
	private boolean needDebug=false;
	public static String debug_filter_put_a_user="debug.filter.put.a.user";

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hreq = (HttpServletRequest) request; 
		HttpServletResponse hres = (HttpServletResponse) response;  
		String userAccount = this.userName;
		HttpSession session=hreq.getSession();
		String contextPath = hreq.getContextPath();
	
		try {
			Set<String> notFilterURLs=ProtocoURLsUtils.getProtocolPrefex(request.getServletContext());
			String ruri = hreq.getRequestURI();
			if (notFilterURLs !=null && !notFilterURLs.isEmpty()) {
				String[] strs = notFilterURLs.toArray(new String[0]);
				if (ArrayUtils.isNotEmpty(strs)) {
					for (int i = 0; i < strs.length; i++) {
						if (strs[i].startsWith("/")) {
							if (ruri.startsWith(contextPath + strs[i])) {
								chain.doFilter(hreq, hres);
								return;
							}
						}
					}
				}
			}
			if( ActionSupport.getUserInfo(hreq)==null){
				if(needDebug) {
					log.debug("debug.filter");
					SessionUserInfo userInfo  = AkaServiceUtils.getUserInfoService().getUserInfo(userAccount);
					if(userInfo!=null){
						ActionSupport.setUserInfo(hreq,userInfo);
						session.setAttribute("rights", userInfo.getRights());
						session.setAttribute(debug_filter_put_a_user,true);

					}
				}
			
			}

		} catch (Exception e) {
			log.error(""+e,e);
			throw new RuntimeException(e);
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig Config) throws ServletException {
		// TODO Auto-generated method stub
		CbAppConfigProperties properties = BeanGet.getBean(CbAppConfigProperties.class,Config.getServletContext());
		//log.debug("CbAppConfigProperties:"+ ObjectUtils.toStringUseFastJson(properties));
		if(properties.getDebugFilter()!=null) {
			userName = properties.getDebugFilter().getUsername();
			log.debug("userNameJ:"+userName);
			needDebug = properties.getDebugFilter().getEnable();
		}else{
			log.debug(""+properties.getDebugFilter());
		}
	}

}
