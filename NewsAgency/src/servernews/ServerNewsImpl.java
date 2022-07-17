package servernews;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.IServerNews;

public class ServerNewsImpl extends UnicastRemoteObject implements IServerNews {
	private static final long serialVersionUID = 942555912224188404L;

	protected ServerNewsImpl() throws RemoteException {
		super();
	}

	@Override
	public synchronized void pubNews(String news, String type) throws RemoteException {
		ServerNewsThread thread = new ServerNewsThread(news, type);
		thread.start();
	}

}
