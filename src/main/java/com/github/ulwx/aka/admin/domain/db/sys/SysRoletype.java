package com.github.ulwx.aka.admin.domain.db.sys;

import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;

import java.time.LocalDateTime;

/*********************************************
角色类型表
***********************************************/
public class SysRoletype  implements java.io.Serializable {

	private Integer sysRoletypeCode;/*编码，;len:10*/
	private String sysRoletypeName;/*角色类型名称;len:30*/
	private Integer sysRoletypeclassCode;/*分类code，对应 sys_roletypeclass表的code;len:3*/
	private String description;/*角色说明;len:100*/
	private LocalDateTime updateTime;/*更新时间;len:19*/
	private String updator;/*更新人;len:30*/

	public void setSysRoletypeCode(Integer sysRoletypeCode){
		this.sysRoletypeCode = sysRoletypeCode;
	}
	public Integer getSysRoletypeCode(){
		return sysRoletypeCode;
	}
	public void setSysRoletypeName(String sysRoletypeName){
		this.sysRoletypeName = sysRoletypeName;
	}
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
	public String getDescription(){
		return description;
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

	private static final long serialVersionUID =533413463L;

}