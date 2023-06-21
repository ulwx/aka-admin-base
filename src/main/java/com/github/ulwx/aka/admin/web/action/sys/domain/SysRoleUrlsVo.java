package com.github.ulwx.aka.admin.web.action.sys.domain;

import com.github.ulwx.aka.admin.domain.db.sys.SysRoleUrls;

public class SysRoleUrlsVo extends SysRoleUrls {

	private static final long serialVersionUID = 1L;
	
	private String roleName;

	public synchronized String getRoleName() {
		return roleName;
	}

	public synchronized void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
	
}
