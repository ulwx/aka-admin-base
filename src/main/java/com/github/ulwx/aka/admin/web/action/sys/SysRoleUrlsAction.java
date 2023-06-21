package com.github.ulwx.aka.admin.web.action.sys;

import com.github.ulwx.aka.admin.domain.SessionUserInfo;
import com.github.ulwx.aka.admin.domain.cus.CbEasyUICombobox;
import com.github.ulwx.aka.admin.domain.cus.CbEasyUIGridModel;
import com.github.ulwx.aka.admin.domain.db.sys.SysRole;
import com.github.ulwx.aka.admin.domain.db.sys.SysRoleUrls;
import com.github.ulwx.aka.admin.utils.ActionLogUtils;
import com.github.ulwx.aka.admin.web.action.sys.domain.SysRoleUrlsVo;
import com.github.ulwx.aka.admin.web.action.sys.services.service.sys.SysRoleUrlsService;
import com.github.ulwx.aka.dbutils.tool.PageBean;
import com.github.ulwx.aka.webmvc.web.action.ActionSupport;
import com.ulwx.tool.ObjectUtils;
import com.ulwx.tool.RequestUtils;
import com.ulwx.tool.StringUtils;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SysRoleUrlsAction extends ActionSupport {
	
	private static final long serialVersionUID = 5500766020226211517L;
	// 跳转页面指向，即从当前页面跳转到指定的页面
	private static Logger logger = Logger.getLogger(SysRoleUrlsAction.class);
	/**
	 * 查询URL信息
	 * 
	 * @return
	 */
	public String getJProjectList() {
		RequestUtils ru = this.getRequestUtils();

		//分页信息
		Integer pageNum = ru.getInt("page");
		if(pageNum==null) pageNum=1;
		Integer perPage = ru.getInt("rows");
		if(perPage==null) perPage=20;
		PageBean pb = new PageBean();
		CbEasyUIGridModel<SysRoleUrlsVo> model = new CbEasyUIGridModel<SysRoleUrlsVo>();

		//查询条件
		String roleId = ru.getTrimString("roleId");
		List<SysRoleUrlsVo> urlList = null;
		try {
			urlList =beanGet.bean(SysRoleUrlsService.class).getSysRoleUrlsList(roleId,pageNum, perPage, pb);
			model.setRows(urlList);
			model.setTotal(pb.getTotal());
			return this.JsonViewSuc(model);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("",e);
		}
		return this.JsonViewError("无法获取URL信息");
	}
	
	
	/**
	 * 删除角色菜单
	 * @return
	 */
	public String delRoleUrls() {
		RequestUtils ru = this.getRequestUtils();
		SessionUserInfo userInfo = (SessionUserInfo)this.getUserInfo();
		
		try {
			
			String id = ru.getTrimString("id");

			beanGet.bean(SysRoleUrlsService.class).delRoleUrlsById(id);
			ActionLogUtils.log(this.getRequest(), userInfo, 3, "删除角色菜单 [菜单ID:"+id+"]", "系统管理");
			
			return this.JsonViewSuc("操作成功!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("",e);
		}
		
		return this.JsonViewError("操作失败!");
	}


	/**
	 * 新增角色URL
	 * @return
	 */
	public String addRoleUrls() {
		RequestUtils ru = this.getRequestUtils();
		SysRoleUrls roleUrls = new SysRoleUrls();
		SessionUserInfo userInfo = (SessionUserInfo)this.getUserInfo();
		LocalDateTime createTime = LocalDateTime.now();
		LocalDateTime updateTIme = createTime;
		
		roleUrls = ObjectUtils.fromMapToJavaBean(roleUrls,ru.getrParms());
		/*if(roleUrls.getRoleId()==null) {
			return this.ERR("角色不能为空!");
		} */
		
		if(StringUtils.isEmpty(roleUrls.getUrlMatch())) {
			return this.JsonViewError("URL不能为空!");
		} 
		
		try {
			
			List<SysRoleUrlsVo> urlList =beanGet.bean( SysRoleUrlsService.class).getSysRoleUrlsList(String.valueOf(roleUrls.getRoleId()));
			if(urlList.size() > 0) {
				return this.JsonViewError("角色类型已存在!");
			}
			
			SysRoleUrls rUrls = new SysRoleUrls();
			rUrls.setRoleId(Integer.parseInt(String.valueOf(roleUrls.getRoleId())));
			rUrls.setUrlMatch(roleUrls.getUrlMatch());
			rUrls.setUpdatime(updateTIme);

			beanGet.bean(SysRoleUrlsService.class).addRoleUrls(rUrls);
			ActionLogUtils.log(this.getRequest(), userInfo, 1, "新增角色菜单 [菜单ID:"+roleUrls.getId()+"]", "系统管理");
			
			return this.JsonViewSuc("操作成功!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("",e);
		}
		
		return this.JsonViewError("操作失败!");
	}
	
	/**
	 * 修改
	 * @return
	 */
	public String updateRoleUrlsById() {
		RequestUtils ru = this.getRequestUtils();
		SysRoleUrls roleUrls = new SysRoleUrls();
		SessionUserInfo userInfo = (SessionUserInfo)this.getUserInfo();
		
		try {
			roleUrls = ObjectUtils.fromMapToJavaBean(roleUrls,ru.getrParms());
			beanGet.bean(SysRoleUrlsService.class).updateRoleUrlsById(roleUrls);

			ActionLogUtils.log(this.getRequest(), userInfo, 2, "修改角色菜单  [菜单ID:"+roleUrls.getId()+"]", "系统管理");
			return this.JsonViewSuc("更新成功!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("",e);
		}
		
		return this.JsonViewError("更新失败!");
	}
	
	//获取角色Combobox
	public String getRoleList() {
		RequestUtils ru = this.getRequestUtils();
		List<CbEasyUICombobox> roleList = new ArrayList<>();
		
		try {
			String roleId = ru.getTrimString("id");
			List<SysRole> roles =  beanGet.bean(SysRoleUrlsService.class).getRoleList(roleId);
			
			for (SysRole r : roles) {
				CbEasyUICombobox item = new CbEasyUICombobox();
				item.setId(r.getSysRoleSno()+"");
				item.setText(r.getRoleName());
				roleList.add(item);
			}
			
			return this.JsonViewSuc(roleList);
		} catch (Exception e) {
			logger.error("", e);
		}
		
		return this.JsonViewError("无法获取角色信息！");
	}
	
	
}
