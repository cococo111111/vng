/**
 *Class: RequestFormController *
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
import vng.zingme.payment.frontend.credits.config.Configuration;
import vng.zingme.payment.frontend.credits.utils.LogUtil;
import vng.zingme.payment.frontend.credits.utils.Utils;
import vng.zingme.payment.thrift.T_AppInfo;

/**
 * get request form
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: May 19, 2011
 */
public class ErrorController extends CreditsCore {

    private static Logger logger_ = Logger.getLogger(ErrorController.class);

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
            logger_.error(LogUtil.throwableToString(ex));
            this.echo(Configuration.SYSTEM_MAINTAIN_TEXT, response);
        }
    }

    public static String processRequest(HttpServletRequest request, HttpServletResponse response) throws TException, TemplateException {
        String version = request.getParameter("_v");
        if (version == null) {
            version = "2";
        }
        String _t = request.getParameter("_t");
        if (_t == null) {
            _t = "0";
        }
        
        String _url = request.getParameter("_url");
        if (_url == null) {
            _url = "";
        }
        TemplateLoader templateLoader = TemplateResourceLoader.create("view/");
        Template template = templateLoader.getTemplate("layout");
        TemplateDataDictionary dic = TemplateDictionary.create();
        makeExt(dic, request);
        dic.setVariable("PAYTITLE", Configuration.SYSTEM_REQUESTFORM_TITLE);
        dic.setVariable("PAYURL", Configuration.SYSTEM_URL);
        dic.setVariable("STATIC_URL", Configuration.STATIC_URL);
        dic.setVariable("CREDITSURL", Configuration.SYSTEM_CREDITS_URL);
        dic.setVariable("_url", _url);
        dic.setVariable("_t", _t);
        if ("3".equals(version)) {
            dic.addSection("DOMAINV3");
        } else {
            dic.addSection("DOMAINV2");
        }
        T_AppInfo appInfo = null;
        if (request.getParameter("appID") != null && !request.getParameter("appID").equals("")) {
            appInfo = getAppInfo(request.getParameter("appID"));
            if (appInfo != null) {
                dic.setVariable("APPICON", appInfo.getIconPath());
                dic.setVariable("APPID", appInfo.getAppID());
                dic.setVariable("APPNAME", appInfo.getAppName());
                dic.showSection("top");
            }
        }
        dic.addSection("BILLING_ERROR_FORM");
        int code = -2;
        if (request.getAttribute("code") != null) {
            try {
                code = Integer.parseInt(request.getAttribute("code").toString());
            } catch (Exception e) {
                code = -2;
            }
        }
        switch (code) {
            case -2:
                dic.addSection("BILL_RESULT__2");
                break;
            case -4:
                dic.addSection("BILL_RESULT__4");
                break;
            case -5:
                dic.addSection("BILL_RESULT__5");
                break;
            case -6:
                dic.addSection("BILL_RESULT__6");
                break;
            default:
                dic.addSection("BILL_RESULT_OTHER");
                break;
        }
        dic.showSection("error");

        TemplateDataDictionary footer = dic.addSection("footer");
        if ("3".equals(version)) {
            TemplateDataDictionary me3Dic = footer.addSection("ME3JS");
            if (appInfo != null) {
                me3Dic.setVariable("APPURL", Utils.removeHTTP(appInfo.appURL));
            }
        } else if("4".equals(version)) {
            TemplateDataDictionary me4Dic = footer.addSection("ME4JS");
            if (appInfo != null) {
                me4Dic.setVariable("APPURL", Utils.removeHTTP(appInfo.appURL));
            }
        } else {
            footer.addSection("ME2JS");
        }
        
        String html = template.renderToString(dic);
        return html;
    }
}
