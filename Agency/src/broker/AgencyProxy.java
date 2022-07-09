package broker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.IAgency;

public class AgencyProxy extends UnicastRemoteObject implements IAgency {
	private static final long serialVersionUID = 4675783765L;
	
	private int port;
	
	public AgencyProxy(int port) throws RemoteException {
		this.port = port;
	}
	
	@Override
	public void buy(int amount) throws RemoteException {
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), this.port);
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			
			out.writeUTF("buy");
			out.writeInt(amount);
			
			in.readUTF();	// ack
			
			System.out.println("[AgencyProxy] Request to buy " + amount + " ticket");
			
			in.close();
			out.close();
			socket.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void sell(int amount) throws RemoteException {
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), this.port);
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			
			out.writeUTF("sell");
			out.writeInt(amount);
			
			in.readUTF();	// ack
			
			System.out.println("[AgencyProxy] Request to sell " + amount + " ticket");
			
			in.close();
			out.close();
			socket.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
