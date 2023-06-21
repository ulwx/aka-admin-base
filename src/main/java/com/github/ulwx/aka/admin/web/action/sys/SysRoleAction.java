package com.github.ulwx.aka.admin.web.action.sys;

import com.github.ulwx.aka.admin.domain.SessionUserInfo;
import com.github.ulwx.aka.admin.domain.cus.CbEasyUICombobox;
import com.github.ulwx.aka.admin.domain.cus.CbEasyUIComboboxTree;
import com.github.ulwx.aka.admin.domain.cus.CbEasyUIGridModel;
import com.github.ulwx.aka.admin.domain.db.sys.SysRight;
import com.github.ulwx.aka.admin.domain.db.sys.SysRole;
import com.github.ulwx.aka.admin.domain.db.sys.SysRoleRight;
import com.github.ulwx.aka.admin.utils.ActionLogUtils;
import com.github.ulwx.aka.admin.web.action.sys.domain.SysRoleInfo;
import com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb.SysRightDao;
import com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb.SysRoleDao;
import com.github.ulwx.aka.dbutils.tool.MD;
import com.github.ulwx.aka.webmvc.WebMvcCbConstants;
import com.github.ulwx.aka.webmvc.web.action.ActionContext;
import com.github.ulwx.aka.webmvc.web.action.ActionSupport;
import com.ulwx.tool.RequestUtils;
import com.ulwx.type.TInteger;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 接口或类的说明:
 * 
 * 
 * @开发：linrb
 * @版本：1.0
 * @创建时间：2011-10-19
 */
public class SysRoleAction extends ActionSupport {
	
	private static final long serialVersionUID = 5500766020226211517L;
	// 跳转页面指向，即从当前页面跳转到指定的页面
	private static Logger logger = Logger.getLogger(SysRoleAction.class);


	public String ListData() throws Exception {
		return _OK;
	}

	/**
	 * 返回列表数据
	 */
	public String list() {
		ActionContext ctx = ActionContext.getContext();
		RequestUtils ru = this.getRequestUtils();
		Integer pageNum = ru.getInt("page");
		Integer pageSize = ru.getInt("rows");

		String RoleName = ru.getTrimString("sRoleName");
		CbEasyUIGridModel<SysRoleInfo> model = new CbEasyUIGridModel<SysRoleInfo>();
		try {
			beanGet.bean(SysRoleDao.class).getData(RoleName,  pageNum, pageSize,
					model);
		} catch (Exception e) {
			logger.error("" + e);
			return this.JsonViewError("获取数据失败！");
		}

		/*UserInfo userInfo=(UserInfo)ctx.getSession().get(MyConstants.SessionKey.USER);
		this.log(this.getRequest(),userInfo,
				5,
				"查看角色表管理数据,用户为"
						+ userInfo.getUser()
								.getName());*/
		return this.JsonViewSuc(model);
	}

	/**
	 * 新增数据
	 */
	public String insert() {
		ActionContext ctx = ActionContext.getContext();
		RequestUtils ru = this.getRequestUtils();
		SysRole entity = new SysRole();
		SysRoleRight srr = new SysRoleRight();
		// 获取参数值
		entity.setRoleName(ru.getString("roleName").trim());
		String description = ru.getString("description");
		String[] rights = ru.getStrings("sysRight");
		if(StringUtils.isEmpty(ru.getString("roleName").trim())) {
			return this.JsonViewError("角色名称必填");
		}else {
			if(ru.getString("roleName").trim().length()>20) {
				return this.JsonViewError("角色名称最大输入20个字符！");
			}
		}
		if(rights==null) {
			return this.JsonViewError("功能菜单必选");
		}else {
			if(rights.length==0) {
				return this.JsonViewError("功能菜单必选");
			}
		}
		if (description != null && !"".equals(description)) {
			if(description.trim().length()>300) {
				return this.JsonViewError("说明内容最大300个字符");
			}else {
				entity.setDescription(description.trim());
			}
			
		}
			
		
			
		entity.setUpdateTime(LocalDateTime.now());
		String updator = ((SessionUserInfo)this.getUserInfo()).getUser().getName();
		entity.setUpdator(updator);
		srr.setUpdateTime(LocalDateTime.now());
		srr.setUpdator(updator);
		
		Set<String> rightSet = new HashSet<String>();
		for (String r : rights) {
			if (!rightSet.contains(r.substring(0, 2) + "000"))
				rightSet.add(r.substring(0, 2) + "000");
			rightSet.add(r);
		}
		try {
			int ID = beanGet.bean(SysRoleDao.class).insertData(entity, srr,
					rightSet.toArray(new String[0]));
			SessionUserInfo userInfo=(SessionUserInfo)this.getUserInfo();;
			ActionLogUtils.log(this.getRequest(),userInfo,1, "添加角色表管理数据,ID为" + ID);
		} catch (Exception e) {
			logger.error("" , e);
			return this.JsonViewError("插入失败！");
		}
		return this.JsonViewSuc("插入成功！");
	}

