package com.revature.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.daos.ReimbDAO;
import com.revature.models.Reimb;

import io.javalin.http.Handler;

public class ReimbController {

	Gson gson = new Gson();
	
	ReimbDAO reimbDAO = new ReimbDAO();
	
	public Handler getAllReimbsHandler = (ctx) -> {
		
		//Checking if user logged in
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		ArrayList<Reimb> reimbList = reimbDAO.getAllReimbs();
		
		String reimbListJson = gson.toJson(reimbList);
		
		System.out.println("Successful GET request.");
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
		
		//Responding with JSON User object
		System.out.println("Successful GET request.");
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
			
			ctx.result("Reimb with ID " + newReimb.getReimb_id() + " successfully added.");
			ctx.status(200);
		} else {
			
			ctx.result("Failed to add Reimb.");
			ctx.status(406);
		}
	};
	
	public Handler deleteReimbHandler = (ctx) -> {
	
		if (!AuthController.isLoggedIn(ctx)) {
			return;
		}
		
		int reimbId = Integer.parseInt(ctx.pathParam("reimb_id"));
		
		if (reimbDAO.deleteReimbWithId(reimbId)) {
			
			ctx.result("Reimb successfully deleted.");
			ctx.status(200);
		} else {
			
			ctx.result("Failed to delete Reimb.");
			ctx.status(404);
		}
	};
}
