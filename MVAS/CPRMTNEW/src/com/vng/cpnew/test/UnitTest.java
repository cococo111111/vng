package com.vng.cpnew.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.vng.cpnew.util.MobileOperator;

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
			} catch (Exception ecx) {
				// logger.error("FAILED TO CREATE CONNECTION TO :" +
				// ecx.getMessage());
				throw new SQLException("FAILED TO CREATE CONNECTION TO" + ": "
						+ ecx.getMessage());
			}

			if (connection == null) {
				System.out.println("connection is null");
			}
			String operator = MobileOperator.getInstance().getCachedOperator(
					connection, "84909541169");
			String operator2 = MobileOperator.getInstance().getCachedOperator(
					connection, "84909541169");
			System.out.println(operator);
			System.out.println(operator2);
			/*
			 * String serviceID = "6569,"; int contentType = 0;
			 * 
			 * if (serviceID.indexOf(",") > 0){ String[] tmpserviceIDs =
			 * serviceID.split(","); if (tmpserviceIDs != null &&
			 * tmpserviceIDs.length == 2){ try{ serviceID =
			 * tmpserviceIDs[0].trim(); contentType =
			 * Integer.parseInt(tmpserviceIDs[1].trim()); }catch (Exception e) {
			 * contentType = 0; } }else{ contentType = 0; } }
			 * 
			 * System.out.println("serviceID:" + serviceID);
			 * System.out.println("contentType:" + contentType);
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
