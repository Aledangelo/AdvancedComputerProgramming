package client;

import java.util.Hashtable;
import java.util.Random;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ClientThread extends Thread {
	private Random rnd;
	
	public ClientThread() {
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
			
			QueueConnectionFactory qconnf = (QueueConnectionFactory) context.lookup("QueueConnectionFactory");
			Queue req = (Queue) context.lookup("Request");
			
			QueueConnection conn = qconnf.createQueueConnection();
			QueueSession session = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			QueueSender sender = session.createSender(req);
			MapMessage msg = session.createMapMessage();
			msg.setString("method", "sendNotification");
			int id = 1 + this.rnd.nextInt(5);
			int criticality = 1 + this.rnd.nextInt(3);
			System.out.println("[ClientThread] Sending id: " + id + "; criticality: " + criticality);
			msg.setInt("id", id);
			msg.setInt("criticality", criticality);
			sender.send(msg);
			
			sender.close();
			session.close();
			conn.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
