package com.github.ulwx.aka.admin.filter;

import com.github.ulwx.aka.webmvc.web.action.CbResult;

public class AccessResult extends CbResult {
    private Integer code;
    private String content;
    private String login;
    private Integer exit;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getExit() {
        return exit;
    }

    public void setExit(Integer exit) {
        this.exit = exit;
    }
}
