package com.services.soap.testa;

import java.net.URL;

import org.apache.axis.client.Stub;
import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.zd.WsServiceSMSPort;
import com.services.soap.apiclient.zd.WsServiceSMSService;
import com.services.soap.apiclient.zd.WsServiceSMSServiceLocator;
import com.vmg.sms.common.Util;


public class TestLocalhostVMG {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			
			int responsCode = 0;
			WsServiceSMSService MOReceiver = new WsServiceSMSServiceLocator();
			WsServiceSMSPort receiverMO = MOReceiver.getWsServiceSMSPort(new URL ("http://deal.zing.vn/ws_soapservices/wsdl"));
			
			((Stub)receiverMO).setTimeout(125000); // 125 * 1000
			
			String message = Base64.encode("zd cod ihung".getBytes());
			//Util.logger.info("Encode Message OK!");

			responsCode = receiverMO.addSMSFromUser("1234567895", "84909541169", "6069", "ZD", message, "VMS", "FW", "zingdeal", "22/03/2011 14:30:00");			
	
			System.out.print("responsCode:" + responsCode);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
