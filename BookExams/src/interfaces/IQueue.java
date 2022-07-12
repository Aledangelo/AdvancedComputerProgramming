package interfaces;

public interface IQueue {
	public void insert(int id);
	public int get();
	public boolean full();
	public boolean empty();
	public int getSize();
}
