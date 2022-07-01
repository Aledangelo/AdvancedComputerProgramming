package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import whiteboard.Observer;
import whiteboard.Whiteboard;
import whiteboard.Shape;

public class ObserverImpl extends UnicastRemoteObject implements Observer {
    private static final long serialVersionUID = 1L;
    private Whiteboard remoteWhiteboard;    // Reference to remote Whiteboard

    public ObserverImpl(Whiteboard w) throws RemoteException {
        this.remoteWhiteboard = w;
    }

    public void observerNotify() throws RemoteException {
        System.out.println("\nNotified! Current board content: ");

        // Remote invocation on whiteborad
        Vector<Shape> v = remoteWhiteboard.getShape();
        int size = v.size();

        for (int i = 0; i < size; i++) {
            ((Shape)v.get(i)).draw();
        }
    }
}
