package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import shared.Player;

public class serverconn  {
	static ServerSocket serversocket = null;
	static Socket clientSocket = null;
	static ObjectOutputStream out = null;
	static ObjectInputStream in = null;
	static Scanner scan = new Scanner(System.in);
	static Player player = null;

	public static void main(String[] args) throws IOException {
		serversocket = new ServerSocket(5000);

		Thread outThread = new Thread(new Runnable() {
			//�׽�Ʈ
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					out = new ObjectOutputStream(clientSocket.getOutputStream());
					String aa = "welcome";
					//out.writeUTF(aa);
					//out.flush();
					while (true) {
						if (player != null) {
							out.writeObject(player);
							out.flush();
							break;
						}
					}

					while (true) {
						System.out.println("Ÿ���� : ");
						System.out.println();
						String t = scan.nextLine();
						out.writeUTF(t);
						out.flush();

						if (t.equals("stop")) {
							break;
						}
					}

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.toString());
				}
			}
		});

		Thread inThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					in = new ObjectInputStream(clientSocket.getInputStream());
					while (true) {
						player = (Player) in.readObject();
						System.out.println(player.name);
						if (in.readObject().equals("stop"))
							break;
					}
				} catch (Exception e) {
					// TODO: handle exceptio
					System.out.println(e);
				}
			}
		});

		try {
			System.out.println("��������");
			clientSocket = serversocket.accept();
			System.out.println("Ŭ���̾�Ʈ ����");
			outThread.start();
			inThread.start();

		} catch (Exception e) {
			// TODO: handle exception
			String error = e.toString();
			System.out.println(error);
		}
	}

}
