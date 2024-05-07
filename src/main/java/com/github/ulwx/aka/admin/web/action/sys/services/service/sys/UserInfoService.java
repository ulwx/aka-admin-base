package com.github.ulwx.aka.admin.web.action.sys.services.service.sys;

import com.github.ulwx.aka.admin.domain.*;
import com.github.ulwx.aka.admin.domain.db.sys.*;
import com.github.ulwx.aka.admin.services.IUserInfoService;
import com.github.ulwx.aka.admin.utils.CbAppConfigProperties;
import com.github.ulwx.aka.admin.web.action.sys.domain.AdminUserInfo;
import com.github.ulwx.aka.admin.web.action.sys.domain.SysUserServicePageRight;
import com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb.*;
import com.github.ulwx.aka.admin.web.action.utils.LoginUserInfPlugin;
import com.github.ulwx.aka.dbutils.tool.PageBean;
import com.github.ulwx.aka.webmvc.AkaServiceSupport;
import com.ulwx.tool.ArrayUtils;
import com.ulwx.tool.ObjectUtils;
import com.ulwx.tool.StringUtils;
import com.ulwx.type.TBoolean;
import com.ulwx.type.TInteger;
import com.ulwx.type.TString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 用户登陆锁定信息
 * @author lenovo
 *
 */
public class UserInfoService   extends AkaServiceSupport implements IUserInfoService {
	static Logger log = LoggerFactory.getLogger(UserInfoService.class);

	public void addUser(AdminUserInfo adminUserInfo) throws Exception {
		SysUser sysUser=ObjectUtils.fromBeanToBean(SysUser.class, adminUserInfo);
		Integer userId=beanGet.bean(SysUserDao.class).addUser( sysUser);

		String roles=adminUserInfo.getSysRoleIds();
		String[] roleIdStrs=roles.split(",");
		Integer[] roleIds=ArrayUtils.StringArrayToIntegerArray(roleIdStrs);
		beanGet.bean(SysUserRoleDao.class).insert(userId, roleIds, adminUserInfo.getUpdator());

		String roleTypes=adminUserInfo.getSysRoleTypeCodes();
		String[] roleTypesStr=roleTypes.split(",");
		Integer[] roleTypeIds=ArrayUtils.StringArrayToIntegerArray(roleTypesStr);
		beanGet.bean(SysUserRoletypeDao.class).insert( userId, roleTypeIds, adminUserInfo.getUpdator());
	}

