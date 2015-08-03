package com.vng.cpnew.util;

import java.util.HashMap;
import java.util.Map;

public class ServicePrice {
	private static ServicePrice instance = null;
	public static String SPAM_MESSAGE = "Tin nhan SPAM. Ban khong duoc nhan qua 150.000d/ngay. Vui long goi 1900561558 de duoc huong dan them.";

	private Map<String, String> servicePrices = new HashMap<String, String>();
	private Map<String, String> operatorMaxMoneyAllow = new HashMap<String, String>();

	private ServicePrice() {

		servicePrices.put("6069", "500");
		servicePrices.put("6169", "1000");
		servicePrices.put("6269", "2000");
		servicePrices.put("6369", "3000");
		servicePrices.put("6469", "4000");
		servicePrices.put("6569", "5000");
		servicePrices.put("6669", "10000");
		servicePrices.put("6769", "15000");

		operatorMaxMoneyAllow.put("GPC", "150000");
		operatorMaxMoneyAllow.put("VMS", "150000");
		operatorMaxMoneyAllow.put("VIETEL", "150000");
		operatorMaxMoneyAllow.put("SFONE", "150000");

	}

	public static ServicePrice getInstance() {
		if (instance == null) {
			instance = new ServicePrice();
		}
		return instance;
	}

	public int getPriceByServiceNumber(String serviceNumber) {
		return new Integer(servicePrices.get(serviceNumber)).intValue();
	}

	public int getMaxMoneyAllowByOperator(String operator) {
		return new Integer(operatorMaxMoneyAllow.get(operator)).intValue();
	}

	public boolean isExistServiceNumber(String serviceNumber) {
		return servicePrices.containsKey(serviceNumber);
	}

	public boolean isExistOperator(String operator) {
		return operatorMaxMoneyAllow.containsKey(operator);
	}

}