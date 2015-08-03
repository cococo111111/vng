/**
 *Class: CoreServlet  
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.service;

import FwSession.SessionResult;
import hapax.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.zingme.payment.bo.thrift.NameToIDHandler;
import vng.zingme.payment.common.AppInfoCache;
import vng.zingme.payment.frontend.credits.config.Configuration;
import vng.zingme.payment.frontend.credits.utils.*;
import vng.zingme.payment.thrift.T_AppInfo;

/**
 * Core Servlet for Credits application
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: Apr 18, 2011
 */
public abstract class CreditsCore extends HttpServlet {

    private static Logger logger_ = Logger.getLogger(CreditsCore.class);

    /**
     * check maintain flag.If true we echo to client and return true.<br>
     * Therefore, creditsControler will stop process
     * @param request
     * @param response
     * @return is maintain or not
     */
    protected boolean checkMainTain(int userID, HttpServletRequest request, HttpServletResponse response) {
        if (Configuration.IS_MAINTAIN_FLG && (Configuration.SYSTEM_MAINTAIN_ALOWS_USER.contains(userID + ""))) {
            return false;
        }
        if (Configuration.IS_MAINTAIN_FLG) {
            this.echo(Configuration.SYSTEM_MAINTAIN_TEXT, response);
            return true;
        }
        return false;
    }

    /**
     * check login user whether in white-list(only white list flag is enabled)
     * @param userID
     * @param request
     * @param response
     * @return true :(if white-list is enable and user in white-list) <br>
     * or(if white-list is disabled)
     */
    protected boolean checkWhiteList(int userID, HttpServletRequest request, HttpServletResponse response) {
        if (Configuration.IS_CHECK_WHITELIST_FLG) {
            try {
                HugeListReadHandler hugeListClient = HugeListReadHandler.getMainInstance();
                boolean hasEntry = hugeListClient.li_HasEntry(Configuration.HUGELIST_READ_APPID, userID);
                if (!hasEntry) {
                    this.echo("<p style='text-align:center'>Bạn không được phép truy cập hệ thống Zing Credits</p>", response);
                    return false;
                }
            } catch (Exception ex) {
                logger_.error(ex.getMessage());
            }
        }
        return true;
    }

   
    protected void echo(Object text, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            out.print(text);
            out.close();
        } catch (IOException ex) {
            logger_.error(ex.getMessage());
        } finally {
            out.close();
        }
    }

    protected boolean initUser(String zauth, HttpServletRequest req, HttpServletResponse resp) {
        if (zauth != null && !zauth.equals("")) {
            ZAuthStruct userLogin = new ZAuthStruct();
            int ret = this.getUserLogged(zauth, userLogin);
            if (ret >= 0) {
                String session_id = zauth;
                req.setAttribute("zme.isLogged", true);
                req.setAttribute("zme.viewerId", new Integer(userLogin.userid));
                req.setAttribute("zme.viewerName", userLogin.uname);
                req.setAttribute("zme.session_id", session_id);
                return true;
            }
        }
        req.setAttribute("zme.isLogged", false);
        req.setAttribute("zme.viewerId", 0);
        req.setAttribute("zme.viewerName", "");
        req.setAttribute("zme.session_id", "");
        return false;
    }

    private SessionResult getSession(String zauth) {
        int hope_count = 3;
        SessionResult result = null;
        boolean res = false;
        while (hope_count > 0 && !res) {
            try {
                result = ZAuthHandler.getMainInstance().GetSession(zauth);
                if (result != null && result.getSession() != null) {
                    res = true;
                }
            } catch (Exception ex) {
                logger_.warn(ex);
            }
            --hope_count;
        }
        return result;
    }

    private int name2id(String name) {
        int hope_count = 3;
        int result = 0;
        boolean res = false;
        while (hope_count > 0 && !res) {
            try {
                result = Utils.getUIDFromUName(name);
                if (result >= 0) {
                    res = true;
                }
            } catch (Exception ex) {
                logger_.warn(ex);
            }
            --hope_count;
        }
        return result;
    }

    public int getUserLogged(String zauth, ZAuthStruct struct) {
        try {
            SessionResult sessionResult = getSession(zauth);
            if (sessionResult == null || sessionResult.getSession() == null || sessionResult.getResultCode() < 0) {
                return -1;
            } else {
                String uname = sessionResult.getSession().getAccountName();
                struct.userid = name2id(uname);
                struct.uname = uname;
                return 0;

            }
        } catch (Exception ex) {
            return -1;
        }
    }

    protected String getClientIP(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    protected static T_AppInfo getAppInfo(String appID) {
        AppInfoCache cache = AppInfoCache.getInstance();
        //try get application from cache,If not call admin service
        T_AppInfo appInfo = cache.get(appID);
        if (appInfo != null) {
            return appInfo;
        }
        return appInfo;
    }

    protected boolean validUser(HttpServletRequest request, HttpServletResponse response) {
        //only in test enviroment>
      // request.setAttribute("vngauth", "AAEU1xD%2FrU7jJ9wAAAAAAJLpocA%3D");
      // String zauth = request.getAttribute("vngauth").toString();
        //<only in test enviroment



       String zauth = HttpHelper.getCookie(request, "vngauth");
   
        if (zauth == null) {
        this.echo("<p style='text-align:center'>Bạn không được phép truy cập hệ thống Zing Credits</p>", response);
        return false;
        }
        try {
        zauth = URLDecoder.decode(zauth, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
        this.echo("<p style='text-align:center'>Bạn không được phép truy cập hệ thống Zing Credits</p>", response);
        return false;
        }

     

        boolean isLogged = this.initUser(zauth, request, response);
        if (!isLogged) {
            this.echo("<p style='text-align:center'>Bạn không được phép truy cập hệ thống Zing Credits</p>", response);
            return false;
        }
        //check white list
        int userID = Integer.parseInt(request.getAttribute("zme.viewerId").toString());
        //check maintain flg
        if (this.checkMainTain(userID, request, response)) {
            return false;
        }
        if (!this.checkWhiteList(userID, request, response)) {
            return false;
        }
        return true;
    }
    protected void print(Object obj, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(obj);
    }
    
    protected String applyTemplateAdmin(TemplateDataDictionary dic) throws TemplateException {
        TemplateLoader templateLoader = TemplateResourceLoader.create("view/admin/");
        Template template = templateLoader.getTemplate("layout");
        return template.renderToString(dic);
    }
    protected String applyTemplateDetail(TemplateDataDictionary dic) throws TemplateException {
        TemplateLoader templateLoader = TemplateResourceLoader.create("view/admin/");
        Template template = templateLoader.getTemplate("tranxbyuser_detail");
        return template.renderToString(dic);
    }
    
    protected List<T_AppInfo> sortApp(List<T_AppInfo> appInfos) {
        try {
            Collections.sort(appInfos, new Comparator<T_AppInfo>() {

                public int compare(T_AppInfo app1, T_AppInfo app2) {
                    return app1.getAppID().compareTo(app2.getAppID());
                }
            });
        } catch (Exception ex) {
            logger_.error(LogUtil.stackTrace(ex));
        }
        return appInfos;
    }
    public static void main(String[] args) throws TException {
       Calendar c=Calendar.getInstance();
       c.setTimeInMillis(1318993210);
        System.out.println(c.getTime());
        c.setTimeInMillis(1318993210*1000);
        System.out.println(c.getTime());
         c=Calendar.getInstance();
         System.out.println(c.getTimeInMillis()/1000);
    }
 
 
}
