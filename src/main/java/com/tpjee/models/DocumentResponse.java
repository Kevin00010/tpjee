package com.tpjee.models;

public class DocumentResponse {
	
	private String ISBN;
	private String libelle;
	private String description;
	private Domain domaine;
	private MaisonEdition maisonEdition;
	private Auteur auteur;
	private TypeDocument type;
	private String cover;	
	public DocumentResponse() {
		super();
	}

	public DocumentResponse(String iSBN, String libelle, String description, Domain domaine, MaisonEdition maisonEdition,
			Auteur auteur, TypeDocument type, String cover) {
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

	public Domain getDomaine() {
		return domaine;
	}

	public void setDomaine(Domain domaine) {
		this.domaine = domaine;
	}

	public MaisonEdition getMaisonEdition() {
		return maisonEdition;
	}

	public void setMaisonEdition(MaisonEdition maisonEdition) {
		this.maisonEdition = maisonEdition;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public TypeDocument getType() {
		return type;
	}

	public void setType(TypeDocument type) {
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
		return "DocumentResponse [ISBN=" + ISBN + ", libelle=" + libelle + ", description=" + description + ", domaine="
				+ domaine + ", maisonEdition=" + maisonEdition + ", auteur=" + auteur + ", type=" + type + ", cover="
				+ cover + "]";
	}
	
	
	
	
	
}

