package vng.zingme.stats.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import vng.zingme.payment.thrift.T_ReportSummary;
import vng.zingme.stats.dao.ReportDao;
import vng.zingme.stats.model.ReportDetail;
import vng.zingme.stats.mySqlConnectionPool.Config2;
import vng.zingme.stats.mySqlConnectionPool.DBConnectionManager;

@Repository
public class ReportDaoImpl implements ReportDao {

	private static Logger log = Logger.getLogger(ReportDaoImpl.class);

	private final static String GET_REPORT_SUMMARY = "select * from apps_summary where appID=? and analyticDate>=? and analyticDate<=? order by analyticDate";
	private final static String GET_REPORT_SUMMARY_BY_DATE = "select * from apps_summary where analyticDate>=? and analyticDate<=? order by analyticDate";
	private final static String GET_REMAIN_BALANCE_BY_DATE = "SELECT * FROM `remain_balance` WHERE  analyticDate>=? and analyticDate<=? order by analyticDate";
	private final static String GET_PAYROLL_SUMMARY_BY_DATE = "select  sum(amount) as sumamount from apps_summary where analyticDate>= ? and analyticDate<=? and `appID` != 'zing' and `appID` !='admin' group by analyticDate ";
	// private final static String GET_APPLISTS_BY_DATE =
	// "select appID,analyticDate,amount from  apps_summary where analyticDate >=? and analyticDate <= ? and appID in ( ? ) order by analyticDate; ";
	private Connection dbConnection = null;
	private ResultSet res = null;
	private PreparedStatement pstmt = null;

	@Override
	public List<T_ReportSummary> getListDailyReport(String appId,
			String startDate, String endDate) {

		List<T_ReportSummary> reportList = null;
		try {
			dbConnection = (Connection) DBConnectionManager.getInstance()
					.getConnection(Config2.timeOut);
			pstmt = (PreparedStatement) dbConnection
					.prepareStatement(GET_REPORT_SUMMARY);
			pstmt.setString(1, appId);
			pstmt.setString(2, startDate);
			pstmt.setString(3, endDate);

			res = pstmt.executeQuery();
			reportList = new ArrayList<>();
			while (res.next()) {
				if (res.getString("appID") == null) {
					return null;
				}
				T_ReportSummary report = new T_ReportSummary();
				report.setAgentID(res.getString("appID"));
				report.setStartTime(res.getString("analyticDate"));
				report.setEndTime(res.getString("analyticDate"));
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
			log.error("Connection GET_REPORT_SUMMARY fail " + ex.getMessage());
		} finally {
			if (dbConnection != null) {
				DBConnectionManager.getInstance()
						.returnConnection(dbConnection);
			}
			if (res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					log.error("res GET_REPORT_SUMMARY close fail "
							+ ex.getMessage());
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					log.error("pstm GET_REPORT_SUMMARY close fail "
							+ ex.getMessage());
				}
			}
		}
		return reportList;
	}

