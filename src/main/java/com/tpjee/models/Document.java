package com.tpjee.models;

public class Document {
	private String ISBN;
	private String libelle;
	private String description;
	private String domaine;
	private Integer maisonEdition;
	private Integer auteur;
	private Integer type;
	
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDomaine() {
		return domaine;
	}
	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}
	
	public Integer getMaisonEdition() {
		return maisonEdition;
	}
	public void setMaisonEdition(Integer maisonEdtion) {
		this.maisonEdition = maisonEdtion;
	}
	public Integer getAuteur() {
		return auteur;
	}
	public void setAuteur(Integer auteur) {
		this.auteur = auteur;
	}
	
}
