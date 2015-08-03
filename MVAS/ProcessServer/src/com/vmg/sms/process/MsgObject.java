// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MsgObject.java

package com.vmg.sms.process;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class MsgObject
    implements Serializable
{

    public MsgObject(MsgObject msgobject)
    {
        mo_id = -1;
        serviceid = "";
        userid = "";
        keyword = "";
        usertext = "";
        requestid = new BigDecimal(-1D);
        mobileoperator = "";
        cpid = 0;
        msgtype = 1;
        contenttype = 0;
        msgnotes = "";
        serviceid = msgobject.getServiceid();
        userid = msgobject.getUserid();
        keyword = msgobject.getKeyword();
        usertext = msgobject.getUsertext();
        requestid = msgobject.getRequestid();
        tTimes = msgobject.getTTimes();
        mobileoperator = msgobject.getMobileoperator();
        cpid = msgobject.getCpid();
        msgtype = msgobject.getMsgtype();
        contenttype = msgobject.getContenttype();
        msgnotes = msgobject.getMsgnotes();
        mo_id = msgobject.getMo_id();
    }

    public MsgObject(int mo_id, String serviceid, String userid, String keyword, String usertext, BigDecimal requestid, Timestamp tTimes, 
            String mobileoperator, int msgtype, int contenttype)
    {
        this.mo_id = -1;
        this.serviceid = "";
        this.userid = "";
        this.keyword = "";
        this.usertext = "";
        this.requestid = new BigDecimal(-1D);
        this.mobileoperator = "";
        cpid = 0;
        this.msgtype = 1;
        this.contenttype = 0;
        msgnotes = "";
        this.serviceid = serviceid;
        this.userid = userid;
        this.keyword = keyword;
        this.usertext = usertext;
        this.requestid = requestid;
        this.tTimes = tTimes;
        this.mobileoperator = mobileoperator;
        this.msgtype = msgtype;
        this.contenttype = contenttype;
        this.mo_id = mo_id;
    }

    public int getMo_id()
    {
        return mo_id;
    }

    public void setMo_id(int mo_id)
    {
        this.mo_id = mo_id;
    }

    public MsgObject(int mo_id, String serviceid, String userid, String keyword, String usertext, BigDecimal requestid, Timestamp tTimes, 
            String mobileoperator, int msgtype, int contenttype, int cpid, String msgnotes)
    {
        this.mo_id = -1;
        this.serviceid = "";
        this.userid = "";
        this.keyword = "";
        this.usertext = "";
        this.requestid = new BigDecimal(-1D);
        this.mobileoperator = "";
        this.cpid = 0;
        this.msgtype = 1;
        this.contenttype = 0;
        this.msgnotes = "";
        this.serviceid = serviceid;
        this.userid = userid;
        this.keyword = keyword;
        this.usertext = usertext;
        this.requestid = requestid;
        this.tTimes = tTimes;
        this.mobileoperator = mobileoperator;
        this.msgtype = msgtype;
        this.contenttype = contenttype;
        this.cpid = cpid;
        this.msgnotes = msgnotes;
        this.mo_id = mo_id;
    }

    public String getServiceid()
    {
        return serviceid;
    }

    public void setServiceid(String serviceid)
    {
        this.serviceid = serviceid;
    }

    public String getUserid()
    {
        return userid;
    }

    public void setUserid(String userid)
    {
        this.userid = userid;
    }

    public String getKeyword()
    {
        return keyword;
    }

    public String getMsgnotes()
    {
        return msgnotes;
    }

    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }

    public String getUsertext()
    {
        return usertext;
    }

    public void setUsertext(String usertext)
    {
        this.usertext = usertext;
    }

    public BigDecimal getRequestid()
    {
        return requestid;
    }

    public void setRequestid(BigDecimal requestid)
    {
        this.requestid = requestid;
    }

    public Timestamp getTTimes()
    {
        return tTimes;
    }

    public void setTTimes(Timestamp times)
    {
        tTimes = times;
    }

    public String getMobileoperator()
    {
        return mobileoperator;
    }

    public void setMobileoperator(String mobileoperator)
    {
        this.mobileoperator = mobileoperator;
    }

    public int getMsgtype()
    {
        return msgtype;
    }

    public void setMsgtype(int msgtype)
    {
        this.msgtype = msgtype;
    }

    public int getContenttype()
    {
        return contenttype;
    }

    public void setContenttype(int contenttype)
    {
        this.contenttype = contenttype;
    }

    public void setMsgNotes(String msgnotes)
    {
        if("".equals(this.msgnotes))
            this.msgnotes = msgnotes;
        else
            this.msgnotes = this.msgnotes + "@" + msgnotes;
    }

    public int getCpid()
    {
        return cpid;
    }

    public void setCpid(int cpid)
    {
        this.cpid = cpid;
    }

    public void setMsgnotes(String msgnotes)
    {
        this.msgnotes = msgnotes;
    }

    int mo_id;
    String serviceid;
    String userid;
    String keyword;
    String usertext;
    BigDecimal requestid;
    Timestamp tTimes;
    String mobileoperator;
    int cpid;
    int msgtype;
    int contenttype;
    String msgnotes;
}
