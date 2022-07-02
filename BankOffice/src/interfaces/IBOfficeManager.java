package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBOfficeManager extends Remote {
    public boolean submitRequest(int idClient) throws RemoteException;
    public void subscribe(IBankOffice bo) throws RemoteException;
}
