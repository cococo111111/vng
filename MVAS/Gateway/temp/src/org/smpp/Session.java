// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Session.java

package org.smpp;

import java.io.IOException;
import java.util.Hashtable;
import org.smpp.debug.Debug;
import org.smpp.debug.Event;
import org.smpp.pdu.AlertNotification;
import org.smpp.pdu.BindRequest;
import org.smpp.pdu.BindResponse;
import org.smpp.pdu.CancelSM;
import org.smpp.pdu.CancelSMResp;
import org.smpp.pdu.DataSM;
import org.smpp.pdu.DataSMResp;
import org.smpp.pdu.DeliverSM;
import org.smpp.pdu.DeliverSMResp;
import org.smpp.pdu.EnquireLink;
import org.smpp.pdu.EnquireLinkResp;
import org.smpp.pdu.GenericNack;
import org.smpp.pdu.InvalidPDUException;
import org.smpp.pdu.Outbind;
import org.smpp.pdu.PDU;
import org.smpp.pdu.PDUException;
import org.smpp.pdu.QuerySM;
import org.smpp.pdu.QuerySMResp;
import org.smpp.pdu.ReplaceSM;
import org.smpp.pdu.ReplaceSMResp;
import org.smpp.pdu.Request;
import org.smpp.pdu.Response;
import org.smpp.pdu.SubmitMultiSM;
import org.smpp.pdu.SubmitMultiSMResp;
import org.smpp.pdu.SubmitSM;
import org.smpp.pdu.SubmitSMResp;
import org.smpp.pdu.Unbind;
import org.smpp.pdu.UnbindResp;
import org.smpp.pdu.UnknownCommandIdException;
import org.smpp.pdu.ValueNotSetException;
import org.smpp.util.NotEnoughDataInByteBufferException;
import org.smpp.util.TerminatingZeroNotFoundException;

// Referenced classes of package org.smpp:
//            SmppObject, Transmitter, Receiver, WrongSessionStateException, 
//            NotSynchronousException, TimeoutException, SmppException, Connection, 
//            ReceiverBase, ServerPDUEventListener, ReceivedPDUEvent, ServerPDUEvent

public class Session extends SmppObject
{
    private class UnbindServerPDUEventListener extends SmppObject
        implements ServerPDUEventListener
    {

        public void handleEvent(ServerPDUEvent event)
        {
            PDU pdu = event.getPDU();
            if(pdu.equals(unbindReq))
                synchronized(this)
                {
                    try
                    {
                        unbindResp = (UnbindResp)session.checkResponse(pdu, expectedResp);
                    }
                    catch(Exception e)
                    {
                        SmppObject.debug.write(4, "exception handling unbind " + e);
                        SmppObject.event.write(e, "exception handling unbind");
                    }
                    notify();
                }
            else
            if(origListener != null)
                origListener.handleEvent(event);
        }

        public UnbindResp getUnbindResp()
        {
            return unbindResp;
        }

        Session session;
        ServerPDUEventListener origListener;
        Unbind unbindReq;
        UnbindResp expectedResp;
        UnbindResp unbindResp;

        public UnbindServerPDUEventListener(Session session, ServerPDUEventListener origListener, Unbind unbindReq)
        {
            unbindResp = null;
            this.session = session;
            this.origListener = origListener;
            this.unbindReq = unbindReq;
            expectedResp = (UnbindResp)unbindReq.getResponse();
        }
    }


    protected Session()
    {
        opened = false;
        bound = false;
        disallowUnknownPDU = false;
        state = 1;
        stateChecking = false;
        type = 1;
        pduListener = null;
        asynchronous = false;
    }

    public Session(Connection connection)
    {
        opened = false;
        bound = false;
        disallowUnknownPDU = false;
        state = 1;
        stateChecking = false;
        type = 1;
        pduListener = null;
        asynchronous = false;
        this.connection = connection;
    }

    public void open()
        throws IOException, WrongSessionStateException
    {
        checkState(1);
        if(!opened)
        {
            connection.open();
            opened = true;
            setState(2);
        }
    }

    public void close()
        throws IOException, WrongSessionStateException
    {
        checkState(2);
        if(opened)
        {
            connection.close();
            opened = false;
            setState(1);
        }
    }

    public boolean isOpened()
    {
        return opened;
    }

    public boolean isBound()
    {
        return bound;
    }

