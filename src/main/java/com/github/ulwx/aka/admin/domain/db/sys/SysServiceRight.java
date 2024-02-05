package com.github.ulwx.aka.admin.domain.db.sys;

import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;

import java.time.LocalDateTime;

/*********************************************

***********************************************/
public class SysServiceRight implements java.io.Serializable {

	private Integer rightCode;/*;len:10*/
	private String rightName;/*;len:20*/
	private LocalDateTime updatime;/*;len:19*/
	private Integer status;/*;len:10*/

	public void setRightCode(Integer rightCode){
		this.rightCode = rightCode;
	}
	
	public Integer getRightCode(){
		return rightCode;
	}
	public void setRightName(String rightName){
		this.rightName = rightName;
	}
	
	public String getRightName(){
		return rightName;
	}
	public void setUpdatime(LocalDateTime updatime){
		this.updatime = updatime;
	}
	
	public LocalDateTime getUpdatime(){
		return updatime;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}

	public String toString(){
		return  ObjectUtils.toString(this);
	}

	private static final long serialVersionUID =-1252637098L;

}