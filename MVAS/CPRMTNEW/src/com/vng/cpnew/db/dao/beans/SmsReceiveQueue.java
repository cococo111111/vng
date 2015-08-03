package com.vng.cpnew.db.dao.beans;

public class SmsReceiveQueue {
	private String userID;
	private String serviceID;
	private String operator;
	private String commandCode;
	private String message;
	private String MO_ID;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCommandCode() {
		return commandCode;
	}

	public void setCommandCode(String commandCode) {
		this.commandCode = commandCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMO_ID() {
		return MO_ID;
	}

	public void setMO_ID(String mo_id) {
		MO_ID = mo_id;
	}

	public SmsReceiveQueue() {
	}

}
