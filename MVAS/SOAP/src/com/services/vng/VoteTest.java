package com.services.vng;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.services.soap.mo.KeywordWhiteList;
import com.services.soap.mo.SOAPConstants;
import com.services.soap.mo.ServicePrice;
import com.services.soap.mo.SoapUtils;
import com.services.soap.mo.WSConfigLoader;
import com.services.vote.Session;
import com.services.vote.SessionItems;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.Constants;
import com.vmg.sms.process.ContentAbstract;
import com.vmg.sms.process.DBPool;
import com.vmg.sms.process.Keyword;
import com.vmg.sms.process.MsgObject;

public class VoteTest extends ContentAbstract {
	private static final String className = "com.services.vng.VoteTest";
	private static final String CONNECTION_VOTING = "voting";
	
	private static final String KEYWORD = "keyword";
	private static final String TYPE = "type";
	private static final String ITEM_CODE = "item_code";
	private static final String REPLACE_ITEM_CODE = "#MS#";
	private static final String REPLACE_MOBILE = "#MOBILE#";
	private static final String NUMBERS_OF_THE_ANSWER = "numbers_of_same_answer";
	
	private static final String TABLE_VOTING_VOTE = "voting_vote";	
	
	private static final String INVALID_SYNTAX = "Tin nhan sai cu phap.";
	private static final String OVERLOAD_SYSTEM = "He thong hien tai dang qua tai. Ban hay thu lai. DT ho tro 1900561558.";
	
