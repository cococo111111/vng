package com.vng.adsnew.db.dao.beans;

public class EmsSendQueue {
	private int MessageID;
	private String UserID;
	private String Message;
	private String ServiceID;
	private String CommandCode;
	private String Operator;
	private int MsgType;
	private int ContentType;
	private int SumMT;
	private String RequestID;

	public EmsSendQueue() {
	}

	public int getMessageID() {
		return MessageID;
	}

	public void setMessageID(int messageID) {
		MessageID = messageID;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getServiceID() {
		return ServiceID;
	}

	public void setServiceID(String serviceID) {
		ServiceID = serviceID;
	}

	public String getCommandCode() {
		return CommandCode;
	}

	public void setCommandCode(String commandCode) {
		CommandCode = commandCode;
	}

	public String getOperator() {
		return Operator;
	}

	public void setOperator(String operator) {
		Operator = operator;
	}

	public int getMsgType() {
		return MsgType;
	}

	public void setMsgType(int msgType) {
		MsgType = msgType;
	}

	public int getContentType() {
		return ContentType;
	}

	public void setContentType(int contentType) {
		ContentType = contentType;
	}

	public int getSumMT() {
		return SumMT;
	}

	public void setSumMT(int sumMT) {
		SumMT = sumMT;
	}

	public final String getRequestID() {
		return RequestID;
	}

	public final void setRequestID(String requestID) {
		RequestID = requestID;
	}

}
