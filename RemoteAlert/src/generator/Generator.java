package generator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IManager;

public class Generator {
    public static void main(String[] args) {
        int i = 0;
        Thread[] threads = new Thread[3];
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IManager manager = (IManager)rmiRegistry.lookup("myManager");

            for(i = 0; i < 3; i++) {
                threads[i] = new GeneratorThread(manager);
                threads[i].start();
            }

            for(i = 0; i < 3; i++) {
                try {
                    threads[i].join();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch(NotBoundException e) {
            e.printStackTrace();
        } catch(RemoteException e) {
            e.printStackTrace();
        }
    }
}
