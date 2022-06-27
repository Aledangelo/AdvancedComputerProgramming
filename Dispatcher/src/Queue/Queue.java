package Queue;

public interface Queue {
    public void insert(int x);
    public int withdraw();
    public boolean full();
    public boolean empty();
    public int getSize();
}
