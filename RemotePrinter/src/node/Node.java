package node;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IDispatcher;

public class Node {
    public static void main(String[] args) {
        Thread[] handles = new Thread[5];
        int i;

        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IDispatcher dispatcher = (IDispatcher)rmiRegistry.lookup("myDispatcher");

            for (i = 0; i < 5; i++) {
                handles[i] = new NodeThread(dispatcher);
                handles[i].start();
            }

            for (i = 0; i < 5; i++) {
                try {
                    handles[i].join();
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
