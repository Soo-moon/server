package server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import DB.OracleTest;
import shared.Obj;
import shared.UserData;

public class Network extends Thread {

	ServerSocket serverSocket;
	Socket socket;
	ObjectInputStream oin = null;
	Obj obj = null;
	Client client;
	

	public void run() {

		try {
			serverSocket = new ServerSocket(6000);
			System.out.println("���� ����");

			while (true) {
				socket = serverSocket.accept();
				System.out.println("Ŭ���̾�Ʈ ����");
				client = new Client(socket);
				oin = client.getoinstream();
				filecheck();
				client.start();

			}

		} catch (IOException e) {
			System.out.println("Network Error " + e.getClass().getName() + ": " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void filecheck() throws IOException, ClassNotFoundException, InterruptedException {
		obj = (Obj) oin.readObject();
		String filename = "./userdata/" + obj.getid();
		try {
			FileInputStream fis = new FileInputStream(filename);
			// ���� ���� ���� 
			
			//���������͸� Ŭ���̾�Ʈ userdata�� �ֱ�
			ObjectInputStream ois = new ObjectInputStream(fis);
			Obj obj = (Obj) ois.readObject();
			client.userdata = obj.getUserData();
			
		} catch (FileNotFoundException e) {
			// �ű� ���� ����
			
			//����Ŭ ������ ������
			OracleTest oracleTest = new OracleTest("select * from CARD WHERE CARD_TEAM = 'SK'");
			oracleTest.start();
			oracleTest.join();
			
			//�⺻�����͸� Ŭ���̾�Ʈ userdata�� �ֱ�
			client.userdata.setTeamdata(oracleTest.getarray());
			client.userdata.setid(obj.getid());
			
			//���ο� ���� �����
			obj= new Obj(client.userdata,0);			
			FileOutThread fileOutThread = new FileOutThread(obj,client.userdata.getid());
			fileOutThread.start();
	
		}
	}

}
