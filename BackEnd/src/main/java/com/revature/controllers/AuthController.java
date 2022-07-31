package com.revature.controllers;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.AuthService;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class AuthController {
	
	static final Logger log = LogManager.getLogger();
	public static HttpSession session;
	Gson gson = new Gson();
	
	AuthService authService = new AuthService();
	
	//Method to check if user is authenticated. This method is used in other controller handlers
	public static boolean isLoggedIn(Context ctx) {
		
		if (session != null) {
			return true;
		} else {
			
			log.warn("User access denied: Login required.");
			
			ctx.result("Access denied: Login required.");
			ctx.status(410);
			return false;
		}
		
	}
	
	public Handler loginHandler = (ctx) -> {
		
		//Getting Json from HTTP body
		String body = ctx.body();
		
		//Parsing Json to LoginDTO object, if email instead of username is passed it must still be passed as username in Json
		LoginDTO loginDTO = gson.fromJson(body, LoginDTO.class);
		
		User user = null;
		
		try {
			
			//Instantiate a User object if authService.login is successful
			user = authService.login(loginDTO.getUsernameOrEmail(), loginDTO.getPassword());
		} catch (Exception e) {
			
			log.warn("Exception occurred when assigning User object at login: " + e);
			e.printStackTrace();
		}
		
		
		//If user obj successfully instantiated set session
		if (user != null) {
			
			String userJson = gson.toJson(user);
			int userId = user.getUser_id();
			int userRoleId = user.getUser_role_id_fk();
			
			log.info("Login successfult for user with id " + userId);
			
			ctx.result(userJson);
			ctx.status(200);
			
			//Creating a session and setting userId attribute
			session = ctx.req.getSession(true);
			session.setAttribute("userId", userId);
			session.setAttribute("userRoleId", userRoleId);
		} else {
			
			log.warn("User login attempt failed.");
			
			ctx.result("Login failed.");
			ctx.status(401);
		}
	
	};
		
	public Handler logoutHandler = (ctx) -> {
		
			log.info("Logout successful for user with ID " + session.getAttribute("userId"));
		
			session = null;
			
			ctx.result("Logged out.");
			ctx.status(200);
	};

}
