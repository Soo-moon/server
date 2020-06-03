package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import shared.Obj;
import shared.Player;
import shared.UserData;

public class Client extends Thread {

	Socket socket;
	InputStream in;
	ObjectInputStream oin;
	UserData userdata = new UserData();

	public Client(Socket socket) {
		this.socket = socket;
		try {
			in = socket.getInputStream();
			oin = new ObjectInputStream(in);
		} catch (IOException e) {
			System.out.println("client 초기화 에러" + e.getClass().getName() + ": " + e.getMessage());

		}
	}

	public void run() {
		try {
			Obj obj;
			while (true) {
				obj = (Obj) oin.readObject();

				// object 관리 코드 작성 할 것
				int code = obj.getcode();

				if (code == 1) {
					
					String message = obj.getstr();
					System.out.println(message);
				}

				else if (code == 2) {
					
					ArrayList<Player> arr = obj.getarray();
					for (int i = 0; i < arr.size(); i++)
						System.out.println(arr.get(i));

				} else if (code == 0) {

				}

			}

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("recv Error " + e.getClass().getName() + ": " + e.getMessage());

		}

	}

	public ObjectInputStream getoinstream() {
		return oin;
	}

	public InputStream getinstream() {
		return in;
	}

}
