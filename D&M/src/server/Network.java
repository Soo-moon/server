package server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import Client.Cl_Data;
import Client.Client;
import Client.Data_out;
import DB.Oracle;
import shared.Obj;

public class Network extends Thread {

	ServerSocket serverSocket;
	Socket socket;
	ObjectInputStream oin = null;
	Obj obj = null;
	Client client;
	static HashMap<String, Cl_Data> client_list = new HashMap<>();

	public void run() {

		try {
			
			serverSocket = new ServerSocket(5550);
			System.out.println("���� ����");
			
			while (true) {
				socket = serverSocket.accept();
				System.out.println("Ŭ���̾�Ʈ ����");
				
				client = new Client(socket);
				oin = client.getoinstream();
				
				// ���ε����� �˻�
				String client_id = filecheck(); 
				client.id =client_id;
				client.start();
				
				//Ŭ���̾�Ʈ ����Ʈ 
				Cl_Data cl_data = new Cl_Data(client_id, socket, client.out,client.userdata);
				cl_data.Send(new Obj("ȯ���մϴ�." , 101));
				
				client_list.put(client_id, cl_data);
		
				//�׽�Ʈ �ڵ�
				System.out.println(client_id + "�� ����");
				
				
			}

		} catch (IOException e) {
			System.out.println("Network Error " + e.getClass().getName() + ": " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	@SuppressWarnings("finally")
	public String filecheck() throws IOException, ClassNotFoundException, InterruptedException {
		obj = (Obj) oin.readObject();
		String id = obj.getid();
		String filename = "./userdata/" + obj.getid();
		try {
			FileInputStream fis = new FileInputStream(filename);
			// ���� ���� ���� 
			
			//���������͸� Ŭ���̾�Ʈ userdata�� �ֱ�
			ObjectInputStream ois = new ObjectInputStream(fis);
			Obj obj = (Obj) ois.readObject();
			client.userdata = obj.getUserData();
			
			
			//Ŭ���̾�Ʈ�� UserData ����
			SendUserData(id);
			
			
		} catch (FileNotFoundException e) {
			// �ű� ���� ����
			
			//����Ŭ ������ ������
			//�⺻ ��
			Oracle oracleTest = new Oracle("select * from CARD WHERE CARD_TEAM = 'SK'" , id);
			oracleTest.start();
			oracleTest.join();
			
			//�⺻�����͸� Ŭ���̾�Ʈ userdata�� �ֱ�
			client.userdata.setTeamdata(oracleTest.getarray());
			client.userdata.setid(obj.getid());
			
			
			//Ŭ���̾�Ʈ�� UserData ����
			SendUserData(id);
			
			//���ο� ���� �����
			obj= new Obj(client.userdata,0);			
			FileOutThread fileOutThread = new FileOutThread(obj,client.userdata.getid());
			fileOutThread.start();
	
		}
		finally {
			return id;
		}
	}
	
	
	public void SendUserData(String id) throws InterruptedException {
		Obj starter = new Obj(client.userdata , 100);
		Data_out data_out = new Data_out(client.out, starter ,id);
		data_out.start();
		data_out.join();
	}
	
	public static void addwaitlist(Client client) {
		server.waitingList.addList(client_list.get(client.getid()));
	}

}
