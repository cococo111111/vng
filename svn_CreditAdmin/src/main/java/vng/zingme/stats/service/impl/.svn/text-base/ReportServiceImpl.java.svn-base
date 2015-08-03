package vng.zingme.stats.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vng.zingme.payment.thrift.T_AppInfo;
import vng.zingme.payment.thrift.T_ReportSummary;
import vng.zingme.payment.thrift.T_ReportTransaction;
import vng.zingme.stats.dao.ReportDao;
import vng.zingme.stats.dao.UserDao;
import vng.zingme.stats.model.ReportDetail;
import vng.zingme.stats.model.ReportSummary;
import vng.zingme.stats.model.TranByApp;
import vng.zingme.stats.service.AppService;
import vng.zingme.stats.service.Common;
import vng.zingme.stats.service.CommonService;
import vng.zingme.stats.service.ReportService;
import vng.zingme.stats.service.UserService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private AppService appService;
	@Autowired
	private CommonService common;
	@Autowired
	private ReportDao reportDao;
	private final static Logger log = Logger.getLogger(ReportServiceImpl.class);

	@Override
	public List<TranByApp> getListAppOfLogingUser() {
		// get ListappName of user
		String userName = userService.getLogingUser();
		List<String> appL = userDao.getAppNameOfUser(userName);

		List<TranByApp> appList = new ArrayList<>();

		try {
			if ("1".equals(appL.get(0)) || "2".equals(appL.get(0))) {
				List<T_AppInfo> allApps = appService
						.get_List_T_AppInfo_Sorted();
				for (T_AppInfo t_AppInfo : allApps) {
					TranByApp a = new TranByApp();
					a.setAppId(t_AppInfo.getAppID());
					a.setAppName(t_AppInfo.getAppName());
					appList.add(a);
				}
			} else {
				for (String appId : appL) {
					String appName = Common.getAppHandler().getAppName(appId);
					TranByApp app = new TranByApp();
					app.setAppId(appId);
					app.setAppName(appName);
					appList.add(app);
				}
			}
		} catch (TException e) {
			log.error("fail when get appName in getListAppOfLogingUser "
					+ e.getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		return appList;
	}

	@Override
	public T_ReportSummary getPay_Telco(String id, String fromDate,
			String toDate) {
		T_ReportSummary zingpay = new T_ReportSummary();
		try {
			zingpay = Common.getReportHandler().summary(id, fromDate, toDate);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return zingpay;
	}

	@Override
	public List<T_ReportTransaction> getUserTranxByTranxId(String id,
			String fromDate, String toDate) {
		List<T_ReportTransaction> tranxs = new ArrayList<T_ReportTransaction>();
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date dFromDate = df.parse(fromDate);
			Date dToDate = df.parse(toDate);
			String sTmpdate;
			List<T_ReportTransaction> tranxsPerMonth = new ArrayList<T_ReportTransaction>();

			int minorYear = dToDate.getYear() - dFromDate.getYear();
			for (int i = minorYear; i >= 0; i--) {
				int checkM = (minorYear == 0) ? dToDate.getMonth()
						- dFromDate.getMonth() : 12 - dFromDate.getMonth();
				while (checkM >= 0) {
					if (checkM == 0) { // thang cuoi
						sTmpdate = toDate;
						tranxsPerMonth = Common.getReportHandler()
								.getTransByUser(0, Long.parseLong(id),
										(fromDate), sTmpdate, 0, 100, 0);
					} else { // lay het thang
						sTmpdate = common.getEndDateOfMonth(fromDate);
						tranxsPerMonth = Common.getReportHandler()
								.getTransByUser(0, Long.parseLong(id),
										(fromDate), sTmpdate, 0, 100, 0);
						fromDate = common.getFirstDateOfNextMonth(sTmpdate);
					}
					tranxs.addAll(tranxsPerMonth);
					--checkM;
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return tranxs;
	}

	@Override
	public List<T_ReportTransaction> getUserTranxByUserId(int userId,
			String fromDate, String toDate, int status) {
		List<T_ReportTransaction> tranxs = new ArrayList<T_ReportTransaction>();
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// get tranxs per month fromdate -> todate
			Date dFromDate = df.parse(fromDate);
			Date dToDate = df.parse(toDate);
			String sTmpdate;
			int minorYear = dToDate.getYear() - dFromDate.getYear();
			for (int i = minorYear; i >= 0; i--) {
				int checkM = (minorYear == 0) ? dToDate.getMonth()
						- dFromDate.getMonth() : 12 - dFromDate.getMonth();
				while (checkM >= 0) {
					List<T_ReportTransaction> tranxsPerMonth = new ArrayList<T_ReportTransaction>();
					if (checkM == 0) {
						sTmpdate = toDate;
						tranxsPerMonth = Common.getReportHandler()
								.getTransByUser(userId, 0, fromDate, sTmpdate,
										0, 100, status);
					} else {
						sTmpdate = common.getEndDateOfMonth(fromDate);
						tranxsPerMonth = Common.getReportHandler()
								.getTransByUser(userId, 0, fromDate, sTmpdate,
										0, 100, status);
						fromDate = common.getFirstDateOfNextMonth(sTmpdate);
					}
					tranxs.addAll(tranxsPerMonth);
					--checkM;
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return tranxs;
	}

	@Override
	public List<T_ReportTransaction> getTranxStatus(String txId, int time) {
		List<T_ReportTransaction> tranxs = new ArrayList<>();
		try {
			tranxs = Common.getReportHandler().getTransStatus(
					Long.parseLong(txId), time);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return tranxs;
	}

	@Override
	public List<T_ReportTransaction> getAppTranxByAppid(String appId,
			String fromDate, String toDate, int tranxType, int status, int page, int recordPerPage) {
		
		List<T_ReportTransaction> tranxs = new ArrayList<>();
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// get tranxs per month fromdate -> todate
			Date dFromDate = df.parse(fromDate);
			Date dToDate = df.parse(toDate);
			int checkM = dFromDate.getMonth() - dToDate.getMonth();
			String sTmpdate;
			List<T_ReportTransaction> tranxsPerMonth = new ArrayList<T_ReportTransaction>();
			while (checkM >= 0) {
				if (checkM == 0) {
					sTmpdate = toDate;
					tranxsPerMonth = Common.getReportHandler().getTransByApp(
							appId, fromDate, sTmpdate, tranxType, page*recordPerPage, recordPerPage,
							status);
				} else {
					sTmpdate = common.getEndDateOfMonth(fromDate);
					tranxsPerMonth = Common.getReportHandler().getTransByApp(
							appId, fromDate, sTmpdate, tranxType, page*recordPerPage, recordPerPage,
							status);
					fromDate = common.getFirstDateOfNextMonth(sTmpdate);
				}

				tranxs.addAll(tranxsPerMonth);
				--checkM;
			}
		} catch (Exception e) {
			log.error("format date error " + e.getMessage());
		}
		return tranxs;
	}

	@Override
	public List<ReportSummary> getReportSummary(String fromDate, String toDate) {
		// -1 date for get remainbalancelist
		// because closingBalance of today = openingBalance of tomorrow
		List<ReportSummary> reportSummaryList = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dFromDate = df.parse(fromDate);
			Date dToDate = df.parse(toDate);

			Calendar cal = Calendar.getInstance();
			cal.setTime(dFromDate);
			cal.add(Calendar.DATE, -1);

			String openningDate = df.format(cal.getTime());
			Date dOpenningDate = df.parse(openningDate);

			HashMap<String, String> remainBalanceList = reportDao
					.getRemainBalanceByDate(openningDate, toDate);

			List<T_ReportSummary> imcomeList = reportDao.getListDailyReport(
					"zing", fromDate, toDate);
			List<String> payrollList = reportDao.getPayrollSummaryByDate(
					fromDate, toDate);

			int i = 0;
			while (dFromDate.getTime() <= dToDate.getTime()) {
				ReportSummary reportSummary = new ReportSummary();
				reportSummary.setDate(df.format(dFromDate));
				reportSummary.setIncome(String.valueOf(imcomeList.get(i)
						.getTotalMoney()));

				if (remainBalanceList.containsKey(df.format(dOpenningDate)
						+ " 00:00:00.0")) {
					reportSummary.setOpeningBalance(remainBalanceList.get(df
							.format(dOpenningDate) + " 00:00:00.0"));
				} else {
					reportSummary.setOpeningBalance("");
				}
				if (remainBalanceList.containsKey(df.format(dFromDate)
						+ " 00:00:00.0")) {
					reportSummary.setClosingBalance(remainBalanceList.get(df
							.format(dFromDate) + " 00:00:00.0"));
				} else {
					reportSummary.setClosingBalance("");
				}

				reportSummary.setPayroll(payrollList.get(i));
				reportSummaryList.add(reportSummary);

				i++;
				dFromDate.setDate(dFromDate.getDate() + 1);
				dOpenningDate.setDate(dOpenningDate.getDate() + 1);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return reportSummaryList;
	}

	@Override
	public List<ReportDetail> getReportDetail(String fromDate, String toDate,
			List<String> appList) {
		List<ReportDetail> reportDetailList = new ArrayList<>();
		try {
			// format date - yyyy-MM-dd
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date dFromDate = df.parse(fromDate);
			Date dToDate = df.parse(toDate);

			Calendar cal = Calendar.getInstance();
			cal.setTime(dFromDate);
			cal.add(Calendar.DATE, -1);
			String openningDate = df.format(cal.getTime());
			Date dOpenningDate = df.parse(openningDate);
			// get remainBalance List (ton dau ki, ton cuoi ki): lay truoc 1
			// ngay
			HashMap<String, String> remainBalanceList = reportDao
					.getRemainBalanceByDate(openningDate, toDate);

			// get incomeList (tong nhap)
			List<T_ReportSummary> imcomeList = reportDao.getListDailyReport(
					"zing", fromDate, toDate);
			// get xuat trong ki
			// get amount of user logging list app
			List<ReportDetail> amountOfUserLoggingListApp = reportDao
					.getAmountOfListAppByDate(fromDate, toDate, appList);

			// get tong xuat
			List<String> payrollList = reportDao.getPayrollSummaryByDate(
					fromDate, toDate);

			int i = 0;
			while (dFromDate.getTime() <= dToDate.getTime()) {
				if (!remainBalanceList.isEmpty()
				/*
				 * && i < imcomeList.size() && i < remainBalanceList.size()
				 */) {
					ReportDetail report = new ReportDetail();
					// 1.3 set report AppAmount per app for all apps
					List<TranByApp> apps = new ArrayList<>();

					for (String app : appList) {
						if (!"zing".equals(app) && !"admin".equals(app)) {
							TranByApp a = new TranByApp();
							a.setAppId(app);
							a.setAmount("");
							for (ReportDetail reportDetail : amountOfUserLoggingListApp) {
								if (df.parse(reportDetail.getDate()).equals(
										dFromDate)) {
									if (app.equals(reportDetail.getAppId())) {
										a.setAmount(reportDetail.getAmount());
										break;
									}
								}
							}
							apps.add(a);
						}
					}

					report.setApps(apps);
					report.setDate(df.format(dFromDate));
					report.setIncome(String.valueOf(imcomeList.get(i)
							.getTotalMoney()));

					if (remainBalanceList.containsKey(df.format(dOpenningDate)
							+ " 00:00:00.0")) {
						report.setOpeningBalance(remainBalanceList.get(df
								.format(dOpenningDate) + " 00:00:00.0"));
					} else {
						report.setOpeningBalance("");
					}
					if (remainBalanceList.containsKey(df.format(dFromDate)
							+ " 00:00:00.0")) {
						report.setClosingBalance(remainBalanceList.get(df
								.format(dFromDate) + " 00:00:00.0"));
					} else {
						report.setClosingBalance("");
					}

					report.setPayroll(payrollList.get(i));

					reportDetailList.add(report);
				}
				i++;
				dFromDate.setDate(dFromDate.getDate() + 1);
				dOpenningDate.setDate(dOpenningDate.getDate() + 1);
			}
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		return reportDetailList;
	}
}
