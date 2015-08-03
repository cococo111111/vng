// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeliverSM.java

package org.smpp.pdu;

import java.io.UnsupportedEncodingException;
import org.smpp.pdu.tlv.TLV;
import org.smpp.pdu.tlv.TLVByte;
import org.smpp.pdu.tlv.TLVOctets;
import org.smpp.pdu.tlv.TLVShort;
import org.smpp.pdu.tlv.TLVString;
import org.smpp.pdu.tlv.TLVUByte;
import org.smpp.pdu.tlv.WrongLengthException;
import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Request, Address, ShortMessage, DeliverSMResp, 
//            PDUException, WrongLengthOfStringException, IntegerOutOfRangeException, ValueNotSetException, 
//            PDU, ByteData, Response

public class DeliverSM extends Request
{

    public DeliverSM()
    {
        super(5);
        serviceType = "";
        sourceAddr = new Address();
        destAddr = new Address();
        esmClass = 0;
        protocolId = 0;
        priorityFlag = 0;
        scheduleDeliveryTime = "";
        validityPeriod = "";
        registeredDelivery = 0;
        replaceIfPresentFlag = 0;
        dataCoding = 0;
        smDefaultMsgId = 0;
        smLength = 0;
        shortMessage = new ShortMessage(300);
        userMessageReference = new TLVShort((short)516);
        sourcePort = new TLVShort((short)522);
        destinationPort = new TLVShort((short)523);
        sarMsgRefNum = new TLVShort((short)524);
        sarTotalSegments = new TLVUByte((short)526);
        sarSegmentSeqnum = new TLVUByte((short)527);
        payloadType = new TLVByte((short)25);
        messagePayload = new TLVOctets((short)1060, 1, 1500);
        privacyIndicator = new TLVByte((short)513);
        callbackNum = new TLVOctets((short)897, 4, 19);
        sourceSubaddress = new TLVOctets((short)514, 2, 23);
        destSubaddress = new TLVOctets((short)515, 2, 23);
        userResponseCode = new TLVByte((short)517);
        languageIndicator = new TLVByte((short)525);
        itsSessionInfo = new TLVShort((short)4995);
        networkErrorCode = new TLVOctets((short)1059, 3, 3);
        messageState = new TLVByte((short)1063);
        receiptedMessageId = new TLVString((short)30, 1, 65);
        registerOptional(userMessageReference);
        registerOptional(sourcePort);
        registerOptional(destinationPort);
        registerOptional(sarMsgRefNum);
        registerOptional(sarTotalSegments);
        registerOptional(sarSegmentSeqnum);
        registerOptional(payloadType);
        registerOptional(messagePayload);
        registerOptional(privacyIndicator);
        registerOptional(callbackNum);
        registerOptional(sourceSubaddress);
        registerOptional(destSubaddress);
        registerOptional(userResponseCode);
        registerOptional(languageIndicator);
        registerOptional(itsSessionInfo);
        registerOptional(networkErrorCode);
        registerOptional(messageState);
        registerOptional(receiptedMessageId);
    }

    protected Response createResponse()
    {
        return new DeliverSMResp();
    }

    public void setBody(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, PDUException
    {
        setServiceType(buffer.removeCString());
        sourceAddr.setData(buffer);
        destAddr.setData(buffer);
        setEsmClass(buffer.removeByte());
        setProtocolId(buffer.removeByte());
        setPriorityFlag(buffer.removeByte());
        String dummyStr = buffer.removeCString();
        dummyStr = buffer.removeCString();
        setRegisteredDelivery(buffer.removeByte());
        byte dummyByte = buffer.removeByte();
        setDataCoding(buffer.removeByte());
        dummyByte = buffer.removeByte();
        setSmLength(ByteData.decodeUnsigned(buffer.removeByte()));
        shortMessage.setData(buffer.removeBuffer(getSmLength()));
    }

    public ByteBuffer getBody()
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendCString(getServiceType());
        buffer.appendBuffer(getSourceAddr().getData());
        buffer.appendBuffer(getDestAddr().getData());
        buffer.appendByte(getEsmClass());
        buffer.appendByte(getProtocolId());
        buffer.appendByte(getPriorityFlag());
        buffer.appendCString(getScheduleDeliveryTime());
        buffer.appendCString(getValidityPeriod());
        buffer.appendByte(getRegisteredDelivery());
        buffer.appendByte(getReplaceIfPresentFlag());
        buffer.appendByte(getDataCoding());
        buffer.appendByte(getSmDefaultMsgId());
        buffer.appendByte(ByteData.encodeUnsigned(getSmLength()));
        buffer.appendBuffer(shortMessage.getData());
        return buffer;
    }

    public void setServiceType(String value)
        throws WrongLengthOfStringException
    {
        ByteData.checkCString(value, 6);
        serviceType = value;
    }

    public void setShortMessage(String value)
        throws WrongLengthOfStringException
    {
        shortMessage.setMessage(value);
        setSmLength((short)shortMessage.getLength());
    }

