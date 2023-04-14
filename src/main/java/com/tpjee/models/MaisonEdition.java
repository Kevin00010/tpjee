package com.tpjee.models;

public class MaisonEdition {
	private Integer id;
	private String nomMaison;

	public MaisonEdition() {
		super();
	}

	public MaisonEdition(Integer id, String nomMaison) {
		super();
		this.id = id;
		this.nomMaison = nomMaison;
	}

	public MaisonEdition(String nomMaison) {
		super();
		this.nomMaison = nomMaison;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomMaison() {
		return nomMaison;
	}

	public void setNomMaison(String nomMaison) {
		this.nomMaison = nomMaison;
	}

	@Override
	public String toString() {
		return "MaisonEdition [id=" + id + ", nomMaison=" + nomMaison + "]";
	}

}
