// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MobileBuffer.java

package com.vasc.smpp.gateway;


class MobileBufferInfo
{

    public MobileBufferInfo(String mobile, long moTime, long mtTime, int moCounter, int mtCounter, 
            String msg)
    {
        this.mobile = null;
        mo_Time = 0L;
        mt_Time = 0L;
        mo_Counter = 0;
        mt_Counter = 0;
        this.msg = "";
        this.mobile = mobile;
        mo_Time = moTime;
        mt_Time = mtTime;
        mo_Counter = moCounter;
        mt_Counter = mtCounter;
        this.msg = msg;
    }

    public MobileBufferInfo(String mobile, long moTime, long mtTime, int moCounter, int mtCounter)
    {
        this.mobile = null;
        mo_Time = 0L;
        mt_Time = 0L;
        mo_Counter = 0;
        mt_Counter = 0;
        msg = "";
        this.mobile = mobile;
        mo_Time = moTime;
        mt_Time = mtTime;
        mo_Counter = moCounter;
        mt_Counter = mtCounter;
    }

    public MobileBufferInfo()
    {
        mobile = null;
        mo_Time = 0L;
        mt_Time = 0L;
        mo_Counter = 0;
        mt_Counter = 0;
        msg = "";
    }

    int mo_Counter;
    long mo_Time;
    String mobile;
    String msg;
    int mt_Counter;
    long mt_Time;
}
