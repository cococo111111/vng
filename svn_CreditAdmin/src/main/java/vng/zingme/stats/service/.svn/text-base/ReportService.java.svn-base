package vng.zingme.stats.service;

import java.util.List;

import vng.zingme.payment.thrift.T_ReportSummary;
import vng.zingme.payment.thrift.T_ReportTransaction;
import vng.zingme.stats.model.ReportDetail;
import vng.zingme.stats.model.ReportSummary;
import vng.zingme.stats.model.TranByApp;

/**
 * @author sonhoang
 * 
 */
public interface ReportService {
	/**
	 * @return List<TranByApp>
	 */
	public List<TranByApp> getListAppOfLogingUser();

	/**
	 * @param id
	 * @param fromDate
	 * @param toDate
	 * @return T_ReportSummary
	 */
	public 	T_ReportSummary getPay_Telco(String id, String fromDate, String toDate); 
	

	/**
	 * @param id
	 * @param fromDate
	 * @param toDate
	 * @return List<T_ReportTransaction>
	 */
	public List<T_ReportTransaction> getUserTranxByTranxId(String id, String fromDate, String toDate);

	/**
	 * @param id
	 * @param fromDate
	 * @param toDate
	 * @param status
	 * @return List<T_ReportTransaction>
	 */
	public List<T_ReportTransaction> getUserTranxByUserId(int id, String fromDate, String toDate, int status);
	
	/**
	 * @param txId
	 * @param time
	 * @return List<T_ReportTransaction>
	 */
	public List<T_ReportTransaction> getTranxStatus(String txId, int time);
	
	/**
	 * @param appId
	 * @param fromDate
	 * @param toDate
	 * @param tranxType
	 * @param status
	 * @return List<T_ReportTransaction>
	 */
	public List<T_ReportTransaction> getAppTranxByAppid (String appId, String fromDate, String toDate, int tranxType, int status, int page, int recordPerPage);
	
	/**
	 * @param fromDate
	 * @param toDate
	 * @return  List<ReportSummary>
	 */
	public List<ReportSummary> getReportSummary(String fromDate, String toDate); 
	
	/**
	 * @param fromDate
	 * @param toDate
	 * @return  List<ReportDetail>
	 */
	public List<ReportDetail> getReportDetail(String fromDate, String toDate, List<String> appListPost); 
}
