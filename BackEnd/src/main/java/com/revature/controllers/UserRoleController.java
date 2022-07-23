package com.revature.controllers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.revature.daos.UserRoleDAO;
import com.revature.models.UserRole;

import io.javalin.http.Handler;

public class UserRoleController {

	static final Logger log = LogManager.getLogger();
	Gson gson = new Gson();
	
	UserRoleDAO userRoleDAO = new UserRoleDAO();
	
	public Handler getAllUserRolesHandler = (ctx) -> {
		
		//Checking if user logged in
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		ArrayList<UserRole> userRoleList = userRoleDAO.getAllUserRoles();
		String userRoleListJson = gson.toJson(userRoleList);
		
		log.info("GET Request for all UserRoles successful.");
		
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
		
		log.info("GET Request for UserRole with ID successful.");
		
		//Responding with JSON User object
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
			
			log.info("POST Request for UserRole successful.");
			
			ctx.result("UserRole " + newUserRole.getUser_role_name() + " successfully added.");
			ctx.status(200);
		} else {

			log.warn("POST Request for UserRole failed.");
			
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
			
			log.info("DELETE Request for UserRole successful.");
			
			ctx.result("UserRole successfully deleted.");
			ctx.status(200);
		} else {
			
			log.warn("DELETE Request for UserRole failed.");
			
			ctx.result("Failed to delete UserRole.");
			ctx.status(404);
		}
	};
}
