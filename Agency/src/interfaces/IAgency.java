package interfaces;

import java.rmi.*;

public interface IAgency extends Remote {
	public void buy(int amount) throws RemoteException;
	public void sell(int amount) throws RemoteException;
}
