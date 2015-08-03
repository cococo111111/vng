// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultEvent.java

package org.smpp.debug;

import java.io.*;

// Referenced classes of package org.smpp.debug:
//            Event

public class DefaultEvent
    implements Event
{

    public DefaultEvent()
    {
        active = false;
    }

    public void write(String msg)
    {
        if(active)
            System.out.println(msg);
    }

    public void write(Exception e, String msg)
    {
        if(active)
        {
            StringWriter stackOutString = new StringWriter();
            PrintWriter stackOut = new PrintWriter(stackOutString);
            e.printStackTrace(stackOut);
            try
            {
                write("Exception: " + stackOutString.toString() + " " + msg);
            }
            catch(Exception ex)
            {
                System.err.println("Event log failure " + ex);
            }
        }
    }

    public void activate()
    {
        active = true;
    }

    public void deactivate()
    {
        active = false;
    }

    private boolean active;
}
