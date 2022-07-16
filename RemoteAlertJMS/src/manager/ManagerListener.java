package manager;

import java.util.Vector;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class ManagerListener implements MessageListener {
	private Vector<SubscriberProxy> subs;
	
	public ManagerListener() {
		this.subs = new Vector<SubscriberProxy>();
	}

	@Override
	public void onMessage(Message arg0) {
		MapMessage message = (MapMessage) arg0;
		int id;
		
		try {
			String method = message.getString("method");
			switch(method) {
				case "subscribe":
					id = Integer.valueOf(message.getInt("id")).intValue();
					int port = Integer.valueOf(message.getInt("port")).intValue();
					SubscriberProxy proxy = new SubscriberProxy(port, id);
					this.subs.add(proxy);
					System.out.println("[Listener] Added new proxy with port: " + port + " and id: " + id);
					break;
				case "sendNotification":
					id = Integer.valueOf(message.getInt("id")).intValue();
					int criticality = Integer.valueOf(message.getInt("criticality")).intValue();
					
					for(SubscriberProxy sp: this.subs) {
						if(sp.getID() == id) {
							sp.notifyAlert(criticality);
						}
					}
					System.out.println("[Listener] Allerted all subs whit id: " + id);
					break;
				default:
					System.out.println("[Listener] Invalid Method!");
					break;
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
