package com.vng.cpnew.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Description: DAOUtilities contains helper methods to for DAOs.
 * 
 * @author <A HREF="mailto:duyn77@yahoo.com">Nguyen Duc Duy</A>
 * @version 1.0
 */

public class DAOUtilities {

	private static final String CLASS_NAME = "com.vng.mo.util.DAOUtilities";

	/**
	 * Return list of values of ONE given field indicated by fieldName. This
	 * method uses the conn passed thru the caller for better performance
	 * REMEMBER DO NOT CLOSE conn inside this method because conn will be used
	 * in the caller method later. For example: + query =
	 * "SELECT SELLERID FROM SELLER WHERE LOANID = ?"; + keyValues =
	 * {"LOAN_ID_00001"}; + fieldName = "SELLERID"; +
	 * getListOfFieldValuesByKeys(query, keyValues,fieldName) will return list
	 * of SELLERIDs whose LOANID is "LOAN_ID_00001"
	 * 
	 * @param conn
	 *            Connection
	 * @param query
	 *            String
	 * @param keyValues
	 *            String[]
	 * @param fieldName
	 *            String
	 * @return List
	 * @throws SQLException
	 */
	public static List getListOfFieldValuesByKeys(final Connection conn,
			String query, String[] keyValues, String fieldName)
			throws SQLException {
		// always initilize an empty list
		List values = new ArrayList(0);

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			for (int i = 0, j = keyValues.length; i < j; i++) {
				ps.setString(i + 1, keyValues[i]);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				values.add(rs.getString(fieldName));
			}
		} catch (SQLException ecx) {
			throw ecx;
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
		return values;
	}

	/**
	 * Check if object existed in database by select id, if existed then return
	 * 1 else 0. Please note this method really does not return number of
	 * records. REMEMBER DO NOT CLOSE conn inside this method because conn will
	 * be used in the caller method later. Example: + countQuery:
	 * "SELECT ASSETID FROM ASSET WHERE ASSETID = ?" + recordId: 1
	 * 
	 * @param conn
	 *            Connection
	 * @param countQuery
	 *            String: SELECT ASSETID FROM ASSET WHERE ASSETID = ?
	 * @param recordId
	 *            String: 1
	 * @return int
	 * @throws SQLException
	 */
	public static int countRecords(final Connection conn, String countQuery,
			String recordId) throws SQLException {
		int noOfRecords = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(countQuery);
			ps.setString(1, recordId);
			rs = ps.executeQuery();
			noOfRecords = rs.next() ? 1 : 0;
		} catch (SQLException ecx) {
			// Logger.error(CLASS_NAME +
			// ".countRecords(): " + ecx);
			throw ecx;
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
		return noOfRecords;
	}

	/**
	 * Count the number of records. REMEMBER DO NOT CLOSE conn inside this
	 * method because conn will be used in the caller method later. REMEMBER DO
	 * NOT CLOSE conn inside this method because conn will be used in the caller
	 * method later. Example: + countQuery:
	 * "SELECT COUNT(*) FROM ASSET WHERE ASSETID = ?" + recordId: 1
	 * 
	 * @param conn
	 *            Connection
	 * @param countQuery
	 *            String
	 * @param recordId
	 *            String
	 * @return int
	 * @throws SQLException
	 */
	public static int countNumOfRecords(final Connection conn,
			String countQuery, String recordId) throws SQLException {
		int noOfRecords = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(countQuery);
			ps.setString(1, recordId);
			rs = ps.executeQuery();
			noOfRecords = rs.next() ? rs.getInt(1) : 0;
		} catch (SQLException ecx) {
			// Logger.error(CLASS_NAME +
			// ".countRecords(): " + ecx);
			throw ecx;
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
		return noOfRecords;
	}

	/**
	 * Check if object existed in database by select id, if existed then return
	 * 1 else 0. Please note this method really does not return number of
	 * records. REMEMBER DO NOT CLOSE conn inside this method because conn will
	 * be used in the caller method later. Example: + countQuery: "SELECT
	 * ASSETID FROM BORROWER_ASSET WHERE ASSETID = ? AND BORROWERID =
	 * ?" + keyValues: {"1", "1"}
	 * 
	 * @param conn
	 *            Connection
	 * @param countQuery
	 *            String
	 * @param keyValues
	 *            String[]
	 * @return int
	 * @throws SQLException
	 */
	public static int countRecords(final Connection conn, String countQuery,
			String[] keyValues) throws SQLException {
		int noOfRecords = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(countQuery);
			for (int i = 0, j = keyValues.length; i < j; i++) {
				ps.setString(i + 1, keyValues[i]);
			}
			rs = ps.executeQuery();
			noOfRecords = rs.next() ? 1 : 0;
		} catch (SQLException ecx) {
			// Logger.error(CLASS_NAME +
			// ".countRecords(): " + ecx);
			throw ecx;
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
		return noOfRecords;
	}

