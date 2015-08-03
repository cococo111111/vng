// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataSM.java

package org.smpp.pdu;

import org.smpp.pdu.tlv.TLV;
import org.smpp.pdu.tlv.TLVByte;
import org.smpp.pdu.tlv.TLVEmpty;
import org.smpp.pdu.tlv.TLVInt;
import org.smpp.pdu.tlv.TLVOctets;
import org.smpp.pdu.tlv.TLVShort;
import org.smpp.pdu.tlv.TLVString;
import org.smpp.pdu.tlv.TLVUByte;
import org.smpp.pdu.tlv.WrongLengthException;
import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Request, Address, DataSMResp, PDUException, 
//            WrongLengthOfStringException, IntegerOutOfRangeException, ValueNotSetException, PDU, 
//            ByteData, Response

public class DataSM extends Request
{

    public DataSM()
    {
        super(259);
        serviceType = "";
        sourceAddr = new Address(65);
        destAddr = new Address(65);
        esmClass = 0;
        registeredDelivery = 0;
        dataCoding = 0;
        userMessageReference = new TLVShort((short)516);
        sourcePort = new TLVShort((short)522);
        sourceAddrSubunit = new TLVByte((short)13);
        sourceNetworkType = new TLVByte((short)14);
        sourceBearerType = new TLVByte((short)15);
        sourceTelematicsId = new TLVByte((short)16);
        destinationPort = new TLVShort((short)523);
        destAddrSubunit = new TLVByte((short)5);
        destNetworkType = new TLVByte((short)6);
        destBearerType = new TLVByte((short)7);
        destTelematicsId = new TLVShort((short)8);
        sarMsgRefNum = new TLVShort((short)524);
        sarTotalSegments = new TLVUByte((short)526);
        sarSegmentSeqnum = new TLVUByte((short)527);
        moreMsgsToSend = new TLVByte((short)1062);
        qosTimeToLive = new TLVInt((short)23);
        payloadType = new TLVByte((short)25);
        messagePayload = new TLVOctets((short)1060, 1, 1500);
        setDpf = new TLVByte((short)1057);
        receiptedMessageId = new TLVString((short)30, 1, 65);
        messageState = new TLVByte((short)1063);
        networkErrorCode = new TLVOctets((short)1059, 3, 3);
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
        registerOptional(userMessageReference);
        registerOptional(sourcePort);
        registerOptional(sourceAddrSubunit);
        registerOptional(sourceNetworkType);
        registerOptional(sourceBearerType);
        registerOptional(sourceTelematicsId);
        registerOptional(destinationPort);
        registerOptional(destAddrSubunit);
        registerOptional(destNetworkType);
        registerOptional(destBearerType);
        registerOptional(destTelematicsId);
        registerOptional(sarMsgRefNum);
        registerOptional(sarTotalSegments);
        registerOptional(sarSegmentSeqnum);
        registerOptional(moreMsgsToSend);
        registerOptional(qosTimeToLive);
        registerOptional(payloadType);
        registerOptional(messagePayload);
        registerOptional(setDpf);
        registerOptional(receiptedMessageId);
        registerOptional(messageState);
        registerOptional(networkErrorCode);
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
    }

    protected Response createResponse()
    {
        return new DataSMResp();
    }

    public void setBody(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, PDUException
    {
        setServiceType(buffer.removeCString());
        sourceAddr.setData(buffer);
        destAddr.setData(buffer);
        setEsmClass(buffer.removeByte());
        setRegisteredDelivery(buffer.removeByte());
        setDataCoding(buffer.removeByte());
    }

    public ByteBuffer getBody()
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendCString(getServiceType());
        buffer.appendBuffer(getSourceAddr().getData());
        buffer.appendBuffer(getDestAddr().getData());
        buffer.appendByte(getEsmClass());
        buffer.appendByte(getRegisteredDelivery());
        buffer.appendByte(getDataCoding());
        return buffer;
    }

