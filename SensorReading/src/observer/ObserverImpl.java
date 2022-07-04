package observer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.IDispatcher;
import interfaces.IObserver;
import reading.Reading;

public class ObserverImpl extends UnicastRemoteObject implements IObserver {
    private static final long serialVersionUID = 345672;

    private IDispatcher dispatcher;
    private String pathFile;

    public ObserverImpl(IDispatcher dispatcher, String pathFile) throws RemoteException {
        this.dispatcher = dispatcher;
        this.pathFile = pathFile;
    }

    @Override
    public void notifyReading() throws RemoteException {
        Reading reading = this.dispatcher.getReading();
        String type = reading.getType();
        int value = reading.getValue();
        System.out.println("[ObserverImpl] Type: " + type +"; Value: " + value);

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(this.pathFile)));
            pw.println("Reading | Type: " + type + "; Value: " + value);
            pw.flush();
            pw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
