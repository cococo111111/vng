package vng.zingme.util;

import java.util.Calendar;
import java.util.Date;

public class Period {
	public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private Date startDate = null;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		this.startDate = cal.getTime();
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.set(Calendar.HOUR, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		this.endDate = cal.getTime();
	}
	private Date endDate = null;
	public Period(){
		setStartDate(new Date());
		setEndDate(new Date());
	}
	public Period(Date startDate, Date endDate){
		setStartDate(startDate);
		setEndDate(endDate);
	}
	public String getStartDate(String pattern){
		return DateUtil.formatDate(startDate, pattern);
	}
	public String getEndDate(String pattern){
		return DateUtil.formatDate(endDate, pattern);
	}
}
