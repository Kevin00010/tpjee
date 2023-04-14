package com.tpjee.models;

public class TypeDocument {
	private Integer id;
	private String designation;
	
	
	public TypeDocument() {
		super();
	}

	public TypeDocument(String designation) {
		super();
		this.designation = designation;
	}

	public TypeDocument(Integer id, String designation) {
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
		return "TypeDocument [id=" + id + ", designation=" + designation + "]";
	}

	

}
