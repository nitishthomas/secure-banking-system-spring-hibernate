package sbs.src.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sbs.src.dao.FormValidationGroup;
import sbs.src.form.Registeruser;
import sbs.src.service.UserService;

@Controller
public class LoginController {
	
	private UserService userService;
	

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/login")
	public String showLogin() {
			return "loginpage";    // This takes user to the create user page.
	}
	
	@RequestMapping("/userhome")
	public String userhome() {
			
			return "userhome";    // This takes user to the create user page.
	}
	
	@RequestMapping("/createnew")
	public String createAccount(Model model) {
		model.addAttribute("registeruser", new Registeruser());
		return "createnewuser";    // This takes user to the create user page.
	}
	
	@RequestMapping(value="/createaccountrequest", method=RequestMethod.POST)
	public String accountCreated(@ModelAttribute("registeruser") @Validated(FormValidationGroup.class) Registeruser registeruser , BindingResult result) {
	if(result.hasErrors())
		{	
			System.out.println("Inside errors");
			return "createnewuser";
		}
			
		if(userService.exists(registeruser.getUsername())){
			System.out.println("Caught Duplicate User name.");
			result.rejectValue("username", "DuplicateKey.registeruser.username", "This user name already exists..!!");
			return "createnewuser";
		}
		
		try {
			userService.addUser(registeruser);
			System.out.println("After Service");
			
		}
		catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.registeruser.username", "This user name already exists..!!");
			return "createnewuser";
		}
			
			return "accountcreated";    // This takes user to the create user page.
		}

	
		@RequestMapping("/frgtpassword")
		public String frgtPassword() {
						
			return "frgtpasswrd";    // This takes user to the create user page.
		}
	
		@RequestMapping(value = "/frgt1", method = RequestMethod.POST)
		public String frgtPassword1() {
						
			return "frgtstep2";    // This takes user to the create user page.
		}
}
