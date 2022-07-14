package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IManager extends Remote {
	public boolean depositRequest(int item) throws RemoteException;
	public void subscribe(int port) throws RemoteException;
}