	public void delUser(AdminUserInfo[]  adminUserInfos) throws Exception {
		for(AdminUserInfo adminUserInfo:adminUserInfos) {
			SysUser sysUser=ObjectUtils.fromBeanToBean(SysUser.class, adminUserInfo);
			beanGet.bean(SysUserDao.class).delUser( sysUser);
			Integer userId=adminUserInfo.getSysUserSno();
			beanGet.bean(SysUserRoleDao.class).del( userId);
			beanGet.bean(SysUserRoletypeDao.class).del( userId);
		}


	}
	public void editUser(AdminUserInfo adminUserInfo) throws Exception {
		SysUser sysUser=ObjectUtils.fromBeanToBean(SysUser.class, adminUserInfo);
		beanGet.bean(SysUserDao.class).editUser( sysUser);

		Integer userId=adminUserInfo.getSysUserSno();
		beanGet.bean(SysUserRoleDao.class).del( userId);
		String roles=adminUserInfo.getSysRoleIds();
		String[] roleIdStrs=roles.split(",");
		Integer[] roleIds=ArrayUtils.StringArrayToIntegerArray(roleIdStrs);
		beanGet.bean(SysUserRoleDao.class).insert(userId, roleIds, adminUserInfo.getUpdator());

		beanGet.bean(SysUserRoletypeDao.class).del( userId);
		String roleTypes=adminUserInfo.getSysRoleTypeCodes();
		String[] roleTypesStr=roleTypes.split(",");
		Integer[] roleTypeIds=ArrayUtils.StringArrayToIntegerArray(roleTypesStr);
		beanGet.bean(SysUserRoletypeDao.class).insert( userId, roleTypeIds, adminUserInfo.getUpdator());



	}
	@Override
	public SessionUserInfo getUserInfo(String account) throws Exception {
		SessionUserInfo sessionUser = new SessionUserInfo();
		try {
			SysUser suser = beanGet.bean(SysUserDao.class).getAccountUser(account);
			if(suser==null) {
				return null;
			}
			User holdedUser=new User();
			holdedUser.setId(suser.getSysUserSno()+"");
			holdedUser.setEnable(suser.getEnable());
			holdedUser.setName(suser.getName());
			holdedUser.setNation(suser.getNation());
			holdedUser.setNikeName(suser.getNikeName());
			holdedUser.setPassword(suser.getPassword());
			holdedUser.setPhone(suser.getPhone());
			holdedUser.setPicUrl(suser.getPicUrl());
			holdedUser.setSex(suser.getSex());
			holdedUser.setSign(suser.getSign());
			holdedUser.setTel(suser.getTel());
			holdedUser.setUpdateTime(suser.getUpdateTime());
			holdedUser.setUpdator(suser.getUpdator());
			holdedUser.setAccount(suser.getAccount());
			holdedUser.setAddTime(suser.getAddTime());
			holdedUser.setBirthDay(suser.getBirthDay());
			holdedUser.setEmail(suser.getEmail());

			sessionUser.setUser(holdedUser);

			// 角色类型id
			List<SysUserRoletype> listRoletypes = beanGet.bean(SysUserRoletypeDao.class).getRoletypes(suser.getSysUserSno());
			Map<Integer, RoleType> roleTypesMap=new HashMap<>();
			//RoleTypeClassCode->RoleTypeClass对象的映射，RoleTypeClassCode一般对应部门
			Map<Integer, RoleTypeClass> roleTypeClassMap=new HashMap<>();
			List<SysRoletype> sysRoletypeList = beanGet.bean(SysRoletypeDao.class).getAllRoles();
			List<SysRoletypeclass> sysRoletypeclassList = beanGet.bean(SysRoletypeclassDao.class).getAll();
			for (int i = 0; i < listRoletypes.size(); i++) {
				RoleType roleType=new RoleType();
				roleType.setCode(listRoletypes.get(i).getSysRoletypeCode());
				for(int j=0; j<sysRoletypeList.size(); j++){
					if(sysRoletypeList.get(j).getSysRoletypeCode().equals(roleType.getCode())){
						roleType.setName(sysRoletypeList.get(j).getSysRoletypeName());
						roleType.setRoleTypeClassCode(sysRoletypeList.get(j).getSysRoletypeclassCode());
						break;
					}
				}
				for(int n=0; n<sysRoletypeclassList.size(); n++){
					if(sysRoletypeclassList.get(n).equals(roleType.getRoleTypeClassCode())){
						RoleTypeClass roleTypeClass=new RoleTypeClass();
						roleTypeClass.setCode(sysRoletypeclassList.get(n).getCode());
						roleTypeClass.setName(sysRoletypeclassList.get(n).getClassName());
						roleTypeClassMap.put(sysRoletypeclassList.get(n).getCode(),roleTypeClass);
						break;

					}
				}

				roleTypesMap.put(roleType.getCode(),roleType);
			}

			sessionUser.setRoleTypesMap(roleTypesMap);
			sessionUser.setRoleTypeClassMap(roleTypeClassMap);
			// 判断是否为超级管理员，如果roleTypeCode=0，表明是超级管理员
			boolean isSuperAdmin=sessionUser.getRoleTypesMap().keySet().contains(0);
			sessionUser.setSuperAdmin(isSuperAdmin);
			List<SysUserRole> sysUserRoleList = null;
			List<SysRole> sysRoles=beanGet.bean(SysRoleDao.class).getAllRoles();
			if (sessionUser.isSuperAdmin()) {
				UserRole[] userRoles = new UserRole[sysRoles.size()];
				for (int i=0; i<sysRoles.size();i++) {
					SysRole sysRole=sysRoles.get(i);
					UserRole userRole = new UserRole();
					userRole.setId(sysRole.getSysRoleSno());
					userRole.setName(sysRole.getRoleName());
					//userRole.setRoleTypeCode(sysRole.getRoleTypeCode());
					userRoles[i] = userRole;
				}
				sessionUser.setRoles(userRoles);
			} else {
				// 取角色id
				sysUserRoleList = beanGet.bean(SysUserRoleDao.class).getRoles(suser.getSysUserSno());
				UserRole[] userRoles = new UserRole[sysUserRoleList.size()];
				for (int i = 0; i < sysUserRoleList.size(); i++) {
					for (SysRole sysRole : sysRoles) {
						if (sysRole.getSysRoleSno().equals(sysUserRoleList.get(i).getSysRoleId())) {
							UserRole userRole = new UserRole();
							userRole.setId(sysRole.getSysRoleSno());
							userRole.setName(sysRole.getRoleName());
							//userRole.setRoleTypeCode(sysRole.getRoleTypeCode());
							userRoles[i] = userRole;
							break;
						}
					}

				}
				sessionUser.setRoles(userRoles);
			}
			if(sessionUser.isSuperAdmin()) {
				List<SysRight> rights = beanGet.bean(SysRightDao.class).getAllRight();
				List<UserRight> rightList=new ArrayList<>();
				for(int i=0; i<rights.size();i++){
					UserRight userRight=new UserRight();
					SysRight sysRight=rights.get(i);
					userRight.setCode(sysRight.getSysRightCode());
					userRight.setEnable(sysRight.getEnable());
					userRight.setIcon(sysRight.getIcon());
					userRight.setOrderCode(sysRight.getOrderCode());
					userRight.setRightName(sysRight.getSysRightName());
					userRight.setRightUrl(sysRight.getSysRightUrl());
					userRight.setUpdateTime(sysRight.getUpdateTime());
					userRight.setUpdator(sysRight.getUpdator());
					rightList.add(userRight);
				}
				sessionUser.setRights(rightList);
			}else{
				Integer[] roleIdArray= Arrays.stream(sessionUser.getRoles()).map(userRole -> {
					return userRole.getId();
				}).toArray(Integer[]::new);
				List<SysRight> rights =
						beanGet.bean(SysRoleRightDao.class).getRightByRoles(roleIdArray);
				List<UserRight> rightList=new ArrayList<>();
				for(int i=0; i<rights.size();i++){
					UserRight userRight=new UserRight();
					SysRight sysRight=rights.get(i);
					userRight.setCode(sysRight.getSysRightCode());
					userRight.setEnable(sysRight.getEnable());
					userRight.setIcon(sysRight.getIcon());
					userRight.setOrderCode(sysRight.getOrderCode());
					userRight.setRightName(sysRight.getSysRightName());
					userRight.setRightUrl(sysRight.getSysRightUrl());
					userRight.setUpdateTime(sysRight.getUpdateTime());
					userRight.setUpdator(sysRight.getUpdator());
					rightList.add(userRight);
				}
				sessionUser.setRights(rightList);
			}
			//添加导出权限控制功能，放session
			Map<Integer,List<String>> mapList=new HashMap<>();
			//1.获取权限码
			Integer userNo=suser.getSysUserSno();//用户id
			List<SysUserServicePageRight> pagesList=beanGet.bean(SysServiceDao.class).getSysPagesList(userNo);
			List<UserServiceRight> userServiceRights=new ArrayList<>();
			for(SysUserServicePageRight sysUserServicePageRight:pagesList){
				UserServiceRight userServiceRight=new UserServiceRight();
				userServiceRight.setPageId(sysUserServicePageRight.getPageId());
				userServiceRight.setPageMatchURL(sysUserServicePageRight.getMatchUrlSuffix());
				userServiceRight.setPageName(sysUserServicePageRight.getPageName());
				userServiceRight.setServiceRightCode(sysUserServicePageRight.getServiceRightCode());
				userServiceRight.setServiceRightName(sysUserServicePageRight.getServiceRightName());
				userServiceRights.add(userServiceRight);
			}
			sessionUser.setServiceRightList(userServiceRights);

			String userInfoPlugin= StringUtils.trim(beanGet.bean(CbAppConfigProperties.class)
					.getLoginConfig().getUserExtInfPlugin());//CbAppConfigProperties.getLoginUserInfPlugin();
			if(!userInfoPlugin.isEmpty() && !userInfoPlugin.equalsIgnoreCase("NONE")) {
				LoginUserInfPlugin<?> plugin=(LoginUserInfPlugin<?>)Class.forName(userInfoPlugin).newInstance();
				TBoolean tValidate=new TBoolean(false);
				TString tValidateMsg=new TString();
				Object ret=plugin.contruct(sessionUser,tValidate,tValidateMsg);
				if(!tValidate.getValue().booleanValue()) {
					throw new Exception(tValidateMsg.getValue());
				}
				sessionUser.setExtInfo(ret);
			}

		} catch (Exception e) {
			throw e;
		}
		return sessionUser;
	}

