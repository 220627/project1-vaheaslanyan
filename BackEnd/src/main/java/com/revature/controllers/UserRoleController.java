package com.revature.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.daos.UserRoleDAO;
import com.revature.models.UserRole;

import io.javalin.http.Handler;

public class UserRoleController {

	Gson gson = new Gson();
	
	UserRoleDAO userRoleDAO = new UserRoleDAO();
	
	public Handler getAllUserRolesHandler = (ctx) -> {
		
		//Checking if user logged in
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		ArrayList<UserRole> userRoleList = userRoleDAO.getAllUserRoles();
		
		String userRoleListJson = gson.toJson(userRoleList);
		
		System.out.println("Successful GET request.");
		ctx.result(userRoleListJson);
		ctx.status(200);
	};
	
	public Handler getUserRoleByIdHandler = (ctx) -> {
		
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		//Saving path parameter
		int userRoleId = Integer.parseInt(ctx.pathParam("user_role_id"));
		
		//Getting UserRole with ID and saving it as JSON String
		UserRole userRole = userRoleDAO.getUserRoleById(userRoleId);
		String userRoleJson = gson.toJson(userRole);
		
		//Responding with JSON User object
		System.out.println("Successful GET request.");
		ctx.result(userRoleJson);
		ctx.status(200);
	};
	
	public Handler insertUserRoleHandler = (ctx) -> {
	
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		String body = ctx.body();
		
		UserRole newUserRole = gson.fromJson(body, UserRole.class);
		
		
		
		if (userRoleDAO.insertUserRole(newUserRole)) {
			
			ctx.result("UserRole " + newUserRole.getUser_role_name() + " successfully added.");
			ctx.status(200);
		} else {
			
			ctx.result("Failed to add UserRole.");
			ctx.status(406);
		}
	};
	
	public Handler deleteUserRoleHandler = (ctx) -> {
	
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		int userRoleId = Integer.parseInt(ctx.pathParam("user_role_id"));
		
		if (userRoleDAO.deleteUserRoleWithId(userRoleId)) {
			
			ctx.result("UserRole successfully deleted.");
			ctx.status(200);
		} else {
			
			ctx.result("Failed to delete UserRole.");
			ctx.status(404);
		}
	};
}
