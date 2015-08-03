package vng.zingme.stats.model;

public class PaySummary {
	private String date;
	private String telcoCard;
	private String zingPay;
	private String sum;
	private String telcoPercent;
	private String zingPayPercent;
	private String sms;
	private String reconcile;
	private String admin;
	private String smsPercent;
	private String adminPercent;
	private String reconcilePercent;
	// for get:
	private String fromDate;
	private String toDate;

	public String getTelcoPercent() {
		return telcoPercent;
	}

	public void setTelcoPercent(String telcoPercent) {
		this.telcoPercent = telcoPercent;
	}

	public String getZingPayPercent() {
		return zingPayPercent;
	}

	public void setZingPayPercent(String zingPayPercent) {
		this.zingPayPercent = zingPayPercent;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTelcoCard() {
		return telcoCard;
	}

	public void setTelcoCard(String telcoCard) {
		this.telcoCard = telcoCard;
	}

	public String getZingPay() {
		return zingPay;
	}

	public void setZingPay(String zingPay) {
		this.zingPay = zingPay;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public String getReconcile() {
		return reconcile;
	}

	public void setReconcile(String reconcile) {
		this.reconcile = reconcile;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getSmsPercent() {
		return smsPercent;
	}

	public void setSmsPercent(String smsPercent) {
		this.smsPercent = smsPercent;
	}

	public String getAdminPercent() {
		return adminPercent;
	}

	public void setAdminPercent(String adminPercent) {
		this.adminPercent = adminPercent;
	}

	public String getReconcilePercent() {
		return reconcilePercent;
	}

	public void setReconcilePercent(String reconcilePercent) {
		this.reconcilePercent = reconcilePercent;
	}
	

}
