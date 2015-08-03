package vng.zingme.stats.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vng.zingme.stats.model.ReportDetail;
import vng.zingme.stats.model.TranByApp;
import vng.zingme.stats.service.AppService;
import vng.zingme.stats.service.CommonService;
import vng.zingme.stats.service.ReportService;

@Controller
@RequestMapping("/")
public class ReportDetailController {
	@Autowired
	private CommonService common;
	@Autowired
	private AppService appService;
	@Autowired
	private ReportService reportService;

	@RequestMapping(value = "report/reportdetail")
	public ModelAndView getReportdetail(
			@ModelAttribute("report") ReportDetail reportDetail) {

		// set Date
		String toDate = common.getYesTerDay(reportDetail.getToDate());
		String fromDate = common.getYesTerDay(reportDetail.getFromDate());

		List<TranByApp> appListGet = appService.getListAppOfLoggingUser();
		List<String> appList = new ArrayList<>();
		for (TranByApp tranByApp : appListGet) {
			appList.add(tranByApp.getAppId());
		}

		List<ReportDetail> reportDetailList = reportService.getReportDetail(
				fromDate, toDate,appList);
		// set list app post (-zing , - admin)
		List<TranByApp> appListPost = new ArrayList<>();
		for (TranByApp t : appListGet) {
			if (!"zing".equals(t.getAppId()) && !"admin".equals(t.getAppId())) {
				appListPost.add(t);
			}
		}

		int count = appListPost.size() + 1;
		// map model and view
		ModelAndView mav = new ModelAndView("/jsp/reportdetail.jsp");
		ModelMap model = mav.getModelMap();
		model.addAttribute("fromDate", fromDate);
		model.addAttribute("toDate", toDate);
		model.addAttribute("reportDetailList", reportDetailList);
		model.addAttribute("apps", appListPost);
		model.addAttribute("count", count);
		return mav;

	}
}