package feedsport;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.JMSException;
import javax.jms.MapMessage;

public class FeedSportThread extends Thread {
	private MapMessage message;
	private PrintWriter writer;
	
	public FeedSportThread(MapMessage message) {
		this.message = message;
		
		try {
			this.writer = new PrintWriter(new BufferedWriter(new FileWriter("sport.txt", true)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			String news = this.message.getString("news");
			
			System.out.println("[FeedSportThread] News: " + news);
			this.writer.println("News: " + news);
			this.writer.flush();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
