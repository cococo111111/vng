package com.services.vng;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import sdsjavaclient4mvas.SdsJavaClient4Mvas;

import com.services.soap.mo.KeywordWhiteList;
import com.services.soap.mo.MOSender;
import com.services.soap.mo.SOAPConstants;
import com.services.soap.mo.WSConfig;
import com.services.soap.mo.WSConfigLoader;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.Constants;
import com.vmg.sms.process.ContentAbstract;
import com.vmg.sms.process.DBPool;
import com.vmg.sms.process.Keyword;
import com.vmg.sms.process.MsgObject;

public class SoapVNGSDSPayment extends ContentAbstract {
	private static final String className = "com.services.vng.SoapVNGSDSPayment";
	private static final String MUA123_CP_CODE = "SDSPM";         
	
	private static final String BUSY_SYSTEM = "He thong hien tai dang qua tai. Xin ban hay thu lai sau vai phut nua. Neu can ho tro xin goi so 1900561558. Cam on.";
	private static final String TIME_OUT_SYSTEM = "He thong hien tai dang bi loi. Xin ban hay thu lai sau vai phut nua. Neu can ho tro xin goi so 1900561558. Cam on.";
	private static final int CHARGE_BACK = 2;
	private static final String INVALID_SYNTAX = "Tin nhan cua ban khong dung cu phap, xin hay kiem tra va thu lai. Neu can ho tro xin goi so 1900561558. Cam on.";
	
