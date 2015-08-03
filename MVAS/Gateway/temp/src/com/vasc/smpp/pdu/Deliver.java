// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Deliver.java

package com.vasc.smpp.pdu;

import java.math.BigDecimal;

public class Deliver
{

    public Deliver()
    {
        user_id = null;
        service_id = null;
        mobile_operator = null;
        command_code = null;
        info = null;
        message_id = null;
        seq_number = null;
        command_status = 0;
    }

    public String getCommandCode()
    {
        return command_code;
    }

    public int getCommandStatus()
    {
        return command_status;
    }

    public String getInfo()
    {
        return info;
    }

    public String getMessageId()
    {
        return message_id;
    }

    public String getMobileOperator()
    {
        return mobile_operator;
    }

    public BigDecimal getSeqNumber()
    {
        return seq_number;
    }

    public String getServiceId()
    {
        return service_id;
    }

    public String getUserId()
    {
        return user_id;
    }

    private String rebuildServiceId(String serviceId)
    {
        String temp = serviceId;
        if(temp == null)
            return null;
        if(temp.startsWith("+"))
            temp = temp.substring(1);
        if(temp.startsWith("84") || temp.startsWith("04"))
            temp = temp.substring(2);
        return temp;
    }

    private String removePlusSign(String userId)
    {
        String temp = userId;
        if(temp.startsWith("+"))
            temp = temp.substring(1);
        return temp;
    }

    public void setCommandCode(String cc)
    {
        command_code = cc;
    }

    public void setCommandStatus(int status)
    {
        command_status = status;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public void setMessageId(String msgId)
    {
        message_id = msgId;
    }

    public void setMobileOperator(String mo)
    {
        mobile_operator = mo;
    }

    public void setSeqNumber(BigDecimal seqNum)
    {
        seq_number = seqNum;
    }

    public void setServiceId(String serviceId)
    {
        service_id = rebuildServiceId(serviceId);
    }

    public void setUserId(String userId)
    {
        user_id = removePlusSign(userId);
    }

    private String command_code;
    private int command_status;
    private String info;
    private String message_id;
    private String mobile_operator;
    private BigDecimal seq_number;
    private String service_id;
    private String user_id;
}
