package vng.zingme.stats.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import vng.zingme.stats.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {

	private static Logger log = Logger.getLogger(CommonServiceImpl.class);

	@Override
	public String getEndDateOfMonth(String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date toDate = null;
		try {
			toDate = df.parse(date);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(toDate);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String result = sdf.format(cal.getTime()) + " 23:59:59";
		return result;
	}

	@Override
	public String getFirstDateOfNextMonth(String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date toDate = null;
		try {
			toDate = df.parse(date);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(toDate);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String result = sdf.format(cal.getTime()) + " 00:00:00";
		return result;
	}

	@Override
	public String getYesTerDay(String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		if (date == null || "".equals(date)) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			date = df.format(cal.getTime());
		}
		return date;
	}

	@Override
	public String getToday(String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		if (date == null || "".equals(date)) {
			Calendar cal = Calendar.getInstance();
			date = df.format(cal.getTime());
		}
		return date;
	}

	@Override
	public String getCurrentTime(String date) {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		if (date == null || "".equals(date)) {
			Calendar cal = Calendar.getInstance();
			date = sdfDate.format(cal.getTime());
		}
		return date;
	}

	@Override
	public String getYesTerDay2(String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		if (date == null || "".equals(date)) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			date = df.format(cal.getTime()) + " 00:00";
		}
		return date;
	}

	@Override
	public String getFirstDateOfMonth(String date) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		if (date == null || "".equals(date)) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, 1);
			date = df.format(cal.getTime()) + " 00:00";
		}
		return date;

	}

	@Override
	public long convertDate2Long(String format, String time) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		long iTime = 0;
		try {
			iTime = df.parse(time).getTime() / 1000;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return iTime;
	}

/*	@Override
	public void exportCvsFile(String fileName, List<ReportDetail> data) {
		String csv = fileName;
		CSVWriter writer;
		try {
			writer = new CSVWriter(new FileWriter(csv));
			for (ReportDetail record : data) {
				writer.writeNext(new String[] { record.getDate(),
						record.getOpeningBalance(), record.getIncome(),
						record.getPayroll(), record.getClosingBalance() });
			}
			writer.flush();
			writer.close();

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}*/

}