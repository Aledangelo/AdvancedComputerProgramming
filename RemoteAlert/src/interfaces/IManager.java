package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import alert.AlertNotification;

public interface IManager extends Remote {
    public void sendNotification(AlertNotification alertNotification) throws RemoteException;
    public void subscribe(int componentID, int port) throws RemoteException;
}
