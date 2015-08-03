package com.services.soap.testa;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Timestamp;

import javax.xml.rpc.ServiceException;

import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.award.ReceiveMOZingAward;
import com.services.soap.apiclient.award.ReceiveMOZingAwardLocator;
import com.services.soap.apiclient.award.ReceiveMOZingAwardPortType;
import com.services.soap.mo.SOAPConstants;

public class TestSoapAward {

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
			
			ReceiveMOZingAward ReceiverMOs = new ReceiveMOZingAwardLocator();
			ReceiveMOZingAwardPortType receiverMO = ReceiverMOs.getReceiveMOZingAwardPort(new URL("http://award.vlink.vn/webservice/service/soap-server.php"));
			String message = Base64.encode("ZMA XXXX YY".getBytes());
			System.out.println(message);
			
			//java.lang.String partnerUsername, java.lang.String partnerPassword, java.lang.String requestTime			
			responseCode = receiverMO.receiveMO("82230000007", "84909541181", "6169",  "ZMA", message, "VMS", "voting", "voting123",  
					SOAPConstants.convertTimestampToCalendar(new Timestamp(System.currentTimeMillis()))
					);
			
			System.out.println("responseCode:[" + responseCode + "]");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
