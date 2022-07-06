package store;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.QueueConnection;

import queue.IQueue;

public class StoreListener implements MessageListener {
    private IQueue queue;
    private QueueConnection queueConnection;

    public StoreListener(IQueue queue, QueueConnection queueConnection) {
        this.queue = queue;
        this.queueConnection = queueConnection;
    }

    @Override
    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        StoreThread st = new StoreThread(this.queue, mapMessage, this.queueConnection);
        st.start();
    }
}
