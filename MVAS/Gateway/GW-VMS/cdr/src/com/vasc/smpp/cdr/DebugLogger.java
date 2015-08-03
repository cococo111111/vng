// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DebugLogger.java

package com.vasc.smpp.cdr;

import java.io.*;
import java.util.Calendar;

public class DebugLogger
{

    public DebugLogger()
    {
    }

    public static boolean getEnabled()
    {
        return enabled;
    }

    public static void log(String message)
    {
        if(!enabled)
        {
            return;
        } else
        {
            Calendar now = Calendar.getInstance();
            String nowString = now.get(5) + "/" + (now.get(2) + 1) + "/" + now.get(1) + " " + now.get(11) + ":" + now.get(12) + ":" + now.get(13);
            message = nowString + " : PRIMROSE_DBG : " + message;
            System.out.println(message);
            return;
        }
    }

    public static void printStackTrace(Throwable t)
    {
        if(!enabled)
        {
            return;
        } else
        {
            t.printStackTrace(System.out);
            return;
        }
    }

    public static void setEnabled(boolean bEnabled)
    {
        enabled = bEnabled;
    }

    private static boolean enabled = false;

    static 
    {
        Process p = null;
        p = Runtime.getRuntime().exec("env");
        break MISSING_BLOCK_LABEL_33;
        Exception e;
        e;
        p = Runtime.getRuntime().exec("cmd /c set PRIMROSE_DEBUG");
        if(p != null)
        {
            p.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String s;
            for(; (s = br.readLine()) != null; Nop)
            {
                if(s.toUpperCase().indexOf("PRIMROSE_DEBUG=TRUE") <= -1)
                    continue;
                setEnabled(true);
                break;
            }

        }
        break MISSING_BLOCK_LABEL_108;
        Exception e;
        e;
        e.printStackTrace();
    }
}
