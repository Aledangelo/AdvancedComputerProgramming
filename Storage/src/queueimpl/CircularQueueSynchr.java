package queueimpl;

import queue.IQueue;
import queue.QueueWrapper;

public class CircularQueueSynchr extends QueueWrapper {

    public CircularQueueSynchr(IQueue queue) {
        super(queue);
    }

    public void insert(int value) {
        synchronized(queue) {
            while(queue.full()) {

                try {
                    queue.wait();
                } catch(InterruptedException e) { e.printStackTrace(); }
            }

            queue.insert(value);

            queue.notifyAll();
        }
    }

    public int withdraw() {
        int x = 0;

        synchronized(queue) {
            while(queue.empty()) {
                try {
                    queue.wait();
                } catch(InterruptedException e) { e.printStackTrace(); }
            }

            x = queue.withdraw();

            queue.notifyAll();
        }

        return x;
    }
}