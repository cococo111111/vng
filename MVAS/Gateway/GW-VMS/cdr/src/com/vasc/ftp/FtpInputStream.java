// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FtpInputStream.java

package com.vasc.ftp;

import com.vasc.ftp.ui.CoConsole;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package com.vasc.ftp:
//            FtpDataSocket, Ftp, FtpFile, FtpContext, 
//            FtpConnect

public class FtpInputStream extends InputStream
{

    public FtpInputStream(FtpFile file, FtpConnect connect, CoConsole console)
        throws IOException
    {
        client = new Ftp();
        if(client.connect(connect))
        {
            if(console != null)
                client.getContext().setConsole(console);
            file = new FtpFile(file.toString(), client);
            data = new FtpDataSocket(file.client);
            stream = data.getInputStream("RETR " + file, file.getDataType());
        } else
        {
            throw new IOException("Connect failed.");
        }
    }

    public FtpInputStream(FtpFile file)
        throws IOException
    {
        client = null;
        data = new FtpDataSocket(file.client);
        stream = data.getInputStream("RETR " + file, file.getDataType());
    }

    FtpInputStream()
    {
    }

    public int available()
        throws IOException
    {
        return stream.available();
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
                    InputStream i = stream;
                    stream = null;
                    i.close();
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

    public synchronized void mark(int readlimit)
    {
        stream.mark(readlimit);
    }

    public boolean markSupported()
    {
        return stream.markSupported();
    }

    public int read(byte b[], int off, int len)
        throws IOException
    {
        return stream.read(b, off, len);
    }

    public int read(byte b[])
        throws IOException
    {
        return stream.read(b);
    }

    public int read()
        throws IOException
    {
        return stream.read();
    }

    public synchronized void reset()
        throws IOException
    {
        stream.reset();
    }

    public long skip(long n)
        throws IOException
    {
        return stream.skip(n);
    }

    Ftp client;
    FtpDataSocket data;
    InputStream stream;
}
