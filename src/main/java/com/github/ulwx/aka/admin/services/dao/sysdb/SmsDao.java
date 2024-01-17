package com.github.ulwx.aka.admin.services.dao.sysdb;


import com.github.ulwx.aka.admin.domain.db.sys.SysSms;
import com.github.ulwx.aka.admin.utils.CbDao;
import com.github.ulwx.aka.dbutils.spring.multids.AkaDS;
import com.github.ulwx.aka.webmvc.AkaDaoSupport;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信查询验证码
 * @author lenovo
 *
 */
@AkaDS("${aka.admin-base.ds-name}")
public class SmsDao extends AkaDaoSupport {
	/**
	 * 5分钟短信验证
	 * @return
	 * @throws Exception 
	 */
	public SysSms getSmsInfo(String mobile) throws Exception {
		//TODO
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("mobile",mobile);
		SysSms jsms=this.getTemplate().queryOne(SysSms.class, CbDao.md(SmsDao.class, "getSmsInfo"), params);
		return jsms;
	}
	/**
	 * 短信验证密码修改短信
	 * @return
	 * @throws Exception 
	 */
	public  SysSms getSmsInfoRecent(String mobile) throws Exception {
		//TODO
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("mobile",mobile);
		SysSms jsms=getTemplate().queryOne(SysSms.class, CbDao.md(SmsDao.class, "getSmsRecent"), params);
		return jsms;
	}

	public int addSms(String mobile, String captcha, String smsCode, String response, String responseId)
			throws Exception {
		int result = 1;
		try {
			SysSms sSms = new SysSms();
			sSms.setContent(captcha);
			sSms.setMobile(mobile);
			sSms.setSmsType(42);
			sSms.setSmsCode(smsCode);
			sSms.setCreateTime(LocalDateTime.now());
			sSms.setResponse(response);
			sSms.setResponseId(responseId);

			this.getTemplate().insertBy(sSms);

		} catch (Exception e) {
			throw new Exception("******** ERROR SmsServiceImpl.addCaptcha " + e.getMessage());
		} finally {

		}
		return result;
	}
}
