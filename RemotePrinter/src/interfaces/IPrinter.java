package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPrinter extends Remote {
    public boolean print(String docName) throws RemoteException;
}
