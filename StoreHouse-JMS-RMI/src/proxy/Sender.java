package proxy;

import java.util.Hashtable;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Sender extends Thread {
	private int response;
	
	public Sender(int response) {
		this.response = response;
	}
	
	@Override
	public void run() {
		Hashtable<String, String> hash = new Hashtable<String, String>();
		
		hash.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		hash.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		hash.put("queue.Response", "Response");
		
		try {
			Context context = new InitialContext(hash);
			Queue resQueue = (Queue) context.lookup("Response");
			
			QueueConnectionFactory qConnf = (QueueConnectionFactory) context.lookup("QueueConnectionFactory");
			
			QueueConnection qConn = qConnf.createQueueConnection();
			
			QueueSession session = (QueueSession) qConn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender sender = session.createSender(resQueue);
			
			MapMessage msg = session.createMapMessage();
			
			msg.setInt("response", this.response);
			
			System.out.println("[Sender] Sending " + this.response + " to Response queue");
			
			sender.send(msg);
			
			sender.close();
			session.close();
		} catch (NamingException | JMSException e) {
			e.printStackTrace();
		}
	}
}
