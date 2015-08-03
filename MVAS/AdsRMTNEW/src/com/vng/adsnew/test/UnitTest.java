package com.vng.adsnew.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vng.adsnew.util.MobileOperator;

public class UnitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Connection connection = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager
						.getConnection("jdbc:mysql://10.199.38.102:3306/smsgw?useUnicode=true&characterEncoding=utf8"
								+ "&user=" + "root" + "&password=" + "123456");

				boolean a = isAllowMessageString("fdfdf[]d'f");
				if (a) {
					System.out.println("Ok");
				} else {
					System.out.println("false");
				}
				System.out.println("connection is null");
			} catch (Exception ecx) {
				// logger.error("FAILED TO CREATE CONNECTION TO :" +
				// ecx.getMessage());
				throw new SQLException("FAILED TO CREATE CONNECTION TO" + ": "
						+ ecx.getMessage());
			}

			if (connection == null) {
				System.out.println("connection is null");
			}
			// String operator =
			// MobileOperator.getInstance().getCachedOperator(connection,
			// "84909541169");
			// String operator2 =
			// MobileOperator.getInstance().getCachedOperator(connection,
			// "84909541169");
			// System.out.println(operator);
			// System.out.println(operator2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean isAllowMessageString(String messsage) {
		if (messsage == null || messsage.trim().equals("")) {
			return false;
		}

		Pattern p = Pattern
				.compile("([a-zA-Z_.\\s,?\\[\\]!'@#$%=^\\-&*()_+:<>;/~\"0-9])*");
		Matcher m = p.matcher(messsage);
		return m.matches();
	}

}
