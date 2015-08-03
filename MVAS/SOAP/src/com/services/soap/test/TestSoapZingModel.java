package com.services.soap.test;

import java.net.URL;
import java.sql.Timestamp;

import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.zingmodel.*;
import com.services.soap.mo.SOAPConstants;

public class TestSoapZingModel {

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
			
			
			RecieverMOZingModel ReceiverMO = new RecieverMOZingModelLocator();
			RecieverMOZingModelSoap receiverMO2 = ReceiverMO.getRecieverMOZingModelSoap(new URL("http://test.f-idol.vn/RecieverMOZingModel.asmx"));
			String message = Base64.encode("FIDOL 3465".getBytes());
			responseCode = receiverMO2.receiveMO("98455548", "84909541171", "6069", "FIDOL", message, "VMS", "fidol_sms", "reciver@#!fidol@@", 
					SOAPConstants.convertTimestampToCalendar(new Timestamp(System.currentTimeMillis()))
					);
		
			
			System.out.println("responseCode:[" + responseCode + "]");
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

}
