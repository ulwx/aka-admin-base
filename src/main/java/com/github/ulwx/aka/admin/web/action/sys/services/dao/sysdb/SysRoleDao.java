package com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb;

import com.github.ulwx.aka.admin.domain.cus.CbEasyUICombobox;
import com.github.ulwx.aka.admin.domain.cus.CbEasyUIGridModel;
import com.github.ulwx.aka.admin.domain.db.sys.SysRole;
import com.github.ulwx.aka.admin.domain.db.sys.SysRoleRight;
import com.github.ulwx.aka.admin.utils.CbDao;
import com.github.ulwx.aka.admin.web.action.sys.domain.SysRoleInfo;
import com.github.ulwx.aka.dbutils.database.DataBaseSet;
import com.github.ulwx.aka.dbutils.spring.multids.AkaDS;
import com.github.ulwx.aka.dbutils.tool.MD;
import com.github.ulwx.aka.dbutils.tool.PageBean;
import com.github.ulwx.aka.webmvc.AkaDaoSupport;
import com.ulwx.type.TInteger;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@AkaDS("${aka.admin-base.ds-name}")
public class SysRoleDao extends AkaDaoSupport {

	public  List<SysRoleInfo> getData(String RoleName, Integer pageNum, Integer pageSize,
									  CbEasyUIGridModel<SysRoleInfo> model) throws Exception {
		
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("roleName", RoleName);

		PageBean pb = new PageBean();
		List<SysRoleInfo> list = getTemplate().queryList(SysRoleInfo.class,
				MD.md(), args, pageNum, pageSize, pb, null);
		model.setRows(list);
		model.setTotal(pb.getTotal());
		return list;
	}

	/**
	 * 方法说明:
	 * 
	 * @开发：linrbb
	 * @创建时间：2011-11-8 @param entity
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public  long insertData(SysRole entity, SysRoleRight srr, String[] sysRightArray) throws Exception {

		Long sysRoleID = 0l;
		sysRoleID=getTemplate().insertReturnKeyBy(entity);
		srr.setSysRoleId(sysRoleID.intValue());
		for (String right : sysRightArray) {
			srr.setSysRightCode(right);
			getTemplate().insertBy(srr);
		}
		return sysRoleID;
	}

	/**
	 * 方法说明:
	 * 
	 * @开发：linrb @创建时间：2011-10-19
	 * @param sno
	 * @return
	 * @throws Exception
	 */
	public  SysRole getOneData(Integer sno) throws Exception {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("sysRoleSno", sno);
		List<SysRole> list = getTemplate().queryList(SysRole.class,
				CbDao.md(SysRoleDao.class, "getOneData"), args);
		if (list != null && list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	public  List<String> getSysRightByRole(Integer sno) throws Exception {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("sysRoleSno", sno);
		DataBaseSet set = getTemplate().queryForResultSet(CbDao.md(SysRoleDao.class, "getSysRightByRole"),
				args);
		List<String> list = new ArrayList<String>();
		while (set.next()) {
			list.add(set.getString("sysRightCode"));
		}
		return list;
	}


	public  int updateData(SysRole sysRole,  SysRoleRight sysRoleRight, String[] sysRightArray)
			throws Exception {

		int row = getTemplate().updateBy(sysRole, MD.of("sysRoleSno"));
		getTemplate().delBy(sysRoleRight, MD.of("sysRoleId"));
		for (String sysRight : sysRightArray) {
			sysRoleRight.setSysRightCode(sysRight);
			getTemplate().insertBy(sysRoleRight);
		}
		return row;
	}

	/**
	 * 方法说明:
	 * 
	 * @开发：linrb @创建时间：2011-10-21
	 * @param entity
	 * @param deleteProperteis
	 * @return
	 * @throws Exception
	 */
	public  int deleteData(SysRole entity, String[] deleteProperteis) throws Exception {
		SysRoleRight srr = new SysRoleRight();
		srr.setSysRoleId(entity.getSysRoleSno());
		getTemplate().delBy(srr, MD.of("SysRoleId"));
		int row = getTemplate().delBy(entity, deleteProperteis);
		return row;
	}

	/**
	 * 方法说明:
	 * 
	 * @开发：linrb @创建时间：2011-11-10
	 * @param mainRightID
	 * @return
	 * @throws Exception
	 */
	public  List<CbEasyUICombobox> getSysRight(String mainRightID) throws Exception {

		Map<String, Object> args = new HashMap<String, Object>();

		args.put("sysRightCode", mainRightID);
		args.put("sysRightCode2", mainRightID.substring(0, 2));
		args.put("sysRightCode", "000");

		DataBaseSet set = getTemplate().queryForResultSet( CbDao.md(SysRoleDao.class, "getSysRight"), args);
		List<CbEasyUICombobox> list = new ArrayList<CbEasyUICombobox>();
		CbEasyUICombobox combo = null;
		while (set.next()) {
			combo = new CbEasyUICombobox();
			combo.setId(set.getString("sys_right_code"));
			combo.setText(set.getString("sys_right_name"));
			list.add(combo);
		}
		return list;
	}



	/**
	 * 方法说明:
	 * 
	 * @开发：linrb @创建时间：2011-11-10
	 * @param newRoleName
	 * @return
	 * @throws Exception
	 */
	public  List<SysRole> getDataByName(String newRoleName) throws Exception { ;
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("newRoleName", newRoleName);
		return getTemplate().queryList( SysRole.class, CbDao.md(SysRoleDao.class, "getDataByName"),
				args);
	}
	
	public  List<SysRole> getAllRoles()throws Exception{
		return getTemplate().queryListBy(new SysRole());
	}

	public  TInteger getYdyRoleCount(String[] iDs) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("ids", iDs);
		return getTemplate().queryOne(TInteger.class, CbDao.md(SysRoleDao.class, "getYdyRoleCount"), params);
	}

}
