package sbs.src.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sbs.src.form.Admintransactionrequest;
import sbs.src.form.Authorities;
import sbs.src.form.Externaluseraccount;
import sbs.src.form.Externalusertransaction;
import sbs.src.form.Login;
import sbs.src.form.Registeruser;

@Transactional
@Repository
@Component("userDao")
public class UserDAOImpl implements UserDAO {
	
	/*private NamedParameterJdbcTemplate jdbc;
	Not needed unless using jdbcsql parameter method to run sql query
	public void setDataSource(DataSource jdbc){
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	*/
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	 
		@SuppressWarnings("unchecked")

	public List<Login> listUser() {
		System.out.println("Error1");
        return session().createQuery("from Login").list();        
    }

	public void createUser(Registeruser registeruser){
		
		System.out.println("Inside userDao create user method.");
		registeruser.setPassword(passwordEncoder.encode(registeruser.getPassword()));
		session().save(registeruser);
	
	// Different approach to insert using jdbc sql parameter
		//BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(registeruser);
	//	session().createQuery("INSERT INTO registeruser(username,firstname,lastname,password,email,ssn,dob,gender,phone)"+"Select ");
//jdbc.update("insert into registeruser (username, firstname, lastname, password, email, ssn, dob, gender, phone) values (:username, :firstname, :lastname, :password, :email, :ssn, :dob, :gender, :phone)",params);

	}

	public Login getuserinfo(String username) {
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
	    	System.out.println("coming here");
	    	return list.get(0); 
	    	}
	    else
	    	return null;
	}

	@Override
	public Externaluseraccount getaccountinfo(String username) {
		System.out.println("inside get account detail");
		System.out.println(username);
		List<Externaluseraccount> list = session().createQuery("from Externaluseraccount  where username = :username").setParameter("username", username).list();      
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
	public boolean validateuseramount(String username, Integer amount) {
		
		List<Externaluseraccount> list = session().createQuery("from Externaluseraccount  where username = :username").setParameter("username", username).list();      
	    Externaluseraccount externaluseraccount = (Externaluseraccount) list.get(0);
		if(externaluseraccount.getBalance() > amount)
	    	{
			System.out.println("Insid-e---------"+ externaluseraccount.getBalance());
	    	return true; 
	    	}
	    else
	    	return false ;
		
		
	}

	@Override
	public boolean validateaccountnumber(Integer accountnumber) {
		List<Externaluseraccount> list = session().createQuery("from Externaluseraccount  where accountnumber = :accountnumber").setParameter("accountnumber", accountnumber).list();      
	    /*if(list != null)
	    {
	    	System.out.println("Insid-e---------" + list.get(0));
	    	return true ;
	    }
	    else
		return false;*/
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
	public boolean deleteamount(String username, Integer externalaccount,Integer amount) {
	
		List<Externaluseraccount> list = session().createQuery("from Externaluseraccount  where username = :username").setParameter("username", username).list();    
		Externaluseraccount externaluseraccount = (Externaluseraccount) list.get(0);
		int newAmount = externaluseraccount.getBalance() - amount ;
		

String hql = "UPDATE Externaluseraccount set balance = :balance "  +  "WHERE username = :username";
Query query = session().createQuery(hql).setParameter("username", username);
query.setParameter("balance", newAmount);
int result = query.executeUpdate();
System.out.println("Rows affected: " + result);

List<Externaluseraccount> list1 = session().createQuery("from Externaluseraccount  where accountnumber = :accountnumber").setParameter("accountnumber", externalaccount).list();    
Externaluseraccount externaluseraccount1 = (Externaluseraccount) list1.get(0);
int amount1 = externaluseraccount1.getBalance() + amount ;

String hql1 = "UPDATE Externaluseraccount set balance = :balance "  +  "WHERE accountnumber = :accountnumber";
Query query1 = session().createQuery(hql1).setParameter("accountnumber", externalaccount);
query1.setParameter("balance", amount1);
int result1 = query1.executeUpdate();
System.out.println("Rows affected: " + result1);


		return true ;
	}

		
	public boolean exists(String username){
		List<Registeruser> list1 = session().createQuery("from Registeruser  where username = :username").setParameter("username", username).list();
		//System.out.println(list1);
		
		String str = new String();
		str = list1.toString();
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
	public String getuserauthority(String username) {
		List<Authorities> list = session().createQuery("from Authorities where username = :username").setParameter("username", username).list();		
		String str = new String();
				str = list.toString();
				System.out.println(str);
				
				if(str.length() > 3)
			    	{
					Authorities authority = (Authorities)list.get(0);
			    	System.out.println("Authority");
			    	System.out.println(authority.getAuthority());
			    	return authority.getAuthority();
			    	}
			    else
			    	return null;
	}

	@Override
	public int externalusertransactionSave(
			Externalusertransaction externalusertransaction) {
		// TODO Auto-generated method stub
		 
		return  (int) (session().save(externalusertransaction));
		
	}

	@Override
	public void admintransactionrequestsave(
			Externalusertransaction externalusertransaction) {
		
		Admintransactionrequest admintransactionrequest = new Admintransactionrequest();
		admintransactionrequest.setTransactionid(externalusertransaction.getTransactionid());
		admintransactionrequest.setUsername(externalusertransaction.getUsername());
		admintransactionrequest.setAccountnumber(externalusertransaction.getAccountnumber());
		admintransactionrequest.setSenderaccountnumber(externalusertransaction.getSenderaccountnumber());
		admintransactionrequest.setReceiveraccountnumber(externalusertransaction.getReceiveraccountnumber());
		admintransactionrequest.setAmount(externalusertransaction.getAmount());
		admintransactionrequest.setDate(externalusertransaction.getDate());
		admintransactionrequest.setTransactionstatus(externalusertransaction.getTransactionstatus());
     
		session().save(admintransactionrequest);
		
		/*		
 * 
 * String hql = "INSERT INTO Admintransactionrequest(transactionid,username,accountnumber,senderaccountnumber,receiveraccountnumber,amount,date,transactionstatus)"+ "SELECT transactionid,username,accountnumber,senderaccountnumber,receiveraccountnumber,amount,date,transactionstatus from Externalusertransaction" ;
		Query query = session().createQuery(hql);
		int result = query.executeUpdate();
		System.out.println("Admin request transaction"+ result);*/
	}

	@Override
	public List<Externalusertransaction> listTransaction(String username) {
		System.out.println("inside get account detail");
		System.out.println(username);
		List<Externalusertransaction> list = session().createQuery("from Externalusertransaction  where username = :username").setParameter("username", username).list();      
		String str = new String();
		str = list.toString();
		System.out.println(str);
		
		if(str.length() > 3)
	    	{
	    	System.out.println("coming here");
	    	return list; 
	    	}
	    else
	    	return null;
	}

			


		
	}
