/**
 *Class: IndexController *
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.service;

import hapax.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.spy.memcached.MemcachedClient;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.zingme.payment.bo.thrift.BalanceCacheHandler;
import vng.zingme.payment.bo.thrift.LatestTranxCacheHandler;
import vng.zingme.payment.common.AppInfoCache;
import vng.zingme.payment.frontend.credits.config.Configuration;
import vng.zingme.payment.frontend.credits.utils.HttpHelper;
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
public class IndexController extends CreditsCore {

    private final String PARAM_STATS			= "stats";
    private static Logger logger_				= Logger.getLogger(IndexController.class);
    private final Monitor readStats				= new Monitor();
    protected MemcachedClient _memClient		= null;
    protected final Object _tokenmemClientLock	= new Object();
	
    private final int ZING_CARD	= 0;
    private final int ATM_CARD	= 1;

    public IndexController() {
        _memClient = getMemClient();
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
        Double balance = getBalance(userID);
       
		//check action
        if (request.getParameter("creditscmd") != null) {
            if ("napzingxu".equals(request.getParameter("creditscmd"))) {
                echoAndStats(startTime, this.renderNapXuForm(balance, userID, ZING_CARD, request, response), response);
                return;
            } else if ("napatm".equals(request.getParameter("creditscmd"))) {
                echoAndStats(startTime, this.renderNapXuForm(balance, userID, ATM_CARD, request, response), response);
                return;
            } else if ("sms".equals(request.getParameter("creditscmd"))) {
                echoAndStats(startTime, this.renderSMSInfo(balance, userID, request), response);
                return;
            }
        } else if (HttpHelper.getCookie(request, "atm_errorcode") != null) {
            echoAndStats(startTime, this.renderNapXuForm(balance, userID, ATM_CARD, request, response), response);
            return;
        }

        List<T_Transaction> tranxs = getLatestTranx(userID);
        echoAndStats(startTime, this.renderByTemplate(balance, tranxs, userID, request), response);
    }

    private String renderNapXuForm(Double balance, int userID, int inputType, HttpServletRequest request, HttpServletResponse response) throws TemplateException {
        String balancestr = Utils.formatMoney(balance);
		
        TemplateLoader templateLoader	= TemplateResourceLoader.create("view/");
        Template template				= templateLoader.getTemplate("v3_layout_napzingxu");
        TemplateDataDictionary dic		= TemplateDictionary.create();
		String section					= "";
		
		//set variables 
        dic.setVariable("BALANCE", balancestr);
        dic.setVariable("CREDITS_TITLE", Configuration.SYSTEM_TITLE);
        dic.setVariable("CREDITS_URL", Configuration.SYSTEM_URL);
        dic.setVariable("STATIC_URL", Configuration.STATIC_URL);
        dic.setVariable("MEURL", Configuration.SYSTEM_ME_URL);
        dic.setVariable("CREDITS_APPNAME", Configuration.SYSTEM_APPNAME);
        dic.setVariable("USERID", userID + "");
        dic.setVariable("USERNAME", request.getAttribute("zme.viewerName") + "");
        dic.setVariable("NAPTHEURL", Configuration.NAPTHE_URL + request.getAttribute("zme.viewerName"));
        dic.setVariable("CSSVERSION", Configuration.CSS_VERSION);
		
		switch (inputType) {
			case ZING_CARD: 
			{
				dic.setVariable("QUICKPAY_URL", Configuration.NAPTHE_URL + request.getAttribute("zme.viewerName"));
				dic.setVariable("QUICKPAY_HEIGHT", "250px");
				section = "ZINGCARD_ACTIVE";
				break;
			}
			case ATM_CARD:
			{
				String atm_errorcode = HttpHelper.getCookie(request, "atm_errorcode");
				if (atm_errorcode != null && (!atm_errorcode.equals("3")))
				  dic.setVariable("QUICKPAY_URL", Configuration.NAPATM_THANKYOU_URL + "." + atm_errorcode + ".html");
				else
				  dic.setVariable("QUICKPAY_URL", Configuration.NAPATM_URL);
				
				HttpHelper.removeCookie(request, response, "atm_errorcode");
				dic.setVariable("QUICKPAY_HEIGHT", "510px");
				section = "ATM_ACTIVE";
				break;
			}
		}
		
		//load section
		dic.showSection("v3_layout_tabs");
		dic.showSection(section);
        return template.renderToString(dic);
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

    private String getAppName(String appID) {
        AppInfoCache cache = AppInfoCache.getInstance();
        T_AppInfo appInfo = cache.get(appID);
        if (appInfo == null || appInfo.getAppName() == null) {
            return "";
        }
        return appInfo.getAppName();
    }

    private String renderByTemplate(Double balance, List<T_Transaction> tranxs, int userID, HttpServletRequest request) throws TemplateException {
        String balancestr = Utils.formatMoney(balance);
        String meversion = request.getParameter("_v");
        if (meversion == null) {
            meversion = "2";
        }
        TemplateLoader templateLoader = TemplateResourceLoader.create("view/");
        Template template = null;
        if (meversion.equals("3")) {
            template = templateLoader.getTemplate("v3_layout");
        } else {
            template = templateLoader.getTemplate("layout");
        }
        TemplateDataDictionary dic = TemplateDictionary.create();
        dic.setVariable("CREDITS_TITLE", Configuration.SYSTEM_TITLE);
        dic.setVariable("CREDITS_URL", Configuration.SYSTEM_URL);
        dic.setVariable("STATIC_URL", Configuration.STATIC_URL);
        dic.setVariable("MEURL", Configuration.SYSTEM_ME_URL);
        dic.setVariable("CREDITS_APPNAME", Configuration.SYSTEM_APPNAME);
        dic.setVariable("USERID", userID + "");
        dic.setVariable("USERNAME", request.getAttribute("zme.viewerName") + "");
        dic.setVariable("BALANCE", balancestr);
        dic.setVariable("MEVERSION", meversion);
        dic.setVariable("CSSVERSION", Configuration.CSS_VERSION);
        
        TemplateDataDictionary tranxsDic = dic.addSection("TRANXS");
        if (meversion.equals("3")) {
            dic.showSection("v3_tranxs");
        } else {
            dic.showSection("tranxs");
        }
        
        if (Configuration.NAPTHE_ENABLE || Configuration.SYSTEM_MAINTAIN_ALOWS_USER.contains(userID + "")) {
//          dic.showSection("NAPTHENHANH");
			dic.showSection("v3_layout_tabs");
			dic.showSection("HISTORY_ACTIVE");
        }

        if (tranxs == null || tranxs.isEmpty()) {
            tranxsDic.addSection("TRANXS_NOTFOUND");
        } else {
            int size = tranxs.size();
            for (int i = 0; i < size; i++) {
                T_Transaction transaction = tranxs.get(i);
                TemplateDataDictionary tranxListsDic = tranxsDic.addSection("TRANXS_FOUND");
                tranxListsDic.setVariable("TX_TIME", Utils.formatDate(transaction.getTxTime()) + "");
                TemplateDataDictionary tranxsDetailDic = null;
                String amountstr = Utils.formatMoney(transaction.amount);

                boolean enableDetailOther = false;
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
                        tranxListsDic.setVariable("DETAIL_OTHER_DESC", transaction.description);
                        enableDetailOther = true;
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
                tranxListsDic.setVariable("TX_APPNAME", getAppName(transaction.agentID));





            }
        }
        //show appjs
        tranxsDic.setVariable("APPS_JS", toAppJS(meversion));
        return template.renderToString(dic);
    }
    
    private String renderSMSInfo(Double balance, int userID, HttpServletRequest request) throws TemplateException {
        TemplateLoader templateLoader = TemplateResourceLoader.create("view/");
        Template template = templateLoader.getTemplate("v3_layout");
        String balancestr = Utils.formatMoney(balance);
        TemplateDataDictionary dic = TemplateDictionary.create();
        dic.setVariable("CREDITS_TITLE", Configuration.SYSTEM_TITLE);
        dic.setVariable("CREDITS_URL", Configuration.SYSTEM_URL);
        dic.setVariable("STATIC_URL", Configuration.STATIC_URL);
        dic.setVariable("MEURL", Configuration.SYSTEM_ME_URL);
        dic.setVariable("CREDITS_APPNAME", Configuration.SYSTEM_APPNAME);
        dic.setVariable("USERID", userID + "");
        dic.setVariable("USERNAME", request.getAttribute("zme.viewerName") + "");
        dic.setVariable("BALANCE", balancestr);
        dic.setVariable("CSSVERSION", Configuration.CSS_VERSION);
        
        dic.addSection("SMS");
        dic.showSection("sms_info");
        
        dic.showSection("v3_layout_tabs");
        dic.showSection("SMS_ACTIVE");
        return template.renderToString(dic);
    }

    private String toAppJS(String meversion) {

        StringBuilder result = new StringBuilder("");
        AppInfoCache cache = AppInfoCache.getInstance();
        List<T_AppInfo> appInfos = cache.getAllAppInfo();
        
        this.sortAppInfo(appInfos);
        if (appInfos == null || appInfos.isEmpty()) {
            return result.toString();
        }
        int maxDisplayed = 8;
        if (meversion.equals("3")) {
            maxDisplayed = 10;
        }

        int currentDisplayed = 0;
        int jsindex = -1;
        Set<String> appURLs = new HashSet<String>();
        for (T_AppInfo app : appInfos) {
            if (app.isEnabled != 1 || app.appID.equals("zing") || app.appID.equals("admin")) {
                continue;
            }
            
            //QuyTP modified: 22/05/2012
            String appUrl = Configuration.APP_URLS.getProperty(app.appID, app.appURL)
                            +"?src=vizm";
            if(appUrl.indexOf("?") > 0) {
               appUrl = appUrl + "&src=vizm";
            } else {
                appUrl = appUrl + "?src=vizm";
            }
            
            if (appURLs.contains(appUrl)) {
                continue;
            }
            
            appURLs.add(appUrl);
            //End modified
            
            currentDisplayed++;
            if (currentDisplayed % maxDisplayed == 1) {
                jsindex++;
                result.append("apps[");
                result.append(jsindex);
                result.append("]=new Array();");
                result.append("apps[");
                result.append(jsindex);
                result.append("].push(new Array('");
                result.append(appUrl);
                result.append("','");
                result.append(app.iconPath);
                result.append("','");
                result.append(app.appName);
                result.append("','");
                result.append(app.appDes);
                result.append("'));");
                continue;
            }
            result.append("apps[");
            result.append(jsindex);
            result.append("].push(new Array('");
            result.append(appUrl);
            result.append("','");
            result.append(app.iconPath);
            result.append("','");
            result.append(app.appName);
            result.append("','");
            result.append(app.appDes);
            result.append("'));");            
        }
        return result.toString();
    }

    private List<T_AppInfo> sortAppInfo(List<T_AppInfo> appInfos) {
        try {

            List<String> appkeys = new ArrayList<String>();
            for (T_AppInfo app : appInfos) {
                appkeys.add(app.getAppID());
            }


            final Map<String, Object> result = getTokenMemCache(appkeys);
            if (result == null) {
                return appInfos;
            }
            Collections.sort(appInfos, new Comparator<T_AppInfo>() {

                public int compare(T_AppInfo app1, T_AppInfo app2) {
                    String b1 = "0";
                    String b2 = "0";
                    if (!result.containsKey(app1.getAppID())) {
                        b1 = "0";
                    } else {
                        if(result.get(app1.getAppID()) instanceof String){
                            b1 = result.get(app1.getAppID()).toString();
                        }
                         if(result.get(app1.getAppID()) instanceof byte[]){
                            b1 = new String((byte[]) result.get(app1.getAppID()));
                        }
                        
                    }
                    if (!result.containsKey(app2.getAppID())) {
                        b2 = "0";
                    } else {

                         if(result.get(app2.getAppID()) instanceof String){
                            b2 = result.get(app2.getAppID()).toString();
                        }
                         if(result.get(app2.getAppID()) instanceof byte[]){
                            b2 = new String((byte[]) result.get(app2.getAppID()));
                        }
                    }
                    return new Integer(b2).compareTo(new Integer(b1));
                }
            });
        } catch (Exception ex) {
            logger_.error(LogUtil.stackTrace(ex));
        }
        return appInfos;
    }
    protected MemcachedClient getMemClient() {
        synchronized (_tokenmemClientLock) {
            if (_memClient == null) {
                _memClient = getMemCacheClient(Configuration.MEMCACHE_APP_SORT_HOST, Configuration.MEMCACHE_APP_SORT_PORT);
            }
        }
        return _memClient;
    }

    protected MemcachedClient renewMemClient() {
        synchronized (_tokenmemClientLock) {
            if (_memClient != null) {
                _memClient.shutdown();
            }
            _memClient = getMemCacheClient(Configuration.MEMCACHE_APP_SORT_HOST, Configuration.MEMCACHE_APP_SORT_PORT);
        }
        return _memClient;
    }

    protected MemcachedClient getMemCacheClient(String host, int port) {
        MemcachedClient client = null;
        try {
            client = new MemcachedClient(new InetSocketAddress(host, port));
        } catch (Exception ex) {
            logger_.warn(ex);
        }
        return client;
    }

    protected Map<String,Object> getTokenMemCache(List<String> key) {
        int hope_count = 3;
        boolean flag = false;
        Map<String,Object> obj = null;
        while (hope_count > 0 && !flag) {
            try {
                obj = _memClient.getBulk(key);
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
}
