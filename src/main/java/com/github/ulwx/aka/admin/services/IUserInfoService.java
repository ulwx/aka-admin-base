package com.github.ulwx.aka.admin.services;


import com.github.ulwx.aka.admin.domain.SessionUserInfo;

public interface IUserInfoService {

	public SessionUserInfo getUserInfo(String account,String userName) throws Exception ;
}
