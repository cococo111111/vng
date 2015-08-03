// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CoOpen.java

package com.vasc.ftp.io;

import com.vasc.ftp.ui.CoConsole;
import java.io.*;

// Referenced classes of package com.vasc.ftp.io:
//            CoFile

public interface CoOpen
{

    public abstract CoConsole getConsole();

    public abstract char getDataType();

    public abstract InputStream getInputStream()
        throws IOException;

    public abstract OutputStream getOutputStream()
        throws IOException;

    public abstract OutputStream getOutputStream(boolean flag)
        throws IOException;

    public abstract CoFile newFileChild(String s);

    public abstract CoFile newFileRename(String s);
}
