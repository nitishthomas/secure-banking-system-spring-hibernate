package sbs.src.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@Component("adminDao")
public class AdminDAOImpl implements AdminDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Admintransactionrequest> listRequest() {
		   return session().createQuery("from Admintransactionrequest").list();
	}

	@Override
	public List<Registeruser> listUserRequest() {
		return session().createQuery("from Registeruser").list();
	}

	@Override
	public Externalusertransaction submitExternalUserTransaction(int transactionid) {
 		List<Externalusertransaction> list = session().createQuery("from Externalusertransaction  where transactionid = :transactionid").setParameter("transactionid", transactionid).list();      
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
	public boolean deleteamount(String username, Integer externalaccount,
			Integer amount) {

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

	@Override
	public int setSuccessAdminTransaction(int transactionid) {
		// TODO Auto-generated method stub
		String hql = "DELETE FROM Admintransactionrequest "  + 
	             "WHERE transactionid = :transactionid";
	Query query = session().createQuery(hql);
	query.setParameter("transactionid", transactionid);
	int result = query.executeUpdate();
	System.out.println("Rows affected: " + result);
	return 0;
	}

	@Override
	public Registeruser getRegisterUserAccountInfo(int tid) {
		// TODO Auto-generated method stub
		System.out.println("inside Admin DAO register user get account detail");
		
		List<Registeruser> list = session().createQuery("from Registeruser  where id = :id").setParameter("id", tid).list();      
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
	public int saveLoginUser(Login loginuser) {
		// TODO Auto-generated method stub
		session().save(loginuser);
		return 0;
	}

	@Override
	public int saveuserAuthority(Authorities auth) {
		// TODO Auto-generated method stub
		session().save(auth);
		return 0;
	}

	@Override
	public int saveExternalAccount(Externaluseraccount externaluseraccount) {
		// TODO Auto-generated method stub
		session().save(externaluseraccount);
		return 0;
	}

	@Override
	public int updateRegisterUser(int tid) {
		String hql1 = "UPDATE Registeruser set activestatus = :activestatus "  +  "WHERE id = :id";
		Query query1 = session().createQuery(hql1).setParameter("id" , tid);
		query1.setParameter("activestatus", "success" );
		int result1 = query1.executeUpdate();
		System.out.println("Rows affected: " + result1);
		return 0;
	}
 	
	

}
