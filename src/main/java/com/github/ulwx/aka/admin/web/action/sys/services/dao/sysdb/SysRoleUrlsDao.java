package com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb;

import com.github.ulwx.aka.admin.domain.db.sys.SysRole;
import com.github.ulwx.aka.admin.domain.db.sys.SysRoleUrls;
import com.github.ulwx.aka.admin.utils.CbDao;
import com.github.ulwx.aka.admin.web.action.sys.domain.SysRoleUrlsVo;
import com.github.ulwx.aka.dbutils.spring.multids.AkaDS;
import com.github.ulwx.aka.dbutils.tool.MD;
import com.github.ulwx.aka.dbutils.tool.PageBean;
import com.github.ulwx.aka.webmvc.AkaDaoSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AkaDS("${aka.admin-base.ds-name}")
public class SysRoleUrlsDao extends AkaDaoSupport {

	//根据角色ID获取URL - 分页
	public  List<SysRoleUrlsVo> getSysRoleUrlsList(String roleId, Integer pageNum, Integer pageSize, PageBean pb) throws Exception {
		
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("roleId", roleId);

		List<SysRoleUrlsVo> list = getTemplate().queryList( SysRoleUrlsVo.class,
				CbDao.md(SysRoleUrlsDao.class, "getSysRoleUrlsList"), args, pageNum, pageSize, pb, null);
		return list;
	}
	
	//根据角色ID获取URL
	public  List<SysRoleUrlsVo> getSysRoleUrlsList(String roleId) throws Exception {
		
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("roleId", roleId);

		List<SysRoleUrlsVo> list = getTemplate().queryList( SysRoleUrlsVo.class,
				CbDao.md(SysRoleUrlsDao.class, "getSysRoleUrlsList"), args);
		return list;
	}
	
	
	//新增
	public  int addRoleUrls(SysRoleUrls rUrls) throws Exception {
		return getTemplate().insertBy(rUrls);
	}
	
	//删除
	public  int delRoleUrlsById(String id) throws Exception {
		SysRoleUrls u = new SysRoleUrls();
		u.setId(Integer.parseInt(id));
		return getTemplate().delBy(u, MD.of("id"));
	}
	
	//修改
	public  int updateRoleUrlsById(SysRoleUrls urls) throws Exception {
		return getTemplate().updateBy(urls, MD.of("id"));
	}
	
	//角色
	public  List<SysRole> getRoleList(String roleId) throws Exception{
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("roleId", roleId);
		List<SysRole> list = getTemplate().queryList( SysRole.class,
				CbDao.md(SysRoleUrlsDao.class, "getRoleList"), args);
		return list;
	}


}
