/**
 *Class: IndexController *
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.service;

import com.vng.zing.stdprofile2.thrift.TValue;
import hapax.*;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.zingme.payment.bo.thrift.BalanceCacheHandler;
import vng.zingme.payment.bo.thrift.LatestTranxCacheHandler;
import vng.zingme.payment.common.AppInfoCache;
import vng.zingme.payment.frontend.credits.config.Configuration;
import vng.zingme.payment.frontend.credits.utils.LogUtil;
import vng.zingme.payment.frontend.credits.utils.Monitor;
import vng.zingme.payment.frontend.credits.utils.Utils;
import vng.zingme.payment.thrift.T_AccResponse;
import vng.zingme.payment.thrift.T_AppInfo;
import vng.zingme.payment.thrift.T_Transaction;

/**
 * get user's Ví Zing Me
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: May 19, 2011
 */
public class IndexWapController extends CreditsCore {

    private final String PARAM_STATS = "stats";
    private static Logger logger_ = Logger.getLogger(IndexWapController.class);
    private final Monitor readStats = new Monitor();

    public IndexWapController() {
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
        if (this.validParam(request)) {
            if (request.getAttribute("_f") != null) {
                if (request.getAttribute("_f").equals("tranx")) {
                    List<T_Transaction> tranxs = getLatestTranx(userID);
                    echoAndStats(startTime, this.renderTranxByTemplate(tranxs, userID, request), response);
                    return;
                }
                if (request.getAttribute("_f").equals("tranxdetail") && request.getParameter("_tranxid") != null) {
                    Object[] objs = getTranx(userID, request.getParameter("_tranxid"));
                    T_Transaction tranx = (T_Transaction) objs[0];
                    Long backID = (Long) objs[1];
                    Long nextID = (Long) objs[2];
                    echoAndStats(startTime, this.renderTranxDetailByTemplate(tranx, userID, request, backID, nextID), response);
                    return;
                }
            }
        }
        Double balance = getBalance(userID);
        echoAndStats(startTime, this.renderMobileHome(balance, userID, request), response);

    }

    private void echoAndStats(long startTime, String html, HttpServletResponse response) {
        this.echo(html, response);
        this.readStats.addMicro((System.nanoTime() - startTime) / 1000);
    }

    private Double getBalance(int userID) {
        int hope_count = 3;
        boolean flag = false;
        Double obj = new Double(0);
        while (hope_count > 0 && !flag) {
            try {
                BalanceCacheHandler balanceCacheHandler = BalanceCacheHandler.getMainInstance(Configuration.BALANCE_HOST, Configuration.BALANCE_PORT);
                T_AccResponse balance = balanceCacheHandler.getBalance(userID);

                if (balance != null && balance.getCode() >= 0) {
                    obj = balance.getCurrentBalance();
                    flag = true;
                }
            } catch (Exception ex) {
                logger_.warn(ex);
            }
            --hope_count;
        }
        return obj;
    }

    private List<T_Transaction> getLatestTranx(int userID) {
        int hope_count = 3;
        boolean flag = false;
        List<T_Transaction> obj = null;
        while (hope_count > 0 && !flag) {
            try {
                LatestTranxCacheHandler ltch = LatestTranxCacheHandler.getMainInstance(Configuration.LASTEDCACHE_HOST, Configuration.LASTEDCACHE_PORT);
                obj = ltch.get(userID);
                if (obj != null && !obj.isEmpty()) {
                    flag = true;
                }
            } catch (Exception ex) {
                logger_.warn(ex);
            }
            --hope_count;
        }
        return obj;
    }

    public Object[] getTranx(int userID, String tranxID) {
        Object[] result = new Object[3];
        result[0] = null;
        result[1] = null;
        result[2] = null;
        try {
            long tranxIDl = Long.parseLong(tranxID);
            List<T_Transaction> tranxs = getLatestTranx(userID);
            int size = tranxs.size();
            for (int i = 0; i < size; i++) {
                T_Transaction tranx = tranxs.get(i);
                if (tranx.getTxID() == tranxIDl) {
                    result[0] = tranx;
                    if (i - 1 >= 0) {
                        result[2] = tranxs.get(i - 1).getTxID();
                    }
                    if (i + 1 < size) {
                        result[1] = tranxs.get(i + 1).getTxID();
                    }

                    break;
                }
            }
        } catch (Exception e) {
            logger_.error(LogUtil.stackTrace(e));
        }
        return result;
    }

