package com.services.soap.mo;

import java.net.URL;

import org.apache.axis.client.Stub;
import org.apache.axis.encoding.Base64;


import com.services.soap.apiclient.conbaofan.ReceiverMO;
import com.services.soap.apiclient.conbaofan.ReceiverMOLocator;
import com.services.soap.apiclient.conbaofan.ReceiverMOPortType;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.MsgObject;

public class MOSenderFAN extends MOSender{

	protected String sentMoToReceiverMO(URL url, MsgObject msgObject, String partnerUsername, String partnerPassword) throws Exception {
		int responsCode = 0;
		ReceiverMO serviceLocator = new ReceiverMOLocator();
		ReceiverMOPortType receiverMO = serviceLocator.getReceiverMOPort(url);

		((Stub)receiverMO).setTimeout(60000); // 60 * 1000
		
		String message = Base64.encode(msgObject.getUsertext().getBytes());
		Util.logger.info("Encode Message OK!");

		responsCode = receiverMO.moReceive( String.valueOf(msgObject.getRequestid()), msgObject.getUserid(), msgObject.getServiceid(),
				msgObject.getKeyword(), message, msgObject.getMobileoperator(),
				partnerUsername, partnerPassword, SOAPConstants.convertTimestampToCalendar(msgObject.getTTimes()));

		Util.logger.info("Calling " + this.getClass().getName() + ".sentMoToReceiverMO() OK!!!" );
		return String.valueOf(responsCode);
	}

}
