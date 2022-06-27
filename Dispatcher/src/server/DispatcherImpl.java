package server;

import QueueImpl.CircularQueue;
import QueueImpl.QueueWrapperLock;

public class DispatcherImpl extends DispatcherSkeleton {
    private QueueWrapperLock queueLock;

    public DispatcherImpl(int size) {
        CircularQueue queue = new CircularQueue(size);
        queueLock = new QueueWrapperLock(queue);
    }

    @Override
    public int getCmd() {
        System.out.println("[Dispatcher] getCmd");
        return queueLock.withdraw();
    }

    @Override
    public void sendCmd(int cmd) {
        System.out.println("[Dispatcher] sendCmd");
        queueLock.insert(cmd);
    }
}