	/**
	 * 修改数据
	 */
	public String update() {
		ActionContext ctx = ActionContext.getContext();
		RequestUtils ru = this.getRequestUtils();
		SysRole entity = new SysRole();
		
		Integer sno = ru.getInt("sno");
		entity.setSysRoleSno(sno);
		// 获取参数值
		entity.setRoleName(ru.getString("roleName").trim());

		String[] rights = ru.getStrings("sysRight");
		
		if(StringUtils.isEmpty(ru.getString("roleName").trim())) {
			return this.JsonViewError("角色名称必填");
		}else {
			if(ru.getString("roleName").trim().length()>20) {
				return this.JsonViewError("角色名称最大输入20个字符！");
			}
		}
		if(rights==null) {
			return this.JsonViewError("功能菜单必选");
		}else {
			if(rights.length==0) {
				return this.JsonViewError("功能菜单必选");
			}
		}
		
		
		String Description = ru.getString("description");
		if (Description != null && !"".equals(Description)) {
			if(Description.trim().length()>300) {
				return this.JsonViewError("说明内容最大300个字符");
			}else {
				entity.setDescription(Description.trim());
			}
			
		}
		entity.setUpdateTime(LocalDateTime.now());
		String updator = ((SessionUserInfo)ctx.getSession().getAttribute(
				WebMvcCbConstants.USER)).getUser().getName();
		entity.setUpdator(updator);
		SysRoleRight srr = new SysRoleRight();
		srr.setUpdateTime(LocalDateTime.now());
		srr.setUpdator(updator);
		srr.setSysRoleId(sno);
		// 功能菜单
		
		Set<String> rightSet = new HashSet<String>();
		for (String r : rights) {
			if (!rightSet.contains(r.substring(0, 2) + "000")) {//检测是否为一级菜单
				rightSet.add(r.substring(0, 2) + "000");
			}
			rightSet.add(r);
		}
		try {
			// 修改数据
			beanGet.bean(SysRoleDao.class).updateData(entity,  srr,
					rightSet.toArray(new String[0]));
			SessionUserInfo userInfo=(SessionUserInfo)this.getUserInfo();
			ActionLogUtils.log(this.getRequest(),userInfo,2, "修改角色表数据,id为" + ru.getString("sno"));
		} catch (Exception e) {
			logger.error("" ,e);
			return this.JsonViewError("更新失败！");
		}
		return this.JsonViewSuc("更新成功！");
	}

	/**
	 * 获取单个数据 方法说明:
	 * 
	 * @开发：linrb
	 * @创建时间：2011-10-25
	 * @return
	 */
	public String getOneData() {
		RequestUtils ru = this.getRequestUtils();
		// 获取参数值
		Integer sno = ru.getInt("sno");
		try {
			
			
			SysRole entity = beanGet.bean(SysRoleDao.class).getOneData(sno);

			List<String> list = beanGet.bean(SysRoleDao.class).getSysRightByRole(sno);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("roleInfo", entity);
			map.put("rightList", list);
			return this.JsonViewSuc(map);
	
		} catch (Exception e) {
			logger.error("" + e);
			return this.JsonViewError("获取数据失败！");
		}
		
	}

