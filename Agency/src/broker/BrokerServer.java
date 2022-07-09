package broker;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IBroker;

public class BrokerServer {
	public static void main(String[] args) {
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			
			IBroker broker = new BrokerImpl();
			rmiRegistry.rebind("myBroker", broker);
			
			System.out.println("[BrokerServer] Broker started!");
		} catch(RemoteException e) {
			e.printStackTrace();
		}
	}
}
