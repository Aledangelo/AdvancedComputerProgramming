package proxy;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IServer;

public class Proxy {
	public static void main(String[] args) {
		
		Registry rmi;
		try {
			System.out.println("[Proxy] Started!");
			
			rmi = LocateRegistry.getRegistry();
			IServer server = (IServer) rmi.lookup("myServer");
			
			Receiver receiver = new Receiver(server);
			
			System.out.println("[Proxy] Receiver created!");
			
			receiver.start();
			receiver.join();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
