package com.revature.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.daos.UserDAO;
import com.revature.models.User;

import io.javalin.http.Handler;

public class UserController {
	
	Gson gson = new Gson();
	
	UserDAO userDAO = new UserDAO();
	
	public Handler getAllUsersHandler = (ctx) -> {
		
		//Checking if user logged in
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		ArrayList<User> userList = userDAO.getAllUsers();
		
		String userListJson = gson.toJson(userList);
		
		System.out.println("Successful GET request.");
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
		
		//Responding with JSON User object
		System.out.println("Successful GET request.");
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
			
			ctx.result("User " + newUser.getUsername() + " successfully added.");
			ctx.status(200);
		} else {
			
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
			
			ctx.result("User successfully deleted.");
			ctx.status(200);
		} else {
			
			ctx.result("Failed to delete User.");
			ctx.status(404);
		}
	};
}
