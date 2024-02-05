package com.github.ulwx.aka.admin.domain.db.sys;
import java.util.*;
import java.sql.*;
import java.time.*;
import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;
import com.github.ulwx.aka.dbutils.database.annotation.AkaColumn;

/*********************************************
鼎汉配置表
***********************************************/
public class SmsConfig implements java.io.Serializable {

	private Integer id;/*主键;len:10*/
	private String smsPrex;/*短信前缀;len:20*/
	private String url;/*;len:255*/
	private String userCode;/*用户名;len:50*/
	private String userPwd;/*密码;len:50*/
	private String type;/*类型：(1-验证码类短信 2-营销类短信 3:通知类短信);len:2*/
	private String filter;/*过滤关键字，以英文逗号分隔;len:100*/
	private String commpany;/*鼎汉;len:10*/
	private String blackPhone;/*黑名单手机号码,以逗号分隔;len:500*/
	private Integer status;/*状态;len:5*/

	public void setId(Integer id){
		this.id = id;
	}
	@AkaColumn(isAutoincrement=true,isNullable=false)
	public Integer getId(){
		return id;
	}
	public void setSmsPrex(String smsPrex){
		this.smsPrex = smsPrex;
	}
	
	public String getSmsPrex(){
		return smsPrex;
	}
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return url;
	}
	public void setUserCode(String userCode){
		this.userCode = userCode;
	}
	
	public String getUserCode(){
		return userCode;
	}
	public void setUserPwd(String userPwd){
		this.userPwd = userPwd;
	}
	
	public String getUserPwd(){
		return userPwd;
	}
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
	public void setFilter(String filter){
		this.filter = filter;
	}
	
	public String getFilter(){
		return filter;
	}
	public void setCommpany(String commpany){
		this.commpany = commpany;
	}
	
	public String getCommpany(){
		return commpany;
	}
	public void setBlackPhone(String blackPhone){
		this.blackPhone = blackPhone;
	}
	
	public String getBlackPhone(){
		return blackPhone;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	@AkaColumn(isNullable=false)
	public Integer getStatus(){
		return status;
	}

	public String toString(){
		return  ObjectUtils.toString(this);
	}

	private static final long serialVersionUID =1710124318L;

}