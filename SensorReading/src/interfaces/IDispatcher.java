package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import reading.Reading;

public interface IDispatcher extends Remote {
    public void setReading(Reading reading) throws RemoteException;
    public Reading getReading() throws RemoteException;
    public void attach(IObserver observer, String type) throws RemoteException;
}
