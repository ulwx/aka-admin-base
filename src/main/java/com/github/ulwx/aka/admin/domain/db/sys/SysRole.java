package com.github.ulwx.aka.admin.domain.db.sys;

import com.github.ulwx.aka.dbutils.database.annotation.AkaColumn;
import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;

import java.time.LocalDateTime;

/*********************************************
角色表
***********************************************/
public class SysRole implements java.io.Serializable {

	private Integer sysRoleSno;/*流水号;len:10*/
	private String roleName;/*角色名称;len:30*/
	private String description;/*角色说明;len:100*/
	private LocalDateTime updateTime;/*更新时间;len:19*/
	private String updator;/*更新人;len:30*/

	public void setSysRoleSno(Integer sysRoleSno){
		this.sysRoleSno = sysRoleSno;
	}
	@AkaColumn(isAutoincrement=true,isNullable=false)
	public Integer getSysRoleSno(){
		return sysRoleSno;
	}
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}
	@AkaColumn(isNullable=false)
	public String getRoleName(){
		return roleName;
	}
	public void setDescription(String description){
		this.description = description;
	}
	@AkaColumn(isNullable=false)
	public String getDescription(){
		return description;
	}
	public void setUpdateTime(LocalDateTime updateTime){
		this.updateTime = updateTime;
	}
	@AkaColumn(isNullable=false)
	public LocalDateTime getUpdateTime(){
		return updateTime;
	}
	public void setUpdator(String updator){
		this.updator = updator;
	}
	@AkaColumn(isNullable=false)
	public String getUpdator(){
		return updator;
	}

	public String toString(){
		return  ObjectUtils.toString(this);
	}

	private static final long serialVersionUID =-1571396898L;

}