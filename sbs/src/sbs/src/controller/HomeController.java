package sbs.src.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {

	@RequestMapping("/")
	public String showHome() {

		return "home";
	}
	
	@RequestMapping("/privacypolicy")
	public String privacypolicy() {

		return "privacypolicy";
	}
	
	/*@RequestMapping("/admin")
	public String adminHome() {
		return "admin";
	}*/
}
