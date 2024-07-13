package com.github.ulwx.aka.admin.web.action.sys.domain;

import java.time.LocalDateTime;

//用户分配页面权限列表vo类
public class SysPagesAdminVo {

	private int id;
	private String name;
	private String account;
	private String pageName;
	private Integer serviceRight;
	private String serviceRightName;
	private LocalDateTime updateTime;

	public String getServiceRightName() {
		return serviceRightName;
	}

	public void setServiceRightName(String serviceRightName) {
		this.serviceRightName = serviceRightName;
	}

	public Integer getServiceRight() {
		return serviceRight;
	}

	public void setServiceRight(Integer serviceRight) {
		this.serviceRight = serviceRight;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

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
