package com.vng.mvas.vmsgateway;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import org.apache.axis.client.Stub;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.axis.types.URI;
import org.apache.axis.types.URI.MalformedURIException;
import org.apache.log4j.Logger;
import org.csapi.www.schema.parlayx.common.v2_1.PolicyException;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2._interface.SendSms;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceLocator;

import com.vng.mvas.models.Sending;
import com.vng.mvas.utils.Utils;

public class SendSMSClient {
	private static String SPID = System.getProperty("spid");
	private static String PASSWORD = System.getProperty("password_VMS");
	private static String SERVICEID = System.getProperty("serviceId");
	private static String MT_URL = System.getProperty("MT_URL_VMS");
	private static final Logger logger = Logger.getLogger(SendSMSClient.class);

	/**
	 * @param args
	 */
	public static String sendSMS(Sending sending) {
		String result = null;
		SendSmsServiceLocator locator = new SendSmsServiceLocator();
		locator.setSendSmsEndpointAddress(MT_URL);
//		logger.info("MT_URL:" + MT_URL);
		
		try {

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			SendSms service = locator.getSendSms();
			SOAPHeaderElement header = new SOAPHeaderElement(
					"http://www.huawei.com.cn/schema/common/v2_1",
					"RequestSOAPHeader");

			SOAPElement spId = header.addChildElement("spId");
			spId.addTextNode(SPID);
			SOAPElement password = header.addChildElement("spPassword");
			String strtimeStamp= df.format(new Date());
			password.addTextNode(Utils.MD5(SPID + PASSWORD + strtimeStamp));

			SOAPElement serviceId = header.addChildElement("serviceId");
			serviceId.addTextNode(Utils.getServiceID(sending.getServiceId()));

			SOAPElement timeStamp = header.addChildElement("timeStamp");
			timeStamp.addTextNode(strtimeStamp);

			SOAPElement OA = header.addChildElement("OA");
			OA.addTextNode(sending.getUSER_ID());
			
			SOAPElement FA = header.addChildElement("FA");
			FA.addTextNode(sending.getUSER_ID());
			

			((Stub) service).setHeader(header);
			URI address = new URI("tel:" + sending.getUSER_ID());
			logger.info("Sending msg:" + sending.toString());
			
			result = service.sendSms(new URI[] { address },
					sending.getServiceId(), null, sending.getINFO(), null);
			logger.info("Result:" + result);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		} catch (SOAPException e) {
			logger.error(e.getMessage());
		} catch (MalformedURIException e) {
			logger.error( e.getMessage());
		} catch (PolicyException e) {
			logger.error(e.getMessage());
		} catch (org.csapi.www.schema.parlayx.common.v2_1.ServiceException e) {
			String messageID = e.getMessageId();
			String text = e.getText();
			logger.error(e.getMessage());
			logger.info("ServiceException:messageID= " + messageID);
			logger.info("ServiceException:text= " + text);
		} catch (RemoteException e) {
			logger.error(e.getMessage());
		}
		return result;

	}
	
	public static String sendSMS_Test(Sending sending) {
		return "test";
	}

}
