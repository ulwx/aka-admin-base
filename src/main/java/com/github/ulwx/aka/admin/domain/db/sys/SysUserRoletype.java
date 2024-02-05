package com.github.ulwx.aka.admin.domain.db.sys;
import java.util.*;
import java.sql.*;
import java.time.*;
import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;
import com.github.ulwx.aka.dbutils.database.annotation.AkaColumn;

/*********************************************
用户角色表
***********************************************/
public class SysUserRoletype implements java.io.Serializable {

	private Integer sysUserRoletypeSno;/*流水号;len:10*/
	private Integer sysUserId;/*用户ID;len:10*/
	private Integer sysRoletypeCode;/*角色类型;len:10*/
	private LocalDateTime updateTime;/*更新时间;len:19*/
	private String updator;/*更新人;len:30*/

	public void setSysUserRoletypeSno(Integer sysUserRoletypeSno){
		this.sysUserRoletypeSno = sysUserRoletypeSno;
	}
	@AkaColumn(isAutoincrement=true,isNullable=false)
	public Integer getSysUserRoletypeSno(){
		return sysUserRoletypeSno;
	}
	public void setSysUserId(Integer sysUserId){
		this.sysUserId = sysUserId;
	}
	@AkaColumn(isNullable=false)
	public Integer getSysUserId(){
		return sysUserId;
	}
	public void setSysRoletypeCode(Integer sysRoletypeCode){
		this.sysRoletypeCode = sysRoletypeCode;
	}
	@AkaColumn(isNullable=false)
	public Integer getSysRoletypeCode(){
		return sysRoletypeCode;
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

	private static final long serialVersionUID =-48075544L;

}