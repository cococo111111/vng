// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SubmitSM.java

package org.smpp.pdu;

import java.io.UnsupportedEncodingException;
import org.smpp.pdu.tlv.TLVByte;
import org.smpp.pdu.tlv.TLVEmpty;
import org.smpp.pdu.tlv.TLVOctets;
import org.smpp.pdu.tlv.TLVShort;
import org.smpp.pdu.tlv.TLVUByte;
import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Request, Address, ShortMessage, SubmitSMResp, 
//            ValueNotSetException, PDUException, WrongLengthOfStringException, IntegerOutOfRangeException, 
//            WrongDateFormatException, ByteData, Response

public class SubmitSM extends Request
{

    public SubmitSM()
    {
        super(4);
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
        shortMessage = new ShortMessage(254);
        userMessageReference = new TLVShort((short)516);
        sourcePort = new TLVShort((short)522);
        sourceAddrSubunit = new TLVByte((short)13);
        destinationPort = new TLVShort((short)523);
        destAddrSubunit = new TLVByte((short)5);
        sarMsgRefNum = new TLVShort((short)524);
        sarTotalSegments = new TLVUByte((short)526);
        sarSegmentSeqnum = new TLVUByte((short)527);
        moreMsgsToSend = new TLVByte((short)1062);
        payloadType = new TLVByte((short)25);
        messagePayload = new TLVOctets((short)1060, 1, 1500);
        privacyIndicator = new TLVByte((short)513);
        callbackNum = new TLVOctets((short)897, 4, 19);
        callbackNumPresInd = new TLVByte((short)770);
        callbackNumAtag = new TLVOctets((short)771, 1, 65);
        sourceSubaddress = new TLVOctets((short)514, 2, 23);
        destSubaddress = new TLVOctets((short)515, 2, 23);
        userResponseCode = new TLVByte((short)517);
        displayTime = new TLVByte((short)4609);
        smsSignal = new TLVShort((short)4611);
        msValidity = new TLVByte((short)4612);
        msMsgWaitFacilities = new TLVByte((short)2);
        numberOfMessages = new TLVByte((short)772);
        alertOnMsgDelivery = new TLVEmpty((short)4876);
        languageIndicator = new TLVByte((short)525);
        itsReplyType = new TLVByte((short)4992);
        itsSessionInfo = new TLVShort((short)4995);
        ussdServiceOp = new TLVByte((short)1281);
        sfoneChargeFlag = new TLVShort((short)5120);
        registerOptional(userMessageReference);
        registerOptional(sourcePort);
        registerOptional(sourceAddrSubunit);
        registerOptional(destinationPort);
        registerOptional(destAddrSubunit);
        registerOptional(sarMsgRefNum);
        registerOptional(sarTotalSegments);
        registerOptional(sarSegmentSeqnum);
        registerOptional(moreMsgsToSend);
        registerOptional(payloadType);
        registerOptional(messagePayload);
        registerOptional(privacyIndicator);
        registerOptional(callbackNum);
        registerOptional(callbackNumPresInd);
        registerOptional(callbackNumAtag);
        registerOptional(sourceSubaddress);
        registerOptional(destSubaddress);
        registerOptional(userResponseCode);
        registerOptional(displayTime);
        registerOptional(smsSignal);
        registerOptional(msValidity);
        registerOptional(msMsgWaitFacilities);
        registerOptional(numberOfMessages);
        registerOptional(alertOnMsgDelivery);
        registerOptional(languageIndicator);
        registerOptional(itsReplyType);
        registerOptional(itsSessionInfo);
        registerOptional(ussdServiceOp);
        registerOptional(sfoneChargeFlag);
    }

    public ByteBuffer callbackNum()
        throws ValueNotSetException
    {
        return callbackNum.getValue();
    }

    protected Response createResponse()
    {
        return new SubmitSMResp();
    }

