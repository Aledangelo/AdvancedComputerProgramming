package queue;

public abstract class QueueWrapper implements IQueue {
    protected IQueue queue;

    public QueueWrapper(IQueue q) {
        this.queue = q;
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
