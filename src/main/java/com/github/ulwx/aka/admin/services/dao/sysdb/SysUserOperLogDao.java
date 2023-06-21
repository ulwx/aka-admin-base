package com.github.ulwx.aka.admin.services.dao.sysdb;


import com.github.ulwx.aka.admin.domain.db.sys.SysUserOperLog;
import com.github.ulwx.aka.webmvc.AkaDaoSupport;

/**
 * 用户操作日志Dao
 * @author lenovo
 *
 */
public class SysUserOperLogDao extends AkaDaoSupport {
	
	public  void insertOper(SysUserOperLog obj) throws Exception {
		// TODO Auto-generated method stub
		getTemplate().insertBy( obj);
	}



}
