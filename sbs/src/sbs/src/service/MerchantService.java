package sbs.src.service;

import java.util.List;



import sbs.src.form.Merchanttransaction;
import sbs.src.form.Registeruser;

public interface MerchantService {
	public List<Merchanttransaction> listRequest();

	public List<Registeruser> listUserRequest();
	
	 public int merchanttransfer(Merchanttransaction merchanttransaction);
	 public int  merchanttranferSendUserKey(Merchanttransaction merchanttransaction);
	 public int merchantusertransfer(int merchantTransactionId,String encryptedkey);
}
