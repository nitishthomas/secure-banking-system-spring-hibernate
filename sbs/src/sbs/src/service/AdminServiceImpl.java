package sbs.src.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sbs.src.dao.AdminDAO;
import sbs.src.form.Admintransactionrequest;
import sbs.src.form.Authorities;
import sbs.src.form.Externaluseraccount;
import sbs.src.form.Externalusertransaction;
import sbs.src.form.Login;
import sbs.src.form.Registeruser;

@Transactional
@Service("adminService")
public class AdminServiceImpl implements AdminService{
private AdminDAO adminDAO;
    
	@Autowired
    public void setadminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}
	
	@Override
	public List<Admintransactionrequest> listRequest() {
		   return adminDAO.listRequest();
	        
	}

	@Override
	public List<Registeruser> listUserRequest() {
		return adminDAO.listUserRequest();
	}

	@Override
	public int submitExternalUserTransaction(int tid) 
	{
	Externalusertransaction externalusertransaction = adminDAO.submitExternalUserTransaction(tid);

		//if(adminDAO.validateusername(externalusertransaction.getUsername() ,externalusertransaction.getAmount()) && (adminDAO.validateaccountnumber(externalusertransaction.getReceiveraccountnumber())))
	    int senderaccount =  adminDAO.getaccountinfo(externalusertransaction.getUsername()).getAccountnumber();
	    int recaccount= externalusertransaction.getReceiveraccountnumber();
	    
	    
	    externalusertransaction.setAccountnumber(senderaccount);
	    externalusertransaction.setSenderaccountnumber(senderaccount);
	    //externalusertransaction.setReceiveraccountnumber(externalaccount);
	    externalusertransaction.setDate(new SimpleDateFormat().format(new Date()));
	    //externalusertransaction.setAmount(amount);
	    
	   //if( externalusertransaction.getReceiveraccountnumber() == ((Externaluseraccount) adminDAO.getaccountinfo(externalusertransaction.getUsername())).getAccountnumber())
	   int amt = externalusertransaction.getAmount();
	    
	    if(senderaccount == recaccount)
	    {
	    	System.out.println("inside moneytransfer same acccount...");
	    	return -2;//return "enter valid account number" ;
	    }
	    
		    else if (adminDAO.validateuseramount(externalusertransaction.getUsername() , externalusertransaction.getAmount() ) &&  adminDAO.validateaccountnumber(externalusertransaction.getReceiveraccountnumber()) )
		{
				boolean status = adminDAO.deleteamount(externalusertransaction.getUsername() , externalusertransaction.getReceiveraccountnumber() , externalusertransaction.getAmount());
		        if(status)
		        {
					externalusertransaction.setTransactionstatus("completed");
					adminDAO.setSuccessAdminTransaction(tid);
					return 0;   // Success trans
		        } 
		}
	    
		    else 
		        return	-1 ;   //Transaction failed
		return 0;
	}

	@Override
	public int submitExternalAccountRequest(int tid) {
		Registeruser registeruser = adminDAO.getRegisterUserAccountInfo(tid);
		Login loginuser = new Login();
		loginuser.setDob(registeruser.getDob());
		loginuser.setEmail(registeruser.getEmail());
		loginuser.setFirstname(registeruser.getFirstname());
		loginuser.setGender(registeruser.getGender());
		loginuser.setLastname(registeruser.getLastname());
		loginuser.setPassword(registeruser.getPassword());
		loginuser.setPhone(registeruser.getPhone());
		loginuser.setSsn(registeruser.getSsn());
		loginuser.setUsername(registeruser.getUsername());
		
		int result = adminDAO.saveLoginUser(loginuser);
		
		
		
		Authorities auth = new Authorities();
		auth.setAuthority("user");
		auth.setUsername(registeruser.getUsername());
		result = adminDAO.saveuserAuthority(auth);
		
		long timeSeed = System.nanoTime(); 

	       double randSeed = Math.random() * 1000; 

	       long midSeed = (long) (timeSeed * randSeed);
	                                                    
	       String s = midSeed + "";
	       String subStr = "1"+s.substring(0,9); 
	       System.out.println(subStr);
	       
	       while(adminDAO.validateaccountnumber(Integer.parseInt(subStr)) )
	       {
	    	    timeSeed = System.nanoTime(); 

		        randSeed = Math.random() * 1000; 

		        midSeed = (long) (timeSeed * randSeed);
		                                                    
		        s = midSeed + "";
		        subStr = "1"+s.substring(0,9); 
		       System.out.println("finally account number chosen " + subStr);
	       }
	       
	      Externaluseraccount externaluseraccount = new Externaluseraccount();
	      externaluseraccount.setAccountnumber(Integer.parseInt(subStr));
	      externaluseraccount.setBalance(100);
	      externaluseraccount.setUsername(registeruser.getUsername());
		result = adminDAO.saveExternalAccount(externaluseraccount);
		
		
		result = adminDAO.updateRegisterUser(tid);
		return result;
		
		
	}
	

}
