// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PictureMessage.java

package com.vmg.smpp.gateway;

import java.io.*;
import org.smpp.util.ByteBuffer;

// Referenced classes of package com.vmg.smpp.gateway:
//            EMSException

public class PictureMessage
{

    public void setText(String t)
    {
        text = t;
    }

    public void setOTB(byte b[])
    {
        OTB = b;
    }

    public void setEncoded(byte b[])
    {
        encoded = new ByteBuffer(b);
    }

    public ByteBuffer getEncoded()
    {
        return encoded;
    }

    public boolean encode()
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendByte((byte)48);
        buffer.appendByte((byte)0);
        if(text != null)
        {
            buffer.appendShort((short)text.length());
            buffer.appendString(text);
        } else
        {
            buffer.appendShort((short)0);
        }
        buffer.appendByte((byte)2);
        buffer.appendShort((short)256);
        buffer.appendBytes(OTB);
        encoded = buffer;
        return true;
    }

    public PictureMessage()
    {
        text = "";
        OTB = null;
        encoded = null;
    }

    public PictureMessage(String text, byte data[])
    {
        this.text = "";
        OTB = null;
        encoded = null;
        this.text = text;
        OTB = data;
    }

    public PictureMessage(String filename)
        throws EMSException
    {
        text = "";
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
            PictureMessage pic = new PictureMessage();
            ByteBuffer b = pic.loadByteBuffer("test.otb");
            pic.setOTB(b.getBuffer());
            pic.setText("Tho test");
            pic.encode();
            System.out.println(pic.getEncoded().getHexDump());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    static final int PIC_WIDTH_DEFAULT = 72;
    static final int PIC_HEIGHT_DEFAULT = 28;
    private String text;
    private byte OTB[];
    private ByteBuffer encoded;
}
