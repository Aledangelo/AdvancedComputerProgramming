package manager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IManager;

public class Manager {
    public static void main(String[] args) {
        try {
            IManager manager = new ManagerImpl();

            Registry rmiRegistry = LocateRegistry.getRegistry();
            rmiRegistry.rebind("myManager", manager);
            System.out.println("[Manager] Method < myManager > registered!");
        } catch(RemoteException e) {
            e.printStackTrace();
        }
    }
}
