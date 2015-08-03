// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CoLoad.java

package com.vasc.ftp.io;

import com.vasc.ftp.ui.CoConsole;
import java.io.*;

// Referenced classes of package com.vasc.ftp.io:
//            CoLoad, CoFile

final class CoTextLoad extends CoLoad
{

    CoTextLoad(CoFile dst, CoFile src, CoConsole console)
        throws IOException
    {
        ibuf = null;
        obuf = null;
        this.console = console;
        ibuf = new BufferedReader(new InputStreamReader(src.getInputStream()));
        obuf = new BufferedWriter(new OutputStreamWriter(dst.getOutputStream()));
        break MISSING_BLOCK_LABEL_167;
        IOException e;
        e;
        if(ibuf == null)
            console.print("CoTextLoad< Can't obtain INPUT STREAM for '" + src.getName() + "'! >");
        else
        if(obuf == null)
            console.print("CoTextLoad< Can't obtain OUTPUT STREAM for '" + dst.getName() + "'! >");
        close();
        throw e;
        return;
    }

    public void close()
        throws IOException
    {
        IOException x = null;
        while(ibuf != null || obuf != null) 
        {
            try
            {
                if(ibuf != null)
                {
                    Reader r = ibuf;
                    ibuf = null;
                    r.close();
                }
                if(obuf != null)
                {
                    Writer w = obuf;
                    obuf = null;
                    w.close();
                }
            }
            catch(IOException e)
            {
                x = e;
            }
        }
        if(x != null)
        {
            throw x;
        } else
        {
            return;
        }
    }

    public int transfer()
        throws IOException
    {
        String line = null;
        if((line = ibuf.readLine()) != null)
        {
            obuf.write(line);
            obuf.newLine();
            return line.length();
        } else
        {
            return -1;
        }
    }

    private BufferedReader ibuf;
    private BufferedWriter obuf;
}
