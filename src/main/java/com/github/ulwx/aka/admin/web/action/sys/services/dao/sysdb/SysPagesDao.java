package com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb;

import com.github.ulwx.aka.admin.domain.db.sys.*;
import com.github.ulwx.aka.admin.utils.CbDao;
import com.github.ulwx.aka.admin.web.action.sys.domain.SysPagesAdminVo;
import com.github.ulwx.aka.admin.web.action.sys.domain.SysPagesVo;
import com.github.ulwx.aka.dbutils.spring.multids.AkaDS;
import com.github.ulwx.aka.dbutils.tool.MD;
import com.github.ulwx.aka.dbutils.tool.PageBean;
import com.github.ulwx.aka.webmvc.AkaDaoSupport;
import com.ulwx.type.TInteger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@AkaDS("${aka.admin-base.ds-name}")
public class SysPagesDao extends AkaDaoSupport {

	public  List<SysPagesVo> getPageList(String pageName, int pageNum, int perPage , PageBean pb) throws Exception{
		Map<String ,Object> arg = new HashMap<String,Object>();
		arg.put("pageName", pageName);
		return getTemplate().queryList( SysPagesVo.class, CbDao.md(SysPagesDao.class, "getPageList"), arg, pageNum, perPage, pb, null);
	}
	
	public  int AddPage(SysPages page) throws Exception {
		return getTemplate().insertBy(page);
	}
	
	public  int updatePage(SysPages page) throws Exception {
		return getTemplate().updateBy(page, MD.of("id"));
	}
	
	public  List<SysPagesAdminVo> getPageAdminList(String name, int pageNum, int perPage , PageBean pb) throws Exception{
		Map<String ,Object> arg = new HashMap<String,Object>();
		arg.put("name", name);
		
		return getTemplate().queryList( SysPagesAdminVo.class, CbDao.md(SysPagesDao.class, "getPageAdminList"), arg, pageNum, perPage, pb, null);
	}
	
	public  List<SysUser> getSysload() throws Exception{
		Map<String ,Object> arg = new HashMap<String,Object>();
		return getTemplate().queryList( SysUser.class, CbDao.md(SysPagesDao.class, "getSysload"), arg);
	}
	

	
	public  List<SysPages> getloadPageName() throws Exception{
		Map<String ,Object> arg = new HashMap<String,Object>();
		return getTemplate().queryList(SysPages.class, CbDao.md(SysPagesDao.class, "getloadPageName"), arg);
	}
	
	public  List<SysServiceRight> getloadRightName() throws Exception{
		Map<String ,Object> arg = new HashMap<String,Object>();
		return getTemplate().queryList(SysServiceRight.class, CbDao.md(SysPagesDao.class, "getloadRightName"), arg);
	}
	
	public  int addPageServiceRight( SysPagesServiceRight right) throws Exception {
		return (int) getTemplate().insertReturnKeyBy(right);
	}
	
	public  int addPageServiceRightUser( SysPagesServiceRightUser rightUser) throws Exception {
		return getTemplate().insertBy(rightUser);
	}
	
	public  TInteger countUserById(int userId,int pageId,int rightCode) throws Exception {
		Map<String ,Object> arg = new HashMap<String,Object>();
		arg.put("userId", userId);
		arg.put("pageId", pageId);
		arg.put("rightCode", rightCode);
		return getTemplate().queryOne(TInteger.class, CbDao.md(SysPagesDao.class, "countUserById"), arg);
	}
	
	public  TInteger getRight(int pageId,int rightCode) throws Exception {
		Map<String ,Object> arg = new HashMap<String,Object>();
		arg.put("pageId", pageId);
		arg.put("rightCode", rightCode);
		return getTemplate().queryOne( TInteger.class, CbDao.md(SysPagesDao.class, "getRight"), arg);
	}
	
	public  int deleteServiceRightUser( SysPagesServiceRightUser serviceRightUser) throws Exception {
		return getTemplate().delBy(serviceRightUser, MD.of("id"));
	}


}
