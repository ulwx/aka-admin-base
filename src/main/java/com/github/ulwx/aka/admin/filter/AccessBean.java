package com.github.ulwx.aka.admin.filter;

public class AccessBean {

	private int status=1;//0：失败  1：成功
	private int errorCode=0;//错误码
	private String message="成功";//描述
	private int isExit=0;//是否退出
	
	
	public int getIsExit() {
		return isExit;
	}
	public void setIsExit(int isExit) {
		this.isExit = isExit;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


}
