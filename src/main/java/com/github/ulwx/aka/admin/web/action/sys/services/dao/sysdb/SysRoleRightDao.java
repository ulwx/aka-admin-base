package com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb;

import com.github.ulwx.aka.admin.domain.db.sys.SysRight;
import com.github.ulwx.aka.admin.utils.CbDao;
import com.github.ulwx.aka.dbutils.spring.multids.AkaDS;
import com.github.ulwx.aka.webmvc.AkaDaoSupport;
import com.ulwx.tool.ArrayUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@AkaDS("${aka.admin-base.ds-name}")
public class SysRoleRightDao extends AkaDaoSupport {

	private static Logger log = Logger.getLogger(SysRoleRightDao.class);

	public List<SysRight> getRightByRoles(Integer[] roles) throws Exception{

		if(ArrayUtils.isEmpty(roles)){
			return new ArrayList<SysRight>();
		}
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("roles", roles);

		List<SysRight> list = getTemplate().queryList( SysRight.class,
				CbDao.md(SysRoleRightDao.class, "getRightByRoles")

				, args);
		return list;

	}



}
