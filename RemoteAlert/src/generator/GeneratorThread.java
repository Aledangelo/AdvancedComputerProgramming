package generator;

import java.rmi.RemoteException;
import java.util.Random;

import alert.AlertNotification;
import interfaces.IManager;

public class GeneratorThread extends Thread {
    private IManager manager;

    public GeneratorThread(IManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        Random rnd = new Random();

        int componentID = 1 + rnd.nextInt(5);
        int criticality = 1 + rnd.nextInt(3);

        AlertNotification alertNotification = new AlertNotification(componentID, criticality);

        
        try {
            manager.sendNotification(alertNotification);
        } catch(RemoteException e) {
            e.printStackTrace();
        }

        System.out.println("[GeneratorThread] sendNotification(" + componentID + ", " + criticality + ")");
    }
}
