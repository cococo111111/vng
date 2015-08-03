package vng.zingme.stats.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

import vng.zingme.payment.thrift.T_ReportTransaction;
import vng.zingme.stats.service.Common;

public class TranxUser {

	@NotNull
	private String txStatus;
	private String txTime;
	private String txType;
	private String txId;
	private String currentBalance;
	private String amount;
	private String itemName;
	private String itemPrices;
	private String itemQuantities;
	private String agentId;
	private String refId;
	private String txDescription;

	public String getTxDescription() {
		return txDescription;
	}

	public void setTxDescription(String txDescription) {
		this.txDescription = txDescription;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getTxStatus() {
		return txStatus;
	}

	public void setTxStatus(String txStatus) {
		this.txStatus = txStatus;
	}

	public String getTxTime() {
		return txTime;
	}

	public void setTxTime(String txTime) {
		this.txTime = txTime;
	}

	public String getTxType() {
		return txType;
	}

	public void setTxType(String txType) {
		this.txType = txType;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemPrices() {
		return itemPrices;
	}

	public void setItemPrices(String itemPrices) {
		this.itemPrices = itemPrices;
	}

	public String getItemQuantities() {
		return itemQuantities;
	}

	public void setItemQuantities(String itemQuantities) {
		this.itemQuantities = itemQuantities;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public TranxUser() {
		super();
	}

	public static TranxUser convertT_reportTranx2TranxUser(
			T_ReportTransaction t_report) {
		String type = Common.convertTranxType(t_report.getTxType(), t_report.getAmount());
		TranxUser txu = new TranxUser();
		DateFormat df = new SimpleDateFormat("HH:mm, dd/MM/yyyy");
		txu.setTxTime(df.format(new Date(t_report.getTxTime() * 1000L)) + "");
		txu.setTxType(type);
		txu.setTxId(t_report.getTxID() + "");
		txu.setCurrentBalance(String.valueOf(t_report.getCurrentBalance()));
		txu.setAmount(t_report.getAmount() + "");
		txu.setItemName(t_report.getItemNames() == null ? "" : String
				.valueOf(t_report.getItemNames()));
		txu.setItemPrices(t_report.getItemPrices() == null ? "" : String
				.valueOf(t_report.getItemPrices()));
		txu.setItemQuantities(t_report.getItemQuantities() == null ? ""
				: String.valueOf(t_report.getItemQuantities()));
		txu.setAgentId(t_report.getAgentID() == null ? "" : String
				.valueOf(t_report.getAgentID()));
		// txu.setTxDescription(String.valueOf(t_report.getResultCode()));
		txu.setRefId(t_report.getRefID() == null ? "" : String.valueOf(t_report
				.getRefID()));
		return txu;
	}		

}
