package subscriber;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import interfaces.ISubscriber;

public abstract class SubscriberSkeleton implements ISubscriber {
	private int port;
	
	public SubscriberSkeleton(int port) {
		this.port = port;
	}

	public void runSkeleton() {
		try {
			DatagramSocket socket = new DatagramSocket(this.port);
			System.out.println("[SubscriberSkeleton] Listening on port: " + this.port);
			
			while(true) {
				byte[] buff = new byte[65000];
				DatagramPacket packet = new DatagramPacket(buff, buff.length);
				socket.receive(packet);
				
				SubscriberThread st = new SubscriberThread(socket, packet, this);
				st.start();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
