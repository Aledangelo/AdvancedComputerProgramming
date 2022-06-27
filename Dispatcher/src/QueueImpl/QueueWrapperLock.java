package QueueImpl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Queue.QueueWrapper;
import Queue.Queue;

public class QueueWrapperLock extends QueueWrapper {
    // Lock and Condition Variables
    Lock lock;
    Condition full;
    Condition empty;

    public QueueWrapperLock(Queue q) {
        super(q);

        lock = new ReentrantLock();
        empty = lock.newCondition();
        full = lock.newCondition();
    }
    
    public void insert(int x) {
        // Synchronization through locks and condition variables
        try {
            lock.lock();
            try {
                while(queue.full()) {
                    empty.await();
                }

                queue.insert(x);
                full.signal();
            } finally {
                lock.unlock();
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int withdraw() {
        int x = 0;
        try {
            lock.lock();
            try {
                while(queue.empty()) {
                    full.await();
                }

                x = queue.withdraw();
                empty.signal();
            } finally {
                lock.unlock();
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        return x;
    }
}
