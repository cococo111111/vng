// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HexaTool.java

package com.vmg.common;


public class HexaTool
{

    public HexaTool()
    {
    }

    public static String toHexString(byte b[])
    {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for(int i = 0; i < b.length; i++)
        {
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0xf]);
        }

        return sb.toString();
    }

    public static byte[] fromHexString(String s)
        throws IllegalArgumentException
    {
        int stringLength = s.length();
        if((stringLength & 1) != 0)
            throw new IllegalArgumentException("fromHexString requires an even number of hex characters");
        byte b[] = new byte[stringLength / 2];
        int i = 0;
        for(int j = 0; i < stringLength; j++)
        {
            int high = charToNibble(s.charAt(i));
            int low = charToNibble(s.charAt(i + 1));
            b[j] = (byte)(high << 4 | low);
            i += 2;
        }

        return b;
    }

    private static int charToNibble(char c)
    {
        if('0' <= c && c <= '9')
            return c - 48;
        if('a' <= c && c <= 'f')
            return (c - 97) + 10;
        if('A' <= c && c <= 'F')
            return (c - 65) + 10;
        else
            throw new IllegalArgumentException("Invalid hex character: " + c);
    }

    static char hexChar[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'A', 'B', 'C', 'D', 'E', 'F'
    };

}
