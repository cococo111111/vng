package com.services.soap.mo;

import java.net.URL;

import org.apache.axis.client.Stub;
import org.apache.axis.encoding.Base64;


import com.services.soap.apiclient.award.ReceiveMOZingAward;
import com.services.soap.apiclient.award.ReceiveMOZingAwardLocator;
import com.services.soap.apiclient.award.ReceiveMOZingAwardPortType;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.MsgObject;

public class MOSenderAward extends MOSender{

	protected String sentMoToReceiverMO(URL url, MsgObject msgObject, String partnerUsername, String partnerPassword) throws Exception {
		int responsCode = 0;
		ReceiveMOZingAward serviceLocator = new ReceiveMOZingAwardLocator();
		ReceiveMOZingAwardPortType receiverMO = serviceLocator.getReceiveMOZingAwardPort(url);

		((Stub)receiverMO).setTimeout(60000); // 60 * 1000
		
			
		String message = Base64.encode(msgObject.getUsertext().getBytes());
		Util.logger.info("Encode Message OK!");

		responsCode = receiverMO.receiveMO( String.valueOf(msgObject.getRequestid()), msgObject.getUserid(), msgObject.getServiceid(),
				msgObject.getKeyword(), message, msgObject.getMobileoperator(),
				partnerUsername, partnerPassword, SOAPConstants.convertTimestampToCalendar(msgObject.getTTimes()));

		Util.logger.info("Calling " + this.getClass().getName() + ".sentMoToReceiverMO() OK!!!" );
		return String.valueOf(responsCode);
	}

}
