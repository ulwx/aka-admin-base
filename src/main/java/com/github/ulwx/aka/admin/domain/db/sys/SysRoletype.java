package com.github.ulwx.aka.admin.domain.db.sys;

import com.github.ulwx.aka.dbutils.database.annotation.AkaColumn;
import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;

import java.time.LocalDateTime;

/*********************************************
角色类型表
***********************************************/
public class SysRoletype implements java.io.Serializable {

	private Integer sysRoletypeCode;/*编码，;len:10*/
	private String sysRoletypeName;/*角色类型名称;len:30*/
	private Integer sysRoletypeclassCode;/*分类code，对应 sys_roletypeclass表的code;len:3*/
	private String description;/*角色说明;len:100*/
	private LocalDateTime updateTime;/*更新时间;len:19*/
	private String updator;/*更新人;len:30*/

	public void setSysRoletypeCode(Integer sysRoletypeCode){
		this.sysRoletypeCode = sysRoletypeCode;
	}
	@AkaColumn(isAutoincrement=true,isNullable=false)
	public Integer getSysRoletypeCode(){
		return sysRoletypeCode;
	}
	public void setSysRoletypeName(String sysRoletypeName){
		this.sysRoletypeName = sysRoletypeName;
	}
	@AkaColumn(isNullable=false)
	public String getSysRoletypeName(){
		return sysRoletypeName;
	}
	public void setSysRoletypeclassCode(Integer sysRoletypeclassCode){
		this.sysRoletypeclassCode = sysRoletypeclassCode;
	}
	
	public Integer getSysRoletypeclassCode(){
		return sysRoletypeclassCode;
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

	private static final long serialVersionUID =-1923615208L;

}