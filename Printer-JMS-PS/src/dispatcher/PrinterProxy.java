package dispatcher;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import interfaces.IPrinter;

public class PrinterProxy implements IPrinter {
	private int port;
	private DatagramSocket socket;
	
	public PrinterProxy(int port) {
		this.port = port;
		
		try {
			this.socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void printDoc(String docName) {
		String request = new String("print$" + docName);
		
		try {
			DatagramPacket packet = new DatagramPacket(request.getBytes(), request.getBytes().length, InetAddress.getLocalHost(), this.port);
			this.socket.send(packet);
			
			byte[] ack = new byte[100];
			DatagramPacket response = new DatagramPacket(ack, ack.length);
			this.socket.receive(response);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
