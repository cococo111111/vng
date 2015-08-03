// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Receiver.java

package org.smpp;

import java.io.IOException;
import org.smpp.debug.Debug;
import org.smpp.debug.Event;
import org.smpp.pdu.GenericNack;
import org.smpp.pdu.InvalidPDUException;
import org.smpp.pdu.PDU;
import org.smpp.pdu.PDUException;
import org.smpp.pdu.UnknownCommandIdException;
import org.smpp.util.ProcessingThread;
import org.smpp.util.Queue;
import org.smpp.util.Unprocessed;

// Referenced classes of package org.smpp:
//            ReceiverBase, TimeoutException, ServerPDUEvent, NotSynchronousException, 
//            SmppObject, ServerPDUEventListener, Transmitter, Connection

public class Receiver extends ReceiverBase
{

    public Receiver(Connection connection)
    {
        transmitter = null;
        this.connection = null;
        pduQueue = new Queue();
        queueWaitTimeout = 10000L;
        receiver = false;
        unprocessed = new Unprocessed();
        pduListener = null;
        asynchronous = false;
        this.connection = connection;
    }

    public Receiver(Transmitter transmitter, Connection connection)
    {
        this.transmitter = null;
        this.connection = null;
        pduQueue = new Queue();
        queueWaitTimeout = 10000L;
        receiver = false;
        unprocessed = new Unprocessed();
        pduListener = null;
        asynchronous = false;
        this.transmitter = transmitter;
        this.connection = connection;
    }

    public boolean isReceiver()
    {
        return receiver;
    }

    public synchronized void setServerPDUEventListener(ServerPDUEventListener pduListener)
    {
        this.pduListener = pduListener;
        asynchronous = pduListener != null;
        if(asynchronous)
            synchronized(pduQueue)
            {
                int queueSize = pduQueue.size();
                for(int i = 0; i < queueSize; i++)
                {
                    PDU pdu = (PDU)pduQueue.dequeue();
                    process(pdu);
                }

            }
    }

    public void start()
    {
        SmppObject.debug.write(1, "Receiver starting");
        receiver = true;
        unprocessed.reset();
        super.start();
        SmppObject.debug.write(1, "Receiver started");
    }

    public void stop()
    {
        SmppObject.debug.write(1, "Receiver stoping");
        if(isReceiver())
        {
            super.stop();
            receiver = false;
        }
        SmppObject.debug.write(1, "Receiver stoped");
    }

    public synchronized PDU receive(long timeout)
        throws UnknownCommandIdException, TimeoutException, NotSynchronousException, PDUException, IOException
    {
        PDU pdu = null;
        if(!asynchronous)
            pdu = tryReceivePDUWithTimeout(connection, null, timeout);
        return pdu;
    }

    public synchronized PDU receive(PDU expectedPDU)
        throws UnknownCommandIdException, TimeoutException, NotSynchronousException, PDUException, IOException
    {
        PDU pdu = null;
        if(!asynchronous)
            pdu = tryReceivePDUWithTimeout(connection, expectedPDU);
        return pdu;
    }

    protected PDU tryReceivePDU(Connection connection, PDU expectedPDU)
        throws UnknownCommandIdException, TimeoutException, PDUException, IOException
    {
        PDU pdu = null;
        if(receiver)
        {
            SmppObject.debug.write(3, "Is receiver/transciever => trying to get from queue.");
            synchronized(pduQueue)
            {
                if(expectedPDU == null)
                {
                    if(!pduQueue.isEmpty())
                        pdu = (PDU)pduQueue.dequeue();
                } else
                {
                    pdu = (PDU)pduQueue.dequeue(expectedPDU);
                }
                if(pdu == null)
                    try
                    {
                        pduQueue.wait(getQueueWaitTimeout());
                    }
                    catch(InterruptedException e)
                    {
                        SmppObject.debug.write(1, "tryReceivePDU got interrupt waiting for queue");
                    }
            }
        } else
        {
            SmppObject.debug.write(1, "Is transmitter only => trying to receive from connection.");
            pdu = receivePDUFromConnection(connection, unprocessed);
            if(pdu != null && (expectedPDU == null || !pdu.equals(expectedPDU)))
            {
                SmppObject.debug.write(1, "This is not the pdu we expect, processing" + pdu.debugString());
                enqueue(pdu);
                pdu = null;
            }
        }
        return pdu;
    }

