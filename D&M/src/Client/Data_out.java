package Client;

import java.io.IOException;
import java.io.ObjectOutputStream;

import shared.Obj;

public class Data_out extends Thread{

	ObjectOutputStream out;
	Obj obj;
	String id;
	
	public Data_out(ObjectOutputStream out , Obj obj,String id) {
		this.out = out;
		this.obj = obj;
		this.id = id;
	}
	
	public void run() {
		try {
			out.writeObject(obj);
			out.flush();
			System.out.println(id+": 파일전송 완료");
		} catch (IOException e) {
			System.out.println("data_out error " + e.toString());
		}		
	}
}
