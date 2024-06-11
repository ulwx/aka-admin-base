package com.github.ulwx.aka.admin.filter;


import com.github.ulwx.aka.admin.domain.SessionUserInfo;
import com.github.ulwx.aka.admin.services.service.SysUsersSessionService;
import com.github.ulwx.aka.admin.utils.CbIPAddressUtil;
import com.github.ulwx.aka.webmvc.BeanGet;
import com.github.ulwx.aka.webmvc.WebMvcCbConstants;
import com.ulwx.tool.StringUtils;
import com.ulwx.type.TInteger;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements AccessPlugin {
    Logger log = Logger.getLogger(SessionFilter.class);

    @Override
    public AccessBean doVerify(HttpServletRequest hreq, HttpServletResponse hres, Filter filter) {
        // TODO Auto-generated method stub
        HttpSession session = hreq.getSession();
        SessionUserInfo userInfo = (SessionUserInfo) session.getAttribute(WebMvcCbConstants.USER);
        AccessBean aceBean = new AccessBean();
        aceBean.setIsExit(1);
        Boolean debug_filter_put_a_user=(Boolean)session.getAttribute(
                F2DebugFilter.debug_filter_put_a_user);
        if(debug_filter_put_a_user!=null && debug_filter_put_a_user.booleanValue()){
            aceBean.setErrorCode(0);
            aceBean.setMessage("成功");
            aceBean.setStatus(1);
            return aceBean;
        }
        try {

            TInteger  currentMinuteSession = BeanGet.
                    getBean(SysUsersSessionService.class, hreq).countAllUsersSession();
            if (currentMinuteSession.getValue() > 50) {//控制到50个并发
                aceBean.setErrorCode(0);
                aceBean.setMessage("资源正在加载中,请等待");
                aceBean.setStatus(0);
                return aceBean;
            }
            String sessionId = hreq.getSession().getId();
            String ipaddress = CbIPAddressUtil.getRemoteAddr(hreq);
            log.debug("filter" + sessionId);
            if (!StringUtils.isEmpty(sessionId) && !StringUtils.isEmpty(ipaddress)) {

                TInteger count = BeanGet.getBean(SysUsersSessionService.class, hreq).countUsersSession(userInfo.getUser().getId().toString(), sessionId, ipaddress);
                if (count.getValue() == 0) {
//                    aceBean.setErrorCode(0);
//                    aceBean.setMessage("您已经在其它地方登录，请重新登录！");
//                    aceBean.setStatus(0);
                    aceBean.setErrorCode(0);
                    aceBean.setMessage("");
                    aceBean.setStatus(1);
                } else {
                    aceBean.setErrorCode(0);
                    aceBean.setMessage("");
                    aceBean.setStatus(1);
                }
            } else {
                aceBean.setErrorCode(0);
                aceBean.setMessage("成功");
                aceBean.setStatus(1);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            log.error("", e);
            aceBean.setErrorCode(0);
            aceBean.setMessage("您已经在其它地方登录，请重新登录！");
            aceBean.setStatus(0);
        }
        return aceBean;
    }

}
