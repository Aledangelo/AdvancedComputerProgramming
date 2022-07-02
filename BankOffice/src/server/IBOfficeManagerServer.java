package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IBOfficeManager;

public class IBOfficeManagerServer {
    public static void main(String[] args) {
        String name = new String("myManager");

        try {
            System.out.println("\nCreating Object...");
            IBOfficeManager manager = new IBOfficeManagerImpl();

            Registry rmiRegistry = LocateRegistry.getRegistry();
            rmiRegistry.rebind(name, manager);

            System.out.println("\nObject registered with name: " + name);
        } catch(RemoteException e) {
            e.printStackTrace();
        }
    }
}
