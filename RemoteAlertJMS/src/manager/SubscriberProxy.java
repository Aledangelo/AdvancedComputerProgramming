package manager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import interfaces.ISubscriber;

public class SubscriberProxy implements ISubscriber {
	private int port;
	private int id;
	
	public SubscriberProxy(int port, int id) {
		this.port = port;
		this.id = id;
	}

	@Override
	public void notifyAlert(int criticality) {
		try {
			try (DatagramSocket socket = new DatagramSocket()) {
				String request = new String("notifyAlert#" + criticality);
				DatagramPacket packet = new DatagramPacket(request.getBytes(), request.getBytes().length, InetAddress.getLocalHost(), this.port);
				socket.send(packet);
				
				byte[] buff = new byte[1000];
				DatagramPacket ack = new DatagramPacket(buff, buff.length);
				socket.receive(ack);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setID(int id) {
		this.id = id;
		
	}

	@Override
	public int getID() {
		return this.id;
	}
	
	

}
