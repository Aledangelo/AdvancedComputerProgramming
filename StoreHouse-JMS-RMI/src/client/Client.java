package client;


public class Client {
	public static void main(String[] args) {		
		try {
			CSender cs = new CSender(10);
			cs.start();
			
			CReceiver cr = new CReceiver();
			cr.start();
			
			cs.join();
			cr.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
