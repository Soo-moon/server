package Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import shared.Obj;
import shared.UserData;

public class Cl_Data {

	String id;
	Socket socket;
	ObjectOutputStream outstream;
	String state = "normal";
	UserData userData = new UserData();

	public Cl_Data(String id, Socket socket, ObjectOutputStream out, UserData userData) {
		this.id = id;
		this.socket = socket;
		this.outstream = out;
		this.userData = userData;		
	}

	public String getid() {
		return id;
	}

	public Socket getsocket() {
		return socket;
	}

	public void setState(int i) {
		switch (i) {
		case 0:
			this.state = "normal";
			break;
		case 1:
			this.state = "wait";
			break;
		case 2:
			this.state = "play";
			break;
		}
	}
	
	public String getState() {
		return state;
	}
	
	public UserData getUserData() {
		return userData;
	}

	public void Send(Obj obj) throws IOException {
		Data_out data_out = new Data_out(outstream, obj,id);
		data_out.start();
	}

}
