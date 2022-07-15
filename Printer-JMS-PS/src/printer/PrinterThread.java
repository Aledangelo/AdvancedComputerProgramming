package printer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

import interfaces.IPrinter;

public class PrinterThread extends Thread {
	private DatagramSocket socket;
	private DatagramPacket packet;
	private IPrinter printer;
	
	public PrinterThread(DatagramSocket socket, DatagramPacket packet, IPrinter printer) {
		this.socket = socket;
		this.packet = packet;
		this.printer = printer;
	}
	
	@Override
	public void run() {
		String msg = new String(this.packet.getData(), 0, this.packet.getLength());
		
		StringTokenizer tokenizer = new StringTokenizer(msg, "$");
		String method = tokenizer.nextToken();
		
		switch(method) {
			case "print":
				String docName = tokenizer.nextToken();
				this.printer.printDoc(docName);
				
				String ack = new String("ack");
				DatagramPacket rply = new DatagramPacket(ack.getBytes(), ack.getBytes().length, this.packet.getAddress(), this.packet.getPort());
				
				try {
					this.socket.send(rply);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			default:
				System.out.println("[PrinterThread] Invalid Method!");
				break;
		}
	}
}
