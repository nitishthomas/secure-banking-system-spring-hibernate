package sbs.src.service;

import java.util.List;

import sbs.src.form.Admintransactionrequest;
import sbs.src.form.Registeruser;


public interface AdminService {
	public List<Admintransactionrequest> listRequest();

	public List<Registeruser> listUserRequest();

	public int submitExternalUserTransaction(int tid);

	public int submitExternalAccountRequest(int tid);
}
