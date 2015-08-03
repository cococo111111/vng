package com.services.soap.mo;

import java.net.URL;

import org.apache.axis.client.Stub;
import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.mp3.ReceiverMOZingMP3Port;
import com.services.soap.apiclient.mp3.ReceiverMOZingMP3Service;
import com.services.soap.apiclient.mp3.ReceiverMOZingMP3ServiceLocator;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.MsgObject;

public class MOSenderMP3 extends MOSender{

	protected String sentMoToReceiverMO(URL url, MsgObject msgObject, String partnerUsername, String partnerPassword) throws Exception{
		int responsCode = 0;
		ReceiverMOZingMP3Service MOReceiver = new ReceiverMOZingMP3ServiceLocator();
		ReceiverMOZingMP3Port receiverMO = MOReceiver.getReceiverMOZingMP3Port(url);
		
		((Stub)receiverMO).setTimeout(125000); // 125 * 1000
		
		String message = Base64.encode(msgObject.getUsertext().getBytes());
		Util.logger.info("Encode Message OK!");

		responsCode = receiverMO.receiveMO(msgObject.getRequestid().toString(), msgObject.getUserid(), 
				msgObject.getServiceid(), msgObject.getKeyword(), message, msgObject.getMobileoperator(), 
				partnerUsername, partnerPassword, SOAPConstants.convertTimestampToString(msgObject.getTTimes(), 
				SOAPConstants.DATE_TIME));
		
		Util.logger.info("Calling " + this.getClass().getName() + ".sentMoToReceiverMO() OK!!!" );
		return String.valueOf(responsCode);
	}	

}
