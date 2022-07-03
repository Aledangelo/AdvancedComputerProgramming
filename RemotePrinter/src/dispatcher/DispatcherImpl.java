package dispatcher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import interfaces.IDispatcher;
import interfaces.IPrinter;

public class DispatcherImpl extends UnicastRemoteObject implements IDispatcher {
    private static final long serialVersionUID = 100;

    private Vector<IPrinter> printers;

    public DispatcherImpl() throws RemoteException {
        this.printers = new Vector<IPrinter>();
    }

    @Override
    public void addPrinter(String ip, int port) throws RemoteException {
        IPrinter printer = new PrinterProxy(ip, port);
        printers.add(printer);
        System.out.println("[DispatcherImpl] Added new printer! " + ip + ":" + port);
    }

    @Override
    public boolean printRequest(String docName) throws RemoteException {
        boolean check = false;
        int i = 0;

        while(!check && i < printers.size()) {
            check = printers.get(i).print(docName);
            i++;
        }

        return check;
    }
}
