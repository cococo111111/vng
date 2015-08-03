package com.services.soap.mo;

import java.net.URL;

import com.vmg.sms.process.MsgObject;

public abstract class BaseMOSender {
	public String sendMO(String url, MsgObject msgObject, String id_vng, String partnerUsername, String partnerPassword) throws Exception {
		String responseCode = "";
		responseCode = sentMoToReceiverMO(new URL(url), msgObject, id_vng, partnerUsername, partnerPassword);
		return responseCode;
	}	
	
	protected abstract String sentMoToReceiverMO(URL url, MsgObject msgObject, String idVNG, String partnerUsername, String partnerPassword) throws Exception;
	
}
