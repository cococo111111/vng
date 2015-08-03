// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReceiverBase.java

package org.smpp;

import java.io.*;
import org.smpp.debug.Debug;
import org.smpp.pdu.HeaderIncompleteException;
import org.smpp.pdu.MessageIncompleteException;
import org.smpp.pdu.PDU;
import org.smpp.pdu.PDUException;
import org.smpp.pdu.UnknownCommandIdException;
import org.smpp.util.ByteBuffer;
import org.smpp.util.NotEnoughDataInByteBufferException;
import org.smpp.util.ProcessingThread;
import org.smpp.util.Unprocessed;

// Referenced classes of package org.smpp:
//            TimeoutException, SmppObject, Data, Connection

public abstract class ReceiverBase extends ProcessingThread
{

    public ReceiverBase()
    {
        receiveTimeout = 60000L;
    }

    protected abstract void receiveAsync();

    protected abstract PDU tryReceivePDU(Connection connection, PDU pdu)
        throws UnknownCommandIdException, TimeoutException, PDUException, IOException;

    public void process()
    {
        receiveAsync();
    }

    protected final PDU tryReceivePDUWithTimeout(Connection connection, PDU expectedPDU)
        throws UnknownCommandIdException, TimeoutException, PDUException, IOException
    {
        return tryReceivePDUWithTimeout(connection, expectedPDU, getReceiveTimeout());
    }

    protected final PDU tryReceivePDUWithTimeout(Connection connection, PDU expectedPDU, long timeout)
        throws UnknownCommandIdException, TimeoutException, PDUException, IOException
    {
        SmppObject.debug.write(1, "receivePDU: Going to receive response.");
        long startTime = Data.getCurrentTime();
        PDU pdu = null;
        if(timeout == 0L)
            pdu = tryReceivePDU(connection, expectedPDU);
        else
            for(; pdu == null && canContinueReceiving(startTime, timeout); pdu = tryReceivePDU(connection, expectedPDU));
        if(pdu != null)
            SmppObject.debug.write(1, "Got pdu " + pdu.debugString());
        return pdu;
    }

    protected final PDU receivePDUFromConnection(Connection connection, Unprocessed unprocessed)
        throws UnknownCommandIdException, TimeoutException, PDUException, IOException
    {
        SmppObject.debug.write(3, "ReceiverBase.receivePDUFromConnection start");
        PDU pdu = null;
        if(unprocessed.getHasUnprocessed())
        {
            ByteBuffer unprocBuffer = unprocessed.getUnprocessed();
            SmppObject.debug.write(1, "have unprocessed " + unprocBuffer.length() + " bytes from previous try");
            pdu = tryGetUnprocessedPDU(unprocessed);
        }
        if(pdu == null)
        {
            ByteBuffer buffer = connection.receive();
            ByteBuffer unprocBuffer = unprocessed.getUnprocessed();
            if(buffer.length() != 0)
            {
                unprocBuffer.appendBuffer(buffer);
                unprocessed.setLastTimeReceived();
                pdu = tryGetUnprocessedPDU(unprocessed);
            } else
            {
                SmppObject.debug.write(3, "no data received this time.");
                long timeout = getReceiveTimeout();
                if(unprocBuffer.length() > 0 && unprocessed.getLastTimeReceived() + timeout < Data.getCurrentTime())
                {
                    SmppObject.debug.write(1, "and it's been very long time.");
                    unprocessed.reset();
                    throw new TimeoutException(timeout, unprocessed.getExpected(), unprocBuffer.length());
                }
            }
        }
        SmppObject.debug.write(3, "ReceiverBase.receivePDUFromConnection finished");
        return pdu;
    }

    private final PDU tryGetUnprocessedPDU(Unprocessed unprocessed)
        throws UnknownCommandIdException, PDUException
    {
        SmppObject.debug.write(1, "trying to create pdu from unprocessed buffer");
        PDU pdu = null;
        ByteBuffer unprocBuffer = unprocessed.getUnprocessed();
        try
        {
            pdu = PDU.createPDU(unprocBuffer);
            unprocessed.check();
        }
        catch(HeaderIncompleteException e)
        {
            SmppObject.debug.write(2, "incomplete message header, will wait for the rest.");
            unprocessed.setHasUnprocessed(false);
            unprocessed.setExpected(16);
        }
        catch(MessageIncompleteException e)
        {
            SmppObject.debug.write(2, "incomplete message, will wait for the rest.");
            unprocessed.setHasUnprocessed(false);
            unprocessed.setExpected(16);
        }
        catch(UnknownCommandIdException e)
        {
            SmppObject.debug.write(1, "unknown pdu, might remove from unprocessed buffer. CommandId=" + e.getCommandId());
            if(e.getCommandLength() <= unprocBuffer.length())
            {
                try
                {
                    unprocBuffer.removeBytes(e.getCommandLength());
                }
                catch(NotEnoughDataInByteBufferException e1)
                {
                    throw new Error("Not enough data in buffer even if previously checked that there was enough.");
                }
                unprocessed.check();
                throw e;
            }
        }
        catch(Exception e)
        {
            SmppObject.debug.write(1, "Exception catched: " + e.toString());
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            SmppObject.debug.write(1, stringWriter.toString());
        }
        if(pdu != null)
        {
            SmppObject.debug.write(1, "received complete pdu" + pdu.debugString());
            SmppObject.debug.write(1, "there is " + unprocBuffer.length() + " bytes left in unprocessed buffer");
        }
        return pdu;
    }

    public void setReceiveTimeout(long timeout)
    {
        receiveTimeout = timeout;
    }

    public long getReceiveTimeout()
    {
        return receiveTimeout;
    }

    private boolean canContinueReceiving(long startTime, long timeout)
    {
        return timeout != -1L ? Data.getCurrentTime() <= startTime + timeout : true;
    }

    private long receiveTimeout;
}
