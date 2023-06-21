package com.github.ulwx.aka.admin.web.action.sys.services.service.sys;

import com.github.ulwx.aka.admin.domain.db.sys.*;
import com.github.ulwx.aka.admin.web.action.sys.domain.SysPagesAdminVo;
import com.github.ulwx.aka.admin.web.action.sys.domain.SysPagesVo;
import com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb.SysPagesDao;
import com.github.ulwx.aka.dbutils.tool.PageBean;
import com.github.ulwx.aka.webmvc.AkaServiceSupport;
import com.ulwx.type.TInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

public class SysPagesService extends AkaServiceSupport {
	public final static Logger log = LoggerFactory.getLogger(SysPagesService.class);


	public List<SysPagesVo> getPageList(String pageName, int pageNum, int perPage , PageBean pb) throws Exception{
		return beanGet.bean(SysPagesDao.class).getPageList(pageName, pageNum, perPage, pb);
	}
	
	public void AddPage(SysPages page) throws Exception {
		beanGet.bean(SysPagesDao.class).AddPage(page);
	}
	
	public void updatePage(SysPages page) throws Exception {

		beanGet.bean(SysPagesDao.class).updatePage(page);
	}
	
	public List<SysPagesAdminVo> getPageAdminList(String name, int pageNum, int perPage ,
												  PageBean pb) throws Exception{
		return beanGet.bean(SysPagesDao.class).getPageAdminList(name, pageNum, perPage, pb);
	}
	
	public List<SysUser> getSysload() throws Exception{
		return beanGet.bean(SysPagesDao.class).getSysload();
	}
	
	
	public List<SysPages> getloadPageName() throws Exception{
		return beanGet.bean(SysPagesDao.class).getloadPageName();
	}
	
	public List<SysServiceRight> getloadRightName() throws Exception{
		return beanGet.bean(SysPagesDao.class).getloadRightName();
	}
	
	public int addRigthUser(int pageId,int rightCode,Integer[] names) throws Exception {
		//根据pageId和rightCode查询sys_pages_service_right表中是否有记录
		TInteger rightId = beanGet.bean(SysPagesDao.class).getRight(pageId, rightCode);

		if(rightId != null) {//如果有，该表不做重复插入，只操作sys_pages_service_right_user表
			int ServiceRightId = rightId.getValue();
			for(int i=0;i<names.length;i++) {
				SysPagesServiceRightUser SysUser = new SysPagesServiceRightUser();
				SysUser.setUpdatime(LocalDateTime.now());
				SysUser.setPageServiceRightId(ServiceRightId);
				SysUser.setSysUserId(names[i]);
				beanGet.bean(SysPagesDao.class).addPageServiceRightUser( SysUser);
			}
		}else {//如果没有，则两张表同时插入数据
			SysPagesServiceRight right = new SysPagesServiceRight();
			right.setPageId(pageId);
			right.setServiceRightCode(rightCode);
			right.setUpdatime(LocalDateTime.now());
			int id = beanGet.bean(SysPagesDao.class).addPageServiceRight( right);
			if(id>0) {
				for(int i=0;i<names.length;i++) {
					SysPagesServiceRightUser SysUser = new SysPagesServiceRightUser();
					SysUser.setPageServiceRightId(id);
					SysUser.setSysUserId(names[i]);
					SysUser.setUpdatime(LocalDateTime.now());
					beanGet.bean(SysPagesDao.class).addPageServiceRightUser( SysUser);
				}

			}
		}

		return 1;
	}
	
	public TInteger countUserById(int userId,int pageId,int rightCode) throws Exception {
		return beanGet.bean(SysPagesDao.class).countUserById(userId,pageId,rightCode);
	}
	
	public void deleteServiceRightUser(SysPagesServiceRightUser serviceRightUser) throws Exception {
		beanGet.bean(SysPagesDao.class).deleteServiceRightUser( serviceRightUser);
	}
}
