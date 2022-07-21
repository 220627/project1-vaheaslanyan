package com.revature.models;

public class ReimbType {

	private int reimb_type_id;
	private String reimb_type_name;
	
	/* MARK: - Constructors --------------------------------------------------------------------- */
	
	//For getting ReimbType
	public ReimbType(int reimb_type_id, String reimb_type_name) {
		super();
		this.reimb_type_id = reimb_type_id;
		this.reimb_type_name = reimb_type_name;
	}

	//For inserting ReimbType
	public ReimbType(String reimb_type_name) {
		super();
		this.reimb_type_name = reimb_type_name;
	}


	/* MARK: - Getters and Setters -------------------------------------------------------------- */
	
	//Getters
	public int getReimb_type_id() {
		return reimb_type_id;
	}


	public String getReimb_type_name() {
		return reimb_type_name;
	}

	//Setters
	public void setReimb_type_id(int reimb_type_id) {
		this.reimb_type_id = reimb_type_id;
	}


	public void setReimb_type_name(String reimb_type_name) {
		this.reimb_type_name = reimb_type_name;
	}
	
}
