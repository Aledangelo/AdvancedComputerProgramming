package coda;

public interface Coda {
    public void insert(int i);
    public int withdraw();
    public boolean full();
    public boolean empty();
    public int getSize();
}
