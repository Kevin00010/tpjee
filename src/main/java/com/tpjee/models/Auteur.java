package com.tpjee.models;

public class Auteur {
	
	private Integer id;
	private String nom;
	private String prenom;
	private String nationalite;
	

//constructors
	
	public Auteur() {
		super();
	}
		
	public Auteur(String nom, String prenom, String nationalite) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.nationalite = nationalite;
	}

	public Auteur(Integer id, String nom, String prenom, String nationalite) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.nationalite = nationalite;
	}

//Getters and Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNationalite() {
		return nationalite;
	}
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	@Override
	public String toString() {
		return "Auteur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", nationalite=" + nationalite + "]";
	}
	
	
}
