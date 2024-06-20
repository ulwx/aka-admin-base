package com.github.ulwx.aka.admin.web.action.sys;

import com.github.ulwx.aka.admin.domain.SessionUserInfo;
import com.github.ulwx.aka.admin.domain.cus.CbEasyUICombobox;
import com.github.ulwx.aka.admin.domain.cus.CbEasyUIGridModel;
import com.github.ulwx.aka.admin.domain.db.sys.SysRole;
import com.github.ulwx.aka.admin.domain.db.sys.SysRoletype;
import com.github.ulwx.aka.admin.domain.db.sys.SysUser;
import com.github.ulwx.aka.admin.utils.CbConst;
import com.github.ulwx.aka.admin.web.action.sys.domain.AdminUserInfo;
import com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb.SysRoleDao;
import com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb.SysRoletypeDao;
import com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb.SysUserDao;
import com.github.ulwx.aka.admin.web.action.sys.services.service.sys.UserInfoService;
import com.github.ulwx.aka.dbutils.tool.PageBean;
import com.github.ulwx.aka.webmvc.web.action.ActionContext;
import com.github.ulwx.aka.webmvc.web.action.ActionSupport;
import com.ulwx.tool.RequestUtils;
import com.ulwx.tool.StringUtils;
import com.ulwx.tool.ValidationUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 接口或类的说明:
 * 
 * 
 * @开发：linrb @版本：1.0 @创建时间：2011-10-19
 */
public class SysUserAction extends ActionSupport {
	private static final long serialVersionUID = -449402560791918959L;
	// 跳转页面指向，即从当前页面跳转到指定的页面
	private static Logger logger = Logger.getLogger(SysUserAction.class);

	public String ListData() throws Exception {
		return _OK;
	}

