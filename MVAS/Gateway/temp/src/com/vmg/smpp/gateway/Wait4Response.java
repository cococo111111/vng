// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Wait4Response.java

package com.vmg.smpp.gateway;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Wait4Response
{

    public Wait4Response()
    {
        emsId = null;
        time = new Timestamp(System.currentTimeMillis());
    }

    public Wait4Response(BigDecimal emsId)
    {
        this.emsId = emsId;
        time = new Timestamp(System.currentTimeMillis());
    }

    public Wait4Response(BigDecimal emsId, int totalSegments, int seqNum)
    {
        this.emsId = emsId;
        this.totalSegments = totalSegments;
        this.seqNum = seqNum;
        time = new Timestamp(System.currentTimeMillis());
    }

    public boolean isLastSegment()
    {
        return seqNum == totalSegments;
    }

    public boolean isTimeout()
    {
        long currTime = System.currentTimeMillis();
        return currTime - time.getTime() > 60000L;
    }

    static final long RESPONSE_TIMEOUT = 60000L;
    BigDecimal emsId;
    int totalSegments;
    int seqNum;
    Timestamp time;
}
