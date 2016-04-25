package sbs.src.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sbs.src.form.*;

public interface UserService {

	public List<Login> listUser();
	public void addUser(Registeruser registeruser);
	public Login getuserinfo(String username);
	public Externaluseraccount getaccountinfo(String username);
    public void userlogout();
    public String moneytransfer(Externalusertransaction externalusertransaction);
	public boolean exists(String username);
	public String getuserauthority(String username);
	public List<Externalusertransaction> listTransaction(String username);
}
