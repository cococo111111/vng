// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FtpControlSocket.java

package com.vasc.ftp;

import java.io.*;
import java.net.*;

// Referenced classes of package com.vasc.ftp:
//            FtpContext, FtpInterpret

final class FtpControlSocket
{

    FtpControlSocket(FtpContext context)
    {
        control = null;
        in = null;
        out = null;
        this.context = null;
        replyline = null;
        server = null;
        this.context = context;
    }

    synchronized boolean completeCommand(String replies[])
    {
        boolean done = false;
        replyline = readReply();
        done = FtpInterpret.startsWith(replyline, replies);
        break MISSING_BLOCK_LABEL_80;
        IOException e;
        e;
        if(e.getMessage() != null)
            context.printlog("< " + e.getMessage() + " >");
        else
            context.printerr(e);
        return done;
    }

    synchronized boolean connect(String server, int port)
    {
        boolean done;
        done = false;
        if(control != null)
            break MISSING_BLOCK_LABEL_205;
        context.printlog("Getting host by name: " + server);
        InetAddress addr = InetAddress.getByName(server);
        context.printlog("Connecting to host: " + addr.getHostAddress());
        control = new Socket(addr, port);
        control.setSoTimeout(60000);
        in = new BufferedReader(new InputStreamReader(control.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(control.getOutputStream()));
        this.server = server;
        done = true;
        break MISSING_BLOCK_LABEL_204;
        UnknownHostException e;
        e;
        context.printlog("< Ctrl: Can't resolve host address! >");
        break MISSING_BLOCK_LABEL_204;
        IOException e;
        e;
        disconnect();
        context.printlog("< Ctrl: Can't obtain connection to host! >");
        break MISSING_BLOCK_LABEL_204;
        Exception e;
        e;
        context.printlog("< Ctrl: Permission denied! >");
        return done;
    }

    synchronized void disconnect()
    {
        IOException e;
        while(in != null || out != null) 
        {
            try
            {
                if(in != null)
                {
                    Reader r = in;
                    in = null;
                    r.close();
                }
                if(out != null)
                {
                    Writer w = out;
                    out = null;
                    w.close();
                }
            }
            // Misplaced declaration of an exception variable
            catch(IOException e)
            {
                context.printerr(e);
            }
        }
        if(control == null)
            break MISSING_BLOCK_LABEL_142;
        control.close();
        break MISSING_BLOCK_LABEL_141;
        e;
        context.printerr(e);
        break MISSING_BLOCK_LABEL_141;
        local;
        control = null;
        context.printlog("< Ctrl: Disconnected! >");
        JVM INSTR ret 4;
        server = null;
        return;
    }

    synchronized boolean executeCommand(String commandline)
    {
        if(writeCommand(commandline))
            return completeCommand(FtpInterpret.getReplies(commandline));
        else
            return false;
    }

    boolean isConnected()
    {
        return control != null;
    }

    synchronized boolean manualCommand(String commandline)
    {
        if(!FtpInterpret.allowManualExecution(commandline))
        {
            context.printlog("< Ctrl: Command, No Manual Execution! >");
            return false;
        } else
        {
            return executeCommand(commandline);
        }
    }

    private synchronized String readLine()
        throws IOException
    {
        String line;
        line = null;
        if(in == null)
            break MISSING_BLOCK_LABEL_91;
        line = in.readLine();
        System.out.println("doc trong socket:");
        break MISSING_BLOCK_LABEL_61;
        IOException e;
        e;
        e.printStackTrace();
        throw new IOException("Ctrl: Read, Error!\n" + e);
        if(line == null)
        {
            System.out.println("FtpControlSocket >>line =null roi:");
            disconnect();
            throw new IOException("Ctrl: Read, End Of File!");
        }
        break MISSING_BLOCK_LABEL_101;
        throw new IOException("Ctrl: Read, No connection!");
        return line;
    }

    private synchronized String readReply()
        throws IOException
    {
        String line = null;
        do
        {
            line = readLine();
            context.printlog(line);
        } while(line.length() == 0 || line.indexOf("-") == 3 || "0123456789".indexOf(line.charAt(0)) < 0);
        return line;
    }

    String replyOfCommand()
        throws IOException
    {
        if(replyline != null)
            return replyline;
        else
            throw new IOException("Ctrl: No Reply!");
    }

    synchronized boolean writeCommand(String commandline)
    {
        boolean done;
        if(commandline.startsWith("PASS"))
            context.printlog("Ftp> PASS ******");
        else
            context.printlog("Ftp> " + commandline);
        if(!FtpInterpret.allowExecution(commandline))
        {
            context.printlog("< Ctrl: Command, Not Implemented! >");
            return false;
        }
        done = true;
        replyline = null;
        writeLine(commandline.trim());
        break MISSING_BLOCK_LABEL_143;
        IOException e;
        e;
        done = false;
        if(e.getMessage() != null)
            context.printlog("< " + e.getMessage() + " >");
        else
            context.printerr(e);
        return done;
    }

    private synchronized void writeLine(String line)
        throws IOException
    {
        if(out == null)
            break MISSING_BLOCK_LABEL_76;
        out.write(line + "\r\n");
        out.flush();
        break MISSING_BLOCK_LABEL_72;
        IOException e;
        e;
        throw new IOException("Ctrl: Write, failed!\n" + e);
        break MISSING_BLOCK_LABEL_86;
        throw new IOException("Ctrl: Write, No connection!");
        return;
    }

    private FtpContext context;
    private Socket control;
    private BufferedReader in;
    private BufferedWriter out;
    private String replyline;
    String server;
}
