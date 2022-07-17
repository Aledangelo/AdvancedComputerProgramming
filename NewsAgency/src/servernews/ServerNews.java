package servernews;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IServerNews;

public class ServerNews {
	public static void main(String[] args) {
		try {
			Registry rmi = LocateRegistry.getRegistry();
			
			IServerNews server = new ServerNewsImpl();
			
			rmi.rebind("myServerNews", server);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
