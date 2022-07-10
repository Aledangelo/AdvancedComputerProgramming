package queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import interfaces.IQueue;

public class QueueWrapperLock extends QueueWrapper {
	private Lock lock;
	private Condition empty;
	private Condition full;
	
	public QueueWrapperLock(IQueue queue) {
		super(queue);
		
		this.lock = new ReentrantLock();
		this.empty = this.lock.newCondition();
		this.full = this.lock.newCondition();
	}
	
	@Override
	public void insert(int value) {
		this.lock.lock();
		
		try {
			while(queue.full()) {
				this.empty.await();
			}
			
			queue.insert(value);
			this.full.signal();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}
		
	}
	@Override
	public int get() {
		int x = 0;
		
		this.lock.lock();
		
		try {
			while(queue.empty()) {
				this.full.await();
			}
			
			x = queue.get();
			this.empty.signal();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}
		
		return x;
	}
}
