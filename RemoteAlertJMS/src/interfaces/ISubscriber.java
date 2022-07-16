package interfaces;

public interface ISubscriber {
	public void notifyAlert(int criticality);
	public void setID(int id);
	public int getID();
}
