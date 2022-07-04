package generator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IDispatcher;

public class Generator {
    public static void main(String[] args) {
        Thread[] threads = new Thread[3];
        int i;

        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IDispatcher dispatcher = (IDispatcher) rmiRegistry.lookup("myDispatcher");

            for(i = 0; i < 3; i++) {
                threads[i] = new GeneratorThread(dispatcher);
                threads[i].start();
            }

            for(i = 0; i < 3; i++) {
                try {
                    threads[i].join();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch(RemoteException e) {
            e.printStackTrace();
        } catch(NotBoundException e) {
            e.printStackTrace();
        }
    }
}
