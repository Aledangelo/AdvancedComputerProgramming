package bankoffice;

public class BankOffice {
	public static void main(String[] args) {
		int port = Integer.valueOf(args[0]).intValue();
		
		if(port == 9000) {
			System.out.println("[BankOffice] Port already in use from Manager!");
			return;
		}
		
		ManagerProxy proxy = new ManagerProxy();
		proxy.subscribe(port);
		
		System.out.println("[BankOffice] Subscribed to Manager whit port " + port);
		
		BankOfficeImpl office = new BankOfficeImpl(port, "log.txt");
		office.runSkeleton();
	}
}
