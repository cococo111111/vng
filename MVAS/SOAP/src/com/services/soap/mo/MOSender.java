package com.services.soap.mo;

import java.net.URL;

import com.vmg.sms.process.MsgObject;

public abstract class MOSender {
	public String sendMO(String url, MsgObject msgObject, String partnerUsername, String partnerPassword) throws Exception {
		String responseCode = sentMoToReceiverMO(new URL(url), msgObject, partnerUsername, partnerPassword);
		return responseCode;
	}
	
	protected abstract String sentMoToReceiverMO(URL url, MsgObject msgObject, String partnerUsername, String partnerPassword) throws Exception;
	
}
