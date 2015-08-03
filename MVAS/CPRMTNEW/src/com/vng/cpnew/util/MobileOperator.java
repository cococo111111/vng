package com.vng.cpnew.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.vng.cpnew.db.dao.beans.SeriesMobileNumber;
import com.vng.cpnew.server.DBConfigLoader;

public class MobileOperator {
	private static MobileOperator instance = null;
	private static Logger logger = Logger.getLogger(MobileOperator.class);

	private Map<String, String> seriesNumbers = new HashMap<String, String>();
	private SeriesMobileNumber seriesMobileNumber = null;

	private MobileOperator() {
	}

	public static MobileOperator getInstance() {
		if (instance == null) {
			synchronized (MobileOperator.class) {
				if (instance == null) {
					instance = new MobileOperator();
				}
			}

		}
		return instance;
	}

	/*
	 * public boolean isValidUserPhone(Connection connection, String userID){
	 * String operator = seriesNumbers.get(userID); if ( null != operator &&
	 * !"".equals(operator) ){ return true; }else{ seriesMobileNumber =
	 * getOperator(connection, userID); if (null != seriesMobileNumber &&
	 * !seriesMobileNumber.getSeriesNumber().equalsIgnoreCase("")
	 * &&!seriesMobileNumber.getOperator().equalsIgnoreCase("") ){
	 * seriesNumbers.put(seriesMobileNumber.getSeriesNumber(),
	 * seriesMobileNumber.getOperator()); return true; }else{ return false; } }
	 * }
	 */

	public String getCachedOperator(Connection connection, String userID) {

		Set<String> series = seriesNumbers.keySet();
		for (String serie : series) {
			if (userID.startsWith(serie)) {
				return seriesNumbers.get(serie);
			}

		}
		seriesMobileNumber = getOperator(connection, userID);
		if (null != seriesMobileNumber
				&& !seriesMobileNumber.getSeriesNumber().equalsIgnoreCase("")
				&& !seriesMobileNumber.getOperator().equalsIgnoreCase("")) {
			seriesNumbers.put(seriesMobileNumber.getSeriesNumber(),
					seriesMobileNumber.getOperator());
			return seriesMobileNumber.getOperator();
		} else {
			return null;
		}

	}

	private SeriesMobileNumber getOperator(Connection connection, String userID) {
		String strQuery = "SELECT operator, LEFT(?,length(series)) seriesNumber FROM operators WHERE series = LEFT(?,length(series)) LIMIT 1";
		String operator = "";
		String seriesNumber = "";
		PreparedStatement ps = null;
		SeriesMobileNumber seriesMobileNumber = null;
		try {
			ps = connection.prepareStatement(strQuery);
			ps.setString(1, userID);
			ps.setString(2, userID);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				operator = result.getString("operator");
				seriesNumber = result.getString("seriesNumber");
				if (operator == null) {
					operator = "";
				}
				seriesMobileNumber = new SeriesMobileNumber();
				seriesMobileNumber.setOperator(operator.trim().toUpperCase());
				seriesMobileNumber.setSeriesNumber(seriesNumber.trim());
			}
			result.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
			}
			;
		}
		return seriesMobileNumber;
	}

	public boolean isAllowOperator(String operator) {
		String ALLOW_OPERATORS = DBConfigLoader.getInstance().getDbConfig()
				.getAllowOperators();
		if (ALLOW_OPERATORS != null) {
			String[] ALLOW_OPERATORStmp = ALLOW_OPERATORS.trim().split(",");
			if (ALLOW_OPERATORStmp != null) {
				for (int i = 0; i < ALLOW_OPERATORStmp.length; i++) {
					if (ALLOW_OPERATORStmp[i].trim().equalsIgnoreCase(operator)) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
