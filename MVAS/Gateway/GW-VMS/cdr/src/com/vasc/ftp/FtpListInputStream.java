// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FtpListInputStream.java

package com.vasc.ftp;

import java.io.IOException;

// Referenced classes of package com.vasc.ftp:
//            FtpInputStream, FtpDataSocket, FtpFile, Ftp, 
//            FtpContext

public final class FtpListInputStream extends FtpInputStream
{

    public FtpListInputStream(FtpFile dir)
        throws IOException
    {
        String command = null;
        if(!dir.client.cd(dir.toString()))
            throw new IOException("File: cd command failed!\ncd " + dir);
        dir.path = dir.client.pwd();
        data = new FtpDataSocket(dir.client);
        switch(data.context.getListCommandMode())
        {
        case 1: // '\001'
            command = "LIST";
            break;

        case 2: // '\002'
            command = "NLST";
            break;

        case 4: // '\004'
            command = "NLST -F";
            break;

        case 3: // '\003'
            command = "NLST -p";
            break;

        case 5: // '\005'
            command = "NLST -la";
            break;

        default:
            throw new IOException("File: Invalid List Command Mode!");
        }
        stream = data.getInputStream(command, 'A');
    }
}
