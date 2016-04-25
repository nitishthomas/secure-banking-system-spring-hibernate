package sbs.src.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sbs.src.form.Externaluseraccount;
import sbs.src.form.Externalusertransaction;
import sbs.src.form.Login;
import sbs.src.form.Merchantaccount;
import sbs.src.form.Merchanttransaction;
import sbs.src.form.Registeruser;


@Transactional
@Repository
@Component("merchantDao")
public class MerchantDAOImpl implements MerchantDAO{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Merchanttransaction> listRequest() {
		// TODO Auto-generated method stub
		
		 return session().createQuery("from Merchanttransaction").list();
	}

	@Override
	public List<Registeruser> listUserRequest() {
		// TODO Auto-generated method stub
		return session().createQuery("from Registeruser").list();
	}

	
	@Override
	public Externaluseraccount externalaccountnumber(Integer accountnumber) {
		// TODO Auto-generated method stub
		List<Externaluseraccount> list = session().createQuery("from Merchantaccount  where accountnumber = :accountnumber").setParameter("accountnumber", accountnumber).list();      
		   System.out.println(list);
			String str = list.toString();
			
		   if(str.length() > 3)
		    {
		    	
		    	return list.get(0) ;
		    }
		    else
			return null;
		
	}
	
	@Override
	public Externaluseraccount getuserinfo(int accountnumber) {
		
		List<Externaluseraccount> list = session().createQuery("from Externaluseraccount where accountnumber = :accountnumber").setParameter("accountnumber", accountnumber).list();      
	    if(list != null)
	    	{
	    	return list.get(0); 
	    	}
	    else
	    	return null;
	  
	}

	
	@Override
	public boolean savemerchanttransaction( Merchanttransaction merchanttransaction) {
		// TODO Auto-generated method stub
		
	/*String hql = "INSERT INTO Merchanttransaction(username, accountnumber, customeraccountnumber, otp )"  + 
	             "SELECT firstName, lastName, salary FROM old_employee";
    Query query = session().createQuery(hql);
	int result = query.executeUpdate();
	System.out.println("Rows affected: " + result);*/
		session().save(merchanttransaction);
	
		return true ;
		//return false;
	}
	
	@Override
	public boolean validateExternaluseraccountnumber(Integer accountnumber) {
		List<Externaluseraccount> list = session().createQuery("from Externaluseraccount  where accountnumber = :accountnumber").setParameter("accountnumber", accountnumber).list();      
	    /*if(list != null)
	    {
	    	System.out.println("Insid-e---------" + list.get(0));
	    	return true ;
	    }
	    else
		return false;*/
		System.out.println("in merchant DAO validate externalUser account" + list);
		String str = new String();
		str = list.toString();
		System.out.println(str);
		
		if(str.length() > 3)
	    	{
	    	System.out.println("coming here");
	    	return true; 
	    	}
	    else
	    	return false;
		
	}
	
	@Override
	public Merchantaccount getMerchantaccountinfo(String username) {
		System.out.println("inside get account detail");
		System.out.println(username);
		List<Merchantaccount> list = session().createQuery("from Merchantaccount  where username = :username").setParameter("username", username).list();      
		String str = new String();
		str = list.toString();
		System.out.println(str);
		
		if(str.length() > 3)
	    	{
	    	System.out.println("coming here");
	    	return list.get(0); 
	    	}
	    else
	    	return null;
	}

	@Override
	public Externaluseraccount getExternaluserinfo(Integer accountnumber) {
		// TODO Auto-generated method stub
		
		
		
		List<Externaluseraccount> list = session().createQuery("from Externaluseraccount  where accountnumber = :accountnumber").setParameter("accountnumber", accountnumber).list();      
		String str = new String();
		str = list.toString();
		System.out.println(str);
		
		if(str.length() > 3)
	    	{
	    	System.out.println("coming here");
	    	return list.get(0); 
	    	}
	    else
	    	return null;
		
	}
	
	public Login getLoginuserinfo(String username) {
		System.out.println("Inside get user info");
		System.out.println(username);
	//	Criteria criteria = session().createCriteria(Login.class);
	  //  Login login2 = (Login) criteria.add(Restrictions.idEq(username));
		List<Login> list = session().createQuery("from Login where username = :username").setParameter("username", username).list();      
/*	    if(list != null)
	    	{
	    	return list.get(0); 
	    	}
	    else
	    	return null;
*/	
		String str = new String();
		str = list.toString();
		System.out.println(str);
		
		if(str.length() > 3)
	    	{
	    	
	    	return list.get(0); 
	    	}
	    else
	    	return null;
	}

