package client;

import java.util.Hashtable;
import java.util.Random;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Client {
	public static void main(String[] args) {
		Random rnd = new Random();
		
		String printerName = args[0];
		int nReq = 5;
		
		Hashtable<String, String> hash = new Hashtable<String, String>();
		
		hash.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		hash.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		hash.put("topic.PrintRequest", "PrintRequest");
		
		try {
			Context context = new InitialContext(hash);
			
			TopicConnectionFactory tconnf = (TopicConnectionFactory) context.lookup("TopicConnectionFactory");
			Topic topic = (Topic) context.lookup("PrintRequest");
			
			TopicConnection conn = tconnf.createTopicConnection();
			TopicSession session = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			TopicPublisher sender = session.createPublisher(topic);
			MapMessage msg;
			
			for(int i = 0; i < nReq; i++) {
				msg = session.createMapMessage();
				
				String docName = new String("doc" + (1 + rnd.nextInt(40)));
				
				msg.setString("printerName", printerName);
				msg.setString("docName", docName);
				
				sender.send(msg);
				
				System.out.println("[Client] Message sent! PrinterName: " + printerName + "; DocName: " + docName);
			}
			
			sender.close();
			session.close();
			conn.close();
		} catch (NamingException | JMSException e) {
			e.printStackTrace();
		}
	}
}