	public List<AdminUserInfo> getUserList(String userName, String userPhone, int pageNum, int perPage, PageBean pb)
			throws Exception {

		return beanGet.bean(SysUserDao.class).getUserList(userName, userPhone, pageNum, perPage, pb);

	}
	/**
	 * 通过账户获取信息
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public SysUser getAccountInfo(String account) throws Exception {
		// TODO Auto-generated method stub
		try {
			return beanGet.bean(SysUserDao.class).getAccountUser(account);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("",e);
			throw new Exception("**************通过account获取用户信息异常****"+e.getMessage());
		}
	}
	/**
	 * 通过手机号获取用户信息
	 * @param userPhone
	 * @return
	 * @throws Exception
	 */
	public SysUser getUserByPhone(String userPhone) throws Exception {
		// TODO Auto-generated method stub
		try {
			 return beanGet.bean(SysUserDao.class).getUserByPhone(userPhone);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("",e);
			throw new Exception("**************通过phone获取用户的信息异常****"+e.getMessage());
		}
	}

	public TInteger getUserInfo(String account,int userId) throws Exception {
		try {
			 return beanGet.bean(SysUserDao.class).getUserInfo(account,userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("",e);
			throw new Exception("**************通过account与userId获取用户的信息异常****"+e.getMessage());
		}
	}

	public void updatePassword(String newPassword, int sysUserId)throws Exception {
		// TODO Auto-generated method stub
		beanGet.bean(SysUserDao.class).updatePassword(newPassword,sysUserId);
	}

	public void updateUserNameMobile(String realname, String mobile, int sysUserId)throws Exception {
		// TODO Auto-generated method stub
		beanGet.bean(SysUserDao.class).updateUserNameMobile(realname,mobile,sysUserId);
	}

	public SysUser getUserById(Integer sysUserId)throws Exception {
		// TODO Auto-generated method stub
		return beanGet.bean(SysUserDao.class).getUserById(sysUserId);
	}
}
