package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

public class ClientThread extends Thread {

	private int nRequest;
	private int port;
	private Random rnd;
	
	public ClientThread(int nRequest, int port) {
		this.nRequest = nRequest;
		this.port = port;
		this.rnd = new Random();
	}
	
	@Override
	public void run() {
		try {
			try (DatagramSocket socket = new DatagramSocket()) {
				for(int i = 0; i < this.nRequest; i++) {
					String request = new String("submitRequest#" + this.rnd.nextInt(101));
					DatagramPacket packet = new DatagramPacket(request.getBytes(), request.getBytes().length, InetAddress.getLocalHost(), this.port);
					socket.send(packet);
					
					byte[] res = new byte[100];
					DatagramPacket response = new DatagramPacket(res, res.length);
					socket.receive(response);
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
