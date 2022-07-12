package queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import interfaces.IQueue;

public class QueueWrapperLock extends QueueWrapper {
	
	private Lock lock;
	private Condition full;
	private Condition empty;
	
	public QueueWrapperLock(IQueue queue) {
		super(queue);
		
		this.lock = new ReentrantLock();
		this.full = this.lock.newCondition();
		this.empty = this.lock.newCondition();
	}

	@Override
	public void insert(int id) {
		this.lock.lock();
		
		try {
			while(queue.full()) {
				this.empty.await();
			}
			
			queue.insert(id);
			
			this.full.signal();
		} catch (InterruptedException e) {
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
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}
		
		return x;
	}
	
	
}