    private String getAppName(String appID) {
        AppInfoCache cache = AppInfoCache.getInstance();
        T_AppInfo appInfo = cache.get(appID);
        if (appInfo == null || appInfo.getAppName() == null) {
            return "";
        }
        return appInfo.getAppName();
    }

    private T_AppInfo getApp(String appID) {
        AppInfoCache cache = AppInfoCache.getInstance();
        T_AppInfo appInfo = cache.get(appID);
        if (appInfo == null || appInfo.getAppName() == null) {
            return null;
        }
        return appInfo;
    }

    private String renderMobileHome(Double balance, int userID, HttpServletRequest request) throws TemplateException {
        String balancestr = Utils.formatMoney(balance);
        TemplateLoader templateLoader = TemplateResourceLoader.create("view/");
        Template template = null;
        template = templateLoader.getTemplate("wap_index");
        TemplateDataDictionary dic = TemplateDictionary.create();
        dic.setVariable("CREDITS_TITLE", Configuration.SYSTEM_TITLE);
        dic.setVariable("CREDITS_URL", Configuration.MOBILE_WAP_CREDITS_URL);
        dic.setVariable("STATIC_URL", Configuration.STATIC_URL);
        dic.setVariable("USERID", userID + "");
        dic.setVariable("BALANCE", balancestr);
        dic.setVariable("TX_URL", getShowTranxParam(request));
        dic.setVariable("REFERER", "javascript:window.history.go(-1)");
        dic.setVariable("USERNAME", request.getAttribute("zme.viewerName").toString());
        dic.setVariable("DESKTOP_CREDITS_URL", Configuration.SYSTEM_URL);
        //get Avata
        TValue tValue = null;
        try {
            tValue = Utils.getProfile(userID);
        } catch (Exception ex) {
            logger_.error(LogUtil.stackTrace(ex));
        }
        if (tValue != null) {
            String avata = Utils.buildAvatarPath(request.getAttribute("zme.viewerName").toString(), tValue.avatarVersion, "75");
            dic.setVariable("AVATA", avata);
            dic.setVariable("DISPLAYNAME", tValue.getDisplayName());
        }

        dic.addSection("wap_header");
        dic.addSection("wap_footer");
        AppInfoCache cache = AppInfoCache.getInstance();
        List<T_AppInfo> appInfos = cache.getAllAppInfo();
        List<T_AppInfo> filterAppInfos = new ArrayList<T_AppInfo>();
        Set<String> appURLs = new HashSet<String>();
        for (T_AppInfo app : appInfos) {
            if (app.isEnabled != 1 || app.appID.equals("zing") || app.appID.equals("admin") || appURLs.contains(app.appURL)) {
                continue;
            }
            appURLs.add(app.appURL);
            filterAppInfos.add(app);
        }
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        int count = 5;
        int size = filterAppInfos.size();
        if (page * count - count >= size) {
            page = 1;
        }
        dic.setVariable("PAGE", page + "");

        if (page != 1) {
            dic.setVariable("PREPAGE", (page - 1) + "");
            dic.addSection("BEFORE");
        }
        if (page * count < size) {
            dic.setVariable("NEXTPAGE", (page + 1) + "");
            dic.addSection("AFTER");
        }

        for (int i = page * count - count; i < size && i < page * count; i++) {
            TemplateDataDictionary appDic = dic.addSection("APP");
            T_AppInfo app = filterAppInfos.get(i);
            appDic.setVariable("IMAGE", app.getIconPath());
            appDic.setVariable("NAME", app.getAppName());
            appDic.setVariable("INDEX", i + 1 + "");
        }
//        Collections.shuffle(filterAppInfos);
//        if (filterAppInfos.size() > 5) {
//            filterAppInfos = filterAppInfos.subList(0, 5);
//        }
//        int i = 1;
//        for (T_AppInfo app : filterAppInfos) {
//            TemplateDataDictionary appDic = dic.addSection("APP");
//            appDic.setVariable("IMAGE", app.getIconPath());
//            appDic.setVariable("NAME", app.getAppName());
//            appDic.setVariable("INDEX", i + "");
//            i++;
//        }
        dic.setVariable("BACKURL", "javascript:window.history.go(-1)");
        return template.renderToString(dic);
    }

