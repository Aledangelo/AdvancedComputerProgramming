package printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterImpl extends PrinterSkeleton {
	
	private PrintWriter writer;
	private Lock lock;
	
	public PrinterImpl(String pathFile, int port) {
		super(port);
		
		try {
			this.writer = new PrintWriter(new BufferedWriter(new FileWriter(pathFile, true)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.lock = new ReentrantLock();
	}

	@Override
	public void printDoc(String docName) {
		this.lock.lock();
		
		try {
			Thread.sleep(5000);
			
			System.out.println("[PrinterImpl] docName: " + docName);
			
			this.writer.println("docName: " + docName);
			this.writer.flush();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}
	}
	
}
