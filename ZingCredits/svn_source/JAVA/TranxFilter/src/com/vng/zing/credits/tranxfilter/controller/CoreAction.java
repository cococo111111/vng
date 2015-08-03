package com.vng.zing.credits.tranxfilter.controller;

import com.vng.jcore.common.Config;
import com.vng.zing.credits.tranxfilter.util.SecurityHelper;
import hapax.Template;
import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import hapax.TemplateException;
import hapax.TemplateLoader;
import hapax.TemplateResourceLoader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author quytp2@vng.com.vn
 */
public abstract class CoreAction extends HttpServlet {

    static final String COOKIE_ADMIN_AUTH_KEY = "ZING_ADMIN_CREDITS_AUTH";
    static final String COOKIE_ADMIN_AUTH_SIG_KEY = "ZING_ADMIN_CREDITS_AUTH_SIG";
    static final String COOKIE_ADMIN_AUTH_ROLE = "ZING_ADMIN_CREDITS_AUTH_ROLE";
    
    protected void print(Object obj, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(obj);
    }
    
    protected void printJSON(Object json, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(json);
    }
    
    protected String getClientIP(HttpServletRequest request) {
        return request.getRemoteAddr();
    }
    
    protected TemplateDataDictionary getDictionary() {
        TemplateDataDictionary dic = TemplateDictionary.create();
        dic.setVariable("domain", Config.getParam("APP", "domain"));
        return dic;
    }
    
    public static String getCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        String value = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(name)) {
                    value = cookies[i].getValue();
                    break;
                }
            }
        }
        return value;
    }
    
    protected boolean validUser(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
//        String admin_auth_key = getCookie(request, COOKIE_ADMIN_AUTH_KEY);
//        String admin_auth_role = getCookie(request, COOKIE_ADMIN_AUTH_ROLE);
//        String admin_auth_sign_key = getCookie(request, COOKIE_ADMIN_AUTH_SIG_KEY);
//        if (admin_auth_role != null && (Config.getParam("USER", "sysadmin").equals(admin_auth_role) || Config.getParam("USER", "admin").equals(admin_auth_role))) {
//            if (SecurityHelper.md5(admin_auth_key + admin_auth_role + Config.getParam("USER", "sign_key")).equals(admin_auth_sign_key)) {
//                return true;
//            }
//        }
//        return false;
        return true; // TODO : LENTD will be changed
    }    
    
    protected String applyTemplate(TemplateDataDictionary dic, String tplName) throws TemplateException {
        TemplateLoader templateLoader = TemplateResourceLoader.create("com/vng/zing/credits/tranxfilter/view/");
        Template template = templateLoader.getTemplate(tplName);
        return template.renderToString(dic);
    }    
    
    protected String applyTemplate(TemplateDataDictionary dic) throws TemplateException {
        TemplateLoader templateLoader = TemplateResourceLoader.create("com/vng/zing/credits/tranxfilter/view/");
        Template template = templateLoader.getTemplate("layout");
        return template.renderToString(dic);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
