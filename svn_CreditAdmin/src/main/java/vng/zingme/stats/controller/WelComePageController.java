package vng.zingme.stats.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class WelComePageController {

	private static Logger log = Logger.getLogger(WelComePageController.class);

	@RequestMapping(value = "/layout", method = RequestMethod.GET)
	public String Hello(ModelMap model) {
		log.info("Display welcome page!");
		return "/jsp/welcomepage.jsp";
	}
}
