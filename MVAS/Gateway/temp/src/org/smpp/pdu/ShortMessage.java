// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ShortMessage.java

package org.smpp.pdu;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import org.smpp.SmppObject;
import org.smpp.debug.Debug;
import org.smpp.debug.Event;
import org.smpp.util.*;
import sun.io.CharToByteConverter;

// Referenced classes of package org.smpp.pdu:
//            ByteData, WrongLengthOfStringException, PDUException

public class ShortMessage extends ByteData
{

    public ShortMessage(int maxLength)
    {
        minLength = 0;
        this.maxLength = 0;
        message = null;
        encoding = null;
        length = 0;
        messageData = null;
        this.maxLength = maxLength;
    }

    public ShortMessage(int minLength, int maxLength)
    {
        this.minLength = 0;
        this.maxLength = 0;
        message = null;
        encoding = null;
        length = 0;
        messageData = null;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public void setData(ByteBuffer buffer)
        throws PDUException, NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException
    {
        byte messageData[] = null;
        int length = 0;
        if(buffer != null)
        {
            messageData = buffer.getBuffer();
            length = messageData != null ? messageData.length : 0;
            ByteData.checkString(minLength, length, maxLength);
        }
        message = null;
        this.messageData = messageData;
        this.length = length;
    }

    public ByteBuffer getData()
    {
        ByteBuffer buffer = null;
        buffer = new ByteBuffer(messageData);
        return buffer;
    }

    public void setMessage(String message)
        throws WrongLengthOfStringException
    {
        try
        {
            setMessage(message, "ASCII");
        }
        catch(UnsupportedEncodingException e) { }
    }

    public void setMessage(String message, String encoding)
        throws WrongLengthOfStringException, UnsupportedEncodingException
    {
        try
        {
            ByteData.checkString(message, minLength, maxLength, encoding);
        }
        catch(WrongLengthOfStringException ex)
        {
            throw new WrongLengthOfStringException("ShortMessage.setMessage():" + message + "-->" + ex.getMessage());
        }
        if(message != null)
        {
            try
            {
                messageData = message.getBytes(encoding);
            }
            catch(UnsupportedEncodingException e)
            {
                SmppObject.debug.write("encoding " + encoding + " not supported. Exception " + e);
                SmppObject.event.write(e, "encoding " + encoding + " not supported");
                throw e;
            }
            this.message = message;
            length = messageData.length;
            this.encoding = encoding;
        } else
        {
            this.message = null;
            messageData = null;
            this.encoding = encoding;
            length = 0;
        }
    }

    public void setEncoding(String encoding)
        throws UnsupportedEncodingException
    {
        message = new String(messageData, encoding);
        this.encoding = encoding;
    }

    public String getMessage()
    {
        String useEncoding = encoding == null ? "ASCII" : encoding;
        String theMessage = null;
        try
        {
            theMessage = getMessage(useEncoding);
        }
        catch(UnsupportedEncodingException e) { }
        return theMessage;
    }

    public String getMessage(String encoding)
        throws UnsupportedEncodingException
    {
        String message = null;
        if(messageData != null)
            if(encoding != null && this.encoding != null && encoding.equals(this.encoding))
            {
                if(this.message == null)
                    this.message = new String(messageData, encoding);
                message = this.message;
            } else
            if(encoding != null)
                message = new String(messageData, encoding);
            else
                message = new String(messageData);
        return message;
    }

    public int getLength()
    {
        return messageData.length;
    }

    public String getEncoding()
    {
        return encoding;
    }

    public static boolean encodingSuported(String encoding)
    {
        boolean supported = true;
        try
        {
            CharToByteConverter.getConverter(encoding);
        }
        catch(UnsupportedEncodingException e)
        {
            supported = false;
        }
        return supported;
    }

    public String debugString()
    {
        String dbgs = "(sm: ";
        if(encoding != null)
        {
            dbgs = dbgs + "enc: ";
            dbgs = dbgs + encoding;
            dbgs = dbgs + " ";
        }
        dbgs = dbgs + "msg: ";
        dbgs = dbgs + getMessage();
        dbgs = dbgs + ") ";
        return dbgs;
    }

    public static void main(String args[])
    {
        ShortMessage sm = new ShortMessage(300);
        try
        {
            sm.setMessage("060504");
            System.out.println(sm.getMessage());
            System.out.println(new String(sm.getData().getBuffer()));
        }
        catch(WrongLengthOfStringException ex)
        {
            ex.printStackTrace();
        }
    }

    int minLength;
    int maxLength;
    String message;
    String encoding;
    int length;
    byte messageData[];
}
