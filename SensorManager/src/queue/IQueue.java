package queue;

public interface IQueue {
    public void insert(String value);
    public String withdraw();
    public boolean full();
    public boolean empty();
    public int getSize();
}
