package servernews;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServerNewsThread extends Thread {
	private String type;
	private String news;
	
	public ServerNewsThread(String news, String type) {
		this.news = news;
		this.type = type;
	}
	
	@Override
	public void run() {
		Hashtable<String, String> hash = new Hashtable<String, String>();
		
		hash.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		hash.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		hash.put("topic.Finance", "Finance");
		hash.put("topic.Sport", "Sport");
		
		try {
			Context context = new InitialContext(hash);
			
			TopicConnectionFactory tconnf = (TopicConnectionFactory) context.lookup("TopicConnectionFactory");
			Topic req;
			if(this.type.equals("sport")) {
				req = (Topic) context.lookup("Sport");
			} else {
				req = (Topic) context.lookup("Finance");
			}
			
			TopicConnection conn = tconnf.createTopicConnection();
			TopicSession session = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			TopicPublisher pub = session.createPublisher(req);
			MapMessage msg = session.createMapMessage();
			
			msg.setString("news", this.news);
			pub.send(msg);
			
			System.out.println("[ServerNewsThread] Sending news: " + this.news + "; type: " + this.type);
			
			pub.close();
			session.close();
			conn.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
