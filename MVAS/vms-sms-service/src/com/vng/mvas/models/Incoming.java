/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vng.mvas.models;

import java.util.Date;

/**
 *
 * @author vinh
 */
public class Incoming {
	
    @Override
	public String toString() {
		return "Incoming [spId=" + spId + ", serviceId=" + serviceId
				+ ", timeStamp=" + timeStamp + ", message=" + message
				+ ", senderAddress=" + senderAddress
				+ ", smsServiceActivationNumber=" + smsServiceActivationNumber
				+ ", dateTime=" + dateTime + ", traceUniqueID=" + traceUniqueID
				+ ", requestID=" + requestID + "]";
	}


	private String spId;
    private String serviceId;
    private String timeStamp;
    private String message;
    private String senderAddress;
    private String smsServiceActivationNumber;
    private Date dateTime;
    private String traceUniqueID;
    private String requestID;

    public Incoming(String spId, String serviceId, String timeStamp, String message, String senderAddress, String smsServiceActivationNumber, Date dateTime, String traceUniqueID, String requestID) {
        this.spId = spId;
        this.serviceId = serviceId;
        this.timeStamp = timeStamp;
        this.message = message;
        this.senderAddress = senderAddress;
        this.smsServiceActivationNumber = smsServiceActivationNumber;
        this.dateTime = dateTime;
        this.traceUniqueID = traceUniqueID;
        this.setRequestID(requestID);
    }
    
    
    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getSmsServiceActivationNumber() {
        return smsServiceActivationNumber;
    }

    public void setSmsServiceActivationNumber(String smsServiceActivationNumber) {
        this.smsServiceActivationNumber = smsServiceActivationNumber;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getTraceUniqueID() {
        return traceUniqueID;
    }

    public void setTraceUniqueID(String traceUniqueID) {
        this.traceUniqueID = traceUniqueID;
    }


	public String getRequestID() {
		return requestID;
	}


	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
    
    
}