	protected Collection getMessages(MsgObject msgObject, Keyword keyword)throws Exception {
		Collection messages = new ArrayList();
		
		KeywordWhiteList keywordWhiteList = WSConfigLoader.getInstance().getKeywordWhiteListByKeywordServiceID(keyword.getKeyword() + msgObject.getServiceid());
		if (keywordWhiteList != null && !inWhiteList(keywordWhiteList.getWhiteList(), msgObject.getUserid())){
			msgObject.setUsertext(keywordWhiteList.getMsgReturn());
			msgObject.setMsgtype(keywordWhiteList.getMsgType());
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
		Util.logger.info("messageInfor: " + messageInfor.toString());
		
		Date now = new Date();
        
        Session session = null;
        Connection connection = null;
        DBPool dbpool = null;
        try{
        	dbpool = new DBPool();
			connection = dbpool.getConnection(CONNECTION_VOTING); 
			session = Session.getVotingInfo(connection, messageInfor.get(KEYWORD), messageInfor.get(TYPE), now);
			
			if (session == null){
				msgObject.setUsertext("Tin nhan sai cu phap hoac het thoi gian tham gia bau chon. DT ho tro 1900561558.");
				msgObject.setMsgtype(1);
				messages.add(new MsgObject(msgObject));
				return messages;
			}
			
			if (session.getId() == -1){
				msgObject.setUsertext(OVERLOAD_SYSTEM);
				msgObject.setMsgtype(2);
				messages.add(new MsgObject(msgObject));
				return messages;
			}
						
			Util.logger.info("sessionInfor: keyword:[" + session.getKeyword() + "], ServiceID:[" + session.getServiceID() + "], "
					+ "VoteType:[" + session.getVoteType() + "], VoteNumberAgree:[" + session.getVoteNumberAgree() + "], " 
					+ "BeginTime:[" + session.getBeginTime() + "], EndTime:[" + session.getEndTime() + "]");
			
			if (!SoapUtils.isDigits(messageInfor.get(ITEM_CODE))){
				writeLogInfo(msgObject, "ITEM_CODE: [" + messageInfor.get(ITEM_CODE) + "] invalid. It must digits.");
				msgObject.setUsertext(INVALID_SYNTAX);
				msgObject.setMsgtype(1);
				messages.add(new MsgObject(msgObject));
				return messages;
			}
			
			//vote_type = ''; vote_number_agree=0
			//syntax: keyword item_id
			if (session.getVoteType().equals("") && session.getVoteNumberAgree() == 0){
				if (messageInfor.get(KEYWORD).equals("") || messageInfor.get(ITEM_CODE).equals("") 
						|| !messageInfor.get(NUMBERS_OF_THE_ANSWER).equals("") || !messageInfor.get(TYPE).equals("") ){
					writeLogInfo(msgObject, "INVALID_SYNTAX: " + "syntax: keyword item_id");
					msgObject.setUsertext(INVALID_SYNTAX);
					msgObject.setMsgtype(1);
					messages.add(new MsgObject(msgObject));
					return messages;
				}			
			}
			
			//vote_type = '';vote_number_agree=1
			//syntax: keyword item_id number_agree
			if (session.getVoteType().equals("") && session.getVoteNumberAgree() == 1){
				if ( messageInfor.get(KEYWORD).equals("") || messageInfor.get(ITEM_CODE).equals("")  
						|| messageInfor.get(NUMBERS_OF_THE_ANSWER).equals("") 
						|| !messageInfor.get(TYPE).equals("")){
					writeLogInfo(msgObject, "INVALID_SYNTAX: " + "syntax: keyword item_id number_agree");
					msgObject.setUsertext(INVALID_SYNTAX);
					msgObject.setMsgtype(1);
					messages.add(new MsgObject(msgObject));
					return messages;
				}			
			}
			
			//vote_type != ''; vote_number_agree=0
			//syntax: keyword vote_type item_id
			if (!session.getVoteType().equals("") && session.getVoteNumberAgree() == 0){
				if ( messageInfor.get(KEYWORD).equals("") || messageInfor.get(TYPE).equals("")
						|| messageInfor.get(ITEM_CODE).equals("")  
						|| !messageInfor.get(NUMBERS_OF_THE_ANSWER).equals("")){
					writeLogInfo(msgObject, "INVALID_SYNTAX: " + "syntax: keyword vote_type item_id");
					msgObject.setUsertext(INVALID_SYNTAX);
					msgObject.setMsgtype(1);
					messages.add(new MsgObject(msgObject));
					return messages;
				}			
			}
			
			//vote_type != ''; vote_number_agree=1; 
			//syntax: keyword vote_type item_id number_agree
			if (!session.getVoteType().equals("") && session.getVoteNumberAgree() == 1 ){
				if ( messageInfor.get(KEYWORD).equals("") || messageInfor.get(TYPE).equals("")
						|| messageInfor.get(ITEM_CODE).equals("")  
						|| messageInfor.get(NUMBERS_OF_THE_ANSWER).equals("")){
					writeLogInfo(msgObject, "INVALID_SYNTAX: " + "syntax: keyword vote_type item_id number_agree");
					msgObject.setUsertext(INVALID_SYNTAX);
					msgObject.setMsgtype(1);
					messages.add(new MsgObject(msgObject));
					return messages;
				}			
			}
			int itemId = SessionItems.findItemByCode(connection, session.getId(), messageInfor.get(ITEM_CODE)); 
			if ( itemId < 1 ){
				msgObject.setUsertext(session.getTitleMessage() + " " + messageInfor.get(ITEM_CODE) + " khong ton tai, vui long kiem tra tin nhan cua ban.");
				msgObject.setMsgtype(1);
				messages.add(new MsgObject(msgObject));		
				return messages;
			}
			
			Boolean insertedOK = add2VotingVote(connection, session.getId(), itemId, msgObject.getUserid(), 
					session.getVoteType(), new Timestamp(now.getTime()), SoapUtils.parseInt(messageInfor.get(NUMBERS_OF_THE_ANSWER)));
			
			if (insertedOK){
				String msgReturn = SoapUtils.searchReplace(session.getTemplateMessage(), REPLACE_ITEM_CODE, messageInfor.get(ITEM_CODE));
				msgReturn = SoapUtils.searchReplace(msgReturn, REPLACE_MOBILE, msgObject.getUserid());
				msgObject.setUsertext(session.getTitleMessage() + " " + msgReturn);
				msgObject.setMsgtype(1);
				messages.add(new MsgObject(msgObject));
				Util.logger.info("insert into table " + TABLE_VOTING_VOTE + " OK!");
			}else{
				msgObject.setUsertext(OVERLOAD_SYSTEM);
				msgObject.setMsgtype(2);
				messages.add(new MsgObject(msgObject));
				Util.logger.error("insert into table " + TABLE_VOTING_VOTE + " FAILED!");
			}
        }catch (Exception e) {
			msgObject.setUsertext(OVERLOAD_SYSTEM);
			msgObject.setMsgtype(2);
			messages.add(new MsgObject(msgObject));
			
			String error = e != null ? e.getMessage() : "NULL"; 
			writeLogError(msgObject, error);
		}
        finally{
        	dbpool.cleanup(connection);
        }        
		return messages;
	}
	
	private void writeLogError(MsgObject msgObject, String message){
		Util.logger.error(this.getClass().getName() + ".getMessages() -ERROR: " + message
				+ ". Details: Request_ID: [" + msgObject.getRequestid() + "], "
				+ "UserID: [" + msgObject.getUserid() + "], " 
				+ "ServiceID: [" + msgObject.getServiceid() + "], "
				+ "Keyword: [" + msgObject.getKeyword() + "], "
				+ "RequestID: [" + msgObject.getRequestid() + "], "
				+ "CommandCode: [" + msgObject.getKeyword() + "], "
				+ "RequestTime: [" + SOAPConstants.convertTimestampToString(msgObject.getTTimes(), SOAPConstants.DATE_TIME) + "]. ") ;
	}

	private void writeLogInfo(MsgObject msgObject, String message){
		Util.logger.info(this.getClass().getName() + ".getMessages() -Infor: " + message
				+ ". Details: Request_ID: [" + msgObject.getRequestid() + "], "
				+ "UserID: [" + msgObject.getUserid() + "], " 
				+ "ServiceID: [" + msgObject.getServiceid() + "], "
				+ "Keyword: [" + msgObject.getKeyword() + "], "                                                                                                                          
				+ "RequestID: [" + msgObject.getRequestid() + "], "
				+ "CommandCode: [" + msgObject.getKeyword() + "], "
				+ "RequestTime: [" + SOAPConstants.convertTimestampToString(msgObject.getTTimes(), SOAPConstants.DATE_TIME) + "]. ");
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
			messageInfor.put(KEYWORD, removedSpaceMessages.get(0));
			messageInfor.put(TYPE, "");
			messageInfor.put(ITEM_CODE, "");
			messageInfor.put(NUMBERS_OF_THE_ANSWER, "");
		}else if(removedSpaceMessages.size() == 2){
			messageInfor.put(KEYWORD, removedSpaceMessages.get(0));
			messageInfor.put(TYPE, "");
			messageInfor.put(ITEM_CODE, removedSpaceMessages.get(1));
			messageInfor.put(NUMBERS_OF_THE_ANSWER, "");
		}else if (removedSpaceMessages.size() == 3){
			messageInfor.put(KEYWORD, removedSpaceMessages.get(0));
			if (SoapUtils.isDigits(removedSpaceMessages.get(1)) && SoapUtils.isDigits(removedSpaceMessages.get(2)) ){
				messageInfor.put(TYPE, "");				
				messageInfor.put(ITEM_CODE, removedSpaceMessages.get(1));
				messageInfor.put(NUMBERS_OF_THE_ANSWER, removedSpaceMessages.get(2));
			}else{
				messageInfor.put(TYPE, removedSpaceMessages.get(1));				
				messageInfor.put(ITEM_CODE, removedSpaceMessages.get(2));
				messageInfor.put(NUMBERS_OF_THE_ANSWER, "");
			}
		}else if (removedSpaceMessages.size() == 4){
			messageInfor.put(KEYWORD, removedSpaceMessages.get(0));
			messageInfor.put(TYPE, removedSpaceMessages.get(1));
			messageInfor.put(ITEM_CODE, removedSpaceMessages.get(2));
			messageInfor.put(NUMBERS_OF_THE_ANSWER, removedSpaceMessages.get(3));
		}else{
			messageInfor.put(KEYWORD, "");
			messageInfor.put(TYPE, "");
			messageInfor.put(ITEM_CODE, "");
			messageInfor.put(NUMBERS_OF_THE_ANSWER, "");
		}
		
		return messageInfor;
	}
		