	@Override
	public List<T_ReportSummary> getListReportByDate(String startDate,
			String endDate) {
		List<T_ReportSummary> reportSummaryList = null;
		
		try {
			dbConnection = (Connection) DBConnectionManager.getInstance()
					.getConnection(Config2.timeOut);
			pstmt = (PreparedStatement) dbConnection
					.prepareStatement(GET_REPORT_SUMMARY_BY_DATE);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);

			res = pstmt.executeQuery();
			if (!res.wasNull()) {
				reportSummaryList = new ArrayList<>();
				while (res.next()) {

					T_ReportSummary report = new T_ReportSummary();
					report.setAgentID(res.getString("appID"));
					report.setStartTime(res.getString("analyticDate"));
					report.setEndTime(res.getString("analyticDate"));
					report.setTotalMoney(Double.parseDouble(res
							.getString("amount")));
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

					reportSummaryList.add(report);
				}
			}
		} catch (Exception ex) {
			log.error("Connection GET_REPORT_SUMMARY fail " + ex.getMessage());
		} finally {
			if (dbConnection != null) {
				DBConnectionManager.getInstance()
						.returnConnection(dbConnection);
			}
			if (res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					log.error("res GET_REPORT_SUMMARY close fail "
							+ ex.getMessage());
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					log.error("pstm GET_REPORT_SUMMARY close fail "
							+ ex.getMessage());
				}
			}
		}
		return reportSummaryList;
	}

	@Override
	public HashMap<String, String> getRemainBalanceByDate(String startDate,
			String endDate) {
		HashMap<String, String> result = null;
		try {
			dbConnection = (Connection) DBConnectionManager.getInstance()
					.getConnection(Config2.timeOut);
			pstmt = (PreparedStatement) dbConnection
					.prepareStatement(GET_REMAIN_BALANCE_BY_DATE);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);

			res = pstmt.executeQuery();
			if (!res.wasNull()) {
				result = new HashMap<String, String>();
				while (res.next()) {
					result.put(res.getString("analyticDate"),
							res.getString("remainBalance"));
				}
			}
		} catch (Exception ex) {
			log.error("Connection GET_REMAIN_BALANCE_BY_DATE fail "
					+ ex.getMessage());
		} finally {
			if (dbConnection != null) {
				DBConnectionManager.getInstance()
						.returnConnection(dbConnection);
			}
			if (res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					System.out
							.println("res GET_REMAIN_BALANCE_BY_DATE close fail "
									+ ex.getMessage());
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					log.error("pstm GET_REMAIN_BALANCE_BY_DATE close fail "
							+ ex.getMessage());
				}
			}
		}
		return result;
	}

	public List<String> getPayrollSummaryByDate(String startDate, String endDate) {
		List<String> payRollSummaryList = null;
		try {
			dbConnection = (Connection) DBConnectionManager.getInstance()
					.getConnection(Config2.timeOut);
			pstmt = (PreparedStatement) dbConnection
					.prepareStatement(GET_PAYROLL_SUMMARY_BY_DATE);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);

			res = pstmt.executeQuery();
			if (!res.wasNull()) {
				payRollSummaryList = new ArrayList<>();
				while (res.next()) {
					String dailyRemainBalance = res.getString("sumamount");
					payRollSummaryList.add(dailyRemainBalance);
				}
			}
		} catch (Exception ex) {
			log.error("Connection GET_PAYROLL_SUMMARY_BY_DATE fail "
					+ ex.getMessage());
		} finally {
			if (dbConnection != null) {
				DBConnectionManager.getInstance()
						.returnConnection(dbConnection);
			}
			if (res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					log.error("res GET_PAYROLL_SUMMARY_BY_DATE close fail "
							+ ex.getMessage());
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					log.error("pstm GET_PAYROLL_SUMMARY_BY_DATE close fail "
							+ ex.getMessage());
				}
			}
		}
		return payRollSummaryList;
	}

	public List<ReportDetail> getAmountOfListAppByDate(String startDate,
			String endDate, List<String> appList) {
		List<ReportDetail> amountOfListApp = new ArrayList<>();
		try {
			StringBuilder inClause = new StringBuilder();
			for (int i = 0; i < appList.size(); i++) {
				inClause.append('?');
				if (i != appList.size() - 1) {
					inClause.append(',');
				}
			}

			dbConnection = (Connection) DBConnectionManager.getInstance()
					.getConnection(Config2.timeOut);
			pstmt = (PreparedStatement) dbConnection
					.prepareStatement("select appID,analyticDate,amount from  apps_summary where analyticDate >=? and analyticDate <= ? and appID in ("
							+ inClause + ") order by analyticDate;");
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);

			for (int i = 0; i < appList.size(); i++) {
				pstmt.setString(i + 3, appList.get(i));
			}
			res = pstmt.executeQuery();
			while (res.next()) {
				ReportDetail report = new ReportDetail();
				report.setAppId(res.getString("appID"));
				report.setDate(res.getString("analyticDate"));
				report.setAmount(res.getString("amount"));
				amountOfListApp.add(report);
			}
		} catch (Exception ex) {
			log.error("Connection GET_APPLISTS_BY_DATE fail " + ex.getMessage());
		} finally {
			if (dbConnection != null) {
				DBConnectionManager.getInstance()
						.returnConnection(dbConnection);
			}
			if (res != null) {
				try {
					res.close();
				} catch (SQLException ex) {
					log.error("res GET_APPLISTS_BY_DATE close fail "
							+ ex.getMessage());
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					log.error("pstm GET_APPLISTS_BY_DATE close fail "
							+ ex.getMessage());
				}
			}
		}
		return amountOfListApp;
	}

}
