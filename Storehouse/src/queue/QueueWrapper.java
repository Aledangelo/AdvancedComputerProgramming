package queue;

public abstract class QueueWrapper implements Queue {
    protected Queue queue;

    public QueueWrapper(Queue q) {
        this.queue = q;
    }

    public boolean full() {
        return queue.full();
    }

    public boolean empty() {
        return queue.empty();
    }

    public int getSize() {
        return queue.getSize();
    }
}
