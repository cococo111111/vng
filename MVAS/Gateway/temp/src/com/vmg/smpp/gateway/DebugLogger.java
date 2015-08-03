// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DebugLogger.java

package com.vmg.smpp.gateway;

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

    public static void setEnabled(boolean bEnabled)
    {
        enabled = bEnabled;
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

    private static boolean enabled = false;

    static 
    {
        try
        {
            Process p = null;
            try
            {
                p = Runtime.getRuntime().exec("env");
            }
            catch(Exception e)
            {
                p = Runtime.getRuntime().exec("cmd /c set PRIMROSE_DEBUG");
            }
            if(p != null)
            {
                p.waitFor();
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while((line = br.readLine()) != null) 
                    if(line.toUpperCase().indexOf("PRIMROSE_DEBUG=TRUE") > -1)
                    {
                        setEnabled(true);
                        break;
                    }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