	/**
	 * 删除指定数据 方法说明:
	 * 
	 * @开发：linrb
	 * @创建时间：2011-10-25
	 * @return
	 */
	public String delete() {
		ActionContext ctx = ActionContext.getContext();
		RequestUtils ru = this.getRequestUtils();
		try {

			String deleteId = ru.getString("deleteId");
			if (deleteId != null && !deleteId.equals("")) {
				String[] IDs = deleteId.split(",");
				SysRole entity = new SysRole();
				TInteger YdyRole=beanGet.bean(SysRoleDao.class).getYdyRoleCount(IDs);
				if(YdyRole.getValue()>0) {
					return this.JsonViewError("预定义对象不能删除！");
				}
				for (String ID : IDs) {
					entity.setSysRoleSno(Integer.valueOf(ID));
					beanGet.bean(SysRoleDao.class).deleteData(entity, MD.of("sysRoleSno"));
				}
			}
			SessionUserInfo userInfo=(SessionUserInfo)this.getUserInfo();
			ActionLogUtils.log(this.getRequest(),userInfo,3,
					userInfo.getUser().getName()
							+ "删除了角色表管理数据,ID为"
							+ deleteId);
			return this.JsonViewSuc("删除成功！");
		} catch (Exception e) {
			logger.error("",e);
			return this.JsonViewError("删除失败！");
		}
		
	}

	
	

	/**
	 * 返回功能菜单 方法说明:
	 * 
	 * @开发：linrb
	 * @创建时间：2011-11-10
	 * @return
	 */
	public String getSysRight() {
		ActionContext ctx = ActionContext.getContext();
		RequestUtils ru = this.getRequestUtils();
		String mainRightID = ru.getString("mainRightID");
		try {
			List<CbEasyUICombobox> res = beanGet.bean(SysRoleDao.class).getSysRight(mainRightID);
			return this.JsonViewSuc(res);
		} catch (Exception e) {
			logger.error("" + e);
			return this.JsonViewError("返回数据失败");
		}
	
	}

	/**
	 * 返回功能菜单 方法说明:
	 * 
	 * @开发：linrb
	 * @创建时间：2011-11-10
	 * @return
	 */
	public String getSysRights() {
		ActionContext ctx = ActionContext.getContext();
		RequestUtils ru = this.getRequestUtils();
		String RoleID = ru.getString("roleId");
		// 最终返回的结果
		List<CbEasyUIComboboxTree> res = new ArrayList<CbEasyUIComboboxTree>();
		// 保存comboboxtree下拉框的子节点对象
		List<CbEasyUIComboboxTree> children = new ArrayList<CbEasyUIComboboxTree>();
		try {
			if (RoleID != null && !"".equals(RoleID)) {

				// 获取说有的功能菜单
			} else {
				List<SysRight> list = beanGet.bean(SysRightDao.class).getAllRight();
				CbEasyUIComboboxTree model = null;
				String code = "";
				for (SysRight sr : list) {
					model = new CbEasyUIComboboxTree();
					// code以“000”结尾的都是父菜单
					code = sr.getSysRightCode();
					model.setId(code);
					model.setText(sr.getSysRightName());
					if (code.substring(2).equals("000")) {
						// 设置父菜单的子菜单集合
						if (children.size() > 0 && res.size()>0) {
							res.get(res.size() - 1).setChildren(
									children.toArray(new CbEasyUIComboboxTree[0]));
							children.clear();
						}
						model.setState("closed");
						res.add(model);
					} else {
						children.add(model);
					}
				}
				if (children.size() > 0) {
					res.get(res.size() - 1).setChildren(
							children.toArray(new CbEasyUIComboboxTree[0]));
				}
			}
			return this.JsonViewSuc(res);
		} catch (Exception e) {
			logger.error("" ,e);
			return this.JsonViewError("获取数据失败！");
		}
		
	}
}
