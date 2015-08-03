// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SmppObject.java

package org.smpp;

import org.smpp.debug.Debug;
import org.smpp.debug.DefaultDebug;
import org.smpp.debug.DefaultEvent;
import org.smpp.debug.Event;

public class SmppObject
{

    public SmppObject()
    {
    }

    public static void setDebug(Debug dbg)
    {
        debug = dbg;
    }

    public static void setEvent(Event evt)
    {
        event = evt;
    }

    public static Debug getDebug()
    {
        return debug;
    }

    public static Event getEvent()
    {
        return event;
    }

    protected static Debug debug = new DefaultDebug();
    protected static Event event = new DefaultEvent();
    public static final int DRXTX = 1;
    public static final int DRXTXD = 2;
    public static final int DRXTXD2 = 3;
    public static final int DSESS = 4;
    public static final int DPDU = 5;
    public static final int DPDUD = 6;
    public static final int DCOM = 7;
    public static final int DCOMD = 8;
    public static final int DUTL = 9;
    public static final int DUTLD = 10;

}
