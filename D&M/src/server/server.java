package server;

public class server  {
	
	public static WaitingList waitingList;
	public static Network network;
	public static void main(String[] args) {
		
		network = new Network();
		network.start();
		
		waitingList = new WaitingList();
		waitingList.start();
		
		while(true) {
			if(waitingList.getState() == Thread.State.TERMINATED) {
				System.out.println("dead");
			}
		}
		
	}

}
