// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataSMResp.java

package org.smpp.pdu;

import org.smpp.pdu.tlv.TLV;
import org.smpp.pdu.tlv.TLVByte;
import org.smpp.pdu.tlv.TLVOctets;
import org.smpp.pdu.tlv.TLVString;
import org.smpp.pdu.tlv.WrongLengthException;
import org.smpp.util.*;

// Referenced classes of package org.smpp.pdu:
//            Response, PDUException, WrongLengthOfStringException, ValueNotSetException, 
//            PDU, ByteData

public class DataSMResp extends Response
{

    public DataSMResp()
    {
        super(0x80000103);
        messageId = "";
        deliveryFailureReason = new TLVByte((short)1061);
        networkErrorCode = new TLVOctets((short)1059, 3, 3);
        additionalStatusInfoText = new TLVString((short)29, 1, 256);
        dpfResult = new TLVByte((short)1056);
        registerOptional(deliveryFailureReason);
        registerOptional(networkErrorCode);
        registerOptional(additionalStatusInfoText);
        registerOptional(dpfResult);
    }

    public void setBody(ByteBuffer buffer)
        throws NotEnoughDataInByteBufferException, TerminatingZeroNotFoundException, PDUException
    {
        setMessageId(buffer.removeCString());
    }

    public ByteBuffer getBody()
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendCString(messageId);
        return buffer;
    }

    public void setMessageId(String value)
        throws WrongLengthOfStringException
    {
        ByteData.checkString(value, 64);
        messageId = value;
    }

    public String getMessageId()
    {
        return messageId;
    }

    public boolean hasDeliveryFailureReason()
    {
        return deliveryFailureReason.hasValue();
    }

    public boolean hasNetworkErrorCode()
    {
        return networkErrorCode.hasValue();
    }

    public boolean hasAdditionalStatusInfoText()
    {
        return additionalStatusInfoText.hasValue();
    }

    public boolean hasDpfResult()
    {
        return dpfResult.hasValue();
    }

    public void setDeliveryFailureReason(byte value)
    {
        deliveryFailureReason.setValue(value);
    }

    public void setNetworkErrorCode(ByteBuffer value)
    {
        networkErrorCode.setValue(value);
    }

    public void setAdditionalStatusInfoText(String value)
        throws WrongLengthException
    {
        additionalStatusInfoText.setValue(value);
    }

    public void setDpfResult(byte value)
    {
        dpfResult.setValue(value);
    }

    public byte getDeliveryFailureReason()
        throws ValueNotSetException
    {
        return deliveryFailureReason.getValue();
    }

    public ByteBuffer getNetworkErrorCode()
        throws ValueNotSetException
    {
        return networkErrorCode.getValue();
    }

    public String getAdditionalStatusInfoText()
        throws ValueNotSetException
    {
        return additionalStatusInfoText.getValue();
    }

    public byte getDpfResult()
        throws ValueNotSetException
    {
        return dpfResult.getValue();
    }

    public String debugString()
    {
        String dbgs = "(data_resp: ";
        dbgs = dbgs + super.debugString();
        dbgs = dbgs + getMessageId();
        dbgs = dbgs + " ";
        dbgs = dbgs + debugStringOptional();
        dbgs = dbgs + ") ";
        return dbgs;
    }

    private String messageId;
    private TLVByte deliveryFailureReason;
    private TLVOctets networkErrorCode;
    private TLVString additionalStatusInfoText;
    private TLVByte dpfResult;
}
