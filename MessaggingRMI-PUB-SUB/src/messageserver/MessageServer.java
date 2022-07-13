package messageserver;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MessageServer {
	public static void main(String[] args) {
		try {
			Registry rmi = LocateRegistry.getRegistry();
			
			MessageServerImpl ms = new MessageServerImpl();
			rmi.rebind("myServer", ms);
			
			System.out.println("[MessageServer] Started!");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
