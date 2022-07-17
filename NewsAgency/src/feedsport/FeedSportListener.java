package feedsport;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class FeedSportListener implements MessageListener {

	@Override
	public void onMessage(Message arg0) {
		MapMessage msg = (MapMessage) arg0;
		
		FeedSportThread ft = new FeedSportThread(msg);
		ft.start();
	}

}
