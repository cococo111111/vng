package com.services.soap.mo;

import java.net.URL;
import java.sql.Timestamp;

import org.apache.axis.client.Stub;
import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.passport.PassportSMS;
import com.services.soap.apiclient.passport.PassportSMSLocator;
import com.services.soap.apiclient.passport.PassportSMSSoap;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.MsgObject;

public class MOSenderPPASS extends MOSender{

	protected String sentMoToReceiverMO(URL url, MsgObject msgObject, String partnerUsername, String partnerPassword) throws Exception{
		int responseCode = 0;
		PassportSMS MOReceiver = new PassportSMSLocator();
		PassportSMSSoap receiverMO = MOReceiver.getPassportSMSSoap(url);
		
		((Stub)receiverMO).setTimeout(125000); // 125 * 1000
		
		String message = Base64.encode(msgObject.getUsertext().getBytes());
		Util.logger.info("Encode Message OK!");
		
		responseCode = receiverMO.receiverMO(msgObject.getRequestid().toString(), msgObject.getUserid(), msgObject.getServiceid(), 
				msgObject.getKeyword(), message, msgObject.getMobileoperator(), partnerUsername, partnerPassword, 
				SOAPConstants.convertTimestampToCalendar(new Timestamp(System.currentTimeMillis()))
				);
		
		Util.logger.info("Calling " + this.getClass().getName() + ".sentMoToReceiverMO() OK!!!" );
		return String.valueOf(responseCode);
	}	

}
