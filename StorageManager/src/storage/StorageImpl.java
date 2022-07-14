package storage;

import java.io.*;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StorageImpl extends StorageSkeleton {
	private PrintWriter pw;
	private Lock lock;
	private Random rnd;

	public StorageImpl(int port, String pathFile) {
		super(port);
		
		this.lock = new ReentrantLock();
		this.rnd = new Random();
		
		try {
			this.pw = new PrintWriter(new BufferedWriter(new FileWriter(pathFile, true)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean deposit(int item) {
		boolean check = false;
		
		this.lock.lock();
		
		try {
			Thread.sleep(5000 + this.rnd.nextInt(5001));
			
			System.out.println("[StorageImpl] Item: " + item);
			
			this.pw.println("Item: " + item);
			this.pw.flush();
			check = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}
		
		return check;
	}

}
