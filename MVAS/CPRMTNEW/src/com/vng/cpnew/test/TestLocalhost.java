package com.vng.cpnew.test;

import java.net.URL;

import org.apache.axis.encoding.Base64;

import com.vng.cpnew.util.Hasher;
import com.vng.cpnew.ws.CPRMTNEW;
import com.vng.cpnew.ws.CPRMTNEWService;
import com.vng.cpnew.ws.CPRMTNEWServiceLocator;

public class TestLocalhost {
	public static void main(String[] args) {
		CPRMTNEWService zingReceiverMTService = new CPRMTNEWServiceLocator();
		try {
			// ZingReceiverMT receiverMO =
			// zingReceiverMTService.getZingReceiverMT(new
			// URL("http://localhost:8080/axis/services/ZingReceiverMT"));

			CPRMTNEW receiverMO = zingReceiverMTService.getCPRMT(new URL(
					"http://10.199.38.101/axis/services/CPRMTNEW"));
			// System.out.println(receiverMO.mtReceiver(832028298,
			// "841225471721", "6069", "VNG4", "VNG4 test01", 0, 0, 1,
			// "zingphoto", "password"));
			String message = "Khong thanh cong. So dien thoai dang ky cho tai khoan tunl2 tren he thong cua VNG khong dung voi so dang dung de nhan tin - IT Helpdesk: 0934088588";
			String requestID = "11131216155536009";
			String userID = "84918255063";
			String serviceID = "6169";
			String commandCode = "Aido";
			String secretKey = "mvas@#123";
			String sig = Hasher.getInstance("MD5").hash(
					requestID + userID + serviceID + commandCode + message
							+ secretKey);

			System.out.println(sig);

			System.out.println(message);
			message = Base64.encode(message.getBytes());
			System.out.println(message);
			System.out.println(receiverMO.mtReceiver(requestID, userID,
					serviceID, commandCode, message, 1, "GPC", 1, sig));

			/*
				 */
			/*
			 * Pattern p =
			 * Pattern.compile("([a-zA-Z_.\\s,?!@#$%^\\-&*()_+:<>;/~\"0-9])*");
			 * //Matcher m =p.matcher(
			 * "fdgdfg. Tu choi nhan thong -bao?,: |soan~ tin TC gui 6269.");
			 * Matcher m =p.matcher(
			 * "CSM gui thong tin thong bao toi khach hang. De tu choi thong bao, vui long nhan TCTB gui 6069."
			 * ); boolean b = m.matches(); System.out.println(b); /
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
