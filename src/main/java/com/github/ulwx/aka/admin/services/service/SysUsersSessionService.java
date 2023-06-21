package com.github.ulwx.aka.admin.services.service;

import com.github.ulwx.aka.admin.domain.db.sys.SysUsersSession;
import com.github.ulwx.aka.admin.services.dao.sysdb.SysUsersSessionDao;
import com.github.ulwx.aka.webmvc.AkaServiceSupport;
import com.ulwx.type.TInteger;
import org.apache.log4j.Logger;

/**
 * 用户session服务service
 * @author lenovo
 *
 */
public class SysUsersSessionService extends AkaServiceSupport  {


	Logger log=Logger.getLogger(SysUsersSessionService.class);

	public TInteger countUsersSession(String userId,String sessionId,String loginIp) throws Exception {
		//TODO
		try {
			return beanGet.bean(SysUsersSessionDao.class).countUsersSession(userId,sessionId,loginIp);
		}catch(Exception e){
			log.error("",e);
			throw new Exception("***************SysUserSessionService的countUsersSession获取符合条件个数异常"+e.getMessage());
		}
		
	}

	public SysUsersSession getUsersSession(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		try {
			return beanGet.bean(SysUsersSessionDao.class).getUsersSession(userId);
		}catch(Exception e){
			log.error("",e);
			throw new Exception("***************SysUserSessionService的getUsersSession获取session信息异常"+e.getMessage());
		}
	}
	/**
	 * 更新jusersSession信息
	 * @param mySession
	 * @throws Exception
	 */
	public void updateUsersSession(SysUsersSession mySession) throws Exception {
		// TODO Auto-generated method stub
		try {
			beanGet.bean(SysUsersSessionDao.class).updateUsersSession(mySession);
		}catch(Exception e){
			log.error("",e);
			throw new Exception("***************SysUserSessionService的updateUsersSession更新session信息异常"+e.getMessage());
		}
	}
	/**
	 * 插入jusersSession信息
	 * @param juSession
	 * @throws Exception 
	 */
	public void insertUsersSession(SysUsersSession juSession) throws Exception {
		// TODO Auto-generated method stub
		try {
			beanGet.bean(SysUsersSessionDao.class).insertUsersSession(juSession);
		}catch(Exception e){
			log.error("",e);
			throw new Exception("***************SysUserSessionService的updateUsersSession更新insertUsersSession信息异常"+e.getMessage());
		}
	}
	

	public TInteger countAllUsersSession() throws Exception {
		//TODO
		try {
			return beanGet.bean(SysUsersSessionDao.class).countAllUsersSession();
		}catch(Exception e){
			log.error("",e);
			throw new Exception("***************SysUserSessionService的countAllUsersSession获取符合条件个数异常"+e.getMessage());
		}
		
	}


}
