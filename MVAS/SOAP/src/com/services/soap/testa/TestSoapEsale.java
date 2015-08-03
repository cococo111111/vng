package com.services.soap.testa;

import java.net.URL;
import java.sql.Timestamp;

import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.esale.CardShopAPIService;
import com.services.soap.apiclient.esale.CardShopAPIServiceLocator;
import com.services.soap.apiclient.esale.CardShopAPIServiceSoap;
import com.services.soap.mo.SOAPConstants;

public class TestSoapEsale {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CardShopAPIService cardShopAPIService = new CardShopAPIServiceLocator();			
			CardShopAPIServiceSoap cardShopAPISoap = cardShopAPIService.getCardShopAPIServiceSoap(new URL("http://10.30.17.193/eSaleAPIv2/eSaleServices.asmx"));
			String message = Base64.encode("esale fdfd".getBytes());            
            
            //String 	sig  = com.services.soap.mo.Hasher.getInstance("MD5").hash("Duynd2009" + "#eSale@MVAS!");         
            String 	sig = com.services.soap.mo.Hasher.getInstance("MD5").hash("555887" + "84909541169" + "6069" + "esale" + "esale fdfd" + "mvas@#123");
            int activateAgencyResult = cardShopAPISoap.receiverMOEsale("555887", "84909541169", "6069", "esale", message, "VMS", 
            		SOAPConstants.convertTimestampToCalendar(new Timestamp(System.currentTimeMillis())), sig);
					
			
			System.out.println("responseCode:[" + activateAgencyResult+ "]");

		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	
	public static boolean authenticate(String currentSig, String AcountName, String secretKey) throws Exception{
		String 	sig  = com.services.soap.mo.Hasher.getInstance("MD5").hash(AcountName + secretKey);
		return sig.toLowerCase().equals(currentSig.toLowerCase());
	}

}
