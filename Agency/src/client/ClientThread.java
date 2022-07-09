package client;

import java.rmi.RemoteException;
import java.util.Random;

import interfaces.IBroker;

public class ClientThread extends Thread {
	private IBroker broker;
	private int nRequests;
	private Random rnd;
	
	public ClientThread(IBroker broker, int nRequests) {
		this.broker = broker;
		this.nRequests = nRequests;
		this.rnd = new Random();
	}
	
	@Override
	public void run() {
		for(int i = 0; i < this.nRequests; i++) {
			int op = 1 + this.rnd.nextInt(2);
			int amount = 1 + this.rnd.nextInt(10);
			
			try {
				this.broker.submit(op, amount);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
			System.out.println("[ClientThread] Submitted Op: " + op + "; Amount: " + amount);
		}
	}
}
