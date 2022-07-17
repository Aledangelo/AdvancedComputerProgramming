package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerNews extends Remote {
	public void pubNews(String news, String type) throws RemoteException;
}
