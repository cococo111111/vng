package com.services.soap.testa;

import java.net.URL;
import java.sql.Timestamp;

import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.award2.ZpingPort;
import com.services.soap.apiclient.award2.ZpingService;
import com.services.soap.apiclient.award2.ZpingServiceLocator;
import com.services.soap.apiclient.mp3.ReceiverMOZingMP3Port;
import com.services.soap.apiclient.mp3.ReceiverMOZingMP3Service;
import com.services.soap.apiclient.mp3.ReceiverMOZingMP3ServiceLocator;
import com.services.soap.mo.SOAPConstants;

public class TestSoapAward2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			int responseCode = 0;
			/*
			ReceiverMO ReceiverMO = new ReceiverMOLocator();
			ReceiverMOPortType receiverMO2 = ReceiverMO.getReceiverMOPort(new URL("http://127.0.0.1/conbaofan/ReceiverMO.php"));
			String message = Base64.encode("FAN 1".getBytes());
			responseCode = receiverMO2.moReceive("82220000014", "84903254333", "6169", "FAN", message , "VMS", 
					"conbaofan", "fan$fan#fan", 
					SOAPConstants.convertTimestampToCalendar(new Timestamp(System.currentTimeMillis()))
					);
			*/
			/*
			SmssoapService ReceiverMO = new SmssoapServiceLocator();
			SmssoapPort receiverMO = ReceiverMO.getSmssoapPort(new URL("http://dev20.123mua.com.vn/service/sms"));
			String message = Base64.encode("123mua duynd2009".getBytes());
			
			//java.lang.String partnerUsername, java.lang.String partnerPassword, java.lang.String requestTime			
			responseCode = receiverMO.moReceive("82230000001", "84909541169", "6769", "123mua", message , "VMS", 
					"123mua", "HAeCwcaAhdm", 
					SOAPConstants.convertTimestampToString(new Timestamp(System.currentTimeMillis()), SOAPConstants.DATE_TIME)
					);
			*/
			
			
			ZpingService ReceiverMO = new ZpingServiceLocator();
			ZpingPort receiverMO2 = ReceiverMO.getzpingPort(new URL("http://local.awards.zing.vn/webservice/service/zping.php"));
			String message = Base64.encode("ZMA 6295055 3 3".getBytes());
			responseCode = receiverMO2.receiveMO("98455557901", "84909541172", "6069", "ZMA", message, "VMS", "voting", "voting123", 
					SOAPConstants.convertTimestampToString(new Timestamp(System.currentTimeMillis()), SOAPConstants.DATE_TIME)
					);
		
			
			System.out.println("responseCode:[" + responseCode + "]");
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

}
