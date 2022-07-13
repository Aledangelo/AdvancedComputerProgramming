package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMessageListener extends Remote {
	public void onMessage(String message) throws RemoteException;
	public String getTopic() throws RemoteException;
	public void setTopic(String topic) throws RemoteException;
}
