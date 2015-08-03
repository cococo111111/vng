// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MobileBuffer.java

package com.vmg.smpp.gateway;


class MobileBufferInfo
{

    public MobileBufferInfo()
    {
        mobile = null;
        mo_Time = 0L;
        mt_Time = 0L;
        mo_Counter = 0;
        mt_Counter = 0;
        cdr_Counter = 0;
        msg = "";
    }

    public MobileBufferInfo(String mobile, long moTime, long mtTime, int moCounter, int mtCounter, 
            int cdrCounter)
    {
        this.mobile = null;
        mo_Time = 0L;
        mt_Time = 0L;
        mo_Counter = 0;
        mt_Counter = 0;
        cdr_Counter = 0;
        msg = "";
        this.mobile = mobile;
        mo_Time = moTime;
        mt_Time = mtTime;
        mo_Counter = moCounter;
        mt_Counter = mtCounter;
        cdr_Counter = cdrCounter;
    }

    public MobileBufferInfo(String mobile, long moTime, long mtTime, int moCounter, int mtCounter, 
            int cdrCounter, String msg)
    {
        this.mobile = null;
        mo_Time = 0L;
        mt_Time = 0L;
        mo_Counter = 0;
        mt_Counter = 0;
        cdr_Counter = 0;
        this.msg = "";
        this.mobile = mobile;
        mo_Time = moTime;
        mt_Time = mtTime;
        mo_Counter = moCounter;
        mt_Counter = mtCounter;
        cdr_Counter = cdrCounter;
        this.msg = msg;
    }

    String mobile;
    long mo_Time;
    long mt_Time;
    int mo_Counter;
    int mt_Counter;
    int cdr_Counter;
    String msg;
}
