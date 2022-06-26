package server;

import service.RemoteCounter;

public class CounterImpl implements RemoteCounter {
    private int count;

    public CounterImpl() {
        count = 0;
    }

    public int get() {
        return count;
    }

    // Mutual Exclusion
    public synchronized int inc() {
        count = count + 1;
        return count;
    }

    public synchronized int sum(int value) {
        count = count + value;
        return count;
    }

    public synchronized int sqr() {
        count = count * count;
        return count;
    }
}
