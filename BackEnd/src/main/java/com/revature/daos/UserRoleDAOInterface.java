package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.UserRole;

public interface UserRoleDAOInterface {

	//Get all UserRoles
	ArrayList <UserRole> getAllUserRoles();
	
	//Get UserRole by ID
	UserRole getUserRoleById(int user_role_id);
	
	//Insert UserRole
	boolean insertUserRole(UserRole userRole);
	
	//Delete UserRole with ID
	boolean deleteUserRoleWithId(int user_role_id);

}
