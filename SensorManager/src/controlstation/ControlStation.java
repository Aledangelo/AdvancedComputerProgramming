package controlstation;

import java.util.Hashtable;
import java.util.Random;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ControlStation {
    private static final int N = 20;
    public static void main(String[] args) {
        Random rnd = new Random();

        Hashtable<String, String> hash = new Hashtable<String, String>();

        hash.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        hash.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
        hash.put("topic.SensorRequest", "SensorRequest");

        try {
            Context context = new InitialContext(hash);
            TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) context.lookup("TopicConnectionFactory");
            TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();
            TopicSession session = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic SensorRequest = (Topic) context.lookup("SensorRequest");
            TopicPublisher publisher = session.createPublisher(SensorRequest);

            for(int i = 0; i < N; i++) {

                try {
                    Thread.sleep(1000);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }

                TextMessage textMessage = session.createTextMessage();
                int choose = rnd.nextInt(3);
                String command;

                switch(choose) {
                    case 0:
                        command = new String("startSensor");
                        textMessage.setText(command);
                        break;
                    case 1:
                        command = new String("stopSensor");
                        textMessage.setText(command);
                        break;
                    default:
                        command = new String("read");
                        textMessage.setText(command);
                        break;
                }

                publisher.publish(textMessage);
                System.out.println("[ControlStation] Published message: " + command);
            }

            System.out.println("[ControlStation] Finish!");

        } catch(JMSException e) {
            e.printStackTrace();
        } catch(NamingException e) {
            e.printStackTrace();
        }
    }
}
