/**
 *Class: Utils * 
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.thrift.TException;
import vng.wte.common.Config;
import vng.zingme.payment.bo.thrift.AppServiceHandler;
import wtcommon.TInvalidArgument;
import wtcommon.TInvalidOperation;
import wtprofile.TStdProfileResult;
import wtprofilepool.StdProfileClientManager;

/**
 * mo ta here
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: Apr 18, 2011
 */
public class Utils {

    public static String getConfig(String key) {
        return Config.getParam(System.getProperty("appenv"), key);
    }

    public static int getUIDFromUName(String uName) throws TException, TInvalidOperation, TInvalidArgument {
        StdProfileClientManager spcm = StdProfileClientManager.getInstance(Utils.getConfig("stdprofile.host"), Integer.parseInt(Utils.getConfig("stdprofile.port")));
        TStdProfileResult result = spcm.getDataByName(uName);
        return result.getData().uID;
    }

    public static String formatMoney(double amount) {
        DecimalFormat myFormatter = new DecimalFormat("###,###.###");
        String out = myFormatter.format(amount);
        return out;
    }

    public static String getAppName(String appID) throws TException {
        AppServiceHandler appHandler = AppServiceHandler.getMainInstance(Utils.getConfig("gameinfor.host"), Integer.parseInt(Utils.getConfig("gameinfor.port")));
        return appHandler.getAppName(appID);
    }

    public static String formatDate(long time) {
        DateFormat dateFormat = new SimpleDateFormat("kk:mm, dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000);
        return dateFormat.format(calendar.getTime());

    }

    public static void main(String[] args) {
        double l = 8505.40;
        System.out.println(Utils.formatMoney(l));
    }
}
