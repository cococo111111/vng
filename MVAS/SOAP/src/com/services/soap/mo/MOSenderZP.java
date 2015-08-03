package com.services.soap.mo;

import java.net.URL;

import org.apache.axis.client.Stub;
import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.zingphoto.ReceiverMO;
import com.services.soap.apiclient.zingphoto.ReceiverMOLocator;
import com.services.soap.apiclient.zingphoto.ReceiverMOPortType;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.MsgObject;

public class MOSenderZP extends MOSender{
	//private static final String FULLFORMAT_DATE_TIME = "dd/MM/yyyy kk:mm:ss.SSS";

	protected String sentMoToReceiverMO(URL url, MsgObject msgObject, String partnerUsername, String partnerPassword) throws Exception{
		int responsCode = 0;
		ReceiverMO serviceLocator = new ReceiverMOLocator();
		ReceiverMOPortType receiverMO = serviceLocator.getReceiverMOPort(url);

		((Stub)receiverMO).setTimeout(60000); // 60 * 1000
		
		String message = Base64.encode(msgObject.getUsertext().getBytes());
		Util.logger.info("Encode Message OK!");

		responsCode = receiverMO.moReceive(msgObject.getMo_id(), msgObject.getUserid(), msgObject.getServiceid(),
				msgObject.getKeyword(), message, msgObject.getMobileoperator(),
				partnerUsername, partnerPassword, SOAPConstants.convertTimestampToCalendar(msgObject.getTTimes()), msgObject.getRequestid().toString()); 

		Util.logger.info("Calling " + this.getClass().getName() + ".sentMoToReceiverMO() OK!!!" );
		return String.valueOf(responsCode);
	}	

}
