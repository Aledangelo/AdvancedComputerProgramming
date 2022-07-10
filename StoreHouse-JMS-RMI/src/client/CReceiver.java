package client;

import java.util.Hashtable;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CReceiver extends Thread {
	
	@Override
	public void run() {
		System.out.println("[CReceiver] Started!");
		
		Hashtable<String, String> hash = new Hashtable<String, String>();
		
		hash.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		hash.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		hash.put("queue.Response", "Response");
		
		try {
			Context context = new InitialContext(hash);
			Queue qReq = (Queue) context.lookup("Response");
			
			QueueConnectionFactory qconnf = (QueueConnectionFactory) context.lookup("QueueConnectionFactory");
				
			QueueConnection conn = (QueueConnection) qconnf.createQueueConnection();
			conn.start();
			
			QueueSession session = (QueueSession) conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			QueueReceiver recv = session.createReceiver(qReq);
			
			ClientListener cl = new ClientListener();
			
			recv.setMessageListener(cl);
		} catch (NamingException | JMSException e) {
			e.printStackTrace();
		}
	}
}
