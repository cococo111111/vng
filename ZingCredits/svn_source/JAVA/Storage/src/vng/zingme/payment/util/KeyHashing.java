package vng.zingme.payment.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class KeyHashing {
	public static Long hash(String str){
		Long key = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			Logger.getLogger("appActions").log(Priority.ERROR, e.getMessage());
		}
		return key;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random random = new Random(1);
		for(int i=0; i<100;i++){
			String str = "Testing_rerasrdsrfdsaradrsdeardsrdasrasrrsadrdsarasdras" + random.nextLong();
			System.out.println("String: " + str);
			System.out.println("hash: " + str.hashCode());
		}
	}

}