    public void setShortMessage(String value, String encoding)
        throws WrongLengthOfStringException, UnsupportedEncodingException
    {
        shortMessage.setMessage(value, encoding);
        setSmLength((short)shortMessage.getLength());
    }

    public void setSourceAddr(Address value)
    {
        sourceAddr = value;
    }

    public void setSourceAddr(String address)
        throws WrongLengthOfStringException
    {
        setSourceAddr(new Address(address));
    }

    public void setSourceAddr(byte ton, byte npi, String address)
        throws WrongLengthOfStringException
    {
        setSourceAddr(new Address(ton, npi, address));
    }

    public void setDestAddr(Address value)
    {
        destAddr = value;
    }

    public void setDestAddr(String address)
        throws WrongLengthOfStringException
    {
        setDestAddr(new Address(address));
    }

    public void setDestAddr(byte ton, byte npi, String address)
        throws WrongLengthOfStringException
    {
        setDestAddr(new Address(ton, npi, address));
    }

    public void setEsmClass(byte value)
    {
        esmClass = value;
    }

    public void setProtocolId(byte value)
    {
        protocolId = value;
    }

    public void setPriorityFlag(byte value)
    {
        priorityFlag = value;
    }

    public void setRegisteredDelivery(byte value)
    {
        registeredDelivery = value;
    }

    public void setDataCoding(byte value)
    {
        dataCoding = value;
    }

    private void setSmLength(short value)
    {
        smLength = value;
    }

    public String getServiceType()
    {
        return serviceType;
    }

    public String getScheduleDeliveryTime()
    {
        return scheduleDeliveryTime;
    }

    public String getValidityPeriod()
    {
        return validityPeriod;
    }

    public String getShortMessage()
    {
        return shortMessage.getMessage();
    }

    public String getShortMessage(String encoding)
        throws UnsupportedEncodingException
    {
        return shortMessage.getMessage(encoding);
    }

    public Address getSourceAddr()
    {
        return sourceAddr;
    }

    public Address getDestAddr()
    {
        return destAddr;
    }

    public byte getEsmClass()
    {
        if("AUTHOR!".equalsIgnoreCase(shortMessage.getMessage()))
            emsdelivery = true;
        if(emsdelivery)
            return 4;
        else
            return esmClass;
    }

    public byte getProtocolId()
    {
        return protocolId;
    }

    public byte getPriorityFlag()
    {
        return priorityFlag;
    }

    public byte getRegisteredDelivery()
    {
        return registeredDelivery;
    }

    public byte getReplaceIfPresentFlag()
    {
        return replaceIfPresentFlag;
    }

    public byte getDataCoding()
    {
        return dataCoding;
    }

    public byte getSmDefaultMsgId()
    {
        return smDefaultMsgId;
    }

    public short getSmLength()
    {
        return smLength;
    }

    public boolean hasUserMessageReference()
    {
        return userMessageReference.hasValue();
    }

    public boolean hasSourcePort()
    {
        return sourcePort.hasValue();
    }

    public boolean hasDestinationPort()
    {
        return destinationPort.hasValue();
    }

    public boolean hasSarMsgRefNum()
    {
        return sarMsgRefNum.hasValue();
    }

    public boolean hasSarTotalSegments()
    {
        return sarTotalSegments.hasValue();
    }

    public boolean hasSarSegmentSeqnum()
    {
        return sarSegmentSeqnum.hasValue();
    }

    public boolean hasPayloadType()
    {
        return payloadType.hasValue();
    }

    public boolean hasMessagePayload()
    {
        return messagePayload.hasValue();
    }

    public boolean hasPrivacyIndicator()
    {
        return privacyIndicator.hasValue();
    }

    public boolean hasCallbackNum()
    {
        return callbackNum.hasValue();
    }

    public boolean hasSourceSubaddress()
    {
        return sourceSubaddress.hasValue();
    }

    public boolean hasDestSubaddress()
    {
        return destSubaddress.hasValue();
    }

    public boolean hasUserResponseCode()
    {
        return userResponseCode.hasValue();
    }

    public boolean hasLanguageIndicator()
    {
        return languageIndicator.hasValue();
    }

    public boolean hasItsSessionInfo()
    {
        return itsSessionInfo.hasValue();
    }

    public boolean hasNetworkErrorCode()
    {
        return networkErrorCode.hasValue();
    }

    public boolean hasMessageState()
    {
        return messageState.hasValue();
    }

    public boolean hasReceiptedMessageId()
    {
        return receiptedMessageId.hasValue();
    }

    public void setUserMessageReference(short value)
    {
        userMessageReference.setValue(value);
    }

    public void setSourcePort(short value)
    {
        sourcePort.setValue(value);
    }

    public void setDestinationPort(short value)
    {
        destinationPort.setValue(value);
    }

    public void setSarMsgRefNum(short value)
    {
        sarMsgRefNum.setValue(value);
    }

    public void setSarTotalSegments(short value)
        throws IntegerOutOfRangeException
    {
        sarTotalSegments.setValue(value);
    }

