package com.services.soap.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Timestamp;

import javax.xml.rpc.ServiceException;

import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.passport.PassportSMS;
import com.services.soap.apiclient.passport.PassportSMSLocator;
import com.services.soap.apiclient.passport.PassportSMSSoap;
import com.services.soap.mo.SOAPConstants;

public class TestSoapPassportSMS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PassportSMS passportService = new PassportSMSLocator();			
			PassportSMSSoap passportServiceSoap = passportService.getPassportSMSSoap(new URL("http://10.30.17.193/PassportSMSGateway/PassportSMSGateway.asmx"));
			String message = Base64.encode("XN duynd2009 pass".getBytes());
			
            
            String wsAccount = "quannh";
            String wsPassword = "quannh";
            
			int responseCode = passportServiceSoap.receiverMO("1387844410", "84909541169", "6069", "XN", message, "VMS", wsAccount, wsPassword, 
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
