package store;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import queue.IQueue;
import queueimpl.CircularQueue;
import queueimpl.CircularQueueSynchr;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;

public class Store {
    public static void main(String[] args) {
        Hashtable <String, String> hash = new Hashtable<String, String>();  // Hash Table to define properties

        hash.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        hash.put("java.naming.provider.url", "tcp://127.0.0.1:61616");

        hash.put("queue.Request", "Request");

        try {
            Context context = new InitialContext(hash);

            QueueConnectionFactory qconn = (QueueConnectionFactory)context.lookup("QueueConnectionFactory");
            Queue queue = (Queue)context.lookup("Request");

            QueueConnection conn = qconn.createQueueConnection();
            conn.start();

            QueueSession session = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            QueueReceiver qReceiver = session.createReceiver(queue);
            IQueue circularQueue = new CircularQueueSynchr(new CircularQueue(5));

            StoreListener storeListener = new StoreListener(circularQueue, conn);
            qReceiver.setMessageListener(storeListener);

            System.out.println("[Store] Listener started!");
            
        } catch(NamingException e) {
            e.printStackTrace();
        } catch(JMSException e) {
            e.printStackTrace();
        }
    }
    
}

