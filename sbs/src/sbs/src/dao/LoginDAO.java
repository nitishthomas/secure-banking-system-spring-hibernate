package sbs.src.dao;

import java.util.List;

import sbs.src.form.Login;

public interface LoginDAO {

public List<Login> listLogin();

public void createLogin(Login login);
}