    public void setServiceType(String value)
        throws WrongLengthOfStringException
    {
        ByteData.checkCString(value, 6);
        serviceType = value;
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

    public void setRegisteredDelivery(byte value)
    {
        registeredDelivery = value;
    }

    public void setDataCoding(byte value)
    {
        dataCoding = value;
    }

    public String getServiceType()
    {
        return serviceType;
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
        return esmClass;
    }

    public byte getRegisteredDelivery()
    {
        return registeredDelivery;
    }

    public byte getDataCoding()
    {
        return dataCoding;
    }

    public boolean hasUserMessageReference()
    {
        return userMessageReference.hasValue();
    }

    public boolean hasSourcePort()
    {
        return sourcePort.hasValue();
    }

    public boolean hasSourceAddrSubunit()
    {
        return sourceAddrSubunit.hasValue();
    }

    public boolean hasSourceNetworkType()
    {
        return sourceNetworkType.hasValue();
    }

    public boolean hasSourceBearerType()
    {
        return sourceBearerType.hasValue();
    }

    public boolean hasSourceTelematicsId()
    {
        return sourceTelematicsId.hasValue();
    }

    public boolean hasDestinationPort()
    {
        return destinationPort.hasValue();
    }

    public boolean hasDestAddrSubunit()
    {
        return destAddrSubunit.hasValue();
    }

    public boolean hasDestNetworkType()
    {
        return destNetworkType.hasValue();
    }

    public boolean hasDestBearerType()
    {
        return destBearerType.hasValue();
    }

    public boolean hasDestTelematicsId()
    {
        return destTelematicsId.hasValue();
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

    public boolean hasMoreMsgsToSend()
    {
        return moreMsgsToSend.hasValue();
    }

    public boolean hasQosTimeToLive()
    {
        return qosTimeToLive.hasValue();
    }

    public boolean hasPayloadType()
    {
        return payloadType.hasValue();
    }

    public boolean hasMessagePayload()
    {
        return messagePayload.hasValue();
    }

    public boolean hasSetDpf()
    {
        return setDpf.hasValue();
    }

    public boolean hasReceiptedMessageId()
    {
        return receiptedMessageId.hasValue();
    }

    public boolean hasMessageState()
    {
        return messageState.hasValue();
    }

    public boolean hasNetworkErrorCode()
    {
        return networkErrorCode.hasValue();
    }

    public boolean hasPrivacyIndicator()
    {
        return privacyIndicator.hasValue();
    }

    public boolean hasCallbackNum()
    {
        return callbackNum.hasValue();
    }

    public boolean hasCallbackNumPresInd()
    {
        return callbackNumPresInd.hasValue();
    }

    public boolean hasCallbackNumAtag()
    {
        return callbackNumAtag.hasValue();
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

    public boolean hasDisplayTime()
    {
        return displayTime.hasValue();
    }

    public boolean hasSmsSignal()
    {
        return smsSignal.hasValue();
    }

    public boolean hasMsValidity()
    {
        return msValidity.hasValue();
    }

    public boolean hasMsMsgWaitFacilities()
    {
        return msMsgWaitFacilities.hasValue();
    }

    public boolean hasNumberOfMessages()
    {
        return numberOfMessages.hasValue();
    }

    public boolean hasAlertOnMsgDelivery()
    {
        return alertOnMsgDelivery.hasValue();
    }

    public boolean hasLanguageIndicator()
    {
        return languageIndicator.hasValue();
    }

    public boolean hasItsReplyType()
    {
        return itsReplyType.hasValue();
    }

    public boolean hasItsSessionInfo()
    {
        return itsSessionInfo.hasValue();
    }

    public void setUserMessageReference(short value)
    {
        userMessageReference.setValue(value);
    }

    public void setSourcePort(short value)
    {
        sourcePort.setValue(value);
    }

    public void setSourceAddrSubunit(byte value)
    {
        sourceAddrSubunit.setValue(value);
    }

    public void setSourceNetworkType(byte value)
    {
        sourceNetworkType.setValue(value);
    }

    public void setSourceBearerType(byte value)
    {
        sourceBearerType.setValue(value);
    }

    public void setSourceTelematicsId(byte value)
    {
        sourceTelematicsId.setValue(value);
    }

    public void setDestinationPort(short value)
    {
        destinationPort.setValue(value);
    }

    public void setDestAddrSubunit(byte value)
    {
        destAddrSubunit.setValue(value);
    }

    public void setDestNetworkType(byte value)
    {
        destNetworkType.setValue(value);
    }

    public void setDestBearerType(byte value)
    {
        destBearerType.setValue(value);
    }

    public void setDestTelematicsId(short value)
    {
        destTelematicsId.setValue(value);
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

    public void setMoreMsgsToSend(byte value)
    {
        moreMsgsToSend.setValue(value);
    }

    public void setQosTimeToLive(int value)
    {
        qosTimeToLive.setValue(value);
    }

    public void setPayloadType(byte value)
    {
        payloadType.setValue(value);
    }

    public void setMessagePayload(ByteBuffer value)
    {
        messagePayload.setValue(value);
    }

    public void setSetDpf(byte value)
    {
        setDpf.setValue(value);
    }

    public void setReceiptedMessageId(String value)
        throws WrongLengthException
    {
        receiptedMessageId.setValue(value);
    }

    public void setMessageState(byte value)
    {
        messageState.setValue(value);
    }

    public void setNetworkErrorCode(ByteBuffer value)
    {
        networkErrorCode.setValue(value);
    }

    public void setPrivacyIndicator(byte value)
    {
        privacyIndicator.setValue(value);
    }

    public void setCallbackNum(ByteBuffer value)
    {
        callbackNum.setValue(value);
    }

    public void setCallbackNumPresInd(byte value)
    {
        callbackNumPresInd.setValue(value);
    }

    public void setCallbackNumAtag(ByteBuffer value)
    {
        callbackNumAtag.setValue(value);
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

    public void setDisplayTime(byte value)
    {
        displayTime.setValue(value);
    }

    public void setSmsSignal(short value)
    {
        smsSignal.setValue(value);
    }

    public void setMsValidity(byte value)
    {
        msValidity.setValue(value);
    }

    public void setMsMsgWaitFacilities(byte value)
    {
        msMsgWaitFacilities.setValue(value);
    }

    public void setNumberOfMessages(byte value)
    {
        numberOfMessages.setValue(value);
    }

    public void setAlertOnMsgDelivery(boolean value)
    {
        alertOnMsgDelivery.setValue(value);
    }

    public void setLanguageIndicator(byte value)
    {
        languageIndicator.setValue(value);
    }

    public void setItsReplyType(byte value)
    {
        itsReplyType.setValue(value);
    }

    public void setItsSessionInfo(short value)
    {
        itsSessionInfo.setValue(value);
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

    public byte getSourceAddrSubunit()
        throws ValueNotSetException
    {
        return sourceAddrSubunit.getValue();
    }

    public byte getSourceNetworkType()
        throws ValueNotSetException
    {
        return sourceNetworkType.getValue();
    }

    public byte getSourceBearerType()
        throws ValueNotSetException
    {
        return sourceBearerType.getValue();
    }

    public byte getSourceTelematicsId()
        throws ValueNotSetException
    {
        return sourceTelematicsId.getValue();
    }

    public short getDestinationPort()
        throws ValueNotSetException
    {
        return destinationPort.getValue();
    }

    public byte getDestAddrSubunit()
        throws ValueNotSetException
    {
        return destAddrSubunit.getValue();
    }

    public byte getDestNetworkType()
        throws ValueNotSetException
    {
        return destNetworkType.getValue();
    }

    public byte getDestBearerType()
        throws ValueNotSetException
    {
        return destBearerType.getValue();
    }

    public short getDestTelematicsId()
        throws ValueNotSetException
    {
        return destTelematicsId.getValue();
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

    public byte getMoreMsgsToSend()
        throws ValueNotSetException
    {
        return moreMsgsToSend.getValue();
    }

    public int getQosTimeToLive()
        throws ValueNotSetException
    {
        return qosTimeToLive.getValue();
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

    public byte getSetDpf()
        throws ValueNotSetException
    {
        return setDpf.getValue();
    }

    public String getReceiptedMessageId()
        throws ValueNotSetException
    {
        return receiptedMessageId.getValue();
    }

    public byte getMessageState()
        throws ValueNotSetException
    {
        return messageState.getValue();
    }

    public ByteBuffer getNetworkErrorCode()
        throws ValueNotSetException
    {
        return networkErrorCode.getValue();
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

    public byte getCallbackNumPresInd()
        throws ValueNotSetException
    {
        return callbackNumPresInd.getValue();
    }

    public ByteBuffer getCallbackNumAtag()
        throws ValueNotSetException
    {
        return callbackNumAtag.getValue();
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

    public byte getDisplayTime()
        throws ValueNotSetException
    {
        return displayTime.getValue();
    }

    public short getSmsSignal()
        throws ValueNotSetException
    {
        return smsSignal.getValue();
    }

    public byte getMsValidity()
        throws ValueNotSetException
    {
        return msValidity.getValue();
    }

    public byte getMsMsgWaitFacilities()
        throws ValueNotSetException
    {
        return msMsgWaitFacilities.getValue();
    }

    public byte getNumberOfMessages()
        throws ValueNotSetException
    {
        return numberOfMessages.getValue();
    }

    public boolean getAlertOnMsgDelivery()
        throws ValueNotSetException
    {
        return alertOnMsgDelivery.getValue();
    }

    public byte getLanguageIndicator()
        throws ValueNotSetException
    {
        return languageIndicator.getValue();
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

    public String debugString()
    {
        String dbgs = "(data: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + getSourceAddr().debugString();
        dbgs = dbgs + " ";
        dbgs = dbgs + getDestAddr().debugString();
        dbgs = dbgs + " ";
        dbgs = dbgs + debugStringOptional();
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private String serviceType;
    private Address sourceAddr;
    private Address destAddr;
    private byte esmClass;
    private byte registeredDelivery;
    private byte dataCoding;
    private TLVShort userMessageReference;
    private TLVShort sourcePort;
    private TLVByte sourceAddrSubunit;
    private TLVByte sourceNetworkType;
    private TLVByte sourceBearerType;
    private TLVByte sourceTelematicsId;
    private TLVShort destinationPort;
    private TLVByte destAddrSubunit;
    private TLVByte destNetworkType;
    private TLVByte destBearerType;
    private TLVShort destTelematicsId;
    private TLVShort sarMsgRefNum;
    private TLVUByte sarTotalSegments;
    private TLVUByte sarSegmentSeqnum;
    private TLVByte moreMsgsToSend;
    private TLVInt qosTimeToLive;
    private TLVByte payloadType;
    private TLVOctets messagePayload;
    private TLVByte setDpf;
    private TLVString receiptedMessageId;
    private TLVByte messageState;
    private TLVOctets networkErrorCode;
    private TLVByte privacyIndicator;
    private TLVOctets callbackNum;
    private TLVByte callbackNumPresInd;
    private TLVOctets callbackNumAtag;
    private TLVOctets sourceSubaddress;
    private TLVOctets destSubaddress;
    private TLVByte userResponseCode;
    private TLVByte displayTime;
    private TLVShort smsSignal;
    private TLVByte msValidity;
    private TLVByte msMsgWaitFacilities;
    private TLVByte numberOfMessages;
    private TLVEmpty alertOnMsgDelivery;
    private TLVByte languageIndicator;
    private TLVByte itsReplyType;
    private TLVShort itsSessionInfo;
}
