package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Client.Cl_Data;
import shared.Obj;

public class WaitingList extends Thread {
	
	public static List <Cl_Data> userlist = new ArrayList<>();
	
	
	public void addList(Cl_Data cl_data) {
		userlist.add(cl_data);
		System.out.println(cl_data.getid() + ": 대전리스트 등록");
	}
	
	public void run() {
		while(true) {
				try {
					sleep(200);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			
			if(userlist.size() >= 2) {
				System.out.println("test start ");
				int one = userlist.get(0).getUserData().Teampoint();
				int two = userlist.get(1).getUserData().Teampoint();
				String big ="";
				String small ="";
				int gap;
				String winer ="";
				String loser ="";
				
				if(one > two) {
					gap = one - two;
					big = userlist.get(0).getid();
					small = userlist.get(1).getid();
				}
							
				else {
					gap = two - one;
					big = userlist.get(1).getid();
					small =userlist.get(0).getid();
				}
									
				switch(gap/10) {
				case 1: 
					if(new Random().nextDouble() <= 0.6) {
						winer = big;
						loser = small;
					}
						
					else {
						winer = small;
						loser =big;
					}
						
					break;
				case 2:
					if(new Random().nextDouble() <= 0.7) {
						winer = big;
						loser = small;
					}
						
					else {
						winer = small;
						loser =big;
					}
					break;
				case 3:
					if(new Random().nextDouble() <= 0.8) {
						winer = big;
						loser = small;
					}
						
					else {
						winer = small;
						loser =big;
					}
					break;
				default :
					if(new Random().nextDouble() <= 0.9) {
						winer = big;
						loser = small;
					}
						
					else {
						winer = small;
						loser =big;
					}
					break;
				}
				
				try {
					System.out.println("test");
					
					Network.client_list.get(winer).Send(new Obj(loser , 110));
					Network.client_list.get(loser).Send(new Obj(winer , 111));
					System.out.println("test end");
				} catch (IOException e) {
					e.printStackTrace();
				}				
				userlist.remove(0);
				userlist.remove(0);
			}
		}
	}
		
}
