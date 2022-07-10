package client;

import javax.jms.*;

public class ClientListener implements MessageListener {

	@Override
	public void onMessage(Message arg0) {
		MapMessage msg = (MapMessage) arg0;
		
		try {
			System.out.println("[ClientListener] Response: " + msg.getInt("response"));
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}
