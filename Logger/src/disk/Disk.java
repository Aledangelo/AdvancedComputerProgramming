package disk;

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

public class Disk {
    public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<String, String>();

        hashtable.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        hashtable.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
        hashtable.put("queue.Request", "Request");

        try {
            Context context = new InitialContext(hashtable);
            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) context.lookup("QueueConnectionFactory");
            Queue rQueue = (Queue) context.lookup("Request");

            QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
            queueConnection.start();

            QueueSession session = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueReceiver qReceiver = session.createReceiver(rQueue);

            DiskListener diskListener = new DiskListener();
            qReceiver.setMessageListener(diskListener);

            System.out.println("[Disk] Listening!");
        } catch(JMSException e) {
            e.printStackTrace();
        } catch(NamingException e) {
            e.printStackTrace();
        }
    }
}
