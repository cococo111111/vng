// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReportMsgParser.java

package com.vasc.smpp.gateway;

import com.vasc.common.DateProc;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ReportMsgParser
{

    public ReportMsgParser(String message)
    {
        this.message = null;
        id = null;
        submit = 0;
        deliver = 0;
        submit_date = null;
        done_date = null;
        status = null;
        error = null;
        text = null;
        delivered = false;
        this.message = message;
    }

    public ReportMsgParser()
    {
        message = null;
        id = null;
        submit = 0;
        deliver = 0;
        submit_date = null;
        done_date = null;
        status = null;
        error = null;
        text = null;
        delivered = false;
    }

    public int getDeliver()
    {
        return deliver;
    }

    public String getDoneDate()
    {
        return done_date;
    }

    public String getError()
    {
        return error;
    }

    public String getId()
    {
        return id;
    }

    public static ReportMsgParser getInstance()
    {
        ReportMsgParser parser = new ReportMsgParser();
        String sdate = DateProc.getYYYYMMDDHHMMString(new Timestamp(System.currentTimeMillis()));
        parser.submit_date = sdate.substring(2);
        parser.done_date = parser.submit_date;
        return parser;
    }

    public String getStatus()
    {
        return status;
    }

    public int getSubmit()
    {
        return submit;
    }

    public String getSubmitDate()
    {
        return submit_date;
    }

    public String getText()
    {
        return text;
    }

    public boolean isDelivered()
    {
        return "DELIVRD".equals(status);
    }

    public static void main(String args[])
    {
        ReportMsgParser parser = getInstance();
        System.out.println("Submit date: " + parser.getSubmitDate());
        System.out.println("Done date: " + parser.getDoneDate());
    }

    public boolean parseMessage()
    {
        if(message == null || "".equals(message))
            return false;
        String tempStr = message.toUpperCase().trim();
        int index1 = tempStr.indexOf(":");
        int index2 = tempStr.indexOf(" ");
        BigDecimal strId = new BigDecimal(tempStr.substring(index1 + 1, index2));
        id = strId.toString();
        tempStr = tempStr.substring(index2 + 1);
        index1 = tempStr.indexOf(":");
        index2 = tempStr.indexOf(" ");
        submit = Integer.parseInt(tempStr.substring(index1 + 1, index2));
        tempStr = tempStr.substring(index2 + 1);
        index1 = tempStr.indexOf(":");
        index2 = tempStr.indexOf(" ");
        deliver = Integer.parseInt(tempStr.substring(index1 + 1, index2));
        tempStr = tempStr.substring(index2 + 1);
        index1 = tempStr.indexOf(":");
        index2 = tempStr.indexOf(" ", index1);
        submit_date = tempStr.substring(index1 + 1, index2);
        tempStr = tempStr.substring(index2 + 1);
        index1 = tempStr.indexOf(":");
        index2 = tempStr.indexOf(" ", index1);
        done_date = tempStr.substring(index1 + 1, index2);
        tempStr = tempStr.substring(index2 + 1);
        index1 = tempStr.indexOf(":");
        index2 = tempStr.indexOf(" ");
        status = tempStr.substring(index1 + 1, index2);
        tempStr = tempStr.substring(index2 + 1);
        index1 = tempStr.indexOf(":");
        index2 = tempStr.indexOf(" ");
        error = tempStr.substring(index1 + 1, index2);
        tempStr = tempStr.substring(index2 + 1);
        index1 = tempStr.indexOf(":");
        text = tempStr.substring(index1 + 1);
        return true;
        Exception e;
        e;
        System.out.println("ReportSMParser.parseMessage::" + e.getMessage());
        return false;
    }

    public boolean parseMessage(String message)
    {
        this.message = message;
        return parseMessage();
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    private int deliver;
    private boolean delivered;
    private String done_date;
    private String error;
    private String id;
    private String message;
    private String status;
    private int submit;
    private String submit_date;
    private String text;
}
