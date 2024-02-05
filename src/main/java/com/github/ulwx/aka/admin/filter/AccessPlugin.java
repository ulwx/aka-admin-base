package com.github.ulwx.aka.admin.filter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface AccessPlugin {

	/**
	 * 在session里存在SessionUserInfo对象是触发
	 * @param hreq
	 * @param hres
	 * @param filter
	 * @return
	 */
	default public  AccessBean doVerify(HttpServletRequest hreq, HttpServletResponse hres, Filter filter) {
		return null;
	};

	/** 在aka.admin-base.access-filter.not-filter-urls配置的urls在排除时触发。
	 * 在针对排除某个URL时，如果配置了此插件，则此方法触发。如果返回true表明正常进行排除。返回false，则不进行排查，走后续流程，后续流程会判断session是否存在User对象
	 * @param hreq
	 * @param hres
	 * @param filter
	 * @return
	 */
	default public Boolean  doBeforeDoNotFilterURL(HttpServletRequest hreq, HttpServletResponse hres, Filter filter) {
		return true;
	};
}
