package com.github.ulwx.aka.admin.services.service;

import com.github.ulwx.aka.admin.domain.SessionUserInfo;
import com.github.ulwx.aka.admin.domain.db.sys.SysUserOperLog;
import com.github.ulwx.aka.admin.services.utils.OperLogServiceUtils;
import com.github.ulwx.aka.webmvc.web.action.ActionContext;
import com.ulwx.tool.RequestUtils;
import com.ulwx.tool.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class OperLogServiceImpl  implements IOperLogService {
    static Logger log = LoggerFactory.getLogger(OperLogServiceImpl.class);
    @Override
    public void log(HttpServletRequest request,
                    SessionUserInfo userInfo, int OperaType,
                    String OperaDetail, String rightName) {
        try {
            //插入数据
            SysUserOperLog operLog=new SysUserOperLog();
            if(StringUtils.isEmpty(rightName)) {
                operLog.setRightName("系统模块");
            }else {
                operLog.setRightName(rightName);
            }
            operLog.setOperType(OperaType);
            if(OperaDetail.length()>500) {
                OperaDetail=OperaDetail.substring(0, 500);
            }
            operLog.setDetail(OperaDetail);
            RequestUtils requestUtils=ActionContext.getContext().getRequestUtils(request);
            OperLogServiceUtils.
                    insertUserOperLogInfo(requestUtils,request, operLog, userInfo);

        } catch (Exception e) {
            log.error(""+e, e);
        }
    }
}
