package fr.doranco.com.client;

public class Personne_Client {
	
	public static void main(String[] arg) throws Throwable {
		Personne_Stub p = new Personne_Stub();
		int age = p.getAge();
		String nom = p.getNom();
		p.close();
		System.out.println(nom+" est agé de "+age+" ans");
	}
}
