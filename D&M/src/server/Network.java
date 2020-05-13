package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Network extends Thread {

	ServerSocket serverSocket;
	Socket socket;

	

	public void run() {

		try {
			serverSocket = new ServerSocket(6000);
			System.out.println("서버 생성");

			socket = serverSocket.accept();
			System.out.println("클라이언트 연결");

			Data_instream in = new Data_instream(socket);
			in.start();
			
			Data_outstream out = new Data_outstream(socket);
			out.start();
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Network error" + e.getClass().getName() + ": " + e.getMessage());
		}
	}

}
