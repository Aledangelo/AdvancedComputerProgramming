package manager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import interfaces.IBankOffice;

public class BankOfficeProxy implements IBankOffice {
	
	private int port;
	
	public BankOfficeProxy(int port) {
		this.port = port;
	}

	@Override
	public boolean sendRequest(int id_client) {
		boolean res = false;
		try {
			try (DatagramSocket socket = new DatagramSocket()) {
				String request = new String("sendRequest#" + id_client);
				
				DatagramPacket packet = new DatagramPacket(request.getBytes(), request.getBytes().length, InetAddress.getLocalHost(), this.port);
				socket.send(packet);
				
				byte[] response = new byte[1000];
				DatagramPacket rpacket = new DatagramPacket(response, response.length);
				socket.receive(rpacket);
				String sres = new String(rpacket.getData(), 0, rpacket.getLength());
				System.out.println("[BankOfficeProxy] Reponse: " + sres);
				
				if(sres.equals("true")) {
					res = true;
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

}
