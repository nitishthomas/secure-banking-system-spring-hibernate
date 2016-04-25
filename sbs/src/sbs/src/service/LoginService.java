package sbs.src.service;

import java.util.List;

import sbs.src.form.Login;

public interface LoginService {

	public List<Login> listLogin();
	public void addLogin(Login login);
}
