package dispatcher;

import javax.jms.JMSException;
import javax.jms.MapMessage;

public class DispatcherThread extends Thread {
	private MapMessage message;
	
	public DispatcherThread(MapMessage message) {
		this.message = message;
	}
	
	@Override
	public void run() {
		try {
			String docName = this.message.getString("docName");
			int port = this.message.getInt("port");
			
			System.out.println("[DispatcherThread] docName: " + docName + "; port: " + port);
			
			PrinterProxy proxy = new PrinterProxy(port);
			proxy.printDoc(docName);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
