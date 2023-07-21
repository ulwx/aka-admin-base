package com.github.ulwx.aka.admin;

import com.github.ulwx.aka.webmvc.MyPropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@PropertySource(name="classpath*:aka-application-admin-base.yml"
        , value = {"classpath*:aka-application-admin-base.yml"},
        factory = MyPropertySourceFactory.class)

@Configuration("com.github.ulwx.aka.admin.AkaAdminBaseConfiguration")
public class AkaAdminBaseConfiguration {

}

