package com.github.ulwx.aka.admin.web.action.sys;

import com.github.ulwx.aka.admin.domain.SessionUserInfo;
import com.github.ulwx.aka.admin.domain.cus.CbEasyUIGridModel;
import com.github.ulwx.aka.admin.domain.db.sys.SysRight;
import com.github.ulwx.aka.admin.utils.ActionLogUtils;
import com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb.SysRightDao;
import com.github.ulwx.aka.dbutils.tool.MD;
import com.github.ulwx.aka.webmvc.web.action.ActionContext;
import com.github.ulwx.aka.webmvc.web.action.ActionSupport;
import com.ulwx.tool.RequestUtils;
import com.ulwx.tool.ValidationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;

/**
 * 接口或类的说明:
 * 
 * 
 * @开发：linrb
 * @版本：1.0
 * @创建时间：2011-10-19
 */
public class SysRightAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(SysRightAction.class);
	private static final long serialVersionUID = -4004165890921140820L;
	// 跳转页面指向，即从当前页面跳转到指定的页面

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

		String SysRightCode = ru.getString("sSysRightCode").trim();
		String SysRightName = ru.getString("sSysRightName").trim();
		CbEasyUIGridModel<SysRight> model = new CbEasyUIGridModel<SysRight>();
		try {
			beanGet.bean(SysRightDao.class).getData(SysRightCode, SysRightName, pageNum, pageSize,
					model);
		} catch (Exception e) {
			logger.error("" , e);
			return this.JsonViewError("获取数据失败！");
		}
		/*UserInfo userInfo=(UserInfo)ctx.getSession().get(MyConstants.SessionKey.USER);
		this.log(this.getRequest(),userInfo,
				5,
				"查看功能菜单管理数据,用户为"
						+userInfo.getUser()
								.getName());*/
		return this.JsonViewSuc(model);
	}

	/**
	 * 新增数据
	 */
	public String insert() {
		ActionContext ctx = ActionContext.getContext();
		RequestUtils ru = this.getRequestUtils();
		SysRight entity = new SysRight();
		String rightUrl=ru.getString("sysRightUrl").trim();
		String rightCode=ru.getString("sysRightCode").trim();
		Integer orderCode=ru.getInt("orderCode");
		Integer enable=ru.getInt("enable");
		String rightName=ru.getString("sysRightName").trim();
		String icon=ru.getString("icon").trim();
		if(StringUtils.isEmpty(rightCode)) {
			return  this.JsonViewError("权限码必填！");
		}else {
			if(!ValidationUtils.isNum(rightCode)) {
				return this.JsonViewError("权限码只能输入正整数");
			}else {
				if(rightCode.length()>5) {
					return this.JsonViewError("权限码最大输入5个字符");
				}
			}
		}
		
		if(StringUtils.isEmpty(rightName)) {
			return this.JsonViewError("权限名称必填");
		}else {
			if(rightName.length()>20) {
				return this.JsonViewError("权限名称最大输入20个字符");
			}
		}
		
		
		if(!StringUtils.isEmpty(rightUrl)) {
			if(rightUrl.length()>200) {
				return this.JsonViewError("权限URL最大输入200个字符");
			}
		}
		
		if(orderCode==null) {
			return this.JsonViewError("排序码必填");
		}else {
			if(orderCode.toString().length()>6) {
				return this.JsonViewError("排序码最大输入6个字符");
			}
		}
		
		if(enable==null) {
			return this.JsonViewError("状态必选");
		}else {
			if(enable.toString().length()>2) {
				return this.JsonViewError("状态最大输入2个字符");
			}
		}
		
		if(!StringUtils.isEmpty(icon) && icon.length()>2) {
			return  this.JsonViewError("icon位置最大输入2个字符");
		}
		
		// 获取参数值
		entity.setSysRightUrl(rightUrl);
		entity.setSysRightCode(rightCode);
		entity.setOrderCode(orderCode);
		entity.setEnable(enable);
		entity.setSysRightName(rightName);
		entity.setUpdateTime(LocalDateTime.now());
		entity.setIcon(icon);
		entity.setUpdator(((SessionUserInfo)this.getUserInfo()).getUser().getName());
		try {
			int ID = beanGet.bean(SysRightDao.class).insertData(entity);
			SessionUserInfo userInfo=(SessionUserInfo)this.getUserInfo();
			ActionLogUtils.log(this.getRequest(),userInfo,1, "添加功能菜单管理数据,ID为" + ID);
			return this.JsonViewSuc("添加成功！");
		} catch (Exception e) {
			logger.error("" + e);
			return this.JsonViewError("添加失败！");
		}
	
	}

	/**
	 * 修改数据
	 */
	public String update() {
		ActionContext ctx = ActionContext.getContext();
		RequestUtils ru = this.getRequestUtils();
		SysRight entity = new SysRight();
		String sno = ru.getString("sno");
		String rightUrl=ru.getString("sysRightUrl").trim();
		String rightCode=ru.getString("sysRightCode").trim();
		Integer orderCode=ru.getInt("orderCode");
		Integer enable=ru.getInt("enable");
		String rightName=ru.getString("sysRightName").trim();
		String icon=ru.getString("icon").trim();
		if(StringUtils.isEmpty(rightCode)) {
			return  this.JsonViewError("权限码必填！");
		}else {
			if(!ValidationUtils.isNum(rightCode)) {
				return this.JsonViewError("权限码只能输入正整数");
			}else {
				if(rightCode.length()>5) {
					return this.JsonViewError("权限码最大输入5个字符");
				}
			}
		}
		
		if(StringUtils.isEmpty(rightName)) {
			return this.JsonViewError("权限名称必填");
		}else {
			if(rightName.length()>20) {
				return this.JsonViewError("权限名称最大输入20个字符");
			}
		}
		
		
		if(!StringUtils.isEmpty(rightUrl)) {
			if(rightUrl.length()>200) {
				return this.JsonViewError("权限URL最大输入200个字符");
			}
		}
		
		if(orderCode==null) {
			return this.JsonViewError("排序码必填");
		}else {
			if(orderCode.toString().length()>6) {
				return this.JsonViewError("排序码最大输入6个字符");
			}
		}
		
		if(enable==null) {
			return this.JsonViewError("状态必选");
		}else {
			if(enable.toString().length()>2) {
				return this.JsonViewError("状态最大输入2个字符");
			}
		}
		
		if(!StringUtils.isEmpty(icon) && icon.length()>2) {
			return  this.JsonViewError("icon位置最大输入2个字符");
		}
		
		entity.setSysRightCode(rightCode);
		// 获取参数值
		entity.setSysRightName(rightName);
		entity.setSysRightUrl(rightUrl);
		entity.setOrderCode(orderCode);
		entity.setEnable(enable);
		entity.setIcon(ru.getTrimString("icon"));
		entity.setUpdateTime(LocalDateTime.now());
		String updator = ((SessionUserInfo)this.getUserInfo()).getUser().getName();
		entity.setUpdator(updator);
		try {
			// 修改数据
			beanGet.bean(SysRightDao.class).updateData(entity, sno);

			SessionUserInfo userInfo=(SessionUserInfo)this.getUserInfo();;
			ActionLogUtils.log(this.getRequest(),userInfo,2, "修改功能菜单数据,code为" + ru.getString("sno"));
			return this.JsonViewSuc("修改成功!");
		} catch (Exception e) {
			logger.error("" + e);
			return this.JsonViewError("修改失败！");
		}
		
	}

	/**
	 * 获取单个数据 方法说明:
	 * 
	 * @开发：linrb
	 * @创建时间：2011-10-25
	 * @return
	 */
	public String getOneData() {
		ActionContext ctx = ActionContext.getContext();
		RequestUtils ru = this.getRequestUtils();
		// 获取参数值
		String sno = ru.getString("sno");
		try {
			SysRight entity = beanGet.bean(SysRightDao.class).getOneData(sno);
			return this.JsonViewSuc(entity);
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
				SysRight entity = new SysRight();
				for (String ID : IDs) {
					entity.setSysRightCode(ID.trim());
					beanGet.bean(SysRightDao.class).deleteData(entity, MD.of("sysRightCode"));
				}
			}
			SessionUserInfo userInfo=(SessionUserInfo)this.getUserInfo();
			ActionLogUtils.log(this.getRequest(),userInfo,3,
					userInfo.getUser().getName()
							+ "删除了功能菜单管理数据,ID为"
							+ deleteId);
			return this.JsonViewSuc("删除成功!");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return this.JsonViewError("删除失败!");
		}
		
	}

	

	
	
}


