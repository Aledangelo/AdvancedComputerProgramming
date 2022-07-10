package client;

import java.util.Hashtable;
import java.util.Random;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CSender extends Thread {
	private int N;
	private Random rnd;
	
	public CSender(int N) {
		this.N = N;
		
		this.rnd = new Random();
	}
	
	@Override
	public void run() {
		Hashtable<String, String> hash = new Hashtable<String, String>();
		
		hash.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		hash.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		hash.put("queue.Request", "Request");
		
		try {
			Context context = new InitialContext(hash);
			Queue Squeue = (Queue) context.lookup("Request");
			
			QueueConnectionFactory qconnf = (QueueConnectionFactory) context.lookup("QueueConnectionFactory");
			QueueConnection qconn = qconnf.createQueueConnection();
			
			QueueSession session = (QueueSession) qconn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender sender = session.createSender(Squeue);
			
			System.out.println("[CSender] Started!");
			String type;
			int id;
			int t;
			
			for(int i = 0; i < this.N; i++) {
				
				try {
					Thread.sleep(1000 + rnd.nextInt(4001));
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				t = i % 2;
				
				if(t == 0) {
					type = new String("insert");
				} else {
					type = new String("get");
				}
				
				id = 1 + rnd.nextInt(100);
				
				try {
					MapMessage msg = session.createMapMessage();
					msg.setString("type", type);
					msg.setInt("id", id);
					
					System.out.println("[CSender] Sending Type: " + msg.getString("type") + "; ID: " + msg.getInt("id"));
					
					sender.send(msg);
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
			sender.close();
			session.close();
		} catch (NamingException | JMSException e2) {
			e2.printStackTrace();
		}
		
	}
}
