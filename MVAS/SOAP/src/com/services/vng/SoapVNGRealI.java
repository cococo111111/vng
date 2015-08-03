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
import com.services.soap.mo.SoapUtils;
import com.services.soap.mo.Spam;
import com.services.soap.mo.SpamResult;
import com.services.soap.mo.WSConfig;
import com.services.soap.mo.WSConfigLoader;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.ContentAbstract;
import com.vmg.sms.process.DBPool;
import com.vmg.sms.process.Keyword;
import com.vmg.sms.process.MsgObject;

public class SoapVNGRealI extends ContentAbstract {
	private static final String className = "com.services.vng.SoapVNGRealI";
	private static final String PAYMMENT_CP_CODE = "SMSPR";
	
	protected Collection getMessages(MsgObject msgObject, Keyword keyword) throws Exception {
		int iRetries = SOAPConstants.MAX_RETRIES;
		int iTimeout = SOAPConstants.RETRIES_TIME;
		Collection messages = new ArrayList();
		String result = "";
		
		WSConfig wsConfig = WSConfigLoader.getInstance().getWSConfigByCpCode(PAYMMENT_CP_CODE);
		if (wsConfig == null){	
			Util.logger.warn(this.getClass().getName()+ ".getMessages():  URL WebService for CPCODE:["+ PAYMMENT_CP_CODE +"] Not Found");
			msgObject.setUsertext("He thong hien tai dang qua tai. Ban hay thu lai. DT ho tro 1900561558");
			msgObject.setMsgtype(2);
			messages.add(new MsgObject(msgObject));
			return messages;
		}
		
		synchronized(SoapVNGRealI.class){
			
			synchronized (Spam.class) {	
				SpamResult spamResult = Spam.getInstance().CheckSpamAll(msgObject.getMobileoperator(), msgObject.getServiceid(), msgObject.getUserid(), msgObject.getTTimes(), msgObject.getKeyword(), msgObject.getMo_id());
				if (null != spamResult && 1 == spamResult.getIs_spam() ) {
					String returnMessage = buildSpamMessage(spamResult.getMo_count(), spamResult.getMo_duration());
					msgObject.setUsertext(returnMessage);
					msgObject.setMsgtype(Spam.SPAM_MESSAGE_TYPE);
					messages.add(new MsgObject(msgObject));
					SoapUtils.add2MOLog(msgObject, "202", wsConfig.getCpID(), 1);
					return messages;
				}
				
				if (Spam.getInstance().isOverMoney(msgObject.getMobileoperator(), msgObject.getServiceid(), msgObject.getUserid())){
					msgObject.setUsertext(Spam.MESSAGE_OVER_MONEY);
					msgObject.setMsgtype(Spam.MESSAGE_OVER_MONEY_TYPE);
					messages.add(new MsgObject(msgObject));
					SoapUtils.add2MOLog(msgObject, "202", wsConfig.getCpID(), 1);
					return messages;
				}
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
						
			SoapUtils.add2MOLog(msgObject, "202", wsConfig.getCpID(), 1);
					
			String returnMessage = "Internal MT tra ve gia lap send MO to cp OK";
			msgObject.setUsertext(returnMessage);
			msgObject.setMsgtype(1);
			messages.add(msgObject);
			return messages;
		}
		/*add2SMSSendFailed(msgObject);
		String returnMessage = Constants._prop.getProperty("MESSAGE_SYSTEM_OVERLOAD");
		msgObject.setUsertext(returnMessage);
		msgObject.setMsgtype(2);
		messages.add(msgObject);
		return messages;
		*/
	}
	
	private String buildSpamMessage(int mo_count, int mo_duration){
		if (mo_count == 3 && mo_duration == 5){
			return Spam.THREE_MESSAGES_IN_FIVE_MINUTES;
		}else if (mo_count == 5 && mo_duration == 10){
			return Spam.FIVE_MESSAGES_IN_TEN_MINUTES;		
		}else if (mo_count == 30 && mo_duration == 60*1){
			return Spam.THIRTY_MESSAGES_IN_ONE_HOUR;		
		}else if (mo_count == 300 && mo_duration == 60*24){
			return Spam.THREE00_MESSAGES_IN_24_HOUR;	
		}else{
			return "Tin nhan Spam. Unknown";					
		}
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