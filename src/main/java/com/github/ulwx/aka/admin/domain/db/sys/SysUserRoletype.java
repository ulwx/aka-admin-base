package com.github.ulwx.aka.admin.domain.db.sys;

import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;

import java.time.LocalDateTime;

/*********************************************
用户角色表
***********************************************/
public class SysUserRoletype  implements java.io.Serializable {

	private Integer sysUserRoletypeSno;/*流水号;len:10*/
	private Integer sysUserId;/*用户ID;len:10*/
	private Integer sysRoletypeCode;/*角色类型;len:10*/
	private LocalDateTime updateTime;/*更新时间;len:19*/
	private String updator;/*更新人;len:30*/

	public void setSysUserRoletypeSno(Integer sysUserRoletypeSno){
		this.sysUserRoletypeSno = sysUserRoletypeSno;
	}
	public Integer getSysUserRoletypeSno(){
		return sysUserRoletypeSno;
	}
	public void setSysUserId(Integer sysUserId){
		this.sysUserId = sysUserId;
	}
	public Integer getSysUserId(){
		return sysUserId;
	}
	public void setSysRoletypeCode(Integer sysRoletypeCode){
		this.sysRoletypeCode = sysRoletypeCode;
	}
	public Integer getSysRoletypeCode(){
		return sysRoletypeCode;
	}
	public void setUpdateTime(LocalDateTime updateTime){
		this.updateTime = updateTime;
	}
	public LocalDateTime getUpdateTime(){
		return updateTime;
	}
	public void setUpdator(String updator){
		this.updator = updator;
	}
	public String getUpdator(){
		return updator;
	}

	public String toString(){
		return  ObjectUtils.toString(this);
	}

	private static final long serialVersionUID =1784970631L;

}