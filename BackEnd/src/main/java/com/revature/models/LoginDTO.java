package com.revature.models;

public class LoginDTO {

	private String username; //Can also be email
	private String password;
	
	/* MARK: - Constructors --------------------------------------------------------------------- */
	
	public LoginDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsernameOrEmail() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsernameOrEmail(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
