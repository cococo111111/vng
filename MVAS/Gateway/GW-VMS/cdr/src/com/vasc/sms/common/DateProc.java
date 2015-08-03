// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DateProc.java

package com.vasc.sms.common;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DateProc
{

    public DateProc()
    {
    }

    public static Timestamp String2Timestamp(String strInputDate)
    {
        String strDate;
        int i;
        strDate = strInputDate;
        String strSub = null;
        if(strInputDate == null || "".equals(strInputDate))
            return null;
        strDate = strDate.replace('-', '/');
        strDate = strDate.replace('.', '/');
        strDate = strDate.replace(' ', '/');
        strDate = strDate.replace('_', '/');
        i = strDate.indexOf("/");
        if(i < 0)
            return null;
        int nDay;
        String strSub = strDate.substring(0, i);
        nDay = (new Integer(strSub.trim())).intValue();
        strDate = strDate.substring(i + 1);
        i = strDate.indexOf("/");
        if(i < 0)
            return null;
        Calendar calendar;
        String strSub = strDate.substring(0, i);
        int nMonth = (new Integer(strSub.trim())).intValue() - 1;
        strDate = strDate.substring(i + 1);
        if(strDate.length() < 4)
        {
            if(strDate.substring(0, 1).equals("9"))
                strDate = "19" + strDate.trim();
            else
                strDate = "20" + strDate.trim();
        }
        int nYear = (new Integer(strDate)).intValue();
        calendar = Calendar.getInstance();
        calendar.set(nYear, nMonth, nDay);
        return new Timestamp(calendar.getTime().getTime());
        Exception e;
        e;
        return null;
    }

    public static String Timestamp2DDMMYY(Timestamp ts)
    {
        if(ts == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        Calendar _tmp = calendar;
        String strTemp = Integer.toString(calendar.get(5));
        Calendar _tmp1 = calendar;
        int endYear = calendar.get(1) % 100;
        Calendar _tmp2 = calendar;
        if(calendar.get(5) < 10)
            strTemp = "0" + strTemp;
        Calendar _tmp3 = calendar;
        if(calendar.get(2) + 1 < 10)
            if(endYear < 10)
            {
                Calendar _tmp4 = calendar;
                return strTemp + "/0" + (calendar.get(2) + 1) + "/0" + endYear;
            } else
            {
                Calendar _tmp5 = calendar;
                return strTemp + "/0" + (calendar.get(2) + 1) + "/" + endYear;
            }
        if(endYear < 10)
        {
            Calendar _tmp6 = calendar;
            return strTemp + "/" + (calendar.get(2) + 1) + "/0" + endYear;
        } else
        {
            Calendar _tmp7 = calendar;
            return strTemp + "/" + (calendar.get(2) + 1) + "/" + endYear;
        }
    }

    public static String Timestamp2DDMMYYYY(Timestamp ts)
    {
        if(ts == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        Calendar _tmp = calendar;
        String strTemp = Integer.toString(calendar.get(5));
        Calendar _tmp1 = calendar;
        if(calendar.get(5) < 10)
            strTemp = "0" + strTemp;
        Calendar _tmp2 = calendar;
        if(calendar.get(2) + 1 < 10)
        {
            Calendar _tmp3 = calendar;
            Calendar _tmp4 = calendar;
            return strTemp + "/0" + (calendar.get(2) + 1) + "/" + calendar.get(1);
        } else
        {
            Calendar _tmp5 = calendar;
            Calendar _tmp6 = calendar;
            return strTemp + "/" + (calendar.get(2) + 1) + "/" + calendar.get(1);
        }
    }

    public static String Timestamp2HHMMSS(Timestamp ts, int iStyle)
    {
        if(ts == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        String strTemp;
        if(iStyle == 0)
        {
            Calendar _tmp = calendar;
            strTemp = Integer.toString(calendar.get(11));
        } else
        {
            Calendar _tmp1 = calendar;
            strTemp = Integer.toString(calendar.get(10));
        }
        if(strTemp.length() < 2)
            strTemp = "0" + strTemp;
        Calendar _tmp2 = calendar;
        if(calendar.get(12) < 10)
        {
            Calendar _tmp3 = calendar;
            strTemp = strTemp + ":0" + calendar.get(12);
        } else
        {
            Calendar _tmp4 = calendar;
            strTemp = strTemp + ":" + calendar.get(12);
        }
        Calendar _tmp5 = calendar;
        if(calendar.get(13) < 10)
        {
            Calendar _tmp6 = calendar;
            strTemp = strTemp + ":0" + calendar.get(13);
        } else
        {
            Calendar _tmp7 = calendar;
            strTemp = strTemp + ":" + calendar.get(13);
        }
        if(iStyle != 0)
        {
            Calendar _tmp8 = calendar;
            Calendar _tmp9 = calendar;
            if(calendar.get(9) == 0)
                strTemp = strTemp + " AM";
            else
                strTemp = strTemp + " PM";
        }
        return strTemp;
    }

    public static String TimestampPlusDay2DDMMYYYY(Timestamp ts, int iDayPlus)
    {
        if(ts == null)
        {
            return "";
        } else
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(ts.getTime()));
            Calendar _tmp = calendar;
            int iDay = calendar.get(5);
            Calendar _tmp1 = calendar;
            calendar.set(5, iDay + iDayPlus);
            Timestamp tsNew = new Timestamp(calendar.getTime().getTime());
            return Timestamp2DDMMYYYY(tsNew);
        }
    }

    public static Timestamp createDateTimestamp(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new Timestamp(calendar.getTime().getTime());
    }

    public static Timestamp createTimestamp()
    {
        Calendar calendar = Calendar.getInstance();
        return new Timestamp(calendar.getTime().getTime());
    }

    public static String getDateString(Timestamp ts)
    {
        if(ts == null)
            return "";
        else
            return Timestamp2DDMMYYYY(ts);
    }

    public static String getDateTime12hString(Timestamp ts)
    {
        if(ts == null)
            return "";
        else
            return Timestamp2DDMMYYYY(ts) + " " + Timestamp2HHMMSS(ts, 1);
    }

    public static String getDateTime24hString(Timestamp ts)
    {
        if(ts == null)
            return "";
        else
            return Timestamp2DDMMYYYY(ts) + " " + Timestamp2HHMMSS(ts, 0);
    }

    public static String getDateTimeString(Timestamp ts)
    {
        if(ts == null)
            return "";
        else
            return Timestamp2DDMMYYYY(ts) + " " + Timestamp2HHMMSS(ts, 1);
    }

    public static String getLastestDateOfMonth(String strMonthYear)
    {
        String strDate = strMonthYear;
        String strSub = null;
        int i = strDate.indexOf("/");
        if(i < 0)
            return "";
        strSub = strDate.substring(0, i);
        int nMonth = (new Integer(strSub)).intValue();
        strDate = strDate.substring(i + 1);
        int nYear = (new Integer(strDate)).intValue();
        boolean leapyear = false;
        if(nYear % 100 == 0)
        {
            if(nYear % 400 == 0)
                leapyear = true;
        } else
        if(nYear % 4 == 0)
            leapyear = true;
        if(nMonth == 2)
            if(leapyear)
                return "29/" + strDate;
            else
                return "28/" + strDate;
        if(nMonth == 1 || nMonth == 3 || nMonth == 5 || nMonth == 7 || nMonth == 8 || nMonth == 10 || nMonth == 12)
            return "31/" + strDate;
        if(nMonth == 4 || nMonth == 6 || nMonth == 9 || nMonth == 11)
        {
            return "30/" + strDate;
        } else
        {
            return "";
        }
    }

    public static Timestamp getPreviousDate(Timestamp ts)
    {
        if(ts == null)
        {
            return null;
        } else
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(ts.getTime()));
            Calendar _tmp = calendar;
            int iDay = calendar.get(5);
            Calendar _tmp1 = calendar;
            calendar.set(5, iDay - 1);
            Timestamp tsNew = new Timestamp(calendar.getTime().getTime());
            return tsNew;
        }
    }

    public static String getTimeString(Timestamp ts)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        Calendar _tmp = calendar;
        Calendar _tmp1 = calendar;
        Calendar _tmp2 = calendar;
        return calendar.get(11) + ":" + calendar.get(12) + ":" + calendar.get(13);
    }
}
