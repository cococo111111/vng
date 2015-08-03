package vng.zingme.stats.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {

	// private static Logger log = Logger.getLogger(IndexController.class);

	@RequestMapping(value = { "", "/", "index" }, method = RequestMethod.GET)
	public String index() {
	//	System.out.println("server: " + ((WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getSessionId());
		return "zul/login.zul";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:index";
	}

}