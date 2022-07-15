package manager;

import java.util.Vector;

public class ManagerImpl extends ManagerSkeleton {
	
	private Vector<BankOfficeProxy> offices;
	
	public ManagerImpl(int port) {
		super(port);
		
		this.offices = new Vector<BankOfficeProxy>();
	}

	@Override
	public boolean submitRequest(int id_client) {
		for(BankOfficeProxy office: this.offices)  {
			if(office.sendRequest(id_client)) {
				System.out.println("[ManagerImpl] Request submitted!");
				return true;
			}
		}
		return false;
	}

	@Override
	public void subscribe(int port) {
		BankOfficeProxy proxy = new BankOfficeProxy(port);
		this.offices.add(proxy);
		System.out.println("[ManagerImpl] Added new BankOffice with port: " + port);
	}

}
