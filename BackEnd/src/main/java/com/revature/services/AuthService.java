package com.revature.services;

import com.revature.daos.AuthDAO;
import com.revature.models.User;

public class AuthService {

	AuthDAO authDAO = new AuthDAO();
	
	public User login(String username, String password) {
		
		if (authDAO.login(username, password) != null) {
			return authDAO.login(username, password);
		}
		
		return null;
		
	}
	
}
