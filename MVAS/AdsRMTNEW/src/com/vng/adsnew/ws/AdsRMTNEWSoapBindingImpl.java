/**
 * AdsRMTSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.vng.adsnew.ws;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.axis.Constants;
import org.apache.axis.MessageContext;
import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;

import com.vng.adsnew.db.dao.AdsRMTFactoryDAO;
import com.vng.adsnew.db.dao.EmsSendQueueDAO;
import com.vng.adsnew.db.dao.MsgMTDAO;
import com.vng.adsnew.db.dao.beans.UtilBeans;
import com.vng.adsnew.server.AdsRMTServer;
import com.vng.adsnew.server.DBConfigLoader;
import com.vng.adsnew.util.AdsRMTConstants;
import com.vng.adsnew.util.MobileOperator;
import com.vng.adsnew.util.Utils;

public class AdsRMTNEWSoapBindingImpl implements com.vng.adsnew.ws.AdsRMTNEW {
	private static Logger logger = Logger
			.getLogger(AdsRMTNEWSoapBindingImpl.class);

	public int mtReceiver(java.lang.String requestID, java.lang.String userID,
			java.lang.String serviceID, java.lang.String commandCode,
			java.lang.String message, java.lang.String sig)
			throws java.rmi.RemoteException {
		String yyyyMM = (new SimpleDateFormat("yyyyMM")).format(new Date());
		logger.info("");
		logger.info("##### Start mtReceiver()" + " RequestID: [" + requestID
				+ "] #####");
		Connection connection = null;
		int code = AdsRMTConstants.ERRORCODE_TRANSACTION_OK;
		boolean next = true;

		String partnerUsername = "";
		String partnerPassword = "";

		message = new String(Base64.decode(message));
		logger.info("Decode Message OK!");

		MessageContext curContext = MessageContext.getCurrentContext();
		String remoteIp = AdsRMTConstants.UNKNOWN;
		if (curContext != null) {
			Object ipProperty = curContext
					.getProperty(Constants.MC_REMOTE_ADDR);
			remoteIp = ipProperty.toString();
		}
		String IP = remoteIp;
		StringBuilder dataLog = new StringBuilder();
		try {
			connection = AdsRMTServer.getInstance().getConnection();
			String operator = MobileOperator.getInstance().getCachedOperator(
					connection, userID);

			dataLog.append("Receiver DATA = IP: [" + IP + "], ");
			dataLog.append("RequestID: [" + requestID + "], ");
			dataLog.append("UserID: [" + userID + "], ");
			dataLog.append("ServiceID: [" + serviceID + "], ");
			dataLog.append("CommandCode: [" + commandCode + "], ");
			dataLog.append("OriginalMessage: [" + message + "], ");

			if (DBConfigLoader.getInstance().getDbConfig()
					.getCommandCodeEndMessage(commandCode) != null) {
				message = Utils.buildValidMT(message);
			}

			dataLog.append("Builded Valid Message: [" + message + "], ");
			if (userID.toUpperCase().trim().equalsIgnoreCase("84988997297")) {
				dataLog.append("Operator: [VIETEL1], ");
			} else {
				dataLog.append("Operator: [" + operator + "], ");
			}
			dataLog.append("Sig: [" + sig + "]");

			String ServiceIDtmp = serviceID;
			// DuyND: start 13-July-2011 - support for message content is media
			int contentType = 0;
			if (serviceID != null && serviceID.indexOf(",") > 0) {
				String[] tmpserviceIDs = serviceID.split(",");
				if (tmpserviceIDs != null && tmpserviceIDs.length == 2) {
					try {
						serviceID = tmpserviceIDs[0].trim();
						contentType = Integer.parseInt(tmpserviceIDs[1].trim());
					} catch (Exception e) {
						contentType = 0;
					}
				} else {
					contentType = 0;
				}
			}
			// end
			dataLog.append("contentType: [" + contentType + "]");
			logger.debug(dataLog.toString());
			if (!Utils.isOKConfig()) {
				next = false;
				code = AdsRMTConstants.ERRORCODE_SYSTEM_BUSY;
				logger.error("Config system INCORRECT");
			}

			if (next
					&& !Utils.authenticate(sig, requestID, userID,
							ServiceIDtmp, commandCode, message, DBConfigLoader
									.getInstance().getDbConfig()
									.getPrivateKey())) {
				logger.error("Invalid Sig Partner : [" + sig + "]");
				code = AdsRMTConstants.ERRORCODE_INVALID_PARTNER;
				next = false;
			}

			if (next) {
				code = Utils.checkMT(requestID, operator, userID, serviceID,
						commandCode.toUpperCase(), message, connection);
			}

			if (next && code != AdsRMTConstants.ERRORCODE_TRANSACTION_OK) {
				next = false;
			}

			if (next
					&& Utils.checkDuplicateMTbyRequestID(connection, requestID,
							userID)) {
				logger.error("Duplicate MOID have RequestID:[" + requestID
						+ "] and UserID:[" + userID + "]");
				code = AdsRMTConstants.ERRORCODE_INVALID_DUPLICATE;
				next = false;
			}
			Integer splitIndex = commandCode.length() - 4;
			String codeCheck	= commandCode.substring(splitIndex);
			if(codeCheck.toUpperCase().trim().equalsIgnoreCase("SPAM"))
			{
				if (next
						&& Utils.isCaneledAdsUserID(connection,userID)) {
					logger.error("Invalid Data: UserID = [" + userID
							+ "] were caneled advetise");
					code = AdsRMTConstants.ERRORCODE_CANCELED_ADS;
					next = false;
				}
			}

			if (next) {

				MsgMTDAO msgMTDAO = AdsRMTFactoryDAO.getInstance()
						.getMsgMTDAO();
				int OK = msgMTDAO.insert(connection, UtilBeans.buildMsgMT(
						requestID, userID, serviceID,
						commandCode.toUpperCase(), message, operator, 2,
						contentType, 1, partnerUsername, partnerPassword, IP));
				if (1 == OK) {
					logger
							.info("Insert MT into table: ["
									+ AdsRMTConstants.tblMT + yyyyMM
									+ "] Successed!!!");
				} else {
					logger.error("Insert MT into table: ["
							+ AdsRMTConstants.tblMT + yyyyMM + "] Failed!!!");
					code = AdsRMTConstants.ERRORCODE_SYSTEM_BUSY;
					next = false;
				}
			}

			if (next) {

				EmsSendQueueDAO emsSendQueueDAO = AdsRMTFactoryDAO
						.getInstance().getEmsSendQueueDAO();
				if (1 == emsSendQueueDAO.insert(connection, UtilBeans
						.buildEmsSendQueue(requestID, userID, serviceID,
								commandCode.toUpperCase(), message, operator
										.toUpperCase(), 2, contentType, 1))) {
					logger.info("Insert MT into table: ["
							+ AdsRMTConstants.tblSendQueue + "] Successed!!!");
				} else {
					logger.error("Insert MT into table: ["
							+ AdsRMTConstants.tblSendQueue + "] Failed!!!");
					code = AdsRMTConstants.ERRORCODE_SYSTEM_BUSY;
					next = false;
				}
			}
		} catch (Exception e) {
			logger.error("ERROR: " + e.getMessage());
			code = AdsRMTConstants.ERRORCODE_SYSTEM_BUSY;
		} finally {
			AdsRMTServer.getInstance().closeConnection(connection);
		}

		logger.info("ErrorCode:[" + code + "]");
		logger.info("##### End mtReceiver()" + " RequestID: [" + requestID
				+ "] #####");
		return code;
	}
}
