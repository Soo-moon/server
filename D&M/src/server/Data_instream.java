package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import shared.Player;

public class Data_instream extends Thread {
	Socket socket;
	ArrayList<Player> players;
	ObjectInputStream in;
	Player player;
	int code = 0;
	
	public Data_instream(Socket socket) {this.socket = socket;}

	public Data_instream(Socket socket ,int code) {
		this.socket = socket;
		this.code = code;
	}
	

	public void run() {
		try {
			in = new ObjectInputStream(socket.getInputStream());

			while (true) {
				players = (ArrayList<Player>) in.readObject();
				Iterator<Player> it = players.iterator();
				while (it.hasNext()) {
					player = it.next();
					System.out.println(player.name);
				}
				if (in.readObject().equals("stop"))
					break;
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Input error " + e.getClass().getName() + ": " + e.getMessage());
		}

	}
}
