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
import java.util.Collection;
import java.util.List;

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

public class SoapEsaleR extends ContentAbstract {
	//private static final String BUSY_SYSTEM = "He thong hien tai dang qua tai. Xin ban hay thu lai sau vai phut nua. Neu can ho tro xin goi so 1900561558. Cam on.";
	private static final int CHARGE_BACK = 2;
	private static final String KEYWORD = "KEYWORD";
		
	private static final String InvalidSyntex = "Tin nhan cua ban khong dung cu phap, xin hay kiem tra va thu lai. Neu can ho tro xin goi so 1900561558. Cam on.";
	
	private static final String className = "com.services.vng.SoapEsaleR";
	//LACCR = LOCK COUNT CSTOOL: for Real
	private static final String Esale_CP_CODE = "ESALE";
	
	protected Collection getMessages(MsgObject msgObject, Keyword keyword) throws Exception {
		int iRetries = 3;
		int iTimeout = SOAPConstants.RETRIES_TIME;
		Collection messages = new ArrayList();
		String result = "";
		
		if (!isOKConfig()){
			msgObject.setUsertext(Constants._prop.getProperty("MESSAGE_SYSTEM_OVERLOAD"));
			msgObject.setMsgtype(CHARGE_BACK);
			messages.add(new MsgObject(msgObject));
			return messages;						
		}
		if (!isAllowOperator(msgObject.getMobileoperator())){
			msgObject.setUsertext(Constants._prop.getProperty("ADS_ONLY_SUPPORT_FOR_OPERATOR", "Hien tai he thong chi ho tro cho thue bao Mobifone").trim());
			msgObject.setMsgtype(CHARGE_BACK);
			messages.add(new MsgObject(msgObject));
			return messages;
		}
		
		if (!msgObject.getKeyword().equalsIgnoreCase(msgObject.getUsertext().split(" ")[0].trim()) ){
			msgObject.setUsertext(InvalidSyntex);
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
		
		WSConfig wsConfig = WSConfigLoader.getInstance().getWSConfigByCpCode(Esale_CP_CODE);
		if (wsConfig == null){	
			Util.logger.warn(this.getClass().getName()+ ".getMessages():  URL WebService for CPCODE:["+ Esale_CP_CODE +"] Not Found");
			msgObject.setUsertext(Constants._prop.getProperty("MESSAGE_SYSTEM_OVERLOAD"));
			msgObject.setMsgtype(CHARGE_BACK);
			messages.add(new MsgObject(msgObject));
			return messages;
		}
			
		while (iRetries > 0) {
			try {
				result = sendMessageMO(msgObject, wsConfig);
				
				// 1 transaction OK!
				if ("1".equals(result)) {
					writeLogInfo(msgObject, result, "Send OK!", iRetries);
					return null;
				}else if ("-7".equals(result)) {
					writeLogError(msgObject, result , "Username & Password does not match", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. Username & Password does not match");
					add2SMSSendFailed(msgObject);
					msgObject.setUsertext(Constants._prop.getProperty("MESSAGE_SYSTEM_OVERLOAD"));
					msgObject.setMsgtype(CHARGE_BACK);
					messages.add(new MsgObject(msgObject));
					return messages;
				}else if ("-6".equals(result)) {
					writeLogError(msgObject, result , "System Busy", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. System Busy");
					add2SMSSendFailed(msgObject);
					msgObject.setUsertext(Constants._prop.getProperty("MESSAGE_SYSTEM_OVERLOAD"));
					msgObject.setMsgtype(CHARGE_BACK);
					messages.add(new MsgObject(msgObject));
					return messages;
				}else {
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
								+ "] ServiceID: [" + msgObject.getServiceid()
								+ "] Keyword: [" + msgObject.getKeyword()
								+ "] RequestID: [" + msgObject.getRequestid()
								+ "] CommandCode: [" + msgObject.getKeyword()
								+ "] RequestTime: ["
								+ SOAPConstants.convertTimestampToString(msgObject.getTTimes(), SOAPConstants.DATE_TIME)
								+ "] Online Retry countdown: " + iRetries);
				msgObject.setMsgNotes("Respone Code: [" + result +"]. " + message);
				
				iRetries--;
				Thread.sleep(iTimeout * 1000);
				continue;
			}

		}
			
		add2SMSSendFailed(msgObject);
		String returnMessage = Constants._prop.getProperty("MESSAGE_SYSTEM_OVERLOAD");
		msgObject.setUsertext(returnMessage);
		msgObject.setMsgtype(CHARGE_BACK);
		messages.add(msgObject);
		return messages;
	}
		
	private void writeLogError(MsgObject msgObject, String result, String message ,int iRetries){
		Util.logger.error(this.getClass().getName() + ".getMessages() - "
				+ "Respone Code: [" + result +"]. "+ message + ", Details: MO_ID: [" + msgObject.getMo_id() + "] UserID: ["
				+ msgObject.getUserid() + "] ServiceID: ["
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
	private static boolean add2CanceledAds(String userID) {
		boolean OK = false;
		Util.logger.info(className + ".add2CanceledAds()");
		
		String tablename = "vng_cancel_ads";
		String sSQLInsert = "replace into " + tablename
							+ "(userid)"
							+ " values(?)";

		Connection connection = null;
		PreparedStatement ps = null;
		DBPool dbpool = new DBPool();
		try {
			connection = dbpool.getConnectionGateway();

			ps = connection.prepareStatement(sSQLInsert);
			ps.setString(1, userID);
			if (ps.executeUpdate() >= 1) {
				OK = true;
			}
			ps.close();
		} catch (SQLException e) {
			OK = false;
			Util.logger.info(className + ".add2CanceledAds() error:" + e.getMessage());
		} catch (Exception e) {
			OK = false;
			Util.logger.info(className + ".add2CanceledAds() error:" + e.getMessage());
		}

		finally {
			dbpool.cleanup(connection);
		}
		
		return OK;
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
	private static boolean isAllowOperator(String operator){
		String ALLOW_OPERATORS = Constants._prop.getProperty("ALLOW_OPERATORS");
		if (ALLOW_OPERATORS != null){
			String[] ALLOW_OPERATORStmp = ALLOW_OPERATORS.trim().split(",");
			if (ALLOW_OPERATORStmp != null){
				for (int i = 0; i < ALLOW_OPERATORStmp.length; i++) {
					if (ALLOW_OPERATORStmp[i].trim().equalsIgnoreCase(operator)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private static boolean isOKConfig(){
		if (Constants._prop.getProperty("ALLOW_OPERATORS") == null 
				|| Constants._prop.getProperty("ALLOW_OPERATORS").trim().equals("")
				|| Constants._prop.getProperty("ADS_ONLY_SUPPORT_FOR_OPERATOR") == null
				|| Constants._prop.getProperty("ADS_ONLY_SUPPORT_FOR_OPERATOR").trim().equals("")
				){
			return false;
			
		}
		return true;
	}
}