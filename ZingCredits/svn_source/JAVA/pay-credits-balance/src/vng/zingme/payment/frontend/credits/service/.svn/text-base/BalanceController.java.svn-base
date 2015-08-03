/**
 *Class: BalanceController * 
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.service;

import com.vng.zing.jni.zcommon.std__vectorT_std__string_t;
import com.vng.zing.jni.zcommon.zcommon_StringHolder;
import com.vng.zing.jni.zcypher.ZCypher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import vng.zingme.payment.bo.thrift.AdminHandler;
import vng.zingme.payment.bo.thrift.BalanceCacheHandler;
import vng.zingme.payment.common.AppInfoCache;
import vng.zingme.payment.frontend.credits.config.Configuration;
import vng.zingme.payment.frontend.credits.utils.LogUtil;
import vng.zingme.payment.frontend.credits.utils.Monitor;
import vng.zingme.payment.thrift.T_AccResponse;
import vng.zingme.payment.thrift.T_AppInfo;

/**
 * balance controller
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: Apr 19, 2011
 */
public class BalanceController extends CreditsCore {

    private final String PARAM_APPID = "appID";
    private final String PARAM_DATA = "data";
    private final String PARAM_STATS = "stats";
    private final String EMPTYSTRING = "";
    private final String BALANCE_PREFIX_BAL = "bal";
    private static Logger logger_ = Logger.getLogger(BalanceController.class);
    private final Monitor readStats = new Monitor();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String stats = request.getParameter(PARAM_STATS);
        if (stats != null && stats.equals(PARAM_STATS)) {
            this.echo(this.readStats.dumpHtmlStats(), response);
            return;
        }
        long startTime = System.nanoTime();
        double balanceResult = 0;
        try {
            String appID = request.getParameter(PARAM_APPID);
            String data = request.getParameter(PARAM_DATA);
            if (!this.validReqParam(appID, data)) {
                logger_.error("validate fail with appID=" + appID + " , data=" + data);
                echoAndStats(startTime, balanceResult, response);
                return;
            }
            //decrypt
            BalanceCacheHandler balanceCacheHandler = BalanceCacheHandler.getMainInstance(Configuration.BALANCE_HOST, Configuration.BALANCE_PORT);
            int userID = getUserID(appID, data);
            if (userID == -1) {
                logger_.error("decrypt data fail with appID=" + appID + " , data=" + data);
                echoAndStats(startTime, balanceResult, response);
                return;
            }
            T_AccResponse balance = balanceCacheHandler.getBalance(userID);
            if (balance.getCode() == 0) {
                balanceResult = balance.getCurrentBalance();
            }
        } catch (Exception ex) {
            logger_.error("Uncaught Exception: " + LogUtil.stackTrace(ex));
        }
        echoAndStats(startTime, balanceResult, response);
    }

    private void echoAndStats(long startTime, double balance, HttpServletResponse response) {
        this.echo(balance, response);
        this.readStats.addMicro((System.nanoTime() - startTime) / 1000);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private boolean validReqParam(String appID, String data) {
        return appID != null && data != null && !appID.trim().equals(EMPTYSTRING) && !data.trim().equals(EMPTYSTRING);
    }

    /**
     * get user id from encrypt data
     * 1: get app key from AppInfocache
     * 2: decrypt data
     * 3: get userName from step 2
     * @param appID
     * @param data
     * @return
     */
    private int getUserID(String appID, String data) {
        int userID = -1;
        AppInfoCache cache = AppInfoCache.getInstance();
        //try get application from cache,If not call admin service
        T_AppInfo appInfo = cache.get(appID);
        try {           
            if (appInfo == null) {
                return userID;  
            }
            std__vectorT_std__string_t zma_decode = zma_decode(appID, data, BALANCE_PREFIX_BAL + appInfo.getKey1());
            userID = Integer.parseInt(zma_decode.get(0));
        } catch (Exception ex) {
            logger_.error("getUserID Exception: " + LogUtil.stackTrace(ex));
        }
        return userID;
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
