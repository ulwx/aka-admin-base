package com.github.ulwx.aka.admin.services;


import com.github.ulwx.aka.admin.domain.SessionUserInfo;
import com.github.ulwx.aka.admin.web.action.sys.domain.AdminUserInfo;
import com.github.ulwx.aka.dbutils.tool.PageBean;

import java.util.List;

public interface IUserInfoService {

	public SessionUserInfo getUserInfo(String account,String userName) throws Exception ;
	public void addUser(AdminUserInfo adminUserInfo)throws Exception ;
	public void delUser(AdminUserInfo[]  adminUserInfos) throws Exception ;
	public void editUser(AdminUserInfo adminUserInfo) throws Exception ;
	public List<AdminUserInfo> getUserList(String userName,
										   String userPhone,
										   String enable,
										   int pageNum, int perPage, PageBean pb)throws Exception;
}
