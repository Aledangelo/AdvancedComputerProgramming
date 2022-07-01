package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import whiteboard.Observer;
import whiteboard.Shape;
import whiteboard.Whiteboard;

public class WhiteboardImpl extends UnicastRemoteObject implements Whiteboard {

    private int count;
    private Vector<Shape> boardShapes;
    private Vector<Observer> boardObservers;

    protected final static long serialVersionUID = 10;      // used as a checksum

    private void notifyAllObserver() {
        System.out.println("\nNew Shape!");
        int size = boardObservers.size();

        for (int i = 0; i < size; i++) {
            try {
                boardObservers.get(i).observerNotify();
            } catch(RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    // Constructor
    public WhiteboardImpl() throws RemoteException {
        this.count = 0;
        this.boardShapes = new Vector<Shape>();
        this.boardObservers = new Vector<Observer>();
    }

    //  Gestire la lavagna in mutua esclusione

    public void addShape(Shape s) throws RemoteException {
        count = count + 1;
        System.out.println("\nAdding the shape #" + count + " " + s.toString());

        s.draw();
        boardShapes.add(s);

        notifyAllObserver();
    }

    public Vector<Shape> getShape() throws RemoteException {
        return boardShapes;
    }

    public void attachObserver(Observer o) throws RemoteException {
        System.out.println("\nNew observer attached: " + o.toString());
        boardObservers.add(o);
    }
}
