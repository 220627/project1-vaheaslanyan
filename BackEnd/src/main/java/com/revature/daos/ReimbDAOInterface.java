package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Reimb;
import com.revature.models.ReimbType;
import com.revature.models.User;

public interface ReimbDAOInterface {

	//Get all Reimbs
	ArrayList <Reimb> getAllReimbs();
	
	//Get Reimb by ID
	Reimb getReimbById(int reimb_id);
	
	//Insert a new Reimb
	Reimb insertReimb(Reimb reimb);
	
	//Delete Reimb with ID
	Reimb deleteReimb(Reimb reimb);
	
	//Get ReimbType for Reimb
	ReimbType getReimbType(Reimb reimb);
	
	//Get reimbAuthor
	User getReimbAuthor(Reimb reimb);
	
	//Get reimbResolver
	User getReimbResolver(Reimb reimb);
	
}
