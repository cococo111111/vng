/**
 *Class: RequestFormController *
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.service;

import com.vng.zing.jni.zcommon.std__vectorT_std__string_t;
import hapax.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.wte.common.Config;
import vng.zingme.payment.bo.thrift.PaymentGatewayHandler;
import vng.zingme.payment.bo.thrift.TokenHandler;
import vng.zingme.payment.frontend.credits.config.Configuration;
import vng.zingme.payment.frontend.credits.utils.LogUtil;
import vng.zingme.payment.frontend.credits.utils.Monitor;
import vng.zingme.payment.frontend.credits.utils.ScriberPayment;
import vng.zingme.payment.frontend.credits.utils.Utils;
import vng.zingme.payment.thrift.T_AppInfo;
import vng.zingme.payment.thrift.T_Token;
import vng.zingme.payment.thrift.T_Transaction;

/**
 * get request form
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: May 19, 2011
 */
public class RequestFormMobileController extends CreditsCore {

    private final String ITEM_SEPARATE = "\\|";
    private final String PARAM_STATS = "stats";
    private final String BALANCE_PREFIX_PAY = "pay";
    private static Logger logger_ = Logger.getLogger(RequestFormMobileController.class);
    private final Monitor readStats = new Monitor();
    private BillController _billController;

    public RequestFormMobileController() {
        _memClient = getMemClient();
        _userAppMemClient = getUserAppMemClient();
        _billController = new BillController();
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

        if (!this.validateParams(request)) {
            this.redirectMobileError(request, response);
            return;
        }

        ScriberPayment.sendLogStep1(userID, request.getParameter("appID"), getClientIP(request), Configuration.SYSTEM_URL, Config.getParam("system", "host"));
        T_AppInfo appInfo = getAppInfo(request.getParameter("appID"));
        if (appInfo == null) {
            this.redirectMobileError(request, response);
            return;
        }
        T_Transaction transaction = null;
        try {
            transaction = this.parseTransaction(appInfo, request, response);
        } catch (Exception e) {
            this.redirectMobileError(request, response);
            return;
        }
        if (transaction == null || transaction.getUserName() == null) {
            this.redirectMobileError(request, response);
            return;
        }
        if (!transaction.getUserName().equals(userID + "")) {
            request.setAttribute("code", -4);
            this.redirectMobileError(request, response);
            return;
        }
        ScriberPayment.sendLogStep2(userID, request.getParameter("appID"), transaction.getRefID(), getClientIP(request), Configuration.SYSTEM_URL, Config.getParam("system", "host"));


        boolean isUnFinish = this.generateToken(userID, transaction, request);
        if (!isUnFinish) {
            request.setAttribute("code", -5);//giao dich da duoc thuc hien roi
            this.redirectMobileError(request, response);
            return;
        }
        PaymentGatewayHandler.getMainInstance(Configuration.PAYMENT_GATEWAY_HOST, Configuration.PAYMENT_GATEWAY_PORT).warmupCache(userID);
        Object userAppCache = this.getUserAppMemCache(userID, appInfo.getAppID());
        if (userAppCache != null && ((Integer) (userAppCache)).intValue() == 1) {
            request.setAttribute("refID", transaction.getRefID());
            _billController.processRequest(request, response);
        } else {
            echoAndStats(startTime, this.renderByTemplate(transaction, appInfo, userID, request), response);
        }
    }

    private void echoAndStats(long startTime, String html, HttpServletResponse response) {
        this.echo(html, response);
        this.readStats.addMicro((System.nanoTime() - startTime) / 1000);
    }

    private T_Transaction parseTransaction(T_AppInfo appInfo, HttpServletRequest request, HttpServletResponse response) throws TException {
        String agentID = request.getParameter("appID");
        String encodedData = request.getParameter("data");
        std__vectorT_std__string_t tx = zingUnSignature(appInfo, agentID, encodedData);
        if (tx == null || tx.is_empty() || tx.size() != 8) {
            this.redirectMobileError(request, response);
            return null;
        }
        T_Transaction tranx = new T_Transaction();
        tranx.setUserName(tx.get(0));
        tranx.setRefID(tx.get(1));
        tranx.setItemIDs(tx.get(2));
        tranx.setItemNames(tx.get(3));
        tranx.setItemQuantities(tx.get(4));
        tranx.setItemPrices(tx.get(5));
        tranx.setAmount(Double.parseDouble(tx.get(6)));
        tranx.setTxTime(Integer.parseInt(tx.get(7)));
        return tranx;
    }

