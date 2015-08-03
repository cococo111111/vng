// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Wait4Report.java

package com.vmg.smpp.gateway;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Wait4Report
{

    public Wait4Report()
    {
        logId = null;
        time = null;
        logId = null;
        time = new Timestamp(System.currentTimeMillis());
    }

    public Wait4Report(BigDecimal logId)
    {
        this.logId = null;
        time = null;
        this.logId = logId;
        time = new Timestamp(System.currentTimeMillis());
    }

    public Wait4Report(BigDecimal logId, Timestamp time)
    {
        this.logId = null;
        this.time = null;
        this.logId = logId;
        this.time = time;
    }

    public boolean isTimeout()
    {
        long currTime = System.currentTimeMillis();
        return currTime - time.getTime() > 0x5265c00L;
    }

    static final long WAIT_REPORT_TIMEOUT = 0x5265c00L;
    BigDecimal logId;
    Timestamp time;
}
