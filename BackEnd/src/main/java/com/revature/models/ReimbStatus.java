package com.revature.models;

public class ReimbStatus {
	
	private int reimb_status_id;
	private String reimb_status_name;
	
	/* MARK: - Constructors --------------------------------------------------------------------- */
	
	// For getting ReimbStatus
	public ReimbStatus(int reimb_status_id, String reimb_status_name) {
		super();
		this.reimb_status_id = reimb_status_id;
		this.reimb_status_name = reimb_status_name;
	}

	//For inserting ReimbStatus
	public ReimbStatus(String reimb_status_name) {
		super();
		this.reimb_status_name = reimb_status_name;
	}

	/* MARK: - Getters and Setters -------------------------------------------------------------- */
	
	//Getters
	public int getReimb_status_id() {
		return reimb_status_id;
	}

	public String getReimb_status_name() {
		return reimb_status_name;
	}

	//Setters
	public void setReimb_status_id(int reimb_status_id) {
		this.reimb_status_id = reimb_status_id;
	}

	public void setReimb_status_name(String reimb_status_name) {
		this.reimb_status_name = reimb_status_name;
	}
	
	
	
	

}
