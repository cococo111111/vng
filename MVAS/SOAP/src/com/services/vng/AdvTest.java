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
import com.services.soap.mo.SOAPConstants;
import com.services.soap.mo.ServicePrice;
import com.services.soap.mo.Spam;
import com.services.soap.mo.WSConfigLoader;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.Constants;
import com.vmg.sms.process.ContentAbstract;
import com.vmg.sms.process.DBPool;
import com.vmg.sms.process.Keyword;
import com.vmg.sms.process.MsgObject;

public class AdvTest extends ContentAbstract {
	private static final String className = "com.services.vng.AdvTest";
	
	protected Collection getMessages(MsgObject msgObject, Keyword keyword) throws Exception {
		Collection messages = new ArrayList();;
				
		if (Spam.getInstance().isSpam(msgObject.getUserid())) {
			String returnMessage = Spam.SPAM_MESSAGE_TEXT;
			int msgType = Spam.SPAM_MESSAGE_TYPE;
			msgObject.setUsertext(returnMessage);
			msgObject.setMsgtype(msgType);
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
		
		if (ServicePrice.getInstance().overMaxMoneyAllow(msgObject)) {
			String returnMessage = Constants._prop.getProperty("MESSAGE_OVER_MONEY");
			int msgType = new Integer(Constants._prop.getProperty("MESSAGE_OVER_MONEY_TYPE")).intValue();
			msgObject.setUsertext(returnMessage);
			msgObject.setMsgtype(msgType);
			messages.add(new MsgObject(msgObject));
			return messages;
		}
		
		boolean updateOk = updateAdvUserStatus(msgObject.getUserid(),msgObject.getMobileoperator(), 0);
		if (updateOk){
			String returnMessage = Constants._prop.getProperty("MESSAGE_REGISTER_DONOT_RECEIVE_ADV");			
			msgObject.setUsertext(returnMessage);
			msgObject.setMsgtype(2);	//IMPORTANT
			messages.add(msgObject);
			Util.logger.info("Update User [" + msgObject.getUserid() +"] do not receive adv OK!");
			
		}else{
			String returnMessage = Constants._prop.getProperty("MESSAGE_SYSTEM_OVERLOAD");			
			msgObject.setUsertext(returnMessage);
			msgObject.setMsgtype(2);
			messages.add(msgObject);
			Util.logger.info("Update User [" + msgObject.getUserid() +"] do not receive adv Failed!");
		}
		
		return messages;
	}
	
	private static boolean updateAdvUserStatus(String UserID,String mobileOperator, int isReceiveAdv) {
	    String strQuery = "INSERT INTO adv_user(USER_ID,MOBILE_OPERATOR,IS_RECEIVE_ADV) VALUES (?,?,?) ON DUPLICATE KEY UPDATE IS_RECEIVE_ADV=0"; 
	    Connection connection = null;
		PreparedStatement ps = null;
		DBPool dbpool = new DBPool();
		int numberOfEffectedRows = 0;
		try {
			connection = dbpool.getConnectionGateway();
			ps = connection.prepareStatement(strQuery);
			ps.setString(1, UserID);
			ps.setString(2, mobileOperator);
			ps.setInt(3, isReceiveAdv);
		
			numberOfEffectedRows = ps.executeUpdate();
		
		} catch (SQLException e) {
			numberOfEffectedRows = -1;
			Util.logger.error(className + ".updateAdvUserStatus() ERROR: " + e.getMessage());
		} catch (Exception e) {
			numberOfEffectedRows = -1;
			Util.logger.error(className + ".updateAdvUserStatus() ERROR: " + e.getMessage());
		}

		finally {
			try{
				if (ps != null){
					ps.close();
				}
			}catch (Exception e) {}
		}
		return (numberOfEffectedRows != -1);
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