    boolean validateParams(HttpServletRequest request) {
        if (request.getParameter("appID") == null || request.getParameter("appID").equals("")
                || request.getParameter("data") == null || request.getParameter("data").equals("")) {
            return false;
        }
        return true;
    }

    private boolean generateToken(int userID, T_Transaction transaction, HttpServletRequest request) throws TException {
        //get from memcached first
        String key = this.getTokenCachedKey(transaction, request);
        String[] dataToken = (String[]) getTokenMemCache(key);
        if (dataToken == null || dataToken.length != 2) {
            TokenHandler tokenHandler = TokenHandler.getMainInstance(Configuration.TOKENBACKEND_HOST, Configuration.TOKENBACKEND_PORT);
            T_Token token = tokenHandler.getToken(userID);
            request.setAttribute("pToken", token.getPToken());
            //billstat:0-chua xac nhan,1-hoan thanh xac nhan
            String[] dataTokenInCached = new String[]{"0", token.getPToken()};
            setTokenMemCache(key, dataTokenInCached);
            return true;
        } else {
            if (dataToken[0].equals("1")) {
                return false;
            }
            request.setAttribute("pToken", dataToken[1]);
            return true;
        }
    }

    private String renderByTemplate(T_Transaction transaction, T_AppInfo appInfo, int userID, HttpServletRequest request) throws TemplateException {
        TemplateLoader templateLoader = TemplateResourceLoader.create("view/");
        Template template = templateLoader.getTemplate("m_layout");
        TemplateDataDictionary dic = TemplateDictionary.create();
        dic.setVariable("PAYTITLE", Configuration.SYSTEM_REQUESTFORM_TITLE);
        dic.setVariable("PAYURL", Configuration.SYSTEM_URL);
        dic.setVariable("STATIC_URL", Configuration.STATIC_URL);
        dic.setVariable("APPICON", appInfo.getIconPath());
        dic.setVariable("APPID", appInfo.getAppID());
        dic.setVariable("APPNAME", appInfo.getAppName());
        dic.setVariable("REFID", transaction.getRefID());
        dic.setVariable("USERID", userID + "");
        dic.setVariable("USERNAME", request.getAttribute("zme.viewerName").toString());
        dic.setVariable("DATA", request.getParameter("data"));
        dic.setVariable("PTOKEN", request.getAttribute("pToken").toString());

        String[] arrItemID = transaction.getItemIDs().split(ITEM_SEPARATE);
        String[] arrItemNames = transaction.getItemNames().split(ITEM_SEPARATE);
        String[] arrQty = transaction.getItemQuantities().split(ITEM_SEPARATE);
        String[] arrPrice = transaction.getItemPrices().split(ITEM_SEPARATE);
        String[] arrAmount = (transaction.getAmount() + "").split(ITEM_SEPARATE);
        double total = 0.0;
        for (int i = 0; i < arrItemID.length; i++) {
            double childamount = Double.parseDouble(arrAmount[i]);
            total += childamount;
            TemplateDataDictionary itemDic = dic.addSection("ITEM");
            itemDic.setVariable("ITEMNAME", arrItemNames[i]);
            itemDic.setVariable("QUANTITY", arrQty[i]);
            itemDic.setVariable("PRICE", arrPrice[i]);
            itemDic.setVariable("AMOUNT", Utils.removeDouble(childamount));
        }
        dic.setVariable("PAYTOTAL", Utils.removeDouble(total));
        dic.showSection("m_top");
         dic.showSection("m_header");
        dic.addSection("BILLING_REQUEST_FORM");
        dic.showSection("m_billing");

        TemplateDataDictionary footer = dic.addSection("m_footer");
        footer.setVariable("APPURL", Utils.removeHTTP(appInfo.appURL));
        return template.renderToString(dic);
    }
}