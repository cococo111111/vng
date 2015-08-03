package com.vng.cpnew.server;

import java.util.HashMap;
import java.util.Map;

public class DBConfig {

	private String jdbcDriver;

	private String dbURL;

	private String dbUser;

	private String dbPassword;

	private Map<String, String> commandCodeMap = new HashMap<String, String>();

	private Map<String, String> commandCodeEndMessageMap = new HashMap<String, String>();

	private String serviceID;

	private String endMessage;

	private String privateKey;

	private String allowOperators;

	private String commandCodes;
	private String commandCodesEndMessage;

	public final String getDbPassword() {
		return dbPassword;
	}

	public final void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public final String getDbURL() {
		return dbURL;
	}

	public final void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}

	public final String getDbUser() {
		return dbUser;
	}

	public final void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public final String getJdbcDriver() {
		return jdbcDriver;
	}

	public final void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public final String getCommandCode(String key) {
		return commandCodeMap.get(key);
	}

	public final String getCommandCodeEndMessage(String key) {
		return commandCodeEndMessageMap.get(key);
	}

	public final void setCommandCodes(String commandCodes) {
		this.commandCodes = commandCodes;
		String[] commandCodestmp = commandCodes.split(",");
		for (int i = 0; i < commandCodestmp.length; i++) {
			commandCodeMap.put(commandCodestmp[i], commandCodestmp[i]);
		}
	}

	public final void setCommandCodesEndMessage(String commandCodesEndMessage) {
		this.commandCodesEndMessage = commandCodesEndMessage;
		String[] commandCodesEndMessagetmp = commandCodesEndMessage.split(",");
		for (int i = 0; i < commandCodesEndMessagetmp.length; i++) {
			commandCodeEndMessageMap.put(commandCodesEndMessagetmp[i],
					commandCodesEndMessagetmp[i]);
		}
	}

	public final String getServiceID() {
		return serviceID;
	}

	public final void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public final String getEndMessage() {
		return endMessage;
	}

	public final void setEndMessage(String endMessage) {
		this.endMessage = endMessage;
	}

	public final String getAllowOperators() {
		return allowOperators;
	}

	public final void setAllowOperators(String allowOperators) {
		this.allowOperators = allowOperators;
	}

	public final String getCommandCodes() {
		return commandCodes;
	}

	public final String getCommandCodesEndMessage() {
		return commandCodesEndMessage;
	}

	public final String getPrivateKey() {
		return privateKey;
	}

	public final void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
}