	private static boolean add2VotingVote(Connection connection, int sessionID, int itemID, String mobile, String voteType, Timestamp voteTime, int voteAgree) {
		String tablename = TABLE_VOTING_VOTE;
		String sSQLInsert = "insert into " + tablename
							+ "(session_id, item_id ,mobile ,vote_type, vote_time, vote_agree)"
							+ " values(?,?,?,?,?,?)";

		PreparedStatement ps = null;
		int numberOfEffectedRows = 0;
		try {
			ps = connection.prepareStatement(sSQLInsert);
			ps.setInt(1, sessionID);
			ps.setInt(2, itemID);
			ps.setString(3, mobile);
			ps.setString(4, voteType);
			ps.setTimestamp(5, voteTime);
			ps.setInt(6, voteAgree);
			
			numberOfEffectedRows = ps.executeUpdate();
			
		} catch (SQLException e) {
			numberOfEffectedRows = 0;
			Util.logger.error(className + ".add2VotingVote() ERROR: " + e.getMessage());
		} catch (Exception e) {
			numberOfEffectedRows = 0;
			Util.logger.error(className + ".add2VotingVote() ERROR: " + e.getMessage());
		}
		
		finally {
			try{
				if (ps != null){
					ps.close();
				}
			}catch (Exception e) {}
		}
		return (numberOfEffectedRows == 1);
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