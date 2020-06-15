package server;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import Client.Cl_Data;

public class WaitingList extends Thread {

	public HashMap<String, Cl_Data> client_list;
	
	
	public void run() {
		while(true) {
			HashMap<String, Cl_Data> client_list = Network.client_list;
			
			Iterator<String> mapIter = client_list.keySet().iterator();
			while(mapIter.hasNext()) {
				Cl_Data cl_Data = client_list.get(mapIter);
				String state = cl_Data.getState();
				
				if(state.equals("")) {
					
				}
			}
			
			System.out.println();
		}
	}
}
