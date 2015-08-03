package com.vng.adsnew.db.dao.beans;

public class MsgMO {
	private int MO_ID;
	private String userID;
	private String serviceID;
	private String commandCode;
	private String message;
	private String operator;
	private String partnerUsername;
	private String partnerPassword;
	private String requestTime;
	private String IP;

	public int getMO_ID() {
		return MO_ID;
	}

	public void setMO_ID(int mo_id) {
		MO_ID = mo_id;
	}

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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getPartnerUsername() {
		return partnerUsername;
	}

	public void setPartnerUsername(String partnerUsername) {
		this.partnerUsername = partnerUsername;
	}

	public String getPartnerPassword() {
		return partnerPassword;
	}

	public void setPartnerPassword(String partnerPassword) {
		this.partnerPassword = partnerPassword;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String ip) {
		IP = ip;
	}
}
