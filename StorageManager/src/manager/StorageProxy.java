package manager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import interfaces.IStorage;

public class StorageProxy implements IStorage {
	
	private int port;
	
	public StorageProxy(int port) {
		this.port = port;
	}

	@Override
	public boolean deposit(int item) {
		boolean res = false;
		
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), this.port);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			
			out.writeUTF("deposit");
			out.writeInt(item);
			
			res = in.readBoolean();
			
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
}
