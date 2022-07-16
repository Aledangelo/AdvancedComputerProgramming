package subscriber;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.*;

public class Subscriber {
	public static void main(String[] args) {
		int id = Integer.valueOf(args[0]).intValue();
		int port = Integer.valueOf(args[1]).intValue();
		String fileName = args[2];
		
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
			msg.setString("method", "subscribe");
			msg.setInt("id", id);
			msg.setInt("port", port);
			sender.send(msg);
		} catch (NamingException | JMSException e) {
			e.printStackTrace();
		}
		
		SubscriberImpl sub = new SubscriberImpl(port, fileName, id);
		sub.runSkeleton();
	}
}
