package vng.zingme.stats.model;

import vng.zingme.payment.thrift.T_ReportTransaction;

public class TranxDetail {
	private String lastUpdate;
	private String resultCode;
	private String message;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static TranxDetail convertT_reportTranx2TranxUserDetail(	T_ReportTransaction t_report) {
		String status = "";
		switch (t_report.txStatus) {
		case 1:
			status = "Khởi tạo giao dịch";
			break;
		case 3: // gd dang xu ly
			status = "Chuẩn bị gửi REST request";
			break;
		case 2:
			if (t_report.txType == 2 || t_report.txType == 101
					|| t_report.txType == 102 || t_report.txType == 103
					|| t_report.txType == 201 || t_report.txType == 202) {
				status = "Đã cập nhật số tiền vào tài khoản";
			}
			break;
		case 101:
			status = "Game/app trả lời mã thành công";
			break;
		case 102:
			status = "Gửi request REST cho game/app timeout";
			break;
		case -103:
			status = "Game/app trả lời mã thất bại";
			break;
		case -104:
			status = "Lỗi mạng khi gửi request REST cho game/app";
			break;
		}
		TranxDetail txu = new TranxDetail();
		txu.setResultCode(t_report.resultCode == 32767 ? "" : String
				.valueOf(t_report.resultCode));
		txu.setStatus(status);
		txu.setMessage(t_report.message);
		txu.setLastUpdate(t_report.lastUpdate);
		return txu;
	}
	
}
