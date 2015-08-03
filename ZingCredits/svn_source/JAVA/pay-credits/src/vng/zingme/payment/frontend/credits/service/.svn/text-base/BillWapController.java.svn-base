/**
 *Class: BillController *
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.service;

import hapax.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.wte.common.Config;
import vng.zingme.payment.bo.thrift.AppServiceHandler;
import vng.zingme.payment.bo.thrift.PaymentGatewayHandler;
import vng.zingme.payment.frontend.credits.config.Configuration;
import vng.zingme.payment.frontend.credits.utils.LogUtil;
import vng.zingme.payment.frontend.credits.utils.Monitor;
import vng.zingme.payment.frontend.credits.utils.ScriberPayment;
import vng.zingme.payment.frontend.credits.utils.Utils;
import vng.zingme.payment.thrift.T_AppInfo;
import vng.zingme.payment.thrift.T_Response;
import vng.zingme.payment.thrift.T_Token;

/**
 * process bill an order
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: May 19, 2011
 */
public class BillWapController extends CreditsCore {

    private final String PARAM_STATS = "stats";
    private static Logger logger_ = Logger.getLogger(BillWapController.class);
    private final Monitor readStats = new Monitor();

    public BillWapController() {
        _memClient = getMemClient();
        _userAppMemClient = getUserAppMemClient();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            logger_.error(LogUtil.stackTrace(ex));
            this.echo(Configuration.SYSTEM_MAINTAIN_TEXT, response);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws TException, TemplateException {
        long startTime = System.nanoTime();
        String stats = request.getParameter(PARAM_STATS);
        if (stats != null && stats.equals(PARAM_STATS)) {
            this.echo(this.readStats.dumpHtmlStats(), response);
            return;
        }
        if (!this.validUser(request, response)) {
            return;
        }
        int userID = Integer.parseInt(request.getAttribute("zme.viewerId").toString());
        String pToken = null;

        if (request.getAttribute("pToken") != null) {
            pToken = request.getAttribute("pToken").toString();
        } else {
            pToken = request.getParameter("pToken");
        }

        String refID = null;
        if (request.getAttribute("refID") != null) {
            refID = request.getAttribute("refID").toString();
        } else {
            refID = request.getParameter("refID");
        }

        if (!this.validateParams(request, pToken) || pToken == null || refID == null) {
            this.redirectWapError(request, response);
            return;
        }
        //for internal call from requestform
        String key = this.getTokenCachedKey(null, request);
        String[] dataToken = (String[]) getTokenMemCache(key);
        if (dataToken == null || dataToken.length != 2 || dataToken[0].equals("1")) {
            this.redirectWapError(request, response);
            return;
        }
        updateTokenCached(request, pToken);
        T_Response resCode = bill(request, new T_Token(pToken, 0));
        ScriberPayment.sendLogStep3(userID, request.getParameter("appID"), refID, resCode.code, resCode.refNo, getClientIP(request), Configuration.SYSTEM_URL, Config.getParam("system", "host"));
        Object userAppCache = this.getUserAppMemCache(userID, request.getParameter("appID"));
        echoAndStats(startTime, this.renderByTemplate(refID, resCode, userID, userAppCache, request), response);
    }

    private void echoAndStats(long startTime, String html, HttpServletResponse response) {
        this.echo(html, response);
        this.readStats.addMicro((System.nanoTime() - startTime) / 1000);
    }

    boolean validateParams(HttpServletRequest request, String pToken) {
        if (request.getParameter("appID") == null || request.getParameter("appID").equals("")
                || request.getParameter("data") == null || request.getParameter("data").equals("")
                || pToken == null || pToken.equals("")) {
            return false;
        }
        return true;
    }

    private T_Response bill(HttpServletRequest request, T_Token token) {
        int hope_count = 3;
        boolean flag = false;
        T_Response obj = null;
        while (hope_count > 0 && !flag) {
            try {
                PaymentGatewayHandler gatewayHandler = PaymentGatewayHandler.getMainInstance(Configuration.PAYMENT_GATEWAY_HOST, Configuration.PAYMENT_GATEWAY_PORT);
                obj = gatewayHandler.bill(request.getParameter("appID"), request.getParameter("data"), token, getClientIP(request));
                if (obj != null && obj.getRefNo() != null) {
                    flag = true;
                }
            } catch (Exception ex) {
                logger_.warn(ex);
            }
            --hope_count;
        }
        return obj;
    }

    private void updateTokenCached(HttpServletRequest request, String pToken) {
        String key = this.getTokenCachedKey(null, request);
        String[] dataToken = (String[]) this.getTokenMemCache(key);
        if (dataToken != null && dataToken.length == 2) {
            if (dataToken[0].equals("0") && dataToken[1].equals(pToken)) {
                dataToken[0] = "1";
                setTokenMemCache(key, dataToken);
            }
        } else {
            dataToken = new String[2];
            dataToken[0] = "1";
            dataToken[1] = pToken;
            setTokenMemCache(key, dataToken);
        }
    }

    private String renderByTemplate(String refID, T_Response resCode, int userID, Object userAppCache, HttpServletRequest request) throws TemplateException, TException {
        TemplateLoader templateLoader = TemplateResourceLoader.create("view/");
        Template template = templateLoader.getTemplate("wap_layout");
        TemplateDataDictionary dic = TemplateDictionary.create();
        dic.setVariable("PAYTITLE", Configuration.SYSTEM_BILLINGFORM_TITLE);
        dic.setVariable("PAYURL", Configuration.SYSTEM_URL);
        dic.setVariable("CREDITSURL", Configuration.MOBILE_WAP_CREDITS_URL);
        dic.setVariable("STATIC_URL", Configuration.STATIC_URL);
        dic.setVariable("BILLSTATUS_URL", Configuration.SYSTEM_BILLSTATUS_URL);
        T_AppInfo appInfo = getAppInfo(request.getParameter("appID"));
        if (appInfo != null) {
            dic.setVariable("APPICON", appInfo.getIconPath());
            dic.setVariable("APPID", appInfo.getAppID());
            dic.setVariable("APPNAME", appInfo.getAppName());
            dic.setVariable("APPFULLURL", appInfo.getAppURL());
            dic.showSection("wap_top");
            dic.showSection("wap_header");
            if (resCode.getCode() >= 0) {
                TemplateDataDictionary userAppSection = dic.addSection("USERAPP");
                if (userAppCache != null && ((Integer) (userAppCache)).intValue() == 1) {
                    userAppSection.addSection("LITE_CHECKED");
                }
                userAppSection.setVariable("APPNAME", appInfo.getAppName());
            }
        }

        dic.setVariable("REFID", refID);
        dic.setVariable("TRANXID", resCode.refNo);
        request.setAttribute("USERID", userID);
        if (resCode.getCode() != 0) {
            dic.addSection("BILLING_FAIL_FORM");
            dic.showSection("wap_error");
            switch (resCode.getCode()) {
                case -2:
                    dic.addSection("BILL_RESULT__2");
                    break;
                case -3:
                    dic.addSection("BILL_RESULT__3");
                    break;
                case -5://app disable
                    dic.addSection("BILL_RESULT_OTHER");
                    break;
                default:
                    dic.addSection("BILL_RESULT_OTHER");
                    break;
            }

        } else {
            dic.addSection("BILLING_SUCCESS_FORM");
            dic.showSection("wap_success");
        }

        TemplateDataDictionary footer = dic.addSection("wap_footer");
        footer.setVariable("APPURL", Utils.removeHTTP(appInfo.appURL));
        return template.renderToString(dic);
    }

    public static void main(String[] args) {
        try {
            AppServiceHandler.getMainInstance("10.199.18.64", 10114).removeIdFromWhitelist("ntvv", 777);
            int i = 10;
        } catch (TException ex) {
            ex.printStackTrace();
        }
    }
}
