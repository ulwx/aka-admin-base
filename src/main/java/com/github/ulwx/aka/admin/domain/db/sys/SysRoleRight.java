package com.github.ulwx.aka.admin.domain.db.sys;

import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;

import java.time.LocalDateTime;

/*********************************************
角色权限表
***********************************************/
public class SysRoleRight implements java.io.Serializable {

	private Integer sysRoleRightSno;/*流水号;len:10*/
	private Integer sysRoleId;/*角色id;len:10*/
	private String sysRightCode;/*权限编码;len:20*/
	private LocalDateTime updateTime;/*更新时间;len:19*/
	private String updator;/*更新人;len:30*/

	public void setSysRoleRightSno(Integer sysRoleRightSno){
		this.sysRoleRightSno = sysRoleRightSno;
	}
	public Integer getSysRoleRightSno(){
		return sysRoleRightSno;
	}
	public void setSysRoleId(Integer sysRoleId){
		this.sysRoleId = sysRoleId;
	}
	public Integer getSysRoleId(){
		return sysRoleId;
	}
	public void setSysRightCode(String sysRightCode){
		this.sysRightCode = sysRightCode;
	}
	public String getSysRightCode(){
		return sysRightCode;
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

	private static final long serialVersionUID =-1315903212L;

}