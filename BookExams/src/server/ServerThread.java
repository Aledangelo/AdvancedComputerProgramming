package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import interfaces.IServer;

public class ServerThread extends Thread {
	private Socket socket;
	private IServer server;
	
	public ServerThread(Socket socket, IServer server) {
		this.socket = socket;
		this.server = server;
	}
	
	@Override
	public void run() {
		try {
			DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
			DataInputStream in = new DataInputStream(this.socket.getInputStream());
			
			String op = in.readUTF();
			switch(op) {
				case "book":
					int id = in.readInt();
					boolean type = in.readBoolean();
					boolean res = this.server.book(id, type);
					
					out.writeBoolean(res);
					
					break;
				default:
					System.out.println("[ServerThread] Invalid Op!");
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