	/**
	 * Count the number of records. REMEMBER DO NOT CLOSE conn inside this
	 * method because conn will be used in the caller method later. Example: +
	 * countQuery: "SELECT COUNT(*) FROM BORROWER_ASSET WHERE ASSETID = ? AND
	 * BORROWERID = ?" + keyValues: {"1", "1"}
	 * 
	 * @param conn
	 *            Connection
	 * @param countQuery
	 *            String
	 * @param keyValues
	 *            String[]
	 * @return int
	 * @throws SQLException
	 */
	public static int countNumOfRecords(final Connection conn,
			String countQuery, String[] keyValues) throws SQLException {
		int noOfRecords = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(countQuery);
			for (int i = 0, j = keyValues.length; i < j; i++) {
				ps.setString(i + 1, keyValues[i]);
			}
			rs = ps.executeQuery();
			noOfRecords = rs.next() ? rs.getInt(1) : 0;
		} catch (SQLException ecx) {
			// Logger.error(CLASS_NAME +
			// ".countNumOfRecords(conn,countQuery,keyValues): " + ecx);
			throw ecx;
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
		return noOfRecords;
	}

	/**
	 * Return Map of values of ONE given field indicated by pair (fieldName1,
	 * fieldName2). This method uses the conn passed thru the caller for better
	 * performance REMEMBER DO NOT CLOSE conn inside this method because conn
	 * will be used in the caller method later. For example: + query = "SELECT
	 * SELLERID FROM SELLER WHERE LOANID = ?"; + keyValues = {"LOAN_ID_00001"};
	 * + fieldName = {"SELLERID", "BORROOWERID"}; +
	 * getListOfFieldValuesByKeys(query, keyValues,fieldName) will return list
	 * of SELLERIDs whose LOANID is "LOAN_ID_00001"
	 * 
	 * @param conn
	 *            Connection
	 * @param query
	 *            String
	 * @param keyValues
	 *            String[]
	 * @param fieldName1
	 *            String
	 * @param fieldName2
	 *            String
	 * @return List
	 * @throws MIFException
	 */
	public static List getListOfFieldValuesByKeys(final Connection conn,
			String query, String[] keyValues, String fieldName1,
			String fieldName2) throws SQLException {
		// always initilize an empty list
		List values = new ArrayList(0);

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			for (int i = 0, j = keyValues.length; i < j; i++) {
				ps.setString(i + 1, keyValues[i]);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				values.add(new ValueBean(rs.getString(fieldName1), rs
						.getString(fieldName2)));
			}
		} catch (SQLException ecx) {
			// Logger.error(".getListOfFieldValuesByKeys()" +
			// " query=" + query + " exception: " + ecx.getMessage());
			// throw new MIFException(ecx.getMessage());
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
		return values;
	}

	/**
	 * Return Map of values of ONE given field indicated by pair (fieldName1,
	 * fieldName2). This method uses the conn passed thru the caller for better
	 * performance REMEMBER DO NOT CLOSE conn inside this method because conn
	 * will be used in the caller method later. For example: + query = "SELECT
	 * SELLERID FROM SELLER WHERE LOANID = ?"; + keyValues = {"LOAN_ID_00001"};
	 * + fieldName = {"SELLERID", "BORROOWERID"}; +
	 * getListOfFieldValuesByKeys(query, keyValues,fieldName) will return list
	 * of SELLERIDs whose LOANID is "LOAN_ID_00001"
	 * 
	 * @param conn
	 *            Connection
	 * @param query
	 *            String
	 * @param keyValues
	 *            String[]
	 * @param fieldNames
	 *            String
	 * @return List
	 * @throws SQLException
	 */
	public static List getListOfFieldValuesByKeys(final Connection conn,
			String query, String[] keyValues, String[] fieldNames)
			throws SQLException {
		// always initilize an empty list
		List values = new ArrayList(0);

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			for (int i = 0, j = keyValues.length; i < j; i++) {
				ps.setString(i + 1, keyValues[i]);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				// new String[] to contain values
				String[] fieldValues = new String[fieldNames.length];
				for (int k = 0; k < fieldNames.length; k++) {
					fieldValues[k] = rs.getString(fieldNames[k]);
				}
				values.add(fieldValues);
			}
		} catch (SQLException ecx) {
			throw ecx;
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
		return values;
	}

	/**
	 * supporting to get a pair of columns
	 * 
	 * @author not attributable
	 * @version 1.0
	 */
	static public final class ValueBean {
		String value1 = null;
		String value2 = null;

		public ValueBean() {
		}

		public ValueBean(String val1, String val2) {
			setValue1(val1);
			setValue2(val2);
		}

		public String getValue1() {
			return value1;
		}

		public String getValue2() {
			return value2;
		}

		public void setValue1(String val1) {
			value1 = val1;
		}

		public void setValue2(String val2) {
			value2 = val2;
		}
	}

}
