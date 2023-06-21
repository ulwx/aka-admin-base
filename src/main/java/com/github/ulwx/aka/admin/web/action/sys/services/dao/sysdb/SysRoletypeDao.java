package com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb;


import com.github.ulwx.aka.admin.domain.db.sys.SysRoletype;
import com.github.ulwx.aka.dbutils.spring.multids.AkaDS;
import com.github.ulwx.aka.webmvc.AkaDaoSupport;

import java.util.List;
@AkaDS("${aka.admin-base.ds-name}")
public class SysRoletypeDao extends AkaDaoSupport {
	public  List<SysRoletype> getAllRoles()throws Exception{
		SysRoletype st =new SysRoletype();
		return getTemplate().queryListBy(st );
	}
	
	

}
