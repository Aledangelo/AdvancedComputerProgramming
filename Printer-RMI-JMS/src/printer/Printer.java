package printer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Printer {
	public static void main(String[] args) {
		String name = args[0];
		
		try {
			PrinterImpl printer = new PrinterImpl("printerLog.txt");
			
			Registry rmi = LocateRegistry.getRegistry();
			rmi.rebind(name, printer);
			
			System.out.println("[Printer] Started!");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
