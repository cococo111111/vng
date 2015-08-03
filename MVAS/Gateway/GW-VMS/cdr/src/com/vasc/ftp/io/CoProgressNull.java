// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CoLoad.java

package com.vasc.ftp.io;

import com.vasc.ftp.ui.CoProgress;

// Referenced classes of package com.vasc.ftp.io:
//            CoFile

class CoProgressNull
    implements CoProgress
{

    CoProgressNull()
    {
    }

    public boolean isAborted()
    {
        return false;
    }

    public void setDelay(long l)
    {
    }

    public void setFile(CoFile cofile, CoFile cofile1)
    {
    }

    public void setFile(CoFile cofile)
    {
    }

    public void setProgress(int i)
    {
    }
}
