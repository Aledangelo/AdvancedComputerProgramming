package store;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import queue.IQueue;

public class StoreThread extends Thread {
    private IQueue queue;
    private MapMessage mapMessage;
    private QueueConnection queueConnection;

    public StoreThread(IQueue queue, MapMessage mapMessage, QueueConnection queueConnection) {
        this.queue = queue;
        this.mapMessage = mapMessage;
        this.queueConnection = queueConnection;
    }

    @Override
    public void run() {

        try {
            String action = this.mapMessage.getString("op");
            int id = this.mapMessage.getInt("value");

            switch(action) {
                case "insert":
                    System.out.println("[StoreThread] Insert item with id=" + id + " in queue");
                    this.queue.insert(id);
                    break;
                case "withdraw":
                    System.out.println("[StoreThread] I take the article with id=" + id + " from queue");
                    id = this.queue.withdraw();

                    QueueSession session = this.queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
                    QueueSender sender = session.createSender((Queue) this.mapMessage.getJMSReplyTo());

                    MapMessage response = session.createMapMessage();
                    response.setString("op", "withdrawn_id");
                    response.setInt("value", id);

                    sender.send(response);

                    sender.close();
                    session.close();
                    break;
                default:
                    System.out.println("[StoreThread] Invalid Action!");
                    break;
            }
        } catch(JMSException e) {
            e.printStackTrace();
        }
    }
}
