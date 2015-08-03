// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Testvtc.java

package com.services.vng;

import com.vmg.sms.process.*;
import com.vmg.sms.sender.SendMT2VTC;
import java.util.ArrayList;
import java.util.Collection;

public class Testvtc extends ContentAbstract
{

    public Testvtc()
    {
    }

    protected Collection getMessages(MsgObject msgObject, Keyword keyword)
        throws Exception
    {
        Collection messages = new ArrayList();
        msgObject.setUsertext("Message:" + msgObject.getUsertext());
        msgObject.setMsgtype(1);
        SendMT2VTC send2vtc = new SendMT2VTC();
        send2vtc.sendMessages(new MsgObject(msgObject), keyword);
        return null;
    }
}
