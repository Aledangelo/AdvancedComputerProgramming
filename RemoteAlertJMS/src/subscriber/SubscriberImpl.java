package subscriber;

import java.io.*;

public class SubscriberImpl extends SubscriberSkeleton {
	private PrintWriter writer;
	private int id;

	public SubscriberImpl(int port, String pathFile, int id) {
		super(port);
		this.id = id;
		
		try {
			this.writer = new PrintWriter(new BufferedWriter(new FileWriter(pathFile, true)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notifyAlert(int criticality) {
		System.out.println("[SubscriberImpl] criticality: " + criticality);
		this.writer.println("criticality: " + criticality);
		this.writer.flush();
	}

	@Override
	public void setID(int id) {
		this.id = id;
		
	}

	@Override
	public int getID() {
		return this.id;
	}
	
	

}
