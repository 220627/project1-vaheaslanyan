package com.revature.controllers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.revature.daos.ReimbStatusDAO;
import com.revature.models.ReimbStatus;

import io.javalin.http.Handler;

public class ReimbStatusController {
	
	static final Logger log = LogManager.getLogger();
	Gson gson = new Gson();
	
	ReimbStatusDAO reimbStatusDAO = new ReimbStatusDAO();
	
	public Handler getAllReimbStatussHandler = (ctx) -> {
		
		//Checking if user logged in
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		ArrayList<ReimbStatus> reimbStatusList = reimbStatusDAO.getAllReimbStatuses();
		String reimbStatusListJson = gson.toJson(reimbStatusList);
		
		log.info("GET Request for all ReimbStatuss successful.");

		ctx.result(reimbStatusListJson);
		ctx.status(200);
	};
	
	public Handler getReimbStatusByIdHandler = (ctx) -> {
		
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		//Saving path parameter
		int reimbStatusId = Integer.parseInt(ctx.pathParam("reimb_status_id"));
		
		//Getting ReimbStatus with ID and saving it as JSON String
		ReimbStatus reimbStatus = reimbStatusDAO.getReimbStatusById(reimbStatusId);
		String reimbStatusJson = gson.toJson(reimbStatus);
		
		log.info("GET Request for ReimbStatus by ID successful.");
		
		//Responding with JSON User object
		ctx.result(reimbStatusJson);
		ctx.status(200);
	};
	
	public Handler insertReimbStatusHandler = (ctx) -> {
	
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		String body = ctx.body();
		ReimbStatus newReimbStatus = gson.fromJson(body, ReimbStatus.class);
		
		
		if (reimbStatusDAO.insertReimbStatus(newReimbStatus)) {
			
			log.info("POST Request for ReimbStatus successful.");
			
			ctx.result("ReimbStatus " + newReimbStatus.getReimb_status_name() + " successfully added.");
			ctx.status(200);
		} else {
			
			log.warn("POST Request for ReimbStatus failed.");
			
			ctx.result("Failed to add ReimbStatus.");
			ctx.status(406);
		}
	};
	
	public Handler deleteReimbStatusHandler = (ctx) -> {
	
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		int reimbStatusId = Integer.parseInt(ctx.pathParam("reimb_status_id"));
		
		if (reimbStatusDAO.deleteReimbStatusWithId(reimbStatusId)) {
			
			log.info("DELETE Request for ReimbStatus successful.");
			
			ctx.result("ReimbStatus successfully deleted.");
			ctx.status(200);
		} else {
			
			log.info("DELETE Request for ReimbStatus failed.");
			
			ctx.result("Failed to delete ReimbStatus.");
			ctx.status(404);
		}
	};

}
