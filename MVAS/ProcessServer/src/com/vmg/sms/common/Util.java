// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Util.java

package com.vmg.sms.common;

import com.vmg.sms.process.Logger;
import java.io.IOException;

public class Util
{

    public Util()
    {
        String logfile = "log_process/${yyyy-MM-dd}.log";
        String loglevel = "info,warn,error,crisis";
        logger = new Logger();
        try
        {
            logger.setLogWriter(logfile);
        }
        catch(IOException ioexception) { }
        logger.setLogLevel(loglevel);
    }

    public Util(String logfile, String loglevel)
    {
        logger = new Logger();
        try
        {
            logger.setLogWriter(logfile);
        }
        catch(IOException ioexception) { }
        logger.setLogLevel(loglevel);
    }

    public static Logger logger = null;

}
