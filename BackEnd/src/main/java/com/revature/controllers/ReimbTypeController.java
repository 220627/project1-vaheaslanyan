package com.revature.controllers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.revature.daos.ReimbTypeDAO;
import com.revature.models.ReimbType;

import io.javalin.http.Handler;

public class ReimbTypeController {

	static final Logger log = LogManager.getLogger();
	Gson gson = new Gson();
	
	ReimbTypeDAO reimbTypeDAO = new ReimbTypeDAO();
	
	public Handler getAllReimbTypesHandler = (ctx) -> {
		
		//Checking if user logged in
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		ArrayList<ReimbType> reimbTypeList = reimbTypeDAO.getAllReimbTypes();
		String reimbTypeListJson = gson.toJson(reimbTypeList);
		
		log.info("GET Request for all ReimbTypes successful.");

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
		
		log.info("GET Request for ReimbType by ID successful.");
		
		//Responding with JSON User object
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
			
			log.info("POST Request for ReimbType successful.");
			
			ctx.result("ReimbType " + newReimbType.getReimb_type_name() + " successfully added.");
			ctx.status(200);
		} else {
			
			log.warn("POST Request for ReimbType failed.");
			
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
			
			log.info("DELETE Request for ReimbType successful.");
			
			ctx.result("ReimbType successfully deleted.");
			ctx.status(200);
		} else {
			
			log.info("DELETE Request for ReimbType failed.");
			
			ctx.result("Failed to delete ReimbType.");
			ctx.status(404);
		}
	};
	
}
