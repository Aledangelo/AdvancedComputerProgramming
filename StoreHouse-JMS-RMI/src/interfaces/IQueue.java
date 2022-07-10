package interfaces;

public interface IQueue {
	public void insert(int value);
	public int get();
	public boolean empty();
	public boolean full();
	public int getSize();
}
