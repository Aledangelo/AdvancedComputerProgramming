package observer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IDispatcher;
import interfaces.IObserver;

public class ObserverServer {
    public static void main(String[] args) {
        String type = args[0];
        String pathFile = args[1];

        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IDispatcher dispatcher = (IDispatcher) rmiRegistry.lookup("myDispatcher");

            IObserver observer = new ObserverImpl(dispatcher, pathFile);
            dispatcher.attach(observer, type);

            System.out.println("[ObserverServer] Attahced to dispatcher!");
        } catch(RemoteException e) {
            e.printStackTrace();
        } catch(NotBoundException e) {
            e.printStackTrace();
        }
    }
}
