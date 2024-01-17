package com.github.ulwx.aka.admin.services.dao.sysdb;

import com.github.ulwx.aka.admin.domain.db.sys.SysUsersSession;
import com.github.ulwx.aka.admin.utils.CbDao;
import com.github.ulwx.aka.dbutils.spring.multids.AkaDS;
import com.github.ulwx.aka.dbutils.tool.MD;
import com.github.ulwx.aka.webmvc.AkaDaoSupport;
import com.ulwx.type.TInteger;

import java.util.HashMap;
import java.util.Map;
@AkaDS("${aka.admin-base.ds-name}")
public class SysUsersSessionDao extends AkaDaoSupport {
	
	
	public  TInteger countUsersSession(String userId, String sessionId, String loginIp) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("sessionId", sessionId);
		params.put("loginIp", loginIp);
		return getTemplate().queryOne( TInteger.class, CbDao.md(SysUsersSessionDao.class, "countUsersSession"), params);
	}

	public SysUsersSession getUsersSession(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userId", userId);
		return getTemplate().queryOne( SysUsersSession.class, CbDao.md(SysUsersSessionDao.class, "getUsersSession"), params);
	}

	public  void updateUsersSession(SysUsersSession mySession) throws Exception {
		// TODO Auto-generated method stub
		getTemplate().updateBy(mySession, MD.of("SysUserId"));
	}

	public  void insertUsersSession(SysUsersSession juSession) throws Exception {
		// TODO Auto-generated method stub
		getTemplate().insertBy( juSession);
	}
	/**
	 * 获取当前session一分钟内的登陆数资源
	 * @return
	 * @throws Exception 
	 */
	public  TInteger countAllUsersSession() throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		return getTemplate().queryOne( TInteger.class, CbDao.md(SysUsersSessionDao.class, "countCurrentOneMiniteSession"), params);
	}

}
