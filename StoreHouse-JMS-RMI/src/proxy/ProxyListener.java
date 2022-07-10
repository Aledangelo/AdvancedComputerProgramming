package proxy;

import java.rmi.RemoteException;

import javax.jms.*;

import interfaces.*;

public class ProxyListener implements MessageListener {
	
	private IServer server;
	
	public ProxyListener(IServer server) {
		this.server = server;
	}

	@Override
	public void onMessage(Message arg0) {
		System.out.println("[Listener] New Message!");
		
		MapMessage msg = (MapMessage) arg0;
		
		try {
			String type = msg.getString("type");
			
			switch(type) {
				case "insert":
					int id = msg.getInt("id");
					this.server.insert(id);
					
					System.out.println("[Listener] Insert " + id);
					
					break;
				case "get":
					int res = this.server.get();
					
					System.out.println("[Listener] Get " + res);
					
					Sender sender = new Sender(res);
					sender.start();
					
					break;
				default:
					System.out.println("[Listener] Invalid Operation!");
			}
		} catch (JMSException | RemoteException e) {
			e.printStackTrace();
		}
	}

}
