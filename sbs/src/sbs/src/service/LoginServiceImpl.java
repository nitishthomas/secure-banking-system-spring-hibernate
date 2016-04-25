package sbs.src.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sbs.src.dao.LoginDAO;
import sbs.src.form.Login;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
    private LoginDAO loginDAO;
     
  @Transactional
    public List<Login> listLogin() {
 
        return loginDAO.listLogin();
    }
  @Transactional
  public void addLogin(Login login){
	  loginDAO.createLogin(login);
  }
  

}
