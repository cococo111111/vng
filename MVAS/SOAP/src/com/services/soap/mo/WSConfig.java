package com.services.soap.mo;

public class WSConfig {
	private int cpID ;
	private String cpName ;
	private String wsURL;
	private String userName;
	private String password ;
	private String className ;
	private String cpCode ;
	
	public WSConfig(){
	}

	public final int getCpID() {
		return cpID;
	}

	public final void setCpID(int cpID) {
		this.cpID = cpID;
	}

	public final String getCpName() {
		return cpName;
	}

	public final void setCpName(String cpName) {
		this.cpName = cpName;
	}

	public final String getWsURL() {
		return wsURL;
	}

	public final void setWsURL(String wsURL) {
		this.wsURL = wsURL;
	}

	public final String getUserName() {
		return userName;
	}

	public final void setUserName(String userName) {
		this.userName = userName;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public final String getClassName() {
		return className;
	}

	public final void setClassName(String className) {
		this.className = className;
	}

	public final String getCpCode() {
		return cpCode;
	}

	public final void setCpCode(String cpCode) {
		this.cpCode = cpCode;
	}
}
