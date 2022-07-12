package client;

import java.util.Hashtable;
import java.util.Random;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ClientThread extends Thread {
	private Random rnd;
	
	public ClientThread() {
		this.rnd = new Random();
	}
	
	@Override
	public void run() {
		Hashtable<String, String> hash = new Hashtable<String, String>();
		
		hash.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		hash.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		hash.put("queue.Request", "Request");
		hash.put("queue.ReplSum", "ReplSum");
		hash.put("queue.ReplMul", "ReplMul");
		
		try {
			Context context = new InitialContext(hash);
			
			QueueConnectionFactory connf = (QueueConnectionFactory) context.lookup("QueueConnectionFactory");
			Queue request = (Queue) context.lookup("Request");
			Queue reply;
			
			String type;
			int selectType = this.rnd.nextInt(2);
			if(selectType == 0) {
				type = new String("sum");
				reply = (Queue) context.lookup("ReplSum");
			} else {
				type = new String("mul");
				reply = (Queue) context.lookup("ReplMul");
			}
			
			QueueConnection conn = connf.createQueueConnection();
			conn.start();
			
			QueueSession session = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender sender = session.createSender(request);
			MapMessage msg = session.createMapMessage();
			
			
			int op1 = this.rnd.nextInt(101);
			int op2 = this.rnd.nextInt(101);
			
			int t = 1000 + this.rnd.nextInt(4001);
			Thread.sleep(t);
			
			msg.setString("type", type);
			msg.setInt("op1", op1);
			msg.setInt("op2", op2);
			msg.setJMSReplyTo(reply);
			sender.send(msg);
			
			sender.close();
			
			QueueReceiver recv = session.createReceiver(reply);
			MapMessage response = session.createMapMessage();
			response = (MapMessage) recv.receive();
			
			System.out.println("[ClientThread] Response: " + response.getInt("res"));
			
			
			recv.close();
			session.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
