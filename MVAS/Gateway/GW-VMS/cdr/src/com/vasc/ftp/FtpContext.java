// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FtpContext.java

package com.vasc.ftp;

import com.vasc.ftp.ui.CoConsole;
import java.io.PrintStream;

// Referenced classes of package com.vasc.ftp:
//            FtpSetting

public class FtpContext extends FtpSetting
{

    FtpContext()
    {
        console = new CoConsole() {

            public void print(String message)
            {
                System.out.println(message);
            }

        }
;
    }

    public synchronized CoConsole getConsole()
    {
        return console;
    }

    public String[] getTextFilter()
    {
        return textfilter;
    }

    public void printerr(Exception exception)
    {
        System.out.println("Thread: " + Thread.currentThread().getName());
        System.out.println("Exception:");
        exception.printStackTrace();
    }

    public synchronized void printlog(String message)
    {
        if(console != null)
            console.print(message);
    }

    public synchronized void setConsole(CoConsole console)
    {
        this.console = console;
    }

    public void setTextFilter(String textfilter[])
    {
        this.textfilter = textfilter;
    }

    private CoConsole console;
    private String textfilter[] = {
        ".TXT", ".HTM", ".HTML", ".SHTML", ".CSS", ".JS", ".PL", ".PHP", ".H", ".C", 
        ".HPP", ".CPP", ".JAVA", ".SQL", ".4GL", ".BAT", ".SH", ".AWK"
    };
}
