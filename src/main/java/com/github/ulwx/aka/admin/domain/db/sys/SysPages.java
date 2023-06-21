package com.github.ulwx.aka.admin.domain.db.sys;

import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;

import java.time.LocalDateTime;

/*********************************************

***********************************************/
public class SysPages implements java.io.Serializable {

	private Integer id;/*流水号;len:10*/
	private String pageName;/*此页面名称;len:50*/
	private String matchUrlSuffix;/*此页面的URL后缀;len:158*/
	private Integer status;/*状态 1：有效 2：无效;len:3*/
	private LocalDateTime updatime;/*;len:19*/

	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return id;
	}
	public void setPageName(String pageName){
		this.pageName = pageName;
	}
	public String getPageName(){
		return pageName;
	}
	public void setMatchUrlSuffix(String matchUrlSuffix){
		this.matchUrlSuffix = matchUrlSuffix;
	}
	public String getMatchUrlSuffix(){
		return matchUrlSuffix;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public Integer getStatus(){
		return status;
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

	private static final long serialVersionUID =438180455L;

}