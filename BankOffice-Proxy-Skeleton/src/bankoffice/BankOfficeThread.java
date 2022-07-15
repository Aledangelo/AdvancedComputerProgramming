package bankoffice;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

import interfaces.IBankOffice;

public class BankOfficeThread extends Thread {
	private DatagramSocket socket;
	private DatagramPacket packet;
	private IBankOffice office;
	
	public BankOfficeThread(DatagramSocket socket, DatagramPacket packet, IBankOffice office) {
		this.socket = socket;
		this.packet = packet;
		this.office = office;
	}
	
	@Override
	public void run() {
		String msg = new String(this.packet.getData(), 0, this.packet.getLength());
		
		StringTokenizer tokenizer = new StringTokenizer(msg, "#");
		String method = tokenizer.nextToken();
		switch(method) {
			case "sendRequest":
				int id = Integer.valueOf(tokenizer.nextToken()).intValue();
				
				boolean res = this.office.sendRequest(id);
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
			default:
				System.out.println("[BankOfficeThread] Invalid Method!");
				break;
				
		}
	}
}
