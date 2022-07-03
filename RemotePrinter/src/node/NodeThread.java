package node;

import java.rmi.RemoteException;
import java.util.Random;

import interfaces.IDispatcher;

public class NodeThread extends Thread {
    private IDispatcher dispatcher;

    public NodeThread(IDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void run() {
        Random rnd = new Random();
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(3000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            int n = 1 + rnd.nextInt(50);

            String docName = new String("doc" + n);
            
            try {
                dispatcher.printRequest(docName);
            } catch(RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
