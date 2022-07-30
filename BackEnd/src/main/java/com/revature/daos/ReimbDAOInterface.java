package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Reimb;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.User;

public interface ReimbDAOInterface {

	//Get all Reimbs
	ArrayList <Reimb> getAllReimbs();
	
	//Get Reimb by ID
	Reimb getReimbById(int reimb_id);
	
	//Insert a new Reimb
	boolean insertReimb(Reimb reimb);
	
	//Update Reimb Status
	boolean updateReimbStatus(int reimb_id, int new_reimb_status_id);
	
	//Delete Reimb with ID
	boolean deleteReimbWithId(int reimb_id);
	
	//Get ReimbStatus for Reimb
	ReimbStatus getReimbStatus(Reimb reimb);
	
	//Get ReimbType for Reimb
	ReimbType getReimbType(Reimb reimb);
	
	//Get reimbAuthor
	User getReimbAuthor(Reimb reimb);
	
	//Get reimbResolver
	User getReimbResolver(Reimb reimb);
	
}
