package storage;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IManager;

public class Storage {
	public static void main(String[] args) {
		int port = Integer.valueOf(args[0]).intValue();
		String pathFile = args[1];
		
		try {
			Registry rmi = LocateRegistry.getRegistry();
			IManager manager = (IManager) rmi.lookup("myManager");
			
			StorageImpl storage = new StorageImpl(port, pathFile);
			
			manager.subscribe(port);
			
			System.out.println("[Storage] Subscribed to Manager!");
			
			storage.runSkeleton();
			
			System.out.println("[Storage] Started!");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
