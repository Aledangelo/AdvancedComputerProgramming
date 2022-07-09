package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IBroker;

public class Client {
	public static void main(String[] args) {
		int nThreads = 5;
		int nRequests = 25;
		int i;
		String name = new String("myBroker");
		
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IBroker broker = (IBroker) rmiRegistry.lookup(name);
			
			Thread[] threads = new Thread[nThreads];
			
			for(i = 0; i < nThreads; i++) {
				threads[i] = new ClientThread(broker, nRequests);
				threads[i].start();
			}
			
			for(i = 0; i < nThreads; i++) {
				try {
					threads[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(NotBoundException e) {
			e.printStackTrace();
		}
	}
}
