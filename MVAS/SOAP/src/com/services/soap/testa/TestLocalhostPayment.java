package com.services.soap.testa;

import java.net.URL;
import java.sql.Timestamp;

import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.smspayment.MOReceiver;
import com.services.soap.apiclient.smspayment.MOReceiverLocator;
import com.services.soap.apiclient.smspayment.MOReceiverSoap;
import com.services.soap.mo.SOAPConstants;

public class TestLocalhostPayment {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			int responsCode = 0;
			MOReceiver MOReceiver = new MOReceiverLocator();
			
			MOReceiverSoap receiverMO = MOReceiver.getMOReceiverSoap(new URL("http://10.30.8.20/SMS/MOReceiver_MVAS/MOReceiver.asmx"));
			
			String message = Base64.encode("vng1 memcache012".getBytes());
			responsCode = receiverMO.receiveMO(40053, "84909541169", "6369",
												"VNG1", message, "mvas", "mvas123", SOAPConstants.convertTimestampToCalendar(new Timestamp(System.currentTimeMillis())), "VMS");
			System.out.println("Result:" + responsCode); 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
