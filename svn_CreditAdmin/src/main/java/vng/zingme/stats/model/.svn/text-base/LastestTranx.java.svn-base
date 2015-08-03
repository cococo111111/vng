package vng.zingme.stats.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import vng.zingme.payment.thrift.T_Transaction;
import vng.zingme.stats.service.Common;

public class LastestTranx {

	private String tranxTime;
	private String tranxType;
	private String tranxId;
	private String currentBalance;
	private String amount;
	private String agentId;
	private String tranxStatus;
	private String uId;
	private String userName;
	private String tranxDescription;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getTranxDescription() {
		return tranxDescription;
	}

	public void setTranxDescription(String tranxDescription) {
		this.tranxDescription = tranxDescription;
	}

	public String getTranxTime() {
		return tranxTime;
	}

	public void setTranxTime(String tranxTime) {
		this.tranxTime = tranxTime;
	}

	public String getTranxType() {
		return tranxType;
	}

	public void setTranxType(String tranxType) {
		this.tranxType = tranxType;
	}

	public String getTranxId() {
		return tranxId;
	}

	public void setTranxId(String tranxId) {
		this.tranxId = tranxId;
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

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getTranxStatus() {
		return tranxStatus;
	}

	public void setTranxStatus(String tranxStatus) {
		this.tranxStatus = tranxStatus;
	}

	public static LastestTranx convertT_Tranx2LastestTranx(T_Transaction tranx,
			int userId) {
		LastestTranx lastestTranx = new LastestTranx();
		// format Date code
		DateFormat df = new SimpleDateFormat("HH:mm, dd/MM/yyyy");
		String lastedDate = df.format(new Date(tranx.getTxTime() * 1000L)) + "";

		lastestTranx.setTranxTime(lastedDate);
		String detail = "";
		String type = Common.convertTranxType(tranx.getTxType(), tranx.getAmount());
		
		switch (tranx.getTxType()) {
		case 2:
			type = "Admin";
			detail = tranx.description;
			break;
		case 101:
			detail = "'Chuyển <span class='zc-noiconzc'> "
					+ new Double(tranx.amount).intValue()
					+ "</span> Zing Xu vào tài khoản";
			break;
		case 102:
			detail = "Được Chuyển <span class='zc-noiconzc'> "
					+ new Double(tranx.amount).intValue()
					+ "</span> Zing Xu vào tài khoản";
			break;
		case 103:
			detail = "Được tặng <span class='zc-noiconzc'> "
					+ new Double(tranx.amount).intValue()
					+ "</span> Zing Xu vào tài khoản";
			break;
		case 201:
			detail = "Mua " + tranx.itemQuantities + " <b>" + tranx.itemNames
					+ "</b> tốn <span class='zc-noiconzc'>"
					+ new Double(tranx.amount).intValue() + "</span> Zing Xu";
			break;
		case 202:
			detail = "Được hoàn lại <span class='zc-noiconzc'> "
					+ new Double(tranx.amount).intValue() + "</span> Zing Xu";
			break;
		}
		String status = "";
		switch (tranx.responseCode) {
		case 1:
		case 3: // gd dang xu ly
			status = "Đang Xử Lý";
			break;
		case 2:
			if (tranx.txType == 2 || tranx.txType == 101 || tranx.txType == 102
					|| tranx.txType == 103 || tranx.txType == 202) {
				status = "Thành Công";
			}
			if (tranx.txType == 201) {
				status = "Đang Xử Lý";
			} else {
				detail = tranx.description;
			}
			break;
		case 101:
		case 102:
			status = "Thành Công";
			break;
		case -103:
		case -104:
			detail = tranx.description;
			status = "Gd Thất Bại";
			break;

		}
		lastestTranx.setTranxType(type);
		lastestTranx.setTranxId(String.valueOf(tranx.getTxID()));
		lastestTranx
				.setCurrentBalance(String.valueOf(tranx.getCurrentBalance()));
		lastestTranx.setAgentId(String.valueOf(tranx.getAgentID()));
		lastestTranx.setAmount(String.valueOf(tranx.getAmount()));
		lastestTranx.setTranxStatus(status);
		lastestTranx.setTranxDescription(detail);
		lastestTranx.setuId(String.valueOf(userId));
		return lastestTranx;
	}

}
