package com.vng.cpnew.util;

import java.security.MessageDigest;

/**
 * 
 * 
 * Example String algorithmName = "MD5"; or "SHA1" or "SHA" String test =
 * "test test"; Hasher hasher = Hasher.getInstance(algorithmName);
 * System.out.println(hasher.hash(test));
 */
public class Hasher {
	private String alg;
	private static Hasher instance;

	private Hasher() {
	}

	public Hasher(String alg) {
		this.alg = alg;
	}

	public static synchronized Hasher getInstance(String alg) {
		if (instance == null) {
			return new Hasher(alg);
		} else {
			return instance;
		}
	}

	public String hash(String data) throws Exception {

		if (data == null)
			throw new NullPointerException();

		MessageDigest md = MessageDigest.getInstance(alg);
		byte[] buffer = data.getBytes();

		md.reset();
		md.update(buffer);

		byte[] msgDigest = md.digest();

		StringBuffer result = new StringBuffer();

		for (int i = 0; i < msgDigest.length; i++) {
			String hex = Integer.toHexString(0xff & msgDigest[i]);
			if (hex.length() == 1)
				result.append('0');
			result.append(hex);
		}

		return result.toString();
	}
}