package com.services.soap.mo;

import java.net.URL;

import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;
import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.vmg.Receiver;
import com.services.soap.apiclient.vmg.ReceiverService;
import com.services.soap.apiclient.vmg.ReceiverServiceLocator;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.MsgObject;

public class MOSenderVMG extends BaseMOSender{
	
	protected String sentMoToReceiverMO(URL url, MsgObject msgObject, String idVNG, String partnerUsername, String partnerPassword) throws Exception {
		int responsCode = 0;
		ReceiverService serviceLocator = new ReceiverServiceLocator();
		Receiver receiverMO = serviceLocator.getReceiver(url);

		((Stub)receiverMO).setTimeout(90000); // 90 * 1000
		
		((Stub)receiverMO)._setProperty(Call.USERNAME_PROPERTY, partnerUsername);
		((Stub)receiverMO)._setProperty(Call.PASSWORD_PROPERTY, partnerPassword);
		
		String message = Base64.encode(msgObject.getUsertext().getBytes());
		Util.logger.info("Encode Message OK!");

		responsCode = receiverMO.moReceiverVNG(msgObject.getUserid(), msgObject.getServiceid(), msgObject.getKeyword(), message, 
												String.valueOf(msgObject.getRequestid()), msgObject.getMobileoperator(), idVNG);
		
		Util.logger.info("Calling " + this.getClass().getName() + ".sentMoToReceiverMO() OK!!!" );
		return String.valueOf(responsCode);
	}
}
