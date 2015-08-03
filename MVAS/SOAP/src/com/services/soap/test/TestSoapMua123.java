package com.services.soap.test;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.axis.encoding.Base64;

import sdsjavaclient4mvas.SdsJavaClient4Mvas;


public class TestSoapMua123 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			
			String telco = "GPC";
            String userID   = "841292135137";
            String serviceID= "6569";
            String commandCode = "XUTEST";
            String message  = "XUTEST vunguyen998";
            Date dateNow = new Date (); 
            SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("yyMMddHHmmssSSS");
            StringBuilder nowYYYYMMDD = new StringBuilder( dateformatYYYYMMDD.format( dateNow ) );
            String moid= nowYYYYMMDD.toString();
            if("VIETEL".equals(telco)){
                moid = "31" + moid;
            }
            else if("VMS".equals(telco)){
                 moid = "21" + moid;
            }
            else {
                 moid = "11" + moid;
            }
            System.out.println(moid);
           // moid = "11130515114419959";
            java.util.Calendar timerequest = java.util.Calendar.getInstance();
            SdsJavaClient4Mvas sdspayment = new SdsJavaClient4Mvas();
            int result = sdspayment.receiveMO(moid,userID,serviceID,commandCode,message,telco,timerequest);
            System.out.println(result);
			
			//int responseCode = 0;
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
			/*CSToolReceiverMO MOReceiver = new CSToolReceiverMOLocator();
			CSToolReceiverMOSoap receiverMO = MOReceiver.getCSToolReceiverMOSoap(new URL("http://10.30.18.214:8000/ReceiverSMSMO/ReceiverSMSMO.svc"));*/
			/*responseCode = receiverMO.receiveMO("98455557", "84909541172", "6069", "RPR", message, "VMS", "", "", 
			SOAPConstants.convertTimestampToString(new Timestamp(System.currentTimeMillis()), SOAPConstants.DATE_TIME)
			);*/
			/*SmssoapService  serviceLocator = new SmssoapServiceLocator();
			SmssoapPort   receiverMO = serviceLocator.getSmssoapPort(new URL("http://smscoca.brand.zing.vn/webservices/soapwebservices/execute/frontend"));
				
			
			String message = Base64.encode("888 56789".getBytes());
			
			responseCode = receiverMO.moReceive( "4546", "841292135137", "6169",
					"888", message, "GPC",
					"coca_cola_sms", "g3sms#2013@coca", 
					SOAPConstants.convertTimestampToString(new Timestamp(System.currentTimeMillis()), SOAPConstants.DATE_TIME));
		
			
			System.out.println("responseCode:[" + responseCode + "]");*/
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

}
