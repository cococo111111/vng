package com.vng.adsnew.db.dao.beans;

public class UtilBeans {
	public static MsgMT buildMsgMT(String requestID, String UserID,
			String ServiceID, String CommandCode, String Message,
			String Operator, int MsgType, int ContentType, int SumMT,
			String PartnerUsername, String PartnerPassword, String IP) {
		MsgMT msgMT = new MsgMT();
		msgMT.setRequestID(requestID);
		msgMT.setUserID(UserID);
		msgMT.setMessage(Message);
		if (Operator.toUpperCase().trim().equalsIgnoreCase("VIETEL")) {
			if (CommandCode.toUpperCase().trim().equalsIgnoreCase("SHOP123MT") || CommandCode.toUpperCase().trim().equalsIgnoreCase("123VN_SPAM")) {
				msgMT.setServiceID("123VN");
			}
			else if (CommandCode.toUpperCase().trim().equalsIgnoreCase("PAY123MT")) {
				msgMT.setServiceID("123PAY");
			}
			/*else if (CommandCode.toUpperCase().trim().equalsIgnoreCase("PASSPORTMT")) {
				msgMT.setServiceID("70037");
			}
			else if (CommandCode.toUpperCase().trim().equalsIgnoreCase("GSOMT")) {
				msgMT.setServiceID("70037");
			}*/
			else {
				msgMT.setServiceID("VNG");
			}
			msgMT.setOperator("VIETEL1");
		} else {
			msgMT.setServiceID(ServiceID);
			msgMT.setOperator(Operator);
		}
		msgMT.setCommandCode(CommandCode);
		if (Operator.toUpperCase().trim().equalsIgnoreCase("GPC")) {
			msgMT.setMsgType(1);
		}
		else if(Operator.toUpperCase().trim().equalsIgnoreCase("VMS")){
			msgMT.setMsgType(0);
		}
		else{
			msgMT.setMsgType(MsgType);
		}
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
		if (Operator.toUpperCase().trim().equalsIgnoreCase("VIETEL")) {
			if (CommandCode.toUpperCase().trim().equalsIgnoreCase("SHOP123MT") || CommandCode.toUpperCase().trim().equalsIgnoreCase("123VN_SPAM")) {
				emsSendQueue.setServiceID("123VN");
			}
			else if (CommandCode.toUpperCase().trim().equalsIgnoreCase("PAY123MT")) {
				emsSendQueue.setServiceID("123PAY");
			}
			/*else if (CommandCode.toUpperCase().trim().equalsIgnoreCase("PASSPORTMT")) {
				emsSendQueue.setServiceID("70037");
			}
			else if (CommandCode.toUpperCase().trim().equalsIgnoreCase("GSOMT")) {
				emsSendQueue.setServiceID("70037");
			}*/
			else {
				emsSendQueue.setServiceID("VNG");
			}
			emsSendQueue.setOperator("VIETEL1");
		} else {
			emsSendQueue.setServiceID(ServiceID);
			emsSendQueue.setOperator(Operator);
		}
		emsSendQueue.setCommandCode(CommandCode);
		emsSendQueue.setMessage(Message);
		if (Operator.toUpperCase().trim().equalsIgnoreCase("GPC")) {
			emsSendQueue.setMsgType(1);
		}
		else if(Operator.toUpperCase().trim().equalsIgnoreCase("VMS")){
			emsSendQueue.setMsgType(0);
		}
		else{
			emsSendQueue.setMsgType(MsgType);
		}
		emsSendQueue.setContentType(ContentType);
		emsSendQueue.setSumMT(SumMT);

		return emsSendQueue;
	}
}
