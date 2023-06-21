package com.github.ulwx.aka.admin.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    private Logger log = LoggerFactory.getLogger(ContextListener.class);

    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub

        try {
            MDC.clear();


        } catch (Exception e) {
            log.error("", e);
        }
    }

    public void contextInitialized(ServletContextEvent se) {

        try {

        } catch (Exception e) {
            log.error("", e);
        }
    }

}
