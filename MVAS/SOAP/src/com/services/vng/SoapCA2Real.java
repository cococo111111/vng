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
 * @author duynd@vinagame.com.vn
 * @version 1.0
 */

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.services.soap.mo.BaseMOSender;
import com.services.soap.mo.KeywordWhiteList;
import com.services.soap.mo.SOAPConstants;
import com.services.soap.mo.ServicePrice;
import com.services.soap.mo.SoapUtils;
import com.services.soap.mo.WSConfig;
import com.services.soap.mo.WSConfigLoader;
import com.services.soap.mo.ZMUtils;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.Constants;
import com.vmg.sms.process.ContentAbstract;
import com.vmg.sms.process.DBPool;
import com.vmg.sms.process.Keyword;
import com.vmg.sms.process.MsgObject;

public class SoapCA2Real extends ContentAbstract {
	private static final String className = "com.services.vng.SoapCA2Real";
		
	protected Collection getMessages(MsgObject msgObject, Keyword keyword) throws Exception {
		int iRetries = SOAPConstants.MAX_RETRIES;
		int iTimeout = SOAPConstants.RETRIES_TIME;
		String result = "";
		Collection messages = new ArrayList();
		
		KeywordWhiteList keywordWhiteList = WSConfigLoader.getInstance().getKeywordWhiteListByKeywordServiceID(keyword.getKeyword() + msgObject.getServiceid());
		if (keywordWhiteList != null && !inWhiteList(keywordWhiteList.getWhiteList(), msgObject.getUserid())){
			msgObject.setUsertext(keywordWhiteList.getMsgReturn());
			msgObject.setMsgtype(keywordWhiteList.getMsgType());
			messages.add(new MsgObject(msgObject));
			return messages;
		}		
		
		if (msgObject.getMobileoperator().equals("SFONE")){
			msgObject.setUsertext("He thong hien tai khong ho tro cho thue bao SFONE!!!");
			msgObject.setMsgtype(2);
			messages.add(new MsgObject(msgObject));
			return messages;
		}

		if (ServicePrice.getInstance().overMaxMoneyAllow(msgObject)) {
			String returnMessage = Constants._prop.getProperty("MESSAGE_OVER_MONEY");
			int msgType = new Integer(Constants._prop.getProperty("MESSAGE_OVER_MONEY_TYPE")).intValue();
			msgObject.setUsertext(returnMessage);
			msgObject.setMsgtype(msgType);
			messages.add(new MsgObject(msgObject));
			return messages;
		}
		
		Map<String, String> messageInfor = analyseMessage(msgObject.getUsertext());
		
		if (messageInfor.get(ZMUtils.CARD_ID).equals("") || !SoapUtils.isDigits(messageInfor.get(ZMUtils.CARD_ID))){
			msgObject.setUsertext("[" + msgObject.getServiceid() + "] Ban da nhap sai cu phap. Hay kiem tra san pham ban muon tai ve. DT ho tro 1900561558 hay vao http://hotro.zing.vn");
			msgObject.setMsgtype(1);
			messages.add(new MsgObject(msgObject));
			return messages;
		}
		
		Map<String, String> cpInfor = ZMUtils.getCPCodeFromZingMobile(messageInfor.get(ZMUtils.CARD_ID), ZMUtils.TABLE_CARD);
		Util.logger.info("cpInfor:" + cpInfor);
						
		if (cpInfor.get(ZMUtils.CP_CODE).equals("")){	//NOT FOUND CP_CODE
			Util.logger.warn(this.getClass().getName()+ ".getMessages(): CARD_ID: [" + messageInfor.get(ZMUtils.CARD_ID) + "] NOT FOUND CP_CODE");
			msgObject.setUsertext("[mKool] Thiep " + messageInfor.get(ZMUtils.CARD_ID) + " khong ton tai hoac da bi xoa. Ban hay kiem tra lai tren website http://mkool.zing.vn. DT ho tro 1900561558");
			msgObject.setMsgtype(1);
			messages.add(new MsgObject(msgObject));
			return messages;
		}
		
		if (cpInfor.get(ZMUtils.CP_ITEM_ID).equals("")){
			Util.logger.warn(this.getClass().getName()+ ".getMessages(): CP_ITEM_ID: [" + cpInfor.get(ZMUtils.CP_ITEM_ID) + "] NOT FOUND!");
			msgObject.setUsertext("[mKool] Thiep " + messageInfor.get(ZMUtils.CARD_ID) + " khong ton tai hoac da bi xoa. Ban hay kiem tra lai tren website http://mkool.zing.vn. DT ho tro 1900561558");
			msgObject.setMsgtype(2);
			messages.add(new MsgObject(msgObject));
			return messages;			
		}
		
		if (cpInfor.get(ZMUtils.CP_CODE).equals("-1") || cpInfor.get(ZMUtils.CP_ITEM_ID).equals("-1")){	//System ZingSms is busy
			Util.logger.warn(this.getClass().getName()+ ".getMessages():  System ZingSMS is busy.");
			msgObject.setUsertext("He thong hien tai dang qua tai. Ban hay thu lai. DT ho tro 1900561558");
			msgObject.setMsgtype(2);
			messages.add(new MsgObject(msgObject));
			return messages;
		}
		
		WSConfig wsConfig = WSConfigLoader.getInstance().getWSConfigByCpCode(cpInfor.get(ZMUtils.CP_CODE));
		if (wsConfig == null){	
			Util.logger.warn(this.getClass().getName()+ ".getMessages():  URL WebService for CPCODE:["+ cpInfor.get(ZMUtils.CP_CODE) +"] Not Found");
			msgObject.setUsertext("He thong hien tai dang qua tai. Ban hay thu lai. DT ho tro 1900561558");
			msgObject.setMsgtype(2);
			messages.add(new MsgObject(msgObject));
			return messages;
		}
						
		rewriteMessage(msgObject, messageInfor, cpInfor.get(ZMUtils.CP_ITEM_ID));			
		
		while (iRetries > 0) {
			try {
				result = sendMessageMO(msgObject, wsConfig, messageInfor.get(ZMUtils.CARD_ID));
				// 1 transaction OK!
				if ("1".equals(result)) {
					ZMUtils.add2MoContentLog(msgObject, cpInfor.get(ZMUtils.CP_CODE));
					writeLogInfo(msgObject, result, "SEND MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] OK!", iRetries);
					return null;
				}else if ("-1".equals(result)) {
					writeLogError(msgObject, result, "SEND MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] FAILED!", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. " + "SEND MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] FAILED!");
					add2SMSSendFailed(msgObject);
					return null;
				}else if ("202".equals(result)) {
					ZMUtils.add2MoContentLog(msgObject, cpInfor.get(ZMUtils.CP_CODE));
					writeLogInfo(msgObject, result, "Send MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] OK!", iRetries);					
					return null;
				}else if ("400".equals(result)) {
					writeLogError(msgObject, result, "Send MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] FAILED!." + " Invalid Data MO", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. " + "Send MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] FAILED!" + " Invalid Data MO");
					add2SMSSendFailed(msgObject);
					return null;
				}else if ("404".equals(result)) {
					writeLogError(msgObject, result , "Send MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] FAILED!" + " Username & Password does not match", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]" + ". Send MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] FAILED!" + " Username & Password does not match");
					add2SMSSendFailed(msgObject);
					return null;
				}else if ("407".equals(result)) {
					writeLogError(msgObject, result , "Send MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] FAILED! " + "Invalid CommandCode or ServiceID", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. " + "Send MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] FAILED! " + "Invalid CommandCode or ServiceID");
					add2SMSSendFailed(msgObject);
					return null;
				}else if ("401".equals(result)) {
					writeLogError(msgObject, result , "Send MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] FAILED! " + "Duplicated MOID", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. " + "Send MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] FAILED! " + "Duplicated MOID");
					add2SMSSendFailed(msgObject);
					return null;
				}else if ("405".equals(result) || "406".equals(result) || "408".equals(result)) {
					writeLogError(msgObject, result , "Send MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] FAILED! " + "System Busy - Can not Save SMS Information", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. " + "Send MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] FAILED! " +"System Busy - Can not Save SMS Information");
					add2SMSSendFailed(msgObject);
					return null;
				}else if ("409".equals(result)) {
					writeLogError(msgObject, result , "Send MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] FAILED! " + "System Busy - Can not send MT to ZingWebServiceMT", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. " + "Send MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] FAILED! " + "System Busy - Can not send MT to ZingWebServiceMT");
					add2SMSSendFailed(msgObject);
					return null;
				}else {
					writeLogError(msgObject,  result , "Send MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] FAILED! " + "Going For Retry, Sleeping ", iRetries);					
					msgObject.setMsgNotes("Respone Code: [" + result +"]. " + "Send MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] FAILED! " +"Going For Retry, Sleeping");
					
					iRetries--;
					Thread.sleep(iTimeout * 1000);
					continue;
				}
			} catch (Exception e) {
				String message = "";
				message = (e != null) ? e.getMessage(): "NULL";
				Util.logger.error(this.getClass().getName()+ ".getMessages(). "
								+ "ERROR!: "+ "Send MO to CP:[" + cpInfor.get(ZMUtils.CP_CODE) + "] FAILED! " + message +  ". Got Respone Code: [" + result +"], Going For Retry, Sleeping,Details: "
								+ "MO_ID: [" + msgObject.getMo_id() + "], "   
								+ "UserID: [" + msgObject.getUserid() + "], "
								+ "ServiceID: [" + msgObject.getServiceid() + "], "
								+ "Keyword: [" + msgObject.getKeyword() + "], "
								+ "RequestID: [" + msgObject.getRequestid() + "], "
								+ "CommandCode: [" + msgObject.getKeyword() + "], "
								+ "RequestTime: [" + SOAPConstants.convertTimestampToString(msgObject.getTTimes(), SOAPConstants.DATE_TIME) + "]. "
								+ "Online Retry countdown: " + iRetries);
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
	
	private void rewriteMessage(MsgObject msgObject, Map<String, String> messageInfor, String cpItemID){
		String message = "";
		if (messageInfor.get(ZMUtils.FRIEND_MOBILE).equals("")){
			message = messageInfor.get(ZMUtils.KEYWORD) + " " + cpItemID;
		}else{
			message = messageInfor.get(ZMUtils.KEYWORD) + " " + cpItemID + " " +  messageInfor.get(ZMUtils.FRIEND_MOBILE);
		}
		msgObject.setUsertext(message);
	}
	
	private void writeLogError(MsgObject msgObject, String result, String message ,int iRetries){
		Util.logger.error(this.getClass().getName() + ".getMessages() -ERROR: " + message
				+ ", Respone Code: [" + result +"]. "+ "Details: Request_ID: [" + msgObject.getRequestid() + "], "
				+ "UserID: [" + msgObject.getUserid() + "], " 
				+ "ServiceID: [" + msgObject.getServiceid() + "], "
				+ "Keyword: [" + msgObject.getKeyword() + "], "
				+ "RequestID: [" + msgObject.getRequestid() + "], "
				+ "CommandCode: [" + msgObject.getKeyword() + "], "
				+ "RequestTime: [" + SOAPConstants.convertTimestampToString(msgObject.getTTimes(), SOAPConstants.DATE_TIME) + "]. " 
				+ "Online Retry countdown: " + iRetries);
	}

	private void writeLogInfo(MsgObject msgObject, String result, String message ,int iRetries){
		Util.logger.info(this.getClass().getName() + ".getMessages() -Infor: " + message
				+ ", Respone Code: [" + result +"]. "+ "Details: Request_ID: [" + msgObject.getRequestid() + "], "
				+ "UserID: [" + msgObject.getUserid() + "], " 
				+ "ServiceID: [" + msgObject.getServiceid() + "], "
				+ "Keyword: [" + msgObject.getKeyword() + "], "
				+ "RequestID: [" + msgObject.getRequestid() + "], "
				+ "CommandCode: [" + msgObject.getKeyword() + "], "
				+ "RequestTime: [" + SOAPConstants.convertTimestampToString(msgObject.getTTimes(), SOAPConstants.DATE_TIME) + "]. " 
				+ "Online Retry countdown: " + iRetries);
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
			ps.setBigDecimal(1, msgObject.getRequestid());
			//ps.setBigDecimal(1, new BigDecimal(msgObject.getMo_id()));
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
	
	public synchronized String sendMessageMO(MsgObject msgObject, WSConfig wsConfig, String idVNG) throws Exception {	
		
		String url = wsConfig.getWsURL().trim();
		String module = wsConfig.getClassName().trim();
		String partnerUsername = wsConfig.getUserName();
		String partnerPassword = wsConfig.getPassword();

		BaseMOSender sender = (BaseMOSender) Class.forName(module).newInstance();
		
		return sender.sendMO(url, msgObject, idVNG, partnerUsername, partnerPassword);
	}
	
	private Map<String, String> analyseMessage(String message){
		Map<String, String> messageInfor = new HashMap<String, String>();
		
		message = message.trim();
		String[] subMessages = message.split(" ");
		List<String> removedSpaceMessages = new ArrayList<String>();
		for (int i = 0; i < subMessages.length; i++) {
			if (!subMessages[i].trim().equals("")){
				removedSpaceMessages.add(subMessages[i].trim());
			}
		}
		
		if (removedSpaceMessages.size() == 1){
			messageInfor.put(ZMUtils.KEYWORD, removedSpaceMessages.get(0));
			messageInfor.put(ZMUtils.CARD_ID, "");
			messageInfor.put(ZMUtils.FRIEND_MOBILE, "");
		}else if(removedSpaceMessages.size() == 2){
			messageInfor.put(ZMUtils.KEYWORD, removedSpaceMessages.get(0));
			messageInfor.put(ZMUtils.CARD_ID, removedSpaceMessages.get(1));
			messageInfor.put(ZMUtils.FRIEND_MOBILE, "");
		}else if (removedSpaceMessages.size() == 3){
			messageInfor.put(ZMUtils.KEYWORD, removedSpaceMessages.get(0));
			messageInfor.put(ZMUtils.CARD_ID, removedSpaceMessages.get(1));
			messageInfor.put(ZMUtils.FRIEND_MOBILE, removedSpaceMessages.get(2));
		}
		
		return messageInfor;
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
