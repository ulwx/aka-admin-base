package com.github.ulwx.aka.admin.web.action.sys;

import com.github.ulwx.aka.admin.domain.cus.CbEasyUICombobox;
import com.github.ulwx.aka.admin.domain.cus.CbEasyUIGridModel;
import com.github.ulwx.aka.admin.domain.db.sys.*;
import com.github.ulwx.aka.admin.web.action.sys.domain.SysPagesAdminVo;
import com.github.ulwx.aka.admin.web.action.sys.domain.SysPagesVo;
import com.github.ulwx.aka.admin.web.action.sys.services.service.sys.SysPagesService;
import com.github.ulwx.aka.dbutils.tool.PageBean;
import com.github.ulwx.aka.webmvc.web.action.ActionSupport;
import com.ulwx.tool.ObjectUtils;
import com.ulwx.tool.RequestUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SysPagesAction extends ActionSupport {
	private static final long serialVersionUID = -1672970955045193907L;
	// 跳转页面指向，即从当前页面跳转到指定的页面
	private static Logger logger = Logger.getLogger(SysPagesAction.class);
	//获取页面列表
	public String getPageList() {
		RequestUtils ru = this.getRequestUtils();
		// 分页
		Integer pageNum = ru.getInt("page");
		Integer perPage = ru.getInt("rows");
		PageBean pb = new PageBean();
		
		String pageName = ru.getTrimString("pageName");
		
		CbEasyUIGridModel<SysPagesVo> model = new CbEasyUIGridModel<SysPagesVo>();
		List<SysPagesVo> pages = null;
		try {
			pages = beanGet.bean(SysPagesService.class).getPageList(pageName, pageNum, perPage, pb);
			model.setRows(pages);
			model.setTotal(pb.getTotal());
			return this.JsonViewSuc(model);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("",e);
		}
		return this.JsonViewError("获取失败");
	}
	
	//新增权限页面
	public String AddPage() {
		RequestUtils ru = this.getRequestUtils();
		SysPages page = new SysPages();
		page = ObjectUtils.fromMapToJavaBean(page, ru.getrParms());
		int status = ru.getInt("statusName");
		page.setStatus(status);
		try {
			beanGet.bean(SysPagesService.class).AddPage(page);
			return this.JsonViewSuc("添加成功");
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("",e);
		}
		return this.JsonViewError("添加失败");
	}
	
	//修改
	public String updatePage() {
		RequestUtils ru = this.getRequestUtils();
		SysPages page = new SysPages();
		page = ObjectUtils.fromMapToJavaBean(page, ru.getrParms());
		int status = ru.getInt("statusName");
		page.setStatus(status);
		try {
			beanGet.bean(SysPagesService.class).updatePage(page);
			return this.JsonViewSuc("修改成功");
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("",e);
		}
		return this.JsonViewError("修改失败");
	}
	
	//获取用户和分配的页面
	public String getPageAdminList() {
		RequestUtils ru = this.getRequestUtils();
		// 分页
		Integer pageNum = ru.getInt("page");
		Integer perPage = ru.getInt("rows");
		PageBean pb = new PageBean();
		
		String name = ru.getTrimString("name");
		
		CbEasyUIGridModel<SysPagesAdminVo> model = new CbEasyUIGridModel<SysPagesAdminVo>();
		List<SysPagesAdminVo> admin = null;
		try {
			admin = beanGet.bean(SysPagesService.class).getPageAdminList(name, pageNum, perPage, pb);
			model.setRows(admin);
			model.setTotal(pb.getTotal());
			return this.JsonViewSuc(model);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("",e);
		}
		return this.JsonViewError("获取失败");
	}
	
	//获取所有系统用户
	public String getSysload() {
		RequestUtils ru = this.getRequestUtils();
		String name = ru.getTrimString("q");
		List<SysUser> users = null;
		List<CbEasyUICombobox> retList = new ArrayList<>();
		try {
			users = beanGet.bean(SysPagesService.class).getSysload();
			for (SysUser user : users) {
				CbEasyUICombobox item = new CbEasyUICombobox();
				item.setId(user.getSysUserSno() + "");
				item.setText(user.getName());
				retList.add(item);
			}
			return this.JsonViewSuc(retList);
		}catch (Exception e) {
			logger.error("",e);
		}
		return this.JsonViewError("获取失败");
	}
	
	//取得数据加载进下拉框
	public String getloadDicStatus() {
		List<JDic> dics = null;
		List<CbEasyUICombobox> retList = new ArrayList<>();
		try {

			
			CbEasyUICombobox item = new CbEasyUICombobox();
			item.setId(1 + "");
			item.setText("有效");
			retList.add(item);
			
			item = new CbEasyUICombobox();
			item.setId(2 + "");
			item.setText("无效");
			retList.add(item);
			
			return this.JsonViewSuc(retList);
		}catch (Exception e) {
			logger.error("",e);
		}
		return this.JsonViewError("获取失败");
	}
	
	//取得数据加载进下拉框
	public String getloadPageName() {
		List<SysPages> pages = null;
		List<CbEasyUICombobox> retList = new ArrayList<>();
		try {
			pages = beanGet.bean(SysPagesService.class).getloadPageName();
			for (SysPages page : pages) {
				CbEasyUICombobox item = new CbEasyUICombobox();
				item.setId(page.getId() + "");
				item.setText(page.getPageName());
				retList.add(item);
			}
			return this.JsonViewSuc(retList);
		}catch (Exception e) {
			logger.error("",e);
		}
		return this.JsonViewError("获取失败");
	}
	
	//取得数据加载进下拉框
	public String getloadRightName() {
		List<SysServiceRight> right = null;
		List<CbEasyUICombobox> retList = new ArrayList<>();
		try {
			right = beanGet.bean(SysPagesService.class).getloadRightName();
			for (SysServiceRight page : right) {
				CbEasyUICombobox item = new CbEasyUICombobox();
				item.setId(page.getRightCode() + "");
				item.setText(page.getRightName());
				retList.add(item);
			}
			return this.JsonViewSuc(retList);
		}catch (Exception e) {
			logger.error("",e);
		}
		return this.JsonViewError("获取失败");
	}
	
	//添加用户拥有哪些页面的导出权限
	public String addPagesServiceRightUser()  {
		RequestUtils ru = this.getRequestUtils();
		Integer[] names = ru.getInts("name");
		int rightCode = ru.getInt("rightName");
		int pageId = ru.getInt("pageName");
		try {
			for(int i=0;i<names.length;i++) {
				int num = beanGet.bean(SysPagesService.class).countUserById(names[i],pageId,rightCode).getValue();
				if(num>0) {
					return this.JsonViewError("您所选用户已拥有该页面权限，请不要重复操作");
				}
			}
			beanGet.bean(SysPagesService.class).addRigthUser(pageId,rightCode,names);
			return this.JsonViewSuc("新增成功");
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("",e);
		}
		return this.JsonViewError("新增失败");
	}
	
	//删除数据
	public String delete() {
		RequestUtils ru = this.getRequestUtils();
		int id = ru.getInt("id");
		try {
			SysPagesServiceRightUser serviceRightUser = new SysPagesServiceRightUser();
			serviceRightUser.setId(id);
			beanGet.bean(SysPagesService.class).deleteServiceRightUser(serviceRightUser);
			return this.JsonViewSuc("删除成功");
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("",e);
		}
		return this.JsonViewError("删除失败");
	}
	
}
