package com.github.ulwx.aka.admin.domain.db.sys;
import java.util.*;
import java.sql.*;
import java.time.*;
import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;
import com.github.ulwx.aka.dbutils.database.annotation.AkaColumn;

/*********************************************
后台用户错误次数限制表格
***********************************************/
public class SysUsersLock implements java.io.Serializable {

	private Integer sysUserId;/*后台用户id;len:10*/
	private Integer passCnt;/*密码输入错误的次数;len:10*/
	private LocalDateTime lastTime;/*最后一次输入错误时间;len:19*/
	private LocalDateTime firstTime;/*密码输入错误的第一次时间;len:19*/

	public void setSysUserId(Integer sysUserId){
		this.sysUserId = sysUserId;
	}
	@AkaColumn(isNullable=false)
	public Integer getSysUserId(){
		return sysUserId;
	}
	public void setPassCnt(Integer passCnt){
		this.passCnt = passCnt;
	}
	
	public Integer getPassCnt(){
		return passCnt;
	}
	public void setLastTime(LocalDateTime lastTime){
		this.lastTime = lastTime;
	}
	
	public LocalDateTime getLastTime(){
		return lastTime;
	}
	public void setFirstTime(LocalDateTime firstTime){
		this.firstTime = firstTime;
	}
	
	public LocalDateTime getFirstTime(){
		return firstTime;
	}

	public String toString(){
		return  ObjectUtils.toString(this);
	}

	private static final long serialVersionUID =176306666L;

}