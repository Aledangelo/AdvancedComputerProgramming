package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMessageServer extends Remote {
	public boolean publish(String topic, String message) throws RemoteException;
	public void subscribe(String topic, IMessageListener listener) throws RemoteException;
}
