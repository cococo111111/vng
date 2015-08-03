// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Cli.java

package com.vmg.smpp.gateway;

import java.io.*;
import org.smpp.util.ByteBuffer;

// Referenced classes of package com.vmg.smpp.gateway:
//            EMSException

public class Cli
{

    public byte[] getOTB()
    {
        return OTB;
    }

    public void setOTB(byte b[])
    {
        OTB = b;
    }

    public ByteBuffer getEncoded()
    {
        return encoded;
    }

    public boolean encode()
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendByte((byte)48);
        buffer.appendBytes(OTB);
        encoded = buffer;
        return true;
    }

    public Cli()
    {
        OTB = null;
        encoded = null;
    }

    public Cli(byte data[])
    {
        OTB = null;
        encoded = null;
        OTB = data;
    }

    public Cli(String filename)
        throws EMSException
    {
        OTB = null;
        encoded = null;
        if(filename == null)
            throw new EMSException("File name is not set");
        filename = filename.toLowerCase();
        if(!filename.endsWith(".otb"))
            throw new EMSException("Invalid OTB file");
        try
        {
            ByteBuffer buf = loadByteBuffer(filename);
            setOTB(buf.getBuffer());
        }
        catch(Exception ex)
        {
            throw new EMSException(ex.getMessage());
        }
    }

    public ByteBuffer loadByteBuffer(String fileName)
        throws IOException
    {
        FileInputStream is = new FileInputStream(fileName);
        byte data[] = new byte[is.available()];
        is.read(data);
        is.close();
        return new ByteBuffer(data);
    }

    public static void main(String args[])
    {
        try
        {
            Cli pic = new Cli("CLI.otb");
            pic.encode();
            System.out.println(pic.getEncoded().getHexDump());
            System.out.println("SIZE: " + pic.getEncoded().length());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    static final int CLI_WIDTH_DEFAULT = 72;
    static final int CLI_HEIGHT_DEFAULT = 14;
    private byte OTB[];
    private ByteBuffer encoded;
}
