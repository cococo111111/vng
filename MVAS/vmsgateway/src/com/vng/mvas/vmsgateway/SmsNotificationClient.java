package com.vng.mvas.vmsgateway;

import java.rmi.RemoteException;
import java.util.Calendar;

import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import org.apache.axis.client.Stub;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.axis.types.URI;
import org.apache.axis.types.URI.MalformedURIException;
import org.apache.commons.codec.binary.Hex;
import org.csapi.www.schema.parlayx.sms.v2_2.SmsMessage;
import org.csapi.www.wsdl.parlayx.sms.notification.v2_2._interface.SmsNotification;
import org.csapi.www.wsdl.parlayx.sms.notification.v2_2.service.SmsNotificationServiceLocator;

import com.vng.mvas.utils.Utils;

public class SmsNotificationClient {
	private static String PASS ="1234";

	public static void main(String[] args) {
		try {
			toAscii("!@#!@##%$^&(&*()_+=[2131453554{}||[\';asd sakh>?><,.n~`");
			return;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SmsNotificationServiceLocator locator = new SmsNotificationServiceLocator();
		
//		locator.setSmsNotificationEndpointAddress("http://10.30.29.104:8080/vms-sms-service/services/SmsNotificationService");
		locator.setSmsNotificationEndpointAddress("http://localhost:8081/vms-sms-service/services/SmsNotificationService");
		try {
			SmsNotification service = locator.getSmsNotification();
			SOAPHeaderElement header = new SOAPHeaderElement("http://www.huawei.com.cn/schema/common/v2_1","NotifySOAPHeader");
			SOAPElement spRevId  = header.addChildElement("spRevId");
			spRevId.addTextNode("12345");
			String spID="206069";
			String timestamp ="20140312131415";
			
			SOAPElement spRevpassword  = header.addChildElement("spRevpassword");
			spRevpassword.addTextNode(Utils.MD5(spID+PASS+timestamp));
			SOAPElement spId  = header.addChildElement("spId");
			spId.addTextNode(spID);
			
			SOAPElement serviceId  = header.addChildElement("serviceId");
			serviceId.addTextNode("20606900000001");
			SOAPElement timeStamp  = header.addChildElement("timeStamp");
			timeStamp.addTextNode(timestamp);
			SOAPElement traceUniqueID  = header.addChildElement("traceUniqueID");
			traceUniqueID.addTextNode("1000000011");
			
			((Stub) service).setHeader(header);
			
			String correlator="vinh_correlator";
			SmsMessage message = new SmsMessage();
			message.setMessage("Hello Vinh");
			URI address = new URI("tel:"+"84902421990");
			URI activatorNumber = new URI("tel:"+"9005");
			message.setSenderAddress(address);
			message.setSmsServiceActivationNumber(activatorNumber);
			message.setDateTime(Calendar.getInstance());
			
			
			service.notifySmsReception(correlator, message);
			
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public static String decodeMO(String hexString) throws Exception {
        String strReturn = "";
        hexString = "08005400e900740020007400691ebf006e00670020007600691ec70074";
        String strEncodec = hexString.substring(0, 2); //08 - unicode
        String strInfo = hexString.substring(2, hexString.length());
        byte[] bytes = Hex.decodeHex(strInfo.toCharArray());
        if (strEncodec.equals("00")) {
            strReturn = new String(bytes, "ASCII");
            System.out.println(strReturn);

        } else if (strEncodec.equals("08")) {
            strReturn = new String(bytes, "UnicodeBigUnmarked");
            System.out.println(strReturn);
        }
        return strReturn;
    }
	public static String toAscii(String inputStr)
	{
		System.out.println("Matches: " + inputStr.matches("[\u0000-\u00FF]+"));
		return "";
	}

}
