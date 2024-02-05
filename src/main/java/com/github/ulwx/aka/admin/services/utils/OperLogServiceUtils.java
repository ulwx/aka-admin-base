package com.github.ulwx.aka.admin.services.utils;

import com.github.ulwx.aka.admin.domain.SessionUserInfo;
import com.github.ulwx.aka.admin.domain.db.sys.SysUserOperLog;
import com.github.ulwx.aka.admin.services.service.SysUserOperLogService;
import com.github.ulwx.aka.admin.utils.CbIPAddressUtil;
import com.github.ulwx.aka.webmvc.BeanGet;
import com.github.ulwx.aka.webmvc.WebMvcCbConstants;
import com.ulwx.tool.IpUtils;
import com.ulwx.tool.ObjectUtils;
import com.ulwx.tool.RequestUtils;
import org.apache.log4j.Logger;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 操作日志公用类
 * @author lenovo
 *
 */
public class OperLogServiceUtils {

	public static Logger logger=Logger.getLogger(OperLogServiceUtils.class);

	/**
	 * 更新操作用户日志：用户id,用户名称，ip地址，操作时间,并插入
	 */
	public static  void insertUserOperLogInfo (RequestUtils ru, HttpServletRequest request, SysUserOperLog operLog) throws Exception {
		insertUserOperLogInfo (ru,request,operLog,null);
	}
	
	/**
	 * 更新操作用户日志：用户id,用户名称，ip地址，操作时间,并插入
	 */
	public static  void insertUserOperLogInfo (RequestUtils ru,
										HttpServletRequest request,
										SysUserOperLog operLog,
										SessionUserInfo myuserInfo) throws Exception {
		operLog.setSource(2);
		String mdcLogid=MDC.get("logid");
		String srcIp=IpUtils.getLocalIp();
		operLog.setLogid(mdcLogid);
		operLog.setSrcIp(srcIp);
		Map<String, Object[]> paramsMap=ru.getrParms();
		String reqArgsString=ObjectUtils.toJsonString(paramsMap);
		if(reqArgsString.length()>1000) {
			reqArgsString=reqArgsString.substring(0, 1000);
		}
		operLog.setReqArgs(reqArgsString);
		if(myuserInfo==null) {
			HttpSession session=request.getSession();
			SessionUserInfo userInfo = (SessionUserInfo) session.getAttribute(WebMvcCbConstants.USER);
			if(userInfo!=null) {
				operLog.setUserId(Integer.valueOf(userInfo.getUser().getId()));
				operLog.setUserName(userInfo.getUser().getAccount());
				operLog.setOperTime(LocalDateTime.now());
				operLog.setIp(CbIPAddressUtil.getRemoteAddr(request));
			}else {
				operLog.setUserId(0);
				operLog.setUserName("0");
				operLog.setOperTime(LocalDateTime.now());
				operLog.setIp(CbIPAddressUtil.getRemoteAddr(request));
			}
		}else {
			operLog.setUserId(Integer.valueOf(myuserInfo.getUser().getId()));
			operLog.setUserName(myuserInfo.getUser().getAccount());
			operLog.setOperTime(LocalDateTime.now());
			operLog.setIp(CbIPAddressUtil.getRemoteAddr(request));

		}
		BeanGet.getBean(SysUserOperLogService.class).insertUserOper(operLog);
	}
	
	
	/** 
     * 获取当前网络ip 
     * @param request 
     * @return 
     */  
    public static  String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("WL-Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getRemoteAddr();  
                if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                    //根据网卡取本机配置的IP  
                    InetAddress inet=null;  
                    try {  
                        inet = InetAddress.getLocalHost();  
                    } catch (UnknownHostException e) {  
                      logger.error(e,e);  
                    }  
                    ipAddress= inet.getHostAddress();  
                }  
            }  
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
            if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
                if(ipAddress.indexOf(",")>0){  
                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
                }  
            }  
            return ipAddress;   
    }
}
