package sbs.src.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sbs.src.form.Login;

@Repository

public class LoginDAOImpl implements LoginDAO {
	@Autowired
    private SessionFactory sessionFactory;
 
     
	@SuppressWarnings("unchecked")
	public List<Login> listLogin() {
		System.out.println("Error1");
        return sessionFactory.getCurrentSession().createQuery("from Login").list();
        
    }

	public void createLogin(Login login){
		System.out.println("Inside create login");
		sessionFactory.getCurrentSession().save(login);
	}

	
}
