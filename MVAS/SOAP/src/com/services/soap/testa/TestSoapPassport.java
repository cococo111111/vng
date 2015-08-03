package com.services.soap.testa;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.services.soap.apiclient.passport.PassportService;
import com.services.soap.apiclient.passport.PassportServiceLocator;
import com.services.soap.apiclient.passport.PassportServiceSoap;

public class TestSoapPassport {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			/*
			ReceiverMO ReceiverMO = new ReceiverMOLocator();
			ReceiverMOPortType receiverMO2 = ReceiverMO.getReceiverMOPort(new URL("http://127.0.0.1/conbaofan/ReceiverMO.php"));
			String message = Base64.encode("FAN 1".getBytes());
			responseCode = receiverMO2.moReceive("82220000014", "84903254333", "6169", "FAN", message , "VMS", 
					"conbaofan", "fan$fan#fan", 
					SOAPConstants.convertTimestampToCalendar(new Timestamp(System.currentTimeMillis()))
					);
			*/
			
			PassportService passportService = new PassportServiceLocator();
			
			PassportServiceSoap passportServiceSoap = passportService.getPassportServiceSoap(new URL("http://10.30.17.193/PassportServices/PassportService.asmx"));
			//PassportServiceSoap passportServiceSoap = passportService.getPassportServiceSoap12(new URL("http://10.30.17.193/Passport5_LocalTest/PassportService.asmx"));
			//((Stub)passportServiceSoap).getHeaders();
			//String message = Base64.encode("123mua duynd2009".getBytes());
			
			//java.lang.String partnerUsername, java.lang.String partnerPassword, java.lang.String requestTime	
//			String[][] body = new String[1][];
//            body[0] = new String[2];
//            body[0][0] = "ACC";
            String accountName = "tinhvo18451";
            
            String wsAccount = "passport";
            String wsPassword = "passport";
            
			
			String[] result = passportServiceSoap.getAccountInfoByName(accountName, 12345679, "10.199.38.101", "IE", wsAccount, wsPassword, 12);
			int responseCode = -999999;
			String identify = "";
			String fullName = "";
			
			if (result != null && result.length > 0){
				responseCode = Integer.valueOf(result[0]);
				if (responseCode == 1){
					fullName= String.valueOf(result[10]);
					identify = String.valueOf(result[5]);
				}
			}
			
			
			System.out.println("responseCode:[" + responseCode + "]");
			System.out.println("fullName:[" + fullName + "]");
			System.out.println("identify:[" + identify + "]");
			/*
			*/
			/*
			Service1 passportService = new Service1Locator();
			
			Service1Soap passportServiceSoap = passportService.getService1Soap(new URL("http://localhost:9314/Service1.asmx"));
			//PassportServiceSoap passportServiceSoap = passportService.getPassportServiceSoap12(new URL("http://10.30.17.193/Passport5_LocalTest/PassportService.asmx"));
			//((Stub)passportServiceSoap).getHeaders();
			//String message = Base64.encode("123mua duynd2009".getBytes());
			
			//java.lang.String partnerUsername, java.lang.String partnerPassword, java.lang.String requestTime	
			String[] body[] = new String[][]{new String[]{"ACC", "tinhvo18451"}, new String[]{"ACC", "tinhvo18451"}};
            //body[0] = ;
            //body[1] = ;
            //String[] bodya = new String[]{"ACC", "tinhvo18451"};
            //body[0][0] = "ACC";
            //body[0][1] = "tinhvo18451";
            
            String wsAccount = "passport";
            String wsPassword = "passport";
            
			
			String result = passportServiceSoap.helloWorld(body);
			
			
			System.out.println("result:[" + result + "]");
			*/
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
