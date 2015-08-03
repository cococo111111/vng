package vng.zingme.stats.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
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
public class ReportZingxuController {
	@Autowired
	private AppService appService;
	@Autowired
	private CommonService common;
	@Autowired
	private ReportService reportService;

	private static final Logger log = Logger
			.getLogger(ReportZingxuController.class);

	@RequestMapping(value = "report/reportzingxu")
	public ModelAndView reportZingxuGet(
			@ModelAttribute("report") ReportDetail reportDetail) {
		String toDate = common.getYesTerDay(reportDetail.getToDate());
		String fromDate = common.getYesTerDay(reportDetail.getFromDate());

		List<TranByApp> appListGet = appService.getListAppOfLoggingUser();
		List<String> appListPost = new ArrayList<>();

		if (reportDetail.getAppIdGetList() == null) {
			for (TranByApp t : appListGet) {
				if (!"zing".equals(t.getAppId())
						&& !"admin".equals(t.getAppId())) {
					appListPost.add(t.getAppId());
				}
			}
		} else {
			for (String app : reportDetail.getAppIdGetList()) {
				if ("0".equals(app)) {
					for (TranByApp t : appListGet) {
						if (!"zing".equals(t.getAppId())
								&& !"admin".equals(t.getAppId())) {
							appListPost.add(t.getAppId());
						}
					}
					break;
				} else {
					appListPost.add(app);
				}
			}
		}
		List<ReportDetail> reportDetailList = reportService.getReportDetail(
				fromDate, toDate, appListPost);

		// map attribute
		ModelAndView mav = new ModelAndView("/jsp/reportzingxu.jsp");
		ModelMap model = mav.getModelMap();
		model.addAttribute("reportList", reportDetailList);
		model.addAttribute("appposts", appListPost);
		model.addAttribute("count", appListPost.size());
		model.addAttribute("apps", appListGet);
		model.addAttribute("fromDate", fromDate);
		model.addAttribute("toDate", toDate);
		return mav;

	}

}