    private String renderTranxByTemplate(List<T_Transaction> tranxs, int userID, HttpServletRequest request) throws TemplateException {
        TemplateLoader templateLoader = TemplateResourceLoader.create("view/");
        Template template = null;
        template = templateLoader.getTemplate("wap_tranx");
        TemplateDataDictionary dic = TemplateDictionary.create();
        dic.setVariable("CREDITS_TITLE", Configuration.SYSTEM_TITLE);
        dic.setVariable("CREDITS_URL", Configuration.MOBILE_WAP_CREDITS_URL);
        dic.setVariable("STATIC_URL", Configuration.STATIC_URL);
        dic.setVariable("BACKURL", getShowTranxParam(request));
        dic.setVariable("MEURL", Configuration.SYSTEM_ME_URL);
        dic.setVariable("CREDITS_APPNAME", Configuration.SYSTEM_APPNAME);
        dic.setVariable("USERNAME", request.getAttribute("zme.viewerName").toString());
        dic.setVariable("USERID", userID + "");
        dic.addSection("wap_header");
        dic.addSection("wap_footer");
        dic.setVariable("TX_URL", getShowTranxParam(request));
        dic.setVariable("REFERER", Configuration.SYSTEM_URL + "/wap");
        dic.setVariable("DESKTOP_CREDITS_URL", Configuration.SYSTEM_URL);
        if (tranxs == null || tranxs.isEmpty()) {
            dic.addSection("TRANXS_NOTFOUND");

        } else {
            dic.addSection("TRANXS_FOUND_PAGE");
            int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
            int count = 5;
            int size = tranxs.size();
            if (page * count - count >= size) {
                page = 1;
            }
            dic.setVariable("PAGE", page + "");

            if (page != 1) {
                dic.setVariable("PREPAGE", (page - 1) + "");
                dic.addSection("BEFORE");
            }
            if (page * count < size) {
                dic.setVariable("NEXTPAGE", (page + 1) + "");
                dic.addSection("AFTER");
            }

            for (int i = page * count - count; i < size && i < page * count; i++) {
                T_Transaction transaction = tranxs.get(i);
                TemplateDataDictionary tranxListsDic = dic.addSection("TRANXS_FOUND");
                tranxListsDic.setVariable("TX_TIME", Utils.formatDate(transaction.getTxTime()) + "");
                tranxListsDic.setVariable("INDEX", (i + 1) + "");
                String amountstr = Utils.formatMoney(transaction.amount);
                switch (transaction.responseCode) {
                    case 1:
                    case 3:
                        tranxListsDic.addSection("TX_DXL");
                        break;
                    case 101:
                    case 102:
                        tranxListsDic.addSection("TX_TC");
                        break;
                    case -103:
                    case -104:
                        tranxListsDic.addSection("TX_TB");
                        break;
                    case 2:
                        if (transaction.txType == 2 || transaction.txType == 101 || transaction.txType == 102 || transaction.txType == 103 || transaction.txType == 202) {
                            tranxListsDic.addSection("TX_TC");
                        }
                        if (transaction.txType == 201) {
                            tranxListsDic.addSection("TX_DXL");
                        }
                        break;
                }
                tranxListsDic.setVariable("TX_ID", transaction.txID + "");
                tranxListsDic.setVariable("TX_DETAIL_URL", getShowTranxDetailParam(transaction.txID, request));
                tranxListsDic.setVariable("TX_AMOUNT", amountstr);
                tranxListsDic.setVariable("TX_APPNAME", getAppName(transaction.agentID));
            }
        }
        return template.renderToString(dic);
    }

