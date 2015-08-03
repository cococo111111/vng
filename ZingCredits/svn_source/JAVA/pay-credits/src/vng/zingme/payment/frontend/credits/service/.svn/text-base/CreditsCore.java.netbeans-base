/**
 *Class: CoreServlet  
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.service;

import FwSession.SessionResult;
import com.vng.zing.jni.zcommon.std__vectorT_std__string_t;
import com.vng.zing.jni.zcommon.zcommon_StringHolder;
import com.vng.zing.jni.zcypher.ZCypher;
import hapax.TemplateDataDictionary;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.spy.memcached.MemcachedClient;
import org.apache.log4j.Logger;
import vng.wte.common.LogUtil;
import vng.zingme.payment.common.AppInfoCache;
import vng.zingme.payment.frontend.credits.config.Configuration;
import vng.zingme.payment.frontend.credits.config.ExtraConfig;
import vng.zingme.payment.frontend.credits.utils.HttpHelper;
import vng.zingme.payment.frontend.credits.utils.HugeListReadHandler;
import vng.zingme.payment.frontend.credits.utils.Utils;
import vng.zingme.payment.frontend.credits.utils.ZAuthHandler;
import vng.zingme.payment.frontend.credits.utils.ZAuthStruct;
import vng.zingme.payment.thrift.T_AppInfo;
import vng.zingme.payment.thrift.T_Transaction;

/**
 * Core Servlet for Credits application
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: Apr 18, 2011
 */
public abstract class CreditsCore extends HttpServlet {

    private static Logger logger_ = Logger.getLogger(CreditsCore.class);
    protected MemcachedClient _memClient = null;
    protected final Object _tokenmemClientLock = new Object();
    protected final Object _appmemClientLock = new Object();
    protected MemcachedClient _userAppMemClient = null;
    private final String BALANCE_PREFIX_PAY = "pay";

    protected MemcachedClient getMemClient() {
        synchronized (_tokenmemClientLock) {
            if (_memClient == null) {
                _memClient = getTokenMemCacheClient(Configuration.TOKENCACHE_HOST, Configuration.TOKENCACHE_PORT);
            }
        }
        return _memClient;
    }

    protected MemcachedClient renewMemClient() {
        synchronized (_tokenmemClientLock) {
            if (_memClient != null) {
                _memClient.shutdown();
            }
            _memClient = getTokenMemCacheClient(Configuration.TOKENCACHE_HOST, Configuration.TOKENCACHE_PORT);
        }
        return _memClient;
    }

    protected MemcachedClient getUserAppMemClient() {
        synchronized (_appmemClientLock) {
            if (_userAppMemClient == null) {
                _userAppMemClient = getTokenMemCacheClient(Configuration.USERAPPCACHE_HOST, Configuration.USERAPPCACHE_PORT);
            }
        }
        return _userAppMemClient;
    }

    protected MemcachedClient renewUserAppMemClient() {
        synchronized (_appmemClientLock) {
            if (_userAppMemClient != null) {
                _userAppMemClient.shutdown();
            }
            _userAppMemClient = getTokenMemCacheClient(Configuration.USERAPPCACHE_HOST, Configuration.USERAPPCACHE_PORT);
        }
        return _userAppMemClient;
    }

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

    protected void setView(String key, Object value, HttpServletRequest request) {
        request.setAttribute(key, value);
    }

