package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import whiteboard.Whiteboard;

public class WhiteboardServer {
    public static void main(String[] args) {
        // Remote object creation and registration

        String name = new String("myWhiteboard");

        try {
            System.out.println("\nCreating Object...");
            Whiteboard board = new WhiteboardImpl();
            
            System.out.println("\n" + board.toString());

            Registry rmiRegistry = LocateRegistry.getRegistry();    // I get the reference to the registry
            rmiRegistry.rebind(name, board);    // I use the registry to associate the remote object with a symbolic name

            System.out.println("Object registered whit name: " + name);
        } catch(RemoteException e) {
            e.printStackTrace();
        }
    }
}
