package com.github.ulwx.aka.admin;

import com.github.ulwx.aka.admin.filter.F1LogFilter;
import com.github.ulwx.aka.admin.filter.F2DebugFilter;
import com.github.ulwx.aka.admin.filter.F3AccessFilter;
import com.github.ulwx.aka.admin.filter.F4CrosAndEncodingFilter;
import com.github.ulwx.aka.webmvc.BeanGet;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@PropertySource(name="classpath*:aka-application-admin-base.yml"
//        , value = {"classpath*:aka-application-admin-base.yml"},
//        factory = MyPropertySourceFactory.class)

@Configuration("com.github.ulwx.aka.admin.AkaAdminBaseConfiguration")
public class AkaAdminBaseConfiguration {


    @Bean(initMethod = "init")
    public F1LogFilter f1LogFilter(BeanGet beanGet){
        return new F1LogFilter();
    }
    @Bean
    public DelegatingFilterProxyRegistrationBean f1LogFilterBean(){
        DelegatingFilterProxyRegistrationBean registrationBean=
                new DelegatingFilterProxyRegistrationBean("f1LogFilter");
        registrationBean.addUrlPatterns("/*"); //设置拦截pattern
        registrationBean.setOrder(10 );//设置顺序
        return registrationBean;
    }

    @Bean(initMethod = "init")
    public F2DebugFilter f2DebugFilter(BeanGet beanGet){
        return new F2DebugFilter(beanGet);
    }
    @Bean
    public DelegatingFilterProxyRegistrationBean f2DebugFilterBean(){
        DelegatingFilterProxyRegistrationBean registrationBean=
                new DelegatingFilterProxyRegistrationBean("f2DebugFilter");
        registrationBean.addUrlPatterns("*.jsp","*.action","/swagger-ui/*"); //设置拦截pattern
        registrationBean.setOrder(11);//设置顺序
        return registrationBean;
    }


    @Bean(initMethod = "init")
    public F3AccessFilter fAccessFilter(BeanGet beanGet){
        return new F3AccessFilter(beanGet);
    }

    @Bean
    public DelegatingFilterProxyRegistrationBean fAccessFilterBean(){
        DelegatingFilterProxyRegistrationBean registrationBean=
                new DelegatingFilterProxyRegistrationBean("fAccessFilter");
        registrationBean.addUrlPatterns("*.jsp", "*.action","/swagger-ui/*"); //设置拦截pattern
        registrationBean.setOrder(12);//设置顺序

        return registrationBean;
    }

    @Bean(initMethod = "init")
    public F4CrosAndEncodingFilter f4CrosAndEncodingFilter(){
        return new F4CrosAndEncodingFilter();
    }

    @Bean
    public DelegatingFilterProxyRegistrationBean f4CrosAndEncodingFilterBean(){
        DelegatingFilterProxyRegistrationBean registrationBean=
                new DelegatingFilterProxyRegistrationBean("f4CrosAndEncodingFilter");
        registrationBean.addUrlPatterns("/*"); //设置拦截pattern
        registrationBean.setOrder(13);//设置顺序
        return registrationBean;
    }

}

