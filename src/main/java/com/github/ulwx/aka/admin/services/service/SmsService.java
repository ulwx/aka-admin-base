package com.github.ulwx.aka.admin.services.service;

import com.github.ulwx.aka.admin.domain.db.sys.SysSms;
import com.github.ulwx.aka.admin.services.dao.sysdb.SmsDao;
import com.github.ulwx.aka.admin.utils.CbAppConfigProperties;
import com.github.ulwx.aka.admin.utils.CbThreadPoolUtils;
import com.github.ulwx.aka.webmvc.AkaServiceSupport;
import com.ulwx.tool.StringUtils;
import org.apache.log4j.Logger;

/**
 * 短信发送接口
 */
public class SmsService extends AkaServiceSupport {

	private static Logger logger = Logger.getLogger(SmsService.class);

	public static void main(String[] args) {
		SmsService s = new SmsService();
		s.sendSms("1", "18565574709", "警察叔叔来抓你拉.", "110");
		s.sendSms("1", "18565574709", "警察叔叔来抓你拉.", "110");
		s.sendSms("1", "18565574709", "警察叔叔来抓你拉.", "110");
		s.sendSms("1", "18565574709", "警察叔叔来抓你拉.", "110");
		s.sendSms("1", "18565574709", "警察叔叔来抓你拉.", "110");
		s.sendSms("1", "18565574709", "警察叔叔来抓你拉.", "110");
	}

	public void sendSms(String type, String numbers, String msgContent, String smsCode) {
		CbThreadPoolUtils.pool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					smsSend(type, numbers, msgContent, smsCode);
				} catch (Exception e) {
					// TODO: handle exception
					logger.error(e, e);
				}

			}
		});
	}




	private void smsSend(String type, String numbers, String msgContent, String smsCode) {
		try {
			String smsClass = this.beanGet.bean(CbAppConfigProperties.class)
					.getLoginConfig().getSms().getLoginSmsPlugin();
			if (StringUtils.hasText(smsClass)) {
				ILoginSms loginSms = (ILoginSms) Class.forName(smsClass).getConstructor().newInstance();
				Integer i = loginSms.send(type, numbers, msgContent);
				if (i != null) {
					String str = i.toString();
					beanGet.bean(SmsDao.class).addSms(numbers, msgContent, smsCode, str, str);

				}
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
	}

	/**
	 * 短信五分钟内记录
	 * 
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	public SysSms getSmsInfo(String mobile) throws Exception {
		try {
			return beanGet.bean(SmsDao.class).getSmsInfo(mobile);
		} catch (Exception e) {
			logger.error("", e);
			throw new Exception("*********ERROR********SmsService的获取短信信息异常*******" + e.getMessage());
		}

	}

	/**
	 * 短信最近验证码记录
	 * 
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	public SysSms getSmsInfoRecent(String mobile) throws Exception {
		try {
			return beanGet.bean(SmsDao.class).getSmsInfoRecent(mobile);
		} catch (Exception e) {
			logger.error("", e);
			throw new Exception("*********ERROR********SmsService的获取短信最近信息异常*******" + e.getMessage());
		}

	}
}
