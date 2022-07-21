package com.revature;

import com.revature.daos.UserDAO;

public class Launcher {

	public static void main(String[] args) {
		
		System.out.println("Hello World");

		UserDAO userDAO = new UserDAO();
		
		System.out.println(userDAO.getAllUsers().toString());
	}
	
}


//               __
//          (___()'`;
//          /,    /`
//          \\"--\\