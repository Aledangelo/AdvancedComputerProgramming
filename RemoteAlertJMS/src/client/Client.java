package client;

public class Client {
	public static void main(String[] args) {
		int nThread = 3;
		int i;
		Thread[] threads = new Thread[nThread];
		
		for(i = 0; i < nThread; i++) {
			threads[i] = new ClientThread();
			threads[i].start();
		}
		
		for(i = 0; i < nThread; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
