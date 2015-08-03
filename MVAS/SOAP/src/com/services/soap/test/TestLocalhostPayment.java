package com.services.soap.test;

import java.net.*;
import java.sql.Timestamp;
import java.io.*;
import java.security.*;
import javax.net.ssl.*;

import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.sdspayment.MOReceiver;
import com.services.soap.apiclient.sdspayment.MOReceiverLocator;
import com.services.soap.apiclient.sdspayment.MOReceiverSoap;
import com.services.soap.mo.SOAPConstants;

public class TestLocalhostPayment {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String text = "888_12312";
		if(text.startsWith("888_"))
		{
			text = text.replace("_", " ");
			System.out.println(text);
		}
		/*
		int port = 8080; // default https port
		String host = "sds";

		try{
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

			SSLSocket socket = (SSLSocket) factory.createSocket(host, port);

			Writer out = new OutputStreamWriter(socket.getOutputStream());
			// https requires the full URL in the GET line
			out.write("GET / HTTP/1.0\\r\\\n");
			out.write("\\r\\n");
			out.flush();

			// read response
			BufferedReader in = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
			int c;
			while ((c = in.read()) != -1) {
				System.out.write(c);
			}

			out.close();
			in.close();
			socket.close();
		}catch (IOException e) {
			System.err.println(e);
		}
		/*try {
			
			int responsCode = 0;
			MOReceiver MOReceiver = new MOReceiverLocator();
			
			MOReceiverSoap receiverMO = MOReceiver.getMOReceiverSoap(new URL("https://sds:8080/EntranceService"));
			
			String message = Base64.encode("xutest dfgdf".getBytes());
			responsCode = receiverMO.receiveMO("40053", "84909541169", "6069",
												"xutest", message, "mvas", "mvas123", SOAPConstants.convertTimestampToCalendar(new Timestamp(System.currentTimeMillis())), "VMS");
			System.out.println("Result:" + responsCode); 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		*/
	}

}