    private String renderTranxDetailByTemplate(T_Transaction transaction, int userID, HttpServletRequest request, Long backTranxID, Long nextTranxID) throws TemplateException {
        TemplateLoader templateLoader = TemplateResourceLoader.create("view/");
        Template template = null;
        template = templateLoader.getTemplate("wap_tranxdetail");
        TemplateDataDictionary dic = TemplateDictionary.create();
        dic.setVariable("CREDITS_TITLE", Configuration.SYSTEM_TITLE);
        dic.setVariable("CREDITS_URL", Configuration.MOBILE_WAP_CREDITS_URL);
        dic.setVariable("STATIC_URL", Configuration.STATIC_URL);
        dic.setVariable("DESKTOP_CREDITS_URL", Configuration.SYSTEM_URL);
        dic.setVariable("MEURL", Configuration.SYSTEM_ME_URL);
        dic.setVariable("CREDITS_APPNAME", Configuration.SYSTEM_APPNAME);
        dic.setVariable("USERNAME", request.getAttribute("zme.viewerName").toString());
        dic.setVariable("USERID", userID + "");
        dic.addSection("wap_header");
        dic.addSection("wap_footer");
        dic.setVariable("BACKURL", getShowTranxParam(request));
        dic.setVariable("REFERER", getShowTranxParam(request));

        if (transaction == null) {
            dic.addSection("TRANXS_NOTFOUND");

        } else {
            TemplateDataDictionary tranxListsDic = dic.addSection("TRANXS_FOUND");
            tranxListsDic.setVariable("TX_TIME", Utils.formatDate(transaction.getTxTime()) + "");
            String amountstr = Utils.formatMoney(transaction.amount);
            boolean enableDetailOther = false;
            TemplateDataDictionary tranxsDetailDic = null;

            //check has next
            if (backTranxID != null && backTranxID != 0) {
                dic.setVariable("TX_DETAIL_BACK_URL", getShowTranxDetailParam(backTranxID, request));
                dic.addSection("BEFORE");
            }
            if (nextTranxID != null && nextTranxID != 0) {
                dic.setVariable("TX_DETAIL_NEXT_URL", getShowTranxDetailParam(nextTranxID, request));
                dic.addSection("AFTER");
            }
            switch (transaction.responseCode) {
                case 1:
                case 3:
                    tranxListsDic.addSection("TX_DXL");
                    break;
                case 101:
                case 102:
                    tranxListsDic.addSection("TX_TC");
                    break;
                case -103:
                case -104:
                    enableDetailOther = true;
                    tranxListsDic.setVariable("DETAIL_OTHER_DESC", transaction.description);
                    tranxListsDic.addSection("TX_TB");
                    break;
                case 2:
                    if (transaction.txType == 2 || transaction.txType == 101 || transaction.txType == 102 || transaction.txType == 103 || transaction.txType == 202) {
                        tranxListsDic.addSection("TX_TC");
                    }
                    if (transaction.txType == 201) {
                        tranxListsDic.addSection("TX_DXL");
                    }
                    break;
            }
            switch (transaction.txType) {
                case 101:
                    tranxListsDic.addSection("NAPTIEN");
                    if (!enableDetailOther) {
                        tranxsDetailDic = tranxListsDic.addSection("DETAIL_NAPTIEN");
                        tranxsDetailDic.setVariable("TX_AMOUNT", amountstr);
                    }
                    break;
                case 102:
                    tranxListsDic.addSection("CHUYENTIEN");
                    if (!enableDetailOther) {
                        tranxsDetailDic = tranxListsDic.addSection("DETAIL_CHUYENTIEN");
                        tranxsDetailDic.setVariable("TX_AMOUNT", amountstr);
                    }
                    break;
                case 103:
                    tranxListsDic.addSection("TANGTIEN");
                    if (!enableDetailOther) {
                        tranxsDetailDic = tranxListsDic.addSection("DETAIL_TANGTIEN");
                        tranxsDetailDic.setVariable("TX_AMOUNT", amountstr);
                    }
                    break;
                case 201:
                    tranxListsDic.addSection("THANHTOAN");
                    if (!enableDetailOther) {
                        tranxsDetailDic = tranxListsDic.addSection("DETAIL_THANHTOAN");
                        tranxsDetailDic.setVariable("TX_ITEMQUANTITIES", transaction.itemQuantities);
                        tranxsDetailDic.setVariable("TX_ITEMNAMES", transaction.itemNames);
                        tranxsDetailDic.setVariable("TX_AMOUNT", amountstr);
                    }
                    break;
                case 202:
                    tranxListsDic.addSection("HOANTIEN");
                    if (!enableDetailOther) {
                        tranxsDetailDic = tranxListsDic.addSection("DETAIL_HOANTIEN");
                        tranxsDetailDic.setVariable("TX_AMOUNT", amountstr);
                    }
                    break;
                case 2:
                    if (transaction.amount > 0) {
                        tranxListsDic.addSection("THEMTIEN");
                    } else {
                        tranxListsDic.addSection("GIAMTIEN");
                    }
                    tranxListsDic.setVariable("DETAIL_OTHER_DESC", transaction.description);
                    break;
            }
            tranxListsDic.setVariable("TX_ID", transaction.txID + "");
            tranxListsDic.setVariable("TX_AMOUNT", amountstr);
            T_AppInfo app = getApp(transaction.agentID);
            if (app != null) {
                tranxListsDic.setVariable("TX_APPNAME", app.getAppName());
                tranxListsDic.setVariable("IMAGE", app.getIconPath());
            }
        }
        return template.renderToString(dic);
    }

