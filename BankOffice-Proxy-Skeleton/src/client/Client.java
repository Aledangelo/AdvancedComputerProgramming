package client;

public class Client {
	public static void main(String[] args) {
		int nThread = 10;
		Thread[] threads = new Thread[nThread];
		int i;
		
		for(i = 0; i < nThread; i++) {
			threads[i] = new ClientThread(10, 9000);
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
