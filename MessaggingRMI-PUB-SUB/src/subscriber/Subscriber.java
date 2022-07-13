package subscriber;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IMessageListener;
import interfaces.IMessageServer;

public class Subscriber {
	public static void main(String[] args) {
		String topic = args[0];
		
		try {
			Registry rmi = LocateRegistry.getRegistry();
			IMessageServer ms = (IMessageServer) rmi.lookup("myServer");
			
			IMessageListener listener = new MessageListenerImpl();
			ms.subscribe(topic, listener);
			
			System.out.println("[Subscriber] Subscribed to message Server");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
