// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FtpConnect.java

package com.vasc.ftp;

import java.net.*;
import java.util.StringTokenizer;

public final class FtpConnect
{

    public FtpConnect()
    {
        hostname = "ftp.netscape.com";
        pathname = "";
        username = "anonymous";
        password = "";
        portnum = 21;
    }

    public String getConnect()
        throws MalformedURLException
    {
        return (new URL("ftp", hostname, portnum, pathname)).toString() + "|-user|" + username;
    }

    public String getHostName()
    {
        return hostname;
    }

    public String getPassWord()
    {
        return password;
    }

    public String getPathName()
    {
        return pathname;
    }

    public int getPortNum()
    {
        return portnum;
    }

    public String getUserName()
    {
        return username;
    }

    public static FtpConnect newConnect(String args[])
    {
        FtpConnect connect;
        int i;
        connect = new FtpConnect();
        i = 0;
          goto _L1
_L3:
        if(!args[i].startsWith("ftp:"))
            continue; /* Loop/switch isn't completed */
        try
        {
            URL argi = new URL(args[i]);
            connect.hostname = argi.getHost();
            String pathname = argi.getFile();
            if(pathname.compareTo("/") != 0)
                connect.pathname = pathname;
            int portnum = argi.getPort();
            if(portnum != -1)
                connect.portnum = portnum;
        }
        catch(MalformedURLException e)
        {
            for(i = 0; i < args.length - 1; i++)
            {
                if(args[i].compareTo("-user") != 0)
                    continue;
                connect.username = args[i + 1];
                break;
            }

            break; /* Loop/switch isn't completed */
        }
        for(i = 0; i < args.length - 1; i++)
        {
            if(args[i].compareTo("-user") != 0)
                continue;
            connect.username = args[i + 1];
            break;
        }

        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        for(i = 0; i < args.length - 1; i++)
        {
            if(args[i].compareTo("-user") != 0)
                continue;
            connect.username = args[i + 1];
            break;
        }

        break; /* Loop/switch isn't completed */
        i++;
_L1:
        if(i < args.length) goto _L3; else goto _L2
_L2:
        return connect;
    }

    public static FtpConnect newConnect(String args)
    {
        if(args != null)
        {
            StringTokenizer argt = new StringTokenizer(args, "|");
            int n = argt.countTokens();
            String argn[] = new String[n];
            for(int i = 0; i < n; i++)
                argn[i] = argt.nextToken();

            return newConnect(argn);
        } else
        {
            return new FtpConnect();
        }
    }

    public String saveConnect(String hostname, String filename)
        throws MalformedURLException
    {
        return URLEncoder.encode((new URL("http", hostname, filename)).toString() + "?config=" + getConnect());
    }

    public void setHostName(String hostname)
    {
        this.hostname = hostname;
    }

    public void setPassWord(String password)
    {
        this.password = password;
    }

    public void setPathName(String pathname)
    {
        this.pathname = pathname;
    }

    public void setPortNum(int portnum)
    {
        this.portnum = portnum;
    }

    public void setUserName(String username)
    {
        this.username = username;
    }

    private String hostname;
    private String password;
    private String pathname;
    private int portnum;
    private String username;
}
