package com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb;

import com.github.ulwx.aka.admin.domain.db.sys.SysUser;
import com.github.ulwx.aka.admin.utils.CbDao;
import com.github.ulwx.aka.admin.web.action.sys.domain.AdminUserInfo;
import com.github.ulwx.aka.dbutils.spring.multids.AkaDS;
import com.github.ulwx.aka.dbutils.tool.MD;
import com.github.ulwx.aka.dbutils.tool.PageBean;
import com.github.ulwx.aka.webmvc.AkaDaoSupport;
import com.ulwx.tool.StringUtils;
import com.ulwx.type.TInteger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@AkaDS("${aka.admin-base.ds-name}")
public class SysUserDao extends AkaDaoSupport {
	public  void changePassword(Integer userId,String pass) throws Exception {
		SysUser user=new SysUser();
		user.setSysUserSno(userId);
		user.setPassword(pass);

		getTemplate().updateBy( user, MD.of("sysUserSno"));
		
		 
	}
	
	public  int addUser(SysUser user) throws Exception{
		
		return (int)getTemplate().insertReturnKeyBy(user);
		
	}
	
	public  void delUser(SysUser user) throws Exception{
		user.setEnable(0);
		getTemplate().updateBy(user, MD.of("sysUserSno"));

	}
	
	
	public  int editUser(SysUser user) throws Exception{
		
		return getTemplate().updateBy(user, MD.of("sysUserSno"));
		
	}
	
	public  SysUser getUser(String account)throws Exception{
		SysUser user=new SysUser();
		user.setAccount(account);
		return getTemplate().queryOneBy( user, MD.of("account"));
	}
	
	public  SysUser getAccountUser(String account)throws Exception{
		SysUser user=new SysUser();
		user.setAccount(account);
		user.setEnable(1);
		return getTemplate().queryOneBy( user, MD.of("account","enable"));
	}
	public  SysUser getAccountUser(String account,String userName)throws Exception{
		SysUser user=new SysUser();
		if(StringUtils.hasText(account)) {
			user.setAccount(account);
		}
		if(StringUtils.hasText(userName)) {
			user.setName(userName);
		}
		user.setEnable(1);
		return getTemplate().queryOneBy( user);
	}
	public  List<AdminUserInfo> getUserList(String userName, String userPhone,String enable, int page,
											int perPage, PageBean pb)throws Exception{
		Map<String ,Object> arg=new HashMap<>();
		arg.put("userName", userName);
		arg.put("userPhone", userPhone);
		if(!StringUtils.isEmpty(enable)){
			arg.put("enable", Integer.valueOf(enable));
		}
		arg.put("roles",null);
		return getTemplate().queryList(AdminUserInfo.class,
				MD.md(SysUserDao.class, "getUserList"),
				arg, page, perPage, pb, "");
	}

	public  List<AdminUserInfo> list(Integer[] roles)throws Exception{
		Map<String ,Object> arg=new HashMap<>();
		arg.put("userName", "");
		arg.put("userPhone", "");
		arg.put("roles",roles);
		arg.put("enable",1);
		return getTemplate().queryList(AdminUserInfo.class,
				MD.md(SysUserDao.class, "getUserList"),arg);
	}
	/**
	 * 通过手机号获取用户信息
	 * @param userPhone
	 * @return
	 * @throws Exception 
	 */
	public  SysUser getUserByPhone(String userPhone) throws Exception {
		// TODO Auto-generated method stub
		Map<String ,Object> arg=new HashMap<>();
		arg.put("userPhone", userPhone);
		return getTemplate().queryOne( AdminUserInfo.class, CbDao.md(SysUserDao.class, "getUserList"),arg);

	}

	public  TInteger getUserInfo(String account,int userId) throws Exception {
		// TODO Auto-generated method stub
		Map<String ,Object> arg=new HashMap<>();
		arg.put("account", account);
		arg.put("userId", userId);
		return getTemplate().queryOne(TInteger.class, CbDao.md(SysUserDao.class, "getUserInfo"),arg);

	}
	/**
	 * 修改所有符合条件的用户
	 * @param userPhone
	 * @param password
	 * @throws Exception 
	 */
	public  void changeAllPassword(String userPhone,String password) throws Exception {
		// TODO Auto-generated method stub
		Map<String ,Object> arg=new HashMap<>();
		arg.put("userPhone", userPhone);
		arg.put("password", password);
		getTemplate().update(CbDao.md(SysUserDao.class, "changeAllPassword"), arg);

	}

	public  void updatePassword(String newPassword, int sysUserId) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<>();
		params.put("password", newPassword);
		params.put("sysUserId", sysUserId);
		getTemplate().update(CbDao.md(SysUserDao.class, "updatePassword"), params);
	}

	public  void updateUserNameMobile(String realname, String mobile, int sysUserId) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<>();
		params.put("realname", realname);
		params.put("mobile", mobile);
		params.put("sysUserId", sysUserId);
		getTemplate().update(CbDao.md(SysUserDao.class, "updateUserNameMobile"), params);
	}
	
	public  SysUser getUserById(Integer sysUserId)throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("sysUserId", sysUserId);
		return getTemplate().queryOne( SysUser.class, CbDao.md(SysUserDao.class, "getUserById"), params);
	}
}
