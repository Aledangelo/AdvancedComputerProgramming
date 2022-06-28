package QueueImpl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import queue.Queue;
import queue.QueueWrapper;

public class QueueWrapperLock extends QueueWrapper {
    private Lock lock;
    private Condition empty;
    private Condition full;

    public QueueWrapperLock(Queue q) {
        super(q);

        this.lock = new ReentrantLock();
        this.empty = lock.newCondition();
        this.full = lock.newCondition();
    }

    public int withdraw() {
        int x = 0;
        try {
            lock.lock();

            try {
                while(queue.empty()) {
                    full.await();;
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

    public void insert(int value) {
        try {
            lock.lock();

            try {
                while(queue.full()) {
                    empty.await();
                }

                queue.insert(value);
                full.signal();
            } finally {
                lock.unlock();
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
