package com.github.ulwx.aka.admin.domain;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SessionUserInfo {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserRole[] getRoles() {
		return roles;
	}

	public void setRoles(UserRole[] roles) {
		this.roles = roles;
	}

	public Map<Integer, RoleType> getRoleTypesMap() {
		return roleTypesMap;
	}

	public void setRoleTypesMap(Map<Integer, RoleType> roleTypesMap) {
		this.roleTypesMap = roleTypesMap;
	}



	public Map<Integer, RoleTypeClass> getRoleTypeClassMap() {
		return roleTypeClassMap;
	}

	public void setRoleTypeClassMap(Map<Integer, RoleTypeClass> roleTypeClassMap) {
		this.roleTypeClassMap = roleTypeClassMap;
	}



	public List<UserRight> getRights() {
		return rights;
	}

	public void setRights(List<UserRight> rights) {
		this.rights = rights;
	}

	public List<UserServiceRight> getServiceRightList() {
		return serviceRightList;
	}

	public void setServiceRightList(List<UserServiceRight> serviceRightList) {
		this.serviceRightList = serviceRightList;
	}

	public Object getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(Object extInfo) {
		this.extInfo = extInfo;
	}

	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	public void setSuperAdmin(boolean superAdmin) {
		isSuperAdmin = superAdmin;
	}
}
