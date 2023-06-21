package com.github.ulwx.aka.admin.domain.db.sys;

import com.ulwx.tool.ObjectUtils;

import java.time.LocalDateTime;

/*********************************************

***********************************************/
public class SysServiceRight implements java.io.Serializable {

	private Integer rightCode;/*权限点编码;len:10*/
	private String rightName;/*权限点名册;len:50*/
	private LocalDateTime updatime;/*更新时间;len:19*/
	private Integer status;/*0:初始 1:有效 2：无效;len:3*/

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

	private static final long serialVersionUID =-639779761L;

}