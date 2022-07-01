package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import whiteboard.Observer;
import whiteboard.Whiteboard;

public class WhiteBClient2 {
    public static void main(String[] args) {
        // It obtain reference to remote Whiteboard
        // It create callback object
        // Call remote method

        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            Whiteboard board = (Whiteboard)rmiRegistry.lookup("myWhiteboard");
            System.out.println("\nGot reference < myWhiteboard");
            System.out.println("\n" + board.toString());

            Observer observer = new ObserverImpl(board);

            System.out.println("\nObserver with ref: " + observer.toString());

            board.attachObserver(observer);
        } catch(NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