    public String debugString()
    {
        String dbgs = "(submit: ";
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

    public boolean getAlertOnMsgDelivery()
        throws ValueNotSetException
    {
        return alertOnMsgDelivery.getValue();
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

    public ByteBuffer getCallbackNumAtag()
        throws ValueNotSetException
    {
        return callbackNumAtag.getValue();
    }

    public byte getCallbackNumPresInd()
        throws ValueNotSetException
    {
        return callbackNumPresInd.getValue();
    }

    public byte getDataCoding()
    {
        return dataCoding;
    }

    public Address getDestAddr()
    {
        return destAddr;
    }

    public byte getDestAddrSubunit()
        throws ValueNotSetException
    {
        return destAddrSubunit.getValue();
    }

    public ByteBuffer getDestSubaddress()
        throws ValueNotSetException
    {
        return destSubaddress.getValue();
    }

    public short getDestinationPort()
        throws ValueNotSetException
    {
        return destinationPort.getValue();
    }

    public byte getDisplayTime()
        throws ValueNotSetException
    {
        return displayTime.getValue();
    }

    public byte getEsmClass()
    {
        return esmClass;
    }

    public byte getItsReplyType()
        throws ValueNotSetException
    {
        return itsReplyType.getValue();
    }

    public short getItsSessionInfo()
        throws ValueNotSetException
    {
        return itsSessionInfo.getValue();
    }

    public byte getLanguageIndicator()
        throws ValueNotSetException
    {
        return languageIndicator.getValue();
    }

    public ByteBuffer getMessagePayload()
        throws ValueNotSetException
    {
        return messagePayload.getValue();
    }

    public byte getMoreMsgsToSend()
        throws ValueNotSetException
    {
        return moreMsgsToSend.getValue();
    }

    public byte getMsMsgWaitFacilities()
        throws ValueNotSetException
    {
        return msMsgWaitFacilities.getValue();
    }

    public byte getMsValidity()
        throws ValueNotSetException
    {
        return msValidity.getValue();
    }

    public byte getNumberOfMessages()
        throws ValueNotSetException
    {
        return numberOfMessages.getValue();
    }

    public byte getPayloadType()
        throws ValueNotSetException
    {
        return payloadType.getValue();
    }

    public byte getPriorityFlag()
    {
        return priorityFlag;
    }

    public byte getPrivacyIndicator()
        throws ValueNotSetException
    {
        return privacyIndicator.getValue();
    }

    public byte getProtocolId()
    {
        return protocolId;
    }

    public byte getRegisteredDelivery()
    {
        return registeredDelivery;
    }

    public byte getReplaceIfPresentFlag()
    {
        return replaceIfPresentFlag;
    }

    public short getSarMsgRefNum()
        throws ValueNotSetException
    {
        return sarMsgRefNum.getValue();
    }

    public short getSarSegmentSeqnum()
        throws ValueNotSetException
    {
        return sarSegmentSeqnum.getValue();
    }

    public short getSarTotalSegments()
        throws ValueNotSetException
    {
        return sarTotalSegments.getValue();
    }

    public String getScheduleDeliveryTime()
    {
        return scheduleDeliveryTime;
    }

    public String getServiceType()
    {
        return serviceType;
    }

    public String getShortMessage(String encoding)
        throws UnsupportedEncodingException
    {
        return shortMessage.getMessage(encoding);
    }

    public String getShortMessage()
    {
        return shortMessage.getMessage();
    }

    public ByteBuffer getShortMessageData()
    {
        return shortMessage.getData();
    }

    public byte getSmDefaultMsgId()
    {
        return smDefaultMsgId;
    }

    public short getSmLength()
    {
        return smLength;
    }

    public short getSmsSignal()
        throws ValueNotSetException
    {
        return smsSignal.getValue();
    }

    public Address getSourceAddr()
    {
        return sourceAddr;
    }

    public byte getSourceAddrSubunit()
        throws ValueNotSetException
    {
        return sourceAddrSubunit.getValue();
    }

    public short getSourcePort()
        throws ValueNotSetException
    {
        return sourcePort.getValue();
    }

    public ByteBuffer getSourceSubaddress()
        throws ValueNotSetException
    {
        return sourceSubaddress.getValue();
    }

    public short getUserMessageReference()
        throws ValueNotSetException
    {
        return userMessageReference.getValue();
    }

    public byte getUserResponseCode()
        throws ValueNotSetException
    {
        return userResponseCode.getValue();
    }

    public byte getUssdServiceOp()
        throws ValueNotSetException
    {
        return ussdServiceOp.getValue();
    }

    public String getValidityPeriod()
    {
        return validityPeriod;
    }

    public boolean hasAlertOnMsgDelivery()
    {
        return alertOnMsgDelivery.hasValue();
    }

    public boolean hasCallbackNum()
    {
        return callbackNum.hasValue();
    }

    public boolean hasCallbackNumAtag()
    {
        return callbackNumAtag.hasValue();
    }

    public boolean hasCallbackNumPresInd()
    {
        return callbackNumPresInd.hasValue();
    }

    public boolean hasDestAddrSubunit()
    {
        return destAddrSubunit.hasValue();
    }

    public boolean hasDestSubaddress()
    {
        return destSubaddress.hasValue();
    }

    public boolean hasDestinationPort()
    {
        return destinationPort.hasValue();
    }

    public boolean hasDisplayTime()
    {
        return displayTime.hasValue();
    }

    public boolean hasItsReplyType()
    {
        return itsReplyType.hasValue();
    }

    public boolean hasItsSessionInfo()
    {
        return itsSessionInfo.hasValue();
    }

    public boolean hasLanguageIndicator()
    {
        return languageIndicator.hasValue();
    }

    public boolean hasMessagePayload()
    {
        return messagePayload.hasValue();
    }

    public boolean hasMoreMsgsToSend()
    {
        return moreMsgsToSend.hasValue();
    }

    public boolean hasMsMsgWaitFacilities()
    {
        return msMsgWaitFacilities.hasValue();
    }

    public boolean hasMsValidity()
    {
        return msValidity.hasValue();
    }

    public boolean hasNumberOfMessages()
    {
        return numberOfMessages.hasValue();
    }

    public boolean hasPayloadType()
    {
        return payloadType.hasValue();
    }

    public boolean hasPrivacyIndicator()
    {
        return privacyIndicator.hasValue();
    }

    public boolean hasSarMsgRefNum()
    {
        return sarMsgRefNum.hasValue();
    }

    public boolean hasSarSegmentSeqnum()
    {
        return sarSegmentSeqnum.hasValue();
    }

    public boolean hasSarTotalSegments()
    {
        return sarTotalSegments.hasValue();
    }

    public boolean hasSmsSignal()
    {
        return smsSignal.hasValue();
    }

    public boolean hasSourceAddrSubunit()
    {
        return sourceAddrSubunit.hasValue();
    }

    public boolean hasSourcePort()
    {
        return sourcePort.hasValue();
    }

    public boolean hasSourceSubaddress()
    {
        return sourceSubaddress.hasValue();
    }

    public boolean hasUserMessageReference()
    {
        return userMessageReference.hasValue();
    }

    public boolean hasUserResponseCode()
    {
        return userResponseCode.hasValue();
    }

    public boolean hasUssdServiceOp()
    {
        return ussdServiceOp.hasValue();
    }

    public void setAlertOnMsgDelivery(boolean value)
    {
        alertOnMsgDelivery.setValue(value);
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
        setScheduleDeliveryTime(buffer.removeCString());
        setValidityPeriod(buffer.removeCString());
        setRegisteredDelivery(buffer.removeByte());
        setReplaceIfPresentFlag(buffer.removeByte());
        setDataCoding(buffer.removeByte());
        setSmDefaultMsgId(buffer.removeByte());
        setSmLength(ByteData.decodeUnsigned(buffer.removeByte()));
        shortMessage.setData(buffer.removeBuffer(getSmLength()));
    }

    public void setCallbackNum(ByteBuffer value)
    {
        callbackNum.setValue(value);
    }

    public void setCallbackNumAtag(ByteBuffer value)
    {
        callbackNumAtag.setValue(value);
    }

    public void setCallbackNumPresInd(byte value)
    {
        callbackNumPresInd.setValue(value);
    }

    public void setDataCoding(byte value)
    {
        dataCoding = value;
    }

    public void setDestAddr(byte ton, byte npi, String address)
        throws WrongLengthOfStringException
    {
        setDestAddr(new Address(ton, npi, address));
    }

    public void setDestAddr(String address)
        throws WrongLengthOfStringException
    {
        setDestAddr(new Address(address));
    }

    public void setDestAddr(Address value)
    {
        destAddr = value;
    }

    public void setDestAddrSubunit(byte value)
    {
        destAddrSubunit.setValue(value);
    }

    public void setDestSubaddress(ByteBuffer value)
    {
        destSubaddress.setValue(value);
    }

    public void setDestinationPort(short value)
    {
        destinationPort.setValue(value);
    }

    public void setDisplayTime(byte value)
    {
        displayTime.setValue(value);
    }

    public void setEsmClass(byte value)
    {
        esmClass = value;
    }

    public void setItsReplyType(byte value)
    {
        itsReplyType.setValue(value);
    }

    public void setItsSessionInfo(short value)
    {
        itsSessionInfo.setValue(value);
    }

    public void setLanguageIndicator(byte value)
    {
        languageIndicator.setValue(value);
    }

    public void setMessagePayload(ByteBuffer value)
    {
        messagePayload.setValue(value);
    }

    public void setMoreMsgsToSend(byte value)
    {
        moreMsgsToSend.setValue(value);
    }

    public void setMsMsgWaitFacilities(byte value)
    {
        msMsgWaitFacilities.setValue(value);
    }

    public void setMsValidity(byte value)
    {
        msValidity.setValue(value);
    }

    public void setNumberOfMessages(byte value)
    {
        numberOfMessages.setValue(value);
    }

    public void setPayloadType(byte value)
    {
        payloadType.setValue(value);
    }

    public void setPriorityFlag(byte value)
    {
        priorityFlag = value;
    }

    public void setPrivacyIndicator(byte value)
    {
        privacyIndicator.setValue(value);
    }

    public void setProtocolId(byte value)
    {
        protocolId = value;
    }

    public void setRegisteredDelivery(byte value)
    {
        registeredDelivery = value;
    }

    public void setReplaceIfPresentFlag(byte value)
    {
        replaceIfPresentFlag = value;
    }

    public void setSarMsgRefNum(short value)
    {
        sarMsgRefNum.setValue(value);
    }

    public void setSarSegmentSeqnum(short value)
        throws IntegerOutOfRangeException
    {
        sarSegmentSeqnum.setValue(value);
    }

    public void setSarTotalSegments(short value)
        throws IntegerOutOfRangeException
    {
        sarTotalSegments.setValue(value);
    }

    public void setScheduleDeliveryTime(String value)
        throws WrongDateFormatException
    {
        ByteData.checkDate(value);
        scheduleDeliveryTime = value;
    }

    public void setServiceType(String value)
        throws WrongLengthOfStringException
    {
        ByteData.checkCString(value, 6);
        serviceType = value;
    }

    public void setSfoneChargeFlag(short value)
    {
        sfoneChargeFlag.setValue(value);
    }

    public void setShortMessage(String value, String encoding)
        throws WrongLengthOfStringException, UnsupportedEncodingException
    {
        shortMessage.setMessage(value, encoding);
        setSmLength((short)shortMessage.getLength());
    }

    public void setShortMessage(String value)
        throws WrongLengthOfStringException
    {
        shortMessage.setMessage(value);
        setSmLength((short)shortMessage.getLength());
    }

    public void setShortMessageData(ByteBuffer value)
        throws PDUException, NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException
    {
        shortMessage.setData(value);
        setSmLength((short)shortMessage.getLength());
    }

    public void setSmDefaultMsgId(byte value)
    {
        smDefaultMsgId = value;
    }

    private void setSmLength(short value)
    {
        smLength = value;
    }

    public void setSmsSignal(short value)
    {
        smsSignal.setValue(value);
    }

    public void setSourceAddr(byte ton, byte npi, String address)
        throws WrongLengthOfStringException
    {
        setSourceAddr(new Address(ton, npi, address));
    }

    public void setSourceAddr(String address)
        throws WrongLengthOfStringException
    {
        setSourceAddr(new Address(address));
    }

    public void setSourceAddr(Address value)
    {
        sourceAddr = value;
    }

    public void setSourceAddrSubunit(byte value)
    {
        sourceAddrSubunit.setValue(value);
    }

    public void setSourcePort(short value)
    {
        sourcePort.setValue(value);
    }

    public void setSourceSubaddress(ByteBuffer value)
    {
        sourceSubaddress.setValue(value);
    }

    public void setUserMessageReference(short value)
    {
        userMessageReference.setValue(value);
    }

    public void setUserResponseCode(byte value)
    {
        userResponseCode.setValue(value);
    }

    public void setUssdServiceOp(byte value)
    {
        ussdServiceOp.setValue(value);
    }

    public void setValidityPeriod(String value)
        throws WrongDateFormatException
    {
        ByteData.checkDate(value);
        validityPeriod = value;
    }

    private TLVEmpty alertOnMsgDelivery;
    private TLVOctets callbackNum;
    private TLVOctets callbackNumAtag;
    private TLVByte callbackNumPresInd;
    private byte dataCoding;
    private Address destAddr;
    private TLVByte destAddrSubunit;
    private TLVOctets destSubaddress;
    private TLVShort destinationPort;
    private TLVByte displayTime;
    private byte esmClass;
    private TLVByte itsReplyType;
    private TLVShort itsSessionInfo;
    private TLVByte languageIndicator;
    private TLVOctets messagePayload;
    private TLVByte moreMsgsToSend;
    private TLVByte msMsgWaitFacilities;
    private TLVByte msValidity;
    private TLVByte numberOfMessages;
    private TLVByte payloadType;
    private byte priorityFlag;
    private TLVByte privacyIndicator;
    private byte protocolId;
    private byte registeredDelivery;
    private byte replaceIfPresentFlag;
    private TLVShort sarMsgRefNum;
    private TLVUByte sarSegmentSeqnum;
    private TLVUByte sarTotalSegments;
    private String scheduleDeliveryTime;
    private String serviceType;
    private TLVShort sfoneChargeFlag;
    private ShortMessage shortMessage;
    private byte smDefaultMsgId;
    private short smLength;
    private TLVShort smsSignal;
    private Address sourceAddr;
    private TLVByte sourceAddrSubunit;
    private TLVShort sourcePort;
    private TLVOctets sourceSubaddress;
    private TLVShort userMessageReference;
    private TLVByte userResponseCode;
    private TLVByte ussdServiceOp;
    private String validityPeriod;
}
