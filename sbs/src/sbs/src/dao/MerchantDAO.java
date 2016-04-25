package sbs.src.dao;

import java.util.List;










import sbs.src.form.Externaluseraccount;
import sbs.src.form.Login;
import sbs.src.form.Merchantaccount;
import sbs.src.form.Merchanttransaction;
import sbs.src.form.Registeruser;

public interface MerchantDAO {
	List<Merchanttransaction> listRequest();

	List<Registeruser> listUserRequest();
	
	public Externaluseraccount getuserinfo(int accountnumber);
	public boolean savemerchanttransaction(Merchanttransaction merchanttransaction);
	public Externaluseraccount externalaccountnumber(Integer accountnumber );

	boolean validateExternaluseraccountnumber(Integer accountnumber);

	public Externaluseraccount getExternaluserinfo(Integer customeraccountnumber);
	public Login getLoginuserinfo(String username);

	public Merchantaccount getMerchantaccountinfo(String username);

	public Merchanttransaction getMerchantTransaction(int merchantTransactionId);

	public int completeMoneyTransaction(Integer merchantAccountnumber,
			Integer customeraccountnumber, String amount);

	public int updateMerchantTransaction(Integer transactionid);

	public int createExternalUserTransaction(Merchanttransaction merchantTransaction , String externalusername);

	
	
}
