package com.github.ulwx.aka.admin.domain.db.sys;

import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;

import java.time.LocalDateTime;

/*********************************************
用户角色表
***********************************************/
public class SysUserRole implements java.io.Serializable {

	private Integer sysUserRoleSno;/*流水号;len:10*/
	private Integer sysUserId;/*用户ID;len:10*/
	private Integer sysRoleId;/*用户角色;len:10*/
	private LocalDateTime updateTime;/*更新时间;len:19*/
	private String updator;/*更新人;len:30*/

	public void setSysUserRoleSno(Integer sysUserRoleSno){
		this.sysUserRoleSno = sysUserRoleSno;
	}
	public Integer getSysUserRoleSno(){
		return sysUserRoleSno;
	}
	public void setSysUserId(Integer sysUserId){
		this.sysUserId = sysUserId;
	}
	public Integer getSysUserId(){
		return sysUserId;
	}
	public void setSysRoleId(Integer sysRoleId){
		this.sysRoleId = sysRoleId;
	}
	public Integer getSysRoleId(){
		return sysRoleId;
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

	private static final long serialVersionUID =-1575199231L;

}