package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import interfaces.IServer;

public class ServerProxy implements IServer {
	private int port;
	
	public ServerProxy(int port) {
		this.port = port;
	}

	@Override
	public boolean book(int id, boolean restriction) {
		boolean res = false;
		
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), this.port);
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			
			out.writeUTF("book");
			out.writeInt(id);
			out.writeBoolean(restriction);
			
			res = in.readBoolean();
			
			System.out.println("[ServerProxy] Result to book request: " + res);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return res;
	}
}
