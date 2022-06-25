package codaImpl;

import java.util.concurrent.locks.*;

import coda.*;

public class CodaWrapperLock extends CodaWrapper{
    
    // Istanzio il mutex e le condition variable
    private Lock lock;
    private Condition full;
    private Condition empty;

    public CodaWrapperLock(Coda c) {
        super(c);

        // Con i lock posso creare più condizioni (con synchronized no)
        lock = new ReentrantLock();

        empty = lock.newCondition();
        full = lock.newCondition();
    }

    public void insert(int e) {
        lock.lock();

        try {
            while (coda.full()) {
                try {
                    empty.await();
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }

            coda.insert(e);
            full.signal();
        } finally {
            lock.unlock();
        }
    }

    public int withdraw() {
        int x = 0;

        lock.lock();
        try {
            while (coda.empty()) {
                try {
                    full.await();
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }

            x = coda.withdraw();
            empty.signal();
        } finally {
            lock.unlock();
        }

        return x;
    }
}