    protected void receiveAsync()
    {
        PDU pdu = null;
        try
        {
            SmppObject.debug.write(3, "Receiver.receiveAsync() going to receive pdu.");
            pdu = receivePDUFromConnection(connection, unprocessed);
        }
        catch(InvalidPDUException e)
        {
            SmppObject.event.write(e, "Receiver.receiveAsync(): received PDU is invalid.");
            PDU expdu = e.getPDU();
            int seqNr = expdu != null ? expdu.getSequenceNumber() : 0;
            sendGenericNack(1, seqNr);
        }
        catch(UnknownCommandIdException e)
        {
            SmppObject.event.write(e, "Receiver.receiveAsync(): Unknown command id.");
            sendGenericNack(3, e.getSequenceNumber());
        }
        catch(TimeoutException e)
        {
            SmppObject.debug.write(1, "Receiver.receiveAsync() too long had an uncomplete message.");
        }
        catch(PDUException e)
        {
            SmppObject.event.write(e, "Receiver.receiveAsync()");
        }
        catch(Exception e)
        {
            SmppObject.event.write(e, "Receiver.receiveAsync()");
            stopProcessing(e);
        }
        if(pdu != null)
        {
            SmppObject.debug.write(1, "Receiver.receiveAsync(): PDU received, processing " + pdu.debugString());
            if(asynchronous)
                process(pdu);
            else
                enqueue(pdu);
        }
    }

    private void process(PDU pdu)
    {
        SmppObject.debug.write(1, "receiver passing pdu to ServerPDUEventListener");
        if(pduListener != null)
        {
            ServerPDUEvent pduReceived = new ServerPDUEvent(this, connection, pdu);
            pduListener.handleEvent(pduReceived);
            SmppObject.debug.write(1, "ServerPDUEventListener received pdu");
        } else
        {
            SmppObject.debug.write(1, "async receiver doesn't have ServerPDUEventListener, discarding " + pdu.debugString());
        }
    }

    private void enqueue(PDU pdu)
    {
        SmppObject.debug.write(1, "receiver enqueuing pdu.");
        synchronized(pduQueue)
        {
            pduQueue.enqueue(pdu);
            pduQueue.notifyAll();
        }
    }

    private void sendGenericNack(int commandStatus, int sequenceNumber)
    {
        if(transmitter != null)
            try
            {
                GenericNack gnack = new GenericNack(commandStatus, sequenceNumber);
                transmitter.send(gnack);
            }
            catch(IOException gnacke)
            {
                SmppObject.event.write(gnacke, "Receiver.run(): IOException sending generic_nack.");
            }
            catch(Exception gnacke)
            {
                SmppObject.event.write(gnacke, "Receiver.run(): an exception sending generic_nack.");
                stopProcessing(gnacke);
            }
    }

    public void setQueueWaitTimeout(long timeout)
    {
        queueWaitTimeout = timeout;
    }

    public long getQueueWaitTimeout()
    {
        return queueWaitTimeout;
    }

    public String getThreadName()
    {
        return "Receiver";
    }

    private static final String RECEIVER_THREAD_NAME = "Receiver";
    private Transmitter transmitter;
    private Connection connection;
    private Queue pduQueue;
    private long queueWaitTimeout;
    private boolean receiver;
    private Unprocessed unprocessed;
    private ServerPDUEventListener pduListener;
    private boolean asynchronous;
}
