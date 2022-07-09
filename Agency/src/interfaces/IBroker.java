package interfaces;

import java.rmi.*;

public interface IBroker extends Remote {
	public void subscribe(int port) throws RemoteException;
	public void submit(int op, int amount) throws RemoteException;
}
