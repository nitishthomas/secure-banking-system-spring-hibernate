
package sbs.src.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import sbs.src.form.Externaluseraccount;
import sbs.src.form.Externalusertransaction;
import sbs.src.form.Login;
import sbs.src.service.UserService;

@Controller
public class UserController {
	private UserService userService;


	@Autowired
	public void setUserService(UserService loginService) {
		this.userService = loginService;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//	request.getSession().invalidate();
		//	response.sendRedirect(request.getContextPath());
			userService.userlogout();
			return "logout";    // This takes user to the create user page.
	}

		
	@RequestMapping("/listallusers")
	public String listLog1(Model model) {
	List<Login> listlog = userService.listUser();
	model.addAttribute("login", listlog);	
	return "usertest";    // This will list all the users
	}

/*	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	public String createUser(Model model, Login login1) {
		System.out.println(login1);
		userService.addUser(login1);
		System.out.println("Inside login controller after save.");
		return "home";   // This should return to user's home page.
	}
*/	
	@RequestMapping("/afterlogin")
	public String afterlogin(Model model , Principal principal) {
		String username = principal.getName();
		//String username = userService.getUser();
		model.addAttribute("username", username);
		Login login = userService.getuserinfo(username);
		System.out.println(login);
		model.addAttribute("login",login);
		Externaluseraccount useraccount = userService.getaccountinfo(username);
		System.out.println("------" + useraccount);
		model.addAttribute("useraccount",useraccount);
		return "afterlogin";    // This takes user to the create user page.
	}
	

	@RequestMapping(value = "/transfer")
	public String moneytransfer(Model model , Principal principal) {
		model.addAttribute("Externalusertransaction", new Externalusertransaction());
	   
		//	String error1 = userService.validatetransaction()
		
		
		return "transfer";   // This should return to user's home page.
	}
	
	/*@RequestMapping(value = "/dotransfer", method = RequestMethod.GET)
	public String domoneytransferGet(Model model) {
		
			return "dotransfer";
	}
	*/
	
	@RequestMapping(value = "/dotransfer1", method = RequestMethod.POST)
	public String domoneytransfer(Model model ,Externalusertransaction externalusertransaction, Principal principal) {
					
		String username = principal.getName();
		externalusertransaction.setUsername(username);
		String errortransaction = new String();
		
		model.addAttribute("Externalusertransaction", new Externalusertransaction());
		System.out.println("Username:" +externalusertransaction.getUsername() + "Rec acnt number" + externalusertransaction.getReceiveraccountnumber());
		
		
		 //validate the amount and account number 
		 String result = userService.moneytransfer(externalusertransaction);
		 
		 
		 
		 //critical transaction goes to admin transaction pending table
		 if(result.equalsIgnoreCase("Pending")){ 
			 
	     errortransaction = " Critical transaction sent to bank for approval and click below home to return home or start a new transaction"; 
	     model.addAttribute( "errortransaction",errortransaction);
	     return "transfer";
		 }
		else if(result.equalsIgnoreCase("success"))
		{
			errortransaction ="transaction has been completed and click below home to return home or start a new transaction";
		}
		else
		{
			errortransaction = result;
			model.addAttribute( "errortransaction",errortransaction);
			System.out.println("Amount cannot be transfered");
			return "transfer";
		}
		
		// model.addAttribute( "errortransaction",errortransaction);
		
		 return "dotransfer" ;   // make a new page 
	}
	
	
	@RequestMapping("/services")
	public String showServices(Model model, Principal principal) {
	//List<Login> listlog = userService.listUser();
	//model.addAttribute("login", listlog);	
	String username = principal.getName();
	String userauthority = userService.getuserauthority(username);
	
	return "services";    // This will list all the users
	}
	
	
	@RequestMapping("/externalusertransaction")
	public String listTransaction(Model model,Principal principal) {
	List<Externalusertransaction> listtrans = userService.listTransaction(principal.getName());
	model.addAttribute("externalusertransaction", listtrans);	
	return "externalusertransaction";    // This will list all the users
	}

}

