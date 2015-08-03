/**
 *Class: Utils * 
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.utils;

import com.twmacinta.util.MD5;
import com.vng.zing.stdprofile2.thrift.TUIDResult;
import com.vng.zing.stdprofile2.thrift.TValue;
import com.vng.zing.stdprofile2.thrift.TValueResult;
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
 public static TValue getProfile(int uID) throws ZException, TException {
        STDProfile2Handler profile2Handler = STDProfile2Handler.getMainInstance(Configuration.STDPROFILE_HOST, Configuration.STDPROFILE_PORT);
        OpHandle oh = new OpHandle();
        oh.setSource(Configuration.STDPROFILE_SOURCE);
        oh.setAuth(Configuration.STDPROFILE_AUTH);
        TValueResult tUIDResult = profile2Handler.get(oh, uID);
        if (tUIDResult == null || tUIDResult.getError() < 0) {
            return null;
        }
        return tUIDResult.getValue();
    }
    public static String formatMoney(double amount) {
        DecimalFormat myFormatter = new DecimalFormat("###,###.##");
        String out = myFormatter.format(amount);
        return out;
    }

    public static String getAppName(String appID) throws TException {
        AppServiceHandler adminHandler = AppServiceHandler.getMainInstance(Utils.getConfig("gameinfor.host"), Integer.parseInt(Utils.getConfig("gameinfor.port")));
        return adminHandler.getAppName(appID);
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
        STDProfile2Handler profile2Handler = STDProfile2Handler.getMainInstance(Configuration.STDPROFILE_HOST, Configuration.STDPROFILE_PORT);
        OpHandle oh = new OpHandle();
        oh.setSource(Configuration.STDPROFILE_SOURCE);
        oh.setAuth(Configuration.STDPROFILE_AUTH);
        TValueResult tUIDResult = profile2Handler.get(oh, 11298436);
        int i = 1;
    }

    public static String buildAvatarPath(String username, int avatarversion, String size) {

        String path_big = Config.getParam("avatarpath", "180");

        String path_50 = Config.getParam("avatarpath", "50");

        String path_75 = Config.getParam("avatarpath", "75");

        String path_120 = Config.getParam("avatarpath", "120");

        String path_160 = Config.getParam("avatarpath", "160");

        String path_default = Config.getParam("avatarpath", "default");

        String path = path_default;



        int level = 4;



        int i_size = 50;



        MD5 md5 = new MD5();

        md5.Update(username);



        String hash = md5.asHex();



        String avatarpath = "";



        for (int i = 0; i < level; i++) {

            avatarpath += "/" + hash.substring(i, i + 1);

        }



        try {

            i_size = Integer.valueOf(size);

        } catch (Exception ex) {
            i_size = 50;
        }



        if (i_size == 180) {

            path = path_big;

        } else if (i_size == 50) {

            path = path_50;

            String sub = hash.substring(0, 1);

            if (sub.equals("5")) {
                sub = "1";
            }

            path = String.format(path, sub);

        } else if (i_size == 75) {

            path = path_75;

        } else if (i_size == 120) {

            path = path_120;

        } else if (i_size == 160) {

            path = path_160;

        } else {

            path = path_50;

            String sub = hash.substring(0, 1);

            if (sub.equals("5")) {
                sub = "1";
            }

            path = String.format(path, sub);

        }







        return path + avatarpath + "/" + username + "_" + size + "_" + avatarversion + ".jpg";

    }
}
