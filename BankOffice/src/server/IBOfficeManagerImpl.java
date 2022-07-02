package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import interfaces.IBOfficeManager;
import interfaces.IBankOffice;

public class IBOfficeManagerImpl extends UnicastRemoteObject implements IBOfficeManager {
    private Vector<IBankOffice> bankOffices;
    private static final long serialVersionUID = 1L;

    public IBOfficeManagerImpl() throws RemoteException {
        this.bankOffices = new Vector<IBankOffice>();
    }

    @Override
    public void subscribe(IBankOffice bo) throws RemoteException {
        bankOffices.add(bo);
        System.out.println("\nNew Banck Office subscribed");
    }

    @Override
    public boolean submitRequest(int idClient) throws RemoteException {
        boolean flag = false;
        int i = 0;

        while ((!flag) && (i < bankOffices.size())) {
            flag = bankOffices.get(i).serveRequest(idClient);
            i++;
        }

        System.out.println("\nServed request fomr client " + idClient + ". Result: " + flag);

        return flag;
    }
    
}
