package subscriber;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

import interfaces.ISubscriber;

public class SubscriberThread extends Thread {
	private DatagramSocket socket;
	private DatagramPacket packet;
	private ISubscriber subscriber;
	
	public SubscriberThread(DatagramSocket socket, DatagramPacket packet, ISubscriber subscriber) {
		this.socket = socket;
		this.packet = packet;
		this.subscriber = subscriber;
	}
	
	@Override
	public void run() {
		String msg = new String(this.packet.getData(), 0, this.packet.getLength());
		StringTokenizer tokenizer = new StringTokenizer(msg, "#");
		String method = tokenizer.nextToken();
		switch(method) {
			case "notifyAlert":
				int criticality = Integer.valueOf(tokenizer.nextToken()).intValue();
				this.subscriber.notifyAlert(criticality);
				
				String ack = new String("ack");
				DatagramPacket response = new DatagramPacket(ack.getBytes(), ack.getBytes().length, this.packet.getAddress(), this.packet.getPort());
				try {
					this.socket.send(response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("[SubscriberThread] Invalid Method!");
				break;
		}
	}
}
