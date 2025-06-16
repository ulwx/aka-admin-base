package com.github.ulwx.aka.admin.filter;

import com.github.ulwx.aka.admin.domain.SessionUserInfo;
import com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb.SysRightDao;
import com.github.ulwx.aka.webmvc.BeanGet;
import com.github.ulwx.aka.webmvc.WebMvcCbConstants;
import com.github.ulwx.aka.webmvc.user.UserRight;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class URLControlFilter implements AccessPlugin {
	Logger log=Logger.getLogger(URLControlFilter.class);
	@Override
	public AccessBean doVerify(HttpServletRequest hreq, HttpServletResponse hres, Filter filter) {
		// TODO Auto-generated method stub
		HttpSession session=hreq.getSession();
		SessionUserInfo userInfo=(SessionUserInfo) session.getAttribute(WebMvcCbConstants.USER);
		AccessBean aceBean=new AccessBean();
		String ruri=hreq.getRequestURI();
		String contextPath=hreq.getContextPath();
		String sysRightURL=ruri.replaceFirst(contextPath+"/", "");
		try {
			int count = BeanGet.getBean(SysRightDao.class,hreq.getServletContext()).getDataCountByUrl(sysRightURL);
			if(count==0) {//不存在菜单列表中
				aceBean.setErrorCode(0);
				aceBean.setMessage("");
				aceBean.setStatus(1);
			}else {//存在菜单列表中
				//1.获取角色的SysRight，并判断角色菜单中是否包含本列表
				List<UserRight> sysRightList=userInfo.getRights();
				List<String> urlSet=new ArrayList<String>(); 
				for(UserRight userRight:sysRightList) {
					urlSet.add(userRight.getRightUrl());
				}
				if(urlSet.contains(sysRightURL)) {//包含
					aceBean.setErrorCode(0);
					aceBean.setMessage("");
					aceBean.setStatus(1);
				}else {//不包含
					aceBean.setErrorCode(0);
					aceBean.setMessage("越权访问,您没有访问当前页面的权限");
					aceBean.setStatus(0);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("",e);
			aceBean.setErrorCode(0);
			aceBean.setMessage("越权访问,您没有访问当前页面的权限");
			aceBean.setStatus(0);
		}
		return aceBean;
	}
	
    
	
}
