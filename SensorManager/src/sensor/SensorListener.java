package sensor;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import queue.IQueue;

public class SensorListener implements MessageListener {
    private IQueue queue;

    public SensorListener(IQueue queue) {
        this.queue = queue;
    }

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        
        try {
            TManager tManager = new TManager(textMessage.getText(), this.queue);
            tManager.start();
        } catch(JMSException e) {
            e.printStackTrace();
        }
    }
}
