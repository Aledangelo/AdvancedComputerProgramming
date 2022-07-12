package client;

public class Client {
	public static void main(String[] args) {
		int i;
		int port = Integer.valueOf(args[0]).intValue();
		Thread[] threads = new Thread[10];
		
		ServerProxy sp = new ServerProxy(port);
		
		for(i = 0; i < 10; i++) {
			threads[i] = new ClientThread(sp, 5);
			threads[i].start();
		}
		
		for(i = 0; i < 10; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
