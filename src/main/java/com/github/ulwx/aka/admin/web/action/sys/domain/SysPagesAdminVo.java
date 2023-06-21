package com.github.ulwx.aka.admin.web.action.sys.domain;

import java.time.LocalDateTime;

//用户分配页面权限列表vo类
public class SysPagesAdminVo {

	private int id;
	private String name;
	private String pageName;
	private LocalDateTime updateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
