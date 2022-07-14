package storage;

import java.io.*;
import java.net.Socket;

import interfaces.IStorage;

public class StorageThread extends Thread {
	private IStorage storage;
	private Socket socket;
	
	public StorageThread(IStorage storage, Socket socket) {
		this.storage = storage;
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
			DataInputStream in = new DataInputStream(this.socket.getInputStream());
			
			String op = in.readUTF();
			
			switch(op) {
				case "deposit":
					int item = in.readInt();
					
					boolean res = this.storage.deposit(item);
					
					out.writeBoolean(res);
					break;
				default:
					System.out.println("[StorageThread] Invalid Op!");
					break;
			}
			
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
