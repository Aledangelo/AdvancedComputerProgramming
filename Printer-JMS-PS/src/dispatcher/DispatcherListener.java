package dispatcher;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class DispatcherListener implements MessageListener {
	@Override
	public void onMessage(Message arg0) {
		MapMessage msg = (MapMessage) arg0;
		
		DispatcherThread dt = new DispatcherThread(msg);
		dt.start();
	}

}
