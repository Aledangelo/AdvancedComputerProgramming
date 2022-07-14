package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IManager;

public class Client {
	public static void main(String[] args) {
		String name = new String("myManager");
		int nThread = 10;
		Thread[] threads = new Thread[nThread];
		
		int i;
		try {
			Registry rmi = LocateRegistry.getRegistry();
			IManager manager = (IManager) rmi.lookup(name);
			
			for(i = 0; i < nThread; i++) {
				threads[i] = new ClientThread(manager, 5);
				threads[i].start();
			}
			
			for(i = 0; i < nThread; i++) {
				threads[i].join();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
