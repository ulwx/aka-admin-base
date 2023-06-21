package com.github.ulwx.aka.admin.utils;

import com.github.ulwx.aka.admin.services.IUserInfoService;
import com.github.ulwx.aka.admin.services.service.IOperLogService;
import com.github.ulwx.aka.webmvc.AkaWebMvcProperties.ServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AkaServiceUtils {
    private static volatile Map<String,Object> map = new ConcurrentHashMap<>();
    private static volatile   String log_service_name;
    private static volatile  String user_info_service_name;
    public static IOperLogService getLogService(){
        return (IOperLogService)map.get(log_service_name);
    }

    public static IUserInfoService getUserInfoService(){
        return (IUserInfoService)map.get(user_info_service_name);
    }
    public static void init(ApplicationContext applicationContext,CbAppConfigProperties properties) throws Exception{
        ServiceImpl service=properties.getServiceImpl();
        ConfigurableApplicationContext context=(ConfigurableApplicationContext)applicationContext;
        Object logService=ManualRegistBeanUtil.registerBean(context,service.getLogService(),Class.forName(service.getLogService()));
        log_service_name=service.getLogService();
        Object userInfoService=ManualRegistBeanUtil.registerBean(context,service.getUserInfoService(),Class.forName(service.getUserInfoService()));
        user_info_service_name=service.getUserInfoService();
        map.put(log_service_name ,logService);
        map.put(user_info_service_name ,userInfoService);

    }
}
