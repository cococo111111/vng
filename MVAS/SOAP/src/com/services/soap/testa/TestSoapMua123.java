package com.services.soap.testa;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.isunlock.SmssoapService;
import com.services.soap.apiclient.isunlock.SmssoapServiceLocator;
import com.services.soap.apiclient.isunlock.SmssoapPort;
import com.services.soap.mo.SOAPConstants;

public class TestSoapMua123 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			int responseCode;
			/*
			 * ReceiverMO ReceiverMO = new ReceiverMOLocator();
			 * ReceiverMOPortType receiverMO2 = ReceiverMO.getReceiverMOPort(new
			 * URL("http://127.0.0.1/conbaofan/ReceiverMO.php")); String message
			 * = Base64.encode("FAN 1".getBytes()); responseCode =
			 * receiverMO2.moReceive("82220000014", "84903254333", "6169",
			 * "FAN", message , "VMS", "conbaofan", "fan$fan#fan",
			 * SOAPConstants.convertTimestampToCalendar(new
			 * Timestamp(System.currentTimeMillis())) );
			 */

			// test FIDOL
			/*
			 * SmssoapService serviceLocator = new SmssoapServiceLocator();
			 * SmssoapPort receiverMO = serviceLocator.getSmssoapPort(newURL(
			 * "http://test.f-idol.vn/webservices/soapwebservices/execute/sms_logs"
			 * )); String message = Base64.encode("FIDOL 123".getBytes());
			 * responseCode = receiverMO.moReceive("15", "841292135137", "6169",
			 * "FIDOL", message , "GPC", "fidol_sms_service",
			 * "s6M^$t64V%",SOAPConstants.convertTimestampToString(new
			 * Timestamp(System.currentTimeMillis()), SOAPConstants.DATE_TIME));
			 */
			URL url = new java.net.URL(
					"http://10.30.97.61:8080/SMSWS/ReceiverMO");
			SmssoapService serviceLocator = new SmssoapServiceLocator();
			SmssoapPort receiverMO = serviceLocator.getSmssoapPort(url);

			String message = Base64.encode("unlock tunl2"
					.getBytes());

			/*responseCode = receiverMO.moReceive("500012", "841292135137",
					"6069", "10", message, "GPC", "Voting", "Voting123",
					SOAPConstants.convertTimestampToString(new Timestamp(System
							.currentTimeMillis()), SOAPConstants.DATE_TIME));*/
			responseCode = receiverMO.moReceive("4056789", "84918255063", "6069",
					"unlock", message, "GPC", "MVAS", "Vng@1234",
					SOAPConstants.convertTimestampToString(new Timestamp(System
							.currentTimeMillis()), SOAPConstants.DATE_TIME));

			// test XU
			/*
			 * URL url = new java.net.URL("http://10.30.17.26:80/MOReceiver");
			 * MOReceiver MOReceiver = new MOReceiverLocator(); MOReceiverSoap
			 * receiverMO = MOReceiver.getMOReceiverSoap(url);
			 * 
			 * String message =
			 * Base64.encode("ZXTEST pokerus vunguyen886 ".getBytes());
			 * 
			 * responseCode = receiverMO.receiveMO(500009, "841292135137",
			 * "6069", "ZXTEST", message , "mvasdirect",
			 * "yL7cIMw54hGOBzAbYohwmw"
			 * ,SOAPConstants.convertTimestampToCalendar(new
			 * Timestamp(System.currentTimeMillis())),"GPC");
			 */

			// Test MP3
			/*
			 * ReceiverMOZingMP3Service ReceiverMO = new
			 * ReceiverMOZingMP3ServiceLocator(); ReceiverMOZingMP3Port
			 * receiverMO2 = ReceiverMO.getReceiverMOZingMP3Port(new
			 * URL("http://mp4.zing.vn/ajax/zrtd-api/index")); String message =
			 * Base64.encode("MP3 6295055".getBytes()); responseCode =
			 * receiverMO2.receiveMO("98455557", "84909541172", "6369", "MP3",
			 * message, "VMS", "", "",
			 * SOAPConstants.convertTimestampToString(new
			 * Timestamp(System.currentTimeMillis()), SOAPConstants.DATE_TIME)
			 * );
			 */
			System.out.println("responseCode:[" + responseCode + "]");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
