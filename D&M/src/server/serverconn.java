package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class serverconn {
	static ServerSocket serversocket = null;
	static Socket clientSocket = null;
	static DataOutputStream out =null;
	static DataInputStream in = null;
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		serversocket =new ServerSocket(5000);
		
		Thread outThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					out=new DataOutputStream(clientSocket.getOutputStream());
					String aa = "welcome";
					out.writeUTF(aa);
					out.flush();
					while(true) {
						System.out.println("Ÿ���� : ");
						System.out.println();
						String t = scan.nextLine();
						out.writeUTF(t);
						out.flush();
						
						if(t.equals("stop")) {
							break;
						}
					}
					
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.toString());
				}
			}
		});
		
		
		try {
			System.out.println("��������");
			clientSocket = serversocket.accept();
			System.out.println("Ŭ���̾�Ʈ ����");
			outThread.start();
						
			in = new DataInputStream(clientSocket.getInputStream());
			System.out.println(in.readUTF());

		}catch (Exception e) {
			// TODO: handle exception
			String error = e.toString();
			System.out.println(error);
		}
	}

}
