/**
 * SmsNotificationServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package com.vng.mvas.vms;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.csapi.www.schema.parlayx.sms.notification.v2_2.local.NotifySmsReceptionResponse;
import org.csapi.www.schema.parlayx.sms.notification.v2_2.local.NotifySmsReceptionResponseE;

import cn.com.huawei.www.schema.common.v2_1.NotifySOAPHeader;

import com.reardencommerce.kernel.collections.shared.evictable.ConcurrentLinkedHashMap;
import com.vng.mvas.models.Incoming;
import com.vng.mvas.utils.CacheService;
import com.vng.mvas.utils.ConfigUtils;
import com.vng.mvas.utils.DBUtils;
import com.vng.mvas.utils.DataUtils;
import com.vng.mvas.utils.Utils;

/**
 * SmsNotificationServiceSkeleton java skeleton for the axisService
 */
public class SmsNotificationService {
	private int MSG_TYPE_REFUND = 0;
	private int CONTENT_TYPE_TEXT = 0;
	private int TOTAL_SEGMENT = 0;
	private String PASSWORD = "";
	private ConcurrentLinkedHashMap<String, String> cached = null;
	private static final Logger log = Logger.getLogger(SmsNotificationService.class);

	public SmsNotificationService() {
		ConfigUtils.init();
		MSG_TYPE_REFUND = Integer.parseInt(System
				.getProperty("MSG_TYPE_REFUND"));
		;
		CONTENT_TYPE_TEXT = Integer.parseInt(System
				.getProperty("CONTENT_TYPE_TEXT"));
		TOTAL_SEGMENT = Integer.parseInt(System.getProperty("TOTAL_SEGMENT"));
		PASSWORD = System.getProperty("PASSWORD");
		System.out.println("MOBILE_OPERATOR:"
				+ System.getProperty("MOBILE_OPERATOR"));
		cached = CacheService.getInstance().getCardcache();
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param notifySmsDeliveryReceipt
	 * @return notifySmsDeliveryReceiptResponse
	 */
	

	public org.csapi.www.schema.parlayx.sms.notification.v2_2.local.NotifySmsDeliveryReceiptResponseE notifySmsDeliveryReceipt(
			org.csapi.www.schema.parlayx.sms.notification.v2_2.local.NotifySmsDeliveryReceiptE notifySmsDeliveryReceipt) {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement "
				+ this.getClass().getName() + "#notifySmsDeliveryReceipt");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param notifySmsReception
	 * @return notifySmsReceptionResponse
	 */

	public org.csapi.www.schema.parlayx.sms.notification.v2_2.local.NotifySmsReceptionResponseE notifySmsReception(
			org.csapi.www.schema.parlayx.sms.notification.v2_2.local.NotifySmsReceptionE notifySmsReception) {
		// notifySmsReception.getPullParser(qName)
		org.apache.axiom.soap.SOAPHeader header = org.apache.axis2.context.MessageContext
				.getCurrentMessageContext().getEnvelope().getHeader();
		try {
			OMElement element = header.getFirstChildWithName(new QName(
					"http://www.huawei.com.cn/schema/common/v2_1",
					"NotifySOAPHeader"));
			NotifySOAPHeader h = NotifySOAPHeader.Factory.parse(element
					.getXMLStreamReader());
			String cpID = h.getSpId();
			String longServiceID = h.getServiceId();
			String timeVMSSend = h.getTimeStamp();
			String phoneNumber = notifySmsReception.getNotifySmsReception()
					.getMessage().getSenderAddress().toString();
			String shortServiceID = notifySmsReception.getNotifySmsReception()
					.getMessage().getSmsServiceActivationNumber().toString();
			String traceUniqueID = h.getTraceUniqueID();
			String requestID = DataUtils.getREQUEST_ID();
			String msgInfo = notifySmsReception.getNotifySmsReception()
					.getMessage().getMessage();

			// decodeMO
			msgInfo = decodeMO(msgInfo);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			Date msgTime  = df.parse(timeVMSSend);
			//Date msgTime = notifySmsReception.getNotifySmsReception()					.getMessage().getDateTime().getTime();
			Incoming msg = new Incoming(cpID, longServiceID, timeVMSSend,
					msgInfo, phoneNumber, shortServiceID, msgTime,
					traceUniqueID, requestID);
//			String revPass = h.getSpRevpassword();
//			String pass = Utils.MD5(cpID + PASSWORD + timeVMSSend);
			//System.out.println("VINH- msg:" + msg.toString());
			log.info("Receive msg: " + msg.toString());
			String serviceID = DataUtils.getSERVICE_ID(msg
					.getSmsServiceActivationNumber());
			//if (pass.equals(revPass) || "9005".equals(serviceID)) {
			//	if (true) {
				if (!msgInfo.matches("[\u0000-\u00FF]+")) {
					int rs = DBUtils.logIncoming(msg, true);
					rs = DBUtils.addInvalidMessage(msg, CONTENT_TYPE_TEXT,
							MSG_TYPE_REFUND, TOTAL_SEGMENT);

				} else if (Utils.isOverLimitation(msg, cached)) {
					int rs = DBUtils.logIncoming(msg, false);
					rs = DBUtils.addOverLimitation(msg, CONTENT_TYPE_TEXT,
							MSG_TYPE_REFUND, TOTAL_SEGMENT);
				} else {
					int result = DBUtils.addIncoming(msg);
					String log_line = timeVMSSend + "," + phoneNumber + ","
							+ shortServiceID + "," + msgInfo + ","
							+ msgTime.toString() + "," + result;
					log.info("Receive: " + log_line);
				}
//			} else {
//				log.warn("WRONG PASSWORD:" + PASSWORD + "  spID="
//						+ cpID + "  timestamp=" + timeVMSSend + "\nrevPass="
//						+ revPass + "\nPass=" + pass);
//			}

		} catch (Exception e) {
			log.error("Ex: " + e.getMessage());
		}

		// System.out.println("MSG:"+notifySmsReception.getNotifySmsReception().getMessage().getMessage());
		NotifySmsReceptionResponseE response = new NotifySmsReceptionResponseE();
		NotifySmsReceptionResponse local = new NotifySmsReceptionResponse();
		response.setNotifySmsReceptionResponse(local);

		return response;
	}

	private String decodeMO(String hexString) throws Exception {
		String strReturn = "";
		String strEncodec = hexString.substring(0, 2); // 08 - unicode
		String strInfo = hexString.substring(2, hexString.length());
		byte[] bytes = Hex.decodeHex(strInfo.toCharArray());
		if (strEncodec.equals("00")) {
			strReturn = new String(bytes, "ASCII");
		} else if (strEncodec.equals("08")) {
			strReturn = new String(bytes, "UnicodeBigUnmarked");
		}

		return strReturn;
	}

}
