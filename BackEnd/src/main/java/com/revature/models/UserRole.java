package com.revature.models;

public class UserRole {
	
	private int user_role_id;
	private String user_role_name;
	
	/* MARK: - Constructors --------------------------------------------------------------------- */
	
	//For getting Role(s)
	public UserRole(int user_role_id, String user_role_name) {
		super();
		this.user_role_id = user_role_id;
		this.user_role_name = user_role_name;
	}

	//For inserting new Role
	public UserRole(String user_role_name) {
		super();
		this.user_role_name = user_role_name;
	}
	
	/* MARK: - Getters and Setters -------------------------------------------------------------- */
	
	//Getters
	public int getUser_role_id() {
		return user_role_id;
	}

	public String getUser_role_name() {
		return user_role_name;
	}

	//Setters
	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}

	public void setUser_role_name(String user_role_name) {
		this.user_role_name = user_role_name;
	}

}
