/**
 *Class: Utils * 
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.utils;

import com.vng.zing.stdprofile2.thrift.TUIDResult;
import com.vng.zing.zcommon.thrift.OpHandle;
import com.vng.zing.zcommon.thrift.ZException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.thrift.TException;
import vng.wte.common.Config;
import vng.zingme.payment.bo.thrift.AppServiceHandler;
import vng.zingme.payment.frontend.credits.config.Configuration;

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

    public static int getUIDFromUName(String uName) throws ZException, TException {
        STDProfile2Handler profile2Handler = STDProfile2Handler.getMainInstance(Configuration.STDPROFILE_HOST, Configuration.STDPROFILE_PORT);
        OpHandle oh = new OpHandle();
        oh.setSource(Configuration.STDPROFILE_SOURCE);
        oh.setAuth(Configuration.STDPROFILE_AUTH);
        TUIDResult tUIDResult = profile2Handler.getUID(oh, uName);
        if (tUIDResult == null || tUIDResult.getError() < 0) {
            return -1;
        }
        return tUIDResult.getValue();
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

    public static String removeDouble(double d) {
        if ((d + "").endsWith(".0") || (d + "").endsWith(".00")) {
            return new Double(d).intValue() + "";
        }
        return d + "";
    }

    public static void main(String[] args) {
        String l = "ntvv2.zing.vn";
        System.out.println(Utils.removeHTTP(l));
    }

    public static String removeHTTP(String url) {
        if (url == null) {
            return "";
        }
        try {
            return url.replace("http://", "");
        } catch (Exception e) {
            return url;
        }

    }
}
