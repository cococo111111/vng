// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ShutdownInterceptor.java

package com.vmg.sms.process;

import java.io.PrintStream;

// Referenced classes of package com.vmg.sms.process:
//            ConsoleSRV

public class ShutdownInterceptor extends Thread
{

    public ShutdownInterceptor(ConsoleSRV app)
    {
        this.app = app;
    }

    public void run()
    {
        System.out.println("Call the shutdown routine");
        app.windowClosing();
    }

    private ConsoleSRV app;
}
