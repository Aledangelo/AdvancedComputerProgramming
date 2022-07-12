package computeunit;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ComputeUnit {
	public static void main(String[] args) {
		Hashtable<String, String> hash = new Hashtable<String, String>();
		
		hash.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		hash.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		hash.put("queue.Request", "Request");
		
		try {
			Context context = new InitialContext(hash);
			
			QueueConnectionFactory connf = (QueueConnectionFactory) context.lookup("QueueConnectionFactory");
			Queue request = (Queue) context.lookup("Request");
			
			QueueConnection conn = connf.createQueueConnection();
			conn.start();
			
			QueueSession session = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueReceiver recv = session.createReceiver(request);
			
			Compute compute = new Compute(5, "log.txt");
			
			ComputeListener cl = new ComputeListener(conn, compute);
			recv.setMessageListener(cl);
			
			System.out.println("[ComputeUnit] Listener Started!");
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
