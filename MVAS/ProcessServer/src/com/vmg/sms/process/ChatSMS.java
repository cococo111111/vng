// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ChatSMS.java

package com.vmg.sms.process;

import java.util.ArrayList;
import java.util.Collection;

// Referenced classes of package com.vmg.sms.process:
//            ContentAbstract, MsgObject, Keyword

public class ChatSMS extends ContentAbstract
{

    public ChatSMS()
    {
    }

    protected Collection getMessages(MsgObject msgObject, Keyword keyword)
        throws Exception
    {
        Collection messages = new ArrayList();
        messages.add(new MsgObject(msgObject));
        return messages;
    }
}
