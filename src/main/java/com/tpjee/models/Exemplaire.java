package com.tpjee.models;

public class Exemplaire {
	private Integer id;
	private String codeExp;
	private String documentID;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodeExp() {
		return codeExp;
	}
	public void setCodeExp(String codeExp) {
		this.codeExp = codeExp;
	}
	public String getDocumentID() {
		return documentID;
	}
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}
	@Override
	public String toString() {
		return "Exemplaire [id=" + id + ", codeExp=" + codeExp + ", documentID=" + documentID + "]";
	}
	
	
	
}
