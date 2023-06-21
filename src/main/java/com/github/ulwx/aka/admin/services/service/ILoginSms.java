package com.github.ulwx.aka.admin.services.service;

public interface ILoginSms {

	public int send(String mobile,String content);
	
	public int send(String type,String numbers, String msgContent);
}
