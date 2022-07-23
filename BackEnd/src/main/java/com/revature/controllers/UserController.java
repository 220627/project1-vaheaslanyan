package com.revature.controllers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.revature.daos.UserDAO;
import com.revature.models.User;

import io.javalin.http.Handler;

public class UserController {
	
	static final Logger log = LogManager.getLogger();
	Gson gson = new Gson();
	
	UserDAO userDAO = new UserDAO();
	
	public Handler getAllUsersHandler = (ctx) -> {
		
		//Checking if user logged in
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		ArrayList<User> userList = userDAO.getAllUsers();
		String userListJson = gson.toJson(userList);
		
		log.info("GET Request for all Users successful.");
		
		ctx.result(userListJson);
		ctx.status(200);
	};
	
	public Handler getUserByIdHandler = (ctx) -> {
		
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		//Saving path parameter
		int userId = Integer.parseInt(ctx.pathParam("user_id"));
		
		//Getting user with ID and saving it as JSON String
		User user = userDAO.getUserById(userId);
		String userJson = gson.toJson(user);
		
		log.info("GET Request for User with ID successful.");
		
		//Responding with JSON User object
		ctx.result(userJson);
		ctx.status(200);
	};
	
	public Handler insertUserHandler = (ctx) -> {
	
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		String body = ctx.body();
		User newUser = gson.fromJson(body, User.class);
		
		if (userDAO.insertUser(newUser)) {
			
			log.info("POST Request for Users successful.");
			
			ctx.result("User " + newUser.getUsername() + " successfully added.");
			ctx.status(200);
		} else {
			
			log.warn("POST Request for all Users failed.");
			
			ctx.result("Failed to add User.");
			ctx.status(406);
		}
	};
	
	public Handler deleteUserHandler = (ctx) -> {
	
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		int userId = Integer.parseInt(ctx.pathParam("user_id"));
		
		if (userDAO.deleteUserWithId(userId)) {
			
			log.info("DELETE Request for User successful.");
			
			ctx.result("User successfully deleted.");
			ctx.status(200);
		} else {
			
			log.info("DELETE Request for User failed.");
			
			ctx.result("Failed to delete User.");
			ctx.status(404);
		}
	};
}
