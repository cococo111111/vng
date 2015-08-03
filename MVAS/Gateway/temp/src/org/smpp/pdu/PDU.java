// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PDU.java

package org.smpp.pdu;

import java.util.*;
import org.smpp.SmppObject;
import org.smpp.debug.Debug;
import org.smpp.pdu.tlv.TLV;
import org.smpp.pdu.tlv.TLVException;
import org.smpp.pdu.tlv.TLVOctets;
import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            ByteData, InvalidPDUException, PDUException, PDUHeader, 
//            HeaderIncompleteException, MessageIncompleteException, UnknownCommandIdException, BindTransmitter, 
//            BindTransmitterResp, BindReceiver, BindReceiverResp, BindTransciever, 
//            BindTranscieverResp, Unbind, UnbindResp, Outbind, 
//            SubmitSM, SubmitSMResp, SubmitMultiSM, SubmitMultiSMResp, 
//            DeliverSM, DeliverSMResp, DataSM, DataSMResp, 
//            QuerySM, QuerySMResp, CancelSM, CancelSMResp, 
//            ReplaceSM, ReplaceSMResp, EnquireLink, EnquireLinkResp, 
//            AlertNotification, GenericNack, ValueNotSetException, UnexpectedOptionalParameterException

public abstract class PDU extends ByteData
{

    public PDU()
    {
        sequenceNumberChanged = false;
        header = null;
        optionalParameters = new Vector(10, 2);
        extraOptionalParameters = new Vector(1, 1);
        valid = 3;
        applicationSpecificInfo = null;
    }

    public PDU(int commandId)
    {
        sequenceNumberChanged = false;
        header = null;
        optionalParameters = new Vector(10, 2);
        extraOptionalParameters = new Vector(1, 1);
        valid = 3;
        applicationSpecificInfo = null;
        checkHeader();
        setCommandId(commandId);
    }

