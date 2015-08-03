package com.services.soap.mo;

import java.net.URL;
import java.sql.Timestamp;

import org.apache.axis.client.Stub;
import org.apache.axis.encoding.Base64;


import com.services.soap.apiclient.award.ReceiveMOZingAward;
import com.services.soap.apiclient.award.ReceiveMOZingAwardLocator;
import com.services.soap.apiclient.award.ReceiveMOZingAwardPortType;
import com.services.soap.apiclient.award2.ZpingPort;
import com.services.soap.apiclient.award2.ZpingService;
import com.services.soap.apiclient.award2.ZpingServiceLocator;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.MsgObject;

public class MOSenderAward2 extends MOSender{

	protected String sentMoToReceiverMO(URL url, MsgObject msgObject, String partnerUsername, String partnerPassword) throws Exception {
		int responsCode = 0;
		ZpingService serviceLocator = new ZpingServiceLocator();
		ZpingPort receiverMO = serviceLocator.getzpingPort(url);

		((Stub)receiverMO).setTimeout(30000); // 30 * 1000
		
			
		String message = Base64.encode(msgObject.getUsertext().getBytes());
		Util.logger.info("Encode Message OK!");

		responsCode = receiverMO.receiveMO( String.valueOf(msgObject.getRequestid()), msgObject.getUserid(), msgObject.getServiceid(),
				msgObject.getKeyword(), message, msgObject.getMobileoperator(),
				partnerUsername, partnerPassword, SOAPConstants.convertTimestampToString(new Timestamp(System.currentTimeMillis()), SOAPConstants.DATE_TIME));

		Util.logger.info("Calling " + this.getClass().getName() + ".sentMoToReceiverMO() OK!!!" );
		return String.valueOf(responsCode);
	}

}
