package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBankOffice extends Remote {
    public boolean serveRequest(int idClient) throws RemoteException;
}
