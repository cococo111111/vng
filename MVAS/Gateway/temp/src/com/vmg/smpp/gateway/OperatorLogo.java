// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorLogo.java

package com.vmg.smpp.gateway;

import java.io.*;
import org.smpp.util.ByteBuffer;

// Referenced classes of package com.vmg.smpp.gateway:
//            EMSException

public class OperatorLogo
{

    public void setMobileOperator(String mo)
    {
        mobile_operator = mo;
    }

    public void setOTB(byte b[])
    {
        OTB = b;
    }

    public byte[] getOTB()
    {
        return OTB;
    }

    public ByteBuffer getEncoded()
    {
        return encoded;
    }

    public boolean encode()
        throws EMSException
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendShort((short)21746);
        mobile_operator = mobile_operator.toUpperCase();
        if("VMS".equals(mobile_operator))
            buffer.appendByte((byte)16);
        else
        if("GPC".equals(mobile_operator))
            buffer.appendByte((byte)32);
        else
        if("VIETEL".equals(mobile_operator))
            buffer.appendByte((byte)64);
        else
        if("SFONE".equals(mobile_operator))
            buffer.appendByte((byte)48);
        else
        if("HTC".equals(mobile_operator))
            buffer.appendByte((byte)80);
        else
        if("EVN".equals(mobile_operator))
            buffer.appendByte((byte)96);
        else
            throw new EMSException("Invalid mobile operator");
        buffer.appendBytes(OTB);
        encoded = buffer;
        return true;
    }

    public OperatorLogo()
    {
        mobile_operator = "";
        OTB = null;
        encoded = null;
    }

    public OperatorLogo(String text, byte data[])
    {
        mobile_operator = "";
        OTB = null;
        encoded = null;
        mobile_operator = text;
        OTB = data;
    }

    public OperatorLogo(String filename)
        throws EMSException
    {
        mobile_operator = "";
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

    public static ByteBuffer getMe4RestoringOriginalOne(String mobileOperator, boolean withUDH)
        throws EMSException
    {
        ByteBuffer buffer = new ByteBuffer();
        if(withUDH)
        {
            buffer.appendByte((byte)6);
            buffer.appendByte((byte)5);
            buffer.appendByte((byte)4);
            buffer.appendShort((short)5506);
            buffer.appendShort((short)0);
        }
        buffer.appendByte((byte)48);
        buffer.appendShort((short)0);
        buffer.appendByte((byte)0);
        buffer.appendByte((byte)10);
        buffer.appendByte((byte)0);
        buffer.appendByte((byte)0);
        buffer.appendByte((byte)0);
        buffer.appendByte((byte)1);
        return buffer;
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
            OperatorLogo pic = new OperatorLogo("logo.otb");
            pic.setMobileOperator("VMS");
            pic.encode();
            System.out.println(pic.getEncoded().getHexDump());
            System.out.println(pic.getEncoded().length());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    static final int LOGO_WIDTH_DEFAULT = 72;
    static final int LOGO_HEIGHT_DEFAULT = 14;
    private String mobile_operator;
    private byte OTB[];
    private ByteBuffer encoded;
}
