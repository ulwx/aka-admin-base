package com.github.ulwx.aka.admin.utils;

import com.github.ulwx.aka.admin.domain.SessionUserInfo;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionLogUtils {
    private static Logger log = Logger.getLogger(ActionLogUtils.class);
    /**
     *
     * @param OperaType
     *            操作类型 1：增加数据 2：修改数据 3：删除数据 4：审批数据 5：查看数据 6：登录与退出
     * @param OperaDetail
     *            操作的详细描述
     */
    public static  void log(HttpServletRequest request, SessionUserInfo userInfo, int OperaType, String OperaDetail) {
    	try {
    		//插入数据
    		log(request,userInfo,OperaType,OperaDetail,null);
    	} catch (Exception e) {
    		log.error("", e);
    	}
    }

    /**
     * @param request
     * 		  httpservletRequest
     * @param userInfo
     * 			 用户信息
     * @param OperaType
     *            操作类型 1：增加数据 2：修改数据 3：删除数据 4：审批数据 5：查看数据 6：登录与退出
     * @param OperaDetail
     *            操作的详细描述
     * @param rightName
     * 			  权限名称
     */
    public static  void log(HttpServletRequest request, SessionUserInfo userInfo, int OperaType,
                            String OperaDetail, String rightName) {
    	try {
    		 AkaServiceUtils.getLogService().log(request,userInfo,OperaType,
    				 OperaDetail,rightName);

    	} catch (Exception e) {
    		log.error("", e);
    	}
    }
}
