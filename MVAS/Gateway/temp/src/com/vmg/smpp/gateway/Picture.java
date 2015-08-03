// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Picture.java

package com.vmg.smpp.gateway;

import java.io.*;
import org.smpp.util.ByteBuffer;

// Referenced classes of package com.vmg.smpp.gateway:
//            EMSException

public class Picture
{

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public String getText()
    {
        return text;
    }

    public byte[] getData()
    {
        return data;
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
        buffer.appendByte((byte)0);
        buffer.appendByte((byte)width);
        buffer.appendByte((byte)height);
        buffer.appendByte((byte)1);
        buffer.appendBytes(data);
        encoded = buffer;
        return true;
    }

    public void setWidth(int w)
    {
        width = w;
    }

    public void setHeigth(int h)
    {
        height = h;
    }

    public void setText(String t)
    {
        text = t;
    }

    public void setData(byte b[])
    {
        data = b;
    }

    public Picture()
    {
        width = 72;
        height = 28;
        text = "";
        data = null;
        encoded = null;
    }

    public Picture(String text, byte data[])
    {
        width = 72;
        height = 28;
        this.text = "";
        this.data = null;
        encoded = null;
        this.text = text;
        this.data = data;
    }

    public Picture(String filename)
        throws EMSException
    {
        this.width = 72;
        this.height = 28;
        this.text = "";
        this.data = null;
        encoded = null;
        if(filename == null)
            throw new EMSException("File name is not set");
        filename = filename.toLowerCase();
        int fileType = 0;
        if(filename.endsWith(".npm"))
            fileType = 2;
        else
        if(filename.endsWith(".otb"))
            fileType = 1;
        else
            throw new EMSException("Invalid Nokia Picture Message file");
        int width = 0;
        int height = 0;
        byte data[] = (byte[])null;
        try
        {
            ByteBuffer buf = loadByteBuffer(filename);
            switch(fileType)
            {
            case 2: // '\002'
                if(!"NPM".equals(buf.removeCString()))
                    throw new EMSException("File " + filename + " is not NPM file!");
                byte textLength = buf.removeByte();
                String text = buf.removeCString();
                if(text != null && textLength > 0 && text.length() != textLength)
                    throw new EMSException("<Text Length> field = " + textLength + " but <text> field = " + text);
                width = buf.removeByte();
                height = buf.removeByte();
                if(width != 72 || height != 28)
                    throw new EMSException("Invalid width (" + width + ") or height(" + height + ")");
                buf.removeByte();
                buf.removeByte();
                buf.removeByte();
                data = buf.removeBuffer((width * height) / 8).getBuffer();
                setText(text);
                setData(data);
                break;

            case 1: // '\001'
                if(buf.removeByte() != 0)
                    throw new EMSException("First byte of .otb file is invalid, expected 0");
                width = buf.removeByte();
                height = buf.removeByte();
                if(width != 72 || height != 28)
                    throw new EMSException("Invalid width (" + width + ") or height(" + height + ")");
                buf.removeByte();
                data = buf.removeBuffer((width * height) / 8).getBuffer();
                setData(data);
                break;

            default:
                System.out.println("Picture: Invalid fileType!");
                break;
            }
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
            Picture pic = new Picture("test.otb");
            pic.encode();
            System.out.println(pic.getEncoded().length());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    static final int PIC_TYPE_OTB = 1;
    static final int PIC_TYPE_NPM = 2;
    static final int PIC_WIDTH_DEFAULT = 72;
    static final int PIC_HEIGHT_DEFAULT = 28;
    private int width;
    private int height;
    private String text;
    private byte data[];
    private ByteBuffer encoded;
}