    private void setServerPDUEventListener(ServerPDUEventListener pduListener)
    {
        this.pduListener = pduListener;
        receiver.setServerPDUEventListener(pduListener);
        asynchronous = pduListener != null;
    }

    private ServerPDUEventListener getServerPDUEventListener()
    {
        return pduListener;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public int getType()
    {
        return type;
    }

    public final BindResponse bind(BindRequest bindReq)
        throws ValueNotSetException, TimeoutException, PDUException, IOException, WrongSessionStateException
    {
        checkState(bindReq);
        return bind(bindReq, null);
    }

    public final BindResponse bind(BindRequest bindReq, ServerPDUEventListener pduListener)
        throws ValueNotSetException, TimeoutException, PDUException, IOException, WrongSessionStateException
    {
        checkState(bindReq);
        if(bound)
            return null;
        open();
        transmitter = new Transmitter(connection);
        receiver = new Receiver(transmitter, connection);
        BindResponse bindResp = (BindResponse)send(bindReq, false);
        bound = bindResp != null && bindResp.getCommandStatus() == 0;
        if(!bound)
        {
            close();
        } else
        {
            receiver.start();
            if(bindReq.isTransmitter())
            {
                if(bindReq.isReceiver())
                    setState(16);
                else
                    setState(4);
            } else
            {
                setState(8);
            }
            setServerPDUEventListener(pduListener);
        }
        return bindResp;
    }

    public final void outbind(Outbind request)
        throws ValueNotSetException, TimeoutException, PDUException, IOException, WrongSessionStateException
    {
        checkState(request);
        send(request);
    }

    public final UnbindResp unbind()
        throws ValueNotSetException, TimeoutException, PDUException, IOException, WrongSessionStateException
    {
        UnbindResp unbindResp = null;
        if(bound)
        {
            Unbind unbindReq = new Unbind();
            checkState(unbindReq);
            ServerPDUEventListener origListener = null;
            if(asynchronous)
            {
                unbindReq.assignSequenceNumber();
                origListener = getServerPDUEventListener();
                UnbindServerPDUEventListener unbindListener = new UnbindServerPDUEventListener(this, origListener, unbindReq);
                setServerPDUEventListener(unbindListener);
                synchronized(unbindListener)
                {
                    send(unbindReq);
                    try
                    {
                        unbindListener.wait(receiver.getReceiveTimeout());
                        unbindResp = unbindListener.getUnbindResp();
                    }
                    catch(InterruptedException e) { }
                }
            } else
            {
                SmppObject.debug.write(4, "going to unbound sync session");
                unbindResp = (UnbindResp)send(unbindReq);
            }
            bound = unbindResp == null;
            if(!bound)
            {
                setState(2);
                receiver.stop();
                receiver = null;
                transmitter = null;
                close();
            } else
            {
                SmppObject.debug.write("Unbind unsuccessfull, restoring listener");
                setServerPDUEventListener(origListener);
            }
        }
        return unbindResp;
    }

    public final void genericNack(GenericNack response)
        throws ValueNotSetException, TimeoutException, IOException, WrongSessionStateException
    {
        checkState(response);
        try
        {
            respond(response);
        }
        catch(WrongSessionStateException e)
        {
            SmppObject.debug.write("strange, generic nack thrown " + e);
            SmppObject.debug.write("this shouldn't happend");
            SmppObject.event.write(e, "Unexpected exeption caught");
        }
    }

    public final void genericNack(int commandStatus, int sequenceNumber)
        throws ValueNotSetException, TimeoutException, IOException, WrongSessionStateException
    {
        GenericNack gnack = new GenericNack(commandStatus, sequenceNumber);
        checkState(gnack);
        genericNack(gnack);
    }

    public final SubmitSMResp submit(SubmitSM request)
        throws ValueNotSetException, TimeoutException, PDUException, IOException, WrongSessionStateException
    {
        checkState(request);
        return (SubmitSMResp)send(request);
    }

    public final SubmitMultiSMResp submitMulti(SubmitMultiSM request)
        throws ValueNotSetException, TimeoutException, PDUException, IOException, WrongSessionStateException
    {
        checkState(request);
        return (SubmitMultiSMResp)send(request);
    }

    public final DeliverSMResp deliver(DeliverSM request)
        throws ValueNotSetException, TimeoutException, PDUException, IOException, WrongSessionStateException
    {
        checkState(request);
        return (DeliverSMResp)send(request);
    }

    public final DataSMResp data(DataSM request)
        throws ValueNotSetException, TimeoutException, PDUException, IOException, WrongSessionStateException
    {
        checkState(request);
        return (DataSMResp)send(request);
    }

    public final QuerySMResp query(QuerySM request)
        throws ValueNotSetException, TimeoutException, PDUException, IOException, WrongSessionStateException
    {
        checkState(request);
        return (QuerySMResp)send(request);
    }

    public final CancelSMResp cancel(CancelSM request)
        throws ValueNotSetException, TimeoutException, PDUException, IOException, WrongSessionStateException
    {
        checkState(request);
        return (CancelSMResp)send(request);
    }

    public final ReplaceSMResp replace(ReplaceSM request)
        throws ValueNotSetException, TimeoutException, PDUException, IOException, WrongSessionStateException
    {
        checkState(request);
        return (ReplaceSMResp)send(request);
    }

    public final EnquireLinkResp enquireLink(EnquireLink request)
        throws ValueNotSetException, TimeoutException, PDUException, IOException, WrongSessionStateException
    {
        checkState(request);
        return (EnquireLinkResp)send(request);
    }

    public final EnquireLinkResp enquireLink()
        throws ValueNotSetException, TimeoutException, PDUException, IOException, WrongSessionStateException
    {
        EnquireLink request = new EnquireLink();
        checkState(request);
        return enquireLink(request);
    }

    public final void alertNotification(AlertNotification request)
        throws ValueNotSetException, TimeoutException, PDUException, IOException, WrongSessionStateException
    {
        checkState(request);
        send(request);
    }

    public final PDU receive()
        throws UnknownCommandIdException, TimeoutException, NotSynchronousException, PDUException, IOException
    {
        if(!asynchronous)
            return receive(-1L);
        else
            throw new NotSynchronousException(this);
    }

    public final PDU receive(long timeout)
        throws UnknownCommandIdException, TimeoutException, NotSynchronousException, PDUException, IOException
    {
        PDU pdu = null;
        if(receiver.isReceiver())
            if(!asynchronous)
                pdu = receiver.receive(timeout);
            else
                throw new NotSynchronousException(this);
        return pdu;
    }

    public final void respond(Response response)
        throws ValueNotSetException, IOException, WrongSessionStateException
    {
        checkState(response);
        SmppObject.debug.enter(4, this, "respond(Response)");
        SmppObject.debug.write(4, "Sending response " + response.debugString());
        try
        {
            transmitter.send(response);
        }
        catch(ValueNotSetException e)
        {
            SmppObject.event.write(e, "Sending a response.");
            SmppObject.debug.exit(4, this);
            throw e;
        }
        SmppObject.debug.exit(4, this);
    }

    public Transmitter getTransmitter()
    {
        return transmitter;
    }

    public Receiver getReceiver()
    {
        return receiver;
    }

    public Connection getConnection()
    {
        return connection;
    }

    private final Response send(Request request, boolean asynchronous)
        throws ValueNotSetException, TimeoutException, PDUException, IOException
    {
        SmppObject.debug.enter(4, this, "send(Request)");
        Response response = null;
        SmppObject.debug.write(4, "Sending request " + request.debugString());
        try
        {
            transmitter.send(request);
        }
        catch(ValueNotSetException e)
        {
            SmppObject.event.write(e, "Sending the request.");
            SmppObject.debug.exit(4, this);
            throw e;
        }
        if(!asynchronous && request.canResponse())
        {
            PDU pdu = null;
            Response expResponse = null;
            expResponse = request.getResponse();
            try
            {
                SmppObject.debug.write(4, "Going to receive response. Expecting " + expResponse.debugString());
                try
                {
                    pdu = receiver.receive(expResponse);
                }
                catch(NotSynchronousException e)
                {
                    SmppObject.debug.write("Unexpected NotSynchronousException caught, ignoring :-)");
                }
            }
            catch(UnknownCommandIdException e)
            {
                safeGenericNack(3, e.getSequenceNumber());
            }
            catch(InvalidPDUException e)
            {
                if((e.getException() instanceof NotEnoughDataInByteBufferException) || (e.getException() instanceof TerminatingZeroNotFoundException))
                {
                    SmppObject.debug.write(4, "wrong length " + e);
                    SmppObject.debug.write(4, " => sending gnack.");
                    safeGenericNack(1, e.getPDU().getSequenceNumber());
                } else
                {
                    SmppObject.debug.write(4, "InvalidPDUException - rethrowing " + e);
                    SmppObject.debug.exit(4, this);
                    throw e;
                }
            }
            catch(TimeoutException e)
            {
                SmppObject.debug.write(4, "TimeoutException - rethrowing " + e);
                SmppObject.debug.exit(4, this);
                throw e;
            }
            if(pdu != null)
            {
                SmppObject.debug.write(4, "Got response(?) pdu " + pdu.debugString());
                response = checkResponse(pdu, expResponse);
            } else
            {
                SmppObject.debug.write(4, "No response received.");
            }
        }
        SmppObject.debug.exit(4, this);
        return response;
    }

    private final Response send(Request request)
        throws ValueNotSetException, TimeoutException, PDUException, IOException
    {
        return send(request, asynchronous);
    }

    private Response checkResponse(PDU pdu, Response expResponse)
        throws ValueNotSetException, TimeoutException, IOException
    {
        Response response = null;
        SmppObject.debug.write(4, "checking response if it's what we expected.");
        if(pdu.getCommandId() != expResponse.getCommandId())
        {
            SmppObject.debug.write(4, "Got different response than expected " + expResponse.debugString());
            if(pdu.getCommandId() == 0x80000000)
            {
                SmppObject.debug.write(4, "Got generic nack. What could we do wrong?");
                expResponse.setCommandId(0x80000000);
                expResponse.setCommandLength(pdu.getCommandLength());
                expResponse.setCommandStatus(pdu.getCommandStatus());
                expResponse.setSequenceNumber(pdu.getSequenceNumber());
                response = expResponse;
            } else
            {
                SmppObject.debug.write(4, "invalid command id - sending gnack");
                safeGenericNack(3, pdu.getSequenceNumber());
                response = null;
            }
        } else
        {
            response = (Response)pdu;
        }
        return response;
    }

    private void safeGenericNack(int commandStatus, int sequenceNumber)
        throws IOException
    {
        try
        {
            genericNack(commandStatus, sequenceNumber);
        }
        catch(SmppException e)
        {
            SmppObject.debug.write("Ignoring unexpected SmppException caught sending generic nack.");
            SmppObject.event.write(e, "Ignoring unexpected exception caught sending generic nack.");
        }
    }

    private void setState(int state)
    {
        this.state = state;
    }

    public int getState()
    {
        return state;
    }

    public void enableStateChecking()
    {
        stateChecking = true;
    }

    public void disableStateChecking()
    {
        stateChecking = false;
    }

    public void checkState(int requestedState)
        throws WrongSessionStateException
    {
        if(stateChecking)
        {
            SmppObject.debug.write(4, "checking state current=0x" + Integer.toHexString(state) + " requested esme=0x" + Integer.toHexString(requestedState));
            if((state & requestedState) == 0)
                throw new WrongSessionStateException(type, requestedState, state);
        }
    }

    public void checkState(PDU pdu)
        throws WrongSessionStateException
    {
        if(stateChecking)
        {
            Hashtable pduMatrix = getStateMatrix(type);
            Integer commandIdInteger = new Integer(pdu.getCommandId());
            Integer requestedStateInteger = pduMatrix != null ? (Integer)pduMatrix.get(commandIdInteger) : null;
            if(requestedStateInteger != null)
                checkState(requestedStateInteger.intValue());
            else
            if(disallowUnknownPDU)
                throw new WrongSessionStateException();
        }
    }

    public boolean isStateAllowed(int requestedState)
    {
        boolean stateAllowed = true;
        try
        {
            checkState(requestedState);
        }
        catch(WrongSessionStateException e)
        {
            stateAllowed = false;
        }
        return stateAllowed;
    }

    public boolean isPDUAllowed(PDU pdu)
    {
        boolean pduAllowed = true;
        try
        {
            checkState(pdu);
        }
        catch(WrongSessionStateException e)
        {
            pduAllowed = false;
        }
        return pduAllowed;
    }

    private static void initialiseStateMatrix()
    {
        esmeStateMatrix = new Hashtable();
        addValidState(esmeStateMatrix, 2, 1);
        addValidState(esmeStateMatrix, 0x80000002, 0);
        addValidState(esmeStateMatrix, 1, 1);
        addValidState(esmeStateMatrix, 0x80000001, 0);
        addValidState(esmeStateMatrix, 9, 1);
        addValidState(esmeStateMatrix, 0x80000009, 0);
        addValidState(esmeStateMatrix, 11, 0);
        addValidState(esmeStateMatrix, 6, 28);
        addValidState(esmeStateMatrix, 0x80000006, 28);
        addValidState(esmeStateMatrix, 4, 20);
        addValidState(esmeStateMatrix, 0x80000004, 0);
        addValidState(esmeStateMatrix, 33, 20);
        addValidState(esmeStateMatrix, 0x80000021, 0);
        addValidState(esmeStateMatrix, 259, 20);
        addValidState(esmeStateMatrix, 0x80000103, 24);
        addValidState(esmeStateMatrix, 5, 0);
        addValidState(esmeStateMatrix, 0x80000005, 24);
        addValidState(esmeStateMatrix, 3, 20);
        addValidState(esmeStateMatrix, 0x80000003, 0);
        addValidState(esmeStateMatrix, 8, 20);
        addValidState(esmeStateMatrix, 0x80000008, 0);
        addValidState(esmeStateMatrix, 7, 20);
        addValidState(esmeStateMatrix, 0x80000007, 0);
        addValidState(esmeStateMatrix, 21, 28);
        addValidState(esmeStateMatrix, 0x80000015, 28);
        addValidState(esmeStateMatrix, 258, 0);
        addValidState(esmeStateMatrix, 0x80000000, 28);
        mcStateMatrix = new Hashtable();
        addValidState(mcStateMatrix, 2, 0);
        addValidState(mcStateMatrix, 0x80000002, 28);
        addValidState(mcStateMatrix, 1, 0);
        addValidState(mcStateMatrix, 0x80000001, 28);
        addValidState(mcStateMatrix, 9, 0);
        addValidState(mcStateMatrix, 0x80000009, 28);
        addValidState(mcStateMatrix, 11, 28);
        addValidState(mcStateMatrix, 6, 28);
        addValidState(mcStateMatrix, 0x80000006, 28);
        addValidState(mcStateMatrix, 4, 0);
        addValidState(mcStateMatrix, 0x80000004, 20);
        addValidState(mcStateMatrix, 33, 0);
        addValidState(mcStateMatrix, 0x80000021, 20);
        addValidState(mcStateMatrix, 259, 24);
        addValidState(mcStateMatrix, 0x80000103, 20);
        addValidState(mcStateMatrix, 5, 24);
        addValidState(mcStateMatrix, 0x80000005, 0);
        addValidState(mcStateMatrix, 3, 0);
        addValidState(mcStateMatrix, 0x80000003, 20);
        addValidState(mcStateMatrix, 8, 0);
        addValidState(mcStateMatrix, 0x80000008, 20);
        addValidState(mcStateMatrix, 7, 0);
        addValidState(mcStateMatrix, 0x80000007, 20);
        addValidState(mcStateMatrix, 21, 28);
        addValidState(mcStateMatrix, 0x80000015, 28);
        addValidState(mcStateMatrix, 258, 24);
        addValidState(mcStateMatrix, 0x80000000, 28);
    }

    private static void addValidState(Hashtable matrix, int commandId, int state)
    {
        matrix.put(new Integer(commandId), new Integer(state));
    }

    private static Hashtable getStateMatrix(int type)
    {
        switch(type)
        {
        case 1: // '\001'
            return esmeStateMatrix;

        case 2: // '\002'
            return mcStateMatrix;
        }
        return null;
    }

    private boolean opened;
    private boolean bound;
    public static final int STATE_NOT_ALLOWED = 0;
    public static final int STATE_CLOSED = 1;
    public static final int STATE_OPENED = 2;
    public static final int STATE_TRANSMITTER = 4;
    public static final int STATE_RECEIVER = 8;
    public static final int STATE_TRANSCEIVER = 16;
    public static final int STATE_ALWAYS = 30;
    private static Hashtable esmeStateMatrix;
    private static Hashtable mcStateMatrix;
    private boolean disallowUnknownPDU;
    private int state;
    private boolean stateChecking;
    public static final int TYPE_ESME = 1;
    public static final int TYPE_MC = 2;
    private int type;
    private Connection connection;
    private Transmitter transmitter;
    private Receiver receiver;
    private ServerPDUEventListener pduListener;
    private boolean asynchronous;

    static 
    {
        initialiseStateMatrix();
    }

}
