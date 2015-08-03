// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FtpDataSocket.java

package com.vasc.ftp;

import com.vasc.smpp.gateway.Preference;
import java.io.*;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

// Referenced classes of package com.vasc.ftp:
//            Ftp, FtpControlSocket, FtpInterpret, FtpContext

final class FtpDataSocket
{

    FtpDataSocket(Ftp client)
        throws IOException
    {
        dataserver = null;
        data = null;
        context = null;
        control = null;
        if(client.isConnected())
        {
            control = client.control;
            context = client.getContext();
        } else
        {
            throw new IOException("Data: CreateSocket, No Connection!");
        }
    }

    void close()
        throws IOException
    {
        if(data != null)
            data.close();
        data = null;
        if(control.isConnected())
        {
            if(!control.completeCommand(FtpInterpret.getReplies("data-done")))
            {
                control.executeCommand("ABOR");
                throw new IOException("Data: CloseSocket, Transfer Aborted!");
            }
        } else
        {
            throw new IOException("Data: CloseSocket, No Connection!");
        }
        try
        {
            if(dataserver != null)
                dataserver.close();
        }
        finally
        {
            dataserver = null;
        }
        break MISSING_BLOCK_LABEL_226;
        Exception exception1;
        exception1;
        data = null;
        if(control.isConnected())
        {
            if(!control.completeCommand(FtpInterpret.getReplies("data-done")))
            {
                control.executeCommand("ABOR");
                throw new IOException("Data: CloseSocket, Transfer Aborted!");
            }
        } else
        {
            throw new IOException("Data: CloseSocket, No Connection!");
        }
        try
        {
            if(dataserver != null)
                dataserver.close();
        }
        finally
        {
            dataserver = null;
        }
        throw exception1;
        return;
    }

    String getConnect()
        throws UnknownHostException
    {
        short port = (short)dataserver.getLocalPort();
        return InetAddress.getLocalHost().getHostAddress().replace('.', ',') + "," + port / 256 + "," + port % 256;
    }

    String getConnect(String reply)
        throws NumberFormatException
    {
        if(reply == null)
            throw new NumberFormatException("Null Reply!\n");
        int begin = reply.indexOf('(');
        int end = reply.indexOf(')');
        if(begin != -1 && end != -1 && begin < end)
            return reply.substring(begin + 1, end);
        else
            throw new NumberFormatException("Invalid Reply!\n" + reply);
    }

    String getConnectAddress(String connect)
        throws NumberFormatException
    {
        int s4 = -1;
        for(int i = 0; i < 4; i++)
            if((s4 = connect.indexOf(',', s4 + 1)) == -1)
                throw new NumberFormatException("Misformated Reply! " + i + ":" + s4 + " " + connect);

        return connect.substring(0, s4).replace(',', '.');
    }

    int getConnectPort(String connect)
        throws NumberFormatException, NoSuchElementException
    {
        int s4 = -1;
        for(int i = 0; i < 4; i++)
            if((s4 = connect.indexOf(',', s4 + 1)) == -1)
                throw new NumberFormatException("Misformated Reply! " + i + ":" + s4 + " " + connect);

        StringTokenizer tokenizer = new StringTokenizer(connect.substring(s4 + 1), ",");
        return Integer.parseInt(tokenizer.nextToken()) * 256 + Integer.parseInt(tokenizer.nextToken());
    }

    InputStream getInputStream(String commandline, char type)
        throws IOException
    {
        if(data == null)
            openDataSocket(commandline, type);
        return data.getInputStream();
    }

    OutputStream getOutputStream(String commandline, char type)
        throws IOException
    {
        if(data == null)
            openDataSocket(commandline, type);
        return data.getOutputStream();
    }

    void openActiveDataSocket(String commandline, char type)
        throws IOException
    {
        if(control.isConnected())
        {
            try
            {
                control.executeCommand("TYPE " + type);
                dataserver = new ServerSocket(0);
                dataserver.setSoTimeout(20000);
                control.executeCommand("PORT " + getConnect());
                synchronized(control)
                {
                    control.writeCommand(commandline);
                    data = dataserver.accept();
                    data.setSoTimeout(60000);
                    if(!control.completeCommand(FtpInterpret.getReplies(commandline)))
                        throw new IOException(control.replyOfCommand());
                }
            }
            catch(SocketException e)
            {
                throw new IOException("Data: OpenSocket, Socket Error!\n" + e);
            }
            catch(IOException e)
            {
                throw new IOException("Data: OpenSocket, IO Error!\n" + e);
            }
            catch(Exception e)
            {
                throw new IOException("Data: OpenSocket, Permission Denied!\n" + e);
            }
        } else
        {
            throw new IOException("Data: OpenSocket, No Connection!");
        }
    }

    void openDataSocket(String commandline, char type)
        throws IOException
    {
        if(context.getActiveSocketMode())
            openActiveDataSocket(commandline, type);
        else
        if("EVN".equals(Preference.mobileOperator))
            openActiveDataSocket(commandline, type);
        else
            openPassiveDataSocket(commandline, type);
    }

    void openPassiveDataSocket(String commandline, char type)
        throws IOException
    {
        if(control.isConnected())
        {
            try
            {
                control.executeCommand("TYPE " + type);
                control.executeCommand("PASV");
                String connect = getConnect(control.replyOfCommand());
                String address = getConnectAddress(connect);
                int port = getConnectPort(connect);
                data = new Socket(address, port);
                data.setSoTimeout(60000);
                if(!control.executeCommand(commandline))
                    throw new IOException(control.replyOfCommand());
            }
            catch(NoSuchElementException e)
            {
                throw new IOException("Data: OpenSocket, Invalid Format!\n" + e);
            }
            catch(NumberFormatException e)
            {
                throw new IOException("Data: OpenSocket, Invalid Format!\n" + e);
            }
            catch(SocketException e)
            {
                throw new IOException("Data: OpenSocket, Socket Error!\n" + e);
            }
            catch(IOException e)
            {
                throw new IOException("Data: OpenSocket, IO Error!\n" + e);
            }
            catch(Exception e)
            {
                throw new IOException("Data: OpenSocket, Permission Denied?\n" + e);
            }
        } else
        {
            throw new IOException("Data: OpenSocket, No Connection!");
        }
    }

    FtpContext context;
    private FtpControlSocket control;
    private Socket data;
    private ServerSocket dataserver;
}
