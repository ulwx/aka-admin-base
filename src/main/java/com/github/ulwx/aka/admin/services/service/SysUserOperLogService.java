package com.github.ulwx.aka.admin.services.service;

import com.github.ulwx.aka.admin.domain.db.sys.SysUserOperLog;
import com.github.ulwx.aka.admin.services.dao.sysdb.SysUserOperLogDao;
import com.github.ulwx.aka.webmvc.AkaServiceSupport;
import org.apache.log4j.Logger;

/**
 * 用户操作日志 service
 * @author lenovo
 *
 */
public class SysUserOperLogService extends AkaServiceSupport {
	Logger logger=Logger.getLogger(SysUserOperLogService.class);

	
	/**
	 * 插入用户操作记录
	 * @param obj
	 * @throws Exception
	 */
	public void insertUserOper(SysUserOperLog obj)throws Exception {
		try {
			beanGet.bean(SysUserOperLogDao.class).insertOper(obj);
		}catch(Exception e) {
			logger.error("",e);
			throw new Exception("#######################ERROR##########UserOperLogService插入用户操作日志异常");
			
		}
		
	};
	
	
	
	
}
