package manager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Manager {
	public static void main(String[] args) {
		try {
			Registry rmi = LocateRegistry.getRegistry();
			
			ManagerImpl manager = new ManagerImpl();
			
			rmi.rebind("myManager", manager);
			
			System.out.println("[Manager] Saved manager with name 'myManager'");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
