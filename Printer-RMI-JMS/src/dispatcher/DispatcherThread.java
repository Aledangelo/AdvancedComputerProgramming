package dispatcher;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import interfaces.IPrinter;

public class DispatcherThread extends Thread {
	private MapMessage msg;
	
	public DispatcherThread(MapMessage msg) {
		this.msg = msg;
	}
	
	@Override
	public void run() {
		try {
			String pName = this.msg.getString("printerName");
			String docName = this.msg.getString("docName");
			
			System.out.println("[DispatcherThread] DocName: " + docName + "; PrinterName: " + pName);
			
			Registry rmi = LocateRegistry.getRegistry();
			IPrinter printer = (IPrinter) rmi.lookup(pName);
			
			printer.printDoc(docName);
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
