// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WrongDateFormatException.java

package org.smpp.pdu;


// Referenced classes of package org.smpp.pdu:
//            PDUException

public class WrongDateFormatException extends PDUException
{

    public WrongDateFormatException()
    {
        super("Date must be either null or of format YYMMDDhhmmsstnnp");
    }

    public WrongDateFormatException(String dateStr)
    {
        super("Date must be either null or of format YYMMDDhhmmsstnnp and not " + dateStr + ".");
    }

    public WrongDateFormatException(String dateStr, String msg)
    {
        super("Invalid date " + dateStr + ": " + msg);
    }
}
