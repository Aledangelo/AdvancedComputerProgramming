package publisher;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IMessageServer;

public class Publisher {
	public static void main(String[] args) {
		String topic = args[0];
		String message = args[1];
		
		try {
			Registry rmi = LocateRegistry.getRegistry();
			IMessageServer ms = (IMessageServer) rmi.lookup("myServer");
			
			ms.publish(topic, message);
			System.out.println("[Publisher] Published msg: " + message + " with topic: " + topic);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
