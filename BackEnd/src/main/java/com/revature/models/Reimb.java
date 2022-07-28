package com.revature.models;

public class Reimb {
	
	private int reimb_id;
	private double reimb_amount;
	private String reimb_submitted;
	private String reimb_resolved;
	private String reimb_description;
	private String reimb_receipt_url;
	private boolean reimb_is_approved;
	private int reimb_type_id_fk;
	private int reimb_author_id_fk;
	private int reimb_resolver_id_fk;
	
	private ReimbType reimbType;
	private User reimbAuthor;
	private User reimbResolver;
	
	/* MARK: - Constructors --------------------------------------------------------------------- */
	
	//For getting Reimb
	public Reimb(int reimb_id, double reimb_amount, String reimb_submitted, String reimb_resolved,
			String reimb_description, String reimb_receipt_url, boolean reimb_is_approved, int reimb_type_id_fk,
			int reimb_author_id_fk, int reimb_resolver_id_fk, ReimbType reimbType, User author, User resolver) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_description = reimb_description;
		this.reimb_receipt_url = reimb_receipt_url;
		this.reimb_is_approved = reimb_is_approved;
		this.reimb_type_id_fk = reimb_type_id_fk;
		this.reimb_author_id_fk = reimb_author_id_fk;
		this.reimb_resolver_id_fk = reimb_resolver_id_fk;
		this.reimbType = reimbType;
		this.reimbAuthor = author;
		this.reimbResolver = resolver;
	}

	//For inserting reimb
//	public Reimb(double reimb_amount, String reimb_description, String reimb_receipt_url, int reimb_type_id_fk,
//			int reimb_author_id_fk, int reimb_resolver_id_fk) {
//		super();
//		this.reimb_amount = reimb_amount;
//		this.reimb_description = reimb_description;
//		this.reimb_receipt_url = reimb_receipt_url;
//		this.reimb_type_id_fk = reimb_type_id_fk;
//		this.reimb_author_id_fk = reimb_author_id_fk;
//		this.reimb_resolver_id_fk = reimb_resolver_id_fk;
//	}
	
	public Reimb(double reimb_amount, String reimb_description, String reimb_receipt_url, int reimb_type_id_fk,
			int reimb_author_id_fk) {
		super();
		this.reimb_amount = reimb_amount;
		this.reimb_description = reimb_description;
		this.reimb_receipt_url = reimb_receipt_url;
		this.reimb_type_id_fk = reimb_type_id_fk;
		this.reimb_author_id_fk = reimb_author_id_fk;
	}
	
	/* MARK: - Getters and Setters ---------------------------------------------------------------- */

	//Getters
	public int getReimb_id() {
		return reimb_id;
	}

	public double getReimb_amount() {
		return reimb_amount;
	}

	public String getReimb_submitted() {
		return reimb_submitted;
	}

	public String getReimb_resolved() {
		return reimb_resolved;
	}

	public String getReimb_description() {
		return reimb_description;
	}

	public String getReimb_receipt_url() {
		return reimb_receipt_url;
	}

	public boolean getReimb_is_approved() {
		return reimb_is_approved;
	}

	public int getReimb_type_id_fk() {
		return reimb_type_id_fk;
	}

	public int getReimb_author_id_fk() {
		return reimb_author_id_fk;
	}

	public int getReimb_resolver_id_fk() {
		return reimb_resolver_id_fk;
	}

	public ReimbType getReimbType() {
		return reimbType;
	}

	public User getReimbAuthor() {
		return reimbAuthor;
	}

	public User getReimbResolver() {
		return reimbResolver;
	}

	//Setters
	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public void setReimb_amount(double reimb_amount) {
		this.reimb_amount = reimb_amount;
	}

	public void setReimb_submitted(String reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}

	public void setReimb_resolved(String reimb_resolved) {
		this.reimb_resolved = reimb_resolved;
	}

	public void setReimb_description(String reimb_description) {
		this.reimb_description = reimb_description;
	}

	public void setReimb_receipt_url(String reimb_receipt_url) {
		this.reimb_receipt_url = reimb_receipt_url;
	}

	public void setReimb_is_approved(boolean reimb_is_approved) {
		this.reimb_is_approved = reimb_is_approved;
	}

	public void setReimb_type_id_fk(int reimb_type_id_fk) {
		this.reimb_type_id_fk = reimb_type_id_fk;
	}

	public void setReimb_author_id_fk(int reimb_author_id_fk) {
		this.reimb_author_id_fk = reimb_author_id_fk;
	}

	public void setReimb_resolver_id_fk(int reimb_resolver_id_fk) {
		this.reimb_resolver_id_fk = reimb_resolver_id_fk;
	}

	public void setReimbType(ReimbType reimbType) {
		this.reimbType = reimbType;
	}

	public void setReimbAuthor(User author) {
		this.reimbAuthor = author;
	}

	public void setReimbResolver(User resolver) {
		this.reimbResolver = resolver;
	}	
	
}