	@Override
	public Merchanttransaction getMerchantTransaction(int transactionid) {
		// TODO Auto-generated method stub
		
		System.out.println("Inside MERCHANT DAO for getting merchant trnasaction");
		
	//	Criteria criteria = session().createCriteria(Login.class);
	  //  Login login2 = (Login) criteria.add(Restrictions.idEq(username));
		List<Merchanttransaction> list = session().createQuery("from Merchanttransaction where transactionid = :transactionid").setParameter("transactionid", transactionid).list();      
          
		String str = new String();
		str = list.toString();
		System.out.println(str);
		
		if(str.length() > 3)
	    	{
			System.out.println(list.get(0));
	    	
	    	return list.get(0); 
	    	}
	    else
	    	return null;
		
		
	
	}

	@Override
	public int completeMoneyTransaction(Integer merchantAccountnumber, Integer customeraccountnumber, String amounttransfer) {
		// TODO Auto-generated method stub
		int amount = Integer.parseInt(amounttransfer);
	//	int amount = 10;
		List<Externaluseraccount> list = session().createQuery("from Externaluseraccount  where accountnumber = :accountnumber").setParameter("accountnumber", customeraccountnumber).list();    
		Externaluseraccount externaluseraccount = (Externaluseraccount) list.get(0);
		int newAmount = externaluseraccount.getBalance() - amount ;
		

String hql = "UPDATE Externaluseraccount set balance = :balance "  +  "WHERE username = :username";
Query query = session().createQuery(hql).setParameter("username", externaluseraccount.getUsername());
query.setParameter("balance", newAmount);
int result = query.executeUpdate();
System.out.println("Rows affected: " + result);

List<Merchantaccount> list1 = session().createQuery("from Merchantaccount  where accountnumber = :accountnumber").setParameter("accountnumber", merchantAccountnumber).list();    
Merchantaccount merchantAccount = (Merchantaccount) list1.get(0);
Integer amount1 = merchantAccount.getBalance() + amount ;

String hql1 = "UPDATE Merchantaccount set balance = :balance "  +  "WHERE accountnumber = :accountnumber";
Query query1 = session().createQuery(hql1).setParameter("accountnumber", merchantAccountnumber);
//query1.setParameter("balance", amount1.toString());
query1.setParameter("balance", amount1);
int result1 = query1.executeUpdate();
System.out.println("Rows affected: " + result1);


	// change merchant transaction


   // save in external user transaction
		
		return 0;
	}

	@Override
	public int updateMerchantTransaction(Integer transactionid) {
		// TODO Auto-generated method stub
		String transactionstatus = "success";
		
		//String hql = "UPDATE Externaluseraccount set balance = :balance "  +  "WHERE username = :username";
		String hql = "UPDATE Merchanttransaction set transactionstatus = :transactionstatus "  +  "WHERE transactionid = :transactionid";
		Query query = session().createQuery(hql).setParameter("transactionid", transactionid);
		query.setParameter("transactionstatus", transactionstatus);
		int result = query.executeUpdate();
		System.out.println("Rows affected: " + result);
		
		return 0;
	}

	@Override
	public int createExternalUserTransaction(Merchanttransaction merchantTransaction , String externalusername) {
		// TODO Auto-generated method stub
		Externalusertransaction externalusertransaction = new Externalusertransaction();
		externalusertransaction.setAccountnumber(merchantTransaction.getCustomeraccountnumber());
		
		// if u change externa account transaction amount , you need to change her as well to below commnad -------------------------------------
		//externalusertransaction.setAmount(merchantTransaction.getAmount());
		externalusertransaction.setAmount(Integer.parseInt(merchantTransaction.getAmount()) );
		externalusertransaction.setDate(merchantTransaction.getDate());
		externalusertransaction.setReceiveraccountnumber(merchantTransaction.getAccountnumber());
		externalusertransaction.setTransactionstatus(merchantTransaction.getTransactionstatus());
		externalusertransaction.setUsername(externalusername);
		externalusertransaction.setAccountnumber(merchantTransaction.getCustomeraccountnumber());
		externalusertransaction.setSenderaccountnumber(merchantTransaction.getCustomeraccountnumber());
		
		session().save(externalusertransaction);
		return 0;
	}
	
	
	
}
