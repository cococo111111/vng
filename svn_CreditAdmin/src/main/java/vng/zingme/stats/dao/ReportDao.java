package vng.zingme.stats.dao;

import java.util.HashMap;
import java.util.List;

import vng.zingme.payment.thrift.T_ReportSummary;
import vng.zingme.stats.model.ReportDetail;
import vng.zingme.stats.model.TranByApp;

/**
 * @author sonhoang
 */
public interface ReportDao {

	/**
	 * @param appId
	 * @param startDate
	 * @param endDate
	 * @return List<T_ReportSummary> or null
	 */
	public List<T_ReportSummary> getListDailyReport(String appId,
			String startDate, String endDate);

	/**
	 * @param startDate
	 * @param endDate
	 * @return List<T_ReportSummary> or null
	 */
	public List<T_ReportSummary> getListReportByDate(String startDate,
			String endDate);

	/**
	 * @param startDate
	 * @param endDate
	 * @return List<ReportSummary> or null
	 */
	public HashMap<String, String> getRemainBalanceByDate(String startDate, String endDate);

	/**
	 * @param startDate
	 * @param endDate
	 * @return List<String> or null
	 */
	public List<String> getPayrollSummaryByDate(String startDate, String endDate);

	/**
	 * @param startDate
	 * @param endDate
	 * @param appListGet
	 * @return
	 */
	public List<ReportDetail> getAmountOfListAppByDate(String startDate,
			String endDate, List<String> appListGet);
}
