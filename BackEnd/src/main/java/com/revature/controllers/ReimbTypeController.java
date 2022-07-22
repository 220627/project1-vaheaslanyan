package com.revature.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.daos.ReimbTypeDAO;
import com.revature.models.ReimbType;

import io.javalin.http.Handler;

public class ReimbTypeController {

	Gson gson = new Gson();
	
	ReimbTypeDAO reimbTypeDAO = new ReimbTypeDAO();
	
	public Handler getAllReimbTypesHandler = (ctx) -> {
		
		//Checking if user logged in
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		ArrayList<ReimbType> reimbTypeList = reimbTypeDAO.getAllReimbTypes();
		
		String reimbTypeListJson = gson.toJson(reimbTypeList);
		
		System.out.println("Successful GET request.");
		ctx.result(reimbTypeListJson);
		ctx.status(200);
	};
	
	public Handler getReimbTypeByIdHandler = (ctx) -> {
		
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		//Saving path parameter
		int reimbTypeId = Integer.parseInt(ctx.pathParam("reimb_type_id"));
		
		//Getting ReimbType with ID and saving it as JSON String
		ReimbType reimbType = reimbTypeDAO.getReimbTypeById(reimbTypeId);
		String reimbTypeJson = gson.toJson(reimbType);
		
		//Responding with JSON User object
		System.out.println("Successful GET request.");
		ctx.result(reimbTypeJson);
		ctx.status(200);
	};
	
	public Handler insertReimbTypeHandler = (ctx) -> {
	
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		String body = ctx.body();
		
		ReimbType newReimbType = gson.fromJson(body, ReimbType.class);
		
		
		if (reimbTypeDAO.insertReimbType(newReimbType)) {
			
			ctx.result("ReimbType " + newReimbType.getReimb_type_name() + " successfully added.");
			ctx.status(200);
		} else {
			
			ctx.result("Failed to add ReimbType.");
			ctx.status(406);
		}
	};
	
	public Handler deleteReimbTypeHandler = (ctx) -> {
	
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		int reimbTypeId = Integer.parseInt(ctx.pathParam("reimb_type_id"));
		
		if (reimbTypeDAO.deleteReimbTypeWithId(reimbTypeId)) {
			
			ctx.result("ReimbType successfully deleted.");
			ctx.status(200);
		} else {
			
			ctx.result("Failed to delete ReimbType.");
			ctx.status(404);
		}
	};
	
}
