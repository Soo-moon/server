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
			System.out.println("서버 생성");
			
			while (true) {
				socket = serverSocket.accept();
				System.out.println("클라이언트 연결");
				
				client = new Client(socket);
				oin = client.getoinstream();
				
				// 내부데이터 검사
				String client_id = filecheck(); 
				client.id =client_id;
				client.start();
				
				//클라이언트 리스트 
				Cl_Data cl_data = new Cl_Data(client_id, socket, client.out,client.userdata);
				cl_data.Send(new Obj("환영합니다." , 101));
				
				client_list.put(client_id, cl_data);
		
				//테스트 코드
				System.out.println(client_id + "님 접속");
				
				
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
			// 기존 접속 유저 
			
			//서버데이터를 클라이언트 userdata에 넣기
			ObjectInputStream ois = new ObjectInputStream(fis);
			Obj obj = (Obj) ois.readObject();
			client.userdata = obj.getUserData();
			
			
			//클라이언트로 UserData 전송
			SendUserData(id);
			
			
		} catch (FileNotFoundException e) {
			// 신규 접속 유저
			
			//오라클 데이터 얻어오기
			//기본 팀
			Oracle oracleTest = new Oracle("select * from CARD WHERE CARD_TEAM = 'SK'" , id);
			oracleTest.start();
			oracleTest.join();
			
			//기본데이터를 클라이언트 userdata에 넣기
			client.userdata.setTeamdata(oracleTest.getarray());
			client.userdata.setid(obj.getid());
			
			
			//클라이언트로 UserData 전송
			SendUserData(id);
			
			//새로운 파일 만들기
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
