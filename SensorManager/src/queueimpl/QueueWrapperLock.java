package queueimpl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import queue.IQueue;
import queue.QueueWrapper;

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
    public void insert(String value) {
        this.lock.lock();

        try {
            while(this.queue.full()) {
                this.empty.await();
            }

            this.queue.insert(value);
            this.full.signal();
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

    @Override
    public String withdraw() {
        String x = null;

        this.lock.lock();

        try {
            while(this.queue.empty()) {
                this.full.await();
            }

            x = this.queue.withdraw();
            this.empty.signal();
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }

        return x;
    }
}
