package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import shared.Obj;
import shared.Player;

public class Client extends Thread {

	Socket socket;

	public Client(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			InputStream in = socket.getInputStream();
			ObjectInputStream oin = new ObjectInputStream(in);
			Obj obj;

			while (true) {
				obj = (Obj) oin.readObject();
				
				// object 包府 内靛 累己 且 巴 
				ArrayList<Player> arr = obj.getarray();
				for(int i =0 ; i< arr.size(); i++)
					System.out.println(arr.get(i));
			}

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("recv Error " + e.getClass().getName() + ": " + e.getMessage());

		}

	}
}
