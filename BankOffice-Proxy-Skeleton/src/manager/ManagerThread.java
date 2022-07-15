package manager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

import interfaces.IManager;

public class ManagerThread extends Thread {
	private DatagramSocket socket;
	private DatagramPacket packet;
	private IManager manager;
	
	public ManagerThread(DatagramSocket socket, DatagramPacket packet, IManager manager) {
		this.socket = socket;
		this.packet = packet;
		this.manager = manager;
	}
	
	@Override
	public void run() {
		String msg = new String(this.packet.getData(), 0, this.packet.getLength());
		
		StringTokenizer tokenizer = new StringTokenizer(msg, "#");
		String method = tokenizer.nextToken();
		switch(method) {
			case "submitRequest":
				int id = Integer.valueOf(tokenizer.nextToken()).intValue();
				boolean res = this.manager.submitRequest(id);
				System.out.println("[ManagerThread] Submit Request with id: " + id);
				String rply;
				
				if(res) {
					rply = new String("true");
				} else {
					rply = new String("false");
				}
				
				DatagramPacket reply = new DatagramPacket(rply.getBytes(), rply.getBytes().length, this.packet.getAddress(), this.packet.getPort());
				
				try {
					this.socket.send(reply);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			case "subscribe":
				int port = Integer.valueOf(tokenizer.nextToken()).intValue();
				
				this.manager.subscribe(port);
				System.out.println("[ManagerThread] Subscribed new BankOffice with port: " + port);
				
				String ack = new String("ack");
				DatagramPacket rAck = new DatagramPacket(ack.getBytes(), ack.getBytes().length, this.packet.getAddress(), this.packet.getPort());
				
				try {
					this.socket.send(rAck);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			default:
				System.out.println("[ManagerThread] Invalid Method!");
				break;
		}
	}
}
