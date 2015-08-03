// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SubmitMultiSM.java

package org.smpp.pdu;

import java.io.UnsupportedEncodingException;
import org.smpp.pdu.tlv.TLV;
import org.smpp.pdu.tlv.TLVByte;
import org.smpp.pdu.tlv.TLVEmpty;
import org.smpp.pdu.tlv.TLVOctets;
import org.smpp.pdu.tlv.TLVShort;
import org.smpp.pdu.tlv.TLVUByte;
import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Request, Address, ShortMessage, SubmitMultiSMResp, 
//            DestinationAddress, PDUException, ValueNotSetException, WrongLengthOfStringException, 
//            WrongDateFormatException, TooManyValuesException, IntegerOutOfRangeException, PDU, 
//            ByteDataList, ByteData, Response

public class SubmitMultiSM extends Request
{
    private class DestAddressList extends ByteDataList
    {

        public ByteData createValue()
        {
            return new DestinationAddress();
        }

        public String debugString()
        {
            return "(dest_addr_list: " + super.debugString() + ")";
        }

        public DestAddressList()
        {
            super(254, 1);
        }
    }


    public SubmitMultiSM()
    {
        super(33);
        serviceType = "";
        sourceAddr = new Address();
        destAddresses = new DestAddressList();
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
        sourceAddrSubunit = new TLVByte((short)13);
        destinationPort = new TLVShort((short)523);
        destAddrSubunit = new TLVByte((short)5);
        sarMsgRefNum = new TLVShort((short)524);
        sarTotalSegments = new TLVUByte((short)526);
        sarSegmentSeqnum = new TLVUByte((short)527);
        payloadType = new TLVByte((short)25);
        messagePayload = new TLVOctets((short)1060, 1, 1500);
        privacyIndicator = new TLVByte((short)513);
        callbackNum = new TLVOctets((short)897, 4, 19);
        callbackNumPresInd = new TLVByte((short)770);
        callbackNumAtag = new TLVOctets((short)771, 1, 65);
        sourceSubaddress = new TLVOctets((short)514, 2, 23);
        destSubaddress = new TLVOctets((short)515, 2, 23);
        displayTime = new TLVByte((short)4609);
        smsSignal = new TLVShort((short)4611);
        msValidity = new TLVByte((short)4612);
        msMsgWaitFacilities = new TLVByte((short)2);
        alertOnMsgDelivery = new TLVEmpty((short)4876);
        languageIndicator = new TLVByte((short)525);
        registerOptional(userMessageReference);
        registerOptional(sourcePort);
        registerOptional(sourceAddrSubunit);
        registerOptional(destinationPort);
        registerOptional(destAddrSubunit);
        registerOptional(sarMsgRefNum);
        registerOptional(sarTotalSegments);
        registerOptional(sarSegmentSeqnum);
        registerOptional(payloadType);
        registerOptional(messagePayload);
        registerOptional(privacyIndicator);
        registerOptional(callbackNum);
        registerOptional(callbackNumPresInd);
        registerOptional(callbackNumAtag);
        registerOptional(sourceSubaddress);
        registerOptional(destSubaddress);
        registerOptional(displayTime);
        registerOptional(smsSignal);
        registerOptional(msValidity);
        registerOptional(msMsgWaitFacilities);
        registerOptional(alertOnMsgDelivery);
        registerOptional(languageIndicator);
    }

    protected Response createResponse()
    {
        return new SubmitMultiSMResp();
    }

    public void setBody(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, PDUException
    {
        setServiceType(buffer.removeCString());
        sourceAddr.setData(buffer);
        destAddresses.setData(buffer);
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

    public ByteBuffer getBody()
        throws ValueNotSetException
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendCString(getServiceType());
        buffer.appendBuffer(getSourceAddr().getData());
        buffer.appendBuffer(destAddresses.getData());
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

    public void setScheduleDeliveryTime(String value)
        throws WrongDateFormatException
    {
        ByteData.checkDate(value);
        scheduleDeliveryTime = value;
    }

    public void setValidityPeriod(String value)
        throws WrongDateFormatException
    {
        ByteData.checkDate(value);
        validityPeriod = value;
    }

    public void setShortMessageData(ByteBuffer value)
        throws PDUException, NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException
    {
        shortMessage.setData(value);
        setSmLength((short)shortMessage.getLength());
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

    public void addDestAddress(DestinationAddress destAddr)
        throws TooManyValuesException
    {
        destAddresses.addValue(destAddr);
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

    public void setReplaceIfPresentFlag(byte value)
    {
        replaceIfPresentFlag = value;
    }

    public void setDataCoding(byte value)
    {
        dataCoding = value;
    }

    public void setSmDefaultMsgId(byte value)
    {
        smDefaultMsgId = value;
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

    public ByteBuffer getShortMessageData()
    {
        return shortMessage.getData();
    }

    public Address getSourceAddr()
    {
        return sourceAddr;
    }

    public short getNumberOfDests()
    {
        return (short)destAddresses.getCount();
    }

    public DestinationAddress getDestAddress(int i)
    {
        return (DestinationAddress)destAddresses.getValue(i);
    }

    public byte getEsmClass()
    {
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

    public boolean hasSourceAddrSubunit()
    {
        return sourceAddrSubunit.hasValue();
    }

    public boolean hasDestinationPort()
    {
        return destinationPort.hasValue();
    }

    public boolean hasDestAddrSubunit()
    {
        return destAddrSubunit.hasValue();
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

    public boolean hasAlertOnMsgDelivery()
    {
        return alertOnMsgDelivery.hasValue();
    }

    public boolean hasLanguageIndicator()
    {
        return languageIndicator.hasValue();
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

    public void setDestinationPort(short value)
    {
        destinationPort.setValue(value);
    }

    public void setDestAddrSubunit(byte value)
    {
        destAddrSubunit.setValue(value);
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

    public void setAlertOnMsgDelivery(boolean value)
    {
        alertOnMsgDelivery.setValue(value);
    }

    public void setLanguageIndicator(byte value)
    {
        languageIndicator.setValue(value);
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

    public String debugString()
    {
        String dbgs = "(submitmulti: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + getSourceAddr().debugString();
        dbgs = dbgs + destAddresses.debugString();
        dbgs = dbgs + " ";
        dbgs = dbgs + shortMessage.debugString();
        dbgs = dbgs + " ";
        dbgs = dbgs + debugStringOptional();
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private String serviceType;
    private Address sourceAddr;
    private DestAddressList destAddresses;
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
    private TLVByte sourceAddrSubunit;
    private TLVShort destinationPort;
    private TLVByte destAddrSubunit;
    private TLVShort sarMsgRefNum;
    private TLVUByte sarTotalSegments;
    private TLVUByte sarSegmentSeqnum;
    private TLVByte payloadType;
    private TLVOctets messagePayload;
    private TLVByte privacyIndicator;
    private TLVOctets callbackNum;
    private TLVByte callbackNumPresInd;
    private TLVOctets callbackNumAtag;
    private TLVOctets sourceSubaddress;
    private TLVOctets destSubaddress;
    private TLVByte displayTime;
    private TLVShort smsSignal;
    private TLVByte msValidity;
    private TLVByte msMsgWaitFacilities;
    private TLVEmpty alertOnMsgDelivery;
    private TLVByte languageIndicator;
}
