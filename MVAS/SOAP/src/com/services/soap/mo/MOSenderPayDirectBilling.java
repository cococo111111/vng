package com.services.soap.mo;

import java.net.URL;
import java.sql.Timestamp;

import org.apache.axis.client.Stub;
import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.directpay.ReceiverMOImpl;
import com.services.soap.apiclient.directpay.ReceiverMOImplServiceLocator;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.MsgObject;

public class MOSenderPayDirectBilling extends MOSender{

	protected String sentMoToReceiverMO(URL url, MsgObject msgObject, String partnerUsername, String partnerPassword) throws Exception{
		int responsCode = 0;
		
		ReceiverMOImplServiceLocator directPayAPIService = new ReceiverMOImplServiceLocator();			
		ReceiverMOImpl directPayAPISoap = directPayAPIService.getReceiverMOImplPort(url);
		((Stub)directPayAPISoap).setTimeout(60000); // 60 * 1000
		
        String 	sig = com.services.soap.mo.Hasher.getInstance("MD5").hash(msgObject.getRequestid().toString() + msgObject.getUserid() + 
        									msgObject.getServiceid() + msgObject.getKeyword() +msgObject.getMobileoperator() + partnerPassword);
        
		String message = Base64.encode(msgObject.getUsertext().getBytes());
		Util.logger.info("Encode Message OK!");
        responsCode = directPayAPISoap.receiveMO(msgObject.getRequestid().toString(), msgObject.getUserid(), msgObject.getServiceid(), msgObject.getKeyword(), message, msgObject.getMobileoperator(),
        		partnerUsername, partnerPassword, SOAPConstants.convertTimestampToCalendar(new Timestamp(System.currentTimeMillis())),sig);
				
		Util.logger.info("Calling " + this.getClass().getName() + ".sentMoToReceiverMO() OK!!!" );
		return String.valueOf(responsCode);
	}	

}
