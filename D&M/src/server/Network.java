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
			System.out.println("���� ����");
			
			while(true) {
				socket = serverSocket.accept();
				System.out.println("Ŭ���̾�Ʈ ����");
				Client client = new Client(socket);
				client.start();
				System.out.println("������ ����");
			}
			
			
	

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Network Error " + e.getClass().getName() + ": " + e.getMessage());
		}
	}

}
