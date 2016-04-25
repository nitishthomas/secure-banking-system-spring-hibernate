package sbs.src.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import sbs.src.form.Admintransactionrequest;
import sbs.src.form.Registeruser;
import sbs.src.service.AdminService;

@Controller
public class AdminController {
	private AdminService adminService;


	@Autowired
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}


	
	@RequestMapping("/admin")
	public String adminHome() {
		return "admin";
	}
	
	@RequestMapping("/admintransactionrequest")
	public String adminTransactionrequest(Model model) {
		List<Admintransactionrequest> listlog = adminService.listRequest();
		model.addAttribute("admintransactionrequest", listlog);	
		return "admintransactionrequest";
	}
	
	@RequestMapping("/externalaccountrequest")
	public String externalAccountrequest(Model model) {
		List<Registeruser> listlog = adminService.listUserRequest();
		model.addAttribute("registeruser", listlog);	
		return "externalaccountrequest";
	}
	
	
	@RequestMapping(value="/adminrequestaccept",method=RequestMethod.POST) 
	public String adminRequestaccept(Model model, @ModelAttribute(value="status") String status) {
		System.out.println("Request accepted" + status);
		int result = -1;
		System.out.println(status.substring(0,7));
		if (status.substring(0,6).equalsIgnoreCase("submit"))
		{
			
			int tid = Integer.parseInt((status.substring(6)));
			System.out.println(tid + " tid shud print ");
			result = adminService.submitExternalUserTransaction(tid);
			
			
		}
		else if (status == "decline")
		{
			
		}
		else
		{
			
			return "admin";
		}
		/*List<Registeruser> listlog = adminService.listUserRequest();
		model.addAttribute("registeruser", listlog);	*/
		return "admin";
	}
	
	@RequestMapping(value="/externalrequestaccept",method=RequestMethod.POST) 
	public String externalRequestaccept(Model model, @ModelAttribute(value="status") String status) {
		System.out.println("Request accepted" + status);
		int result = -1;
		System.out.println(status.substring(0,7));
		if (status.substring(0,6).equalsIgnoreCase("submit"))
		{
			
			int tid = Integer.parseInt((status.substring(6)));
			System.out.println(tid + " tid shud print ");
			result = adminService.submitExternalAccountRequest(tid);
		}
		else if (status == "decline")
		{
			
		}
		else
		{
			
			return "admin";
		}
		/*List<Registeruser> listlog = adminService.listUserRequest();
		model.addAttribute("registeruser", listlog);	*/
		return "admin";
	}
	
	
}
