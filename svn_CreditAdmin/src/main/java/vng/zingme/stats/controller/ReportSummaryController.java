package vng.zingme.stats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vng.zingme.stats.model.ReportDetail;
import vng.zingme.stats.model.ReportSummary;
import vng.zingme.stats.service.AppService;
import vng.zingme.stats.service.CommonService;
import vng.zingme.stats.service.ReportService;

@Controller
@RequestMapping("/")
public class ReportSummaryController {
	@Autowired
	private ReportService reportService;
	@Autowired
	private AppService appService;
	@Autowired
	private CommonService common;

	@RequestMapping(value = "report/reportsummary")
	public ModelAndView getReportSummary(
			@ModelAttribute("report") ReportDetail reportDetail) {
		String toDate = common.getToday(reportDetail.getToDate());
		String fromDate = common.getYesTerDay(reportDetail.getFromDate());

		List<ReportSummary> reportSummaryList = reportService.getReportSummary(
				fromDate, toDate);

		ModelAndView mav = new ModelAndView("/jsp/reportsummary.jsp");
		ModelMap model = mav.getModelMap();
		model.addAttribute("reportSummaryList", reportSummaryList);
		model.addAttribute("fromDate", fromDate);
		model.addAttribute("toDate", toDate);
		return mav;

	}

}
