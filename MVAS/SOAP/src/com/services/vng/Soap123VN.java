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

public class Soap123VN extends ContentAbstract {
	private static final String className = "com.services.vng.Soap123VN";
	private static final String TUTHIENVNG_CP_CODE = "S123";         
	
	private static final String BUSY_SYSTEM = "He thong hien tai dang qua tai. Ban hay thu lai. DT ho tro 1800585888";
	private static final int CHARGE_BACK = 2;
	private static final String INVALID_SYNTAX = "Tin nhan cua ban khong dung cu phap, xin hay kiem tra va thu lai. Neu can ho tro xin goi so 1800585888. Cam on.";
	
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
		
		WSConfig wsConfig = WSConfigLoader.getInstance().getWSConfigByCpCode(TUTHIENVNG_CP_CODE);
		if (wsConfig == null){	
			Util.logger.warn(this.getClass().getName()+ ".getMessages():  URL WebService for CPCODE:["+ TUTHIENVNG_CP_CODE +"] Not Found");
			msgObject.setUsertext(BUSY_SYSTEM);
			msgObject.setMsgtype(CHARGE_BACK);
			messages.add(new MsgObject(msgObject));
			return messages;
		}
		
		while (iRetries > 0) {
			try {
				result = sendMessageMO(msgObject, wsConfig);
				
				// 202 transaction OK!
				if ("202".equals(result)) {
					writeLogInfo(msgObject, result, "Send OK!", iRetries);					
					return null;
				}else if ("400".equals(result)) {
					writeLogError(msgObject, result, "Invalid Data MO", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. Invalid Data MO");
					add2SMSSendFailed(msgObject);
					return null;
				}else if ("404".equals(result)) {
					writeLogError(msgObject, result , "Username & Password does not match", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. Username & Password does not match");
					add2SMSSendFailed(msgObject);
					msgObject.setUsertext(BUSY_SYSTEM);
					msgObject.setMsgtype(CHARGE_BACK);
					messages.add(new MsgObject(msgObject));
					return messages;
				}else if ("401".equals(result)) {
					writeLogError(msgObject, result , "Duplicated MOID", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. Duplicated MOID");
					add2SMSSendFailed(msgObject);
					msgObject.setUsertext(BUSY_SYSTEM);
					msgObject.setMsgtype(CHARGE_BACK);
					messages.add(new MsgObject(msgObject));
					return messages;
				}else if ("405".equals(result) || "406".equals(result) || "408".equals(result)) {
					writeLogError(msgObject, result , "System Busy - Can’t Save SMS Information", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. System Busy - Can’t Save SMS Information");
					add2SMSSendFailed(msgObject);
					msgObject.setUsertext(BUSY_SYSTEM);
					msgObject.setMsgtype(CHARGE_BACK);
					messages.add(new MsgObject(msgObject));
					return messages;
				}else if ("409".equals(result)) {
					writeLogError(msgObject, result , "System Busy - Can not send MT to tuthienVNGServiceMT", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. System Busy - Can not send MT to tuthienVNGServiceMT");
					add2SMSSendFailed(msgObject);
					msgObject.setUsertext(BUSY_SYSTEM);
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
		msgObject.setMsgtype(2);
		messages.add(new MsgObject(msgObject));
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