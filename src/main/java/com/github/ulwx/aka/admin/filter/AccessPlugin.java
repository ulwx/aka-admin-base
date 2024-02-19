package com.github.ulwx.aka.admin.filter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface AccessPlugin {


	/**
	 * 前置处理
	 * @param hreq
	 * @param hres
	 * @param filter
	 * @return  返回true则继续后面插件的处理，返回false则终止过滤器
	 */
	default public  void preHandle(HttpServletRequest hreq, HttpServletResponse hres, Filter filter) {
	};
	/**
	 * 在session里存在SessionUserInfo对象时触发
	 * @param hreq
	 * @param hres
	 * @param filter
	 * @return  如果为null，则继续后面的处理，不为null则过滤器终止并返回AccessBean对象
	 */
	default public  AccessBean doVerify(HttpServletRequest hreq, HttpServletResponse hres, Filter filter) {
		return null;
	};

	/** 在处理aka.admin-base.access-filter.not-filter-urls配置的urls进行排除时触发。
	 * 在针对排除某个URL时，如果配置了此插件，则此方法触发。如果返回true表明正常进行排除。返回false，则不进行排除，走后续流程，后续流程会判断session是否存在User对象
	 * @param hreq
	 * @param hres
	 * @param filter
	 * @return
	 */
	default public Boolean  doBeforeDoNotFilterURL(HttpServletRequest hreq, HttpServletResponse hres, Filter filter) {
		return true;
	};
}
