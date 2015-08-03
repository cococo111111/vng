package com.services.soap.mo;

import java.math.BigInteger;
import java.net.URL;
import java.sql.Timestamp;

import org.apache.axis.client.Stub;
import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.barcode.CODLogistic;
import com.services.soap.apiclient.barcode.CODLogisticLocator;
import com.services.soap.apiclient.barcode.CODLogisticPortType;
import com.vmg.sms.common.Util;
import com.vmg.sms.process.MsgObject;

public class MOSenderZDLOG extends MOSender{

	protected String sentMoToReceiverMO(URL url, MsgObject msgObject, String partnerUsername, String partnerPassword) throws Exception {
		int responsCode = 0;
		CODLogistic serviceLocator = new CODLogisticLocator();
		CODLogisticPortType receiverMO = serviceLocator.getCODLogisticPort(url);

		((Stub)receiverMO).setTimeout(30000); // 30 * 1000
					
		String message = Base64.encode(msgObject.getUsertext().getBytes());
		Util.logger.info("Encode Message OK!");

		BigInteger tmpData =  receiverMO.receiveMO( String.valueOf(msgObject.getRequestid()), msgObject.getUserid(), msgObject.getServiceid(),
				msgObject.getKeyword(), message, msgObject.getMobileoperator(),
				partnerUsername, partnerPassword, SOAPConstants.convertTimestampToString(new Timestamp(System.currentTimeMillis()), SOAPConstants.DATE_TIME));
		if (tmpData != null ){
			responsCode = tmpData.intValue();
		}else{
			responsCode = -9999;			
		}
		
		Util.logger.info("Calling " + this.getClass().getName() + ".sentMoToReceiverMO() OK!!!" );
		return String.valueOf(responsCode);
	}

}
