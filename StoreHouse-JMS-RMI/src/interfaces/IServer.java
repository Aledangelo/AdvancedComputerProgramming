package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
	public void insert(int value) throws RemoteException;
	public int get() throws RemoteException;
}
