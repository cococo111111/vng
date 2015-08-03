// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerPDUEventListener.java

package org.smpp;

import java.util.EventListener;

// Referenced classes of package org.smpp:
//            ServerPDUEvent

public interface ServerPDUEventListener
    extends EventListener
{

    public abstract void handleEvent(ServerPDUEvent serverpduevent);
}