    protected void echo(Object text, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            if (out != null) {
                out.print(text);
                out.close();
            }
        } catch (IOException ex) {
            logger_.error(LogUtil.stackTrace(ex));
        } finally {
            out.close();
        }
    }

    protected void redirectError(HttpServletRequest request, HttpServletResponse response) {
        try {
            String html = ErrorController.processRequest(request, response);
            echo(html, response);
        } catch (Exception ex) {
            logger_.error(LogUtil.stackTrace(ex));
        }
    }

    protected void redirectMobileError(HttpServletRequest request, HttpServletResponse response) {
        try {
            String html = ErrorMobileController.processRequest(request, response);
            echo(html, response);
        } catch (Exception ex) {
            logger_.error(ex.getMessage());
        }
    }

    protected void redirectWapError(HttpServletRequest request, HttpServletResponse response) {
        try {
            String html = ErrorWapController.processRequest(request, response);
            echo(html, response);
        } catch (Exception ex) {
            logger_.error(ex.getMessage());
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
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    protected static T_AppInfo getAppInfo(String appID) {
        AppInfoCache cache = AppInfoCache.getInstance();
        return cache.get(appID);
    }

    protected MemcachedClient getTokenMemCacheClient(String host, int port) {
        MemcachedClient client = null;
        try {
            client = new MemcachedClient(new InetSocketAddress(host, port));
        } catch (Exception ex) {
            logger_.warn(ex);
        }
        return client;
    }

    protected boolean setTokenMemCache(String key, Object val) {
        int hope_count = 3;
        boolean res = false;
        while (hope_count > 0 && !res) {
            try {
                _memClient.set(key, 0, val);
                res = true;
            } catch (Exception ex) {
                logger_.warn(ex);
                renewMemClient();
            }
            --hope_count;
        }
        return res;
    }

    protected boolean setUserAppMemCache(int userID, String appID, int val) {
        int hope_count = 3;
        boolean res = false;
        while (hope_count > 0 && !res) {
            try {
                _userAppMemClient.set(getUserAppMemCacheKey(userID, appID), Configuration.USERAPPCACHE_EXPIRE, val);
                res = true;
            } catch (Exception ex) {
                logger_.warn(ex);
                renewUserAppMemClient();
            }
            --hope_count;
        }
        return res;
    }

    private String getUserAppMemCacheKey(int userID, String appID) {
        return userID + "_" + appID;
    }

    protected boolean delUserAppMemCache(int userID, String appID) {
        int hope_count = 3;
        boolean res = false;
        while (hope_count > 0 && !res) {
            try {
                _userAppMemClient.delete(getUserAppMemCacheKey(userID, appID));
                res = true;
            } catch (Exception ex) {
                logger_.warn(ex);
                renewUserAppMemClient();
            }
            --hope_count;
        }
        return res;
    }

    protected Object getTokenMemCache(String key) {
        int hope_count = 3;
        boolean flag = false;
        Object obj = null;
        while (hope_count > 0 && !flag) {
            try {
                obj = _memClient.get(key);
                if (obj != null) {
                    flag = true;
                }
            } catch (Exception ex) {
                logger_.warn(ex);
                renewMemClient();
            }
            --hope_count;
        }
        return obj;
    }

    protected Object getUserAppMemCache(int userID, String appID) {
        int hope_count = 3;
        boolean flag = false;
        Object obj = null;
        while (hope_count > 0 && !flag) {
            try {
                obj = _userAppMemClient.get(getUserAppMemCacheKey(userID, appID));
                if (obj != null) {
                    flag = true;
                }
            } catch (Exception ex) {
                logger_.warn(ex);
                renewUserAppMemClient();
            }
            --hope_count;
        }
        return obj;
    }

    protected String getTokenCachedKey(T_Transaction transaction, HttpServletRequest request) {
        String key = request.getParameter("appID");
        if (transaction != null) {
            key += transaction.getRefID();
        } else {
            if (request.getAttribute("refID") != null) {
                key += request.getAttribute("refID");
            } else {
                key += request.getParameter("refID");
            }
        }
        return key;
    }

    protected boolean validUser(HttpServletRequest request, HttpServletResponse response) {
        //only in test enviroment>AAFeSngO%2F03jJ9wAAAAAAKHvEeI%3D
        //request.setAttribute("vngauth", "4AFb0VBywE7jJ9wAAAAAAJNV2ro%3D");
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

    protected static void makeExt(TemplateDataDictionary dic, HttpServletRequest request) {
        String section = "default";
        if (request.getParameter("_type") != null) {
            section = request.getParameter("_type");
        }
        dic.setVariable("header-top", ExtraConfig.getParam(section, "header-top"));
        dic.setVariable("payfor", ExtraConfig.getParam(section, "payfor"));
        dic.setVariable("credits-info", ExtraConfig.getParam(section, "credits-info"));
        dic.setVariable("billing-table-column1", ExtraConfig.getParam(section, "billing-table-column1"));
        dic.setVariable("billing-table-column2", ExtraConfig.getParam(section, "billing-table-column2"));
        dic.setVariable("billing-table-column3", ExtraConfig.getParam(section, "billing-table-column3"));
        dic.setVariable("billing-table-column4", ExtraConfig.getParam(section, "billing-table-column4"));
        dic.setVariable("billing-table-column5", ExtraConfig.getParam(section, "billing-table-column5"));
        dic.setVariable("billing-table-column6", ExtraConfig.getParam(section, "billing-table-column6"));
        dic.setVariable("billing-footer", ExtraConfig.getParam(section, "billing-footer"));
        dic.setVariable("billing-error", ExtraConfig.getParam(section, "billing-error"));
        dic.setVariable("billing-success-waiting", ExtraConfig.getParam(section, "billing-success-waiting"));
        dic.setVariable("billing-success-success", ExtraConfig.getParam(section, "billing-success-success"));
        dic.setVariable("billing-success-unsuccess", ExtraConfig.getParam(section, "billing-success-unsuccess"));
        dic.setVariable("billing-success-quick", ExtraConfig.getParam(section, "billing-success-quick"));
        dic.setVariable("billing-success-quick-confirm1", ExtraConfig.getParam(section, "billing-success-quick-confirm1"));
        dic.setVariable("billing-success-quick-confirm2", ExtraConfig.getParam(section, "billing-success-quick-confirm2"));
        dic.setVariable("VIEWTYPE", section);

    }
    
    protected std__vectorT_std__string_t zingUnSignature(T_AppInfo appInfo, String agentID, String encodedData) {
        std__vectorT_std__string_t zma_decode = zma_decode(agentID, encodedData, BALANCE_PREFIX_PAY + appInfo.getKey1());
        return zma_decode;
    }
    
    protected std__vectorT_std__string_t zma_decode(String agentID, String strEncoded, String k) {
        std__vectorT_std__string_t params2 = new std__vectorT_std__string_t();

        zcommon_StringHolder key = new zcommon_StringHolder();

        key.setValue(k);
        zcommon_StringHolder data = new zcommon_StringHolder();
        data.setValue(strEncoded);

        int e = ZCypher.zma_decode(params2, data, key, 0, 300);
        if (e < 0) {
            return null;
        }

        return params2;
    }
}
