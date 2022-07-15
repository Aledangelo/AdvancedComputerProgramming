package printer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import interfaces.IPrinter;

public abstract class PrinterSkeleton implements IPrinter {
	private int port;
	
	public PrinterSkeleton(int port) {
		this.port = port;
	}
	
	public void runSkeleton() {
		System.out.println("[Printer] Listening on port: " + port);
		
		try {
			DatagramSocket dSocket = new DatagramSocket(this.port);
			
			while(true) {
				byte[] buff = new byte[65000];
				
				DatagramPacket pack = new DatagramPacket(buff, buff.length);
				dSocket.receive(pack);
				
				PrinterThread pt = new PrinterThread(dSocket, pack, this);
				pt.start();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
