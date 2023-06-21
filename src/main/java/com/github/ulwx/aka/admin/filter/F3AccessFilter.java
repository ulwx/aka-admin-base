package com.github.ulwx.aka.admin.filter;

import com.github.ulwx.aka.admin.utils.CbAppConfigProperties;
import com.github.ulwx.aka.webmvc.AkaWebMvcProperties;
import com.github.ulwx.aka.webmvc.BeanGet;
import com.github.ulwx.aka.webmvc.WebMvcCbConstants;
import com.github.ulwx.aka.webmvc.utils.WebMvcUtils;
import com.github.ulwx.aka.webmvc.web.action.*;
import com.ulwx.tool.ArrayUtils;
import com.ulwx.tool.CollectionUtils;
import com.ulwx.tool.ObjectUtils;
import com.ulwx.tool.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;


@WebFilter(urlPatterns = {"*.jsp", "*.action","/swagger-ui/*"})
@Order(12)
public class F3AccessFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(F3AccessFilter.class);
    protected FilterConfig filterConfig;
    private String LoginPage;
    private String UserSeesionKey;
    private String AjaxURLSTR;
    private String MessagePage;
    private String AjaxMessagePage;
    private String[] NotFilterURLs;
    private List<String> accessPlugin;


    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
        CbAppConfigProperties adminProperties = BeanGet.getBean(CbAppConfigProperties.class, config.getServletContext());
        AkaWebMvcProperties properties = BeanGet.getBean(AkaWebMvcProperties.class, config.getServletContext());

        accessPlugin = adminProperties.getAccessFilter().getAccessPlugins();//

        LoginPage = properties.getGlobalViews().get(ActionSupport.LOGIN);//
        UserSeesionKey = WebMvcCbConstants.USER;
        AjaxURLSTR = WebMvcCbConstants.AjaxURLSTR;
        MessagePage = properties.getGlobalViews().get(ActionSupport.MESSAGE);//
        AjaxMessagePage = properties.getGlobalViews().get(ActionSupport.JSON); //
        LinkedHashSet<String> set = new LinkedHashSet<>();
        String error = StringUtils.trim(properties.getGlobalViews().get(ActionSupport.ERROR));
        String message = StringUtils.trim(properties.getGlobalViews().get(ActionSupport.MESSAGE));
        String json = StringUtils.trim(properties.getGlobalViews().get(ActionSupport.JSON));
        String login = StringUtils.trim(properties.getGlobalViews().get(ActionSupport.LOGIN));
        String forward = StringUtils.trim(properties.getGlobalViews().get(ActionSupport.FORWARD));
        String redirect = StringUtils.trim(properties.getGlobalViews().get(ActionSupport.REDIRECT));
        String download = StringUtils.trim(properties.getGlobalViews().get(ActionSupport.DOWNLOAD));
        String gate = StringUtils.trim(properties.getGlobalViews().get(ActionSupport.GATE));
        if (!error.isEmpty()) set.add(error);
        if (!message.isEmpty()) set.add(message);
        if (!json.isEmpty()) set.add(json);
        if (!login.isEmpty()) set.add(login);
        if (!forward.isEmpty()) set.add(forward);
        if (!redirect.isEmpty()) set.add(redirect);
        if (!download.isEmpty()) set.add(download);
        if (!download.isEmpty()) set.add(download);
        set.addAll(adminProperties.getAccessFilter().getNotFilterUrls());
        Map<String,AcesssNotFilerUrlsProvider> providers
                =BeanGet.getBeans(AcesssNotFilerUrlsProvider.class,config.getServletContext());
        if(providers!=null && providers.size()>0){
            for(String key: providers.keySet()){
                AcesssNotFilerUrlsProvider provider=providers.get(key);
                List<String>  ret=provider.builder();
                set.addAll(ret);
            }
        }
        NotFilterURLs = set.toArray(new String[0]);//

        if (LoginPage == null) {
            log.error("loginPage init param missing!");
        }
    }

    public void doFilter(final ServletRequest req, final ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest hreq = (HttpServletRequest) req;
        HttpServletResponse hres = (HttpServletResponse) res;
        Object userInfo = (Object) hreq.getSession().getAttribute(UserSeesionKey);
        String ruri = hreq.getRequestURI();
        log.debug("ruri=" + ruri);
        String contextPath = hreq.getContextPath();

        if (ArrayUtils.isNotEmpty(NotFilterURLs)) {
            String[] strs = NotFilterURLs;
            if (ArrayUtils.isNotEmpty(strs)) {
                for (int i = 0; i < strs.length; i++) {
                    if (strs[i].startsWith("/")) {
                        if (ruri.startsWith(contextPath + strs[i])) {
                            chain.doFilter(req, res);
                            return;
                        }
                    } else {
                        if (StringUtils.endsWith(ruri, strs[i], false)) {
                            chain.doFilter(req, res);
                            return;
                        }
                    }
                }
            }

        }

        if (userInfo != null) {
            if (CollectionUtils.isNotEmpty(accessPlugin)) {
                String[] plugins = accessPlugin.toArray(new String[0]);
                for (int i = 0; i < plugins.length; i++) {
                    AccessPlugin plugin = null;
                    AccessBean ab = null;
                    try {
                        plugin = (AccessPlugin) Class.forName(plugins[i].trim()).newInstance();
                        ab = plugin.doVerify(hreq, hres, this);
                    } catch (Exception e) {
                        log.error(e + "", e);
                        ab = new AccessBean();
                        ab.setStatus(0);
                        ab.setMessage("服务器内部处理错误，请联系管理员！" + e + "");
                    }
                    if (ab.getStatus() == 1) {//
                        ///
                    } else {
                        if (WebMvcUtils.isAjax(hreq)) {
                            log.debug("JSON request");
                            hres.setHeader("sessionstatus", "control");
                            AccessResult accessResult = new AccessResult();
                            accessResult.setCode(0);
                            accessResult.setContent(ab.getMessage());
                            accessResult.setLogin(hreq.getContextPath() + "" + LoginPage);
                            accessResult.setExit(ab.getIsExit());
                            accessResult.setStatus(Status.ERR);
                            accessResult.setMessage(ab.getMessage());
                            String result = ObjectUtils.toJsonString(accessResult);
                            ActionContext.getContext().getRequestUtils(hreq).setString("callback",JSONP(hreq));
                            hreq.setAttribute(WebMvcCbConstants.ResultKey,
                                    CbResult.of(Status.ERR,0, ab.getMessage(),accessResult));
                            RequestDispatcher rd = hreq.getRequestDispatcher( AjaxMessagePage);
                            rd.forward(hreq, hres);

                            return;
                        } else {
                            String login =  hreq.getContextPath() + LoginPage;
                            if (ab.getIsExit() == 1) {
                                login =  hreq.getContextPath() + LoginPage;
                            } else {
                                login="";
                            }
                            MsgResult msgResult=new MsgResult();
                            msgResult.setMsg(ab.getMessage());
                            msgResult.setReturnURL(login);
                            CbResult cbResult =msgResult.getResult(Status.ERR, 0, ab.getMessage());
                            hreq.setAttribute(WebMvcCbConstants.ResultKey, cbResult);
                            RequestDispatcher rd = hreq.getRequestDispatcher(MessagePage);
                            rd.forward(hreq, hres);
                            return;
                        }

                    }

                }
            }

            chain.doFilter(req, res);
            return;

        } else { //session为空
            if (WebMvcUtils.isAjax(hreq)) {// 如果是json请求，跳转到json出错页面

                log.debug("JSON request");
                hres.setHeader("sessionstatus", "timeout");
                AccessResult accessResult = new AccessResult();
                accessResult.setCode(0);
                accessResult.setContent("您已经超时，请重新登陆！");
                accessResult.setMessage("您已经超时，请重新登陆！");
                accessResult.setLogin(hreq.getContextPath() + "" + LoginPage);
                accessResult.setStatus(Status.ERR);
                String result = ObjectUtils.toJsonString(accessResult);
                ActionContext.getContext().getRequestUtils(hreq).setString("callback",JSONP(hreq));
                CbResult cbResult = CbResult.of(Status.ERR,0, accessResult.getMessage(),accessResult);
                hreq.setAttribute(WebMvcCbConstants.ResultKey, cbResult);
               //hreq.setAttribute("json", jsonResult);
                RequestDispatcher rd = hreq.getRequestDispatcher(AjaxMessagePage);
                rd.forward(hreq, hres);
                return;
            }
            String login = hreq.getContextPath() + "" + LoginPage;
            String message = "您还没有登录或者您长时间没有使用登录系统，请重新登录系统！";
            MsgResult msgResult=new MsgResult();
            msgResult.setMsg(message);
            msgResult.setReturnURL(login);
            CbResult cbResult =msgResult.getResult(Status.ERR, 0, message);
            hreq.setAttribute(WebMvcCbConstants.ResultKey, cbResult);
            RequestDispatcher rd = hreq.getRequestDispatcher(MessagePage);
            rd.forward(hreq, hres);
            return;
        }

    }

    protected String JSONP(HttpServletRequest hreq) {
        String funcName = hreq.getParameter("callback");
        return funcName;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void setFilterConfig(final FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public static void main(String[] args) {

    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public String getLoginPage() {
        return LoginPage;
    }

    public String getUserSeesionKey() {
        return UserSeesionKey;
    }

    public String getAjaxURLSTR() {
        return AjaxURLSTR;
    }

    public String getMessagePage() {
        return MessagePage;
    }

    public String getAjaxMessagePage() {
        return AjaxMessagePage;
    }

    public String[] getNotFilterURLs() {
        return NotFilterURLs;
    }


    public List<String> getAccessPlugin() {
        return accessPlugin;
    }


}
