package com.services.soap.mo;

import java.net.URL;
import java.sql.Timestamp;

import org.apache.axis.client.Stub;
import org.apache.axis.encoding.Base64;


import com.services.soap.apiclient.shop123.SmssoapPort;
import com.services.soap.apiclient.shop123.SmssoapService;
import com.services.soap.apiclient.shop123.SmssoapServiceLocator;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.MsgObject;

public class MOSender123Shop extends MOSender{

	protected String sentMoToReceiverMO(URL url, MsgObject msgObject, String partnerUsername, String partnerPassword) throws Exception {
		int responsCode = 0;
		SmssoapService  serviceLocator = new SmssoapServiceLocator();
		SmssoapPort   receiverMO = serviceLocator.getSmssoapPort(url);

		((Stub)receiverMO).setTimeout(60000); // 60 * 1000
		
		String message = Base64.encode(msgObject.getUsertext().getBytes());
		Util.logger.info("Encode Message OK!");

		responsCode = receiverMO.moReceive( String.valueOf(msgObject.getRequestid()), msgObject.getUserid(), msgObject.getServiceid(),
				msgObject.getKeyword(), message, msgObject.getMobileoperator(),
				partnerUsername, partnerPassword, 
				SOAPConstants.convertTimestampToString(new Timestamp(System.currentTimeMillis()), SOAPConstants.DATE_TIME));

		Util.logger.info("Calling " + this.getClass().getName() + ".sentMoToReceiverMO() OK!!!" );
		return String.valueOf(responsCode);
	}

}
