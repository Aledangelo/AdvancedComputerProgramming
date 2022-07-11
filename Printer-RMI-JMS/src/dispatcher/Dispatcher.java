package dispatcher;

import java.util.Hashtable;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Dispatcher {
	public static void main(String[] args) {
		Hashtable<String, String> hash = new Hashtable<String, String>();
		
		hash.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		hash.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		hash.put("topic.PrintRequest", "PrintRequest");
		
		try {
			Context context = new InitialContext(hash);
			
			TopicConnectionFactory connf = (TopicConnectionFactory) context.lookup("TopicConnectionFactory");
			Topic topic = (Topic) context.lookup("PrintRequest");
			
			TopicConnection conn = connf.createTopicConnection();
			conn.start();
			
			TopicSession session = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			TopicSubscriber subscriber = session.createSubscriber(topic);
			
			DispatcherListener listener = new DispatcherListener();
			subscriber.setMessageListener(listener);
			
			System.out.println("[Dispatcher] Listening!");
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
