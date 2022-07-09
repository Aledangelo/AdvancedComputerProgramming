package broker;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import interfaces.*;

public class BrokerImpl extends UnicastRemoteObject implements IBroker {
	private static final long serialVersionUID = 763526748L;
	
	private Vector<IAgency> agencies;
	private int indexAgency;
	private int maxAgency;
	
	public BrokerImpl() throws RemoteException {
		this.agencies = new Vector<IAgency>();
		this.indexAgency = 0;
		this.maxAgency = 0;
	}
	
	@Override
	public void subscribe(int port) throws RemoteException {
		System.out.println("[BrokerImpl] New agency subscribed!");
		
		IAgency agency = new AgencyProxy(port);
		this.agencies.add(agency);
		this.maxAgency += 1;
	}
	
	@Override
	public void submit(int op, int amount) throws RemoteException {
		this.indexAgency++;
		int index = this.indexAgency % this.maxAgency;
		
		System.out.println("[BrokerImpl] Forward to agency: " + index);
		
		switch(op) {
			case 1:
				((AgencyProxy) this.agencies.get(index)).buy(amount);
				break;
			case 2:
				((AgencyProxy) this.agencies.get(index)).sell(amount);
				break;
			default:
				System.out.println("[BrokerImpl] Invalid Operation!");
				break;
		}
	}
}
