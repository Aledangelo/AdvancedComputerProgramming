package manager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import interfaces.IManager;

public abstract class ManagerSkeleton implements IManager {
	private int port;
	
	public ManagerSkeleton(int port) {
		this.port = port;
	}
	
	public void runSkeleton() {
		System.out.println("[ManagerSkeleton] Listening on port:" + this.port);
		try {
			DatagramSocket socket = new DatagramSocket(this.port);
			
			while(true) {
				byte[] buff = new byte[65000];
				DatagramPacket request = new DatagramPacket(buff, buff.length);
				socket.receive(request);
				
				ManagerThread mt = new ManagerThread(socket, request, this);
				mt.start();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
