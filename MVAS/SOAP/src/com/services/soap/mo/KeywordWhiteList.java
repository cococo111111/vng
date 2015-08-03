package com.services.soap.mo;

public class KeywordWhiteList {
	private String keyword;
	private String serviceId;
	private String mobileOperator;
	private String whiteList;
	private String msgReturn;
	private int msgType;
	
	public KeywordWhiteList() {
	}

	public final String getKeyword() {
		return keyword;
	}

	public final void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public final String getServiceId() {
		return serviceId;
	}

	public final void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	public final String getMobileOperator() {
		return mobileOperator;
	}

	public final void setMobileOperator(String mobileOperator) {
		this.mobileOperator = mobileOperator;
	}

	public final String getWhiteList() {
		return whiteList;
	}

	public final void setWhiteList(String whiteList) {
		this.whiteList = whiteList;
	}

	public final String getMsgReturn() {
		return msgReturn;
	}

	public final void setMsgReturn(String msgReturn) {
		this.msgReturn = msgReturn;
	}

	public final int getMsgType() {
		return msgType;
	}

	public final void setMsgType(int msgType) {
		this.msgType = msgType;
	}

}
