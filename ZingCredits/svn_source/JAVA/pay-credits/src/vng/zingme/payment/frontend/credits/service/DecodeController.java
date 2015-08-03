/**
 *Class: RequestFormController *
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.service;

import com.vng.zing.jni.zcommon.std__vectorT_std__string_t;
import com.vng.zing.jni.zcommon.zcommon_StringHolder;
import com.vng.zing.jni.zcypher.ZCypher;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import vng.zingme.payment.thrift.T_AppInfo;

/**
 * get request form
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: May 19, 2011
 */
public class DecodeController extends CreditsCore {

    private static Logger logger_ = Logger.getLogger(DecodeController.class);

    public DecodeController() {
        try {
            System.load("/zserver/lib/zcypher/libZCommonJN-1.0.so");
            System.load("/zserver/lib/zcypher/libZCypherJN-1.0.so");
        } catch (Exception e) {
            // e.printStackTrace();
            // System.exit(1);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String html = "<html><body><form method='post' action =''>appID:<input type='text' name='appid'><br>Data:<input type='text' name='data'><br><input type='radio' name='type' value='pay'>Pay<input type='radio' name='type' value='bal'>Bal<br><input type='submit' value='decode'></form>" + timeAction() + "</body></html>";
        this.echo(html, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String appid = request.getParameter("appid");
        String data = request.getParameter("data");
        String type = request.getParameter("type");
        if (appid == null || data == null || type == null) {
            this.doGet(request, response);
            return;
        }

        T_AppInfo appInfo = getAppInfo(appid);
        std__vectorT_std__string_t tx = null;
        try {
            tx = this.parseTransaction(appInfo, request, response);
            if (tx == null || tx.is_empty()) {
                this.echo("error code:" + code, response);

            }
            String s = "";
            for (int i = 0; i < tx.size(); i++) {
                s += (i + "= " + tx.get(i) + "<br>");
            }
            this.echo(s, response);
        } catch (Exception e) {
            this.echo("error code:" + code, response);
            return;
        }
    }

    private std__vectorT_std__string_t parseTransaction(T_AppInfo appInfo, HttpServletRequest request, HttpServletResponse response) throws TException {
        String agentID = request.getParameter("appid");
        String encodedData = request.getParameter("data");
        std__vectorT_std__string_t tx = zingUnSignature(appInfo, agentID, encodedData, request.getParameter("type"));

        return tx;
    }

    private std__vectorT_std__string_t zingUnSignature(T_AppInfo appInfo, String agentID, String encodedData, String type) {
        std__vectorT_std__string_t zma_decode = zma_decode(agentID, encodedData, type + appInfo.getKey1());
        return zma_decode;

    }
    public static int code;

    public std__vectorT_std__string_t zma_decode(String agentID, String strEncoded, String k) {
        std__vectorT_std__string_t params2 = new std__vectorT_std__string_t();

        zcommon_StringHolder key = new zcommon_StringHolder();

        key.setValue(k);
        zcommon_StringHolder data = new zcommon_StringHolder();
        data.setValue(strEncoded);

        int e = ZCypher.zma_decode(params2, data, key, 0, 300);

        if (e < 0) {
            code = e;
            return null;
        }

        return params2;
    }

    public String timeAction() {
        String s = "Time Server Dev:<br>Viet Nam GMT+7: ";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd k:m:s");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        Calendar calendar = Calendar.getInstance();
        s += dateFormat.format(calendar.getTime()) + "<br>";


        s+="UTC (GMT+0):";
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar calendar2 = Calendar.getInstance();
        s += dateFormat.format(calendar2.getTime()) + "<br>";
        return s;
    }
}
