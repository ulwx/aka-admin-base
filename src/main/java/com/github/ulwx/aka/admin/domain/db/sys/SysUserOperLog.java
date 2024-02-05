package com.github.ulwx.aka.admin.domain.db.sys;

import com.github.ulwx.aka.dbutils.database.annotation.AkaColumn;
import com.github.ulwx.aka.dbutils.tool.support.ObjectUtils;

import java.time.LocalDateTime;

/*********************************************
用户操作日志表
***********************************************/
public class SysUserOperLog implements java.io.Serializable {

	private Integer id;/*流水号;len:10*/
	private String rightName;/*模块名称;len:30*/
	private Integer operType;/*操作类型 1：增加数据  2：修改数据  3：删除数据  4：审批数据   5：查看数据  6：登录与退出;len:5*/
	private String detail;/*操作详情描述;len:500*/
	private String userName;/*操作人姓名;len:30*/
	private Integer userId;/*用户id;len:10*/
	private String ip;/*ip;len:20*/
	private String logid;/*日志id;len:7*/
	private String srcIp;/*来源ip;len:20*/
	private String reqArgs;/*请求参数;len:2000*/
	private LocalDateTime operTime;/*操作时间;len:19*/
	private Integer source;/*1:android 2:pc  3:微站;len:10*/

	public void setId(Integer id){
		this.id = id;
	}
	@AkaColumn(isAutoincrement=true,isNullable=false)
	public Integer getId(){
		return id;
	}
	public void setRightName(String rightName){
		this.rightName = rightName;
	}
	@AkaColumn(isNullable=false)
	public String getRightName(){
		return rightName;
	}
	public void setOperType(Integer operType){
		this.operType = operType;
	}
	@AkaColumn(isNullable=false)
	public Integer getOperType(){
		return operType;
	}
	public void setDetail(String detail){
		this.detail = detail;
	}
	@AkaColumn(isNullable=false)
	public String getDetail(){
		return detail;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	@AkaColumn(isNullable=false)
	public String getUserName(){
		return userName;
	}
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	@AkaColumn(isNullable=false)
	public Integer getUserId(){
		return userId;
	}
	public void setIp(String ip){
		this.ip = ip;
	}
	
	public String getIp(){
		return ip;
	}
	public void setLogid(String logid){
		this.logid = logid;
	}
	
	public String getLogid(){
		return logid;
	}
	public void setSrcIp(String srcIp){
		this.srcIp = srcIp;
	}
	
	public String getSrcIp(){
		return srcIp;
	}
	public void setReqArgs(String reqArgs){
		this.reqArgs = reqArgs;
	}
	
	public String getReqArgs(){
		return reqArgs;
	}
	public void setOperTime(LocalDateTime operTime){
		this.operTime = operTime;
	}
	
	public LocalDateTime getOperTime(){
		return operTime;
	}
	public void setSource(Integer source){
		this.source = source;
	}
	
	public Integer getSource(){
		return source;
	}

	public String toString(){
		return  ObjectUtils.toString(this);
	}

	private static final long serialVersionUID =325803531L;

}