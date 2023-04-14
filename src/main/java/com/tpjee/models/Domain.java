package com.tpjee.models;

public class Domain {
	private Integer id;
	private String designation;
	
	public Domain() {
		super();
	}

	public Domain(String designation) {
		super();
		this.designation = designation;
	}

	public Domain(Integer id, String designation) {
		super();
		this.id = id;
		this.designation = designation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "Domain [id=" + id + ", designation=" + designation + "]";
	}
	
	
	
}
