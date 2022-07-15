package bankoffice;

import java.io.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class BankOfficeImpl extends BankOfficeSkeleton {
	
	private Random rnd;
	
	private Semaphore maxConcurrent;
	private Semaphore maxThread;
	
	private PrintWriter writer;

	public BankOfficeImpl(int port, String pathFile) {
		super(port);
		this.rnd = new Random();
		this.maxConcurrent = new Semaphore(3);
		this.maxThread = new Semaphore(5);
		
		try {
			this.writer = new PrintWriter(new BufferedWriter(new FileWriter(pathFile, true)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean sendRequest(int id_client) {
		boolean check = false;
		
		if(!this.maxThread.tryAcquire()) {
			return check;
		}
		
		try {
			this.maxConcurrent.acquire();
			
			Thread.sleep(1000 + this.rnd.nextInt(4001));
			
			System.out.println("[BankOfficeImpl] idClient: " + id_client);
			this.writer.println("idClient: " + id_client);
			this.writer.flush();
			check = true;
		} catch (InterruptedException e) {
			check = false;
			e.printStackTrace();
		} finally {
			this.maxConcurrent.release();
			this.maxThread.release();
		}
		
		return check;
	}
	
}
