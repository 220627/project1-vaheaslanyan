package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.ReimbType;
import com.revature.models.UserRole;

public interface ReimbTypeDAOInterface {
	
	ArrayList <ReimbType> getAllReimbTypes();
	
	//Get ReimbType by ID
	ReimbType getReimbTypeById(int reimt_type_id);
	
	//Insert ReimbType
	ReimbType insertReimbType(ReimbType reimbType);
	
	//Delete ReimbType with ID
	ReimbType deleteReimbType(ReimbType reimbType);

}
