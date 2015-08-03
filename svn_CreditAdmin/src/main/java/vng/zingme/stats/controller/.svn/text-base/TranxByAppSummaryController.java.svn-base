package vng.zingme.stats.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vng.zingme.payment.thrift.T_AppInfo;
import vng.zingme.payment.thrift.T_ReportSummary;
import vng.zingme.stats.dao.AppDao;
import vng.zingme.stats.model.App;
import vng.zingme.stats.model.TranByApp;
import vng.zingme.stats.model.TranByAppSummary;
import vng.zingme.stats.service.AppService;
import vng.zingme.stats.service.CommonService;

@Controller
@RequestMapping("/reportsum")
public class TranxByAppSummaryController {

	@Autowired
	private AppService appService;
	@Autowired
	private AppDao appDao;
	@Autowired
	private CommonService common;

	@RequestMapping(value = "/tranxbyappsummary")
	public ModelAndView tranxbyappSummary(
			@ModelAttribute("tranxbyapp") TranByApp tranxByApp) {

		List<App> apps = new ArrayList<>();
		List<T_AppInfo> allApps = appService.get_List_T_AppInfo_Sorted();
		for (T_AppInfo t_App : allApps) {
			App a = new App();
			a.setAppName(t_App.getAppName());
			a.setAppId(t_App.getAppID());

			apps.add(a);
		}
		List<App> appChosenList = new ArrayList<>();
		if ("0".equals(tranxByApp.getAppId()) || tranxByApp.getAppId() == null) { // fixxxxxx
			appChosenList.addAll(apps);
		} else {
			App appchosen = new App();
			appchosen.setAppId(tranxByApp.getAppId());
			appChosenList.add(appchosen);
		}

		String fromDate = common.getYesTerDay(tranxByApp.getFromDate());
		String toDate = common.getYesTerDay(tranxByApp.getToDate());
		String fromDateView = fromDate; 
		String toDateView = fromDate; 
		fromDate = fromDate + " 00:00:00";
		toDate = toDate + " 23:59:59";

		List<TranByAppSummary> subResult = new ArrayList<TranByAppSummary>();
		if ("0".equals(tranxByApp.getStatus())) { // radio 0: khoang thoi gian

			for (App aa : appChosenList) {
				T_ReportSummary result = appDao.summary(aa.getAppId(),
						fromDate, toDate);
				// set lai cai T_Report_Summary
				if (result != null) {
					TranByAppSummary tranByAppSummary = TranByAppSummary
							.convert2TranByAppsSummary(result);
					subResult.add(tranByAppSummary);
				}
			}

		} else { // radio 1: ngay
			for (App aa : appChosenList) {
				List<T_ReportSummary> result2 = appDao.summaryDaily(
						aa.getAppId(), fromDate, toDate);

				for (T_ReportSummary result : result2) {
					// set lai cai T_Report_Summary
					if (result.agentID != null && result.totalMoney != 0) {
						TranByAppSummary tranByAppSummary = TranByAppSummary
								.convert2TranByAppsSummary(result);
						subResult.add(tranByAppSummary);
					}
				}
			}
		}
		ModelAndView mav = new ModelAndView("/jsp/tranxbyappsummary.jsp");
		ModelMap model = mav.getModelMap();

		model.addAttribute("subResult", subResult);
		model.addAttribute("apps", apps);
		model.addAttribute("fromDate", fromDateView);
		model.addAttribute("toDate", toDateView);

		return mav;
	}

}
