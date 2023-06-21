package com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb;

import com.github.ulwx.aka.admin.domain.db.sys.SysRoletypeclass;
import com.github.ulwx.aka.dbutils.spring.multids.AkaDS;
import com.github.ulwx.aka.webmvc.AkaDaoSupport;

import java.util.List;
@AkaDS("${aka.admin-base.ds-name}")
public class SysRoletypeclassDao extends AkaDaoSupport {

    public List<SysRoletypeclass> getAll()throws Exception{
        SysRoletypeclass st =new SysRoletypeclass();
        return getTemplate().queryListBy(st );
    }
}
