// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Constants.java

package com.vmg.smpp.gateway;


public class Constants
{

    public Constants()
    {
    }

    static final short PORT_RING_TONE = 5505;
    static final short PORT_OPER_LOGO = 5506;
    static final short PORT_CLI_ICON = 5507;
    static final short PORT_PIC_MSG = 5514;
    static final short PORT_WAP_PUSH = 2948;
    static final short PORT_WAP_CONNECTIONLESS = 9200;
    static final short PORT_WAP_BROWSER = -15537;
    static final short PORT_WAP_BSOURCE = -16382;
    static final short PORT_VCARD = 9204;
    static final short PORT_VCALENDAR = 9205;
    static final int CT_TEXT = 0;
    static final int CT_RING_TONE = 1;
    static final int CT_OPER_LOGO = 2;
    static final int CT_CLI_ICON = 3;
    static final int CT_PIC_MSG = 4;
    static final int CT_WAP_SI = 8;
    static final int CT_CUTE_TEXT = 5;
    static final int CT_MMS_NOTIFY = 6;
    static final int CT_WAP_BROWSER = 7;
    static final int CT_TEXT_UTF8 = 15;
    static final int CT_VCARD = 10;
    static final int CT_VCALENDAR = 11;
    static final int CT_DALINK = 13;
    static final int CT_KARAOKE = 14;
    static final int CT_DALINK_VSSA = 16;
    static final int CT_MC_8000 = 17;
    static final int CT_VALUE_MIN = 0;
    static final int CT_VALUE_MAX = 20;
    static final int MSG_NOT_SENT = 0;
    static final int MSG_SENT_OK = 1;
    static final int MSG_SENT_FAILT = 4;
    static final int MSG_DELIVERED = 2;
    static final int MSG_UNDELIVERED = 3;
    static final int MSG_OVER_MAX_MT = 5;
    static final int MSG_NOT_RESEND_MT = 5;
    static final int MT_PUSH = 0;
    static final int MT_RESP_VALID = 1;
    static final int MT_RESP_VALID_MORE = 11;
    static final int MT_RESP_INVALID = 2;
    static final int MT_RESP_INVALID_PREFIX = 21;
    static final int MT_RESP_INVALID_SYNTAX = 22;
    static final int MT_RESP_INVALID_MSISDN = 23;
    static final int MT_RESP_CONTENT_NOT_FOUND = 24;
    static final int MT_RESP_GAME_OVER = 25;
    static final int MT_RESP_GET_MARK = 3;
    static final int MT_RESP_OVER_MAX_MO = 4;
    static final int MT_RESP_OVER_MAX_MT = 5;
    public static final int USERID_FORMAT_INTERNATIONAL = 0;
    public static final int USERID_FORMAT_NATIONAL_ZERO = 1;
    public static final int USERID_FORMAT_NATIONAL_NINE = 2;
    public static final int SERVICEID_FORMAT_INTERNATIONAL = 10;
    public static final int SERVICEID_FORMAT_REGIONAL = 11;
    public static final int SERVICEID_FORMAT_SHORTCODE = 12;
    static final int DN_SUCCESS = 0;
    static final int DN_FAILT = 1;
    public static final long MIN_TIME_BETWEEN_MO = 30L;
    public static final int MAX_MO_PER_DAY = 101;
    public static final int MAX_MT_PER_DAY = 301;
    public static final int MAX_CDR_PER_DAY = 101;
}
