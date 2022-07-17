package newsagency;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

import interfaces.IServerNews;

public class NewsAgency {
	public static void main(String[] args) {
		String type = args[0];
		
		if(!type.equals("sport") && !type.equals("finance")) {
			System.out.println("[NewsAgency] Type not valid!");
			System.out.println("[NewsAgency] Accepted types: 'sport' & 'finance'");
			return;
		}
		
		Random rnd = new Random();
		
		try {
			Registry rmi = LocateRegistry.getRegistry();
			IServerNews server = (IServerNews) rmi.lookup("myServerNews");
			
			String news;
			for(int i = 0; i < 5; i++) {
				Thread.sleep(1000);
				
				news = new String("News_" + rnd.nextInt(101));
				System.out.println("[NewsAgency] Publishing News: " + news + "; type: " + type);
				server.pubNews(news, type);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
