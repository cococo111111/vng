package vng.zingme.stats.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import vng.zingme.payment.thrift.T_ReportTransaction;
import vng.zingme.stats.service.Common;

public class TranxByAppDetail {

	private String appId;
	private String tranxTime;
	private String userName;
	private String userId;
	private String tranxType;
	private String amount;
	private String tranxId;
	private String itemName;
	private String itemPrice;
	private String itemQuatities;
	private String refId;
	private int page; 

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTranxTime() {
		return tranxTime;
	}

	public void setTranxTime(String tranxTime) {
		this.tranxTime = tranxTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTranxType() {
		return tranxType;
	}

	public void setTranxType(String tranxType) {
		this.tranxType = tranxType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTranxId() {
		return tranxId;
	}

	public void setTranxId(String tranxId) {
		this.tranxId = tranxId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemQuatities() {
		return itemQuatities;
	}

	public void setItemQuatities(String itemQuatities) {
		this.itemQuatities = itemQuatities;
	}

	public static TranxByAppDetail cover2TranxByAppDetail(
			T_ReportTransaction t_report) {
		TranxByAppDetail tranxByApp = new TranxByAppDetail();
		tranxByApp.setAmount(String.valueOf(t_report.getAmount()));
		tranxByApp.setAppId(t_report.getAgentID());
		tranxByApp.setItemName(t_report.getItemNames());
		tranxByApp.setItemPrice(t_report.getItemPrices());
		tranxByApp.setItemQuatities(t_report.getItemQuantities());
		tranxByApp.setTranxId(String.valueOf(t_report.getTxID()));

		DateFormat df = new SimpleDateFormat("HH:mm, dd/MM/yyyy");
		String sTranxTime = df.format(new Date(t_report.getTxTime() * 1000L))
				+ "";

		tranxByApp.setTranxTime(sTranxTime);
		String type = Common.convertTranxType(t_report.getTxType(), t_report.getAmount());
		tranxByApp.setTranxType(type);
		tranxByApp.setUserName(t_report.getUserName());
		tranxByApp.setUserId(String.valueOf(t_report.getUserID()));
		tranxByApp.setRefId(t_report.getRefID());
		return tranxByApp;
	}
}
