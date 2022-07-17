package feedfinance;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class FeedFinanceListener implements MessageListener {

	@Override
	public void onMessage(Message arg0) {
		MapMessage msg = (MapMessage) arg0;
		
		FeedFinanceThread ft = new FeedFinanceThread(msg);
		ft.start();
	}

}