    private String getShowTranxParam(HttpServletRequest request) {
        long time = System.currentTimeMillis();
        String key = "tranx" + Configuration.SYSTEM_SECRET_KEY + request.getAttribute("zme.viewerId") + request.getAttribute("zme.viewerName") + time;
        String target = getMD5(key);
        return "?type=2&hash=" + target + "&time=" + time;
    }

    private String getShowTranxDetailParam(Long tranxID, HttpServletRequest request) {
        long time = System.currentTimeMillis();
        String key = "tranxdetail" + tranxID + Configuration.SYSTEM_SECRET_KEY + request.getAttribute("zme.viewerId") + request.getAttribute("zme.viewerName") + time;
        String target = getMD5(key);
        return "?type=3&hash=" + target + "&time=" + time + "&_tranxid=" + tranxID;
    }

    private boolean validParam(HttpServletRequest request) {
        String type = request.getParameter("type");
        String time = request.getParameter("time");
        String hash = request.getParameter("hash");

        if (type == null || time == null || hash == null) {
            return false;
        }
        Integer typei = Integer.parseInt(type);

        switch (typei) {
            case 1: {
                //index view
                return true;
            }
            case 2: {
                //view first list tranx

                String key = "tranx" + Configuration.SYSTEM_SECRET_KEY + request.getAttribute("zme.viewerId") + request.getAttribute("zme.viewerName") + time;
                String target = getMD5(key);
                if (hash.equals(target) && matchTimeOut(Long.parseLong(time))) {
                    request.setAttribute("_f", "tranx");
                    return true;
                }
                return false;
            }
            case 3: {
                //view list tranx with page
                String key = "tranxdetail" + request.getParameter("_tranxid") + Configuration.SYSTEM_SECRET_KEY + request.getAttribute("zme.viewerId") + request.getAttribute("zme.viewerName") + time;
                String target = getMD5(key);
                if (hash.equals(target) && matchTimeOut(Long.parseLong(time))) {
                    request.setAttribute("_f", "tranxdetail");
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean matchTimeOut(Long req_timeout) {
        long currentTime = System.currentTimeMillis();
        long period = Configuration.SYSTEM_TIMEOUT * 60000;
        long fromTime = currentTime - period;
        if (req_timeout >= fromTime && req_timeout <= currentTime) {
            return true;
        }
        return false;
    }
}
