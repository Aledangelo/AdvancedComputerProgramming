package sensor;

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

import queueimpl.CircularQueue;
import queueimpl.QueueWrapperLock;

public class Sensor {
    private static final int D = 5;
    public static void main(String[] args) {
        Hashtable<String, String> hash = new Hashtable<String, String>();

        hash.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        hash.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
        hash.put("topic.SensorRequest", "SensorRequest");

        try {
            Context context = new InitialContext(hash);
            TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) context.lookup("TopicConnectionFactory");
            TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();
            topicConnection.start();

            TopicSession session = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic sensorRequest = (Topic) context.lookup("SensorRequest");
            TopicSubscriber subscriber = session.createSubscriber(sensorRequest);
            QueueWrapperLock queueWrapperLock = new QueueWrapperLock(new CircularQueue(D));
            SensorListener sensorListener = new SensorListener(queueWrapperLock);
            subscriber.setMessageListener(sensorListener);

            System.out.println("[Sensor] Started!");

            String pathFile = args[0];
            TExtecutor tExtecutor = new TExtecutor(pathFile, queueWrapperLock);
            tExtecutor.start();
        } catch(JMSException e) {
            e.printStackTrace();
        } catch(NamingException e) {
            e.printStackTrace();
        }
    }
}
