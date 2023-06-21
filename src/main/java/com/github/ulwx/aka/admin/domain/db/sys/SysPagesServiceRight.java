package com.github.ulwx.aka.admin.domain.db.sys;

import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;

import java.time.LocalDateTime;

/*********************************************
页面权限点（一个页面对应多个业务权限点）
***********************************************/
public class SysPagesServiceRight implements java.io.Serializable {

	private Integer id;/*流水号;len:10*/
	private Integer serviceRightCode;/*权限点code，对应sys_service_right的right_code;len:10*/
	private String serviceRightName;/*权限点名称;len:30*/
	private Integer pageId;/*所属的页面id，对应sys_pages的id;len:10*/
	private LocalDateTime updatime;/*;len:19*/

	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return id;
	}
	public void setServiceRightCode(Integer serviceRightCode){
		this.serviceRightCode = serviceRightCode;
	}
	public Integer getServiceRightCode(){
		return serviceRightCode;
	}
	public void setServiceRightName(String serviceRightName){
		this.serviceRightName = serviceRightName;
	}
	public String getServiceRightName(){
		return serviceRightName;
	}
	public void setPageId(Integer pageId){
		this.pageId = pageId;
	}
	public Integer getPageId(){
		return pageId;
	}
	public void setUpdatime(LocalDateTime updatime){
		this.updatime = updatime;
	}
	public LocalDateTime getUpdatime(){
		return updatime;
	}

	public String toString(){
		return  ObjectUtils.toString(this);
	}

	private static final long serialVersionUID =1629167732L;

}