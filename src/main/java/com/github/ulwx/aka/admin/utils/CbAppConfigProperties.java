package com.github.ulwx.aka.admin.utils;

import com.github.ulwx.aka.webmvc.AkaWebMvcProperties.ServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ConfigurationProperties("aka.admin-base")
public class CbAppConfigProperties implements InitializingBean, ApplicationContextAware {
    private String dsName="";
    @NestedConfigurationProperty
    private Login loginConfig=new Login();
    @NestedConfigurationProperty
    private DebugFilter debugFilter=new DebugFilter();
    @NestedConfigurationProperty
    private AccessFilter accessFilter=new AccessFilter();
    @NestedConfigurationProperty
    private ServiceImpl serviceImpl=new ServiceImpl();
    @NestedConfigurationProperty
    private FileServerUrls fileServerUrls=new FileServerUrls();

    public FileServerUrls getFileServerUrls() {
        return fileServerUrls;
    }

    public void setFileServerUrls(FileServerUrls fileServerUrls) {
        this.fileServerUrls = fileServerUrls;
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        AkaServiceUtils.init(applicationContext, this);
    }

    public DebugFilter getDebugFilter() {
        return debugFilter;
    }

    public void setDebugFilter(DebugFilter debugFilter) {
        this.debugFilter = debugFilter;
    }

    public AccessFilter getAccessFilter() {
        return accessFilter;
    }

    public void setAccessFilter(AccessFilter accessFilter) {
        this.accessFilter = accessFilter;
    }

    public ServiceImpl getServiceImpl() {
        return serviceImpl;
    }

    public void setServiceImpl(ServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    public Login getLoginConfig() {
        return loginConfig;
    }

    public void setLoginConfig(Login loginConfig) {
        this.loginConfig = loginConfig;
    }

    public static class FileServerUrls {
        private String crossUploadSingle="";
        private String crossUploadMany="";
        private String crossUploadZip="";
        private String directUpload="";
        private String listUrl="";

        public String getCrossUploadSingle() {
            return crossUploadSingle;
        }

        public void setCrossUploadSingle(String crossUploadSingle) {
            this.crossUploadSingle = crossUploadSingle;
        }

        public String getCrossUploadMany() {
            return crossUploadMany;
        }

        public void setCrossUploadMany(String crossUploadMany) {
            this.crossUploadMany = crossUploadMany;
        }

        public String getCrossUploadZip() {
            return crossUploadZip;
        }

        public void setCrossUploadZip(String crossUploadZip) {
            this.crossUploadZip = crossUploadZip;
        }

        public String getDirectUpload() {
            return directUpload;
        }

        public void setDirectUpload(String directUpload) {
            this.directUpload = directUpload;
        }

        public String getListUrl() {
            return listUrl;
        }

        public void setListUrl(String listUrl) {
            this.listUrl = listUrl;
        }
    }

    public static class Login {
        private String initPassword;
        private String userExtInfPlugin;
        private String clientPrivateKey;
        @NestedConfigurationProperty
        private SMS sms;

        public SMS getSms() {
            return sms;
        }

        public void setSms(SMS sms) {
            this.sms = sms;
        }

        public String getInitPassword() {
            return initPassword;
        }

        public void setInitPassword(String initPassword) {
            this.initPassword = initPassword;
        }

        public String getUserExtInfPlugin() {
            return userExtInfPlugin;
        }

        public void setUserExtInfPlugin(String userExtInfPlugin) {
            this.userExtInfPlugin = userExtInfPlugin;
        }

        public String getClientPrivateKey() {
            return clientPrivateKey;
        }

        public void setClientPrivateKey(String clientPrivateKey) {
            this.clientPrivateKey = clientPrivateKey;
        }
    }

    public static class SMS {
        private String testTo;
        private String testToNoFixSmscode;
        private String loginSmsPlugin;

        public String getTestTo() {
            return testTo;
        }

        public void setTestTo(String testTo) {
            this.testTo = testTo;
        }

        public String getTestToNoFixSmscode() {
            return testToNoFixSmscode;
        }

        public void setTestToNoFixSmscode(String testToNoFixSmscode) {
            this.testToNoFixSmscode = testToNoFixSmscode;
        }

        public String getLoginSmsPlugin() {
            return loginSmsPlugin;
        }

        public void setLoginSmsPlugin(String loginSmsPlugin) {
            this.loginSmsPlugin = loginSmsPlugin;
        }
    }

    public static class DebugFilter{
        private Boolean enable=false;
        private String username="";

        public Boolean getEnable() {
            return enable;
        }

        public void setEnable(Boolean enable) {
            this.enable = enable;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

    }

    public static class AccessFilter{
        @NestedConfigurationProperty
        private Map<String,String> accessPlugins=new LinkedHashMap<>();
        @NestedConfigurationProperty
        private Map<String,String> notFilterUrls=new LinkedHashMap<>();;

        public Map<String, String> getAccessPlugins() {
            return accessPlugins;
        }

        public void setAccessPlugins(Map<String, String> accessPlugins) {
            this.accessPlugins = accessPlugins;
        }

        public Map<String, String> getNotFilterUrls() {
            return notFilterUrls;
        }

        public void setNotFilterUrls(Map<String, String> notFilterUrls) {
            this.notFilterUrls = notFilterUrls;
        }
    }
}
