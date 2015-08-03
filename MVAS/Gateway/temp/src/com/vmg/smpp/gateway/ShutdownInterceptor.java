// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ShutdownInterceptor.java

package com.vmg.smpp.gateway;

import java.io.PrintStream;

// Referenced classes of package com.vmg.smpp.gateway:
//            Gateway

public class ShutdownInterceptor extends Thread
{

    public ShutdownInterceptor(Gateway app)
    {
        this.app = app;
    }

    public void run()
    {
        System.out.println("Call the shutdown routine");
        Gateway.exit();
    }

    private Gateway app;
}
