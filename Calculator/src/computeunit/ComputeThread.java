package computeunit;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

public class ComputeThread extends Thread {
	private MapMessage message;
	private QueueConnection conn;
	private Compute compute;
	
	public ComputeThread(MapMessage message, QueueConnection conn, Compute compute) {
		this.message = message;
		this.conn = conn;
		this.compute = compute;
	}
	
	@Override
	public void run() {
		try {
			QueueSession session = this.conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Queue replQueue = (Queue) this.message.getJMSReplyTo();
			QueueSender sender = session.createSender(replQueue);
			MapMessage reply = session.createMapMessage();
			
			int res = this.compute.getRes(this.message.getString("type"), this.message.getInt("op1"), this.message.getInt("op2"));
			
			reply.setInt("res", res);
			sender.send(reply);
			
			sender.close();
			session.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
