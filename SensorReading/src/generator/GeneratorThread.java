package generator;

import java.rmi.RemoteException;
import java.util.Random;

import interfaces.IDispatcher;
import reading.Reading;

public class GeneratorThread extends Thread {
    private IDispatcher dispatcher;

    public GeneratorThread(IDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        Reading reading;
        Random rnd = new Random();
        String type;
        int checkType;

        for(int i = 0; i < 3; i++) {
            checkType = rnd.nextInt(2);
            if(checkType == 0) {
                type = "pressure";
            } else {
                type = "temperature";
            }

            reading = new Reading(type, rnd.nextInt(51));

            try {
                System.out.println("[GeneratorThread] Sending type: " + reading.getType() + " and value: " + reading.getValue());
                this.dispatcher.setReading(reading);
            } catch(RemoteException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(5000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
