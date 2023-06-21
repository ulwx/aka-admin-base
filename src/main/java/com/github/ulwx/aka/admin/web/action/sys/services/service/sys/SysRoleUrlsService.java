package com.github.ulwx.aka.admin.web.action.sys.services.service.sys;

import com.github.ulwx.aka.admin.domain.db.sys.SysRole;
import com.github.ulwx.aka.admin.domain.db.sys.SysRoleUrls;
import com.github.ulwx.aka.admin.web.action.sys.domain.SysRoleUrlsVo;
import com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb.SysRoleUrlsDao;
import com.github.ulwx.aka.dbutils.tool.PageBean;
import com.github.ulwx.aka.webmvc.AkaServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SysRoleUrlsService  extends AkaServiceSupport {
	static Logger log = LoggerFactory.getLogger(SysRoleUrlsService.class);

	//根据roleId获取URL -分页
	public List<SysRoleUrlsVo> getSysRoleUrlsList(String roleId, int pageNum, int perPage, PageBean pb) throws Exception{
		return beanGet.bean(SysRoleUrlsDao.class).getSysRoleUrlsList(roleId,pageNum, perPage, pb);
	}
	
	//根据roleId获取URL
	public List<SysRoleUrlsVo> getSysRoleUrlsList(String roleId) throws Exception{
		return beanGet.bean(SysRoleUrlsDao.class).getSysRoleUrlsList(roleId);
	}
	
	//新增角色URL
	public void addRoleUrls(SysRoleUrls rUrls) throws Exception {
		beanGet.bean(SysRoleUrlsDao.class).addRoleUrls( rUrls);
	}
	
	//删除角色菜单
	public void delRoleUrlsById(String id) throws Exception {
		beanGet.bean(SysRoleUrlsDao.class).delRoleUrlsById(id);
	}
	
	//修改
	public void updateRoleUrlsById(SysRoleUrls urls) throws Exception {
		beanGet.bean(SysRoleUrlsDao.class).updateRoleUrlsById(urls);
	}
	
	//角色
	public List<SysRole> getRoleList(String roleId) throws Exception{
		return beanGet.bean(SysRoleUrlsDao.class).getRoleList(roleId);
	}
	
}
