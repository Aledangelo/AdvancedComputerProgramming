package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import queue.CircularQueue;
import queue.QueueWrapperLock;

public class Server {
	public static void main(String[] args) {
		
		try {
			Registry rmi = LocateRegistry.getRegistry();
			ServerImpl server = new ServerImpl(new QueueWrapperLock(new CircularQueue(5)));
			
			rmi.rebind("myServer", server);
			
			System.out.println("[Server] Started");
		} catch(RemoteException e) {
			e.printStackTrace();
		}
	}
}
