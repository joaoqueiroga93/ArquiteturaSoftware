package service;

import java.io.IOException;

import dao.LoginDAO;
import model.Login;

public class LoginService {
	LoginDAO dao;
	
	public LoginService(LoginDAO dao){
		this.dao = dao;
	}
	
	public boolean validaUsuario(Login login) throws IOException{
		return dao.validaUsuario(login);
	}

}
