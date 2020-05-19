package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import shared.Obj;

public class Network extends Thread {

	ServerSocket serverSocket;
	Socket socket;
	ObjectInputStream oin = null;
	Obj obj = null;

	public void run() {

		try {
			serverSocket = new ServerSocket(6000);
			System.out.println("서버 생성");
			
			while(true) {
				socket = serverSocket.accept();
				System.out.println("클라이언트 연결");
				Client client = new Client(socket);
				client.start();
				System.out.println("쓰레드 실행");
			}
			
			
	

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Network Error " + e.getClass().getName() + ": " + e.getMessage());
		}
	}

}
