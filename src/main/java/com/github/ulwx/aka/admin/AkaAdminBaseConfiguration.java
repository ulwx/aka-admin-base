package com.github.ulwx.aka.admin;

import com.github.ulwx.aka.admin.filter.F1LogFilter;
import com.github.ulwx.aka.admin.filter.F2DebugFilter;
import com.github.ulwx.aka.admin.filter.F3AccessFilter;
import com.github.ulwx.aka.admin.filter.F4CrosAndEncodingFilter;
import com.github.ulwx.aka.webmvc.MyPropertySourceFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@PropertySource(name="classpath*:aka-application-admin-base.yml"
        , value = {"classpath*:aka-application-admin-base.yml"},
        factory = MyPropertySourceFactory.class)

@Configuration("com.github.ulwx.aka.admin.AkaAdminBaseConfiguration")
public class AkaAdminBaseConfiguration {

    @Bean
    public FilterRegistrationBean<F1LogFilter> f1LogFilterFilterRegistrationBean() {
        FilterRegistrationBean<F1LogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new F1LogFilter()); //设置filter
        registrationBean.addUrlPatterns("/*"); //设置拦截pattern
        registrationBean.setOrder(10 );//设置顺序
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<F2DebugFilter> f2DebugFilterFilterRegistrationBean() {
        FilterRegistrationBean<F2DebugFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new F2DebugFilter()); //设置filter
        // urlPatterns = {"*.jsp","*.action","/swagger-ui/*"}
        registrationBean.addUrlPatterns("*.jsp","*.action","/swagger-ui/*"); //设置拦截pattern
        registrationBean.setOrder(11);//设置顺序
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<F3AccessFilter> f3AccessFilterFilterRegistrationBean() {
        FilterRegistrationBean<F3AccessFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new F3AccessFilter()); //设置filter
        registrationBean.addUrlPatterns("*.jsp", "*.action","/swagger-ui/*"); //设置拦截pattern
        registrationBean.setOrder(12);//设置顺序
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<F4CrosAndEncodingFilter> f4CrosAndEncodingFilterFilterRegistrationBean() {
        FilterRegistrationBean<F4CrosAndEncodingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new F4CrosAndEncodingFilter()); //设置filter
        registrationBean.addUrlPatterns("/*"); //设置拦截pattern
        registrationBean.setOrder(13);//设置顺序
        return registrationBean;
    }
}

