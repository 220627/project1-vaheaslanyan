package com.revature.models;

public class User {

	private int user_id;
	private String username;
	private String password;
	private String user_first_name;
	private String user_last_name;
	private String user_email;
	private int user_role_id_fk;
	
	private UserRole userRole;

	/* MARK: - Constructors --------------------------------------------------------------------- */
	
	//For getting User
	public User(int user_id, String username, String password, String user_first_name, String user_last_name,
			String user_email, int user_role_id_fk, UserRole userRole) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.user_first_name = user_first_name;
		this.user_last_name = user_last_name;
		this.user_email = user_email;
		this.user_role_id_fk = user_role_id_fk;
		this.userRole = userRole;
	}

	//For inserting new User.
	public User(String username, String password, String user_first_name, String user_last_name, String user_email,
			int user_role_id_fk) {
		super();
		this.username = username;
		this.password = password;
		this.user_first_name = user_first_name;
		this.user_last_name = user_last_name;
		this.user_email = user_email;
		this.user_role_id_fk = user_role_id_fk;
	}
	
	/* MARK: - Getters and Setters -------------------------------------------------------------- */

	//Getters
	public int getUser_id() {
		return user_id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getUser_first_name() {
		return user_first_name;
	}

	public String getUser_last_name() {
		return user_last_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public int getUser_role_id_fk() {
		return user_role_id_fk;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	//Setters
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}

	public void setUser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public void setUser_role_id_fk(int user_role_id_fk) {
		this.user_role_id_fk = user_role_id_fk;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
		
}
