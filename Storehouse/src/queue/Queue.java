package queue;

public interface Queue {
    public void insert(int value);
    public int withdraw();
    public boolean full();
    public boolean empty();
    public int getSize();
}
