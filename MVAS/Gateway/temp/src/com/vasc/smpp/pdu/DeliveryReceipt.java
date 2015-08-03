// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeliveryReceipt.java

package com.vasc.smpp.pdu;

import java.math.BigDecimal;

public class DeliveryReceipt
{

    public DeliveryReceipt()
    {
        id = null;
        user_id = null;
        service_id = null;
        mobile_operator = null;
        info = null;
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

    public String getUserId()
    {
        return user_id;
    }

    public void setId(BigDecimal id)
    {
        this.id = id;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public void setMobileOperator(String mo)
    {
        mobile_operator = mo;
    }

    public void setServiceId(String serviceId)
    {
        service_id = serviceId;
    }

    public void setUserId(String userId)
    {
        user_id = userId;
    }

    private BigDecimal id;
    private String info;
    private String mobile_operator;
    private String service_id;
    private String user_id;
}
