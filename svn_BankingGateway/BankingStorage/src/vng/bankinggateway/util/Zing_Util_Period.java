package vng.bankinggateway.util;

import java.util.Date;

public class Zing_Util_Period {

	public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private Date start;

	public Date getStart() {
		return start;
	}//abc

	public void setStart(Date start) {
		this.start = start;
	}

	
	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	private Date end;

	public Zing_Util_Period(Date start, Date end) {
		this.start = start;
		this.end = end;
	}

}