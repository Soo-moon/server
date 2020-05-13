package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import shared.Player;

public class server  {
	
	public static void main(String[] args) throws IOException {
		
		Network network = new Network();
		network.start();
	
	}

}
