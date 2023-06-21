package com.github.ulwx.aka.admin.web.action.sys.domain;

import com.github.ulwx.aka.admin.domain.db.sys.SysUser;

public class AdminUserInfo extends SysUser {

	private String sysRoleTypeCodes;
	private String sysRoleTypeNames;
	private String sysRoleIds;
	private String sysRoleNames;

	public String getSysRoleTypeCodes() {
		return sysRoleTypeCodes;
	}
	public void setSysRoleTypeCodes(String sysRoleTypeCodes) {
		this.sysRoleTypeCodes = sysRoleTypeCodes;
	}
	public String getSysRoleTypeNames() {
		return sysRoleTypeNames;
	}
	public void setSysRoleTypeNames(String sysRoleTypeNames) {
		this.sysRoleTypeNames = sysRoleTypeNames;
	}

	public String getSysRoleIds() {
		return sysRoleIds;
	}
	public void setSysRoleIds(String sysRoleIds) {
		this.sysRoleIds = sysRoleIds;
	}
	public String getSysRoleNames() {
		return sysRoleNames;
	}
	public void setSysRoleNames(String sysRoleNames) {
		this.sysRoleNames = sysRoleNames;
	}

	

	
}
