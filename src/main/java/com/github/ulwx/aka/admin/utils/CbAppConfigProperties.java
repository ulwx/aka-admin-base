package com.github.ulwx.aka.admin.utils;

import com.github.ulwx.aka.webmvc.AkaWebMvcProperties.AccessFilter;
import com.github.ulwx.aka.webmvc.AkaWebMvcProperties.DebugFilter;
import com.github.ulwx.aka.webmvc.AkaWebMvcProperties.ServiceImpl;
import com.ulwx.tool.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@ConfigurationProperties("aka.admin-base")
public class CbAppConfigProperties implements InitializingBean, ApplicationContextAware {
    private String dsName;
    @NestedConfigurationProperty
    private Login loginConfig;
    @NestedConfigurationProperty
    private DebugFilter debugFilter;
    @NestedConfigurationProperty
    private AccessFilter accessFilter;
    @NestedConfigurationProperty
    private ServiceImpl serviceImpl;
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


}
