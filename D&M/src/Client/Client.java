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
			System.out.println("client �ʱ�ȭ ����" + e.getClass().getName() + ": " + e.getMessage());

		}
	}

	public void run() {
		try {
			Obj obj;
			boolean check = true;
			while (check) {
				obj = (Obj) oin.readObject();

				// object ���� �ڵ�
				int code = obj.getcode();

				switch (code) {
				// ���� ��û
				case 0:
					server.network.addwaitlist(this);
					break;
				// ���Ϻ��� ��û
				case 1:
					System.out.println(id + ": ���� ���� ��û");
					FileOutThread fileOutThread = new FileOutThread(obj, id);
					fileOutThread.start();
					break;

				case 2:
					System.out.println(id + ": ���� ��û");
					FileOutThread fileOutThread2 = new FileOutThread(obj, id);
					fileOutThread2.start();
					check = false;
					break;

				// code 3~4 ���� �˻� ��û
				case 3:
					System.out.println(id + ": �� �˻� ��û");
					String query = "select * from CARD WHERE CARD_TEAM = '" + obj.getstr() + "'";
					query(query);
					break;
				case 4:
					System.out.println(id + ": ���� �˻� ��û");
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
				System.out.println(id + ": ���� �Ϸ�");
			} catch (IOException e) {
				System.out.println(id + ": ���� ����");
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
		System.out.println(id + ": DBó�� �Ϸ�");

	}

}
