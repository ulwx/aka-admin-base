package com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb;

import com.github.ulwx.aka.admin.domain.db.sys.SysUserRole;
import com.github.ulwx.aka.dbutils.spring.multids.AkaDS;
import com.github.ulwx.aka.dbutils.tool.MD;
import com.github.ulwx.aka.webmvc.AkaDaoSupport;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@AkaDS("${aka.admin-base.ds-name}")
public class SysUserRoleDao extends AkaDaoSupport {

	public  List<SysUserRole> getRoles(Integer userId) throws Exception{
		SysUserRole sur=new SysUserRole();
		sur.setSysUserId(userId);
		
		return getTemplate().queryListBy( sur);
	};
	
	
	public  List<SysUserRole> getAllRoles() throws Exception{
		SysUserRole sur=new SysUserRole();
		return getTemplate().queryListBy( sur);
	};

	
	public  void insert(Integer userId,Integer[] sysRoleIs,
			String updator)throws Exception {
		List<SysUserRole> list=new ArrayList<>();
		for(Integer id:sysRoleIs) {
			SysUserRole sur=new SysUserRole();
			sur.setSysUserId(userId);
			sur.setSysRoleId(id);
			sur.setUpdateTime(LocalDateTime.now());
			sur.setUpdator(updator);
			list.add(sur);
		}

		getTemplate().insertBy(list.toArray(new SysUserRole[0]));
		
	}
	
	public  void del(Integer userId) throws Exception{
		SysUserRole su=new SysUserRole();
		su.setSysUserId(userId);
		getTemplate().delBy(su, MD.of("sysUserId"));
	}
}
