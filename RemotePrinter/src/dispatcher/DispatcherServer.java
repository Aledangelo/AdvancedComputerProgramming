package dispatcher;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IDispatcher;

public class DispatcherServer {
    public static void main(String[] args) {
        try {
            IDispatcher dispatcher = new DispatcherImpl();

            Registry rmiRegistry = LocateRegistry.getRegistry();
            rmiRegistry.rebind("myDispatcher", dispatcher);

            System.out.println("[Server] Dispatcher started!");

        } catch(RemoteException e) {
            e.printStackTrace();
        }
    }
}
