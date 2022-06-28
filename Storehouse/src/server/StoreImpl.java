package server;

import QueueImpl.CircularQueue;
import QueueImpl.QueueWrapperLock;

public class StoreImpl extends StoreSkeleton {
    private QueueWrapperLock queueWrapperLock_laptop;
    private QueueWrapperLock queueWrapperLock_smartphone;

    public StoreImpl(int size) {
        CircularQueue queue_l = new CircularQueue(size);
        queueWrapperLock_laptop = new QueueWrapperLock(queue_l);

        CircularQueue queue_s = new CircularQueue(size);
        queueWrapperLock_smartphone = new QueueWrapperLock(queue_s);
    }

    public int withdraw(String item) {
        int x = 0;

        switch(item) {
            case "laptop":
                x = queueWrapperLock_laptop.withdraw();
                break;
            case "smartphone":
                x = queueWrapperLock_smartphone.withdraw();
                break;
            default:
                System.out.println("[Error] Invalid Argument");
                break;
        }
        return x;
    }

    public void deposit(String item, int id) {
        switch(item) {
            case "laptop":
                queueWrapperLock_laptop.insert(id);
                break;
            case "smartphone":
                queueWrapperLock_smartphone.insert(id);
                break;
            default:
                System.out.println("[Error] Invalid Argument");
                break;
        }
    }
}
