package com.github.ulwx.aka.admin.domain.db.sys;
import java.util.*;
import java.sql.*;
import java.time.*;
import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;
import com.github.ulwx.aka.dbutils.database.annotation.AkaColumn;

/*********************************************

***********************************************/
public class SysPagesServiceRightUser implements java.io.Serializable {

	private Integer id;/*流水号;len:10*/
	private Integer pageServiceRightId;/*页面权限点id，对应sys_pages_service_right的id;len:10*/
	private Integer sysUserId;/*系统用户id，对应sys_user表id;len:10*/
	private LocalDateTime updatime;/*;len:19*/

	public void setId(Integer id){
		this.id = id;
	}
	@AkaColumn(isAutoincrement=true,isNullable=false)
	public Integer getId(){
		return id;
	}
	public void setPageServiceRightId(Integer pageServiceRightId){
		this.pageServiceRightId = pageServiceRightId;
	}
	
	public Integer getPageServiceRightId(){
		return pageServiceRightId;
	}
	public void setSysUserId(Integer sysUserId){
		this.sysUserId = sysUserId;
	}
	
	public Integer getSysUserId(){
		return sysUserId;
	}
	public void setUpdatime(LocalDateTime updatime){
		this.updatime = updatime;
	}
	@AkaColumn(isNullable=false)
	public LocalDateTime getUpdatime(){
		return updatime;
	}

	public String toString(){
		return  ObjectUtils.toString(this);
	}

	private static final long serialVersionUID =1189562779L;

}