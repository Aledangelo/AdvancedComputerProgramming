package printer;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import interfaces.IPrinter;

public class PrinterImpl extends UnicastRemoteObject implements IPrinter {
	private static final long serialVersionUID = 1L;
	private PrintWriter pw;
	private Lock lock;
	
	public PrinterImpl(String pathFile) throws RemoteException {
		try {
			this.pw = new PrintWriter(new BufferedWriter(new FileWriter(pathFile, true)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.lock = new ReentrantLock();
	}
	
	@Override
	public void printDoc(String docName) throws RemoteException {
		
		this.lock.lock();
		
		try {
			Thread.sleep(5000);
			
			System.out.println("[PrinterImpl] DocName: " + docName);
			
			this.pw.println("DocName: " + docName);
			this.pw.flush();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}
	}

}
