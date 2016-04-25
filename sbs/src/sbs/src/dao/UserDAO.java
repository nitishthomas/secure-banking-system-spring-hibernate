package sbs.src.dao;

import java.util.List;

import sbs.src.form.*;


public interface UserDAO {

public List<Login> listUser();

public Login getuserinfo(String username);

public void createUser(Registeruser registeruser);

public Externaluseraccount getaccountinfo(String username);

public boolean validateuseramount(String username ,  Integer amount);

public boolean validateaccountnumber(Integer accountnumber );

public boolean deleteamount(String username , Integer externalaccount , Integer amount);

public boolean exists(String username);

public String getuserauthority(String username);

public int externalusertransactionSave(Externalusertransaction externalusertransaction);

public void admintransactionrequestsave(Externalusertransaction externalusertransaction);

public List<Externalusertransaction> listTransaction(String username);



}

