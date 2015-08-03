package vng.zingme.stats.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vng.zingme.stats.mySqlConnectionPool.Config2;

@Controller
@RequestMapping("/admin")
public class TranFilterController {

	@RequestMapping(value = "/tranxfilter", method = RequestMethod.GET)
	public ModelAndView tranxfilter() {
		ModelAndView mav = new ModelAndView("/jsp/externalPage.jsp");
		ModelMap model = mav.getModelMap();

		model.addAttribute("externalURL", Config2.tranxfilter);
		return mav;
	}

	@RequestMapping(value = "/autoreport", method = RequestMethod.GET)
	public ModelAndView autoReport() {
		ModelAndView mav = new ModelAndView("/jsp/externalPage.jsp");
		ModelMap model = mav.getModelMap();

		model.addAttribute("externalURL", Config2.autoReport);
		return mav;
	}

}
