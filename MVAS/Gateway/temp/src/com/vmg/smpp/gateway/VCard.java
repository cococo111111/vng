// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VCard.java

package com.vmg.smpp.gateway;

import java.io.PrintStream;
import org.smpp.util.ByteBuffer;

public class VCard
{

    public String getName()
    {
        return name;
    }

    public void setName(String value)
    {
        name = value;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String value)
    {
        tel = value;
    }

    public ByteBuffer getEncoded()
    {
        return vcard;
    }

    public void encode()
    {
        ByteBuffer buffer = new ByteBuffer();
        String strCard = "BEGIN:VCARD\nVERSION:2.1\nN:" + name + "\n" + "TEL;PREF:" + tel + "\n" + "END:VCARD";
        for(int i = 0; i < strCard.length(); i++)
            buffer.appendByte((byte)strCard.charAt(i));

        vcard = buffer;
    }

    public VCard()
    {
        name = "TUANHN;NGUYEN";
        tel = "0904060008";
        vcard = null;
    }

    public static void main(String args[])
    {
        VCard vcard = new VCard();
        vcard.encode();
        System.out.println(vcard.getEncoded().getHexDump());
        System.out.println(vcard.getEncoded().length());
    }

    private String name;
    private String tel;
    private ByteBuffer vcard;
}
