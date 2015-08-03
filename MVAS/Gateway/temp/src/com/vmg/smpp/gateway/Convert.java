// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Convert.java

package com.vmg.smpp.gateway;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Timestamp;

public final class Convert
{

    public Convert()
    {
    }

    public static final String hexToString(String hex)
    {
        if(hex == null)
        {
            return "";
        } else
        {
            int length = hex.length() & -2;
            StringBuffer result = new StringBuffer(length / 2);
            hexToString(hex, result);
            return result.toString();
        }
    }

    public static final void hexToString(String hex, StringBuffer out)
    {
        if(hex == null)
            return;
        int length = hex.length() & -2;
        for(int pos = 0; pos < length; pos += 2)
        {
            int this_char = 0;
            try
            {
                this_char = Integer.parseInt(hex.substring(pos, pos + 2), 16);
            }
            catch(NumberFormatException numberformatexception) { }
            out.append((char)this_char);
        }

    }

    public static final String stringToHex(String java)
    {
        if(java == null)
        {
            return "";
        } else
        {
            int length = java.length();
            StringBuffer result = new StringBuffer(length * 4);
            stringToHex(java, result);
            return result.toString();
        }
    }

    public static final void stringToHex(String java, StringBuffer out)
    {
        if(java == null)
            return;
        int length = java.length();
        for(int pos = 0; pos < length; pos++)
        {
            int this_char = java.charAt(pos);
            for(int digit = 0; digit < 4; digit++)
            {
                int this_digit = this_char & 0xf000;
                this_char <<= 4;
                this_digit >>= 12;
                if(this_digit >= 10)
                    out.append((char)(this_digit + 87));
                else
                    out.append((char)(this_digit + 48));
            }

        }

    }

    public static final void stringToHex(String java, PrintWriter out)
    {
        if(java == null)
            return;
        int length = java.length();
        for(int pos = 0; pos < length; pos++)
        {
            int this_char = java.charAt(pos);
            for(int digit = 0; digit < 4; digit++)
            {
                int this_digit = this_char & 0xf000;
                this_char <<= 4;
                this_digit >>= 12;
                if(this_digit >= 10)
                    out.print((char)(this_digit + 87));
                else
                    out.print((char)(this_digit + 48));
            }

        }

    }

    public static final boolean isDecimalNumber(String fix_value)
    {
        int fix_size = fix_value.length();
        if(fix_size == 0)
            return false;
        int dotcount = 0;
        for(int fix_pos = 0; fix_pos < fix_size; fix_pos++)
        {
            char probe = fix_value.charAt(fix_pos);
            if((probe < '0' || probe > '9') && (fix_pos != 0 || probe != '-') && (fix_pos <= 0 || probe != '.'))
                return false;
            if(probe == '.' && ++dotcount > 1)
                return false;
        }

        return true;
    }

    public static final int charToInt(char base36)
    {
        if(base36 <= '9')
            if(base36 >= '0')
                return base36 - 48;
            else
                return 0;
        if(base36 < 'a')
            if(base36 <= 'Z')
                return (base36 - 65) + 10;
            else
                return 0;
        if(base36 <= 'z')
            return (base36 - 97) + 10;
        else
            return 0;
    }

    public static final char intToChar(int zeroTo36)
    {
        if(zeroTo36 <= 9)
            if(zeroTo36 >= 0)
                return (char)(zeroTo36 + 48);
            else
                return '\0';
        if(zeroTo36 < 36)
            return (char)((zeroTo36 - 10) + 97);
        else
            return '\0';
    }

    public static final void intToHex(int x, StringBuffer out)
    {
        for(int pos = 0; pos < 8; pos++)
            out.append(intToChar(x >>> 28 - pos * 4 & 0xf));

    }

    public static String addToTime(String Time, long Minutes)
    {
        String result = null;
        try
        {
            result = (new Timestamp(Timestamp.valueOf(Time).getTime() + Minutes * 60000L)).toString();
        }
        catch(RuntimeException RunEx)
        {
            System.out.print(RunEx.toString());
        }
        return result;
    }

    public static void replace(String original, char badGuys[], String goodGuys[], PrintWriter out)
    {
        if(original == null)
            return;
        int startOutputAt = 0;
        int count_rules = badGuys.length;
        int orig_length = original.length();
        for(int pos = 0; pos < orig_length; pos++)
        {
            char probe = original.charAt(pos);
            String appendAfterFlush = null;
            for(int rule = 0; rule < count_rules; rule++)
                if(probe == badGuys[rule])
                    appendAfterFlush = goodGuys[rule];

            if(appendAfterFlush != null)
            {
                out.print(original.substring(startOutputAt, pos));
                out.print(appendAfterFlush);
                startOutputAt = pos + 1;
            }
            if(pos + 1 == orig_length)
                out.print(original.substring(startOutputAt));
        }

    }

    public static final int MAX_CHAR_INT = 35;
}