    public void setBody(ByteBuffer bytebuffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, PDUException
    {
    }

    public ByteBuffer getBody()
        throws ValueNotSetException
    {
        return null;
    }

    public boolean canResponse()
    {
        return false;
    }

    public abstract boolean isRequest();

    public abstract boolean isResponse();

    public void assignSequenceNumber()
    {
        assignSequenceNumber(false);
    }

    public void assignSequenceNumber(boolean always)
    {
        if(!sequenceNumberChanged || always)
            setSequenceNumber(++sequenceNumber);
    }

    public void resetSequenceNumber()
    {
        setSequenceNumber(0);
        sequenceNumberChanged = false;
    }

    public void setData(ByteBuffer buffer)
        throws InvalidPDUException, PDUException
    {
        int initialBufLen = buffer.length();
        try
        {
            setValid((byte)0);
            if(buffer.length() < 16 && SmppObject.debug.active(5))
                SmppObject.debug.write(5, "PDU.setData() not enough data for header in the buffer " + buffer.getHexDump());
            ByteBuffer headerBuf = buffer.removeBytes(16);
            if(SmppObject.debug.active(5))
                SmppObject.debug.write(5, "PDU.setData() parsing header " + headerBuf.getHexDump());
            setHeader(headerBuf);
            setValid((byte)1);
            if(SmppObject.debug.active(5))
                if(getCommandLength() > 16)
                {
                    ByteBuffer tempBodyBuf = buffer.readBytes(getCommandLength() - 16);
                    SmppObject.debug.write(5, "PDU.setData() parsing body " + tempBodyBuf.getHexDump());
                } else
                {
                    SmppObject.debug.write(5, "PDU.setData() no data for body");
                }
            setBody(buffer);
            setValid((byte)2);
            if(initialBufLen - buffer.length() < getCommandLength())
            {
                int optionalLength = (getCommandLength() + buffer.length()) - initialBufLen;
                SmppObject.debug.write(5, "have " + optionalLength + " bytes left.");
                ByteBuffer optionalBody = buffer.removeBuffer(optionalLength);
                setOptionalBody(optionalBody);
            }
            setValid((byte)3);
        }
        catch(NotEnoughDataInByteBufferException e)
        {
            throw new InvalidPDUException(this, e);
        }
        catch(TerminatingZeroNotFoundException e)
        {
            throw new InvalidPDUException(this, e);
        }
        catch(PDUException e)
        {
            e.setPDU(this);
            throw e;
        }
        catch(Exception e)
        {
            throw new InvalidPDUException(this, e);
        }
        if(buffer.length() != initialBufLen - getCommandLength())
            throw new InvalidPDUException(this, "The parsed size of the message is not equal to command_length.");
        else
            return;
    }

    public ByteBuffer getData()
        throws ValueNotSetException
    {
        ByteBuffer bodyBuf = new ByteBuffer();
        bodyBuf.appendBuffer(getBody());
        bodyBuf.appendBuffer(getOptionalBody());
        setCommandLength(bodyBuf.length() + 16);
        ByteBuffer pduBuf = getHeader();
        pduBuf.appendBuffer(bodyBuf);
        if(SmppObject.debug.active(5))
            SmppObject.debug.write(5, "PDU.getData() build up data " + pduBuf.getHexDump());
        return pduBuf;
    }

    public void setValid(byte valid)
    {
        this.valid = valid;
    }

    public byte getValid()
    {
        return valid;
    }

    public boolean isValid()
    {
        return getValid() == 3;
    }

    public boolean isInvalid()
    {
        return getValid() == 0;
    }

    public boolean isHeaderValid()
    {
        return getValid() >= 1;
    }

    private void setHeader(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException
    {
        checkHeader();
        header.setData(buffer);
        sequenceNumberChanged = true;
    }

    private ByteBuffer getHeader()
    {
        checkHeader();
        return header.getData();
    }

    private void setOptionalBody(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, UnexpectedOptionalParameterException, TLVException
    {
        TLV tlv = null;
        ByteBuffer tlvBuf;
        for(; buffer.length() > 0; tlv.setData(tlvBuf))
        {
            ByteBuffer tlvHeader = buffer.readBytes(4);
            short tag = tlvHeader.removeShort();
            tlv = findOptional(optionalParameters, tag);
            if(tlv == null)
            {
                tlv = new TLVOctets(tag);
                registerExtraOptional(tlv);
            }
            short length = tlvHeader.removeShort();
            tlvBuf = buffer.removeBuffer(4 + length);
        }

    }

    private ByteBuffer getOptionalBody()
        throws ValueNotSetException
    {
        ByteBuffer optBody = new ByteBuffer();
        optBody.appendBuffer(getOptionalBody(optionalParameters));
        optBody.appendBuffer(getOptionalBody(extraOptionalParameters));
        return optBody;
    }

    private ByteBuffer getOptionalBody(Vector optionalParameters)
        throws ValueNotSetException
    {
        ByteBuffer optBody = new ByteBuffer();
        int size = optionalParameters.size();
        TLV tlv = null;
        for(int i = 0; i < size; i++)
        {
            tlv = (TLV)optionalParameters.get(i);
            if(tlv != null && tlv.hasValue())
                optBody.appendBuffer(tlv.getData());
        }

        return optBody;
    }

    protected void registerOptional(TLV tlv)
    {
        if(tlv != null)
            optionalParameters.add(tlv);
    }

    protected void registerExtraOptional(TLV tlv)
    {
        if(tlv != null)
            extraOptionalParameters.add(tlv);
    }

    private TLV findOptional(Vector optionalParameters, short tag)
    {
        int size = optionalParameters.size();
        TLV tlv = null;
        for(int i = 0; i < size; i++)
        {
            tlv = (TLV)optionalParameters.get(i);
            if(tlv != null && tlv.getTag() == tag)
                return tlv;
        }

        return null;
    }

    private void replaceExtraOptional(TLV tlv)
    {
        int size = extraOptionalParameters.size();
        TLV existing = null;
        short tlvTag = tlv.getTag();
        for(int i = 0; i < size; i++)
        {
            existing = (TLV)extraOptionalParameters.get(i);
            if(existing != null && existing.getTag() == tlvTag)
            {
                extraOptionalParameters.set(i, tlv);
                return;
            }
        }

        registerExtraOptional(tlv);
    }

    public void setExtraOptional(TLV tlv)
    {
        replaceExtraOptional(tlv);
    }

    public void setExtraOptional(short tag, ByteBuffer data)
        throws TLVException
    {
        TLV tlv = new TLVOctets(tag, data);
        setExtraOptional(tlv);
    }

    public TLV getExtraOptional(short tag)
    {
        TLV tlv = findOptional(extraOptionalParameters, tag);
        return tlv;
    }

    private void checkHeader()
    {
        if(header == null)
            header = new PDUHeader();
    }

    public int getCommandLength()
    {
        checkHeader();
        return header.getCommandLength();
    }

    public int getCommandId()
    {
        checkHeader();
        return header.getCommandId();
    }

    public int getCommandStatus()
    {
        checkHeader();
        return header.getCommandStatus();
    }

    public int getSequenceNumber()
    {
        checkHeader();
        return header.getSequenceNumber();
    }

    public void setCommandLength(int cmdLen)
    {
        checkHeader();
        header.setCommandLength(cmdLen);
    }

    public void setCommandId(int cmdId)
    {
        checkHeader();
        header.setCommandId(cmdId);
    }

    public void setCommandStatus(int cmdStatus)
    {
        checkHeader();
        header.setCommandStatus(cmdStatus);
    }

    public void setSequenceNumber(int seqNr)
    {
        checkHeader();
        header.setSequenceNumber(seqNr);
        sequenceNumberChanged = true;
    }

    public boolean isOk()
    {
        return getCommandStatus() == 0;
    }

    public boolean isGNack()
    {
        return getCommandId() == 0x80000000;
    }

    public static final PDU createPDU(ByteBuffer buffer)
        throws HeaderIncompleteException, MessageIncompleteException, UnknownCommandIdException, InvalidPDUException, TLVException, PDUException
    {
        ByteBuffer headerBuf = null;
        try
        {
            headerBuf = buffer.readBytes(16);
        }
        catch(NotEnoughDataInByteBufferException e)
        {
            throw new HeaderIncompleteException();
        }
        PDUHeader header = new PDUHeader();
        try
        {
            header.setData(headerBuf);
        }
        catch(NotEnoughDataInByteBufferException e) { }
        if(buffer.length() < header.getCommandLength())
            throw new MessageIncompleteException();
        PDU pdu = createPDU(header.getCommandId());
        if(pdu != null)
        {
            pdu.setData(buffer);
            return pdu;
        } else
        {
            throw new UnknownCommandIdException(header);
        }
    }

    public static final PDU createPDU(int commandId)
    {
        int size = pduList.size();
        PDU pdu = null;
        PDU newInstance = null;
        for(int i = 0; i < size; i++)
        {
            pdu = (PDU)pduList.get(i);
            if(pdu != null && pdu.getCommandId() == commandId)
            {
                try
                {
                    newInstance = (PDU)pdu.getClass().newInstance();
                }
                catch(IllegalAccessException e) { }
                catch(InstantiationException e) { }
                return newInstance;
            }
        }

        return null;
    }

    public String debugString()
    {
        String dbgs = "(pdu: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + Integer.toString(getCommandLength());
        dbgs = dbgs + " ";
        dbgs = dbgs + Integer.toHexString(getCommandId());
        dbgs = dbgs + " ";
        dbgs = dbgs + Integer.toHexString(getCommandStatus());
        dbgs = dbgs + " ";
        if(sequenceNumberChanged)
            dbgs = dbgs + Integer.toString(getSequenceNumber());
        else
            dbgs = dbgs + "[" + (sequenceNumber + 1) + "]";
        dbgs = dbgs + ") ";
        return dbgs;
    }

    protected String debugStringOptional(String label, Vector optionalParameters)
    {
        String dbgs = "";
        int size = optionalParameters.size();
        if(size > 0)
        {
            dbgs = dbgs + "(" + label + ": ";
            TLV tlv = null;
            for(int i = 0; i < size; i++)
            {
                tlv = (TLV)optionalParameters.get(i);
                if(tlv != null && tlv.hasValue())
                {
                    dbgs = dbgs + tlv.debugString();
                    dbgs = dbgs + " ";
                }
            }

            dbgs = dbgs + ") ";
        }
        return dbgs;
    }

    protected String debugStringOptional()
    {
        String dbgs = "";
        dbgs = dbgs + debugStringOptional("opt", optionalParameters);
        dbgs = dbgs + debugStringOptional("extraopt", extraOptionalParameters);
        return dbgs;
    }

    public boolean equals(Object object)
    {
        if(object != null && (object instanceof PDU))
        {
            PDU pdu = (PDU)object;
            return pdu.getSequenceNumber() == getSequenceNumber();
        } else
        {
            return false;
        }
    }

    public void setApplicationSpecificInfo(Object key, Object value)
    {
        if(applicationSpecificInfo == null)
            applicationSpecificInfo = new Hashtable();
        SmppObject.debug.write(5, "setting app spec info key=\"" + key + "\" value=\"" + (value != null ? value : "null") + "\"");
        applicationSpecificInfo.put(key, value);
    }

    public void setApplicationSpecificInfo(Dictionary applicationSpecificInfo)
    {
        this.applicationSpecificInfo = cloneApplicationSpecificInfo(applicationSpecificInfo);
    }

    public Object getApplicationSpecificInfo(Object key)
    {
        Object value = null;
        if(applicationSpecificInfo != null)
            value = applicationSpecificInfo.get(key);
        SmppObject.debug.write(5, "getting app spec info key=\"" + key + "\" value=\"" + (value != null ? value : "null") + "\"");
        return value;
    }

    public Dictionary getApplicationSpecificInfo()
    {
        return cloneApplicationSpecificInfo(applicationSpecificInfo);
    }

    public void removeApplicationSpecificInfo(Object key)
    {
        if(applicationSpecificInfo != null)
            applicationSpecificInfo.remove(key);
    }

    private Dictionary cloneApplicationSpecificInfo(Dictionary info)
    {
        Dictionary newInfo = null;
        if(info != null)
        {
            newInfo = new Hashtable();
            Object key;
            Object value;
            for(Enumeration keys = info.keys(); keys.hasMoreElements(); newInfo.put(key, value))
            {
                key = keys.nextElement();
                value = info.get(key);
            }

        }
        return newInfo;
    }

    public static final byte VALID_NONE = 0;
    public static final byte VALID_HEADER = 1;
    public static final byte VALID_BODY = 2;
    public static final byte VALID_ALL = 3;
    private static Vector pduList;
    private static int sequenceNumber = 0;
    private boolean sequenceNumberChanged;
    private PDUHeader header;
    private Vector optionalParameters;
    private Vector extraOptionalParameters;
    private byte valid;
    private Dictionary applicationSpecificInfo;

    static 
    {
        pduList = null;
        pduList = new Vector(30, 4);
        pduList.add(new BindTransmitter());
        pduList.add(new BindTransmitterResp());
        pduList.add(new BindReceiver());
        pduList.add(new BindReceiverResp());
        pduList.add(new BindTransciever());
        pduList.add(new BindTranscieverResp());
        pduList.add(new Unbind());
        pduList.add(new UnbindResp());
        pduList.add(new Outbind());
        pduList.add(new SubmitSM());
        pduList.add(new SubmitSMResp());
        pduList.add(new SubmitMultiSM());
        pduList.add(new SubmitMultiSMResp());
        pduList.add(new DeliverSM());
        pduList.add(new DeliverSMResp());
        pduList.add(new DataSM());
        pduList.add(new DataSMResp());
        pduList.add(new QuerySM());
        pduList.add(new QuerySMResp());
        pduList.add(new CancelSM());
        pduList.add(new CancelSMResp());
        pduList.add(new ReplaceSM());
        pduList.add(new ReplaceSMResp());
        pduList.add(new EnquireLink());
        pduList.add(new EnquireLinkResp());
        pduList.add(new AlertNotification());
        pduList.add(new GenericNack());
    }
}
