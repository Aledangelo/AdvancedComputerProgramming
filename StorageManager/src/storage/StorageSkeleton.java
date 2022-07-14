package storage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import interfaces.IStorage;

public abstract class StorageSkeleton implements IStorage {
	private int port;
	
	public StorageSkeleton(int port) {
		this.port = port;
	}

	public void runSkeleton() {
		
		try {
			try (ServerSocket serverSocket = new ServerSocket(this.port)) {
				while(true) {
					Socket socket = serverSocket.accept();
					StorageThread st = new StorageThread(this, socket);
					st.start();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
