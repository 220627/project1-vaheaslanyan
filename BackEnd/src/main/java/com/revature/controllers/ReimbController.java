package com.revature.controllers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.revature.daos.ReimbDAO;
import com.revature.models.Reimb;
//import com.revature.models.StatusForReimbDTO;

import io.javalin.http.Handler;

public class ReimbController {

	static final Logger log = LogManager.getLogger();
	Gson gson = new Gson();
	
	ReimbDAO reimbDAO = new ReimbDAO();
	
	public Handler getAllReimbsHandler = (ctx) -> {
		
		//Checking if user logged in
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		ArrayList<Reimb> reimbList = reimbDAO.getAllReimbs();
		String reimbListJson = gson.toJson(reimbList);
		
		log.info("GET Request from user with ID " + AuthController.session.getAttribute("userId") + " for all Reimbs successful");
		
		ctx.result(reimbListJson);
		ctx.status(200);
	};
	
	public Handler getReimbByIdHandler = (ctx) -> {
		
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		//Saving path parameter
		int reimbId = Integer.parseInt(ctx.pathParam("reimb_id"));
		
		//Getting Reimb with ID and saving it as JSON String
		Reimb reimb = reimbDAO.getReimbById(reimbId);
		String reimbJson = gson.toJson(reimb);
		
		log.info("GET Request for Reimb with ID " + reimbId + " successful");
		
		//Responding with JSON User object
		ctx.result(reimbJson);
		ctx.status(200);
	};
	
	public Handler insertReimbHandler = (ctx) -> {
	
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		String body = ctx.body();
		Reimb newReimb = gson.fromJson(body, Reimb.class);
		
		if (reimbDAO.insertReimb(newReimb)) {
			
			log.info("POST Request for new Reimb successful from user with ID " + AuthController.session.getAttribute("userId"));
			
			ctx.result("Reimb successfully added.");
			ctx.status(200);
		} else {
			
			log.warn("POST Request for Reimb failed.");
			
			ctx.result("Failed to add Reimb.");
			ctx.status(406);
		}
	};
	
	public Handler updateReimbStatusHandler = (ctx) -> {
		
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		int reimbId = Integer.parseInt(ctx.pathParam("reimb_id"));
		String reimbStatusIdFK = ctx.body();
		
		if (reimbDAO.updateReimbStatus(reimbId, Integer.parseInt(reimbStatusIdFK))) {
			
			log.info("PUT Request for Status for a Reimb successful.");
			
			ctx.result("Status for Reimb with ID " + reimbId + " successfully updated.");
			ctx.status(200);
		}
		
	};
	
	public Handler deleteReimbHandler = (ctx) -> {
	
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		int reimbId = Integer.parseInt(ctx.pathParam("reimb_id"));
		
		if (reimbDAO.deleteReimbWithId(reimbId)) {
			
			log.info("DELETE Request for Reimb successful.");
			
			ctx.result("Reimb successfully deleted.");
			ctx.status(200);
		} else {
			
			log.warn("DELETE Request for Reimb failed.");
			
			ctx.result("Failed to delete Reimb.");
			ctx.status(404);
		}
	};
}
