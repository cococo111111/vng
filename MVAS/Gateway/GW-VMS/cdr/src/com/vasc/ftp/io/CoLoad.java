// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CoLoad.java

package com.vasc.ftp.io;

import com.vasc.ftp.ui.CoConsole;
import com.vasc.ftp.ui.CoProgress;
import java.io.IOException;

// Referenced classes of package com.vasc.ftp.io:
//            CoProgressNull, CoTextLoad, CoDataLoad, CoFile

public abstract class CoLoad
{

    CoLoad()
    {
    }

    abstract void close()
        throws IOException;

    public static boolean copy(CoFile dst, CoFile file, CoProgress progress, CoConsole console)
    {
        boolean done;
        CoLoad load;
        done = false;
        load = null;
        int increment;
        load = open(dst, file, console);
        increment = 0;
          goto _L1
_L4:
        if(progress.isAborted())
        {
            done = false;
            break; /* Loop/switch isn't completed */
        }
        increment = load.transfer();
          goto _L2
        IOException e;
        e;
        progress.setDelay(60000L);
        increment = 0;
_L2:
        if(increment != -1)
            progress.setProgress(increment);
_L1:
        if(increment != -1) goto _L4; else goto _L3
_L3:
        done = true;
        break MISSING_BLOCK_LABEL_135;
        IOException e;
        e;
        break MISSING_BLOCK_LABEL_135;
        local;
        try
        {
            if(load != null)
                load.close();
        }
        catch(IOException e) { }
        JVM INSTR ret 9;
        return done;
    }

    public static boolean copy(CoFile dst, CoFile file, CoProgress progress)
    {
        CoConsole console = null;
        if(dst.getConsole() != null)
            console = dst.getConsole();
        else
        if(file.getConsole() != null)
            console = file.getConsole();
        else
        if(console == null)
            console = new CoConsole() {

                public void print(String s)
                {
                }

            }
;
        return copy(dst, file, progress, console);
    }

    public static boolean copy(CoFile dst, CoFile file)
    {
        return copy(dst, file, ((CoProgress) (new CoProgressNull())));
    }

    public static boolean copy(CoFile to, CoFile files[], CoProgress progress)
    {
        boolean done = true;
        if(files == null) goto _L2; else goto _L1
_L1:
        int i = 0;
          goto _L3
_L4:
        if(files[i].isFile())
        {
            if(progress.isAborted())
            {
                done = false;
                break; /* Loop/switch isn't completed */
            }
            CoFile tofile = to.newFileChild(files[i].getName());
            progress.setFile(tofile, files[i]);
            if(!copy(tofile, files[i], progress))
            {
                done = false;
                break; /* Loop/switch isn't completed */
            }
            continue; /* Loop/switch isn't completed */
        }
        if(!files[i].isDirectory())
            continue; /* Loop/switch isn't completed */
        if(progress.isAborted())
        {
            done = false;
            break; /* Loop/switch isn't completed */
        }
        CoFile todir = to.newFileChild(files[i].getName());
        progress.setFile(todir, files[i]);
        todir.mkdir();
        if(!copy(to.newFileChild(files[i].getName()), files[i].listCoFiles(), progress))
        {
            done = false;
            break; /* Loop/switch isn't completed */
        }
        try
        {
            continue; /* Loop/switch isn't completed */
        }
        catch(SecurityException e)
        {
            done = false;
        }
        break; /* Loop/switch isn't completed */
        i++;
_L3:
        if(i < files.length) goto _L4; else goto _L2
_L2:
        return done;
    }

    public static boolean copy(CoFile to, CoFile files[])
    {
        return copy(to, files, ((CoProgress) (new CoProgressNull())));
    }

    public static boolean delete(CoFile files[], CoProgress progress)
    {
        boolean done = true;
        if(files == null) goto _L2; else goto _L1
_L1:
        int i = 0;
          goto _L3
_L4:
        if(!files[i].isDirectory())
        {
            if(progress.isAborted())
            {
                done = false;
                break; /* Loop/switch isn't completed */
            }
            progress.setFile(files[i]);
            if(!files[i].delete())
            {
                done = false;
                break; /* Loop/switch isn't completed */
            }
            continue; /* Loop/switch isn't completed */
        }
        if(!files[i].isDirectory())
            continue; /* Loop/switch isn't completed */
        if(progress.isAborted())
        {
            done = false;
            break; /* Loop/switch isn't completed */
        }
        progress.setFile(files[i]);
        if(!delete(files[i].listCoFiles(), progress))
        {
            done = false;
            break; /* Loop/switch isn't completed */
        }
        if(!files[i].delete())
        {
            done = false;
            break; /* Loop/switch isn't completed */
        }
        try
        {
            continue; /* Loop/switch isn't completed */
        }
        catch(SecurityException e)
        {
            done = false;
        }
        break; /* Loop/switch isn't completed */
        i++;
_L3:
        if(i < files.length) goto _L4; else goto _L2
_L2:
        return done;
    }

    public static boolean delete(CoFile files[])
    {
        return delete(files, ((CoProgress) (new CoProgressNull())));
    }

    static CoLoad open(CoFile dst, CoFile src, CoConsole console)
        throws IOException
    {
        if(dst.getDataType() == 'A' || src.getDataType() == 'A')
            return new CoTextLoad(dst, src, console);
        else
            return new CoDataLoad(dst, src, console);
    }

    abstract int transfer()
        throws IOException;

    CoConsole console;
}
