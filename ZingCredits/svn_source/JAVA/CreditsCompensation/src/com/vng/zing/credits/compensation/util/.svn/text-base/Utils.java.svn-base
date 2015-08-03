/**
 * Class: Utils * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package com.vng.zing.credits.compensation.util;

import com.vng.jcore.thriftutil.TClientPool;
import com.vng.jcore.thriftutil.TClientPoolConfig;
import com.vng.jcore.thriftutil.TClientPoolMan;
import com.vng.zing.stdprofile2.thrift.StdProfile2Service_Rd;
import com.vng.zing.stdprofile2.thrift.TValueResult;
import com.vng.zing.zcommon.thrift.OpHandle;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.log4j.Logger;
import vng.zingme.util.LogUtil;

/**
 * mo ta here version: 1.0
 *
 * @author: nhutnh@vng.com.vn created: Apr 18, 2011
 */
public class Utils {

    private static Utils _mainInstance;
    private static Object lockObj = new Object();
    private final static Logger _logger = Logger.getLogger(Utils.class);

    public Utils() {
        initalizeStdProfileClientPool();
    }


    public static Utils getMainInstance(){
        if (_mainInstance == null) {
            synchronized (lockObj) {
                if (_mainInstance == null) {
                    _mainInstance = new Utils();
                }
            }
        }
        return _mainInstance;
    }

    public static String formatMoney(double amount) {
        DecimalFormat myFormatter = new DecimalFormat("###,###.##");
        String out = myFormatter.format(amount);
        return out;
    }

    public static String formatDate(long time) {
        DateFormat dateFormat = new SimpleDateFormat("kk:mm, dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000);
        return dateFormat.format(calendar.getTime());

    }

    public static String removeDouble(double d) {
        if ((d + "").endsWith(".0") || (d + "").endsWith(".00")) {
            return new Double(d).intValue() + "";
        }
        return d + "";
    }

    public static void main(String[] args) throws Exception {
        initalizeStdProfileClientPool();
        int i = 1;
    }
    private static OpHandle stdprofileOpt = null;

    public int getZingId(String zingName) {
        TClientPool<StdProfile2Service_Rd.Client> firstPool = (TClientPool<StdProfile2Service_Rd.Client>) TClientPoolMan.Instance.getFirstPool(StdProfile2Service_Rd.Client.class);
        int resId = 0;
        StdProfile2Service_Rd.Client cli = null;
        try {
            cli = (StdProfile2Service_Rd.Client) firstPool.borrowClient();
            TValueResult val = cli.getByName(stdprofileOpt, zingName);
            if (val.error >= 0) {
                resId = val.value.uID;
            } else {
                _logger.warn("StdProfile2Service_Rd.getByName fail with code " + val.error + " for username " + zingName);
            }
            firstPool.returnObject(cli);
        } catch (Exception ex) {
            _logger.warn(LogUtil.stackTrace(ex));
            firstPool.invalidObjExless(cli);
        }
        return resId;
    }

    public String getZingName(int id) {
        TClientPool<StdProfile2Service_Rd.Client> firstPool = (TClientPool<StdProfile2Service_Rd.Client>) TClientPoolMan.Instance.getFirstPool(StdProfile2Service_Rd.Client.class);
        String resName = "";
        StdProfile2Service_Rd.Client cli = null;
        try {
            cli = (StdProfile2Service_Rd.Client) firstPool.borrowClient();
            TValueResult val = cli.get(stdprofileOpt, id);
            if (val.error >= 0) {
                resName = val.value.userName;
            } else {
                _logger.warn("StdProfile2Service_Rd.get fail with code " + val.error + " for userid " + id);
            }

            firstPool.returnObject(cli);
        } catch (Exception ex) {
            _logger.warn(LogUtil.stackTrace(ex));
            firstPool.invalidObjExless(cli);
        }
        return resName;
    }

    private static void initalizeStdProfileClientPool() {
        String STDPROFILE_ADDR = System.getProperty("stdprofileHost", "10.30.22.173");
        int STDPROFILE_PORT = Integer.parseInt(System.getProperty(
                "stdprofilePort", "9281"));
        TClientPoolConfig.ConnConfig STDPROFILE_CONFIG_CONN = new TClientPoolConfig.ConnConfig(
                STDPROFILE_ADDR, STDPROFILE_PORT, true, false, 0);

        TClientPoolMan.Instance.addPool(
                new TClientPool<StdProfile2Service_Rd.Client>(
                new StdProfile2Service_Rd.Client.Factory(), STDPROFILE_CONFIG_CONN, TClientPoolConfig.DefaultPoolConfig));

        stdprofileOpt = new OpHandle();
        stdprofileOpt.auth = System.getProperty("stdprofileAuth", "9EpNVY");
        stdprofileOpt.source = System.getProperty("stdprofileSource", "credits");
    }
}
