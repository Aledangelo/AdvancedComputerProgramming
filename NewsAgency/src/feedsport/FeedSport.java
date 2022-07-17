package feedsport;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class FeedSport {
	public static void main(String[] args) {
		Hashtable<String, String> hash = new Hashtable<String, String>();
		
		hash.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		hash.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		hash.put("topic.Sport", "Sport");
		
		try {
			Context context = new InitialContext(hash);
			
			TopicConnectionFactory tconnf = (TopicConnectionFactory) context.lookup("TopicConnectionFactory");
			Topic req = (Topic) context.lookup("Sport");
			
			TopicConnection conn = tconnf.createTopicConnection();
			conn.start();
			
			TopicSession session = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			TopicSubscriber sub = session.createSubscriber(req);
			
			FeedSportListener listener = new FeedSportListener();
			sub.setMessageListener(listener);
			
			System.out.println("[FeedSport] Listening!");
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
