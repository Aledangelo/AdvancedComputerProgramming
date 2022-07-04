package dispatcher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import interfaces.IDispatcher;
import interfaces.IObserver;
import reading.Reading;

public class DispatcherImpl extends UnicastRemoteObject implements IDispatcher {
    private static final long serialVersionUID = 13433212;
    
    private Reading status;

    private Vector<IObserver> observers_temperature;
    private Vector<IObserver> observers_pressure;

    private Lock lock;

    public DispatcherImpl() throws RemoteException {
        this.observers_temperature = new Vector<IObserver>();
        this.observers_pressure = new Vector<IObserver>();
        this.lock = new ReentrantLock();
    }

    @Override
    public void attach(IObserver observer, String type) throws RemoteException {
        switch(type) {
            case "temperature":
                this.observers_temperature.add(observer);
                System.out.println("[DispatcherImpl] New observer added to '" + type + "'");
                break;
            case "pressure":
                this.observers_pressure.add(observer);
                System.out.println("[DispatcherImpl] New observer added to '" + type + "'");
                break;
            default:
                System.out.println("[DispatcherImpl] Invalid Type!");
                break;
        }
    }

    @Override
    public void setReading(Reading reading) throws RemoteException {
        Random rnd = new Random();
        try {
            Thread.sleep(1000 + rnd.nextInt(4001));
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        lock.lock();

        try {
            this.status = reading;
            String type = reading.getType();
            switch(type) {
                case "temperature":
                    if(this.observers_temperature.size() > 0) {
                        for(IObserver o: this.observers_temperature) {
                            o.notifyReading();
                        }
                        System.out.println("[DispatcherImpl] Notified all observers of type: " + type);
                    }
                    break;
                case "pressure":
                    if(this.observers_pressure.size() > 0) {
                        for(IObserver o: this.observers_pressure) {
                            o.notifyReading();
                        }
                        System.out.println("[DispatcherImpl] Notified all observers of type: " + type);
                    }
                    break;
                default:
                    System.out.println("[DispatcherImpl] Invalid Type!");
                    break;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Reading getReading() throws RemoteException {
        return this.status;
    }
}
