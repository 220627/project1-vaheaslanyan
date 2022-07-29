package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.ReimbStatus;

public interface ReimbStatusDAOInterface {

ArrayList <ReimbStatus> getAllReimbStatuses();
	
	//Get ReimbStatus by ID
	ReimbStatus getReimbStatusById(int reimt_type_id);
	
	//Insert ReimbStatus
	boolean insertReimbStatus(ReimbStatus reimbType);
	
	//Delete ReimbStatus with ID
	boolean deleteReimbStatusWithId(int reimb_type_id);

	
}
