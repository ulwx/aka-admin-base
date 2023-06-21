package com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb;

import com.github.ulwx.aka.admin.domain.db.sys.SysUserRoletype;
import com.github.ulwx.aka.dbutils.spring.multids.AkaDS;
import com.github.ulwx.aka.dbutils.tool.MD;
import com.github.ulwx.aka.webmvc.AkaDaoSupport;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@AkaDS("${aka.admin-base.ds-name}")
public class SysUserRoletypeDao extends AkaDaoSupport {
	public  List<SysUserRoletype> getRoletypes(Integer userId) throws Exception{
		SysUserRoletype sur=new SysUserRoletype();
		sur.setSysUserId(userId);
		
		return getTemplate().queryListBy( sur);
	};
	
	public  void insert(Integer userId,Integer[] sys_roletype_codes,
			String updator)throws Exception {
		List<SysUserRoletype> list=new ArrayList<>();
		for(Integer code:sys_roletype_codes) {
			SysUserRoletype sur=new SysUserRoletype();
			sur.setSysUserId(userId);
			sur.setSysRoletypeCode(code);
			sur.setUpdateTime(LocalDateTime.now());
			sur.setUpdator(updator);
			list.add(sur);
		}

		getTemplate().insertBy(list.toArray(new SysUserRoletype[0]));
		
	}
	
	public  void del(Integer userId) throws Exception{
		SysUserRoletype su=new SysUserRoletype();
		su.setSysUserId(userId);
		getTemplate().delBy(su, MD.of("sysUserId"));
	}
}
