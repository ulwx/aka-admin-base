package com.github.ulwx.aka.admin.filter;

import com.github.ulwx.aka.admin.domain.SessionUserInfo;
import com.github.ulwx.aka.webmvc.WebMvcCbConstants;
import com.ulwx.tool.MD5;
import com.ulwx.tool.ObjectUtils;
import com.ulwx.tool.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SignFilter implements AccessPlugin {
	Logger log = Logger.getLogger(SignFilter.class);

	@Override
	public AccessBean doVerify(HttpServletRequest hreq, HttpServletResponse hres, Filter filter) {
		// TODO Auto-generated method stub
		String uri = hreq.getRequestURI();
		log.debug("+++++++++url=" + uri);
		log.debug("++++++++=+eqmap=" + ObjectUtils.toJsonString(hreq.getParameterMap()));

		HttpSession session = hreq.getSession();
		SessionUserInfo userInfo = (SessionUserInfo) session.getAttribute(WebMvcCbConstants.USER);
		AccessBean aceBean = new AccessBean();
		try {
			String ajax=WebMvcCbConstants.AjaxURLSTR;
			boolean isAjax=isAjax(hreq,ajax);
			if(!isAjax) {
				return aceBean;
			}

			Map<String, String[]> map=hreq.getParameterMap();
			Set<String> set=map.keySet();
			String[] keys=set.toArray(new String[0]);
			if(keys==null || keys.length==0) {//不用签名
				return aceBean;
				
			}
			Arrays.sort(keys);
			String md5toStr="";
			String signValue="";
			for(String key:keys) {
				String[] vals=map.get(key);
				if(key.equals("sign")) {
					signValue=vals[0];
					continue;
				}
				if(key.equals("_")) {
					continue;
				}
				Arrays.sort(vals);
				Stream<String> stream = Arrays.stream(vals);
				String valuesMerge=stream.collect(Collectors.joining(","));
				md5toStr=md5toStr+"&"+key+"="+valuesMerge;
			}
			log.debug("md5toStr--1:"+md5toStr);
			if(StringUtils.isEmpty(signValue)) {
				aceBean.setErrorCode(0);
				aceBean.setMessage("");
				aceBean.setStatus(1);
				return aceBean;
			}
			String token=(String)hreq.getSession().getAttribute("token");
			md5toStr=md5toStr+token;
			log.debug("md5toStr="+md5toStr);
			String sign=MD5.MD5generator(md5toStr);
			
			log.debug("param sign="+signValue+"");
			log.debug("figure sign="+sign+"");
		
			if(sign.equals(signValue)) {
				return aceBean;
			}else {
				aceBean.setErrorCode(0);
				aceBean.setMessage("签名验证失败，数据无法返回！");
				aceBean.setStatus(0);
				return aceBean;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			log.error("", e);
			aceBean.setErrorCode(0);
			aceBean.setMessage("签名验证失败，数据无法返回！");
			aceBean.setStatus(0);
		}
		return aceBean;
	}
	public boolean isAjax(HttpServletRequest hreq,String AjaxURLSTR) {
		String ruri = hreq.getRequestURI();
		if ((StringUtils.hasText(AjaxURLSTR) && ruri.contains(AjaxURLSTR))
				|| (hreq.getHeader("x-requested-with") != null
						&& hreq.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))) {
			return true;
		}
		return false;
	}
	private static boolean useList(Object[] arr, Object targetValue) {
		return Arrays.asList(arr).contains(targetValue);
	}

	public static void main(String[] args) {
		Integer[] intArray = { 1, 2, 3, 4, 5, 6 };
		
		String ss="&selInfos=[{\"typeName\":\"平台公告\",\"statusName\":\"无效\",\"id\":636,\"title\":\"简易贷双十一加息活动公告\",\"keyword\":\"简易贷理财，双十一，加息\",\"type\":13,\"content\":\"<p class=\\\"MsoNormal\\\"><strong><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\"><img src=\\\"http://jyd-p2p-image.oss-cn-shenzhen.aliyuncs.com/949f9e6164d34c4088ec5261f16833b8.jpg\\\" alt=\\\"\\\"/><br/></span></strong> </p><p class=\\\"MsoNormal\\\"><strong><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\"><br/></span></strong> </p><p class=\\\"MsoNormal\\\"><strong><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">一、活动主题：</span></strong> </p><p class=\\\"MsoNormal\\\"><strong><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">双十一全场加息，收益飞起溜溜溜</span></strong> </p><p class=\\\"MsoNormal\\\"><strong><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\"><br/></span></strong> </p><p class=\\\"MsoNormal\\\"><!--[if !supportLists]--><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">二、</span><!--[endif]--><strong><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">活动时间</span></strong> </p><p class=\\\"MsoNormal\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">2017.11.11-2017.11.13</span> </p><p class=\\\"MsoNormal\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\"><br/></span> </p><p class=\\\"MsoNormal\\\"><strong><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">三、活动奖励</span></strong><strong><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">:</span></strong> </p><p class=\\\"MsoNormal\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">1、全场加息1%</span> </p><p class=\\\"MsoNormal\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">2、全场加息1.5%</span> </p><p class=\\\"MsoNormal\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\"><br/></span> </p><p class=\\\"MsoNormal\\\"><strong><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">四、参与条件</span></strong><strong><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">：</span></strong> </p><p class=\\\"MsoNormal\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">所有用户</span> </p><p class=\\\"MsoNormal\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\"><br/></span> </p><p class=\\\"MsoNormal\\\"><strong><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">五、活动内容</span></strong><strong><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">:</span></strong> </p><p class=\\\"MsoNormal\\\" style=\\\"margin-left:0pt;text-indent:0pt;\\\"><strong><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">（一）加息</span><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">1%，就是这么任性</span></strong> </p><p class=\\\"MsoNormal\\\" style=\\\"margin-left:0pt;text-indent:0pt;\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">活动期间，用户当天累计投资金额达</span><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">1万元及以上，不限投资期限，当天所有投资即可获得1%年化加息收益奖励。</span> </p><p class=\\\"MsoNormal\\\" style=\\\"margin-left:0pt;text-indent:0pt;\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">举例说明：活动期间，用户</span><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">A当天投资1月标5万元，投资12月标5万元，当天累计投资10万元，则用户A当天这10万元投资可获得1%的年化加息收益奖励；一共可获得奖励金额为：50000*1*1%/12+50000*12*1%/12=41+500=541元</span> </p><p class=\\\"MsoNormal\\\" style=\\\"margin-left:0pt;text-indent:0pt;\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\"><br/></span> </p><p class=\\\"MsoNormal\\\" style=\\\"margin-left:0pt;text-indent:0pt;\\\"><strong><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">（二）加息</span><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">1.5%，让收益多飞一会儿</span></strong> </p><p class=\\\"MsoNormal\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">活动期间，用户当天累计投资金额达</span><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">11万元及以上，</span><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">不限投资期限，当天所有投资即可获得</span><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">1.5%的年化加息收益奖励。</span> </p><p class=\\\"MsoNormal\\\" style=\\\"margin-left:0pt;text-indent:0pt;\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">举例说明：</span><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">活动期间，用户</span><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">B当天投资3月标10万元，投资6月标20万元，当天累计投资30万元，则用户B当天这30万元投资可获得1.5%的年化加息收益奖励；一共可获得奖励金额为：100000*3*1.5%/12+200000*6*1.5%/12=375+1500=1875元</span> </p><p class=\\\"MsoNormal\\\" style=\\\"margin-left:0pt;text-indent:0pt;\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\"><br/></span> </p><p class=\\\"MsoNormal\\\"><strong><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">六、活动规则</span></strong> </p><p class=\\\"MsoNormal\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">1.活动时间：2017年11月11日0:00至2017年11月13日24:00。</span> </p><p class=\\\"MsoNormal\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">2.全场加息即年化加息收益，计算公式为：年化加息收益=当天累计投资金额*投资期限*奖励年化加息收益倍数/12。</span> </p><p class=\\\"MsoNormal\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">3.年化加息收益将在活动结束后7个工作日内发放到用户账户上</span><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">，</span><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">奖励收益仅计算整数。</span> </p><p class=\\\"MsoNormal\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\"> </span> </p><p class=\\\"MsoNormal\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">注：以上信息最终解释权归简易贷所有，如有任何疑问，请致电：400-8238-909</span> </p><p class=\\\"MsoNormal\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\"><br/></span></p><p class=\\\"MsoNormal\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\"><br/></span></p><p class=\\\"MsoNormal\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\"><br/></span></p><p class=\\\"MsoNormal\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\"><br/></span></p><p class=\\\"MsoNormal\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\"></span></p><p class=\\\"MsoNormal\\\" style=\\\"text-align:right;vertical-align:middle;\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">简易贷运营团队</span></p><p class=\\\"MsoNormal\\\" style=\\\"text-align:right;vertical-align:middle;\\\"><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\">2017年11月11日</span></p><p><span style=\\\"font-family:KaiTi_GB2312;font-size:16px;\\\"></span></p><p><br/></p>\",\"remark\":\"\",\"fileUrl\":\"\",\"status\":2,\"updator\":\"杨琴\",\"modifyTime\":\"2018-04-04 14:40:03\",\"createTime\":\"2017-11-11 10:00:16\"}]1de1d99ff5ef4863b307a9fdf6f6259e";
		
		System.out.println(ss);;
		String sign=MD5.MD5generator(ss);
		
		System.out.println(sign);
	}

}
