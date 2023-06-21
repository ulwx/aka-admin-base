package com.github.ulwx.aka.admin.domain.db.sys;

import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;

/*********************************************
角色类型（岗位）分类表，每个角色类型（岗位）对应一个分类
***********************************************/
public class SysRoletypeclass implements java.io.Serializable {

	private Integer code;/*id;len:3*/
	private String className;/*类别名称;len:40*/

	public void setCode(Integer code){
		this.code = code;
	}
	public Integer getCode(){
		return code;
	}
	public void setClassName(String className){
		this.className = className;
	}
	public String getClassName(){
		return className;
	}

	public String toString(){
		return  ObjectUtils.toString(this);
	}

	private static final long serialVersionUID =-276829981L;

}