package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IBOfficeManager;

public class Client {
    private static int T = 10;
    public static void main(String[] args) {
        Thread[] handles = new Thread[T];
        int i;

        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();

            IBOfficeManager officeManager = (IBOfficeManager)rmiRegistry.lookup("myManager");

            for (i = 0; i < T; i++) {
                handles[i] = new ClientThread(10, officeManager);
                handles[i].start();
            }

            for (i = 0; i < T; i++) {
                handles[i].join();
            }
        } catch(NotBoundException e) {
            e.printStackTrace();
        } catch(InterruptedException e) {
            e.printStackTrace();
        } catch(RemoteException e) {
            e.printStackTrace();
        }
    }
}
