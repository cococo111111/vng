// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ByteData.java

package org.smpp.pdu;

import java.io.UnsupportedEncodingException;
import java.text.*;
import org.smpp.SmppObject;
import org.smpp.debug.Debug;
import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            WrongLengthOfStringException, WrongDateFormatException, IntegerOutOfRangeException, PDUException, 
//            ValueNotSetException

public abstract class ByteData extends SmppObject
{

    public abstract void setData(ByteBuffer bytebuffer)
        throws PDUException, NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException;

    public abstract ByteBuffer getData()
        throws ValueNotSetException;

    public ByteData()
    {
    }

    protected static void checkString(String string, int max)
        throws WrongLengthOfStringException
    {
        checkString(string, 0, max);
    }

    protected static void checkString(String string, int max, String encoding)
        throws WrongLengthOfStringException, UnsupportedEncodingException
    {
        checkString(string, 0, max, encoding);
    }

    protected static void checkString(String string, int min, int max)
        throws WrongLengthOfStringException
    {
        int length = string != null ? string.length() : 0;
        checkString(min, length, max);
    }

    protected static void checkString(String string, int min, int max, String encoding)
        throws WrongLengthOfStringException, UnsupportedEncodingException
    {
        byte stringBytes[] = string.getBytes(encoding);
        int length = stringBytes != null ? stringBytes.length : 0;
        checkString(min, length, max);
    }

    protected static void checkString(int min, int length, int max)
        throws WrongLengthOfStringException
    {
        if(length < min || length > max)
            throw new WrongLengthOfStringException(min, max, length);
        else
            return;
    }

    protected static void checkCString(String string, int max)
        throws WrongLengthOfStringException
    {
        checkCString(string, 1, max);
    }

    protected static void checkCString(String string, int min, int max)
        throws WrongLengthOfStringException
    {
        int count = string != null ? string.length() + 1 : 1;
        if(count < min || count > max)
            throw new WrongLengthOfStringException(min, max, count);
        else
            return;
    }

    protected static void checkDate(String dateStr)
        throws WrongDateFormatException
    {
        int count = dateStr != null ? dateStr.length() + 1 : 1;
        if(count != 1 && count != 17)
            throw new WrongDateFormatException(dateStr);
        if(count == 1 || !libraryCheckDateFormat)
            return;
        char locTime = dateStr.charAt(dateStr.length() - 1);
        if("+-R".lastIndexOf(locTime) == -1)
            throw new WrongDateFormatException(dateStr, "time difference relation indicator incorrect; should be +, - or R and is " + locTime);
        int formatLen = "yyMMddHHmmss".length();
        String dateJavaStr = dateStr.substring(0, formatLen);
        java.util.Date date = null;
        synchronized(dateFormatter)
        {
            try
            {
                if(locTime == 'R')
                    Long.parseLong(dateJavaStr);
                else
                    date = dateFormatter.parse(dateJavaStr);
            }
            catch(ParseException e)
            {
                SmppObject.debug.write("Exception parsing absolute date " + dateStr + " " + e);
                throw new WrongDateFormatException(dateStr, "format of absolute date-time incorrect");
            }
            catch(NumberFormatException e)
            {
                SmppObject.debug.write("Exception parsing relative date " + dateStr + " " + e);
                throw new WrongDateFormatException(dateStr, "format of relative date-time incorrect");
            }
        }
        String tenthsOfSecStr = dateStr.substring(formatLen, formatLen + 1);
        int tenthsOfSec = 0;
        try
        {
            tenthsOfSec = Integer.parseInt(tenthsOfSecStr);
        }
        catch(NumberFormatException e)
        {
            throw new WrongDateFormatException(dateStr, "non-numeric tenths of seconds " + tenthsOfSecStr);
        }
        String timeDiffStr = dateStr.substring(formatLen + 1, formatLen + 3);
        int timeDiff = 0;
        try
        {
            timeDiff = Integer.parseInt(timeDiffStr);
        }
        catch(NumberFormatException e)
        {
            throw new WrongDateFormatException(dateStr, "non-numeric time difference " + timeDiffStr);
        }
        if(timeDiff < 0 || timeDiff > 48)
            throw new WrongDateFormatException(dateStr, "time difference is incorrect; should be between 00-48 and is " + timeDiffStr);
        else
            return;
    }

    protected static void checkRange(int min, int val, int max)
        throws IntegerOutOfRangeException
    {
        if(val < min || val > max)
            throw new IntegerOutOfRangeException(min, max, val);
        else
            return;
    }

    protected static short decodeUnsigned(byte signed)
    {
        if(signed >= 0)
            return (short)signed;
        else
            return (short)(256 + (short)signed);
    }

    protected static int decodeUnsigned(short signed)
    {
        if(signed >= 0)
            return signed;
        else
            return 0x10000 + signed;
    }

    protected static byte encodeUnsigned(short positive)
    {
        return (byte)positive;
    }

    protected static short encodeUnsigned(int positive)
    {
        return (short)positive;
    }

    public String debugString()
    {
        return new String("");
    }

    private static final String SMPP_TIME_DATE_FORMAT = "yyMMddHHmmss";
    private static SimpleDateFormat dateFormatter;
    private static boolean libraryCheckDateFormat = true;

    static 
    {
        dateFormatter = new SimpleDateFormat("yyMMddHHmmss");
        dateFormatter.setLenient(false);
    }
}
