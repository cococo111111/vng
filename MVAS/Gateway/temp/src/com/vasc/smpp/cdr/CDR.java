// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CDR.java

package com.vasc.smpp.cdr;

import com.vasc.common.DateProc;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class CDR
{

    public CDR()
    {
        id = null;
        user_id = null;
        service_id = null;
        mobile_operator = null;
        command_code = null;
        info = null;
        submit_date = null;
        done_date = null;
        total_segments = 0;
    }

    public String getCommandCode()
    {
        return command_code;
    }

    private String getCurrentDate()
    {
        String sdate = DateProc.getYYYYMMDDHHMMString(new Timestamp(System.currentTimeMillis()));
        return sdate.substring(2);
    }

    public String getDoneDate()
    {
        if(done_date != null)
            return done_date;
        else
            return getCurrentDate();
    }

    public BigDecimal getId()
    {
        return id;
    }

    public String getInfo()
    {
        return info;
    }

    public String getMobileOperator()
    {
        return mobile_operator;
    }

    public String getServiceId()
    {
        return service_id;
    }

    public String getSubmitDate()
    {
        if(submit_date != null)
            return submit_date;
        else
            return getCurrentDate();
    }

    public int getTotalSegments()
    {
        return total_segments;
    }

    public String getUserId()
    {
        return user_id;
    }

    public static void main(String args[])
    {
        CDR cdr = new CDR();
        System.out.println();
    }

    public void setCommandCode(String value)
    {
        command_code = value;
    }

    public void setDoneDate(String value)
    {
        done_date = value;
    }

    public void setId(BigDecimal value)
    {
        id = value;
    }

    public void setInfo(String value)
    {
        info = value;
        if(info == null)
            info = " ";
    }

    public void setMobileOperator(String value)
    {
        mobile_operator = value;
    }

    public void setServiceId(String value)
    {
        service_id = value;
    }

    public void setSubmitDate(String value)
    {
        submit_date = value;
    }

    public void setTotalSegments(int totalSegments)
    {
        total_segments = totalSegments;
    }

    public void setUserId(String value)
    {
        user_id = value;
    }

    private String command_code;
    private String done_date;
    private BigDecimal id;
    private String info;
    private String mobile_operator;
    private String service_id;
    private String submit_date;
    private int total_segments;
    private String user_id;
}
