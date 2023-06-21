package com.github.ulwx.aka.admin.web.action.sys.domain;

public class SysUserServicePageRight {
    private  Integer pageId;
    private String pageName;
    private String matchUrlSuffix;
    private String serviceRightCode;
    private String serviceRightName;

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getMatchUrlSuffix() {
        return matchUrlSuffix;
    }

    public void setMatchUrlSuffix(String matchUrlSuffix) {
        this.matchUrlSuffix = matchUrlSuffix;
    }

    public String getServiceRightCode() {
        return serviceRightCode;
    }

    public void setServiceRightCode(String serviceRightCode) {
        this.serviceRightCode = serviceRightCode;
    }

    public String getServiceRightName() {
        return serviceRightName;
    }

    public void setServiceRightName(String serviceRightName) {
        this.serviceRightName = serviceRightName;
    }
}
