package bankoffice;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import interfaces.IManager;

public class ManagerProxy implements IManager {

	@Override
	public void subscribe(int port) {
		try {
			DatagramSocket socket = new DatagramSocket();
			
			String msg = new String("subscribe#" + port);
			DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getLocalHost(), 9000);
			socket.send(packet);
			
			byte[] buff = new byte[100];
			DatagramPacket ack = new DatagramPacket(buff, buff.length);
			socket.receive(ack);
			
			socket.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean submitRequest(int id_client) {
		return false;
	}

}
