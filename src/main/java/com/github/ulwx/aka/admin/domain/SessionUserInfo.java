package com.github.ulwx.aka.admin.domain;


import com.github.ulwx.aka.webmvc.SessionUser;
import com.github.ulwx.aka.webmvc.user.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SessionUserInfo implements SessionUser {
	private User user;
	private UserRole[] roles;// 存放角色id
	//RoleTypeCode->RoleType对象映射，RoleTypeCode一般对应岗位，用于处理数据权限，比如上级可以查看下级的数据
	private Map<Integer, RoleType> roleTypesMap;
	//RoleTypeClassCode->RoleTypeClass对象的映射，RoleTypeClassCode一般对应部门
	private Map<Integer, RoleTypeClass> roleTypeClassMap;
	private List<UserRight>  rights;
	private List<UserServiceRight> serviceRightList=new ArrayList<>();
	//用户信息扩展信息
	private Object extInfo;
	//是否是超级管理员
	private boolean isSuperAdmin=false;

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public String getUserName() {
		return this.getUser().getName();
	}

	@Override
	public String getAccount() {
		 return this.getUser().getAccount();
	}

	@Override
	public String getPhoneNumber() {
		return this.getUser().getPhone();
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public UserRole[] getRoles() {
		return roles;
	}

	@Override
	public void setRoles(UserRole[] roles) {
		this.roles = roles;
	}

	@Override
	public Map<Integer, RoleType> getRoleTypesMap() {
		return roleTypesMap;
	}

	@Override
	public void setRoleTypesMap(Map<Integer, RoleType> roleTypesMap) {
		this.roleTypesMap = roleTypesMap;
	}



	@Override
	public Map<Integer, RoleTypeClass> getRoleTypeClassMap() {
		return roleTypeClassMap;
	}

	@Override
	public void setRoleTypeClassMap(Map<Integer, RoleTypeClass> roleTypeClassMap) {
		this.roleTypeClassMap = roleTypeClassMap;
	}



	@Override
	public List<UserRight> getRights() {
		return rights;
	}

	@Override
	public void setRights(List<UserRight> rights) {
		this.rights = rights;
	}

	@Override
	public List<UserServiceRight> getServiceRightList() {
		return serviceRightList;
	}

	@Override
	public void setServiceRightList(List<UserServiceRight> serviceRightList) {
		this.serviceRightList = serviceRightList;
	}

	@Override
	public Object getExtInfo() {
		return extInfo;
	}

	@Override
	public void setExtInfo(Object extInfo) {
		this.extInfo = extInfo;
	}

	@Override
	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	@Override
	public void setSuperAdmin(boolean superAdmin) {
		isSuperAdmin = superAdmin;
	}
}
