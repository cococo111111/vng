// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Constants.java

package com.vasc.smpp.gateway;


public class Constants
{

    public Constants()
    {
    }

    public static final int MAX_MO_PER_DAY = 100;
    public static final int MAX_MT_PER_DAY = 100;
    public static final long MIN_TIME_BETWEEN_MO = 5L;
    static final int MSG_DELIVERED = 2;
    static final int MSG_NOT_SENT = 0;
    static final int MSG_OVER_MAX_MT = 5;
    static final int MSG_SENT_FAILT = 4;
    static final int MSG_SENT_OK = 1;
    static final int MSG_UNDELIVERED = 3;
    static final int MT_PUSH = 0;
    static final int MT_RESP_CONTENT_NOT_FOUND = 24;
    static final int MT_RESP_GAME_OVER = 25;
    static final int MT_RESP_GET_MARK = 3;
    static final int MT_RESP_INVALID = 2;
    static final int MT_RESP_INVALID_MSISDN = 23;
    static final int MT_RESP_INVALID_PREFIX = 21;
    static final int MT_RESP_INVALID_SYNTAX = 22;
    static final int MT_RESP_OVER_MAX_MO = 4;
    static final int MT_RESP_OVER_MAX_MT = 5;
    public static final int MT_RESP_VALID = 1;
    public static final int SERVICEID_FORMAT_INTERNATIONAL = 10;
    public static final int SERVICEID_FORMAT_REGIONAL = 11;
    public static final int SERVICEID_FORMAT_SHORTCODE = 12;
    public static final int USERID_FORMAT_INTERNATIONAL = 0;
    public static final int USERID_FORMAT_NATIONAL_NINE = 2;
    public static final int USERID_FORMAT_NATIONAL_ZERO = 1;
}
