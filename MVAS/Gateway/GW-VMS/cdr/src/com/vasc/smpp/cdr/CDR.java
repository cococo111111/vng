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
        process_result = null;
        Message_Type = null;
        Request_Id = null;
        CP_Id = 0;
        Submit_date_timestamp = null;
        Done_date_timestamp = null;
    }

    public int getCPid()
    {
        return CP_Id;
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

    private String getCurrentDateFULL()
    {
        String sdate = DateProc.getYYYYMMDDHHMMSSString(new Timestamp(System.currentTimeMillis()));
        return sdate;
    }

    public String getDoneDate()
    {
        if(done_date != null)
            return done_date;
        else
            return getCurrentDate();
    }

    public String getDoneDateFULL()
    {
        if(done_date != null)
            return "20" + done_date;
        else
            return getCurrentDateFULL();
    }

    public Timestamp getDone_date_timestamp()
    {
        return Done_date_timestamp;
    }

    public BigDecimal getId()
    {
        return id;
    }

    public String getInfo()
    {
        return info;
    }

    public String getMessageType()
    {
        return Message_Type;
    }

    public String getMobileOperator()
    {
        return mobile_operator;
    }

    public String getProcessResult()
    {
        return process_result;
    }

    public String getRequestId()
    {
        return Request_Id;
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

    public String getSubmitDateFULL()
    {
        if(submit_date != null)
            return "20" + submit_date;
        else
            return getCurrentDateFULL();
    }

    public Timestamp getSubmit_date_timestamp()
    {
        return Submit_date_timestamp;
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

    public void setCPId(int CPId)
    {
        CP_Id = CPId;
    }

    public void setCommandCode(String value)
    {
        command_code = value;
    }

    public void setDoneDate(String value)
    {
        if("20".equals(value.substring(0, 2)))
            done_date = value;
        else
            done_date = "20" + value;
    }

    public void setDone_date_timestamp(Timestamp Done_date_timestamp)
    {
        this.Done_date_timestamp = Done_date_timestamp;
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

    public void setMessageType(String MessageType)
    {
        if(MessageType == null)
            Message_Type = "1";
        else
            Message_Type = MessageType;
    }

    public void setMobileOperator(String value)
    {
        mobile_operator = value;
    }

    public void setProcessResult(String ProcessResult)
    {
        if(ProcessResult == null)
            process_result = "1";
        else
            process_result = ProcessResult;
    }

    public void setRequestId(String RequestId)
    {
        if(RequestId == null)
            Request_Id = "0";
        else
            Request_Id = RequestId;
    }

    public void setServiceId(String value)
    {
        service_id = value;
    }

    public void setSubmitDate(String value)
    {
        if("20".equals(value.substring(0, 2)))
            submit_date = value;
        else
            submit_date = "20" + value;
    }

    public void setSubmit_date_timestamp(Timestamp Submit_date_timestamp)
    {
        this.Submit_date_timestamp = Submit_date_timestamp;
    }

    public void setTotalSegments(int totalSegments)
    {
        total_segments = totalSegments;
    }

    public void setUserId(String value)
    {
        user_id = value;
    }

    private int CP_Id;
    private Timestamp Done_date_timestamp;
    private String Message_Type;
    private String Request_Id;
    private Timestamp Submit_date_timestamp;
    private String command_code;
    private String done_date;
    private BigDecimal id;
    private String info;
    private String mobile_operator;
    private String process_result;
    private String service_id;
    private String submit_date;
    private int total_segments;
    private String user_id;
}
