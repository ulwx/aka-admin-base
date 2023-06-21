package com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb;

import com.github.ulwx.aka.admin.domain.cus.CbEasyUIGridModel;
import com.github.ulwx.aka.admin.domain.db.sys.SysRight;
import com.github.ulwx.aka.admin.utils.CbDao;
import com.github.ulwx.aka.dbutils.database.DataBaseSet;
import com.github.ulwx.aka.dbutils.spring.multids.AkaDS;
import com.github.ulwx.aka.dbutils.tool.MD;
import com.github.ulwx.aka.dbutils.tool.PageBean;
import com.github.ulwx.aka.webmvc.AkaDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@AkaDS("${aka.admin-base.ds-name}")
public class SysRightDao extends AkaDaoSupport {
	static Logger log = LoggerFactory.getLogger(SysRightDao.class);
//	@Override
//	public String getDS() {
//		CbAppConfigProperties Properties=beanGet.bean(CbAppConfigProperties.class);
//		String dsName= StringUtils.trim(Properties.getDsName());
//		return dsName;
//	}
	public  List<SysRight> getAllRight() throws Exception {

		List<SysRight> list = getTemplate().queryList( SysRight.class,
				CbDao.md(SysRightDao.class, "getAllRight"), (Map<String,Object>)null);

		return list;

	}

	/**
	 * @param args
	 */
	public  void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println(NFunction.isNotEmpty("xxxx"));
		getData("", "xxxx", 1, 10, new CbEasyUIGridModel<SysRight>());
	}

	/**
	 * 方法说明:
	 * 
	 * @开发：linrb
	 * @创建时间：2011-11-8 @param sysRightCode
	 * @param sysRightName
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public  List<SysRight> getData(String sysRightCode, String sysRightName, Integer pageNum, Integer pageSize,
			CbEasyUIGridModel<SysRight> model) throws Exception {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("sysRightCode", sysRightCode);
		args.put("sysRightName", sysRightName);
		PageBean pb = new PageBean();
		List<SysRight> list = getTemplate().queryList(SysRight.class, CbDao.md(SysRightDao.class, "getData"),
				args, pageNum, pageSize, pb, null);
		model.setRows(list);
		model.setTotal(pb.getTotal());
		return list;
	}

	/**
	 * 方法说明:
	 * 
	 * @开发：linrb
	 * @创建时间：2011-11-8 @param entity
	 * @return
	 * @throws Exception
	 */
	public  int insertData(SysRight entity) throws Exception {

		return getTemplate().insertBy(entity);
	}

	/**
	 * 方法说明:
	 * 
	 * @开发：linrb
	 * @创建时间：2011-11-8 @param sysRightCode
	 * @param sysRightName
	 * @return
	 * @throws Exception
	 */
	public  int getDataCount(String sysRightCode, String sysRightName) throws Exception {
		int count = 0;
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("sysRightCode", sysRightCode);
		args.put("sysRightName", sysRightName);
		DataBaseSet set = getTemplate().queryForResultSet(CbDao.md(SysRightDao.class, "getDataCount"), args);
		while (set.next())
			count = set.getInt("dataCount");
		return count;

	}

	/**
	 * 方法说明:
	 * 
	 * @开发：linrb @创建时间：2011-10-19
	 * @param sno
	 * @return
	 * @throws Exception
	 */
	public  SysRight getOneData(String sno) throws Exception {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("sysRightCode", sno);

		List<SysRight> list = getTemplate().queryList(SysRight.class, MD.md(),
				args);
		if (list != null && list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	/**
	 * 方法说明:
	 * 
	 * @开发：linrb @创建时间：2011-10-21
	 * @param entity
	 * @return
	 */
	public  int updateData(SysRight entity, String beanKey) throws Exception {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("sysRightCode", entity.getSysRightCode());
		args.put("sysRightName", entity.getSysRightName());
		args.put("sysRightUrl", entity.getSysRightUrl());
		args.put("enable", entity.getEnable());
		args.put("updateTime", entity.getUpdateTime());
		args.put("updator", entity.getUpdator());
		args.put("orderCode", entity.getOrderCode());
		args.put("sysRightCode2", beanKey);
		args.put("icon", entity.getIcon());
		return getTemplate().update(CbDao.md(SysRightDao.class, "updateData"), args);
	}

	/**
	 * 方法说明:
	 * 
	 * @开发：linrb @创建时间：2011-10-21
	 * @param entity
	 * @param deleteProperteis
	 * @return
	 */
	public  int deleteData(SysRight entity, String[] deleteProperteis) throws Exception {

		return getTemplate().delBy(entity, deleteProperteis);
	}

	/**
	 * 方法说明:
	 * 
	 * @开发：linrb @创建时间：2012-1-11
	 * @param rightCode
	 * @return
	 * @throws Exception
	 */
	public  String getUrlByRightCode(String rightCode) throws Exception {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("sysRightCode", rightCode.trim());
		DataBaseSet set = getTemplate().queryForResultSet(CbDao.md(SysRightDao.class, "getUrlByRightCode"), args);
		while (set.next()) {
			return set.getString("SysRightUrl").trim();
		}
		return null;
	}

	/**
	 * 方法说明:
	 * 
	 * @开发：linrb @创建时间：2012-3-21
	 * @param substring
	 * @return
	 * @throws Exception
	 */
	public  List<SysRight> getRightByCode(String substring) throws Exception {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("sysRightCode", substring);
		return getTemplate().queryList(SysRight.class, CbDao.md(SysRightDao.class, "getRightByCode"), args);
	}
	
	
	/**
	 * 方法说明:
	 * 
	 * @开发：linrb
	 * @创建时间：2011-11-8 @param sysRightCode
	 * @param sysRightUrl
	 * @return
	 * @throws Exception
	 */
	public  int getDataCountByUrl(String sysRightUrl) throws Exception {
		int count = 0;
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("sysRightUrl", sysRightUrl);
		DataBaseSet set = getTemplate().queryForResultSet(CbDao.md(SysRightDao.class, "getDataCountByUrl"), args);
		while (set.next())
			count = set.getInt("dataCount");
		return count;

	}
}
