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
public class ErrorWapController extends CreditsCore {

    private static Logger logger_ = Logger.getLogger(ErrorWapController.class);

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
        TemplateLoader templateLoader = TemplateResourceLoader.create("view/");
        Template template = templateLoader.getTemplate("wap_layout");
        TemplateDataDictionary dic = TemplateDictionary.create();
        dic.setVariable("PAYTITLE", Configuration.SYSTEM_REQUESTFORM_TITLE);
        dic.setVariable("PAYURL", Configuration.SYSTEM_URL);
        dic.setVariable("STATIC_URL", Configuration.STATIC_URL);
        dic.setVariable("CREDITSURL", Configuration.SYSTEM_CREDITS_URL);
        T_AppInfo appInfo = null;
        if (request.getParameter("appID") != null && !request.getParameter("appID").equals("")) {
            appInfo = getAppInfo(request.getParameter("appID"));
            if (appInfo != null) {
                dic.setVariable("APPICON", appInfo.getIconPath());
                dic.setVariable("APPID", appInfo.getAppID());
                dic.setVariable("APPNAME", appInfo.getAppName());
                 dic.setVariable("APPFULLURL", appInfo.getAppURL());
                dic.showSection("wap_top");
                dic.showSection("wap_header");
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
            default:
                dic.addSection("BILL_RESULT_OTHER");
                break;
        }
        dic.showSection("wap_error");

        TemplateDataDictionary footer = dic.addSection("wap_footer");
        footer.setVariable("APPURL", Utils.removeHTTP(appInfo.appURL));
        String html = template.renderToString(dic);
        return html;
    }
}
