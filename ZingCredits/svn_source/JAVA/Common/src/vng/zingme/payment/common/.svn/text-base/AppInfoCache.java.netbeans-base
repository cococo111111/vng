/**
 *Class: AppInfoCache * 
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.zingme.payment.bo.thrift.AppServiceHandler;
import vng.zingme.payment.thrift.T_AppInfo;

/**
 * cache for application information
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * @modify by: loidc@vng.com.vn
 * created: Apr 20, 2011
 */
public class AppInfoCache {

    private static Logger logger_ = Logger.getLogger("appActions");
    private static HashMap<String, T_AppInfo> __mainMap = null;
    private static AppInfoCache _instance = null;
    protected static ReentrantLock locker = new ReentrantLock();

    public static AppInfoCache getInstance() {
        if (_instance == null) {
            locker.lock();
            try {
                if (_instance == null) {
                    _instance = new AppInfoCache();
                }
            } finally {
                locker.unlock();
            }
        }
        return _instance;
    }

    private AppInfoCache() {
        __mainMap = new HashMap<String, T_AppInfo>();
        this.setUpThread();
    }

    public T_AppInfo get(String appID) {
        return getMainMap().get(appID);
    }

    protected HashMap<String, T_AppInfo> getMainMap() {
        HashMap<String, T_AppInfo> ret = null;
        synchronized (obj) {
            ret = __mainMap;
        }
        return ret;
    }

    public List<T_AppInfo> getAllAppInfo() {
        List<T_AppInfo> allApps = new LinkedList<T_AppInfo>();

        for (T_AppInfo t_AppInfo : getMainMap().values()) {
            allApps.add(t_AppInfo);
        }

        return allApps;
    }

    private void setUpThread() {
        Thread appInfoSync = new Thread(new AppInfoSync());
        appInfoSync.start();
    }

    protected static class AppInfoSync implements Runnable {

        private static int sleep = 0;

        public AppInfoSync() {
            sleep = Integer.parseInt(System.getProperty("aicacheperiod", "2"));
            sleep *= 60 * 1000;
            appServiceHandler = AppServiceHandler.getMainInstance(System.getProperty("appHost", "localhost"), Integer.parseInt(System.getProperty("appPort", "10115")));
        }

        public void run() {
            while (true) {
                int hope_count = 2;
                while (hope_count > 0) {
                    hope_count--;
                    try {
                        List<T_AppInfo> allAppInfo = appServiceHandler.getAllAppInfo();
                        if (allAppInfo == null || allAppInfo.isEmpty()) {
                            continue;
                        }
                        HashMap<String, T_AppInfo> tmpMap = new HashMap<String, T_AppInfo>();
                        for (T_AppInfo appInfo : allAppInfo) {
                            if (appInfo != null && appInfo.getAppID() != null) {
                                tmpMap.put(appInfo.getAppID(), appInfo);
                            }
                        }
                        if (tmpMap.size() > 0) {
                            synchronized (obj) {
                                __mainMap = tmpMap;
                            }
                        }
                    } catch (TException ex) {
                        logger_.error("thread getAllApplication Exception: " + vng.zingme.util.LogUtil.stackTrace(ex));
                    }
                }
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException ex) {
                    logger_.error("thread getAllApplication Exception: " + vng.zingme.util.LogUtil.stackTrace(ex));
                }
            }
        }
        private static AppServiceHandler appServiceHandler = null;
    }
    private static final Object obj = new Object();
}
