package vng.zingme.stats.service;

import java.security.MessageDigest;

public class Md5Encryption {

	private static String convertToHex(byte[] data) {
		// convert the byte to hex format
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < data.length; i++) {
			sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(
					1));
		}
		return sb.toString();
	}

	public static String MD5(String text) {
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("MD5");
			byte[] md5hash = new byte[32];
			md.update(text.getBytes("UTF-8"), 0, text.length());
			md5hash = md.digest();
			return convertToHex(md5hash);
		} catch (Exception ex) {
		}
		return "";
	}

	public static String SHA1(String text) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] sha1hash = new byte[40];
			md.update(text.getBytes("iso-8859-1"), 0, text.length());
			sha1hash = md.digest();
			return convertToHex(sha1hash);
		} catch (Exception ex) {
		}
		return "";
	}
}