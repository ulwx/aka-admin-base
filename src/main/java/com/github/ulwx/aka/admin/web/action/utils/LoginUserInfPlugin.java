package com.github.ulwx.aka.admin.web.action.utils;

import com.github.ulwx.aka.admin.domain.SessionUserInfo;
import com.ulwx.type.TBoolean;
import com.ulwx.type.TString;

public interface LoginUserInfPlugin<T> {

	/**
	 *
	 * @param sessionUserInfo：用户信息
	 * @param validate：存放是否验证通过的标志
	 * @param validateMsg：存放验证不通过时的提示
	 * @return
	 * @throws Exception
	 */
	public T contruct(SessionUserInfo sessionUserInfo, TBoolean validate, TString validateMsg) throws Exception;
}
