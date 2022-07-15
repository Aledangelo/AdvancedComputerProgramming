package client;

import java.util.Hashtable;
import java.util.Random;

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

public class Client {
	public static void main(String[] args) {
		int port = Integer.valueOf(args[0]).intValue();
		Random rnd = new Random();
		
		Hashtable<String, String> hash = new Hashtable<String, String>();
		
		hash.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		hash.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		hash.put("topic.Request", "Request");
		
		try {
			Context context = new InitialContext(hash);
			
			TopicConnectionFactory tconnf = (TopicConnectionFactory) context.lookup("TopicConnectionFactory");
			Topic request = (Topic) context.lookup("Request");
			
			TopicConnection conn = tconnf.createTopicConnection();
			TopicSession session = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			TopicPublisher pub = session.createPublisher(request);
			for(int i = 0; i < 5; i++) {
				Thread.sleep(1000);
				
				MapMessage msg = session.createMapMessage();
				String docName = new String("doc" + (1 + rnd.nextInt(100)));
				msg.setString("docName", docName);
				msg.setInt("port", port);
				pub.send(msg);
				
				System.out.println("[Client] Sending docName: " + docName + " to port: " + port);
			}
			
			pub.close();
			session.close();
			conn.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
