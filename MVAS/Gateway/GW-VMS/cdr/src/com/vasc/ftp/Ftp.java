// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Ftp.java

package com.vasc.ftp;

import com.vasc.ftp.io.CoSource;
import java.io.IOException;

// Referenced classes of package com.vasc.ftp:
//            FtpContext, FtpControlSocket, FtpConnect, FtpInterpret

public final class Ftp
    implements CoSource
{

    public Ftp()
    {
        context = new FtpContext();
        control = new FtpControlSocket(context);
    }

    public void abort()
    {
        disconnect();
    }

    public boolean cd(String directory)
    {
        return control.executeCommand("CWD " + directory);
    }

    public boolean cdup()
    {
        return control.executeCommand("CDUP");
    }

    public boolean chmod(String filename, String mode)
    {
        return control.executeCommand("SITE CHMOD " + mode + " " + filename);
    }

    public boolean command(String commandline)
    {
        return control.manualCommand(commandline);
    }

    public boolean connect(String server, int port)
        throws IOException
    {
        if(!isConnected() && server != null)
        {
            if(control.connect(server, port))
            {
                if(!control.completeCommand(FtpInterpret.getReplies("login-done")))
                {
                    printlog("< Can't obtain welcome message from host! >");
                    control.disconnect();
                    return false;
                } else
                {
                    return true;
                }
            } else
            {
                return false;
            }
        } else
        {
            return false;
        }
    }

    public boolean connect(FtpConnect connect)
        throws IOException
    {
        if(connect(connect.getHostName(), connect.getPortNum()))
            if(login(connect.getUserName(), connect.getPassWord()))
            {
                String pathname = connect.getPathName();
                if(connect.getPathName().length() > 0)
                    cd(connect.getPathName());
            } else
            {
                disconnect();
            }
        return isConnected();
    }

    public void disconnect()
    {
        control.disconnect();
    }

    public FtpContext getContext()
    {
        return context;
    }

    public String host()
        throws IOException
    {
        if(isConnected())
            return control.server;
        else
            throw new IOException("Ctrl: No Connection!");
    }

    public boolean isConnected()
    {
        return control.isConnected();
    }

    public boolean login(String username, String password)
        throws IOException
    {
        if(control.executeCommand("USER " + username))
        {
            if(control.executeCommand("PASS " + password))
            {
                syst();
                return true;
            } else
            {
                printlog("< Can't login to host. >");
                return false;
            }
        } else
        {
            printlog("< Can't login to host. >");
            return false;
        }
    }

    public boolean mkdir(String directory)
    {
        return control.executeCommand("MKD " + directory);
    }

    public boolean mv(String oldfilename, String newfilename)
    {
        if(control.executeCommand("RNFR " + oldfilename))
            return control.executeCommand("RNTO " + newfilename);
        else
            return false;
    }

    void printerr(Exception exception)
    {
        context.printerr(exception);
    }

    void printlog(String message)
    {
        context.printlog(message);
    }

    public String pwd()
        throws IOException
    {
        String directory;
        String replyline;
        if(!isConnected())
            break MISSING_BLOCK_LABEL_63;
        directory = null;
        control.executeCommand("PWD");
        replyline = control.replyOfCommand();
        directory = replyline.substring(replyline.indexOf('"') + 1, replyline.lastIndexOf('"'));
        break MISSING_BLOCK_LABEL_61;
        StringIndexOutOfBoundsException e;
        e;
        throw new IOException("Ctrl: PWD, Invalid Format!");
        return directory;
        throw new IOException("Ctrl: PWD, No Connection!");
    }

    public boolean rm(String filename)
    {
        return control.executeCommand("DELE " + filename);
    }

    public boolean rmdir(String directory)
    {
        return control.executeCommand("RMD " + directory);
    }

    public String syst()
        throws IOException
    {
        if(isConnected())
        {
            control.executeCommand("SYST");
            String system = control.replyOfCommand();
            getContext().setServerSystemMode(1);
            if(system != null && system.toUpperCase().indexOf("WINDOWS") >= 0)
            {
                getContext().setServerSystemMode(2);
                printlog("< File: Setting 'WIN' Server Mode >");
            }
            return system;
        } else
        {
            throw new IOException("Ctrl: PWD, No Connection!");
        }
    }

    public static final int PORT = 21;
    private FtpContext context;
    FtpControlSocket control;
}
