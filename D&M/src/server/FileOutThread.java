package server;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import shared.Obj;

public class FileOutThread extends Thread{

	Obj obj;
	String id;
	
	public FileOutThread(Obj obj, String id) {
		this.obj = obj;
		this.id = id;
	}
	
	public FileOutThread(String id) {this.id=id;}
	
	public  void run() {
		FileOutputStream fileOutputStream =null;
			
		try {
			String filename = "./userdata/"+id;
			fileOutputStream = new FileOutputStream(filename);
			
			ObjectOutputStream objectOutputStream =new ObjectOutputStream(fileOutputStream);
			
			objectOutputStream.writeObject(obj);
			
			fileOutputStream.close();
			objectOutputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
