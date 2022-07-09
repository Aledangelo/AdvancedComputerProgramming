package agency;

import interfaces.*;

import java.rmi.registry.*;
import java.rmi.*;

public class AgencyServer {
	public static void main(String[] args) {
		String name = new String("myBroker");
		int port = Integer.valueOf(args[0]).intValue();
		
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IBroker broker = (IBroker) rmiRegistry.lookup(name);
			
			AgencyImpl agency = new AgencyImpl(10, port);
			
			broker.subscribe(port);
			
			agency.runSkeleton();
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(NotBoundException e) {
			e.printStackTrace();
		}
	}
}
