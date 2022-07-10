package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.IServer;
import queue.QueueWrapperLock;

public class ServerImpl extends UnicastRemoteObject implements IServer {
	private static final long serialVersionUID = 64782658L;
	
	private QueueWrapperLock queue;
	
	
	public ServerImpl(QueueWrapperLock queue) throws RemoteException {
		this.queue = queue;
	}


	@Override
	public void insert(int value) throws RemoteException {
		this.queue.insert(value);
		System.out.println("[ServerImpl] Insert " + value + " in queue");
	}


	@Override
	public int get() throws RemoteException {
		int x = this.queue.get();
		System.out.println("[ServerImpl] Get " + x + " from queue");
		return x;
	}
	
	
}
