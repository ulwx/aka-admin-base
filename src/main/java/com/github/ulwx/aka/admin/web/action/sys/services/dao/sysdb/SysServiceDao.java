package com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb;

import com.github.ulwx.aka.admin.utils.CbDao;
import com.github.ulwx.aka.admin.web.action.sys.domain.SysUserServicePageRight;
import com.github.ulwx.aka.dbutils.spring.multids.AkaDS;
import com.github.ulwx.aka.webmvc.AkaDaoSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 系统用户按钮权限dao
 * @author lenovo
 *
 */
@AkaDS("${aka.admin-base.ds-name}")
public class SysServiceDao extends AkaDaoSupport {

	public  List<SysUserServicePageRight> getSysPagesList(Integer userNo) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userNo", userNo);
		return getTemplate().queryList( SysUserServicePageRight.class, CbDao.md(SysServiceDao.class, "getSysPagesList"), params);
	}
	
}
