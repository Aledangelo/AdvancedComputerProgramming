package server;

import java.io.*;

import interfaces.IQueue;

public class StorageThread extends Thread {
	private IQueue queue;
	private PrintWriter pw;
	
	public StorageThread(IQueue queue, String pathFile) {
		this.queue = queue;
		
		try {
			this.pw = new PrintWriter(new BufferedWriter(new FileWriter(pathFile, true)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			for(int i = 0; i < this.queue.getSize(); i++) {
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				this.pw.println("ID: " + this.queue.get());
				this.pw.flush();
			}
		}
	}
}
