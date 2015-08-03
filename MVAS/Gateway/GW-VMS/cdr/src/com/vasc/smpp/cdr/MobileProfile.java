// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MobileProfile.java

package com.vasc.smpp.cdr;

import java.sql.Timestamp;

public class MobileProfile
{

    public MobileProfile()
    {
        soTB84 = null;
        lastChargedTime = null;
        lastChargedShortCode = null;
    }

    public String getLastChargedShortCode()
    {
        return lastChargedShortCode;
    }

    public Timestamp getLastChargedTime()
    {
        return lastChargedTime;
    }

    public String getSoTB84()
    {
        return soTB84;
    }

    public void setLastChargedShortCode(String value)
    {
        lastChargedShortCode = value;
    }

    public void setLastChargedTime(Timestamp value)
    {
        lastChargedTime = value;
    }

    public void setSoTB84(String value)
    {
        soTB84 = value;
    }

    private String lastChargedShortCode;
    private Timestamp lastChargedTime;
    private String soTB84;
}
