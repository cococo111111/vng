/**
 * CPRMTSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.vng.cpnew.ws;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.axis.Constants;
import org.apache.axis.MessageContext;
import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;

import com.vng.cpnew.db.dao.CPRMTFactoryDAO;
import com.vng.cpnew.db.dao.EmsSendQueueDAO;
import com.vng.cpnew.db.dao.MsgMTDAO;
import com.vng.cpnew.db.dao.beans.UtilBeans;
import com.vng.cpnew.server.CPRMTServer;
import com.vng.cpnew.server.DBConfigLoader;
import com.vng.cpnew.util.CPRMTConstants;
import com.vng.cpnew.util.Utils;

public class CPRMTNEWSoapBindingImpl implements com.vng.cpnew.ws.CPRMTNEW {
	private static Logger logger = Logger
			.getLogger(CPRMTNEWSoapBindingImpl.class);

	public int mtReceiver(java.lang.String requestID, java.lang.String userID,
			java.lang.String serviceID, java.lang.String commandCode,
			java.lang.String message, int messageType,
			java.lang.String mobileOperator, int sumMT, java.lang.String sig)
			throws java.rmi.RemoteException {
		String yyyyMM = (new SimpleDateFormat("yyyyMM")).format(new Date());
		logger.info("");

		String partnerUsername = "";
		String partnerPassword = "";

		logger.info("##### Start mtReceiver()" + " RequestID: [" + requestID
				+ "] #####");
		Connection connection = null;
		int code = CPRMTConstants.ERRORCODE_TRANSACTION_OK;
		boolean next = true;

		if (message != null) {
			message = new String(Base64.decode(message));
			logger.info("Decode Message OK!");
		}

		MessageContext curContext = MessageContext.getCurrentContext();
		String remoteIp = CPRMTConstants.UNKNOWN;
		if (curContext != null) {
			Object ipProperty = curContext
					.getProperty(Constants.MC_REMOTE_ADDR);
			remoteIp = ipProperty.toString();
		}

		String IP = remoteIp;
		StringBuilder dataLog = new StringBuilder();

		try {
			connection = CPRMTServer.getInstance().getConnection();
			dataLog.append("Receiver DATA = IP: [" + IP + "], ");
			dataLog.append("RequestID: [" + requestID + "], ");
			dataLog.append("UserID: [" + userID + "], ");
			dataLog.append("ServiceID: [" + serviceID + "], ");

			dataLog.append("CommandCode: [" + commandCode + "], ");
			dataLog.append("OriginalMessage: [" + message + "], ");
			dataLog.append("MessageType: [" + messageType + "], ");
			dataLog.append("Operator: [" + mobileOperator + "], ");
			dataLog.append("SumMT: [" + sumMT + "], ");
			dataLog.append("Sig: [" + sig + "],");

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

			if (next
					&& !Utils.authenticate(sig, requestID, userID,
							ServiceIDtmp, commandCode, message, DBConfigLoader
									.getInstance().getDbConfig()
									.getPrivateKey())) {
				String plainText=requestID + userID + serviceID + commandCode + message+ DBConfigLoader
						.getInstance().getDbConfig()
						.getPrivateKey();
				
				logger.error(String.format("Invalid Sig. plainText:%s, partner's sig:%s", plainText, sig));
				code = CPRMTConstants.ERRORCODE_INVALID_PARTNER;
				next = false;
			}

			if (next) {
				code = Utils.checkMT(requestID, mobileOperator, userID,
						serviceID, commandCode.toUpperCase(), message,
						connection);
			}

			if (code != CPRMTConstants.ERRORCODE_TRANSACTION_OK) {
				next = false;
			}

			if (next
					&& Utils.checkDuplicateMTbyRequestID(connection, requestID,
							userID, sumMT)) {
				logger.error("Duplicate MOID have RequestID:[" + requestID
						+ "] and UserID:[" + userID + "] and Summt:[" + sumMT
						+ "]");
				code = CPRMTConstants.ERRORCODE_INVALID_DUPLICATE;
				next = false;
			}

			if (next) {

				MsgMTDAO msgMTDAO = CPRMTFactoryDAO.getInstance().getMsgMTDAO();
				int OK = msgMTDAO.insert(connection, UtilBeans.buildMsgMT(
						requestID, userID, serviceID,
						commandCode.toUpperCase(), message, mobileOperator,
						messageType, contentType, sumMT, partnerUsername,
						partnerPassword, IP));
				if (1 == OK) {
					logger.info("Insert MT into table: ["
							+ CPRMTConstants.tblMT + yyyyMM + "] Successed!!!");
				} else {
					logger.error("Insert MT into table: ["
							+ CPRMTConstants.tblMT + yyyyMM + "] Failed!!!");
					code = CPRMTConstants.ERRORCODE_SYSTEM_BUSY;
					next = false;
				}
			}

			if (next) {

				EmsSendQueueDAO emsSendQueueDAO = CPRMTFactoryDAO.getInstance()
						.getEmsSendQueueDAO();
				if (1 == emsSendQueueDAO.insert(connection, UtilBeans
						.buildEmsSendQueue(requestID, userID, serviceID,
								commandCode.toUpperCase(), message,
								mobileOperator.toUpperCase(), messageType,
								contentType, sumMT))) {
					logger.info("Insert MT into table: ["
							+ CPRMTConstants.tblSendQueue + "] Successed!!!");
				} else {
					logger.error("Insert MT into table: ["
							+ CPRMTConstants.tblSendQueue + "] Failed!!!");
					code = CPRMTConstants.ERRORCODE_SYSTEM_BUSY;
					next = false;
				}
			}
		} catch (Exception e) {
			logger.error("ERROR: " + e.getMessage());
			code = CPRMTConstants.ERRORCODE_SYSTEM_BUSY;
		} finally {
			CPRMTServer.getInstance().closeConnection(connection);
		}

		logger.info("ErrorCode:[" + code + "]");
		logger.info("##### End mtReceiver()" + " RequestID: [" + requestID
				+ "] #####");
		return code;
	}

}
