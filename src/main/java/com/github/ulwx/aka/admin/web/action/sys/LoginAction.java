package com.github.ulwx.aka.admin.web.action.sys;

import com.github.ulwx.aka.admin.domain.SessionUserInfo;
import com.github.ulwx.aka.admin.domain.UserRight;
import com.github.ulwx.aka.admin.domain.db.sys.*;
import com.github.ulwx.aka.admin.services.service.SmsService;
import com.github.ulwx.aka.admin.services.service.SysUsersSessionService;
import com.github.ulwx.aka.admin.utils.*;
import com.github.ulwx.aka.admin.web.action.sys.services.dao.sysdb.SysUserDao;
import com.github.ulwx.aka.admin.web.action.sys.services.service.sys.SysUsersLockService;
import com.github.ulwx.aka.admin.web.action.sys.services.service.sys.UserInfoService;
import com.github.ulwx.aka.admin.utils.secure.RsaApplyUtils;
import com.github.ulwx.aka.webmvc.OperType;
import com.github.ulwx.aka.webmvc.web.action.ActionContext;
import com.github.ulwx.aka.webmvc.web.action.ActionSupport;
import com.ulwx.tool.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class LoginAction extends ActionSupport {
    private static final long serialVersionUID = -1672970955045193907L;
    // 跳转页面指向，即从当前页面跳转到指定的页面
    private static Logger logger = Logger.getLogger(LoginAction.class);

    public String logout() throws Exception {
        this.getRequest().getSession().invalidate();
        return _SUCCESS;
    }

    public String login() throws Exception {

        RequestUtils ru = this.getRequestUtils();

        HttpSession curSession = this.getRequest().getSession();
        logger.debug("session-id=" + curSession.getId());
        String sessinValCode = StringUtils.trim((String) curSession.getAttribute("ValCode"));
        this.getRequest().getSession().invalidate();
        HttpSession session = this.getRequest().getSession();
        // ActionContext ctx = ActionContext.getContext();

        String account = StringUtils.trim(ru.getString("username"));
        String password = StringUtils.trim(ru.getString("password"));
        String smscode = StringUtils.trim(ru.getString("smscode"));
        logger.debug("account=" + account + ";pass=" + password);
        if (!ValidationUtils.isDigital(smscode)) {
            this.getRequest().getSession().invalidate();
            return this.JsonViewError("登陆失败！验证码非法！");
        }

        SessionUserInfo userInfo = null;
        try {

            int i = 0;
            if (account.equals("")) {

                this.getRequest().getSession().invalidate();
                return this.JsonViewError("登陆失败！用户帐号为空！");

            }

            if (password.equals("")) {
                this.getRequest().getSession().invalidate();
                return this.JsonViewError("用户密码为空！");

            }

            password = RsaApplyUtils.decryptRsa(password,
                    beanGet.bean(CbAppConfigProperties.class).getLoginConfig().getClientPrivateKey());

            String parmValCode = StringUtils.trim(ru.getString("valcode"));


            if (parmValCode.isEmpty()) {
                this.getRequest().getSession().invalidate();
                return this.JsonViewError("图形验证码必须输入！");

            }
            logger.debug("session valcode=" + sessinValCode);
            if (!(sessinValCode.toLowerCase().equals(parmValCode.toLowerCase()))) {
                this.getRequest().getSession().invalidate();
                return this.JsonViewError("图形验证码输入有误！");

            }

            if (StringUtils.isEmpty(smscode)) {// 没有输入短信验证码
                this.getRequest().getSession().invalidate();
                return this.JsonViewError("短信验证码不能为空");
            }
            // 获取系统用户信息
            UserInfoService userInfoService = beanGet.bean(UserInfoService.class);
            int flag = 0;
            userInfo = userInfoService.getUserInfo(account,null);
            SysUsersLock objLock = null;
            if (userInfo != null && userInfo.getUser() != null) {
                // 判断是否为测试
                String smsTestTo = StringUtils.trim(
                        beanGet.bean(CbAppConfigProperties.class).getLoginConfig().getSms().getTestTo()
                );
                boolean smsHandled = false;
                if (StringUtils.hasText(smsTestTo)) {
                    if (smsTestTo.equals("all")
                            || StringUtils.contain(smsTestTo, userInfo.getUser().getPhone(), ",")
                    ) {
                        String testSmsCode = StringUtils.trim(
                                beanGet.bean(CbAppConfigProperties.class).getLoginConfig().getSms().getTestToNoFixSmscode()
                        );
                        if (!smscode.equals(testSmsCode)) {
                            this.getRequest().getSession().invalidate();
                            return this.JsonViewError("请输入正确的短信验证码");// 短信验证码错误
                        }
                        smsHandled = true;
                    } else {

                    }
                } else {
                }
                if (!smsHandled) {
                    // 获取短信信息
                    SysSms jsms = beanGet.bean(SmsService.class).getSmsInfo(userInfo.getUser().getPhone());
                    if (jsms == null) {// 验证码超过5分钟
                        this.getRequest().getSession().invalidate();
                        return this.JsonViewError("短信验证码超过5分钟");// 验证码失效
                    } else {
                        if (!smscode.equals(jsms.getSmsCode())) {
                            this.getRequest().getSession().invalidate();
                            return this.JsonViewError("请输入正确的短信验证码");// 短信验证码错误
                        }
                    }
                }
                // 密码MD5加密
                String MD5Password = CbConst.getPassword(password);
                logger.debug("MD5Password=" + MD5Password);

                String pss = "";
                List<UserRight> rights = new ArrayList<>();

                //logger.debug("userInfo=" + ObjectUtils.toString(userInfo));

                // 登陆锁定
                /*
                 * 登陆锁定的话，以30分钟为界限，分3种情况， 1.在30分钟内有5次机会，机会用完之后，锁定向后推迟30分钟（从锁定的时间先后算）
                 * 2.30分钟内没用完机会，从第一次输错，超过30分钟，回复机会次数 3.登陆成功之后恢复次数
                 */
                objLock = beanGet.bean(SysUsersLockService.class).getSysUsersLockInfo(
                        Integer.valueOf(userInfo.getUser().getId())
                );
                if (objLock != null) {
                    if (objLock.getPassCnt() == 0) {
                        LocalDateTime lastTime = objLock.getLastTime();
                        LocalDateTime nowTime = LocalDateTime.now();
                        Duration duration = Duration.between(lastTime, nowTime);
                        if (duration.toMinutes() < 30) {
                            this.getRequest().getSession().invalidate();
                            return this.JsonViewError("由于次数用完，账户已是锁定状态，请等待30分后再登陆"); // 由于次数用完，账户已是锁定状态，请等待30分后再登陆
                        }
                    }
                }

                pss = userInfo.getUser().getPassword();
                logger.debug("MD5Password=" + MD5Password + ",dbpass=" + pss);
                if (MD5Password.equals(pss)) {// 密码相等
                    // 左侧菜单!
                    rights = userInfo.getRights();
                    this.setUserInfo(userInfo);
                    ActionLogUtils.log(this.getRequest(), userInfo, OperType.LOGIN, "登陆成功！");
                    session.setAttribute("rights", rights);

                    if (objLock != null) {
                        objLock.setPassCnt(5);
                        objLock.setSysUserId(Integer.valueOf(userInfo.getUser().getId()));
                        beanGet.bean(SysUsersLockService.class).updateUsersLock(objLock);
                    }

                } else {// 密码不相等
                    flag = 1;
                }

            } else {// 没有用户名
                flag = 2;
            }

            if (flag == 1) {

                Integer count = 0;
                if (objLock == null) {// 第一次错误
                    objLock = new SysUsersLock();
                    objLock.setFirstTime(LocalDateTime.now());
                    objLock.setLastTime(LocalDateTime.now());
                    objLock.setPassCnt(4);
                    objLock.setSysUserId(Integer.valueOf(userInfo.getUser().getId()));
                    beanGet.bean(SysUsersLockService.class).insertUsersLock(objLock);
                    count = 4;
                } else {// 已存在
                    /*
                     * 登陆锁定的话，以30分钟为界限，分3种情况，1.在30分钟内有5次机会，机会用完之后，锁定向后推迟30分钟（从锁定的时间先后算）
                     * 2.30分钟内没用完机会，从第一次输错，超过30分钟，回复机会次数
                     */
                    if (objLock.getPassCnt() == 0) {
                        LocalDateTime lastTime = objLock.getLastTime();
                        LocalDateTime nowTime = LocalDateTime.now();
                        Duration duration = Duration.between(lastTime, nowTime);
                        if (duration.toMinutes() < 30) {
                            this.getRequest().getSession().invalidate();
                            return this.JsonViewError(-7); // 由于次数用完，账户已是锁定状态，请等30分后再登陆
                        } else {// 超过30分重新开始计算
                            objLock.setFirstTime(LocalDateTime.now());
                            objLock.setLastTime(LocalDateTime.now());
                            objLock.setPassCnt(4);
                            objLock.setSysUserId(Integer.valueOf(userInfo.getUser().getId()));
                            beanGet.bean(SysUsersLockService.class).updateUsersLock(objLock);
                            count = 4;
                        }
                    } else {
                        LocalDateTime nowTime = LocalDateTime.now();
                        LocalDateTime firstTime = objLock.getFirstTime();
                        Duration duration = Duration.between(firstTime, nowTime);
                        if (duration.toMinutes() > 30) {
                            objLock.setFirstTime(LocalDateTime.now());
                            objLock.setLastTime(LocalDateTime.now());
                            objLock.setPassCnt(4);
                            objLock.setSysUserId(Integer.valueOf(userInfo.getUser().getId()));
                            beanGet.bean(SysUsersLockService.class).updateUsersLock(objLock);
                            count = 4;
                        } else {
                            objLock.setFirstTime(objLock.getFirstTime());
                            objLock.setLastTime(LocalDateTime.now());
                            objLock.setPassCnt(objLock.getPassCnt() - 1);
                            objLock.setSysUserId(Integer.valueOf(userInfo.getUser().getId()));
                            beanGet.bean(SysUsersLockService.class).updateUsersLock(objLock);
                            count = objLock.getPassCnt();
                        }
                    }

                }

                this.getRequest().getSession().invalidate();
                if (count == 0) {
                    return this.JsonViewError("由于次数用完，账户已是锁定状态，请等待30分后再登陆");
                } else {
                    return this.JsonViewError("账户或密码错误，还剩" + count + "次数机会后锁定账户");
                }

            } else if (flag == 2) {
                this.getRequest().getSession().invalidate();
                return this.JsonViewError("登陆失败！账户[" + account + "]不存在，请返回重新登录！");

            } else {
                // 防止会话固定
                // HttpSession newSession=HttpSessionUtils.getCopySession(this.getRequest());
                fixeSession(userInfo);
                return this.JsonViewSuc("main.jsp");

            }
        } catch (Exception e) {
            this.getRequest().getSession().invalidate();
            logger.error(account + "登录失败", e);
            return this.JsonViewError("登陆失败！账户[" + account + "]" + e.getMessage());

        }

    }

    public String getMobileSms() {
        RequestUtils ru = this.getRequestUtils();
        HttpSession session = this.getRequest().getSession();
        String valcodeSession = StringUtils.trim((String) session.getAttribute("ValCode"));

        String username = ru.getTrimString("username");
        String valcode = ru.getTrimString("valcode");
        try {
            if (StringUtils.isEmpty(valcode)) {
                return this.JsonViewError("图像验证码不能为空");
            }
            if (StringUtils.isEmpty(username)) {
                return this.JsonViewError("账户不能为空");
            }

            if (valcode.equalsIgnoreCase(valcodeSession)) {
                SysUser users = beanGet.bean(UserInfoService.class).getAccountInfo(username);
                if (users != null) {
                    // 判断是否为测试
                    String smsTestTo = StringUtils.trim(
                            beanGet.bean(CbAppConfigProperties.class).getLoginConfig().getSms().getTestTo()
                    );
                    if (StringUtils.hasText(smsTestTo)) {
                        if (smsTestTo.equals("all")
                                || StringUtils.contain(smsTestTo, users.getPhone(), ",")) {
                            return this.JsonViewSuc();
                        } else {
                            String captcha = RandomUtils.getRandomNumberString(6);
                            SmsService service = beanGet.bean(SmsService.class);
                            String type = "1";
                            service.sendSms(type, users.getPhone(), "验证码：" + captcha + ",欢迎使用,有效时间为五分钟,请尽快验证", captcha);
                            return this.JsonViewSuc();
                        }
                    } else {

                        if (!StringUtils.isEmpty(users.getPhone())) {
                            String captcha = RandomUtils.getRandomNumberString(6);
                            SmsService service = beanGet.bean(SmsService.class);
                            String type = "1";
                            service.sendSms(type, users.getPhone(), "验证码：" + captcha + ",欢迎使用,有效时间为五分钟,请尽快验证", captcha);
                            return this.JsonViewSuc();
                        } else {
                            return this.JsonViewError("账号没有配置手机号");
                        }
                    }
                } else {
                    return this.JsonViewError("账户或密码错误");
                }
            } else {
                return this.JsonViewError("图像验证码输入错误");
            }

        } catch (Exception e) {
            logger.error("", e);
            this.JsonViewError("异常：" + e.getMessage());
        }
        return this.JsonViewSuc();
    }

    public void getSmsCode(String mobile) {
        // 判断是否为测试
        String smsTestTo = StringUtils.trim(
                beanGet.bean(CbAppConfigProperties.class).getLoginConfig().getSms().getTestTo()
        );
        if (StringUtils.hasText(smsTestTo)) {
            if (smsTestTo.equals("all") || StringUtils.contain(smsTestTo, mobile, ",")) {
                return;
            } else {
                String smscode = RandomUtils.getRandomNumberString(6);
                SmsService service = beanGet.bean(SmsService.class);
                String type = "1";
                service.sendSms(type, mobile, "验证码：" + smscode + ",欢迎使用,有效时间为五分钟,请尽快验证", smscode);
                return;
            }
        }

        if (!StringUtils.isEmpty(mobile)) {
            String smscode = RandomUtils.getRandomNumberString(6);
            SmsService service = beanGet.bean(SmsService.class);
            String type = "1";
            service.sendSms(type, mobile, "验证码：" + smscode + ",找回密码,有效时间为五分钟,请尽快验证", smscode);
        }

    }

    /**
     * 固定会话
     *
     * @param userInfo
     * @throws Exception
     */
    private void fixeSession(SessionUserInfo userInfo) throws Exception {
        HttpSession newSession = CbHttpSessionUtils.getCopySession(this.getRequest());
        newSession.setAttribute("token", RandomUtils.genUUID());
        SysUsersSession mySession = beanGet.bean(SysUsersSessionService.class).
                getUsersSession(Integer.valueOf(userInfo.getUser().getId()));
        logger.debug("loginSessionId:" + newSession.getId());
        if (mySession != null) {
            mySession.setLoginIp(CbIPAddressUtil.getRemoteAddr(this.getRequest()));
            mySession.setLoginTime(LocalDateTime.now());
            mySession.setSessionId(newSession.getId());
            beanGet.bean(SysUsersSessionService.class).updateUsersSession(mySession);
        } else {
            SysUsersSession juSession = new SysUsersSession();
            juSession.setLoginIp(CbIPAddressUtil.getRemoteAddr(this.getRequest()));
            juSession.setLoginTime(LocalDateTime.now());
            juSession.setSessionId(newSession.getId());
            juSession.setStatus(1);
            juSession.setSysUserId(Integer.valueOf(userInfo.getUser().getId()));
            beanGet.bean(SysUsersSessionService.class).insertUsersSession(juSession);
        }
    }

    /**
     * 检查用户是否存在
     *
     * @return
     */
    public String checkUserPhone() {
        HttpSession session = this.getRequest().getSession();
        RequestUtils ru = this.getRequestUtils();
        String account = ru.getString("account").trim();
        if (StringUtils.isEmpty(account)) {
            return this.JsonViewError("登陆账号不能为空");
        }


        try {
            SysUser user = beanGet.bean(UserInfoService.class).getAccountInfo(account);
            if (user != null) {
                getSmsCode(user.getPhone());
            } else {
                return this.JsonViewError("登录账号不存在");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            logger.error("", e);
        }
        return this.JsonViewSuc();
    }

    public String checkSmsCode() {
        RequestUtils ru = this.getRequestUtils();
        String smsCode = ru.getString("smsCode").trim();
        String account = ru.getString("account").trim();
        if (StringUtils.isEmpty(account)) {
            return this.JsonViewError("登陆不能为空");
        }

        if (StringUtils.isEmpty(smsCode)) {
            return this.JsonViewError("手机验证码不能为空");
        }

        try {
            // 判断是否测试
            String smsTestTo = StringUtils.trim(
                    beanGet.bean(CbAppConfigProperties.class).getLoginConfig().getSms().getTestTo()
            );
            if (StringUtils.hasText(smsTestTo)) {
                SysUser sysUser = beanGet.bean(UserInfoService.class).getAccountInfo(account);
                if (smsTestTo.equals("all")
                        || StringUtils.contain(smsTestTo, sysUser.getPhone(), ",")) {
                    String testSmsCode = StringUtils.trim(
                            beanGet.bean(CbAppConfigProperties.class).getLoginConfig().getSms().getTestToNoFixSmscode()
                    );
                    if (!smsCode.equals(testSmsCode)) {
                        return this.JsonViewError("请输入正确的短信验证码");// 短信验证码错误
                    }

                } else {
                    if (sysUser != null) {
                        // 获取短信信息
                        SysSms jsms = beanGet.bean(SmsService.class).getSmsInfo(sysUser.getPhone());
                        if (jsms == null) {//
                            return this.JsonViewError("手机验证码错误");
                        } else {
                            if (!smsCode.equals(jsms.getSmsCode())) {
                                return this.JsonViewError("请输入正确的短信验证码");// 短信验证码错误
                            } else {
                                return this.JsonViewSuc();
                            }
                        }
                    } else {
                        return this.JsonViewError("登陆账户不存在");
                    }

                }
            } else {

                SysUser sysUser = beanGet.bean(UserInfoService.class).getAccountInfo(account);
                if (sysUser != null) {
                    // 获取短信信息
                    SysSms jsms = beanGet.bean(SmsService.class).getSmsInfo(sysUser.getPhone());
                    if (jsms == null) {//
                        return this.JsonViewError("手机验证码错误");
                    } else {
                        if (!smsCode.equals(jsms.getSmsCode())) {
                            return this.JsonViewError("请输入正确的短信验证码");// 短信验证码错误
                        } else {
                            return this.JsonViewSuc();
                        }
                    }
                } else {
                    return this.JsonViewError("登陆账户不存在");
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            return this.JsonViewError(e.getMessage());
        }

        return this.JsonViewSuc();

    }

    public String savePassword() {
        RequestUtils ru = this.getRequestUtils();
        String password = ru.getTrimString("password");
        String account = ru.getTrimString("account");
        String smsCode = ru.getTrimString("smsCode");
        String conFirmPWD = ru.getTrimString("conFirmPWD");
        try {
            String privateKey = beanGet.bean(CbAppConfigProperties.class).getLoginConfig().getClientPrivateKey();
            password = RsaApplyUtils.decryptRsa(password, privateKey);
            conFirmPWD = RsaApplyUtils.decryptRsa(conFirmPWD, privateKey);
            if (StringUtils.isEmpty(password)) {
                return this.JsonViewError("密码不能为空");
            } else {

                if (password.length() >= 6 && password.length() <= 16) {
                    //////
                } else {
                    return this.JsonViewError("密码必须6-16位字符!");
                }
            }
            if (StringUtils.isEmpty(account)) {
                return this.JsonViewError("非正常路径进入操作");
            }
            if (StringUtils.isEmpty(conFirmPWD)) {
                return this.JsonViewError("确认密码不能为空");
            }
            if (StringUtils.isEmpty(smsCode)) {
                return this.JsonViewError("非正常路径进入操作");
            }

            if (conFirmPWD.equals(password)) {
                SysUser sysUser = beanGet.bean(UserInfoService.class).getAccountInfo(account);
                if (sysUser == null) {
                    return this.JsonViewError("账号不存在");
                }
                // 判断是否测试
                String smsTestTo = StringUtils.trim(
                        beanGet.bean(CbAppConfigProperties.class).getLoginConfig().getSms().getTestTo()
                );
                if (StringUtils.hasText(smsTestTo)) {
                    if (smsTestTo.equals("all")
                            || StringUtils.contain(smsTestTo, sysUser.getPhone(), ",")) {
                        String smsTestCode = StringUtils.trim(
                                beanGet.bean(CbAppConfigProperties.class).getLoginConfig().getSms().getTestToNoFixSmscode()
                        );
                        if (smsCode.equals(smsTestCode)) {
                            beanGet.bean(SysUserDao.class).changePassword(sysUser.getSysUserSno(), CbConst.getPassword(password));
                        } else {
                            return this.JsonViewError("非正常路径进入操作");
                        }
                    } else {
                        SysSms sms = beanGet.bean(SmsService.class).getSmsInfoRecent(sysUser.getPhone());
                        if (sms != null) {
                            if (smsCode.equals(sms.getSmsCode())) {
                                beanGet.bean(SysUserDao.class).changePassword(sysUser.getSysUserSno(), CbConst.getPassword(password));
                            } else {
                                return this.JsonViewError("非正常路径进入操作");
                            }
                        } else {
                            return this.JsonViewError("非正常路径进入操作");
                        }
                    }
                } else {
                    SysSms sms = beanGet.bean(SmsService.class).getSmsInfoRecent(sysUser.getPhone());
                    if (sms != null) {
                        if (smsCode.equals(sms.getSmsCode())) {
                            beanGet.bean(SysUserDao.class).changePassword(sysUser.getSysUserSno(), CbConst.getPassword(password));
                        } else {
                            return this.JsonViewError("非正常路径进入操作");
                        }
                    } else {
                        return this.JsonViewError("非正常路径进入操作");
                    }
                }
            } else {
                return this.JsonViewError("两次密码不一致");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            logger.error("", e);
            return this.JsonViewError(e.getMessage());
        }

        return this.JsonViewSuc();
    }

    public static void main(String[] args) {
        System.out.println(CbConst.getPassword("yscf100"));
    }

    public String selectUserInfo() throws Exception {
        HttpSession session = this.getRequest().getSession();
        SessionUserInfo userInfo = (SessionUserInfo) this.getUserInfo();
        String account = userInfo.getUser().getAccount();
        int userId = Integer.valueOf(userInfo.getUser().getId());
        int i = beanGet.bean(UserInfoService.class).getUserInfo(account, userId).getValue();
        if (i == 1) {
            return this.JsonViewError("因为您是第一次登录系统,密码过于简单,请尽快修改密码");
        }
        return this.JsonViewSuc("");
    }

    public String authLogin() {
        RequestUtils ru = this.getRequestUtils();
        ActionContext ctx = ActionContext.getContext();
        Integer sysUserId = ru.getInt("sysUserId");
        String mobile = ru.getTrimString("mobile");
        String sign = ru.getTrimString("sign");
        HttpServletRequest request = this.getRequest();
        HttpSession session = this.getRequest().getSession();
        try {
            if (!StringUtils.isEmpty(mobile) && sysUserId != null && !StringUtils.isEmpty(sign)) {
                DateFormat df = new SimpleDateFormat("yyyyMMdd");
                String key = df.format(new Date()) + mobile + sysUserId;
                String signKey = CbConst.getPassword(key);
                if (signKey.equals(sign)) {
                    UserInfoService userInfoService = beanGet.bean(UserInfoService.class);
                    SysUser sysUser = userInfoService.getUserById(sysUserId);
                    if (sysUser != null) {
                        List<UserRight> rights = new ArrayList<UserRight>();
                        SessionUserInfo userInfo = userInfoService.getUserInfo(sysUser.getAccount(),null);
                        if (userInfo != null) {
                            rights = userInfo.getRights();
                            this.setUserInfo(userInfo);
                            session.setAttribute("rights", rights);
                            return this.RedirectView("/main.jsp");
                        }
                    }
                }

            }
        } catch (Exception e) {
            logger.error(e, e);

        }
        return this.RedirectView("/index.jsp");
    }

    public String getAutoSignLoginUrl() {
        RequestUtils ru = this.getRequestUtils();
        String mobile = ru.getTrimString("mobile");
        Integer sysUserId = ru.getInt("sysUserId");
        try {
            if (!StringUtils.isEmpty(mobile) && sysUserId != null) {
                String path = this.getRequest().getContextPath();
                String basePath = this.getRequest().getScheme() + "://" + this.getRequest().getServerName() + ":" + this.getRequest().getServerPort() + path + "/";

                DateFormat df = new SimpleDateFormat("yyyyMMdd");
                String key = df.format(new Date()) + mobile + sysUserId;
                String signKey = CbConst.getPassword(key);

                basePath = basePath + "sys/sys_Login_authLogin.action?mobile=" + mobile + "&sysUserId=" + sysUserId + "&sign=" + signKey;

                return this.JsonViewSuc(basePath);
            } else {
                return this.JsonViewError("参数不能为空");
            }
        } catch (Exception e) {
            logger.error(e, e);
            return this.JsonViewError(e.getMessage());
        }

    }

}
