package com.services.soap.mo;

import java.net.URL;

import org.apache.axis.client.Stub;
import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.csm.CSMService;
import com.services.soap.apiclient.csm.CSMServiceLocator;
import com.services.soap.apiclient.csm.CSMServiceSoap;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.MsgObject;

public class MOSenderCSM extends MOSender{

	protected String sentMoToReceiverMO(URL url, MsgObject msgObject, String partnerUsername, String partnerPassword) throws Exception{
		int responsCode = 0;
		CSMService  MOReceiver = new CSMServiceLocator();
		CSMServiceSoap receiverMO = MOReceiver.getCSMServiceSoap(url);
		
		((Stub)receiverMO).setTimeout(125000); // 125 * 1000
		
		String message = Base64.encode(msgObject.getUsertext().getBytes());
		Util.logger.info("Encode Message OK!");

		responsCode = receiverMO.rejectSMSAdv(String.valueOf(msgObject.getRequestid()), msgObject.getUserid(), msgObject.getServiceid(),
						msgObject.getKeyword(), msgObject.getUsertext(), msgObject.getMobileoperator(), 
						partnerUsername, partnerPassword, SOAPConstants.convertTimestampToCalendar(msgObject.getTTimes()));

		Util.logger.info("Calling " + this.getClass().getName() + ".sentMoToReceiverMO() OK!!!" );
		return String.valueOf(responsCode);
	}	

}
