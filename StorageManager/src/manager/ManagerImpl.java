package manager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import interfaces.IManager;
import interfaces.IStorage;

public class ManagerImpl extends UnicastRemoteObject implements IManager {
	private static final long serialVersionUID = -705390781373459187L;
	
	private Vector<IStorage> storages;
	private int currentIndex;

	public ManagerImpl() throws RemoteException {
		this.storages = new Vector<IStorage>();
		this.currentIndex = 0;
	}
	
	@Override
	public boolean depositRequest(int item) throws RemoteException {
		boolean res = false;
		
		int size = this.storages.size();
		res = this.storages.get(this.currentIndex % size).deposit(item);
		
		System.out.println("[ManagerImpl] Insert item: " + item + " in storage[" + this.currentIndex % size + "]");
		this.currentIndex++;
		
		return res;
	}

	@Override
	public void subscribe(int port) throws RemoteException {
		StorageProxy proxy = new StorageProxy(port);
		this.storages.add(proxy);
		System.out.println("[ManagerImpl] Added new Storage!");
	}
	
}
