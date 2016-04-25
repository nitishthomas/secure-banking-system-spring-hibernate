package sbs.src.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sbs.src.dao.UserDAO;
import sbs.src.form.Admintransactionrequest;
import sbs.src.form.Externalusertransaction;
import sbs.src.form.Login;
import sbs.src.form.Externaluseraccount;
import sbs.src.form.Registeruser;

@Service("userService")
public class UserServiceImpl implements UserService{
	private UserDAO userDAO;
    
	@Autowired
    public void setuserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Transactional
    public List<Login> listUser() {
        /*userDAO.validateaccountnumber(123456789);
        userDAO.validateuseramount("amit123", 20);
        userDAO.deleteamount("amit123",123456789,20);
        System.out.println("Valdation success.");*/
		return userDAO.listUser();
        
        
    }
  @Transactional
  public void addUser(Registeruser registeruser){
	 userDAO.createUser(registeruser);
  }

  @Transactional
	public Login getuserinfo(String username){
		return userDAO.getuserinfo(username);
	}
@Override
public Externaluseraccount getaccountinfo(String username) {
	// TODO Auto-generated method stub
	return userDAO.getaccountinfo(username);
}
@Override
public void userlogout() {
	
	// TODO Auto-generated method stub

}

//create this 


@Override
public String moneytransfer(Externalusertransaction externalusertransaction) {
	
	//if(userDAO.validateusername(externalusertransaction.getUsername() ,externalusertransaction.getAmount()) && (userDAO.validateaccountnumber(externalusertransaction.getReceiveraccountnumber())))
    int senderaccount =  userDAO.getaccountinfo(externalusertransaction.getUsername()).getAccountnumber();
    int recaccount= externalusertransaction.getReceiveraccountnumber();
    
    
    externalusertransaction.setAccountnumber(senderaccount);
    externalusertransaction.setSenderaccountnumber(senderaccount);
    //externalusertransaction.setReceiveraccountnumber(externalaccount);
    externalusertransaction.setDate(new SimpleDateFormat().format(new Date()));
    //externalusertransaction.setAmount(amount);
    
   //if( externalusertransaction.getReceiveraccountnumber() == ((Externaluseraccount) userDAO.getaccountinfo(externalusertransaction.getUsername())).getAccountnumber())
   int amt = externalusertransaction.getAmount();
    
    if(senderaccount == recaccount)
    {
    	System.out.println("inside moneytransfer same acccount...");
    	return "enter valid account number" ;
    }
	    else if(userDAO.validateuseramount(externalusertransaction.getUsername() , externalusertransaction.getAmount() ) &&  userDAO.validateaccountnumber(externalusertransaction.getReceiveraccountnumber()) )
	{
		if(amt  > 10000)
		{
			externalusertransaction.setTransactionstatus("pending");
			int id = userDAO.externalusertransactionSave(externalusertransaction);
			// critical transaction save in register pending table
			
			 userDAO.admintransactionrequestsave(externalusertransaction);
			return "pending" ;
		}
		else {
			boolean status = userDAO.deleteamount(externalusertransaction.getUsername() , externalusertransaction.getReceiveraccountnumber() , externalusertransaction.getAmount());
	        if(status)
	        {
	        	
				
	        	
				externalusertransaction.setTransactionstatus("completed");
				
				
				return "success";
	        	
	        }
	        else 
	        return	"failed"; 
		}
	    
	}
  
	else if(!(userDAO.validateuseramount( externalusertransaction.getUsername() , externalusertransaction.getAmount() ) ))
	   return "not enough balance" ;
	
	else
	return "please enter valid account number";
}


public boolean exists(String username){
	return userDAO.exists(username);
	
}
@Override
public String getuserauthority(String username) {
	return 	userDAO.getuserauthority(username);
}

@Override
public List<Externalusertransaction> listTransaction(String username) {
	return userDAO.listTransaction(username);
}

}
