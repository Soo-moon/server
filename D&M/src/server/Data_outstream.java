package server;

import java.io.ObjectOutputStream;
import java.net.Socket;

import shared.Player;

public class Data_outstream extends Thread {

	Socket socket;
	Player player;
	
	
	public Data_outstream(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			String aa = "welcome";
			
			while (true) {
				if (player != null) {
					out.writeObject(player);
					out.flush();
					break;
				}
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
	}
	
}
