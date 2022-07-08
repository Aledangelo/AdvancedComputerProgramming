package client;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Client {
    public static void main(String[] args) {
        int data = Integer.valueOf(args[0]).intValue();
        int port = Integer.valueOf(args[1]).intValue();

        Hashtable<String, String> hashtable = new Hashtable<String, String>();

        hashtable.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        hashtable.put("java.namin.provider.url", "tcp://127.0.0.1:61616");
        hashtable.put("queue.Request", "Request");

        try {
            Context context = new InitialContext(hashtable);
            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) context.lookup("QueueConnectionFactory");
            Queue rQueue = (Queue) context.lookup("Request");

            QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
            QueueSession session = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            QueueSender sender = session.createSender(rQueue);
            MapMessage mapMessage = session.createMapMessage();

            mapMessage.setString("op", "save");
            mapMessage.setInt("data", data);
            mapMessage.setInt("port", port);

            sender.send(mapMessage);

            System.out.println("[Client] Sending Data: " + data + " and Port: " + port);

            sender.close();
            session.close();
            queueConnection.close();
        } catch(JMSException e) {
            e.printStackTrace();
        } catch(NamingException e) {
            e.printStackTrace();
        }
    }
}
