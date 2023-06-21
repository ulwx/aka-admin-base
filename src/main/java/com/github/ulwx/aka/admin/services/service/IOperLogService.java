package com.github.ulwx.aka.admin.services.service;

import com.github.ulwx.aka.admin.domain.SessionUserInfo;

import javax.servlet.http.HttpServletRequest;

public interface IOperLogService {
     void log(HttpServletRequest request, SessionUserInfo userInfo, int OperaType,
              String OperaDetail, String rightName);
}
