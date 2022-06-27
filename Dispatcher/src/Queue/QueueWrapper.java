package Queue;

public abstract class QueueWrapper implements Queue {
    protected Queue queue;

    public QueueWrapper(Queue q) {
        queue = q;
    }

    // I don't implement methods insert and withdraw to force the decorator to implement them

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
