package com.services.soap.mo;

import java.net.URL;
import java.sql.Timestamp;

import org.apache.axis.client.Stub;
import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.zingmodel.RecieverMOZingModel;
import com.services.soap.apiclient.zingmodel.RecieverMOZingModelLocator;
import com.services.soap.apiclient.zingmodel.RecieverMOZingModelSoap;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.MsgObject;

public class MOSenderFIDOL extends MOSender{

	protected String sentMoToReceiverMO(URL url, MsgObject msgObject, String partnerUsername, String partnerPassword) throws Exception{
		int responsCode = 0;
		RecieverMOZingModel MOReceiver = new RecieverMOZingModelLocator();
		RecieverMOZingModelSoap receiverMO = MOReceiver.getRecieverMOZingModelSoap(url);
		
		((Stub)receiverMO).setTimeout(125000); // 125 * 1000
		
		String message = Base64.encode(msgObject.getUsertext().getBytes());
		Util.logger.info("Encode Message OK!");

		responsCode = receiverMO.receiveMO(msgObject.getRequestid().toString(), msgObject.getUserid(), 
				msgObject.getServiceid(), msgObject.getKeyword(), message, msgObject.getMobileoperator(), 
				partnerUsername, partnerPassword, SOAPConstants.convertTimestampToCalendar(new Timestamp(System.currentTimeMillis())));
		
		Util.logger.info("Calling " + this.getClass().getName() + ".sentMoToReceiverMO() OK!!!" );
		return String.valueOf(responsCode);
	}	

}
