package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import interfaces.IServer;

public abstract class ServerSkeleton implements IServer {
	private int port;
	
	public ServerSkeleton(int port) {
		this.port = port;
	}
	
	public void runSkeleton() {
		System.out.println("[ServerSkeleton] Started!");
		
		ExecutorService exe = Executors.newFixedThreadPool(5);
		
		try {
			try (ServerSocket serverSocket = new ServerSocket(this.port)) {
				while(true) {
					Socket socket = serverSocket.accept();
					ServerThread st = new ServerThread(socket, this);
					exe.execute(st);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
