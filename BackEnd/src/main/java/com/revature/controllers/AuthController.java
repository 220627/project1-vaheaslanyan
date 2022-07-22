package com.revature.controllers;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.daos.AuthDAO;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.AuthService;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class AuthController {
	
	Gson gson = new Gson();
	
	AuthService authService = new AuthService();
	
	public static HttpSession session;
	
	public static boolean isLoggedIn(Context ctx) {
		
		if (session != null) {
			return true;
		} else {
			
			System.out.println("Access denied: User not logged in.");
			ctx.result("Access denied: Login required.");
			ctx.status(410);
			return false;
		}
		
	}
	
	public Handler loginHandler = (ctx) -> {
		
		String body = ctx.body();
		
		LoginDTO loginDTO = gson.fromJson(body, LoginDTO.class);
		
		User user = authService.login(loginDTO.getUsername(), loginDTO.getPassword());
		
		if (user != null) {
			
			System.out.println("User login successful.");
			ctx.result("Login successful");
			ctx.status(200);
			
			session = ctx.req.getSession();
		} else {
			
			System.out.println("Login attempt failed.");
			ctx.result("Login failed.");
			ctx.status(401);
		}
	
	};
		
	public Handler logoutHandler = (ctx) -> {
			
			session = null;
			ctx.result("Logged out.");
			ctx.status(200);
	};

}
