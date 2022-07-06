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

public class Client {

    private static final int N = 10;
    public static void main(String[] args) {
        Random rnd = new Random();

        Hashtable<String, String> hashtable = new Hashtable<String, String>();

        hashtable.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        hashtable.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
        
        hashtable.put("queue.Request", "Request");
        hashtable.put("queue.Response", "Response");

        try {
            Context context = new InitialContext(hashtable);

            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) context.lookup("QueueConnectionFactory");
            Queue request = (Queue) context.lookup("Request");
            Queue response = (Queue) context.lookup("Response");

            QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
            queueConnection.start();

            QueueSession session = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            QueueReceiver queueReceiver = session.createReceiver(response);
            ClientListener clientListener = new ClientListener();
            queueReceiver.setMessageListener(clientListener);

            QueueSender sender = session.createSender(request);
            MapMessage message = session.createMapMessage();

            for(int i = 0; i < N; i++) {
                int x = rnd.nextInt(2);
                try {
                    Thread.sleep(2000);
                } catch(InterruptedException e) { e.printStackTrace(); }
                if(x > 0) {
                    message.setString("op", "insert");
                    int value = rnd.nextInt(101);
                    message.setInt("value", value);

                    message.setJMSReplyTo(response);

                    sender.send(message);
                    System.out.println("[Client] Message sent! Op: insert; Value: " + value);
                } else {
                    message.setString("op", "withdraw");
                    
                    message.setJMSReplyTo(response);

                    sender.send(message);

                    System.out.println("[Client] Message sent! Op: withdraw");
                }
            }
        } catch(JMSException e) {
            e.printStackTrace();
        } catch(NamingException e) {
            e.printStackTrace();
        }
    }
}
