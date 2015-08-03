package vng.zingme.stats.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vng.zingme.payment.thrift.T_AppInfo;
import vng.zingme.payment.thrift.T_ReportTransaction;
import vng.zingme.stats.model.App;
import vng.zingme.stats.model.TranByApp;
import vng.zingme.stats.model.TranxByAppDetail;
import vng.zingme.stats.model.TranxDetail;
import vng.zingme.stats.service.AppService;
import vng.zingme.stats.service.CommonService;
import vng.zingme.stats.service.ReportService;

@Controller
@RequestMapping("/")
public class TranxByAppController {

	// private static Logger log = Logger.getLogger(TranxByAppController.class);

	@Autowired
	private AppService appService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private ReportService reportService;

	@RequestMapping(value = "reportdetail/tranxbyapp")
	public ModelAndView tranxbyapp(
			SecurityContextHolderAwareRequestWrapper request,@ModelAttribute("trxbyapp") TranByApp tranByApp) {
		ModelAndView mav = new ModelAndView("/jsp/tranxbyapp.jsp");
		ModelMap model = mav.getModelMap();
		//1. get list appName. 
		List<App> apps = new ArrayList<>();
		if (request.isUserInRole("ADMIN")) {
			List<T_AppInfo> appList = appService.get_List_T_AppInfo_Sorted();
			for (T_AppInfo app : appList) {
				App a = new App();
				a.setAppId(app.getAppID());
				a.setAppName(app.getAppName());
				apps.add(a);
			}
		} else {
			List<TranByApp> appOfLoggingUser = appService
					.getListAppOfLoggingUser();

			for (TranByApp app : appOfLoggingUser) {
				if ("zing".equals(app.getAppId())
						|| "admin".equals(app.getAppId())
						|| "ZingCreditsPlus".equals(app.getAppId())) {
					continue;
				}
				App a = new App();
				a.setAppId(app.getAppId());
				a.setAppName(app.getAppName());
				apps.add(a);
			}
		}
		model.addAttribute("apps", apps);
		//2. date  
		String sDate =  commonService.getFirstDateOfMonth(tranByApp.getFromDate());
		String tDate = commonService.getCurrentTime(tranByApp.getToDate());
		String sfromDate = sDate + ":00";
		String stoDate = tDate + ":59";
		model.addAttribute("fromDate", sDate);
		model.addAttribute("toDate", tDate);
		
		//3. status , tranxType
		//4. getTable 
		int recordPerPage = (tranByApp.getRecordPerPage() == null)?100: Integer.parseInt(tranByApp.getRecordPerPage());
		int tranxType = (tranByApp.getTranxType()==null)?0: Integer.parseInt(tranByApp.getTranxType());
		int status = (tranByApp.getStatus() == null)?0: Integer.parseInt(tranByApp.getStatus());
		int page = (tranByApp.getPage() == null)?0: Integer.parseInt(tranByApp.getPage());
		
		String appID = (tranByApp.getAppId() == null)?"2lover": tranByApp.getAppId();
		List<T_ReportTransaction> tranxs = reportService.getAppTranxByAppid(	
				appID, sfromDate, stoDate, tranxType,	status, page, recordPerPage);
		if (tranxs.isEmpty() && page >0) {
			page = 0; 
		}
		
		List<TranxByAppDetail> tranxByAppDetail = new ArrayList<>();
		
		for (T_ReportTransaction t_report : tranxs) {
			TranxByAppDetail tranxDetail = new TranxByAppDetail();
			tranxDetail = TranxByAppDetail.cover2TranxByAppDetail(t_report);
			tranxByAppDetail.add(tranxDetail);
		}
		//5.paging 
		
		model.addAttribute("page", page);
		model.addAttribute("recordperpage",recordPerPage );
		model.addAttribute("recordCount", tranxByAppDetail.size());
		model.addAttribute("tranxbyapp", tranxByAppDetail);
		return mav;
	}

	/*@RequestMapping(value = "ajax/tranxbyapp")
	public @ResponseBody
	ModelAndView tranxbyappPost(@RequestParam(value = "appId") String appId,
			@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate,
			@RequestParam(value = "status") String status,
			@RequestParam(value = "tranxType") String tranxType,
			@RequestParam("page") int page) {
		String sfromDate = commonService.getFirstDateOfMonth(fromDate) + ":00";
		String stoDate = commonService.getCurrentTime(toDate) + ":59";

		List<T_ReportTransaction> tranxs = reportService.getAppTranxByAppid(
				appId, sfromDate, stoDate, Integer.parseInt(tranxType),
				Integer.parseInt(status), page);
		List<TranxByAppDetail> tranxByAppDetail = new ArrayList<>();
		
		for (T_ReportTransaction t_report : tranxs) {
			TranxByAppDetail tranxDetail = new TranxByAppDetail();
			tranxDetail = TranxByAppDetail.cover2TranxByAppDetail(t_report);
			tranxByAppDetail.add(tranxDetail);
		}

		ModelAndView mav = new ModelAndView("/jsp/tranxbyapp.jsp");
		ModelMap model = mav.getModelMap();
		model.addAttribute("tranxbyapp", tranxByAppDetail);
		
		return mav;
	}
*/
	@RequestMapping(value = "ajax/tranxbyappdetail", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView tranxbyappPost(
			@RequestParam(value = "tranxId") String tranxId,
			@RequestParam(value = "tranxTime") String tranxTime) {

		long lTime = commonService.convertDate2Long("HH:mm, dd/MM/yyyy",
				tranxTime);

		List<T_ReportTransaction> tranxs = reportService.getTranxStatus(
				tranxId, (int) lTime);
		List<TranxDetail> tranxDetailList = new ArrayList<>();
		for (T_ReportTransaction tranx : tranxs) {
			TranxDetail tdetail = TranxDetail
					.convertT_reportTranx2TranxUserDetail(tranx);
			tranxDetailList.add(tdetail);
		}
		return new ModelAndView("/jsp/tranxdetail.jsp", "tranxDetailList",
				tranxDetailList);
	}
	
}
