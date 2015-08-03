package vng.zingme.stats.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import vng.zingme.payment.thrift.T_ReportSummary;
import vng.zingme.stats.dao.AppDao;
import vng.zingme.stats.mySqlConnectionPool.Config2;
import vng.zingme.stats.mySqlConnectionPool.DBConnectionManager;

/**
 * @author sonhoang
 * 
 */
@Repository
public class AppDaoImpl implements AppDao {
	private static Logger log = Logger.getLogger(AppDaoImpl.class);

	private static final String SUMMARY = "select appID,SUM(totalTranx) as totalTranx,SUM(totalSuccessTranx) as totalSuccessTranx,SUM(totalFailureTranx) as totalFailureTranx,SUM(totalTimeoutTranx) as totalTimeoutTranx,SUM(totalNetworkFailTranx) as totalNetworkFailTranx,SUM(amount) as amount from apps_summary where appID=? and analyticDate>=? and analyticDate<=? order by analyticDate";
	private static final String SUMMARY_DAILY = "select * from apps_summary where appID=? and analyticDate>=? and analyticDate<=? order by analyticDate";

	private Connection dbConnection = null;
	private ResultSet res = null;
	private PreparedStatement pstmt = null;

	@Override
	public T_ReportSummary summary(String appID, String startDate,
			String endDate) {
		T_ReportSummary report = null;
		try {
			dbConnection = (Connection) DBConnectionManager.getInstance()
					.getConnection(Config2.timeOut);
			pstmt = (PreparedStatement) dbConnection.prepareStatement(SUMMARY);

			pstmt.setString(1, appID);
			pstmt.setString(2, startDate);
			pstmt.setString(3, endDate);

			res = pstmt.executeQuery();
			while (res.next()) {
				report = new T_ReportSummary();
				if (res.getString("appID") == null) {
					return null;
				}
				report.setAgentID(res.getString("appID"));
				report.setTotalMoney(Double.parseDouble(res.getString("amount")));
				report.setTotalTransaction(Integer.parseInt(res
						.getString("totalTranx")));
				report.setTotalTransactionSuccess(Integer.parseInt(res
						.getString("totalSuccessTranx")));
				report.setTotalTransactionFail(Integer.parseInt(res
						.getString("totalFailureTranx")));
				report.setTotalTransactionTimeOut(Integer.parseInt(res
						.getString("totalTimeoutTranx")));
				report.setTotalTransactionNetError(Integer.parseInt(res
						.getString("totalNetworkFailTranx")));
			}
		} catch (Exception ex) {
			log.error("Connection SUMMARY fail " + ex.getMessage());
		} finally {
			if (dbConnection != null) {
				DBConnectionManager.getInstance()
						.returnConnection(dbConnection);
			}
			if (res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					log.error("res SUMMARY close fail " + ex.getMessage());
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					log.error("pstm SUMMARY close fail " + ex.getMessage());
				}
			}
		}
		return report;
	}

	@Override
	public List<T_ReportSummary> summaryDaily(String appID, String startDate,
			String endDate) {
		List<T_ReportSummary> reportList = null;
		try {
			dbConnection = (Connection) DBConnectionManager.getInstance()
					.getConnection(Config2.timeOut);
			pstmt = (PreparedStatement) dbConnection
					.prepareStatement(SUMMARY_DAILY);
			pstmt.setString(1, appID);
			pstmt.setString(2, startDate);
			pstmt.setString(3, endDate);

			res = pstmt.executeQuery();

			reportList = new ArrayList<>();
			while (res.next()) {
				T_ReportSummary report = new T_ReportSummary();
				report.setAgentID(res.getString("appID"));
				report.setStartTime(String.valueOf(res.getDate("analyticDate")));
				report.setEndTime(String.valueOf(res.getDate("analyticDate")));
				report.setTotalMoney(Double.parseDouble(res.getString("amount")));
				report.setTotalTransaction(Integer.parseInt(res
						.getString("totalTranx")));
				report.setTotalTransactionSuccess(Integer.parseInt(res
						.getString("totalSuccessTranx")));
				report.setTotalTransactionFail(Integer.parseInt(res
						.getString("totalFailureTranx")));
				report.setTotalTransactionTimeOut(Integer.parseInt(res
						.getString("totalTimeoutTranx")));
				report.setTotalTransactionNetError(Integer.parseInt(res
						.getString("totalNetworkFailTranx")));
				report.setTotalDiffUser(Integer.parseInt(res
						.getString("totalUsers")));

				reportList.add(report);
			}
		} catch (Exception ex) {
			log.error("Connection SUMMARY_DAILY fail " + ex.getMessage());
		} finally {
			if (dbConnection != null) {
				DBConnectionManager.getInstance()
						.returnConnection(dbConnection);
			}
			if (res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					log.error("res SUMMARY_DAILY close fail " + ex.getMessage());
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					log.error("pstm SUMMARY_DAILY close fail "
							+ ex.getMessage());
				}
			}
		}
		return reportList;
	}

}
