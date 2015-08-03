package com.services.soap.test;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.g3gateway.SmssoapService;
import com.services.soap.apiclient.g3gateway.SmssoapServiceLocator;
import com.services.soap.apiclient.g3gateway.SmssoapPort;
import com.services.soap.mo.SOAPConstants;

public class TestSoapG3Gateway {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			int responseCode = 0;
			
			URL url = new java.net.URL(
					"http://sms.ad.zing.vn/webservices/soapwebservices/execute/sms_logs");
			SmssoapService serviceLocator = new SmssoapServiceLocator();
			SmssoapPort receiverMO = serviceLocator.getSmssoapPort(url);

			String message = Base64.encode("ostar 12313"
					.getBytes());
			
			responseCode = receiverMO.moReceive("40", "841292135137", "6069",
					"ostar", message, "GPC", "vng_g3_sms_gateway", "20VNG^*d@p13",
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