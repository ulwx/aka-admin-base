package com.github.ulwx.aka.admin.web.action.sys.services.service.sys;


import com.github.ulwx.aka.admin.domain.db.sys.SysUsersLock;
import com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb.SysUsersLockDao;
import com.github.ulwx.aka.webmvc.AkaServiceSupport;
import org.apache.log4j.Logger;
/**
 * 用户登陆锁定信息
 * @author lenovo
 *
 */
public class SysUsersLockService  extends AkaServiceSupport {
	Logger logger=Logger.getLogger(SysUsersLockService.class);

	public SysUsersLock getSysUsersLockInfo(Integer userId) throws Exception {
		return beanGet.bean(SysUsersLockDao.class).getUsersLockInfo(userId);
		
	}

	public void updateUsersLock(SysUsersLock objLock) throws Exception {
		beanGet.bean(SysUsersLockDao.class).updateUsersLock(objLock);
	}

	public void insertUsersLock(SysUsersLock objLock) throws Exception {
		// TODO Auto-generated method stub
		beanGet.bean(SysUsersLockDao.class).insertUsersLock(objLock);
	}
}
