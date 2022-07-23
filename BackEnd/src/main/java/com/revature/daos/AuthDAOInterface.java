package com.revature.daos;

import com.revature.models.User;

public interface AuthDAOInterface {

	User login(String usernameOrEmail, String password);
	
}
