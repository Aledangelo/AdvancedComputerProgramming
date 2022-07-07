package queue;

public abstract class QueueWrapper implements IQueue {
    protected IQueue queue;

    public QueueWrapper(IQueue queue) {
        this.queue = queue;
    }

    public boolean empty() {
        return this.queue.empty();
    }

    public boolean full() {
        return this.queue.full();
    }

    public int getSize() {
        return this.queue.getSize();
    }
}
