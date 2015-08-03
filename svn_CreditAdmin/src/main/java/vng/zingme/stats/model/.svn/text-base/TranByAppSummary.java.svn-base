package vng.zingme.stats.model;

import vng.zingme.payment.thrift.T_ReportSummary;

public class TranByAppSummary {

	private String agentID;
	private String endTime;
	private String startTime;
	private String totalDiffUser;
	private String totalMoney;
	private String totalTransaction;
	private String totalTransactionFail;
	private String totalTransactionNetError;
	private String totalTransactionSuccess;
	private String totalTransactionTimeOut;
	private String totalTransactionPending;

	public String getTotalTransactionPending() {
		return totalTransactionPending;
	}

	public void setTotalTransactionPending(String totalTransactionPending) {
		this.totalTransactionPending = totalTransactionPending;
	}

	public TranByAppSummary() {
		super();
	}

	public String getAgentID() {
		return agentID;
	}

	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTotalDiffUser() {
		return totalDiffUser;
	}

	public void setTotalDiffUser(String totalDiffUser) {
		this.totalDiffUser = totalDiffUser;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getTotalTransaction() {
		return totalTransaction;
	}

	public void setTotalTransaction(String totalTransaction) {
		this.totalTransaction = totalTransaction;
	}

	public String getTotalTransactionFail() {
		return totalTransactionFail;
	}

	public void setTotalTransactionFail(String totalTransactionFail) {
		this.totalTransactionFail = totalTransactionFail;
	}

	public String getTotalTransactionNetError() {
		return totalTransactionNetError;
	}

	public void setTotalTransactionNetError(String totalTransactionNetError) {
		this.totalTransactionNetError = totalTransactionNetError;
	}

	public String getTotalTransactionSuccess() {
		return totalTransactionSuccess;
	}

	public void setTotalTransactionSuccess(String totalTransactionSuccess) {
		this.totalTransactionSuccess = totalTransactionSuccess;
	}

	public String getTotalTransactionTimeOut() {
		return totalTransactionTimeOut;
	}

	public void setTotalTransactionTimeOut(String totalTransactionTimeOut) {
		this.totalTransactionTimeOut = totalTransactionTimeOut;
	}

	public static TranByAppSummary convert2TranByAppsSummary(
			T_ReportSummary t_report) {
		TranByAppSummary tranx = new TranByAppSummary();
		tranx.setAgentID(t_report.getAgentID());
		
		tranx.setEndTime(t_report.getEndTime());
		tranx.setStartTime(t_report.getStartTime());
		tranx.setTotalMoney(String.valueOf(t_report.getTotalMoney()));
		tranx.setTotalTransaction(String.valueOf(t_report.getTotalTransaction()));

		tranx.setTotalDiffUser(String
				.valueOf((t_report.getTotalDiffUser() == 0) ? "-" : t_report
						.getTotalDiffUser()));
		tranx.setTotalTransactionFail(String.valueOf((t_report
				.getTotalTransactionFail() == 0) ? "-" : t_report
				.getTotalTransactionFail()));
		tranx.setTotalTransactionNetError(String.valueOf((t_report
				.getTotalTransactionNetError() == 0) ? "-" : t_report
				.getTotalTransactionNetError()));
		tranx.setTotalTransactionSuccess(String.valueOf((t_report
				.getTotalTransactionSuccess() == 0) ? "-" : t_report
				.getTotalTransactionSuccess()));
		tranx.setTotalTransactionTimeOut(String.valueOf((t_report
				.getTotalTransactionTimeOut() == 0) ? "-" : t_report
				.getTotalTransactionTimeOut()));

		int totalTranxPending = t_report.getTotalTransaction()
				- (t_report.getTotalTransactionFail()
						+ t_report.getTotalTransactionNetError()
						+ t_report.getTotalTransactionSuccess() + t_report
							.getTotalTransactionTimeOut());
		tranx.setTotalTransactionPending((totalTranxPending == 0) ? "-"
				: String.valueOf(totalTranxPending));

		return tranx;
	}
}
