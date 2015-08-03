package vng.zingme.stats.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vng.zingme.payment.thrift.T_ReportSummary;
import vng.zingme.stats.model.PaySummary;
import vng.zingme.stats.service.CommonService;
import vng.zingme.stats.service.ReportService;

@Controller
@RequestMapping("/admin")
public class PaySummaryController {

	@Autowired
	private CommonService common;
	@Autowired
	private ReportService reportService;

	@RequestMapping(value = "/paysummary")
	public ModelAndView tranxbyappSummary(
			@ModelAttribute("paysummary") PaySummary paysummary)
			throws ParseException, TException {

		// set date
		String fromDate = common.getYesTerDay(paysummary.getFromDate());
		String toDate = common.getToday(paysummary.getToDate());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Date dFromDate = df.parse(fromDate);
		Date dToDate = df.parse(toDate);
		DecimalFormat fm = new DecimalFormat("###,###,###");
		
		List<PaySummary> paySummaryList = new ArrayList<>();
		double telcoSum = 0;
		double zingpaySum = 0;
		double smsSum = 0;
		double reconcileSum = 0;
		double adminSum = 0;
		DecimalFormat dformat = new DecimalFormat("#.##");
		while (dFromDate.getTime() <= dToDate.getTime()) {
			PaySummary paySummary = new PaySummary();
			T_ReportSummary zingpay = reportService.getPay_Telco("zing#101",
					df.format(dFromDate) + " 00:00:00", df.format(dFromDate)
							+ " 23:59:59");
			T_ReportSummary telco = reportService.getPay_Telco("zing#1016",
					df.format(dFromDate) + " 00:00:00", df.format(dFromDate)
							+ " 23:59:59");
			
			T_ReportSummary sms = reportService.getPay_Telco("zing#1014",
					df.format(dFromDate) + " 00:00:00", df.format(dFromDate)
							+ " 23:59:59");
			T_ReportSummary reconcile = reportService.getPay_Telco("zing#1017",
					df.format(dFromDate) + " 00:00:00", df.format(dFromDate)
							+ " 23:59:59");
			
			T_ReportSummary admin = reportService.getPay_Telco("zing#2",
					df.format(dFromDate) + " 00:00:00", df.format(dFromDate)
							+ " 23:59:59");

			paySummary.setZingPay(fm.format(zingpay.getTotalMoney()));
			paySummary.setTelcoCard(fm.format(telco.getTotalMoney()));
			paySummary.setSms(fm.format(sms.getTotalMoney()));
			paySummary.setReconcile(fm.format(reconcile.getTotalMoney()));
			paySummary.setAdmin(fm.format(admin.getTotalMoney()));

			double total = zingpay.getTotalMoney() + telco.getTotalMoney() + sms.getTotalMoney() + admin.getTotalMoney() + reconcile.getTotalMoney();
			if (total !=0) {
				String telcoPercent = (total == 0) ? "0" : dformat.format(telco.getTotalMoney() * 100d / total);
				String zingPayPercent = (total == 0) ? "0" : dformat.format(zingpay.getTotalMoney() * 100 / total);
				String smsPercent = (total == 0) ? "0" : dformat.format(sms.getTotalMoney() * 100 / total);
				String adminPercent = (total == 0) ? "0" : dformat.format(admin.getTotalMoney() * 100 / total);
				String reconcilePercent = (total == 0) ? "0" : dformat.format(reconcile.getTotalMoney() * 100 / total);
				
				paySummary.setDate(df.format(dFromDate));
				paySummary.setSum(fm.format(total));
				paySummary.setTelcoPercent(telcoPercent);
				paySummary.setZingPayPercent(zingPayPercent);
				paySummary.setSmsPercent(smsPercent);
				paySummary.setAdminPercent(adminPercent);
				paySummary.setReconcilePercent(reconcilePercent);
				
				telcoSum += telco.getTotalMoney();
				zingpaySum += zingpay.getTotalMoney();
				smsSum += sms.getTotalMoney();
				adminSum += admin.getTotalMoney();
				reconcileSum += reconcile.getTotalMoney();
				
				paySummaryList.add(paySummary);
			}
			dFromDate.setDate(dFromDate.getDate() + 1);

		}
		double totalSum = telcoSum + zingpaySum;
		String telcoSumPercent =  (totalSum == 0) ? "0" : dformat.format(telcoSum * 100 / totalSum);
		String zingpaySumPercent =  (totalSum == 0) ? "0" : dformat.format(zingpaySum * 100 / totalSum);
		String smsSumPercent =  (totalSum == 0) ? "0" : dformat.format(smsSum * 100 / totalSum);
		String adminSumPercent =  (totalSum == 0) ? "0" : dformat.format(adminSum * 100 / totalSum);
		String reconcileSumPercent =  (totalSum == 0) ? "0" : dformat.format(reconcileSum * 100 / totalSum);
	
		ModelAndView mav = new ModelAndView("/jsp/paysummary.jsp");
		ModelMap model = mav.getModelMap();
		model.addAttribute("fromDateView", fromDate);
		model.addAttribute("toDateView", toDate);
		model.addAttribute("paySummaryList", paySummaryList);
		model.addAttribute("totalSum", fm.format(totalSum));
		model.addAttribute("telcoSumPercent", telcoSumPercent);
		model.addAttribute("zingpaySumPercent",	zingpaySumPercent);
		model.addAttribute("smsSumPercent",	smsSumPercent);
		model.addAttribute("adminSumPercent",	adminSumPercent);
		model.addAttribute("reconcileSumPercent",	reconcileSumPercent);
		model.addAttribute("telcoSum", fm.format(telcoSum));
		model.addAttribute("zingpaySum", fm.format(zingpaySum));
		model.addAttribute("smsSum", fm.format(smsSum));
		model.addAttribute("adminSum", fm.format(adminSum));
		model.addAttribute("reconcileSum", fm.format(reconcileSum));

		return mav;
	}

}
