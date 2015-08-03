/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vng.zingme.payment.dailyreport;

import java.util.Calendar;
import java.util.TimeZone;
import vng.zingme.payment.common.CommonDef;

/**
 *
 * @author root
 */
public class TimeParser {

    public static int timeParser(String yyyymmddhhmmss) {
        int res = 0;
        if (yyyymmddhhmmss.length() != 14) {
            return res;
        }

        int year = Integer.parseInt(yyyymmddhhmmss.substring(0, 4));
        int month = Integer.parseInt(yyyymmddhhmmss.substring(4).substring(0, 2)) - 1;
        int day = Integer.parseInt(yyyymmddhhmmss.substring(6).substring(0, 2));
        int hour = Integer.parseInt(yyyymmddhhmmss.substring(8).substring(0, 2));
        int minute = Integer.parseInt(yyyymmddhhmmss.substring(10).substring(0, 2));
        int sec = Integer.parseInt(yyyymmddhhmmss.substring(12).substring(0, 2));

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.set(year, month, day, hour, minute, sec);

        res = (int) (cal.getTimeInMillis() / (long) CommonDef.MILISECINSEC);

        return res;
    }
}
