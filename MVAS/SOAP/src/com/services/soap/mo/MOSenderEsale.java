package com.services.soap.mo;

import java.net.URL;
import java.sql.Timestamp;

import org.apache.axis.client.Stub;
import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.esale.ActivateAgencyResult;
import com.services.soap.apiclient.esale.CardShopAPIService;
import com.services.soap.apiclient.esale.CardShopAPIServiceLocator;
import com.services.soap.apiclient.esale.CardShopAPIServiceSoap;
import com.services.soap.apiclient.passport.PassportSMS;
import com.services.soap.apiclient.passport.PassportSMSLocator;
import com.services.soap.apiclient.passport.PassportSMSSoap;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.MsgObject;

public class MOSenderEsale extends MOSender{

	protected String sentMoToReceiverMO(URL url, MsgObject msgObject, String partnerUsername, String partnerPassword) throws Exception{
		int responsCode = 0;
		
		CardShopAPIService cardShopAPIService = new CardShopAPIServiceLocator();			
		CardShopAPIServiceSoap cardShopAPISoap = cardShopAPIService.getCardShopAPIServiceSoap(url);
		((Stub)cardShopAPISoap).setTimeout(60000); // 60 * 1000
		
        String 	sig = com.services.soap.mo.Hasher.getInstance("MD5").hash(msgObject.getRequestid().toString() + msgObject.getUserid() + 
        									msgObject.getServiceid() + msgObject.getKeyword() + partnerPassword);
        
		String message = Base64.encode(msgObject.getUsertext().getBytes());
		Util.logger.info("Encode Message OK!");
        responsCode = cardShopAPISoap.receiverMOEsale(msgObject.getRequestid().toString(), msgObject.getUserid(), msgObject.getServiceid(), msgObject.getKeyword(), message, msgObject.getMobileoperator(), 
        		SOAPConstants.convertTimestampToCalendar(new Timestamp(System.currentTimeMillis())), sig);
				
		Util.logger.info("Calling " + this.getClass().getName() + ".sentMoToReceiverMO() OK!!!" );
		return String.valueOf(responsCode);
	}	

}
