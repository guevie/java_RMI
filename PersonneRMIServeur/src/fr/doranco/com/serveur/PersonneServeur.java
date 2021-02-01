package fr.doranco.com.serveur;

import fr.doranco.com.Personne;

public class PersonneServeur implements Personne {

	private int age;
	private String nom;
	
	@Override
	public int getAge() {
		return this.age;
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
