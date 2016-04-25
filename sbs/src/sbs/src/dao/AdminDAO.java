package sbs.src.dao;

import java.util.List;

import sbs.src.form.Admintransactionrequest;
import sbs.src.form.Authorities;
import sbs.src.form.Externaluseraccount;
import sbs.src.form.Externalusertransaction;
import sbs.src.form.Login;
import sbs.src.form.Registeruser;

public interface AdminDAO {

	public List<Admintransactionrequest> listRequest();

	public List<Registeruser> listUserRequest();

	public Externalusertransaction submitExternalUserTransaction(int tid);

	public Externaluseraccount getaccountinfo(String username);

	public boolean validateaccountnumber(Integer receiveraccountnumber);

	public boolean validateuseramount(String username, Integer amount);

	public boolean deleteamount(String username, Integer receiveraccountnumber,
			Integer amount);

	public int  setSuccessAdminTransaction(int tid);

	public Registeruser getRegisterUserAccountInfo(int tid);

	public int saveLoginUser(Login loginuser);

	

	public int saveuserAuthority(Authorities auth);

	public int saveExternalAccount(Externaluseraccount externaluseraccount);

	public int updateRegisterUser(int tid);
}
