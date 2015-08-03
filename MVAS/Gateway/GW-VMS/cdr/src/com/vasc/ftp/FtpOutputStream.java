// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FtpOutputStream.java

package com.vasc.ftp;

import com.vasc.ftp.ui.CoConsole;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package com.vasc.ftp:
//            FtpDataSocket, Ftp, FtpFile, FtpContext, 
//            FtpConnect

public final class FtpOutputStream extends OutputStream
{

    public FtpOutputStream(FtpFile file, FtpConnect connect, CoConsole console, boolean append)
        throws IOException
    {
        client = new Ftp();
        if(client.connect(connect))
        {
            if(console != null)
                client.getContext().setConsole(console);
            file = new FtpFile(file.toString(), client);
            data = new FtpDataSocket(file.client);
            if(append)
                stream = data.getOutputStream("APPE " + file, file.getDataType());
            else
                stream = data.getOutputStream("STOR " + file, file.getDataType());
        } else
        {
            throw new IOException("Connect failed.");
        }
    }

    public FtpOutputStream(FtpFile file, FtpConnect connect, CoConsole console)
        throws IOException
    {
        this(file, connect, console, false);
    }

    public FtpOutputStream(FtpFile file, boolean append)
        throws IOException
    {
        client = null;
        data = new FtpDataSocket(file.client);
        if(append)
            stream = data.getOutputStream("APPE " + file, file.getDataType());
        else
            stream = data.getOutputStream("STOR " + file, file.getDataType());
    }

    public FtpOutputStream(FtpFile file)
        throws IOException
    {
        this(file, false);
    }

    public void close()
        throws IOException
    {
        IOException x = null;
        while(stream != null || data != null || client != null) 
            try
            {
                if(stream != null)
                {
                    OutputStream o = stream;
                    stream = null;
                    o.close();
                }
                if(data != null)
                {
                    FtpDataSocket d = data;
                    data = null;
                    d.close();
                }
                if(client != null)
                {
                    Ftp c = client;
                    client = null;
                    c.disconnect();
                }
            }
            catch(IOException e)
            {
                x = e;
            }
        if(x != null)
        {
            throw x;
        } else
        {
            return;
        }
    }

    public void flush()
        throws IOException
    {
        stream.flush();
    }

    public void write(byte b[], int off, int len)
        throws IOException
    {
        stream.write(b, off, len);
    }

    public void write(byte b[])
        throws IOException
    {
        stream.write(b);
    }

    public void write(int b)
        throws IOException
    {
        stream.write(b);
    }

    private Ftp client;
    private FtpDataSocket data;
    private OutputStream stream;
}
