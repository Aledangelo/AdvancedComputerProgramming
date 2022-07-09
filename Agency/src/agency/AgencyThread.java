package agency;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import interfaces.IAgency;

public class AgencyThread extends Thread {
	private Socket socket;
	private IAgency agency;
	
	public AgencyThread(Socket socket, IAgency agency) {
		this.socket = socket;
		this.agency = agency;
	}
	
	@Override
	public void run() {
		try {
			DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
			DataInputStream in = new DataInputStream(this.socket.getInputStream());
			
			String op = in.readUTF();
			int amount = in.readInt();
			
			switch(op) {
				case "buy":
					System.out.println("[AgencyThread] Buy " + amount + " tickets");
					this.agency.buy(amount);
					
					out.writeUTF("ack");
					
					break;
				case "sell":
					System.out.println("[AgencyThread] Sell " + amount + " tickets");
					this.agency.sell(amount);
					
					out.writeUTF("ack");
					
					break;
				default:
						System.out.println("[AgencyThread] Invalid Operation!");
			}
			
			in.close();
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
