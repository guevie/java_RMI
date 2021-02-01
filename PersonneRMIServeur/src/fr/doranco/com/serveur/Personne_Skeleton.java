package fr.doranco.com.serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import fr.doranco.com.Personne;

public class Personne_Skeleton extends Thread {

	private Personne personne;
	
	public Personne_Skeleton(PersonneServeur p) {
		this.personne = p;
	}
	
	public static void main(String[] arg) throws Throwable {
		final PersonneServeur p = new PersonneServeur();
		p.setAge(42);
		p.setNom("Coluche");
		final Personne_Skeleton skel = new Personne_Skeleton(p);
		skel.start();
	}
	
	public void run() {
		ServerSocket serverSocket;
		System.err.println("Server est actif");
		try {
			serverSocket = new ServerSocket(9000);
			while (true) {
				Socket socket = serverSocket.accept(); // attente
				while (socket != null) {
					ObjectInputStream inStream = null;
					try {
						inStream = new ObjectInputStream(socket.getInputStream());
					} catch (IOException e) {
						socket.close();
						break;
					}
					
					String service = (String) inStream.readObject();
					if (service.equals("age")) {
						System.out.println("Récupération de l'age");
						ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
						outStream.writeInt(this.personne.getAge());
						outStream.flush();
					} else if (service.equals("nom")) {
						System.out.println("Récupération du nom");
						ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
						outStream.writeObject(this.personne.getNom());
						outStream.flush();
					}
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
