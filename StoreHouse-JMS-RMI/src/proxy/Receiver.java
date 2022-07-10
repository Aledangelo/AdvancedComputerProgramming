package proxy;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.jms.*;

import interfaces.IServer;

public class Receiver extends Thread {
	private IServer server;
	
	public Receiver(IServer server) {
		this.server = server;
	}
	
	@Override
	public void run() {
		Hashtable<String, String> hash = new Hashtable<String, String>();
		
		hash.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		hash.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		hash.put("queue.Request", "Request");
		
		try {
			Context context = new InitialContext(hash);
			Queue reqQueue = (Queue) context.lookup("Request");
			
			QueueConnectionFactory qconnf = (QueueConnectionFactory) context.lookup("QueueConnectionFactory");
			QueueConnection qconn = qconnf.createQueueConnection();
			qconn.start();
			
			System.out.println("[Receiver] Connection Started!");
			
			QueueSession session = (QueueSession) qconn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueReceiver receiver = session.createReceiver(reqQueue);
			
			ProxyListener listener = new ProxyListener(this.server);
			receiver.setMessageListener(listener);
			
			System.out.println("[Receiver] Listening!");
		} catch (NamingException | JMSException e) {
			e.printStackTrace();
		}
	}
}
