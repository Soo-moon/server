package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import shared.Obj;

public class Data_instream extends Thread {

	ObjectInputStream in = null;
	Obj obj = null;

	public Data_instream(ObjectInputStream in) {
		this.in = in;
	}

	public void run() {
		try {
			obj = (Obj) in.readObject();
			System.out.println(obj.getcode());
			
			
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Input error " + e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}

	}
}
