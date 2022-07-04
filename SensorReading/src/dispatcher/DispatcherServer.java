package dispatcher;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IDispatcher;

public class DispatcherServer {
    public static void main(String[] args) {
        try {
            IDispatcher dispatcher = new DispatcherImpl();
            String name = new String("myDispatcher");

            Registry rmiRegistry = LocateRegistry.getRegistry();
            rmiRegistry.rebind(name, dispatcher);

            System.out.println("[DispatcherServer] Service registered with name < " + name + " >");
        } catch(RemoteException e) {
            e.printStackTrace();
        }
    }
}
