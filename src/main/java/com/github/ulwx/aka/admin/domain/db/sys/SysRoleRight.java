package com.github.ulwx.aka.admin.domain.db.sys;
import java.util.*;
import java.sql.*;
import java.time.*;
import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;
import com.github.ulwx.aka.dbutils.database.annotation.AkaColumn;

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
	@AkaColumn(isAutoincrement=true,isNullable=false)
	public Integer getSysRoleRightSno(){
		return sysRoleRightSno;
	}
	public void setSysRoleId(Integer sysRoleId){
		this.sysRoleId = sysRoleId;
	}
	@AkaColumn(isNullable=false)
	public Integer getSysRoleId(){
		return sysRoleId;
	}
	public void setSysRightCode(String sysRightCode){
		this.sysRightCode = sysRightCode;
	}
	@AkaColumn(isNullable=false)
	public String getSysRightCode(){
		return sysRightCode;
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

	private static final long serialVersionUID =-733465093L;

}