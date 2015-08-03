// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NotSynchronousException.java

package org.smpp;


// Referenced classes of package org.smpp:
//            SmppException, Session

public class NotSynchronousException extends SmppException
{

    public NotSynchronousException()
    {
        session = null;
    }

    public NotSynchronousException(Session session)
    {
        this.session = null;
        this.session = session;
    }

    public Session getSession()
    {
        return session;
    }

    private Session session;
}
