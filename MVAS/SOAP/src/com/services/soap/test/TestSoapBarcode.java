package com.services.soap.test;

import java.net.URL;
import java.sql.Timestamp;

import org.apache.axis.encoding.Base64;

import com.services.soap.apiclient.barcode.CODLogistic;
import com.services.soap.apiclient.barcode.CODLogisticLocator;
import com.services.soap.apiclient.barcode.CODLogisticPortType;
import com.services.soap.mo.SOAPConstants;

public class TestSoapBarcode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			int responseCode = 0;
			
			CODLogistic ReceiverMO = new CODLogisticLocator();
			CODLogisticPortType receiverMO2 = ReceiverMO.getCODLogisticPort(new URL("http://sales.dev.zing.vn/codsms/wsdl/index.php"));
			String message = Base64.encode("barcode 58745".getBytes());
			responseCode = receiverMO2.receiveMO("98455557905", "84909541172", "6069", "ZMA", message, "VMS", "barcode", "barcode@123", 
					SOAPConstants.convertTimestampToString(new Timestamp(System.currentTimeMillis()), SOAPConstants.DATE_TIME)
					).intValue();
		
			
			System.out.println("responseCode:[" + responseCode + "]");
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

}
