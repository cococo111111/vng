package com.services.soap.mo;

import java.net.URL;

import org.apache.axis.client.Stub;
import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.smspaymentnew.MOReceiver;
import com.services.soap.apiclient.smspaymentnew.MOReceiverLocator;
import com.services.soap.apiclient.smspaymentnew.MOReceiverSoap;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.MsgObject;

public class MOSenderSmsPaymentNew extends MOSender{

	protected String sentMoToReceiverMO(URL url, MsgObject msgObject, String partnerUsername, String partnerPassword) throws Exception{
		int responsCode = 0;
		MOReceiver MOReceiver = new MOReceiverLocator();
		MOReceiverSoap receiverMO = MOReceiver.getMOReceiverSoap(url);
		
		((Stub)receiverMO).setTimeout(125000); // 125 * 1000
		
		String message = Base64.encode(msgObject.getUsertext().getBytes());
		Util.logger.info("Encode Message OK!");

		responsCode = receiverMO.receiveMO(msgObject.getMo_id(), msgObject.getUserid(), msgObject.getServiceid(),
				msgObject.getKeyword(), message, 
				partnerUsername, partnerPassword, SOAPConstants.convertTimestampToCalendar(msgObject.getTTimes()), msgObject.getMobileoperator()
		);

		Util.logger.info("Calling " + this.getClass().getName() + ".sentMoToReceiverMO() OK!!!" );
		return String.valueOf(responsCode);
	}	

}
