package com.github.ulwx.aka.admin.domain.db.sys;

import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;

import java.time.LocalDateTime;

/*********************************************
后台session单点登陆表格
***********************************************/
public class SysUsersSession  implements java.io.Serializable {

	private Integer sysUserId;/*后台登陆用户id;len:10*/
	private String sessionId;/*session后台;len:60*/
	private Integer status;/*状态：1.成功 2.失败;len:10*/
	private String loginIp;/*登陆ip;len:20*/
	private LocalDateTime loginTime;/*登陆时间;len:19*/

	public void setSysUserId(Integer sysUserId){
		this.sysUserId = sysUserId;
	}
	public Integer getSysUserId(){
		return sysUserId;
	}
	public void setSessionId(String sessionId){
		this.sessionId = sessionId;
	}
	public String getSessionId(){
		return sessionId;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setLoginIp(String loginIp){
		this.loginIp = loginIp;
	}
	public String getLoginIp(){
		return loginIp;
	}
	public void setLoginTime(LocalDateTime loginTime){
		this.loginTime = loginTime;
	}
	public LocalDateTime getLoginTime(){
		return loginTime;
	}

	public String toString(){
		return  ObjectUtils.toString(this);
	}

	private static final long serialVersionUID =-1301839764L;

}