package client;

public class Client {
	public static void main(String[] args) {
		int nThreads = 10;
		int i;
		
		Thread[] threads = new Thread[nThreads];
		
		for(i = 0; i < nThreads; i++) {
			threads[i] = new ClientThread();
			threads[i].start();
		}
		
		for(i = 0; i < nThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