	protected Collection getMessages(MsgObject msgObject, Keyword keyword) throws Exception {
		int iRetries = 3;
		int iTimeout = SOAPConstants.RETRIES_TIME;
		String result = "";
		Collection messages = new ArrayList();
		
		if (!msgObject.getKeyword().equalsIgnoreCase(msgObject.getUsertext().split(" ")[0].trim()) ){
			msgObject.setUsertext(INVALID_SYNTAX);
			msgObject.setMsgtype(CHARGE_BACK);
			messages.add(new MsgObject(msgObject));
			return messages;
		}
	
		KeywordWhiteList keywordWhiteList = WSConfigLoader.getInstance().getKeywordWhiteListByKeywordServiceID(keyword.getKeyword() + msgObject.getServiceid());
		if (keywordWhiteList != null && !inWhiteList(keywordWhiteList.getWhiteList(), msgObject.getUserid())){
			msgObject.setUsertext(keywordWhiteList.getMsgReturn());
			msgObject.setMsgtype(keywordWhiteList.getMsgType());
			messages.add(new MsgObject(msgObject));
			return messages;
		}
		else {
			keywordWhiteList = WSConfigLoader.getInstance().getKeywordWhiteListByKeywordServiceID(keyword.getKeyword() + msgObject.getServiceid() + msgObject.getMobileoperator());
			if (keywordWhiteList != null && !inWhiteList(keywordWhiteList.getWhiteList(), msgObject.getUserid())){
				msgObject.setUsertext(keywordWhiteList.getMsgReturn());
				msgObject.setMsgtype(keywordWhiteList.getMsgType());
				messages.add(new MsgObject(msgObject));
				return messages;
			}
		}
		while (iRetries > 0) {
			Long timestart = Calendar.getInstance().getTimeInMillis();
			//Util.logger.info("SendToSDSService_Start:"+ timestart);
			try {
				result = String.valueOf(sendToSDS(msgObject));
				Long timeend = Calendar.getInstance().getTimeInMillis();
				Long timesend = timeend - timestart;
				Util.logger.info("TimeSendToSDSService:"+ timesend);
				if ("5".equals(result)) {
					writeLogInfo(msgObject, result, "Send OK!", iRetries);					
					return null;
				}else if ("0".equals(result)) {
					writeLogError(msgObject, result , "SDS System Busy", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. SDS System Busy");
					add2SMSSendFailed(msgObject);
					msgObject.setUsertext(BUSY_SYSTEM);
					msgObject.setMsgtype(CHARGE_BACK);
					messages.add(new MsgObject(msgObject));
					return messages;
				}else if ("-1".equals(result)) {
					writeLogError(msgObject, result , "UnAllowIP", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. UnAllowIP");
					add2SMSSendFailed(msgObject);
					msgObject.setUsertext(BUSY_SYSTEM);
					msgObject.setMsgtype(CHARGE_BACK);
					messages.add(new MsgObject(msgObject));
					return messages;
				}else if ("-2".equals(result)) {
					writeLogError(msgObject, result , "Invalid CommandCode", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. Invalid CommandCode");
					add2SMSSendFailed(msgObject);
					msgObject.setUsertext(INVALID_SYNTAX);
					msgObject.setMsgtype(CHARGE_BACK);
					messages.add(new MsgObject(msgObject));
					return messages;
				}else if ("-3".equals(result)) {
					writeLogError(msgObject,  result,  "Invalid Telco", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. Invalid Telco");
					add2SMSSendFailed(msgObject);
					msgObject.setUsertext("Dich vu khong ho tro dau so nay, xin hay kiem tra va thu lai. Neu can ho tro xin goi so 1900561558. Cam on.");
					msgObject.setMsgtype(CHARGE_BACK);
					messages.add(new MsgObject(msgObject));
					return messages;
				}else if ("-4".equals(result)) {
					writeLogError(msgObject, result , "Invalid MOID", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. Invalid MOID");
					add2SMSSendFailed(msgObject);
					msgObject.setUsertext(BUSY_SYSTEM);
					msgObject.setMsgtype(CHARGE_BACK);
					messages.add(new MsgObject(msgObject));
					return messages;
				}else if ("-5".equals(result)) {
					writeLogError(msgObject, result , "UnknownServiceCode", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. UnknownServiceCode");
					add2SMSSendFailed(msgObject);
					msgObject.setUsertext(INVALID_SYNTAX);
					msgObject.setMsgtype(CHARGE_BACK);
					messages.add(new MsgObject(msgObject));
					return messages;
				}else if ("-29".equals(result)) {
					writeLogError(msgObject, result , "UnknownErrorCode", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. UnknownErrorCode");
					add2SMSSendFailed(msgObject);
					msgObject.setUsertext(BUSY_SYSTEM);
					msgObject.setMsgtype(CHARGE_BACK);
					messages.add(new MsgObject(msgObject));
					return messages;
				}else if ("-32".equals(result)) {
					writeLogError(msgObject, result , "RequestQueueFull", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. RequestQueueFull");
					add2SMSSendFailed(msgObject);
					msgObject.setUsertext(BUSY_SYSTEM);
					msgObject.setMsgtype(CHARGE_BACK);
					messages.add(new MsgObject(msgObject));
					return messages;
				} else if ("998".equals(result)) {
					writeLogError(msgObject, result , "SyntaxError", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. SyntaxError");
					add2SMSSendFailed(msgObject);
					msgObject.setUsertext(INVALID_SYNTAX);
					msgObject.setMsgtype(CHARGE_BACK);
					messages.add(new MsgObject(msgObject));
					return messages;
				} else if ("999".equals(result)) {
					writeLogError(msgObject, result , "TimeOutError", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. TimeOutError");
					add2SMSSendFailed(msgObject);
					msgObject.setUsertext(TIME_OUT_SYSTEM);
					msgObject.setMsgtype(CHARGE_BACK);
					messages.add(new MsgObject(msgObject));
					return messages;
				} else {
					writeLogError(msgObject,  result , "Going For Retry, Sleeping", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. Going For Retry, Sleeping");
					
					iRetries--;
					Thread.sleep(iTimeout * 1000);
					continue;
				}
			} catch (Exception e) {
				String message = "";
				message = (e != null) ? e.getMessage(): "NULL";
				
				Util.logger.error(this.getClass().getName()+ ".getMessages()@"
								+ "ERROR!: " + message +  ". Got Respone Code: [" + result +"], Going For Retry, Sleeping,Details: MO_ID: [" + msgObject.getMo_id()  
								+ "] UserID: [" + msgObject.getUserid()
								+ "] Telco: [" + msgObject.getMobileoperator()
								+ "] ServiceID: [" + msgObject.getServiceid()
								+ "] Keyword: [" + msgObject.getKeyword()
								+ "] RequestID: [" + msgObject.getRequestid()
								+ "] CommandCode: [" + msgObject.getKeyword()
								+ "] RequestTime: ["
								+ SOAPConstants.convertTimestampToString(msgObject.getTTimes(), SOAPConstants.DATE_TIME)
								+ "] Online Retry countdown: " + iRetries);
				msgObject.setMsgNotes("Respone Code: [" + result +"]. " + message);
				Long timeend2 = Calendar.getInstance().getTimeInMillis();
				Long timesend = timeend2 - timestart;
				Util.logger.info("TimeSendToSDSService:"+ timesend);
				iRetries--;
				Thread.sleep(iTimeout * 1000);
				continue;
			}

		}
		
		add2SMSSendFailed(msgObject);
		String returnMessage = Constants._prop.getProperty("MESSAGE_SYSTEM_OVERLOAD");
		msgObject.setUsertext(returnMessage);
		msgObject.setMsgtype(2);
		messages.add(new MsgObject(msgObject));
		return messages;
	}
	
	
	private void writeLogError(MsgObject msgObject, String result, String message ,int iRetries){
		Util.logger.error(this.getClass().getName() + ".getMessages() - "
				+ "Respone Code: [" + result +"]. "+ message + ", Details: MO_ID: [" + msgObject.getMo_id() + "] UserID: ["
				+ msgObject.getUserid() + "] Telco: ["
				+ msgObject.getMobileoperator() + "] ServiceID: ["
				+ msgObject.getServiceid() + "] Keyword: ["
				+ msgObject.getKeyword() + "] RequestID: ["
				+ msgObject.getRequestid() + "] CommandCode: ["
				+ msgObject.getKeyword() + "] RequestTime: ["
				+ SOAPConstants.convertTimestampToString(msgObject.getTTimes(), SOAPConstants.DATE_TIME) 
				+ "] Online Retry countdown: " + iRetries);
	}

	private void writeLogInfo(MsgObject msgObject, String result, String message ,int iRetries){
		Util.logger.info(this.getClass().getName() + ".getMessages() - "
				+ "Respone Code: [" + result +"]. "+ message + ", Details: MO_ID: [" + msgObject.getMo_id() + "] UserID: ["
				+ msgObject.getUserid() + "] ServiceID: ["
				+ msgObject.getServiceid() + "] Keyword: ["
				+ msgObject.getKeyword() + "] RequestID: ["
				+ msgObject.getRequestid() + "] CommandCode: ["
				+ msgObject.getKeyword() + "] RequestTime: ["
				+ SOAPConstants.convertTimestampToString(msgObject.getTTimes(), SOAPConstants.DATE_TIME) 
				+ "] Online Retry countdown: " + iRetries);
	}
	
	
	private static BigDecimal add2SMSSendFailed(MsgObject msgObject) {
		BigDecimal result =  msgObject.getRequestid();

		Util.logger.info(className + ".add2SMSSendFailed():" + msgObject.getUserid() + "@" + msgObject.getUsertext());
		String tablename = "sms_receive_error";
		String sSQLInsert = "insert into " + tablename
							+ "(REQUEST_ID,USER_ID,SERVICE_ID,MOBILE_OPERATOR,COMMAND_CODE,INFO,RECEIVE_DATE,RESPONDED,CPID, NOTES)"
							+ " values(?,?,?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement ps = null;
		DBPool dbpool = new DBPool();
		try {
			connection = dbpool.getConnectionGateway();

			ps = connection.prepareStatement(sSQLInsert);
			//ps.setBigDecimal(1, msgObject.getRequestid());
			ps.setBigDecimal(1, new BigDecimal(msgObject.getMo_id()));
			ps.setString(2, msgObject.getUserid());
			ps.setString(3, msgObject.getServiceid());
			ps.setString(4, msgObject.getMobileoperator());
			ps.setString(5, msgObject.getKeyword());
			ps.setString(6, msgObject.getUsertext());
			ps.setTimestamp(7, msgObject.getTTimes());
			ps.setInt(8, 0);
			ps.setInt(9, msgObject.getCpid());
			
			String notes = msgObject.getMsgnotes(); 
			if	(notes != null && notes.length() > 255){
				notes = notes.substring(0, 254);
			}
				
			ps.setString(10, notes);
			if (ps.executeUpdate() != 1) {
				Util.logger.error(className + ".add2SMSSendFailed():" + msgObject.getUserid() 
									+ ":" + msgObject.getUsertext() + ":ps.executeUpdate failed");
				result = new BigDecimal(-1);
			}
			ps.close();
		} catch (SQLException e) {
			Util.logger.error(className + ".add2SMSSendFailed():" + msgObject.getUserid()
					+ ":" + msgObject.getUsertext()
					+ ":Error add row from sms receive error:" + e.toString());
			result = new BigDecimal(-1);
		} catch (Exception e) {
			Util.logger.error(className + ".add2SMSSendFailed():" + msgObject.getUserid()
					+ ":" + msgObject.getUsertext()
					+ ":Error add row from sms receive error:" + e.toString());
			result = new BigDecimal(-1);
		}

		finally {
			dbpool.cleanup(connection);
		}
		
		return result;
	}
	
	public int sendToSDS(MsgObject msgObject) {		
		String moID 		= String.valueOf(msgObject.getRequestid());
		String userID		= msgObject.getUserid(); 
		String serviceID	= msgObject.getServiceid();
		String commandCode	= msgObject.getKeyword();
		String message		= msgObject.getUsertext();
		String telco		= msgObject.getMobileoperator(); 
		java.util.Calendar timRequest	= SOAPConstants.convertTimestampToCalendar(msgObject.getTTimes());
		Util.logger.info("log send to SDS --- MO_ID: [" + moID + "] UserID: ["
				+ userID + "] Telco: ["
				+ telco + "] ServiceID: ["
				+ serviceID + "] Message: ["
				+ message + "] CommandCode: ["
				+ commandCode + "] RequestTime: ["
				+ timRequest);
		int result_call = 999;
		try {
			SdsJavaClient4Mvas sdspayment = new SdsJavaClient4Mvas();
			result_call = sdspayment.receiveMO(moID,userID,serviceID,commandCode,message,telco,timRequest);
			sdspayment = null;
		} catch (Exception ex) {
			 ex.printStackTrace();
			 Util.logger.error("Exception from teco : " + telco + " with message " + message);
			 result_call = 998;
		}
		
		return result_call;
		
	}
	
	public synchronized String sendMessageMO(MsgObject msgObject, WSConfig wsConfig) throws Exception {	
		
		String url = wsConfig.getWsURL().trim();
		String module = wsConfig.getClassName().trim();
		String partnerUsername = wsConfig.getUserName();
		String partnerPassword = wsConfig.getPassword();

		MOSender sender = (MOSender) Class.forName(module).newInstance();
		
		return sender.sendMO(url, msgObject, partnerUsername, partnerPassword);
	}
	
	public boolean inWhiteList(String whiteList, String userID){
		boolean isAllow = true;
		
		List<String> listofUsers = getWhiteLists(whiteList, userID);
		
		if (listofUsers == null || listofUsers.size() == 0) {
			return true;
		}
		
		if (!listofUsers.contains(userID)){
			isAllow = false;
		}
		
		return isAllow;
	}
	
	
	private List<String> getWhiteLists(String whiteList, String userID){
		
		List<String> listofUsers = new ArrayList<String>();
		
		String users = whiteList;
		
		if (users != null ){
			users = users.trim(); 
		}
		
		if (users != null && users.length() > 0){
			String[] tmpUsers  = users.split(",");
			for (int i = 0; i < tmpUsers.length; i++) {
				listofUsers.add(tmpUsers[i].trim());
			}
		}
		
		return listofUsers;
	}
}