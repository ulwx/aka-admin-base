package com.github.ulwx.aka.admin.domain.db.sys;

import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;

import java.time.LocalDateTime;

/*********************************************
后台短信验证发送表
***********************************************/
public class SysSms implements java.io.Serializable {

	private Integer id;/*id;len:10*/
	private Integer smsType;/*短信类型;len:10*/
	private String mobile;/*接收手机号;len:13*/
	private String content;/*内容;len:200*/
	private String smsCode;/*手机验证码;len:10*/
	private String response;/*返回;len:10*/
	private String responseId;/*返回ID;len:200*/
	private LocalDateTime createTime;/*创建时间;len:19*/
	private Integer status;/*1:有效  2：无效;len:3*/

	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return id;
	}
	public void setSmsType(Integer smsType){
		this.smsType = smsType;
	}
	public Integer getSmsType(){
		return smsType;
	}
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	public String getMobile(){
		return mobile;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return content;
	}
	public void setSmsCode(String smsCode){
		this.smsCode = smsCode;
	}
	public String getSmsCode(){
		return smsCode;
	}
	public void setResponse(String response){
		this.response = response;
	}
	public String getResponse(){
		return response;
	}
	public void setResponseId(String responseId){
		this.responseId = responseId;
	}
	public String getResponseId(){
		return responseId;
	}
	public void setCreateTime(LocalDateTime createTime){
		this.createTime = createTime;
	}
	public LocalDateTime getCreateTime(){
		return createTime;
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

	private static final long serialVersionUID =-1917762638L;

}