package subscriber;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IManager;
import interfaces.ISubscriber;

public class Subscriber {
    public static void main(String[] args) {
        int componentID = Integer.valueOf(args[0]).intValue();
        int port = Integer.valueOf(args[1]).intValue();
        String pathFile = args[2];

        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IManager manager = (IManager)rmiRegistry.lookup("myManager");

            manager.subscribe(componentID, port);
            System.out.println("[SubscriberServer] Subscribed to manager");

            ISubscriber subscriber = new SubscriberImpl(pathFile);
            SubscriberSkeleton subscriberSkeleton = new SubscriberSkeleton(subscriber, port);
            subscriberSkeleton.runSkeleton();
        } catch(NotBoundException e) {
            e.printStackTrace();
        } catch(RemoteException e) {
            e.printStackTrace();
        }
    }
}
