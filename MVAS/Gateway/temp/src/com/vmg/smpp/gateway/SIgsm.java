// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SIgsm.java

package com.vmg.smpp.gateway;

import com.vmg.common.Utilities;
import java.io.PrintStream;
import org.smpp.util.ByteBuffer;

// Referenced classes of package com.vmg.smpp.gateway:
//            Gateway

public class SIgsm
{

    public SIgsm()
    {
        URL = null;
        message = null;
        encodedSi = null;
    }

    public String getURL()
    {
        return URL;
    }

    public String getMessage()
    {
        return message;
    }

    public void setURL(String URL)
    {
        this.URL = URL;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public ByteBuffer getEncodedSI()
    {
        return encodedSi;
    }

    public void encodeSI()
    {
        if(URL == null)
        {
            System.out.println("encodeSI: URL not set.");
            Utilities _tmp = Gateway.util;
            Utilities.log(getClass().getName(), "encodeSI: URL not set.");
            return;
        }
        if(message == null || "".equals(message))
        {
            System.out.println("encodeSI: Message not set.");
            Utilities _tmp1 = Gateway.util;
            Utilities.log(getClass().getName(), "encodeSI: Message not set.");
            return;
        } else
        {
            ByteBuffer buffer = new ByteBuffer();
            buffer.appendByte((byte)1);
            buffer.appendByte((byte)6);
            buffer.appendByte((byte)1);
            buffer.appendByte((byte)-82);
            buffer.appendByte((byte)2);
            buffer.appendByte((byte)5);
            buffer.appendByte((byte)106);
            buffer.appendByte((byte)0);
            buffer.appendByte((byte)69);
            buffer.appendByte((byte)-58);
            buffer.appendBuffer(encodeURL(URL));
            buffer.appendByte((byte)3);
            buffer.appendCString(message);
            buffer.appendByte((byte)1);
            buffer.appendByte((byte)1);
            encodedSi = buffer;
            return;
        }
    }

    private ByteBuffer encodeURL(String url)
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendByte((byte)12);
        buffer.appendByte((byte)3);
        String urlPath = url.substring(7);
        System.out.println("url=" + urlPath);
        buffer.appendCString(urlPath);
        buffer.appendByte((byte)7);
        buffer.appendByte((byte)1);
        return buffer;
    }

    public static void main(String args[])
    {
        SIgsm si = new SIgsm();
        si.setURL("http://dalink.vietnamnet.vn/si/book.mid");
        si.setMessage("VietnamNet");
        si.encodeSI();
        System.out.print(si.getEncodedSI().getHexDump());
    }

    private String URL;
    private String message;
    private ByteBuffer encodedSi;
}
