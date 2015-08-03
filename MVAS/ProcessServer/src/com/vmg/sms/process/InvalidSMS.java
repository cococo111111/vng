// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InvalidSMS.java

package com.vmg.sms.process;

import java.util.ArrayList;
import java.util.Collection;

// Referenced classes of package com.vmg.sms.process:
//            ContentAbstract, MsgObject, Keyword

public class InvalidSMS extends ContentAbstract
{

    public InvalidSMS()
    {
    }

    protected Collection getMessages(MsgObject msgObject, Keyword keyword)
        throws Exception
    {
        Collection messages = new ArrayList();
        msgObject.setUsertext("Tin nhan sai cu phap");
        msgObject.setKeyword("INV");
        msgObject.setMsgtype(2);
        messages.add(new MsgObject(msgObject));
        return messages;
    }
}
