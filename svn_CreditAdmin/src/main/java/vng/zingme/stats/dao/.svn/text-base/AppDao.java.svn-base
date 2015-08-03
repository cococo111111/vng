package vng.zingme.stats.dao;

import java.util.List;

import vng.zingme.payment.thrift.T_ReportSummary;

/**
 * @author sonhoang
 * 
 */
public interface AppDao {

	/**
	 * @param appID
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public T_ReportSummary summary(String appID, String startDate,
			String endDate);

	/**
	 * @param appID
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<T_ReportSummary> summaryDaily(String appID, String startDate,
			String endDate);
}
