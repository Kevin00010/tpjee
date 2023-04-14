package com.tpjee.models;

public class Document {
	
	private String ISBN;
	private String libelle;
	private String description;
	private Integer domaine;
	private Integer maisonEdition;
	private Integer auteur;
	private Integer type;
	private String cover;
	
	public Document() {
		super();
	}

	public Document(String iSBN, String libelle, String description, Integer domaine, Integer maisonEdition,
			Integer auteur, Integer type, String cover) {
		super();
		ISBN = iSBN;
		this.libelle = libelle;
		this.description = description;
		this.domaine = domaine;
		this.maisonEdition = maisonEdition;
		this.auteur = auteur;
		this.type = type;
		this.cover = cover;
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

	public Integer getDomaine() {
		return domaine;
	}

	public void setDomaine(Integer domaine) {
		this.domaine = domaine;
	}

	public Integer getMaisonEdition() {
		return maisonEdition;
	}

	public void setMaisonEdition(Integer maisonEdition) {
		this.maisonEdition = maisonEdition;
	}

	public Integer getAuteur() {
		return auteur;
	}

	public void setAuteur(Integer auteur) {
		this.auteur = auteur;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	@Override
	public String toString() {
		return "Document [ISBN=" + ISBN + ", libelle=" + libelle + ", description=" + description + ", domaine="
				+ domaine + ", maisonEdition=" + maisonEdition + ", auteur=" + auteur + ", type=" + type + ", cover="
				+ cover + "]";
	}
	
	
	
	
		
	
	
}
