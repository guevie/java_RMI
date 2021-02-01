package fr.doranco.com.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import fr.doranco.com.Personne;

public class Personne_Stub implements Personne {

	private Socket pSocket;
	
	public Personne_Stub() throws Throwable {
		pSocket = new Socket("localhost", 9000);
	}
	
	@Override
	public int getAge() {
		return (Integer) this.get("age");
	}

	@Override
	public String getNom() {
		return (String) this.get("nom");
	}
	
	private Object get(String champ) {
		ObjectOutputStream outStream;
		ObjectInputStream inStream;
		try {
			outStream = new ObjectOutputStream(pSocket.getOutputStream());
			outStream.writeObject(champ);
			outStream.flush();
			inStream = new ObjectInputStream(pSocket.getInputStream());
			if (champ == "age")
				return inStream.readInt();
			else if (champ == "nom")
				return (String) inStream.readObject();
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void close() throws Throwable {
		pSocket.close();
	}
}
