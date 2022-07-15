package bankoffice;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import interfaces.IBankOffice;

public abstract class BankOfficeSkeleton implements IBankOffice {
	
	private int port;
	
	public BankOfficeSkeleton(int port) {
		this.port = port;
	}
	
	public void runSkeleton() {
		System.out.println("[BankOfficeSkeleton] Listening on port: " + this.port);
		
		try {
			DatagramSocket socket = new DatagramSocket(this.port);
			
			while(true) {
				byte[] buff = new byte[6500];
				DatagramPacket packet = new DatagramPacket(buff, buff.length);
				socket.receive(packet);
				
				BankOfficeThread bt = new BankOfficeThread(socket, packet, this);
				bt.start();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
