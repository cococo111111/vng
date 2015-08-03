// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CoLoad.java

package com.vasc.ftp.io;

import com.vasc.ftp.ui.CoConsole;
import java.io.*;

// Referenced classes of package com.vasc.ftp.io:
//            CoLoad, CoFile

final class CoDataLoad extends CoLoad
{

    CoDataLoad(CoFile dst, CoFile src, CoConsole console)
        throws IOException
    {
        cbuf = new byte[4096];
        ibuf = null;
        obuf = null;
        this.console = console;
        ibuf = new BufferedInputStream(src.getInputStream());
        obuf = new BufferedOutputStream(dst.getOutputStream());
        break MISSING_BLOCK_LABEL_162;
        IOException e;
        e;
        if(ibuf == null)
            console.print("CoDataLoad< Can't obtain INPUT STREAM for '" + src.getName() + "'! >");
        else
        if(obuf == null)
            console.print("CoDataLoad < Can't obtain OUTPUT STREAM for '" + dst.getName() + "'! >");
        close();
        throw e;
        return;
    }

    public void close()
        throws IOException
    {
        IOException ex = null;
        while(ibuf != null || obuf != null) 
        {
            try
            {
                if(ibuf != null)
                {
                    InputStream i = ibuf;
                    ibuf = null;
                    i.close();
                }
                if(obuf != null)
                {
                    OutputStream o = obuf;
                    obuf = null;
                    o.close();
                }
            }
            catch(IOException e)
            {
                ex = e;
            }
        }
        if(ex != null)
        {
            throw ex;
        } else
        {
            return;
        }
    }

    public int transfer()
        throws IOException
    {
        int len;
        if((len = ibuf.read(cbuf, 0, 4096)) > 0)
            obuf.write(cbuf, 0, len);
        return len;
    }

    private byte cbuf[];
    private InputStream ibuf;
    private OutputStream obuf;
}
