package computeunit;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.QueueConnection;

public class ComputeListener implements MessageListener {
	
	private QueueConnection conn;
	private Compute compute;
	
	public ComputeListener(QueueConnection conn, Compute compute) {
		this.conn = conn;
		this.compute = compute;
	}

	@Override
	public void onMessage(Message arg0) {
		MapMessage msg = (MapMessage) arg0;
		ComputeThread ct = new ComputeThread(msg, this.conn, this.compute);
		ct.start();
	}

}
