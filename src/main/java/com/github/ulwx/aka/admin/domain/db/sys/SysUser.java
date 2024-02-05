package com.github.ulwx.aka.admin.domain.db.sys;
import java.util.*;
import java.sql.*;
import java.time.*;
import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;
import com.github.ulwx.aka.dbutils.database.annotation.AkaColumn;

/*********************************************
用户表，此表存放所有使用本系统的用户
***********************************************/
public class SysUser implements java.io.Serializable {

	private Integer sysUserSno;/*用户id，流水号;len:10*/
	private String account;/*用户登录名称;len:40*/
	private String password;/*用户密码;len:80*/
	private String name;/*用户名称;len:20*/
	private String tel;/*电话;len:30*/
	private String sex;/*性别 男 ，女 ，未知;len:4*/
	private String phone;/*手机号码;len:20*/
	private LocalDate birthDay;/*生日;len:10*/
	private String nikeName;/*昵称;len:20*/
	private String email;/*邮件地址;len:30*/
	private String nation;/*民族;len:16*/
	private LocalDateTime addTime;/*添加时间;len:19*/
	private String picUrl;/*用户图片URL;len:128*/
	private String sign;/*个性签名;len:40*/
	private LocalDateTime updateTime;/*更新时间;len:19*/
	private String updator;/*添加人姓名;len:15*/
	private Integer enable;/*0：无效 1：有效;len:3*/

	public void setSysUserSno(Integer sysUserSno){
		this.sysUserSno = sysUserSno;
	}
	@AkaColumn(isAutoincrement=true,isNullable=false)
	public Integer getSysUserSno(){
		return sysUserSno;
	}
	public void setAccount(String account){
		this.account = account;
	}
	@AkaColumn(isNullable=false)
	public String getAccount(){
		return account;
	}
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}
	public void setName(String name){
		this.name = name;
	}
	@AkaColumn(isNullable=false)
	public String getName(){
		return name;
	}
	public void setTel(String tel){
		this.tel = tel;
	}
	@AkaColumn(isNullable=false)
	public String getTel(){
		return tel;
	}
	public void setSex(String sex){
		this.sex = sex;
	}
	@AkaColumn(isNullable=false)
	public String getSex(){
		return sex;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	@AkaColumn(isNullable=false)
	public String getPhone(){
		return phone;
	}
	public void setBirthDay(LocalDate birthDay){
		this.birthDay = birthDay;
	}
	
	public LocalDate getBirthDay(){
		return birthDay;
	}
	public void setNikeName(String nikeName){
		this.nikeName = nikeName;
	}
	
	public String getNikeName(){
		return nikeName;
	}
	public void setEmail(String email){
		this.email = email;
	}
	@AkaColumn(isNullable=false)
	public String getEmail(){
		return email;
	}
	public void setNation(String nation){
		this.nation = nation;
	}
	
	public String getNation(){
		return nation;
	}
	public void setAddTime(LocalDateTime addTime){
		this.addTime = addTime;
	}
	
	public LocalDateTime getAddTime(){
		return addTime;
	}
	public void setPicUrl(String picUrl){
		this.picUrl = picUrl;
	}
	
	public String getPicUrl(){
		return picUrl;
	}
	public void setSign(String sign){
		this.sign = sign;
	}
	
	public String getSign(){
		return sign;
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
	public void setEnable(Integer enable){
		this.enable = enable;
	}
	@AkaColumn(isNullable=false)
	public Integer getEnable(){
		return enable;
	}

	public String toString(){
		return  ObjectUtils.toString(this);
	}

	private static final long serialVersionUID =1384987187L;

}