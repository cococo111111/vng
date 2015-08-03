// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DateProc.java

package com.vmg.sms.common;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DateProc
{

    public DateProc()
    {
    }

    public static Timestamp createTimestamp()
    {
        Calendar calendar = Calendar.getInstance();
        return new Timestamp(calendar.getTime().getTime());
    }

    public static Timestamp createDateTimestamp(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new Timestamp(calendar.getTime().getTime());
    }

    public static Timestamp String2Timestamp(String strInputDate)
    {
        String strDate;
        int i;
        strDate = strInputDate.trim();
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
            if(strDate.substring(0, 1).equals("9"))
                strDate = "19" + strDate.trim();
            else
                strDate = "20" + strDate.trim();
        int nYear = (new Integer(strDate)).intValue();
        calendar = Calendar.getInstance();
        calendar.set(nYear, nMonth, nDay);
        return new Timestamp(calendar.getTime().getTime());
        Exception e;
        e;
        return null;
    }

    public static String getDateTimeString(Timestamp ts)
    {
        if(ts == null)
            return "";
        else
            return Timestamp2DDMMYYYY(ts) + " " + Timestamp2HHMMSS(ts, 1);
    }

    public static String getDateString(Timestamp ts)
    {
        if(ts == null)
            return "";
        else
            return Timestamp2DDMMYYYY(ts);
    }

    public static String getTimeString(Timestamp ts)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        return calendar.get(11) + ":" + calendar.get(12) + ":" + calendar.get(13);
    }

    public static String Timestamp2DDMMYYYY(Timestamp ts)
    {
        if(ts == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        String strTemp = Integer.toString(calendar.get(5));
        if(calendar.get(5) < 10)
            strTemp = "0" + strTemp;
        if(calendar.get(2) + 1 < 10)
            return strTemp + "/0" + (calendar.get(2) + 1) + "/" + calendar.get(1);
        else
            return strTemp + "/" + (calendar.get(2) + 1) + "/" + calendar.get(1);
    }

    public static String Timestamp2DDMMYY(Timestamp ts)
    {
        if(ts == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        String strTemp = Integer.toString(calendar.get(5));
        int endYear = calendar.get(1) % 100;
        if(calendar.get(5) < 10)
            strTemp = "0" + strTemp;
        if(calendar.get(2) + 1 < 10)
            if(endYear < 10)
                return strTemp + "/0" + (calendar.get(2) + 1) + "/0" + endYear;
            else
                return strTemp + "/0" + (calendar.get(2) + 1) + "/" + endYear;
        if(endYear < 10)
            return strTemp + "/" + (calendar.get(2) + 1) + "/0" + endYear;
        else
            return strTemp + "/" + (calendar.get(2) + 1) + "/" + endYear;
    }

    public static String Timestamp2HHMMSS(Timestamp ts, int iStyle)
    {
        if(ts == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        String strTemp;
        if(iStyle == 0)
            strTemp = Integer.toString(calendar.get(11));
        else
            strTemp = Integer.toString(calendar.get(10));
        if(strTemp.length() < 2)
            strTemp = "0" + strTemp;
        if(calendar.get(12) < 10)
            strTemp = strTemp + ":0" + calendar.get(12);
        else
            strTemp = strTemp + ":" + calendar.get(12);
        if(calendar.get(13) < 10)
            strTemp = strTemp + ":0" + calendar.get(13);
        else
            strTemp = strTemp + ":" + calendar.get(13);
        if(iStyle != 0)
            if(calendar.get(9) == 0)
                strTemp = strTemp + " AM";
            else
                strTemp = strTemp + " PM";
        return strTemp;
    }

    public static String getDateTime24hString(Timestamp ts)
    {
        if(ts == null)
            return "";
        else
            return Timestamp2DDMMYYYY(ts) + " " + Timestamp2HHMMSS(ts, 0);
    }

    public static String getDateTime12hString(Timestamp ts)
    {
        if(ts == null)
            return "";
        else
            return Timestamp2DDMMYYYY(ts) + " " + Timestamp2HHMMSS(ts, 1);
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
            int iDay = calendar.get(5);
            calendar.set(5, iDay + iDayPlus);
            Timestamp tsNew = new Timestamp(calendar.getTime().getTime());
            return Timestamp2DDMMYYYY(tsNew);
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
            int iDay = calendar.get(5);
            calendar.set(5, iDay - 1);
            Timestamp tsNew = new Timestamp(calendar.getTime().getTime());
            return tsNew;
        }
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
            return "30/" + strDate;
        else
            return "";
    }

    public static Timestamp String2TimestampOld(String strInputDate)
    {
        String strDate;
        Calendar calendar_curr;
        int i;
        strDate = strInputDate.trim();
        calendar_curr = Calendar.getInstance();
        String strSub = null;
        if(strInputDate == null || "".equals(strInputDate))
            return null;
        strDate = strDate.replace('-', '/');
        strDate = strDate.replace('.', '/');
        strDate = strDate.replace(' ', '/');
        strDate = strDate.replace('_', '/');
        i = strDate.indexOf("/");
        if(i < 0)
        {
            strDate = strDate + "/" + (calendar_curr.get(2) + 1);
            strDate = strDate + "/" + calendar_curr.get(1);
            i = strDate.indexOf("/");
        }
        Calendar calendar;
        String strSub = strDate.substring(0, i);
        int nDay = (new Integer(strSub.trim())).intValue();
        strDate = strDate.substring(i + 1);
        i = strDate.indexOf("/");
        if(i < 0)
        {
            strDate = strDate + "/" + calendar_curr.get(1);
            i = strDate.indexOf("/");
        }
        strSub = strDate.substring(0, i);
        int nMonth = (new Integer(strSub.trim())).intValue() - 1;
        strDate = strDate.substring(i + 1);
        if(strDate.length() < 4)
            if(strDate.substring(0, 1).equals("9"))
                strDate = "19" + strDate.trim();
            else
                strDate = "20" + strDate.trim();
        int nYear = (new Integer(strDate)).intValue();
        calendar = Calendar.getInstance();
        calendar.set(nYear, nMonth, nDay);
        return new Timestamp(calendar.getTime().getTime());
        Exception e;
        e;
        return null;
    }

    public static Timestamp String2TimestampNewOld(String strInputDate)
    {
        Calendar calendar_curr;
        int i;
        String strDate;
        calendar_curr = Calendar.getInstance();
        String strSub = null;
        if(strInputDate == null || "".equals(strInputDate))
            return null;
        strDate = strInputDate.trim();
        strDate = strDate.replace('-', '/');
        strDate = strDate.replace('.', '/');
        strDate = strDate.replace(' ', '/');
        strDate = strDate.replace('_', '/');
        i = strDate.indexOf("/");
        if(i < 0)
            if(strDate.length() > 2)
            {
                strDate = strDate.substring(0, 2) + "/" + strDate.substring(2);
                i = strDate.indexOf("/");
            } else
            {
                strDate = strDate + "/" + (calendar_curr.get(2) + 1);
                strDate = strDate + "/" + calendar_curr.get(1);
                i = strDate.indexOf("/");
            }
        Calendar calendar;
        String strSub = strDate.substring(0, i);
        int nDay = (new Integer(strSub.trim())).intValue();
        strDate = strDate.substring(i + 1);
        i = strDate.indexOf("/");
        if(i < 0)
            if(strDate.length() > 2)
            {
                strDate = strDate.substring(0, 2) + "/" + strDate.substring(2);
                i = strDate.indexOf("/");
            } else
            {
                strDate = strDate + "/" + calendar_curr.get(1);
                i = strDate.indexOf("/");
            }
        strSub = strDate.substring(0, i);
        int nMonth = (new Integer(strSub.trim())).intValue() - 1;
        strDate = strDate.substring(i + 1);
        i = strDate.indexOf("/");
        if(i > 0)
            strDate = strDate.substring(0, i);
        if(strDate.length() < 4)
            if(strDate.substring(0, 1).equals("9"))
                strDate = "19" + strDate.trim();
            else
                strDate = "20" + strDate.trim();
        int nYear = (new Integer(strDate)).intValue();
        calendar = Calendar.getInstance();
        calendar.set(nYear, nMonth, nDay);
        return new Timestamp(calendar.getTime().getTime());
        Exception e;
        e;
        return null;
    }

    public static String String2StringDDMMYYYY(String strInputdate)
    {
        Timestamp timenew = String2TimestampNew(strInputdate);
        return Timestamp2DDMMYYYY(timenew);
    }

    public static Timestamp String2TimestampNew(String strInputDate)
    {
        Calendar calendar_curr;
        int i;
        String strDate;
        calendar_curr = Calendar.getInstance();
        String strSub = null;
        if(strInputDate == null || "".equals(strInputDate))
            return null;
        strDate = strInputDate.trim();
        strDate = strDate.replace('-', '/');
        strDate = strDate.replace('.', '/');
        strDate = strDate.replace(' ', '/');
        strDate = strDate.replace('_', '/');
        i = strDate.indexOf("/");
        if(i < 0)
        {
            String currYear = calendar_curr.get(1);
            if(strDate.length() == "dmyyyy".length() && strDate.indexOf(currYear) > 0)
                strDate = "0" + strDate.substring(0, 1) + "0" + strDate.substring(1, 2) + currYear;
            if(strDate.length() == "ddmyyyy".length() && strDate.indexOf(currYear) > 0)
            {
                int itemp = (new Integer(strDate.substring(0, 2))).intValue();
                if(itemp > 9)
                    strDate = strDate.substring(0, 2) + "0" + strDate.substring(2, 3) + currYear;
                else
                    strDate = "0" + strDate.substring(0, 1) + strDate.substring(1, 3) + currYear;
            }
            if(strDate.length() > 2)
            {
                strDate = strDate.substring(0, 2) + "/" + strDate.substring(2);
                i = strDate.indexOf("/");
            } else
            {
                if((new Integer(strDate.trim())).intValue() > calendar_curr.get(5))
                {
                    if(calendar_curr.get(2) == 0)
                    {
                        strDate = strDate + "/" + "12";
                        strDate = strDate + "/" + (calendar_curr.get(1) - 1);
                    } else
                    {
                        strDate = strDate + "/" + calendar_curr.get(2);
                        strDate = strDate + "/" + calendar_curr.get(1);
                    }
                } else
                {
                    strDate = strDate + "/" + (calendar_curr.get(2) + 1);
                    strDate = strDate + "/" + calendar_curr.get(1);
                }
                i = strDate.indexOf("/");
            }
        }
        Calendar calendar;
        String strSub = strDate.substring(0, i);
        int nDay = (new Integer(strSub.trim())).intValue();
        strDate = strDate.substring(i + 1);
        i = strDate.indexOf("/");
        if(i < 0)
            if(strDate.length() > 2)
            {
                if(strDate.length() == 3 || strDate.length() == 5)
                    strDate = "0" + strDate.substring(0, 1) + "/" + strDate.substring(1);
                else
                    strDate = strDate.substring(0, 2) + "/" + strDate.substring(2);
                i = strDate.indexOf("/");
            } else
            {
                strDate = strDate + "/" + calendar_curr.get(1);
                i = strDate.indexOf("/");
            }
        strSub = strDate.substring(0, i);
        int nMonth = (new Integer(strSub.trim())).intValue() - 1;
        strDate = strDate.substring(i + 1);
        i = strDate.indexOf("/");
        if(i > 0)
            strDate = strDate.substring(0, i);
        if(strDate.length() < 4)
            if(strDate.substring(0, 1).equals("9"))
                strDate = "19" + strDate.trim();
            else
                strDate = "20" + strDate.trim();
        int nYear;
        if(strDate.trim().length() == 4)
            nYear = (new Integer(strDate)).intValue();
        else
            nYear = calendar_curr.get(1);
        calendar = Calendar.getInstance();
        calendar.set(nYear, nMonth, nDay);
        return new Timestamp(calendar.getTime().getTime());
        Exception e;
        e;
        return null;
    }

    public static int getDayOfWeek(Timestamp ts)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        return calendar.get(7);
    }

    public static boolean isValidString2Timestamp(String strInputDate)
    {
        Calendar calendar_curr;
        int i;
        String strDate;
        calendar_curr = Calendar.getInstance();
        int curMonth = calendar_curr.get(2);
        int curYear = calendar_curr.get(1);
        String strSub = null;
        if(strInputDate == null || "".equals(strInputDate))
            return true;
        strDate = strInputDate.trim();
        strDate = strDate.replace('-', '/');
        strDate = strDate.replace('.', '/');
        strDate = strDate.replace(' ', '/');
        strDate = strDate.replace('_', '/');
        i = strDate.indexOf("/");
        if(i < 0)
        {
            String currYear = calendar_curr.get(1);
            if(strDate.length() == "dmyyyy".length() && strDate.indexOf(currYear) > 0)
                strDate = "0" + strDate.substring(0, 1) + "0" + strDate.substring(1, 2) + currYear;
            if(strDate.length() == "ddmyyyy".length() && strDate.indexOf(currYear) > 0)
            {
                int itemp = (new Integer(strDate.substring(0, 2))).intValue();
                if(itemp > 9)
                    strDate = strDate.substring(0, 2) + "0" + strDate.substring(2, 3) + currYear;
                else
                    strDate = "0" + strDate.substring(0, 1) + strDate.substring(1, 3) + currYear;
            }
            if(strDate.length() > 2)
            {
                strDate = strDate.substring(0, 2) + "/" + strDate.substring(2);
                i = strDate.indexOf("/");
            } else
            {
                strDate = strDate + "/" + (calendar_curr.get(2) + 1);
                strDate = strDate + "/" + calendar_curr.get(1);
                i = strDate.indexOf("/");
            }
        }
        int nMonth;
        int nDay;
        String strSub = strDate.substring(0, i);
        nDay = (new Integer(strSub.trim())).intValue();
        strDate = strDate.substring(i + 1);
        i = strDate.indexOf("/");
        if(i < 0)
            if(strDate.length() > 2)
            {
                if(strDate.length() == 3 || strDate.length() == 5)
                    strDate = "0" + strDate.substring(0, 1) + "/" + strDate.substring(1);
                else
                    strDate = strDate.substring(0, 2) + "/" + strDate.substring(2);
                i = strDate.indexOf("/");
            } else
            {
                strDate = strDate + "/" + calendar_curr.get(1);
                i = strDate.indexOf("/");
            }
        strSub = strDate.substring(0, i);
        nMonth = (new Integer(strSub.trim())).intValue() - 1;
        strDate = strDate.substring(i + 1);
        i = strDate.indexOf("/");
        if(i > 0)
            strDate = strDate.substring(0, i);
        if(strDate.length() < 4)
            if(strDate.substring(0, 1).equals("9"))
                strDate = "19" + strDate.trim();
            else
                strDate = "20" + strDate.trim();
        int nYear;
        if(strDate.trim().length() == 4)
            nYear = (new Integer(strDate)).intValue();
        else
            nYear = calendar_curr.get(1);
        Calendar calendar = Calendar.getInstance();
        calendar.set(nYear, nMonth, nDay);
        if(nDay > 31 || nDay < 1)
            return false;
        return nMonth <= 12 && nMonth >= 0;
        Exception e;
        e;
        return false;
    }
}
