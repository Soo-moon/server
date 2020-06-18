package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import DB.Oracle;
import server.FileOutThread;
import server.Network;
import server.server;
import shared.Obj;
import shared.Player;
import shared.UserData;

public class Client extends Thread {

	Socket socket;

	InputStream in;
	ObjectInputStream oin;
	public ObjectOutputStream out;
	public String id;

	public UserData userdata = new UserData();

	public Client(Socket socket) {
		this.socket = socket;
		try {
			in = socket.getInputStream();
			oin = new ObjectInputStream(in);
			out = new ObjectOutputStream(socket.getOutputStream());

		} catch (IOException e) {
			System.out.println("client 초기화 에러" + e.getClass().getName() + ": " + e.getMessage());

		}
	}

	public void run() {
		try {
			Obj obj;
			boolean check = true;
			while (check) {
				obj = (Obj) oin.readObject();

				// object 관리 코드
				int code = obj.getcode();

				switch (code) {
				// 대전 요청
				case 0:
					server.network.addwaitlist(this);
					break;
				// 파일변경 요청
				case 1:
					System.out.println(id + ": 파일 변경 요청");
					FileOutThread fileOutThread = new FileOutThread(obj, id);
					fileOutThread.start();
					break;

				case 2:
					System.out.println(id + ": 종료 요청");
					FileOutThread fileOutThread2 = new FileOutThread(obj, id);
					fileOutThread2.start();
					check = false;
					break;

				// code 3~4 쿼리 검색 요청
				case 3:
					System.out.println(id + ": 팀 검색 요청");
					String query = "select * from CARD WHERE CARD_TEAM = '" + obj.getstr() + "'";
					query(query);
					break;
				case 4:
					System.out.println(id + ": 선수 검색 요청");
					String query2 = "select * from CARD WHERE CARD_NAME = '" + obj.getstr() + "'";
					query(query2);
					break;

				}
			}

		} catch (ClassNotFoundException | InterruptedException e) {
			System.out.println("recv Error " + e.getClass().getName() + ": " + e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getClass()+": "+e.getMessage());
		} finally {
			try {
				oin.close();
				out.close();
				in.close();
				socket.close();
				System.out.println(id + ": 종료 완료");
			} catch (IOException e) {
				System.out.println(id + ": 종료 오류");
			}

		}
	}

	public ObjectInputStream getoinstream() {
		return oin;
	}

	public InputStream getinstream() {
		return in;
	}
	
	public String getid() {
		return id;
	}

	private void query(String query) throws InterruptedException {
		Oracle oracle = new Oracle(query, id);
		oracle.start();
		oracle.join();
		Obj search = new Obj(oracle.getarray(), 103);

		Data_out data_out = new Data_out(out, search, id);
		data_out.start();
		System.out.println(id + ": DB처리 완료");

	}

}
