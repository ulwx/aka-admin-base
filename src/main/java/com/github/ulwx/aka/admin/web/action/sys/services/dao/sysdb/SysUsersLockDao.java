package com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb;

import com.github.ulwx.aka.admin.domain.db.sys.SysUsersLock;
import com.github.ulwx.aka.admin.utils.CbDao;
import com.github.ulwx.aka.dbutils.spring.multids.AkaDS;
import com.github.ulwx.aka.dbutils.tool.MD;
import com.github.ulwx.aka.webmvc.AkaDaoSupport;

import java.util.HashMap;
import java.util.Map;
@AkaDS("${aka.admin-base.ds-name}")
public class SysUsersLockDao extends AkaDaoSupport {
	public SysUsersLock getUsersLockInfo(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userId", userId);
		SysUsersLock  obj=this.getTemplate().queryOne( SysUsersLock.class, CbDao.md(SysUsersLockDao.class, "getUsersLock"), params);
		return obj;
	}

	public  void updateUsersLock(SysUsersLock objLock) throws Exception {
		// TODO Auto-generated method stub
		getTemplate().updateBy(objLock, MD.of("SysUserId"));
	}

	public  void insertUsersLock(SysUsersLock objLock) throws Exception {
		// TODO Auto-generated method stub
		getTemplate().insertBy(objLock);
	}

}
