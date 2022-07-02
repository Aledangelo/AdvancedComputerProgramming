package server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IBOfficeManager;
import interfaces.IBankOffice;

public class BankOfficeServer {
    public static void main(String[] args) {
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IBOfficeManager manager = (IBOfficeManager)rmiRegistry.lookup("myManager");

            IBankOffice bankOffice = new BankOfficeImpl();
            manager.subscribe(bankOffice);
        } catch(NotBoundException e) {
            e.printStackTrace();
        } catch(RemoteException e) {
            e.printStackTrace();
        }
    }
}