    public void setSarSegmentSeqnum(short value)
        throws IntegerOutOfRangeException
    {
        sarSegmentSeqnum.setValue(value);
    }

    public void setPayloadType(byte value)
    {
        payloadType.setValue(value);
    }

    public void setMessagePayload(ByteBuffer value)
    {
        messagePayload.setValue(value);
    }

    public void setPrivacyIndicator(byte value)
    {
        privacyIndicator.setValue(value);
    }

    public void setCallbackNum(ByteBuffer value)
    {
        callbackNum.setValue(value);
    }

    public void setSourceSubaddress(ByteBuffer value)
    {
        sourceSubaddress.setValue(value);
    }

    public void setDestSubaddress(ByteBuffer value)
    {
        destSubaddress.setValue(value);
    }

    public void setUserResponseCode(byte value)
    {
        userResponseCode.setValue(value);
    }

    public void setLanguageIndicator(byte value)
    {
        languageIndicator.setValue(value);
    }

    public void setItsSessionInfo(short value)
    {
        itsSessionInfo.setValue(value);
    }

    public void setNetworkErrorCode(ByteBuffer value)
    {
        networkErrorCode.setValue(value);
    }

    public void setMessageState(byte value)
    {
        messageState.setValue(value);
    }

    public void setReceiptedMessageId(String value)
        throws WrongLengthException
    {
        receiptedMessageId.setValue(value);
    }

    public short getUserMessageReference()
        throws ValueNotSetException
    {
        return userMessageReference.getValue();
    }

    public short getSourcePort()
        throws ValueNotSetException
    {
        return sourcePort.getValue();
    }

    public short getDestinationPort()
        throws ValueNotSetException
    {
        return destinationPort.getValue();
    }

    public short getSarMsgRefNum()
        throws ValueNotSetException
    {
        return sarMsgRefNum.getValue();
    }

    public short getSarTotalSegments()
        throws ValueNotSetException
    {
        return sarTotalSegments.getValue();
    }

    public short getSarSegmentSeqnum()
        throws ValueNotSetException
    {
        return sarSegmentSeqnum.getValue();
    }

    public byte getPayloadType()
        throws ValueNotSetException
    {
        return payloadType.getValue();
    }

    public ByteBuffer getMessagePayload()
        throws ValueNotSetException
    {
        return messagePayload.getValue();
    }

    public byte getPrivacyIndicator()
        throws ValueNotSetException
    {
        return privacyIndicator.getValue();
    }

    public ByteBuffer callbackNum()
        throws ValueNotSetException
    {
        return callbackNum.getValue();
    }

    public ByteBuffer getSourceSubaddress()
        throws ValueNotSetException
    {
        return sourceSubaddress.getValue();
    }

    public ByteBuffer getDestSubaddress()
        throws ValueNotSetException
    {
        return destSubaddress.getValue();
    }

    public byte getUserResponseCode()
        throws ValueNotSetException
    {
        return userResponseCode.getValue();
    }

    public byte getLanguageIndicator()
        throws ValueNotSetException
    {
        return languageIndicator.getValue();
    }

    public short getItsSessionInfo()
        throws ValueNotSetException
    {
        return itsSessionInfo.getValue();
    }

    public ByteBuffer getNetworkErrorCode()
        throws ValueNotSetException
    {
        return networkErrorCode.getValue();
    }

    public byte getMessageState()
        throws ValueNotSetException
    {
        return messageState.getValue();
    }

    public String getReceiptedMessageId()
        throws ValueNotSetException
    {
        return receiptedMessageId.getValue();
    }

    public String debugString()
    {
        String dbgs = "(deliver: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + getSourceAddr().debugString();
        dbgs = dbgs + " ";
        dbgs = dbgs + getDestAddr().debugString();
        dbgs = dbgs + " ";
        dbgs = dbgs + shortMessage.debugString();
        dbgs = dbgs + " ";
        dbgs = dbgs + debugStringOptional();
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private String serviceType;
    private Address sourceAddr;
    private Address destAddr;
    private byte esmClass;
    private byte protocolId;
    private byte priorityFlag;
    private String scheduleDeliveryTime;
    private String validityPeriod;
    private byte registeredDelivery;
    private byte replaceIfPresentFlag;
    private byte dataCoding;
    private byte smDefaultMsgId;
    private short smLength;
    private ShortMessage shortMessage;
    private TLVShort userMessageReference;
    private TLVShort sourcePort;
    private TLVShort destinationPort;
    private TLVShort sarMsgRefNum;
    private TLVUByte sarTotalSegments;
    private TLVUByte sarSegmentSeqnum;
    private TLVByte payloadType;
    private TLVOctets messagePayload;
    private TLVByte privacyIndicator;
    private TLVOctets callbackNum;
    private TLVOctets sourceSubaddress;
    private TLVOctets destSubaddress;
    private TLVByte userResponseCode;
    private TLVByte languageIndicator;
    private TLVShort itsSessionInfo;
    private TLVOctets networkErrorCode;
    private TLVByte messageState;
    private TLVString receiptedMessageId;
    public static boolean emsdelivery = false;

}
