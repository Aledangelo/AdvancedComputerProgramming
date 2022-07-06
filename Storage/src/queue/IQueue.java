package queue;

public interface IQueue {
    public void insert(int value);
    public int withdraw();
    public boolean full();
    public boolean empty();
    public int getSize();
}
