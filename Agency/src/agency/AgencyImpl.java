package agency;

import java.rmi.RemoteException;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class AgencyImpl extends AgencySkeleton {
	
	private int numTicket;
	
	private Lock lock;
	private Condition okBuy;
	
	public AgencyImpl(int numTicket, int port) throws RemoteException {
		super(port);
		
		this.numTicket = numTicket;
		
		this.lock = new ReentrantLock();
		this.okBuy = this.lock.newCondition();
	}
	
	@Override
	public void buy(int amount) throws RemoteException {
		Random rnd = new Random();
		this.lock.lock();
		
		try {
			Thread.sleep(4000 + rnd.nextInt(4001));
			
			while(amount > this.numTicket) {
				this.okBuy.await();
			}
			
			this.numTicket = this.numTicket - amount;
			
		} catch(InterruptedException e) {
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}
		
		System.out.println("[AgencyImpl] Buy " + amount + " tickets");
	}
	
	@Override
	public void sell(int amount) throws RemoteException {
		Random rnd = new Random();
		
		try {
			Thread.sleep(4000 + rnd.nextInt(4001));
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		this.lock.lock();
		
		try {
			this.numTicket = this.numTicket + amount;
			this.okBuy.signal();
		} finally {
			this.lock.unlock();
		}
		
		System.out.println("[AgencyImpl] Sell " + amount + " tickets");
	}
}
