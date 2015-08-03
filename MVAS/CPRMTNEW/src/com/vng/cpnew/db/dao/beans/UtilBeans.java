package com.vng.cpnew.db.dao.beans;

public class UtilBeans {
	public static MsgMT buildMsgMT(String requestID, String UserID,
			String ServiceID, String CommandCode, String Message,
			String Operator, int MsgType, int ContentType, int SumMT,
			String PartnerUsername, String PartnerPassword, String IP) {
		MsgMT msgMT = new MsgMT();
		msgMT.setRequestID(requestID);
		msgMT.setUserID(UserID);
		msgMT.setMessage(Message);
		msgMT.setServiceID(ServiceID);
		msgMT.setCommandCode(CommandCode);
		msgMT.setOperator(Operator);
		msgMT.setMsgType(MsgType);
		msgMT.setContentType(ContentType);
		msgMT.setSumMT(SumMT);
		msgMT.setPartnerUsername(PartnerUsername);
		msgMT.setPartnerPassword(PartnerPassword);
		msgMT.setIP(IP);

		return msgMT;
	}

	public static EmsSendQueue buildEmsSendQueue(String requestID,
			String UserID, String ServiceID, String CommandCode,
			String Message, String Operator, int MsgType, int ContentType,
			int SumMT) {
		EmsSendQueue emsSendQueue = new EmsSendQueue();

		emsSendQueue.setRequestID(requestID);
		emsSendQueue.setUserID(UserID);
		emsSendQueue.setServiceID(ServiceID);
		emsSendQueue.setCommandCode(CommandCode);
		emsSendQueue.setMessage(Message);
		emsSendQueue.setOperator(Operator);
		emsSendQueue.setMsgType(MsgType);
		emsSendQueue.setContentType(ContentType);
		emsSendQueue.setSumMT(SumMT);

		return emsSendQueue;
	}
}
