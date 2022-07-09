package agency;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import interfaces.IAgency;

public abstract class AgencySkeleton implements IAgency {
	
	private int port;
	
	public AgencySkeleton(int port) {
		this.port = port;
	}
	
	public void runSkeleton() {
		
		try {
			try (ServerSocket serverSocket = new ServerSocket(port)) {
				System.out.println("[AgencySkeleton] Listening on port " + this.port);
				
				while(true) {
					Socket socket = serverSocket.accept();
					AgencyThread at = new AgencyThread(socket, this);
					at.start();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
