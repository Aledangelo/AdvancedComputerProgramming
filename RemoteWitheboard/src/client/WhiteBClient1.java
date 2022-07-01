package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

import whiteboard.Whiteboard;
import whiteboard.Shape;

public class WhiteBClient1  {
    public static void main(String[] args) {
        Random rnd = new Random();
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            Whiteboard board = (Whiteboard)rmiRegistry.lookup("myWhiteboard");
            System.out.println("\nGot reference < myWhiteboard");
            System.out.println("\n" + board.toString());

            Shape s;
            int x;

            for (int i = 0; i < 4; i++) {
                x = rnd.nextInt(2);
                if (x > 0) {
                    s = new Triangle();
                } else {
                    s = new Square();
                }

                System.out.println("\nAdding new shape: " + s.toString());

                // Remote invocation
                board.addShape(s);

                Thread.sleep(1000);
            }
        } catch(NotBoundException e) {
            e.printStackTrace();
        } catch(RemoteException e) {
            e.printStackTrace();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
