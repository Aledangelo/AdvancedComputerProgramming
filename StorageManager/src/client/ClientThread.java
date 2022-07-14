package client;

import java.rmi.RemoteException;
import java.util.Random;

import interfaces.IManager;

public class ClientThread extends Thread {
	private int nRequest;
	private IManager manager;
	private Random rnd;
	
	public ClientThread(IManager manager, int nRequest) {
		this.manager = manager;
		this.nRequest = nRequest;
		this.rnd = new Random();
	}
	
	@Override
	public void run() {
		int id;
		for(int i = 0; i < this.nRequest; i++) {
			
			try {
				Thread.sleep(1000 + this.rnd.nextInt(2001));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			id = 1 + this.rnd.nextInt(100);
			
			try {
				this.manager.depositRequest(id);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
			System.out.println("[ClientThread] Request sent with id: " + id);
		}
	}
}
