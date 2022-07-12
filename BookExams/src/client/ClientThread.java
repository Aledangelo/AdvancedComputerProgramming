package client;

import java.util.Random;

import interfaces.IServer;

public class ClientThread extends Thread {
	private int nRequest;
	private IServer server;
	private Random rnd;
	
	public ClientThread(IServer server, int nRequest) {
		this.server = server;
		this.nRequest = nRequest;
		this.rnd = new Random();
	}
	
	@Override
	public void run() {
		boolean type;
		int id;
		
		for(int i = 0; i < this.nRequest; i++) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			int b = rnd.nextInt(2);
			if (b == 0) {
				type = false;
			} else {
				type = true;
			}
			
			id = 1 + rnd.nextInt(40);
			
			boolean res = this.server.book(id, type);
			System.out.println("[ClientThread] Book: " + res);
		}
	}
}
