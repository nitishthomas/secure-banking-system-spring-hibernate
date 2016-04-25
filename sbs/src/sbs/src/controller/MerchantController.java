package sbs.src.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sbs.src.form.Merchanttransaction;
import sbs.src.service.MerchantService;
import sbs.src.service.otpGenerator;

@Controller
public class MerchantController {

	private MerchantService merchantService;

	@Autowired
	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}
	
	@RequestMapping("/mrctrans")
	public String merchantHome(Model model , Principal principal) {
		model.addAttribute("merchanttransaction", new Merchanttransaction());
	//    model.addAttribute("otp" , otpGenerator.generatorOTP());
	    System.out.println(" coming in controlller ------------");
		return "mrctrans";
	}
	

	
	@RequestMapping(value = "/domrctrans", method = RequestMethod.POST)
	public String domrctrans(Model model ,Merchanttransaction merchanttransaction, Principal principal) {
		
		System.out.println("otp--" + merchanttransaction.getOtp() );	
		String username = principal.getName();
	 	merchanttransaction.setUsername(username);
		String errortransaction = new String();
	    
		model.addAttribute("Merchanttransaction", new Merchanttransaction());
	//	model.addAttribute("otp" , otpGenerator.generatorOTP());
		
		
		//SendMailSSL.customerMail(merchanttransaction);
		 merchanttransaction.setOtp(otpGenerator.generatorOTP());
		merchanttransaction.setUsername(username);
		
		//merchant started the transaction 
	
		int result = merchantService.merchanttransfer(merchanttransaction);
	    //testing 
	 //   merchantService.testing("amitgupta132453");
	    
		 if(result == 0) // returning  0 for success
		{   
			
			 merchanttransaction.setOtp(otpGenerator.generatorOTP());
		//	result = userService.merchanttranfer(merchanttransaction ); 
			errortransaction ="transaction request has been sent to customer and click below home to return home or start a new transaction";
	        
			return "redirect:/menterid";	
			
		}
		else
		{
			errortransaction = "Please check with user for details or balance availability";
		}
		 model.addAttribute("errortransaction",errortransaction);
		System.out.println("error  in transaction");
		 
		
		
		return "mrctrans" ;  
	}
	
	@RequestMapping("/menterid")
	public String addUserInput(Model model ,Merchanttransaction merchanttransaction,  Principal principal) {
		System.out.println("IN CONTROLLER MERCHANT TRANSACTION "+ merchanttransaction);
		
		
		int id = 42 ;
		String encryptedkey = "successsuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccess ";
	//	System.out.println(encryptedkey);
	//	int result = merchantService.merchantusertransfer( id , encryptedkey);
		
		
		return "completemerctrans";
	}
	
	@RequestMapping("/successfultrans")
	public String successfultransaction(Model model,@RequestParam ("id") String tid, @RequestParam ("key") String key,Merchanttransaction merchanttransaction,  Principal principal) {
		System.out.println("IN CONTROLLER MERCHANT TRANSACTION "+ merchanttransaction);
		
		System.out.println("in merchant controller " + key);
		System.out.println("in merchant controller " + tid);
		int id = Integer.parseInt(tid) ;
		System.out.println("in merchant controller " + id);
		// String encryptedkey = "1222223 ";
		System.out.println("in merchant controller " + key);
		int result = merchantService.merchantusertransfer( id , key);
		
		
		return "domrctrans";
	}
	
/*	@RequestMapping(value = "/merchantusertrans")
	public String merchantusertrans(Model model , Principal principal) {
		
		  // here merchant have encrypted date pass from user externally passed 
		//let it be encryptedkey 
		long encryptedkey = 0 ; //get it by model attrubute from jsp passed by domrctrans page
		
		// get aother attribute merchant transaction from previous page
		//let say
		Merchanttransaction merchanttransaction = new Merchanttransaction(); // get it by previous page
		
		//call service method for completion
		
		int result = 0; 
		// to get the result if its -1 not enough amount contact customer 
		//  -2 encrypted date wrong , wrong details
		//  0 transaction succeeded
		
		result = merchantService.merchantusertransfer(merchanttransaction , encryptedkey);
		
		
		
		return "mrctrans"; 
	}
*/
	
	
}