	public String getSysRoletypes() {

		try {
			List<SysRoletype> list = beanGet.bean(SysRoletypeDao.class).getAllRoles();
			List<CbEasyUICombobox> retList = new ArrayList<>();
			for (SysRoletype roleType : list) {
				CbEasyUICombobox item = new CbEasyUICombobox();
				item.setId(roleType.getSysRoletypeCode()+ "");
				item.setText(roleType.getSysRoletypeName());
				retList.add(item);
			}

			return this.JsonViewSuc(retList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("", e);
		}

		return this.JsonViewError("无法获取角色！");

	}

	public String addUser() {
		AdminUserInfo adminUserInfo=this.getBean(AdminUserInfo.class);
		
		try {
			
			if(StringUtils.isEmpty(adminUserInfo.getAccount())) {
				return this.JsonViewError("密码账号必填！");
			}else {
//				Pattern account = Pattern 
//						.compile("^[a-zA-Z][a-zA-Z0-9_]{5,15}$");
//				if(!account.matcher(adminUserInfo.getAccount()).matches()) {
//					return this.ERR("只允许字母开头，6-16字节，字母数字下划线!");
//				}
			}
			
			if(StringUtils.isEmpty(adminUserInfo.getPhone())) {
				return this.JsonViewError("手机号码必填!");
			}else {
				if(!ValidationUtils.isMobile(adminUserInfo.getPhone())) {
					return this.JsonViewError("手机号码格式错误!");
				}
			}
			if(StringUtils.isEmpty(adminUserInfo.getPassword())) {
				return this.JsonViewError("密码必填！");
			}else {
//				Pattern password = Pattern 
//						.compile("^(?![0-9]+$)(?![a-zA-Z]+$)\\S{8,16}$");
//				boolean flag=password.matcher(adminUserInfo.getPassword()).matches();
//				if(!flag) {
//					return this.ERR("8-16位，不包含空格，不能为纯数字或纯字母！");
//				}
			}
			if(StringUtils.isEmpty(adminUserInfo.getName())) {
				return this.JsonViewError("姓名必填！");
			}else {
				if(!ValidationUtils.isChinese(adminUserInfo.getName())) {
					return this.JsonViewError("姓名只能为中文！");
				}else {
					if(adminUserInfo.getName().length()>6) {
						return this.JsonViewError("姓名最大6个字节");
					}
				}
			}
			if(!StringUtils.isEmpty(adminUserInfo.getNikeName())) {
				if(adminUserInfo.getNikeName().length()>10) {
					return this.JsonViewError("昵称应小于10个字符！");
				}
			}
			
			if(StringUtils.isEmpty(adminUserInfo.getSysRoleIds())) {
				return this.JsonViewError("菜单角色必填！");
			}

			if(StringUtils.isEmpty(adminUserInfo.getSysRoleTypeCodes())) {
				return this.JsonViewError("角色类型必填！");
			}

			logger.debug("adminUserInfo="+ObjectUtils.toString(adminUserInfo));
			SysUser userInfo = beanGet.bean(UserInfoService.class).getAccountInfo(adminUserInfo.getAccount());
			if(userInfo!=null) {
				return this.JsonViewError("账号已经存在");
			}
			adminUserInfo.setAddTime(LocalDateTime.now());
			adminUserInfo.setUpdateTime(LocalDateTime.now());
			adminUserInfo.setUpdator(((SessionUserInfo)this.getUserInfo()).getUser().getName());
			adminUserInfo.setPassword(CbConst.getPassword(adminUserInfo.getPassword()));;
			beanGet.bean(UserInfoService.class).addUser(adminUserInfo);
			return this.JsonViewSuc("添加成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("",e);
		}
		return this.JsonViewError("添加失败！");
		
	}
	public String delUser() {
		//selInfos
		RequestUtils ru=this.getRequestUtils();
		try {
			AdminUserInfo[] adminUserInfos=ru.getJson("selInfos", AdminUserInfo[].class);
			beanGet.bean(UserInfoService.class).delUser(adminUserInfos);;
			
			return this.JsonViewSuc("删除成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("",e);
		}
		return this.JsonViewError("删除失败！");
		
	}
	public String editUser() {
		AdminUserInfo adminUserInfo=this.getBean(AdminUserInfo.class);
		
		if(StringUtils.isEmpty(adminUserInfo.getAccount())) {
			return this.JsonViewError("密码账号必填！");
		}else {
//			Pattern account = Pattern 
//					.compile("^[a-zA-Z][a-zA-Z0-9_]{5,15}$");
//			if(!account.matcher(adminUserInfo.getAccount()).matches()) {
//				return this.ERR("只允许字母开头，6-16字节，字母数字下划线!");
//			}
		}
		
		if(StringUtils.isEmpty(adminUserInfo.getPhone())) {
			return this.JsonViewError("手机号码必填!");
		}else {
			if(!ValidationUtils.isMobile(adminUserInfo.getPhone())) {
				return this.JsonViewError("手机号码格式错误!");
			}
		}
		
		if(!StringUtils.isEmpty(adminUserInfo.getNikeName())) {
			if(adminUserInfo.getNikeName().length()>10) {
				return this.JsonViewError("昵称应小于10个字符！");
			}
		}
		
		if(StringUtils.isEmpty(adminUserInfo.getName())) {
			return this.JsonViewError("姓名必填！");
		}else {
			if(!ValidationUtils.isChinese(adminUserInfo.getName())) {
				return this.JsonViewError("姓名只能为中文！");
			}else {
				if(adminUserInfo.getName().length()>6) {
					return this.JsonViewError("姓名最大6个字节");
				}
			}
		}
		
		if(StringUtils.isEmpty(adminUserInfo.getPassword())) {
			return this.JsonViewError("密码必填！");
		}else {
//			Pattern password = Pattern 
//					.compile("^(?![0-9]+$)(?![a-zA-Z]+$)\\S{8,16}$");
//			boolean flag=password.matcher(adminUserInfo.getPassword()).matches();
//			if(!flag) {
//				return this.ERR("8-16位，不包含空格，不能为纯数字或纯字母！");
//			}
		}
		
		if(StringUtils.isEmpty(adminUserInfo.getSysRoleIds())) {
			return this.JsonViewError("菜单角色必填！");
		}
			
			
		if(StringUtils.isEmpty(adminUserInfo.getSysRoleTypeCodes())) {
			return this.JsonViewError("角色类型必填！");
		}
		
		try {
			logger.debug("adminUserInfo="+ObjectUtils.toString(adminUserInfo));
			adminUserInfo.setUpdateTime(LocalDateTime.now());
			adminUserInfo.setUpdator(((SessionUserInfo)this.getUserInfo()).getUser().getName());
			if(adminUserInfo.getPassword().equals(CbConst.NoChangedPassword)) {
				adminUserInfo.setPassword(null);
			}else {
				adminUserInfo.setPassword(CbConst.getPassword(adminUserInfo.getPassword()));
			}
			beanGet.bean(UserInfoService.class).editUser(adminUserInfo);
			return this.JsonViewSuc("更新成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("",e);
		}
		return this.JsonViewError("更新失败！");
		
	}
	public String getSysRoles() {


		try {
			List<SysRole> list = beanGet.bean(SysRoleDao.class).getAllRoles();
			List<CbEasyUICombobox> retList = new ArrayList<>();
			for (SysRole role : list) {
				CbEasyUICombobox item = new CbEasyUICombobox();
				item.setId(role.getSysRoleSno() + "");
				item.setText(role.getRoleName());
				retList.add(item);

			}

			return this.JsonViewSuc(retList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("", e);
		}

		return this.JsonViewError("无法获取角色！");

	}

	
	public String userList() {
		ActionContext ctx = ActionContext.getContext();
		// 获取页面信息
		RequestUtils ru = this.getRequestUtils();
		String userName = ru.getTrimString("userName");
		String userPhone = ru.getTrimString("userPhone");
		String enable = ru.getTrimString("enable");
		// 页面的分页参数
		Integer pageNum = ru.getInt("page");
		Integer perPage = ru.getInt("rows");

		PageBean pb = new PageBean();

		List<AdminUserInfo> adminUserInfos = null;
		try {
			adminUserInfos = beanGet.bean(UserInfoService.class).getUserList(userName, userPhone, enable,pageNum, perPage, pb);

			// 得到schoolid

			CbEasyUIGridModel<AdminUserInfo> easyUIGridModel = new CbEasyUIGridModel<>();
			easyUIGridModel.setRows(adminUserInfos);
			easyUIGridModel.setTotal(pb.getTotal());
			return this.JsonViewSuc(easyUIGridModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("", e);
		}

		return this.JsonViewError("无法获取用户信息！");
	}

	public String execModifyPass() {
		ActionContext ctx = ActionContext.getContext();
		RequestUtils ru = this.getRequestUtils();

		String originalPass = StringUtils.trim(ru.getString("OriginalPass"));
		String pass = ((SessionUserInfo)this.getUserInfo()).getUser().getPassword();
		try {
			if (!pass.equals(CbConst.getPassword(originalPass))) {
				ctx.put("json", "原始密码不正确!!");
				return this.JsonViewError("原始密码不正确!!");
			}
		} catch (Exception e) {
			logger.error("" + e);
			ctx.put("json", "error");
			return this.JsonViewError("错误!!"+e);
		}

		String newPass = StringUtils.trim(ru.getString("NewPass"));
		String confirmPassword = StringUtils.trim(ru.getString("VerifyPass"));

		if (StringUtils.hasText(newPass) && newPass.equals(confirmPassword)) {
			try {
				beanGet.bean(SysUserDao.class).changePassword(Integer.valueOf(((SessionUserInfo)this.getUserInfo()).getUser().getId()), CbConst.getPassword(newPass));
				ctx.put("json", "ok");

			} catch (Exception e) {
				logger.error("" + e);
				ctx.put("json", "error");
				return this.JsonViewError("错误!!"+e);
			}
			return this.JsonViewSuc("更新密码成功！");
		} else {
			ctx.put("json", "两次输入的新密码不一致!!");
			return this.JsonViewError("两次输入的新密码不一致!!");
		}

	}

}
